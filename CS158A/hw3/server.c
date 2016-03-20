/*

LINUX SOCKET API:

Using Linux and C, create two programs: client and server and using TCP/IP socket
interface implement the following:

The server should take a port number as a command line argument and it should open a socket using TCP/IP and wait for connections on the post specified. The client should take a target port and a string as a command line arguments and it should connect to that port and send a message containing the string. The server should print the string as it receives it and close the socket and exit with success code 0. The client should also close the socket after sending the message with the string on it.

>server 9000& (runs the server waiting for connections at port 9000)
>client 9000 “My message”

Here server should print My message and both programs should exit cleanly.

*/

