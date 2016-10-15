var fs = require('fs');
var net = require('net');
var crypto = require('crypto');
var events = require('events');

var PORT_MIN_RANGE = 9000
var PORT_MAX_RANGE = 9010

var ZEROES_REQ = 10;

const DEBUG_FUNC = false;
const DEBUG_CONTENT = false;

var INITIAL_LEDGER = {
  '404f8fd144': 132,
  '07f946d659': 46,
  'c214b8bfb6': 8
};

// Constructor: takes a public key, a private key, and a connection.
function CoinClient(privKeyFile, pubKeyFile, conn) {
  if (DEBUG_FUNC) this.log(" > CoinClient()");
  this.privKey = fs.readFileSync(privKeyFile).toString('ascii');
  this.pubKey = fs.readFileSync(pubKeyFile).toString('ascii');
  this.ledger = INITIAL_LEDGER;
  this.srvr = net.createServer();
  this.srvr.on('connection', this.makeMessageReceiver(this));
  this.srvr.listen(conn.port);
  // Get the latest version of the ledger
  this.broadcast({type: 'getledger'});
}

// Inheritance in JavaScript -- all instances of CoinClient
// will inherit the functionality of EventEmitter.
CoinClient.prototype = new events.EventEmitter();

// On receiving a broadcast message, trigger the appropriate event handler
CoinClient.prototype.makeMessageReceiver = function(self) {
  return function(client) {
    client.on('data', function(data) {
      var trans = JSON.parse(data);
      // Ignore your own messages
      if (trans.id === self.getID()
            && trans.type !== 'transfer'
            && trans.type !== 'proof')
        return;
      self.emit(trans.type, self, trans);
    });
  };
}

// On a request for the latest ledger, broadcast what you have.
CoinClient.prototype.on('getledger', function(self, trans) {
  self.broadcast({type:'shareledger', 'ledger':self.ledger});
});

// On receiving an updated ledger, update your own copy
CoinClient.prototype.on('shareledger', function(self, trans) {
  if (DEBUG_FUNC) this.log(" > shareledger()");
  self.ledger = trans.ledger;
  self.log("Transaction ledger updated");
  self.showLedger();
});

CoinClient.prototype.on('transfer', function(self, trans) {
  if (DEBUG_FUNC) this.log(" > transfer()");
  self.validateTransfer(trans);
});

CoinClient.prototype.on('reject', function(self, trans) {
  if (DEBUG_FUNC) this.log(" > reject()");
  self.log("Reject from " + trans.id + ": " + trans.msg);
});


// Broadcast a transfer of money to all parties
CoinClient.prototype.transferFunds = function(details) {
  if (DEBUG_FUNC) this.log(" > transferFunds()");
  var trans = { type: 'transfer'};
  var msg = JSON.stringify(details);
  trans.details = details;
  trans.pubKey = this.pubKey;
  trans.id = this.getID();
  
  // Create sig
  var sign = crypto.createSign('RSA-SHA256');
  sign.update(msg);
  trans.sig = sign.sign(this.privKey, 'hex');

  if (DEBUG_CONTENT) this.log(trans);
  this.broadcast(trans);
}



CoinClient.prototype.validateTransfer = function(trans) {
  if (DEBUG_FUNC) this.log(" > validateTransfer()");
  var msg = JSON.stringify(trans.details);

  // Sig verification
  var verifier = crypto.createVerify('RSA-SHA256');
  verifier.update(msg);
  const verify = verifier.verify(trans.pubKey, trans.sig, 'hex');
  if (!verify) {
    if (DEBUG_FUNC) this.log(" > Bad transaction signature");
    this.broadcast({type: 'reject', msg: 'bad signature'});
    return;
  } else {
    this.log("Valid transaction sig from " + trans.id);
  }

  var coins = this.ledger[trans.id];

  // Create new ledger
  var newLedger = {};
  for (var id in this.ledger) {
    newLedger[id] = this.ledger[id];
  }
  // All coins must be accounted for in the transaction
  newLedger[trans.id] = 0;
  // Retaining a copy of the old ledger
  newLedger.prev = this.ledger;

  // Update transaction
  for (var id in trans.details) {
    coins -= trans.details[id];
    if (coins < 0) {
      this.broadcast({type: 'reject', msg: 'insufficient funds'});
      return;
    }
    newLedger[id] += trans.details[id];
  }

  // Pay the miner
  newLedger[this.id] = newLedger[this.id] + 1; // New coin is created

  this.mineProof(newLedger, 0);
}


CoinClient.prototype.mineProof = function(newLedger, start) {
  if (DEBUG_FUNC) this.log(" > mineProof()");
  if (this.chainLength(this.ledger) >= this.chainLength(newLedger)) return;
  
  var ledge = JSON.stringify(newLedger);
  var proof = start;
  var hashResult;
  var found = false;
  var leadingZeroes = Array(ZEROES_REQ+1).join('0');
  proof = start
  while (!found) {
    hashResult = this.hash(ledge + proof);
    // ensure leading zeroes
    if (leadingZeroes === this.hash(ledge + proof).substring(0, ZEROES_REQ)) {
      found = true;
    } else {
      proof++;
    }
  }
  this.log("Found hash with proof: " + proof)
  this.ledger = newLedger;
  this.broadcast({type: 'proof', ledger: newLedger, proof: proof});
  // this.showLedger();
}

CoinClient.prototype.hash = function(s) {
  if (DEBUG_FUNC) this.log(" > hash()");
  var i, ch;
  var binStr = "";
  var h = crypto.createHash('sha256').update(s).digest('hex');
  for (i=0; i<h.length; i++) {
    digits = parseInt(h[i],16).toString(2);
    while (digits.length < 4) { digits = '0' + digits; }
    binStr += digits;
  }
  return binStr;
}


CoinClient.prototype.chainLength = function(ledger) {
  if (ledger) return 1 + this.chainLength(ledger.prev);
  else return 0;
}


// Creating a more readable ID (but anonymized) string
CoinClient.prototype.getID = function() {
  this.id = this.id || crypto.createHash('sha256').update(this.pubKey).digest('hex').slice(0,10);
  return this.id;
}

CoinClient.prototype.getCoins = function() {
  return this.ledger[this.getID()];
}

CoinClient.prototype.showLedger = function() {
  var keys = Object.keys(this.ledger);
  for (var i in keys) {
    var id = keys[i];
    if (id !== 'prev')
      this.log(id + " has " + this.ledger[id]);
  }
}

CoinClient.prototype.broadcast = function(msgObj) {
  msgObj.id = this.getID();
  var data = JSON.stringify(msgObj);
  for (p=PORT_MIN_RANGE; p<PORT_MAX_RANGE; p++) {
    (function() { // Creating a closure, because JavaScript.
      var client = net.connect({'port':p}, function() {
        client.write(data);
      });
      client.on('error', function(e) {
        //console.error(e);
      });
    })(); // ending closure
  }
}

CoinClient.prototype.log = console.log;

/*********** EXPORTS FOR THIS MODULE **************/

exports.CoinClient = CoinClient;

