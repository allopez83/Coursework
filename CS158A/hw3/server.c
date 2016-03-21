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
#include <string.h> //strlen
#include <sys/socket.h>
#include <arpa/inet.h> //inet_addr
#include <unistd.h> //write
#include <stdlib.h> // for strtol
 
int main(int argc , char *argv[])
{
    int server_socket, client_socket, size, read_size, port;
    struct sockaddr_in server , client;
    char client_message[2000];

    port = strtol(argv[1], NULL, 10);

    //Create socket
    server_socket = socket(AF_INET , SOCK_STREAM , 0);
    if (server_socket == -1)
    {
        printf("Could not create socket");
    }
    puts("Socket created");

    //Prepare the sockaddr_in structure
    server.sin_family = AF_INET;
    server.sin_addr.s_addr = INADDR_ANY;
    server.sin_port = htons(port);
    if(bind(server_socket,(struct sockaddr *)&server , sizeof(server)) < 0)
    {
        perror("bind error");
        return 1;
    }

    // Stand by
    listen(server_socket , 3);
    puts("Waiting for client");
     
    // Accept and print transmission
    size = sizeof(struct sockaddr_in);
    client_socket = accept(server_socket, (struct sockaddr *)&client, (socklen_t*)&size);
    puts("-- Message from client --");
    recv(client_socket , client_message , 2000 , 0);
    printf("%s\n", client_message);

    // Exit
    puts("-- End of message --\nClosing connection");
    close(server_socket);
    fflush(stdout);

    return 0;
}
