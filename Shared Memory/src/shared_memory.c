#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <errno.h>

#include "shared_memory.h"

void prepare_file_for_mapping() {
	FILE *fd = fopen(FILENAME, "w+");

	if(fd == 0) {
		printf("File wasn't opened!\n");
	}

	ftruncate(fileno(fd), NUM_OF_PAGES * PAGE_SIZE);

	fclose(fd);
}

void* create_client_shared_memory(int *fd)
{
	if(*fd == 0)
		*fd = open(FILENAME, O_RDWR);

	return mmap(NULL,
				PAGE_SIZE,
				PROT_READ | PROT_WRITE,
				MAP_SHARED,
				*fd,
				0);
}

void* create_server_shared_memory(int *fd)
{
	if(*fd == 0)
		*fd = open(FILENAME, O_RDWR);

	return mmap(NULL,
				PAGE_SIZE,
				PROT_READ | PROT_WRITE,
				MAP_SHARED,
				*fd,
				PAGE_SIZE);
}

void close_shared_memory(int fd, void *mapped_file)
{
	close(fd);
	munmap(mapped_file, PAGE_SIZE);
}
