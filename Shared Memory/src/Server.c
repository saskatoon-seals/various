#include <stdlib.h>
#include <string.h>
#include <signal.h>
#include <unistd.h>
#include "shared_memory.h"

char *request_data;
char *response_data;
extern int finished;

void receive_message() {
	strcpy(response_data, request_data);

	pid_t client_pid = getppid();

	kill(client_pid, SIGUSR1);
}

static void signal_handler(int sig, siginfo_t *siginfo, void *context) {
	receive_message();
	finished = 1;
}

void listen() {
	struct sigaction signal_action;

	signal_action.sa_sigaction = &signal_handler;
	signal_action.sa_flags = SA_SIGINFO;

	sigaction(SIGUSR1, &signal_action, NULL);

	while(finished == 0) {
		sleep(1);
	}
}

void start_shm_server() {
	int fd = 0;

	request_data = create_client_shared_memory(&fd);
	response_data = create_server_shared_memory(&fd);

	if(response_data == MAP_FAILED) {
		printf("Mapping file for server failed.\n");
	}

	listen();

	close_shared_memory(fd, request_data);
	close_shared_memory(fd, response_data);
}
