var net = require('net');
var eol = require('os').EOL;

var srvr = net.createServer();
var clientList = [];

srvr.on('connection', function(client) {
  client.name = client.remoteAddress + ':' + client.remotePort;
  client.write('Welcome, ' + client.name + eol);
  clientList.push(client);

  // TODO: change broadcast() to input()
  client.on('data', function(data) {
    broadcast(data, client);
  });
});

// All user input goes through
function input(data, client) {
  if (data[0] == '\\')
    command(data, client)
  else
    broadcast(data, client)
}

// Backslash command
function command(data, client) {
  /*

  \list: names of all users
  \rename $NAME: change own name to $NAME
  \private $NAME $MSG: send $MSG only to $NAME

  */
}

// Send regular message
function broadcast(data, client) {
  for (var i in clientList) {
    if (client !== clientList[i]) {
      clientList[i].write(client.name + " says " + data);
    }
  }
}

srvr.listen(9000);
