/*************************************************************************
 *  Compilation:  javac ModFrequencyCounter.java
 *  Execution:    java ModFrequencyCounter L < input.txt
 *  Dependencies: ST.java StdIn.java StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/31elementary/tnyTale.txt
 *                http://algs4.cs.princeton.edu/31elementary/tale.txt
 *                http://algs4.cs.princeton.edu/31elementary/leipzig100K.txt
 *                http://algs4.cs.princeton.edu/31elementary/leipzig300K.txt
 *                http://algs4.cs.princeton.edu/31elementary/leipzig1M.txt
 *
 *  Read in a list of words from standard input and print out
 *  the most frequently occurring word that has length greater than
 *  a given threshold.
 *
 *  % java ModFrequencyCounter 1 < tinyTale.txt
 *  it 10
 *
 *  % java ModFrequencyCounter 8 < tale.txt
 *  business 122
 *
 *  % java ModFrequencyCounter 10 < leipzig1M.txt
 *  government 24763
 *
 *
 *************************************************************************/

http://www.ime.usp.br/~yoshi/2015i/mac323/html/exx/ex3.2.44.Algs4th.html


public class ModFrequencyCounter {

    /**
     * Reads in a command-line integer and sequence of words from
     * standard input and prints out a word (whose length exceeds
     * the threshold) that occurs most frequently to standard output.
     * It also prints out the number of words whose length exceeds
     * the threshold and the number of distinct such words.
     */
    public static void main(String[] args) {
        int distinct = 0, words = 0;
        int minlen = Integer.parseInt(args[0]);
        //ST<String, Integer> st = new ST<String, Integer>(); StdOut.println("----ST----");
        BST<String, Integer> st = new BST<String, Integer>(); StdOut.println("----BST----");
        StdOut.println("args[0] = " + args[0]);

                    Stopwatch start = new Stopwatch();
                    int comparison = 0;

        // compute frequency counts
        while (!StdIn.isEmpty()) {
            String key = StdIn.readString();
	    // key = key.toLowerCase();
	    // key = key.replaceAll("[^a-zA-Z]", "");
            if (key.length() < minlen) continue;
            words++;
            if (st.contains(key)) {
                st.put(key, st.get(key) + 1);


                        //comparison++;
            }
            else {
                st.put(key, 1);
                distinct++;
                        //comparison++;
            }
        }
            // int words here
            //StdOut.println("MINE HERE words    = " + words); //WORKING
                    VisualAccumulator a = new VisualAccumulator(words, 1.0);

        // find a key with the highest frequency count
        String max = "";
        st.put(max, 0);
        for (String word : st.keys()) {
                    //comparison++;
                    //StdOut.println("comparison = " + comparison++);
                    //a.addDataValue(comparison++);
            if (st.get(word) > st.get(max)) {
                max = word;
            }
        }
                    //comparison++; 
                    //a.addDataValue(comparison);
/*
                    for (int t = 0; t < words; t++)
*/
                        //a.addDataValue(t);
                        //a.addDataValue(comparison);
                        //a.addDataValue(StdRandom.uniform());
                        //StdOut.println("comparison = " + comparison);


        StdOut.println(a);                          // impressao da media
        StdOut.println(max + " " + st.get(max));
        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);
                    //StdOut.println("comparison = " + comparison);
                    StdOut.println("st height = " + st.height());

        if (args.length > 1) {
            for (String word : st.keys()) 
            StdOut.println(word);
        }

        double time = start.elapsedTime();
        StdOut.println("time = " + time);
        StdOut.println();
    }
}

/*
java-algs4 ModFrequencyCounter 4 < Data/Prejudice.txt 
that 1415
distinct = 13208
words    = 70695

java-algs4 ModFrequencyCounter 4 1 < Data/Prejudice.txt 
same result but comes with a following list
*/