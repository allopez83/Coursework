/*

LINUX SOCKET API:

Using Linux and C, create two programs: client and server and using TCP/IP socket
interface implement the following:

The server should take a port number as a command line argument and it should open a socket using TCP/IP and wait for connections on the post specified. The client should take a target port and a string as a command line arguments and it should connect to that port and send a message containing the string. The server should print the string as it receives it and close the socket and exit with success code 0. The client should also close the socket after sending the message with the string on it.

>server 9000& (runs the server waiting for connections at port 9000)
>client 9000 “My message”

Here server should print My message and both programs should exit cleanly.

*/

#include <stdio.h> //printf
#include <string.h> //strlen
#include <sys/socket.h> //socket
#include <arpa/inet.h> //inet_addr

int main(int argc , char *argv[])
{
    int sock, port;
    struct sockaddr_in server;
    char *message;

    port = strtol(argv[1], NULL, 10);
    message = argv[2];

    //Create socket
    sock = socket(AF_INET , SOCK_STREAM , 0);
    if (sock == -1)
    {
        printf("Could not create socket");
    }
    puts("Socket created");

    server.sin_addr.s_addr = inet_addr("127.0.0.1");
    server.sin_family = AF_INET;
    server.sin_port = htons(port);
 
    //Connect to remote server
    if (connect(sock , (struct sockaddr *)&server , sizeof(server)) < 0)
    {
        perror("connect failed. Error");
        return 1;
    }
    
    //Send some data
    puts("Sending message");
    send(sock, message, strlen(message), 0);

    // Exit
    puts("Close connection");
    close(sock);

    return 0;
}
