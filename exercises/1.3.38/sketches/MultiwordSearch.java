public class MultiwordSearch {
    public static void main(String[] args) {

        String[]    words       = StdIn.readAllStrings();
        int         n_words     = words.length;
        int         n_args      = args.length;

        ///////////////////////////////////////
        StdOut.println("***********BEGIN: n_words = " + n_words);
        ///////////////////////////////////////

        // construct queues[j] = sequence of positions of jth query word
        Queue<Integer>[] queues = (Queue<Integer>[]) new Queue[args.length];
        for (int j = 0; j < n_args; j++)
            queues[j] = new Queue<Integer>();

        boolean full = true;
        int bestlo  = -1, besthi = n_words;

        for (int i = 0; i < n_words; i++) {
            for (int j = 0; j < n_args; j++) {

                int high, low = n_words - 1;

                if (words[i].equals(args[j])) {
                    if (queues[j].isEmpty()) 
                        queues[j].enqueue(i);
                    else {
                        queues[j].dequeue();
                        queues[j].enqueue(i);
                    }

                    /* checking if queries are full */
                    for (int t = 0; t < n_args; t++) {
                        if (!queues[t].isEmpty()) {
                            full = true;

                            StdOut.println("queues["+t+"].peek() -> ("+queues[t].peek()+") < low("+low+")");

                            if (queues[t].peek() < low) {
                                low = queues[t].peek(); //lowest element in queries acquired
                                StdOut.println("***low = " + low);
                            }
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


    }
}

/*

                    ///////////////////////////////////////////////
                    for (int d = 0; d < n_args; d++) {
                        StdOut.println("1 - q[" + d + "] : " + queues[d]);
                    }
                    StdOut.println();
                    ///////////////////////////////////////////////



        ///////////////////////////////////////////////
        for (int j = 0; j < n_args; j++) {
            StdOut.println("1 - q[" + j + "] : " + queues[j]);
        }
        StdOut.println();
        ///////////////////////////////////////////////


public class MultiwordSearch {
    public static void main(String[] args) {

        String[]    words       = StdIn.readAllStrings();
        int         n_words     = words.length;
        int         n_args      = args.length;

        ///////////////////////////////////////
        StdOut.println("***********BEGIN: n_words = " + n_words);
        ///////////////////////////////////////

        // construct queues[j] = sequence of positions of jth query word
        Queue<Integer>[] queues = (Queue<Integer>[]) new Queue[args.length];
        for (int j = 0; j < n_args; j++)
            queues[j] = new Queue<Integer>();

        for (int i = 0; i < n_words; i++) {
            for (int j = 0; j < n_args; j++) {

                if (i == 14) StdOut.println("14*******");

                if (words[i].equals(args[j])) {
                    if (queues[j].isEmpty()) queues[j].enqueue(i);
                    else {
                        if (i == 14) {
                            StdOut.println("else **");
                        }
                        queues[j].dequeue();
                        queues[j].enqueue(i);
                    }

                    ///////////////////////////////////////////////

                    for (int d = 0; d < n_args; d++) {
                        StdOut.println("1 - q[" + d + "] : " + queues[d]);
                    }
                    StdOut.println();
                    ///////////////////////////////////////////////
                }
            }
        }



    }
}

*/