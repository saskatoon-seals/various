/*
 ============================================================================
 Name        : Shared.c
 Author      :
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#include "shared_memory.h"

void start_shm_client();
void start_shm_server();

//Reasons for sleep: server needs to start listening to requests first
int main(void)
{
	prepare_file_for_mapping();

	pid_t pid = fork();

	if(pid > 0) {
		sleep(1);
		printf("Starting client!\n");
		start_shm_client(pid);
		printf("Finishing client!\n");
	}
	else if(pid == 0) {
		printf("Starting server!\n");
		start_shm_server();
		printf("Finishing server!\n");
	}

	return EXIT_SUCCESS;
}
