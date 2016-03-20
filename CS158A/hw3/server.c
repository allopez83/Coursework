/*

LINUX SOCKET API:

Using Linux and C, create two programs: client and server and using TCP/IP socket
interface implement the following:

The server should take a port number as a command line argument and it should open a socket using TCP/IP and wait for connections on the post specified. The client should take a target port and a string as a command line arguments and it should connect to that port and send a message containing the string. The server should print the string as it receives it and close the socket and exit with success code 0. The client should also close the socket after sending the message with the string on it.

>server 9000& (runs the server waiting for connections at port 9000)
>client 9000 “My message”

Here server should print My message and both programs should exit cleanly.

*/

#include <stdio.h>
#include <string.h>    //strlen
#include <sys/socket.h>
#include <arpa/inet.h> //inet_addr
#include <unistd.h>    //write
#include <stdlib.h>  // for strtol
 
int main(int argc , char *argv[])
{
    int socket_desc , client_sock , c , read_size;
    struct sockaddr_in server , client;
    char client_message[2000];
    int port = strtol(argv[1], NULL, 10);
    printf("Port: %u\n", port);
     
    //Create socket
    socket_desc = socket(AF_INET , SOCK_STREAM , 0);
    if (socket_desc == -1)
    {
        printf("Could not create socket");
    }
    puts("Socket created");
     
    //Prepare the sockaddr_in structure
    server.sin_family = AF_INET;
    server.sin_addr.s_addr = INADDR_ANY;
    server.sin_port = htons( port );
     
    //Bind
    if( bind(socket_desc,(struct sockaddr *)&server , sizeof(server)) < 0)
    {
        //print the error message
        perror("bind failed. Error");
        return 1;
    }
    puts("bind done");
     
    //Listen
    listen(socket_desc , 3);
     
    //Accept and incoming connection
    puts("Waiting for incoming connections...");
    c = sizeof(struct sockaddr_in);
     
    //accept connection from an incoming client
    client_sock = accept(socket_desc, (struct sockaddr *)&client, (socklen_t*)&c);
    if (client_sock < 0)
    {
        perror("accept failed");
        return 1;
    }
    puts("Connection accepted");
     
    //Receive a message from client
    while( (read_size = recv(client_sock , client_message , 2000 , 0)) > 0 )
    {
        printf("%s\n", client_message);
        //Send the message back to client
        // write(client_sock , client_message , strlen(client_message));
    }

    // Exit after transmission
    close(socket_desc);
    puts("Client disconnected");
    fflush(stdout);

    return 0;
}
