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

void sigHandler(int sigNum);
void getWork(void* arg);

char text[256]; //initial input from the user
pthread getFiles;

void* files;

int main() {
	signal(SIGINT, sigHandler);

	if ((status = pthread_create(&getFiles, NULL, getWork, NULL) != 0) {
	    fprintf(stderr, "create error %d: %s\n", status, strerror(status));
	    exit(1);
	}
	
	if ((status =pthread_join(getWork, &files)) != 0) {
			fprintf(stderr, "join error %d: %s\n", status, strerror(status));
			exit(1);
	}
	//the file names are now contained in files
	
	
	
	printf("Spawning Children:");
	
}

void* getWork(void* arg) {
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
	    fputs("Please enter the file name: ", stdout);
	    fflush(stdout);
	    if (fgets(text, sizeof text, stdin) != NULL) {
		char *newLine = strchr(text, '\n');
		if (newLine != NULL) 
		    *newLine = '\0';
	    }
	    //set text = to something
	    //store file names
      }
  
      return arg;
}


void sigHandler(int sigNum) {
	printf("Have a nice day!\n"); 
	exit(0);
}
