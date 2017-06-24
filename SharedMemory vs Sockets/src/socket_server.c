/*
    C socket server example
*/

#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>
#include <sys/types.h>
#include <time.h>
#include "constants.h"

void start_socket_server()
{
    int socket_desc , client_sock , c , read_size;
    struct sockaddr_in server , client;
    char client_message[MESSAGE_SIZE];
    char client_message_copy[MESSAGE_SIZE];
    clock_t start_time, end_time;

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
    server.sin_port = htons( SERVER_PORT );

    //Bind
    if( bind(socket_desc,(struct sockaddr *)&server , sizeof(server)) < 0)
    {
        //print the error message
        perror("bind failed. Error");
        return;
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
        return;
    }
    puts("Connection accepted");

    //--------------------------------------------------------------------------

    start_time = clock();

    //Receive a message from client
    while( (read_size = recv(client_sock , client_message , MESSAGE_SIZE , 0)) > 0 );
//    memcpy(client_message_copy, client_message, MESSAGE_SIZE);
//    for(int i=0; i<MESSAGE_SIZE; i++)
//    	client_message[i];
//    {
//        //Send the message back to client
//        write(client_sock , client_message , strlen(client_message));
//    }

    end_time = clock();
	float time_per_mb = (float)(end_time-start_time) / MESSAGE_SIZE_MB / 1000;

	printf("Shared memory time meassurements:\n");
	printf("Total elapsed time %d\n", end_time-start_time);
	printf("Elapsed time %f [ms] per 1 MB.\n", time_per_mb);

    //--------------------------------------------------------------------------


    if(read_size == 0)
    {
        puts("Client disconnected");
        fflush(stdout);
    }
    else if(read_size == -1)
    {
        perror("recv failed");
    }
}
