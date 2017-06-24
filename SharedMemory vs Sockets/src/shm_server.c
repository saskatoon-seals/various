#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <stdio.h>
#include "constants.h"

start_shm_server(key_t key, pid_t pid)
{
    char c;
    int shmid;
    char *shm, *s;
    int status;

    /*
     * Create the segment.
     */
    if ((shmid = shmget(key, DATA_SIZE, IPC_CREAT | 0666)) < 0) {
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

    /*
     * Now put some things into the memory for the
     * other process to read.
     */
    s = shm;

//    for (c = 'a'; c <= 'z'; c++)
//        *s++ = c;
    for(int i=0; i<DATA_SIZE; i++)
    	*s++ = 'a';
    *s = NULL;

    waitpid(pid, &status, 0);

    if(shmdt(shm) == -1) {
    	perror("shmdt");
    	exit(1);
    }
    shmctl(shmid, IPC_RMID, (struct shmid_ds *) 0);
}
