#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <errno.h>
#include <string.h>
#include <sys/types.h>
#include <sys/time.h>
#include <sys/resource.h>
#include <time.h>

typedef int (gvpipe_t) [2];      // gvpipe_t is now a new user-defined type
const int READ = 0;
const int WRIT = 1;
int i;

void doWork(int id, gvpipe_t fd[], int N);
int compare (const void * a, const void * b);


int main (void) {
    gvpipe_t *copper;
    pid_t who;
    int k;
    int numFiles;
    char text[254];     
    

    fputs("How many files would you like to sort?\n", stdout);
	fflush(stdout);
	if (fgets(text, sizeof text, stdin) != NULL) {
	      char *newLine = strchr(text, '\n');
	      if (newLine != NULL)
		  *newLine = '\0';
    }

    numFiles = atoi (text);
  
    copper = (gvpipe_t *) calloc (numFiles, sizeof(gvpipe_t));

    // create ALL the pipes BEFORE fork so all the children inherit them
    for (k = 0; k < numFiles; k++)
        pipe (copper[k]);

    // create N children
    for (k = 0; k < numFiles; k++) {
        who = fork ();
	int stat;
        wait (&stat);
        if (who == 0) {
		doWork(k, copper, numFiles);
		exit (0);           /* each child will end here */
        }
    }

//       int arrayOfIntForMaster[numFiles][99];  

      int z = 0;
      int count = 0;
      int val;
      int size = 7; 
	  //we assume the length of the files are always 7 so there are 
	  //7 items inside a pipe given to the parent	
      if (numFiles == 1) {
  	do {
	  read(copper[z][READ], &val, sizeof(int));
	  //printf("Status: %d\n", status);
	  printf("Num: %d\n", val);
	  count++;
	} while(count < size * (numFiles+1));
      } else {
	
	  int values[numFiles];
	  int pipes[numFiles];
	  int counts[numFiles];
	  int winner = -1; 
	  
	  //Everything starts out as 0!
	  for (z = 0; z < numFiles; z++) {
	      values[z] = 0;
	      pipes[z] = 1;
	      counts[z] = 0;
	  }
	/********************************************************************************************************/
	int pipeNumber = 0;
	  int t = 0;
	  count = 0;
	  while (count < size * numFiles) {
	    
	    count++;	    
        /********************************************************************************************************/
	      /*This should read in each time*/
	      for (t = 0; t < numFiles; t++) {
		if (pipes[t] == 1 && counts[t] < size) {
		  read(copper[t][READ], &values[t], sizeof(int));
		  //printf("Read from pipe %d: value %d\n", z, values[t]);
		  pipes[t] = 0;
		  counts[t]++;
		}
	      }//end of for loop
      
	      pipeNumber = 0;
	      for (z = 0; z < numFiles-1; z++) {
		printf("Determining winner...");
		if (z == 0) {
		  winner = values[z];
		  pipeNumber = z;
		 }
		  if (winner > values[z+1]) {
		    winner = values[z+1];
		    pipeNumber = z+1;
		  } else if (winner == values[z+1]) {
		    if (counts[z] < counts[z+1]) {
		      winner = values[z];
		      pipeNumber = z;
		    } else {
		      winner = values[z+1];
		      pipeNumber = z+1;
		    }
		  }
		  
		  if (counts[z] > size) {
		      winner = z + 1;
		      pipeNumber = z + 1;
		  }
		  if (counts[z+1] > size) {
		      winner = z;
		      pipeNumber = z;
		  }
		}//end of forloop
		
		printf("Winner: %d\n", winner); //This is the lowest number
		printf("Pipe Number %d\n", pipeNumber);
		printf("Counts of pipe number: %d\n", counts[pipeNumber]);
		printf("Count is at : %d\n", count);
		pipes[pipeNumber] = 1; //this sets the pipe to be read again
	    }
      }
       /********************************************************************************************************/
	   


     /* now wait for all children to stop */
    for (k = 0; k < numFiles; k++) {
         int stat;
         wait (&stat);
     }
   
    free (copper);
    return 0;	
}


/**********************************************************
 * 		Child process
 **********************************************************/
void doWork(int id, gvpipe_t fd[], int N) {

	char *fileName;
	char text[254];
	
	fputs("Please enter a file name to sort\n", stdout);
	fflush(stdout);
	if (fgets(text, sizeof text, stdin) != NULL) {
	      char *newLine = strchr(text, '\n');
	      if (newLine != NULL)
		  *newLine = '\0';
	}

	fileName = text;
	int size = 80;
	char line[80];
	FILE *fr;
	fr = fopen(fileName, "rt");
	int temp;
	int arrayOfInt[80];
	 i=0;
	
	while(fgets(line,80, fr) !=NULL){
	  
	  sscanf(line, "%d", &temp);
	  arrayOfInt[i] = temp;
	  i++;
	}
	size=i;
	
	int x = 0;	
	qsort (arrayOfInt, size, sizeof(int), compare);
	
	while(x < i){
	    printf("%d\n", arrayOfInt[x]);	
	      write (fd[id][WRIT], &arrayOfInt[x], sizeof(int));
	    x++;
	}
	
	fclose(fr);
	close(fd[id][WRIT]);
 	close(fd[id][READ]);
}

int compare (const void * a, const void * b)
{
  return ( *(int*)a - *(int*)b );
}
