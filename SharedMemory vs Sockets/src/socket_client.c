/*
    C ECHO client example using sockets
*/
#include <stdio.h> //printf
#include <string.h>    //strlen
#include <sys/socket.h>    //socket
#include <arpa/inet.h> //inet_addr
#include "constants.h"

start_socket_client()
{
    int sock;
    struct sockaddr_in server;
    char message[MESSAGE_SIZE];

    //Create socket
    sock = socket(AF_INET , SOCK_STREAM , 0);
    if (sock == -1)
    {
        printf("Could not create socket");
    }
    puts("Socket created");

    server.sin_addr.s_addr = inet_addr(SERVER_IP);
    server.sin_family = AF_INET;
    server.sin_port = htons( SERVER_PORT );

    //Connect to remote server
    if (connect(sock , (struct sockaddr *)&server , sizeof(server)) < 0)
    {
        perror("connect failed. Error");
        return 1;
    }

    puts("Connected\n");

    for(int i=0; i<MESSAGE_SIZE; i++)
    	message[i] = 'a';

	//Send some data
	if( send(sock , message , MESSAGE_SIZE, 0) < 0)
	{
		puts("Send failed");
		return 1;
	}

    printf("Done!\n");

    close(sock);
    return 0;
}
