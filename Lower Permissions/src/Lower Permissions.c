#include <stdio.h>
#include <stdlib.h>

#include <unistd.h>
#include <sys/types.h>
#include <pwd.h>
#include <sys/wait.h>

#define USERNAME "ales"

void function(char* caller) {
	printf("%s - UID: %d\n", caller, getuid());
}

void execute_as_lqa_admin(void (*fun_ptr)(char*), char* user) {
	int pid = fork();

	//new child process
	if (pid == 0) {
		setuid(
			getpwnam(user)->pw_uid
		);

		(*fun_ptr)(user);
	} else if (pid > 0) {
		int status;
		waitpid(pid, &status, WNOHANG|WUNTRACED);
	}
}

int main(void) {
	if (getuid() == 0) {
		function("root");
	} else {
		execute_as_lqa_admin(&function, USERNAME);
	}

	return EXIT_SUCCESS;
}
