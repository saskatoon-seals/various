/*
 * shm-client - client program to demonstrate shared memory.
 */
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <stdio.h>
#include <time.h>
#include "constants.h"

#define SHMSZ     27

start_shm_client(key_t key)
{
    int shmid;
    char *shm, *s;
    char client_message_copy[DATA_SIZE];
    clock_t start_time, end_time;

    /*
     * Locate the segment.
     */
    if ((shmid = shmget(key, SHMSZ, 0666)) < 0) {
        perror("shmget");
        exit(1);
    }

    /*
     * Now we attach the segment to our data space.
     */
    if ((shm = shmat(shmid, NULL, 0)) == (char *) -1) {
        perror("shmat");
        exit(1);
    }

    //--------------------------------------------------------------------------

    start_time = clock();

    memcpy(client_message_copy, shm, DATA_SIZE);

    end_time = clock();
    float time_per_mb = (float)(end_time-start_time) / DATA_SIZE_MB / 1000;

    printf("Shared memory time meassurements:\n");
    printf("Total elapsed time %d\n", end_time-start_time);
    printf("Elapsed time %f [ms] per 1 MB.\n", time_per_mb);

    //--------------------------------------------------------------------------

    if(shmdt(shm) == -1) {
		perror("shmdt");
		exit(1);
	}
}
