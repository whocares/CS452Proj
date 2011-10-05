#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <errno.h>
#include <string.h>
#include <sys/types.h>
#include <sys/time.h>
#include <sys/resource.h>
#include <sys/wait.h>
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

      int arrayOfIntForMaster[99];  

      int z = 0;
      int count = 0;
      
//       read(copper[0][READ], &arrayOfIntForMaster[count], sizeof(int));
     do {
// 	    count++;
	    read(copper[0][READ], &arrayOfIntForMaster[count], sizeof(int));
	    printf("Num: %d\n", arrayOfIntForMaster[count]);
 	    count++;
	    printf("Count = %d\n", count);
      } while(arrayOfIntForMaster[count-1] != 0 );
      
      printf("Done with while loop\n");

       for (z = 0; z < 14; z++) {
	   printf("the number in the array is: %d\n", arrayOfIntForMaster[z]);
       }


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
	  //printf("%i\n",  arrayOfInt[i]);
	  i++;
	}
	size=i;
	
	int x = 0;	
	qsort (arrayOfInt, size, sizeof(int), compare);
	
	/*Not sure if right*/
	while(x < i){
		printf("%i\n", arrayOfInt[x]);
		write (fd[0][WRIT], &arrayOfInt[x], sizeof(int));
		x++;
	}
	
	fclose(fr);
}

int compare (const void * a, const void * b)
{
  return ( *(int*)a - *(int*)b );
}