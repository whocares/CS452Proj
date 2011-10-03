#include <pthread.h>
#include <stdio.h>
#include <errno.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/time.h>
#include <sys/resource.h>
#include <sys/wait.h>

//for lab have two freq and counter and then t1 and t2 to subratct that by. Then use a method 
//to do a "quantum" and then subract to get the answer :D 	

#define WRITE 1
#define READ 0

void sigHandler(int sigNum);
void getWork(void* arg);

char text[256]; //initial input from the user
pthread getFiles;

pid_t pid = 0;

void* files;

int main() {
	signal(SIGINT, sigHandler);
	int fd[2];
	int fd2[2];
	
	i (pipe (fd) < 0) {
	    perror("Plumbing problem");
	    exit(1);
	}
	
	dup2(fd[READ], STDIN_FILENO); 
	//he master will now read from he standard in
	
	//MIGHT NOT NEED!!!!!!
	/*if ((status = pthread_create(&getFiles, NULL, getWork, NULL) != 0) {
	    fprintf(stderr, "create error %d: %s\n", status, strerror(status));
	    exit(1);
	}
	
	if ((status =pthread_join(getWork, &files)) != 0) {
			fprintf(stderr, "join error %d: %s\n", status, strerror(status));
			exit(1);
	}
	//the file names are now contained in files*/
	
	
	
	printf("Spawning Children:");
	
	return 0;
}


/***************************************************************************************************
 * 
 * Creates the parent
 * 
 ***************************************************************************************************/

void* getWork(void* arg) {
      if (pipe(fd2) < 0) {
	perror("Plubming problem"); 
	exit(1);
      }
      dup(fd[WRITE], stdout);
      //send your out put to the pipe made earlier
      dup(fd[READ], stdin);
      //get your input from the new pipe
      
      close(fd[WRITE]);
      close(fd[READ]);
      //close the pipe from the master 

      fputs("How many files would you like to sort?\n", stdout);
      fflush(stdout);
      if (fgets(text, sizeof text, stdin) != NULL) {
	    char *newLine = strchr(text, '\n');
	    if (newLine != NULL)
		*newLine = '\0';
      }
      
      int numFiles = atoi (text);
      int count = 0;
      
      while (count < numFiles) {
	if(pid == 0) {
	  if ((pid = forK()) < 0) {
	      perror("Fork fail, use chopsticks");
	      exit(1);
	  }
	} else {
	    //ask for the file names
	    //open the files
	    
	    fputs("Which file would you like to have sorted?", stdout);
	    fflush(stdout); 
	    if (fgets(text, size of text, stdin) != NULL) {
	      char *newLine = strchr(text, '\n'); 
	      if (newLine != NULL) 
		  *newLine = '\0';
	    }
	}
	
	
	count++;
      }
      
      return arg;
}


void sigHandler(int sigNum) {
	printf("Have a nice day!\n"); 
	exit(0);
}
