//  Compilation:  javac FrequencyCounter.java
//  Execution:    java FrequencyCounter L < input.txt
//  Dependencies: ST.java StdIn.java StdOut.java
//  Data files:   http://algs4.cs.princeton.edu/31elementary/tnyTale.txt
//                http://algs4.cs.princeton.edu/31elementary/tale.txt
//                http://algs4.cs.princeton.edu/31elementary/leipzig100K.txt
//                http://algs4.cs.princeton.edu/31elementary/leipzig300K.txt
//                http://algs4.cs.princeton.edu/31elementary/leipzig1M.txt
//
//  Read in a list of words from standard input and print out
//  the most frequently occurring word that has length greater than
//  a given threshold.
//
//  % java FrequencyCounter 1 < tinyTale.txt
//  it 10
//
//  % java FrequencyCounter 8 < tale.txt
//  business 122
//
//  % java FrequencyCounter 10 < leipzig1M.txt
//  government 24763
///////////////////////////////////////////////////////////////////////////

/**
 *  The FrequencyCounter class provides a client for 
 *  reading in a sequence of words and printing a word 
 *  (exceeding a given length) that occurs most frequently. 
 *  It is useful as a test client for various symbol table implementations.
 *  <p>
 *  For additional documentation, see 
 *  <a href="http://algs4.cs.princeton.edu/31elementary/">Section 3.1</a> 
 *  of "Algorithms, 4th Edition", by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */

public class FrequencyCounter {

    /**
     * Reads in a command-line integer representing a threshold
     * and sequence of words from standard input.
     * Let's say that a word is long if its length is greater than 
     * or equal to the threshold. 
     * The method prints out a word that has maximum frenquency
     * among the long ones.
     * It also prints out the number of long words and 
     * the number of distinct such words.
     * (If there are no long words, the maximum frequency word and
     * its frequency are undefined, but the method pretends that 
     * the word in question is "" and that it occurs 0 times.)
     */
    public static void main(String[] args) {
        int distinct = 0, words = 0;
        int minlen = Integer.parseInt(args[0]);
        ST<String, Integer> st = new ST<String, Integer>();

        // compute frequency counts
        while (!StdIn.isEmpty()) {
            String key = StdIn.readString();
            if (key.length() < minlen) continue;
            words++;
            if (st.contains(key)) {
                st.put(key, st.get(key) + 1);
            }
            else {
                st.put(key, 1);
                distinct++;
            }
        }

        // find a key with the highest frequency count
        String max = "";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max))
                max = word;
        }

        StdOut.println(max + " " + st.get(max));
        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);
    }
}

