#include <sys/types.h>
#include <sys/stat.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <string.h>
#include <sys/mman.h>
#define FIFO_NAME_write "RESP_PIPE_17245"
#define FIFO_NAME_read "REQ_PIPE_17245"
#define c1 "PING"
#define c2 "PONG"
#define c3 "EXIT"
#define c4 "CONNECT"
#define c5 "CREATE_SHM"
#define c6 "ERROR"
#define c7 "SUCCESS"
#define c8 "WRITE_TO_SHM"
int shmFd = -1;
char *sharedChar = NULL;
int write_value = 0;
int write_offset = 0;
int n;
int nr=17245;
int cn=7;
int ccreate=10;
int c8l=12;
int cerror=5;
int csuccess=7;
int numar=3193861;
unsigned int s,d;
void scrierePING(int fdw){
    write(fdw, &n, 1);
    write(fdw, &c1, 4);
    write(fdw, &n, 1);
    write(fdw, &c2, 4);
    write(fdw, &nr, sizeof(nr));
}
void scrieCREATE_SHMerror(int fdw){
	write(fdw, &ccreate, 1);
	write(fdw, &c5, ccreate);
	write(fdw, &cerror, 1);
	write(fdw, &c6, cerror);       
}
void scrieCREATE_SHMsuccess(int fdw){
	write(fdw, &ccreate, 1);
	write(fdw, &c5, ccreate);
	write(fdw, &csuccess, 1);
	write(fdw, &c7, csuccess);       
}
void scrieWRITE_TO_SHM_SUCCESS(int fdw){
	write(fdw, &c8l, 1);
	write(fdw, &c8, c8l);
	write(fdw, &csuccess, 1);
	write(fdw, &c7, csuccess);  
}
void scrieWRITE_TO_SHM_ERROR(int fdw){
	write(fdw, &c8l, 1);
	write(fdw, &c8, c8l);
	write(fdw, &cerror, 1);
	write(fdw, &c6, cerror); 
}
int main()
{
    int fdr=-1;
    int fdw=-1;
    if(mkfifo(FIFO_NAME_write, 0600) != 0) {
        printf("Error creating FIFO1\n");        
    }

    fdr = open(FIFO_NAME_read, O_RDONLY);
    if(fdr == -1) {
        perror("Could not open FIFO3");
        
    }

    fdw = open(FIFO_NAME_write, O_WRONLY);    
    if(fdw == -1) {
        printf("Could not open FIFO2\n");
        
    }
        
    
    if((write(fdw, &cn, 1) != -1) && (write(fdw, c4, cn) != -1)) {
        printf("SUCCESS\n");
    }

    for(;;){
    read(fdr, &n, 1);
        char* verif = (char*)malloc(n * sizeof(char));
        read(fdr, verif, n);
        
        if(strcmp(verif, "PING")==0) {
            scrierePING(fdw);
        }
        if(strcmp(verif, "EXIT") == 0) {
            close(fdw);
            close(fdr);
            free(verif);
            verif = NULL;
            unlink(FIFO_NAME_write);
            unlink(FIFO_NAME_write);
            return 0;
        }
        
        if(strcmp(verif,"CREATE_SHM")==0){ 
            read(fdr,&s,1); 
            read(fdr,&d,4);
            
            shmFd = shm_open("/LG9NqK", O_CREAT | O_RDWR , 0664);
            if(shmFd == -1) {
                scrieCREATE_SHMerror(fdw);
                break;
            }               
              
			ftruncate(shmFd, d);
			sharedChar = (char *)mmap(0, d, PROT_READ | PROT_WRITE, MAP_SHARED, shmFd, 0); 
			if(sharedChar == (void*)-1){
				scrieCREATE_SHMerror(fdw);
				break;
			}
			scrieCREATE_SHMsuccess(fdw);
        }   

		if (strcmp(verif, "WRITE_TO_SHM")==0){
			read(fdr, &write_offset, 4);
			read(fdr, &write_value, 4);
			if (write_offset < d){
				int* location = (int*)(sharedChar + write_offset);
				*location = write_value;
				scrieWRITE_TO_SHM_SUCCESS(fdw);
			}
			else{
				scrieWRITE_TO_SHM_ERROR(fdw);
			}
		}
		
		if (strcmp(verif, "MAP_FILE")==0)
		{
			
		}
		
    }
    return 0;
 
}
