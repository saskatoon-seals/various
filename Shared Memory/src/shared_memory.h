#ifndef __SHARED_MEMORY_H__
#define __SHARED_MEMORY_H__

#include <stdio.h>
#include <sys/mman.h>

//File must already exist and it must be larger than 0
#define FILENAME "data/temp_file"
#define PAGE_SIZE 4096
#define NUM_OF_PAGES 2

void prepare_file_for_mapping();
void* create_client_shared_memory(int *fd);
void* create_server_shared_memory(int *fd);
void close_shared_memory(int fd, void *mapped_file);

#endif
