#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <signal.h>
#include "shared_memory.h"

char *response_data;
int finished = 0;

void send_message(char *data, pid_t server_pid) {
	char *message = "Gomila!";

	strcpy(data, message);

	kill(server_pid, SIGUSR1);
}

void receive_response() {
	char *message = malloc(strlen(response_data));
	strcpy(message, response_data);

	printf("Final received message is: %s\n", message);
}

static void signal_handler(int sig, siginfo_t *siginfo, void *context) {
	receive_response();
	finished = 1;
}

void wait_for_response() {
	struct sigaction signal_action;

	signal_action.sa_sigaction = &signal_handler;
	signal_action.sa_flags = SA_SIGINFO;

	sigaction(SIGUSR1, &signal_action, NULL);

	while(finished == 0) {
		sleep(1);
	}
}

void start_shm_client(pid_t server_pid) {
	int fd = 0;

	char *mapped_file_request = create_client_shared_memory(&fd);
	response_data = create_server_shared_memory(&fd);

	send_message(mapped_file_request, server_pid);
	wait_for_response();

	close_shared_memory(fd, mapped_file_request);
	close_shared_memory(fd, response_data);
}
