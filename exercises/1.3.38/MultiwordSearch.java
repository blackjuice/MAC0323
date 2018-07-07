/*************************************************************************
 *  Compilation:  javac MultiwordSearch.java
 *  Execution:    java MultiwordSearch query1 query2 ... < input.txt
 *  Dependencies: Queue.java StdIn.java
 *
 *  Find the shortest interval (number of words) in the input file
 *  that contains the query words in the order specified on the command line.
 *
 *************************************************************************/

public class MultiwordSearch {
    public static void main(String[] args) {
        //Stopwatch
        Stopwatch       time    = new Stopwatch();
        StopwatchCPU    timeCPU = new StopwatchCPU();

        String[]        words   = StdIn.readAllStrings();
        int             n_words = words.length;
        int             n_args  = args.length;

        // construct queues[j] = sequence of positions of jth query word
        Queue<Integer>[] queues = (Queue<Integer>[]) new Queue[args.length];
        for (int j = 0; j < n_args; j++)
            queues[j] = new Queue<Integer>();

        boolean full;
        int bestlo  = -1, besthi = n_words;

        for (int i = 0; i < n_words; i++) {
            for (int j = 0; j < n_args; j++) {

                int high, low = n_words - 1;

                if (words[i].equals(args[j])) {
                    if (queues[j].isEmpty()) queues[j].enqueue(i);
                    else {
                        queues[j].dequeue();
                        queues[j].enqueue(i);
                    }

                    /* checking if queries are full */
                    for (int t = 0; t < n_args; t++) {
                        if (!queues[t].isEmpty()) {
                            full = true;
                            if (queues[t].peek() < low) low = queues[t].peek(); //lowest element in queries acquired
                        }
                        else {
                            full = false;
                            break;
                        }
                    }

                    if (full) {
                        high = queues[j].peek();
                        if (high - low < besthi - bestlo) {
                            besthi = high;
                            bestlo = low;
                        }
                    }
                }
            }
        }

        StdOut.println("besthi("+besthi+") && bestlo("+bestlo+")");
        ///////////////////////////////////////////////
        for (int d = 0; d < n_args; d++) {
            StdOut.println("- - q[" + d + "] : " + queues[d]);
        }
        StdOut.println();
        ///////////////////////////////////////////////
        StdOut.println();StdOut.println();

        if (bestlo >= 0) {
            for (int i = bestlo; i <= besthi; i++)
                StdOut.print(words[i] + " ");
            StdOut.println();
        }
        else
            StdOut.println("NOT FOUND!");

    	//end of time
    	double     endtime     = time.elapsedTime();
    	double     endtimeCPU  = timeCPU.elapsedTime();
    	StdOut.println("time = " + endtime);
    	StdOut.println("timeCPU = " + endtimeCPU);
    }
}