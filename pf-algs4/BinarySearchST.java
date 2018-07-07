/** ***********************************************************************
 *  Compilation:  javac BinarySearchST.java
 *  Execution:    java BinarySearchST
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/31elementary/tinyST.txt  
 *  
 *  Symbol table implementation with binary search in an ordered array.
 *
 *  % more tinyST.txt
 *  S E A R C H E X A M P L E
 *  
 *  % java BinarySearchST < tinyST.txt
 *  A 8
 *  C 4
 *  E 12
 *  H 5
 *  L 11
 *  M 9
 *  P 10
 *  R 3
 *  S 0
 *  X 7
 *
 *************************************************************************/

import java.util.NoSuchElementException;

/** This is an implementation of a symbol table whose keys are comparable.
 * The keys are kept in increasing order in an array.
 * The corresponding values are kept in a parallel array.
 * Following our usual convention for symbol tables, 
 * the keys are pairwise distinct.
 * <p>
 * For additional documentation, see 
 * <a href="http://algs4.cs.princeton.edu/31elementary/">Section 3.1</a> 
 * of "Algorithms, 4th Edition" (p.378 of paper edition), 
 * by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */

public class BinarySearchST<Key extends Comparable<Key>, Value> {

    private static final int INIT_CAPACITY = 2;
    private Key[] keys;
    private Value[] vals;
    private int N = 0;   // number of (key,value) pairs in the table

    /** Costructor.
     * Creates an empty symbol table with default initial capacity.
     */
    public BinarySearchST() { 
        this(INIT_CAPACITY); 
    }   

    /** Costructor.
     * Creates an empty symbol table with given initial capacity.
     */
    public BinarySearchST(int capacity) { 
        keys = (Key[]) new Comparable[capacity]; 
        vals = (Value[]) new Object[capacity]; 
    }   

    // resize the underlying arrays
    private void resize(int capacity) {
        assert capacity >= N;
        Key[]   tempk = (Key[])   new Comparable[capacity];
        Value[] tempv = (Value[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            tempk[i] = keys[i];
            tempv[i] = vals[i];
        }
        vals = tempv;
        keys = tempk;
    }


    /** Is the key in this symbol table?
     */
    public boolean contains(Key key) {
        return get(key) != null;
    }

    /** Returns the number of (key,value) pairs in this symbol table.
     */
    public int size() {
        return N;
    }

    /** Is this symbol table empty?
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /** Returns the value associated with the given key, 
     *  or null if no such key.
     *  Argument key must be nonnull.
     */
    public Value get(Key key) {
        if (isEmpty()) return null; // I guess this is redundant
        int i = rank(key); 
        if (i < N && keys[i].compareTo(key) == 0) 
            return vals[i];
        return null;
    } 

    /** Returns the number of keys in the table 
     *  that are strictly smaller than the given key.
     *  (This is the heart of binary search method.)
     *  Argument key must be nonnull.
     */
    public int rank(Key key) {
        int lo = 0, hi = N-1; 
        while (lo <= hi) { 
            int m = lo + (hi - lo) / 2; 
            int cmp = key.compareTo(keys[m]); 
            if      (cmp < 0) hi = m - 1; 
            else if (cmp > 0) lo = m + 1; 
            else return m; 
        } 
        return lo;
    } 


    /** Search for key in this symbol table. 
     * If key is in the table, update the corresponing value.
     * Otherwise, add the (key,val) pair to the table.
     */
    public void put(Key key, Value val)  {
        if (val == null) { 
            delete(key); 
            return; 
        }
        int i = rank(key);
        // key is already in table
        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }
        // insert new key-value pair
        if (N == keys.length) resize(2*keys.length);

        for (int j = N; j > i; j--)  {
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;
        assert check();
    } 


    /** Remove key (and the corresponding value) from this symbol table.
     * If key is not in the table, do nothing.
     */
    public void delete(Key key)  {
        if (isEmpty()) return;
        // compute rank
        int i = rank(key);
        // key not in table
        if (i == N || keys[i].compareTo(key) != 0) {
            return;
        }
        for (int j = i; j < N-1; j++)  {
            keys[j] = keys[j+1];
            vals[j] = vals[j+1];
        }
        N--;
        keys[N] = null;  // to avoid loitering
        vals[N] = null;

        // resize if 1/4 full
        if (N > 0 && N == keys.length/4) resize(keys.length/2);

        assert check();
    } 

    /** Delete the minimum key and its associated value
     * from this symbol table.
     * The symbol table must be nonempty.
     */
    public void deleteMin() {
        if (isEmpty()) 
            throw new NoSuchElementException("Symbol table underflow error");
        delete(min());
    }

    /** Delete the maximum key and its associated value
     * from this symbol table.
     */
    public void deleteMax() {
        if (isEmpty()) 
            throw new NoSuchElementException("Symbol table underflow error");
        delete(max());
    }


   /***************************************************************************
    *  Ordered symbol table methods
    **************************************************************************/

    /** Returns the smallest key in this table.
     * Returns null if the table is empty.
     */
    public Key min() {
        if (isEmpty()) return null;
        return keys[0]; 
    }

   
    /** Returns the greatest key in this table.
     * Returns null if the table is empty.
     */
    public Key max() {
        if (isEmpty()) return null;
        return keys[N-1];
    }

    /** Returns a key that is strictly greater than 
     * (exactly) k other keys in the table. 
     * Returns null if k < 0.
     * Returns null if k is greater that or equal to 
     * the total number of keys in the table.
     */
    public Key select(int k) {
        if (k < 0 || k >= N) return null;
        return keys[k];
    }

    /** Returns the greatest key that is 
     * smaller than or equal to the given key.
     * If there is no such key in the table
     * (i.e., if the given key is smaller than any key in the table), 
     * returns null.
     */
    public Key floor(Key key) {
        int i = rank(key);
        if (i < N && key.compareTo(keys[i]) == 0) return keys[i];
        if (i == 0) return null;
        else return keys[i-1];
    }

    /** Returns the smallest key that is 
     * greater than or equal to the given key.
     * If there is no such key in the table
     * (i.e., if the given key is greater than any key in the table), 
     * returns null.
     */
    public Key ceiling(Key key) {
        int i = rank(key);
        if (i == N) return null; 
        else return keys[i];
    }

    /** Number of keys in the closed interval [lo, hi].
     * Returns the number of keys in this symbol table  
     * that belong to the closed interval [lo, hi].
     * Arguments lo and hi must be nonnull. 
     */
    public int size(Key lo, Key hi) {
        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else              return rank(hi) - rank(lo);
    }

    /** Returns an iterable queue containing all keys 
     * in this symbol table.
     */
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    /** Returns an iterable queue containing all keys 
     * of this symbol table that belong to the closed interval [lo, hi].
     * Arguments lo and hi must be both nonnull
     * or both null. In the latter case, return empty queue.
     */
    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<Key>(); 
        if (lo == null && hi == null) return queue;
        if (lo == null) throw new NullPointerException("lo is null in keys()");
        if (hi == null) throw new NullPointerException("hi is null in keys()");
        if (lo.compareTo(hi) > 0) return queue;
        for (int i = rank(lo); i < rank(hi); i++) 
            queue.enqueue(keys[i]);
        if (contains(hi)) queue.enqueue(keys[rank(hi)]);
        return queue; 
    }


   /***************************************************************************
    *  Check internal invariants
    **************************************************************************/

    private boolean check() {
        return isSorted() && rankCheck();
    }

    // are the items in the array in ascending order?
    private boolean isSorted() {
        for (int i = 1; i < size(); i++)
            if (keys[i].compareTo(keys[i-1]) < 0) return false;
        return true;
    }

    // check that rank(select(i)) = i
    private boolean rankCheck() {
        for (int i = 0; i < size(); i++)
            if (i != rank(select(i))) return false;
        for (int i = 0; i < size(); i++)
            if (keys[i].compareTo(select(rank(keys[i]))) != 0) return false;
        return true;
    }


   /** Test client.
    * Reads a sequence of strings from the standard input.
    * Builds a symbol table whose keys are the strings read.
    * The value of each string is its position in the input stream
    * (0 for the first string, 1 for the second, and so on).
    * Then prints all the (key,value) pairs.
    */
    public static void main(String[] args) { 
        BinarySearchST<String, Integer> st;
        st = new BinarySearchST<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
    }
}

