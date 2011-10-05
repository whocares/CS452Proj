/**
 * This program shows how N sibling processes can share pipes. In general
 * pipes can be shared among
 * <ul>
 *    <li>a parent and all its children OR</li>
 *    <li>all children of the same parent ("siblings")</li>
 * </ul>
 * The following program creates N children and N pipes. The children use
 * the pipes in a circular fashion. 
 * Child-k read from the k-th pipe, and writes to the (k+1)-th pipe.
 * <p>
 * The parent sends an integer to the middle child, the integer then
 * circulates among the children, decremented by 1 by each child, until it 
 * reaches zero. When each child detects that the integer is the same as the
 * last time it was received, the circulation is about to stop.
 */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

/** 
 * The following "trick" makes this code more readable. The typedef lets us
 * use a new type in our program.
 */
typedef int (gvpipe_t) [2];      // gvpipe_t is now a new user-defined type
const int READ = 0;
const int WRIT = 1;

/* 
 * For instance to declare an array of six pipes:
 *    gvpipe_t plumbing[6];
 *
 * which is essentially equivalent to:
 *    int plumbing[6][2];
 *
 *
 * or if the array is allocated at run-time:
 * 
 *    gvpipe_t *channels;        // declare
 *
 *    channels = (gvpipe_t *) calloc (num_channels, sizeof(gvpipe_t));
 *
 *    // read from the fourth pipe 
 *    read (channels[3][READ], ......);
 *
 *    // write into the second pipe 
 *    read (channels[1][WRIT], ......);
 *
 *    free (channels);           // free it when done
 */

void run_in_child (int, gvpipe_t[], int);

int main (int argc, char *argv[]) {
    gvpipe_t *copper;
    pid_t who;
    int k, val, num_children;

    if (argc < 2) {
        fprintf (stderr, "Usage: %s num-of-children\n", argv[0]);
        exit (1);
    }
    num_children = atoi (argv[1]);
    copper = (gvpipe_t *) calloc (num_children, sizeof(gvpipe_t));

    // create ALL the pipes BEFORE fork so all the children inherit them
    for (k = 0; k < num_children; k++)
        pipe (copper[k]);

    // create N children
    for (k = 0; k < num_children; k++) {
        who = fork ();
        if (who == 0) {
            run_in_child (k, copper, num_children);
            exit (0);           /* each child will end here */
        }
    }

    /* parent will continue here */
    val = 11 + 2 * num_children;
    int target = num_children / 2;   /* middle child is our target */
    write (copper[target][WRIT], &val, sizeof(int));

    /* now wait for all children to stop */
    for (k = 0; k < num_children; k++) {
        int stat;
        wait (&stat);
    }
    free (copper);
    return 0;
}

/*--- Everything below this line runs in child ----*/

/* The child reads an integer from its predecessor, decrements it (when
 * positive) and pass it to its successor.
 */
void run_in_child (int id, gvpipe_t fd[], int N) {
    int val, last_val;
    int next;
    next = (id + 1) % N;
    printf ("Child-%d, PID=%d, next in line is Child-%d\n", id, getpid(), next);
    last_val = -1;
    while (1) {
        /* read from predecessor */
        read (fd[id][READ], &val, sizeof (int));
        printf ("Child-%d got %d, last value = %d\n", id, val, last_val);
        if (val > 0) val--;

        /* pass it on to my successor */
        write (fd[next][WRIT], &val, sizeof (int));

        if (val == last_val) break; /* no further update, I can quit now */
        last_val = val;
    }
    printf ("Child-%d terminated\n", id);
}
