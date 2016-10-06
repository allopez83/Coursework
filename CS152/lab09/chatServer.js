var net = require('net');
var eol = require('os').EOL;

var srvr = net.createServer();
var clientList = [];

srvr.on('connection', function(client) {
  client.name = client.remoteAddress + ':' + client.remotePort;
  client.write('Welcome, ' + client.name + eol);
  clientList.push(client);

  client.on('data', function(data) {
    input(data, client);
  });
});

// All user input goes through
function input(data, client) {
  var dataStr = data.toString();
  console.log("input: " + dataStr.substring(0, dataStr.length - 2)) // remove newline

  if (dataStr[0] == '\\'){
    console.log("input(): command recieved");
    command(data, client);
  }
  else {
    console.log("input(): broadcast recieved");
    broadcast(data, client);
  }
}

// Backslash command
function command(data, client) {
  /*
  \list: names of all users
  \rename $NAME: change own name to $NAME
  \private $NAME $MSG: send $MSG only to $NAME
  */
  var dataStr = data.toString();

  var end = 0;
  var params = "";
  if (dataStr.includes(' ')) {
    console.log("command(): has spaces");
    end = dataStr.indexOf(' ');
    params = dataStr.substring(end + 1, dataStr.length - 2); // remove newline
  } else {
    console.log("command(): no spaces");
    end = dataStr.length - 2;
  }
  
  var commandIn = dataStr.substring(1, end);
  console.log(commandIn + ' : ' + params)
  
  if (commandIn == "list") 
    listNames(client);
  else if (commandIn == "rename")
    rename(params, client);
  else if (commandIn == "private")
    privateMsg(params, client);
  else
    console.log("command(): unknown command input!");
}

function listNames(client) {
  for (var i in clientList)
    client.write(' > ' + clientList[i].name + '\n');
}

function rename(data, client) {
  client.name = data
  // var i = clientList.indexOf(client)
  // clientList[i].name = data
  // console.log(clientList[i].name)
}

function privateMsg(data, client) {
  var split = data.indexOf(' ')
  var text = data.substring(split + 1, data.length)
  var recvName = data.substring(0, split);
    
  for (var i in clientList) {
    // console.log(i)
    if (clientList[i].name == recvName)
      var recvClient = clientList[i]
  }

  // console.log(split)
  // console.log(text)
  // console.log(recvName)
  // console.log(clientList.indexOf(recvName))
  // console.log(recvClient.name)

  recvClient.write(client.name + " PM to you: " + text + '\n')
}

// Send regular message
function broadcast(data, client) {
  for (var i in clientList) {
    if (client !== clientList[i]) {
      clientList[i].write(client.name + " says: " + data);
    }
  }
}

srvr.listen(9000);
