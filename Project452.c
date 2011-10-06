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

typedef int (gvpipe_t) [2];

const int READ = 0;
const int WRITE = 1;

//this is the method that the child runs
void doWork(int id, gvpipe_t fd[], int N);

//compares two integers. used for qsort
int intCompare (const void * a, const void * b);


int main (void) {
    gvpipe_t *brass; //the pipes
    pid_t pid; //the pids
    int k; //a counter used ALOT throughout the code
    int numFiles; //number of files to be sorted
    char text[254]; //just used to receive input from the user
	int i; //another counter used through out the main method

	//receive how many files to be sorted
    fputs("How many files would you like to sort?\n", stdout);
	fflush(stdout);
	if (fgets(text, sizeof text, stdin) != NULL) {
	      char *newLine = strchr(text, '\n');
	      if (newLine != NULL)
		  *newLine = '\0';
    }

	//convert the number of files to an integer
    numFiles = atoi (text);
  
	//allocate enough memory so that there are enough pipes for the number of files
    brass = (gvpipe_t *) calloc (numFiles, sizeof(gvpipe_t));

    //the pipes are now created for each child before the child is even created
    for (k = 0; k < numFiles; k++)
        pipe (brass[k]);

    //creating enough children to sort all of the files, 1 child per file
    for (k = 0; k < numFiles; k++) {
        pid = fork ();
		
		//I'm not 100% sure anymore on what this does but if I remember right 
		//removing it makes things work to fast which causes it to crash
		int stat;
        wait (&stat);
        
		//each child will then doWork and the exit 
		if (pid == 0) {
			doWork(k, brass, numFiles);
			exit (0);
		}
    }
	
    int z = 0;
    int count = 0;
    int val;
	  
    /**
	 * We assume the length of the files are always 7 so there are 
	 * 7 items inside a pipe given to the parent. The reason we 
	 * assume this is because I had alot of difficulty with a
	 * while loop that simply checked to make sure that read 
	 * was still reading in numbers and not finished as it would 
	 * wait for more info in the pipe even though the pipe was 
	 * closed. Tryed to manually insert a large int to check for
	 * at the end of a pipe but it resulted in the same out come.
	 */
    int size = 7; 
	  
	//if you only inputed one file do this!
	if (numFiles == 1) {
		do {
			read(brass[z][READ], &val, sizeof(int));
			printf("Num: %d\n", val);
			count++;
		} while(count < size * (numFiles+1));
    } else {
	
		int val[numFiles]; //values to be stored (one at a time)
		int pipes[numFiles]; //the pipes, which will then decide which to read
		int timesTaken[numFiles]; //the number of times you've read from a pipe
		int lowestNum = -1; //the result of the comparisons 
		  
		//Start al of them at 0 except for pipes as you want to read from 
		//all the pipes the first time
		for (z = 0; z < numFiles; z++) {
			val[z] = 0;
			pipes[z] = 1;
			timesTaken[z] = 0;
		}
		 
		int pipeNumber = 0; //this is the pipe to be changed to 1 to get the new one
		int t = 0; //this is just a counter used later
		count = 0; //this is also a counter used later but reset to 0
	  
		//so while you haven't read through all of the stuff in the pipes
		while (count < size * numFiles) {
		  
			//increment count by 1, count isn't used except to count how many times the
			//while loop runs
			count++;	    
			
			for (t = 0; t < numFiles; t++) {
				//each time a pipe is set to 1 in pipes, it will read from that pipe again
				//provided that the pipe that you're reading in from is still less then
				//the size of the input (which is predetermined)
				if (pipes[t] == 1 && timesTaken[t] < size) {
					read(brass[t][READ], &val[t], sizeof(int));
					pipes[t] = 0;
					timesTaken[t]++;
				}
			}//end of for loop
		  
			//This is the difficult part of the actual reading in.
			//So all the info is stored in val[z] so you loop through
			//numFiles - 1 so you can compare the current with the one
			//coming up and set that as the lowestNumber, so you are
			//constantly setting lowestNumber as you compare through 
			//this.
			for (z = 0; z < numFiles-1; z++) {
				
				//so you set the lowest number to be the first value
				//in the first pipe to just start off.
				if (z == 0) {
					lowestNum = val[z]; //sets val[z] to the lowest number
					pipeNumber = z; //sets the pipeNumber to z
				}
				
				//next we see if the lowest number is greater then the 
				//next value 
				if (lowestNum > val[z+1]) {
					lowestNum = val[z+1]; //sets the lowest number to val[z+1]
					pipeNumber = z+1; //sets the pipe number to z+1
				} else if (lowestNum == val[z+1]) { //if they are the same
					//see which of them has been taken from more 
					if (timesTaken[pipeNumber] < timesTaken[z+1]) {
						lowestNum = val[z];
						pipeNumber = z;
					} else {
						lowestNum = val[z+1];
						pipeNumber = z+1;
					}
				}
				
				//So if the current winner has been taken from to many times 
				//this method will search to see which file hasn't been taken
				//from to many times and set that to the lowest value
				//is NOT the BEST way or for that matter always correct way!
				if (timesTaken[pipeNumber] > size) {
					int test; //test variable
					for (test = 0; test < numFiles; test++) {
						if (timesTaken[test] < size) {
							lowestNum = val[z + 1];
							pipeNumber = z + 1;
						}
					}
				}
			}//end of for loop
			
			printf("%d\n", lowestNum); //This is the lowest number
			pipes[pipeNumber] = 1; //this sets the pipe to be read again
		}//end while
    }//end long else statement
   
	//brass's memory is now free and you then exit the program
    free (brass);
    return 0;	
}


//This is the the method that child method runs before terminating
//itself.
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
	int size = 99;
	char line[99];
	FILE *file;
	file = fopen(fileName, "rt");
	int temp;
	int intArray[99];
	 i=0;
	
	while(fgets(line,99, file) !=NULL){
	  sscanf(line, "%d", &temp);
	  intArray[i] = temp;
	  i++;
	}
	size=i;
	
	int count = 0;	
	qsort (intArray, size, sizeof(int), intCompare);
	
	//right all of your content to the pipe. 
	while(count < i){
	    write (fd[id][WRITE], &intArray[count], sizeof(int));
	    count++;
	}
	
	//here you're closing the file and closing the pipes
	fclose(file);
	close(fd[id][WRITE]);
 	close(fd[id][READ]);
}

int intCompare (const void * a, const void * b)
{
  return ( *(int*)a - *(int*)b );
}
