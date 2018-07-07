/*************************************************************************
 *  Compilation:  javac BinarySearch.java
 *  Execution:    java BinarySearch whitelist.txt < input.txt
 *  Data files:   http://algs4.cs.princeton.edu/11model/tinyW.txt
 *                http://algs4.cs.princeton.edu/11model/tinyT.txt
 *                http://algs4.cs.princeton.edu/11model/largeW.txt
 *                http://algs4.cs.princeton.edu/11model/largeT.txt
 *
 *  % java BinarySearch tinyW.txt < tinyT.txt
 *  50
 *  99
 *  13
 *
 *  % java BinarySearch largeW.txt < largeT.txt | more
 *  499569
 *  984875
 *  295754
 *  207807
 *  140925
 *  161828
 *  [3,675,966 total values]
 *  
 *  *** I guess the correct number is 367,966 *** PF 2014-02-13 
 *************************************************************************/

import java.util.Arrays;

/**
 *  The BinarySearch class provides a static method for binary
 *  searching for an integer in a sorted array of integers.
 *  <p>
 *  The rank operations takes logarithmic time in the worst case.
 *  <p>
 *  For additional documentation, see Section 1.1, page 9 of
 *  "Algorithms, 4th Edition", by Robert Sedgewick and Kevin Wayne
 *  or <a href="http://algs4.cs.princeton.edu/11model/">the booksite</a>.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */

public class BinarySearch {

    /** This class should not be instantiated.
     */
    private BinarySearch() { }

    /** Searches for the integer key in the sorted array a[].
     * Array a[] must be sorted in ascending order.
     * Rerturns index of key in array a[] if present
     * or -1 if not present.
     */
    public static int rank(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if      (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    /** Whitelist filter.
     * Reads in a sequence of integers from the whitelist file, 
     * specified as a command-line argument. Reads in integers 
     * from standard input and prints to standard output 
     * those integers that do not appear in the whitelist.
     */
    public static void main(String[] args) {

        // read in the whitelist from given file
        In in = new In(args[0]);
        int[] whitelist = in.readAllInts();

        // sort the array
        Arrays.sort(whitelist);

        // read key; print if not in whitelist
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            if (rank(key, whitelist) == -1)
                StdOut.println(key);
        }
    }
}

