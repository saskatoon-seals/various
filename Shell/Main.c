//One time use SHELL
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/wait.h>

extern char **environ;

/* argv[0] - name of this program (needs to be ignored)
 * argv[1] - name of the program to execute (e.g.: /bin/ls)
 * argv[i], i > 1 - parameters for the program to execute
 */

int main(int argc, char *argv[]) {
	int status;

	if(argc < 2) {
		printf("Not enough arguments.");
		return EXIT_FAILURE;
	}

	pid_t pid = fork();

	if(pid == 0) {
		char **parameters = malloc(sizeof(char) * (argc-1));

		for (int i=0; i<argc-1; i++) {
			parameters[i] = strndup(argv[i+1], strlen(argv[i+1]));
		}

		printf("Execute shell command:\n");
		if (-1 == execve(parameters[0], parameters, environ)) {
			printf("Shell command encountered an error!\n");
		}
		printf("Finished executing shell command!\n");
	}
	else if (pid > 0) {
		printf("Wait for the command to finish!\n");
		waitpid(pid, //wait this process (child)
				&status,
				0); //block
		printf("Command finished, exiting shell!\n");
	}

	return EXIT_SUCCESS;
}
