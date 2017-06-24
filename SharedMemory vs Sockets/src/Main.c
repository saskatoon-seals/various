#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <stdbool.h>

bool option = false;

//Reasons for sleep: server needs to start listening to requests first
int main(void)
{
	pid_t pid;

	//SHARED MEMORY
	if(option) {
		key_t shared_memory_key = 5644;
		pid = fork();

		if(pid == 0) {
			sleep(1);
			printf("Starting shared memory client!\n");
			start_shm_client(shared_memory_key);
			printf("Finishing shared memory client!\n");
		}
		else if(pid > 0) {
			printf("Starting shared memory server!\n");
			start_shm_server(shared_memory_key, pid);
			printf("Finishing shared memory server!\n");
		}
	}
	//SOCKETS
	else {
		pid = fork();

		if(pid == 0) {
			sleep(1);
			printf("Starting socket client!\n");
			start_socket_client();
			printf("Finishing socket client!\n");
		}
		else if(pid > 0) {
			printf("Starting socket server!\n");
			start_socket_server();
			printf("Finishing socket server!\n");
		}
	}

	return EXIT_SUCCESS;
}
