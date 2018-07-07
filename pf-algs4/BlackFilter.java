/*  Compilation:  javac BlackFilter.java
//  Execution:    java BlackFilter blacklist.txt < input.txt
//  Dependencies: SET.java In.java StdIn.java StdOut.java
//  Data files:   http://algs4.cs.princeton.edu/35applications/tinyTale.txt
//                http://algs4.cs.princeton.edu/35applications/list.txt
//
//  Read in a blacklist of words from a file. Then read in a list of
//  words from standard input and print out all those words that
//  are not in the first file.
// 
//  % more tinyTale.txt 
//  it was the best of times it was the worst of times 
//  it was the age of wisdom it was the age of foolishness 
//  it was the epoch of belief it was the epoch of incredulity 
//  it was the season of light it was the season of darkness 
//  it was the spring of hope it was the winter of despair
//
//  % more list.txt 
//  was it the of 
// 
//  % java BlackFilter list.txt < tinyTale.txt 
//  best times worst times 
//  age wisdom age foolishness 
//  epoch belief epoch incredulity 
//  season light season darkness 
//  spring hope winter despair 
*/

/** This application program reads in a blacklist of words from a file.
 * Then reads in a list of words from standard input 
 * and print out all those words that are not in the first file.<br>
 * The program is a client of the SET ADT.
 * It depends also of In.java, StdIn.java, and StdOut.java.<p>
 *
 * For additional documentation see 
 * Section 3.5 (p.491) and Exercises 3.5.31 and 5.2.24
 * in Algorithms, 4th Edition, by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */ 
public class BlackFilter {  

    // There is no constructor.
    // This class is not to be instanciated.
    private BlackFilter() {}

    /** Sample run:
     * <pre>
     *    % more tinyTale.txt 
     *    it was the best of times it was the worst of times 
     *    it was the age of wisdom it was the age of foolishness 
     *    it was the epoch of belief it was the epoch of incredulity 
     *    it was the season of light it was the season of darkness 
     *    it was the spring of hope it was the winter of despair
     *   
     *    % more list.txt 
     *    was it the of 
     *   
     *    % java BlackFilter list.txt < tinyTale.txt 
     *    best times worst times 
     *    age wisdom age foolishness 
     *    epoch belief epoch incredulity 
     *    season light season darkness 
     *    spring hope winter despair 
     * </pre>
     */ 
    public static void main(String[] args) {
        SET<String> set = new SET<String>();

        // read in strings and add to set
        In in = new In(args[0]);
        while (!in.isEmpty()) {
            String word = in.readString();
            set.add(word);
        }

        // read in string from standard input, printing out all exceptions
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            if (!set.contains(word))
                StdOut.println(word);
        }
    }
}

