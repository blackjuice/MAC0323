
/**
* This is a library of methods to handle different alphabets.
* Every alphabet is a set of (16-bit) characters.<p>
* 
* Aditional documentation: 
* see Section 5.1, p.698, of "Algorithms, 4th Edition", 
* by Robert Sedgewick e Kevin Wayne.
*
* @author Robert Sedgewick
* @author Kevin Wayne
*/

public class Alphabet {

    /** This is the alphabet {0,1}.
    */
    public static final Alphabet BINARY         = new Alphabet("01");

    /** This is the alphabet {0,1,..,7}.
    */
    public static final Alphabet OCTAL          = new Alphabet("01234567");

    /** This is the alphabet {0,1,..,9}.
    */
    public static final Alphabet DECIMAL        = new Alphabet("0123456789");

    /** This is the alphabet {0,1,..,9,A,..,F}.
    */
    public static final Alphabet HEXADECIMAL    = new Alphabet("0123456789ABCDEF");

    /** This is the alphabet {A,C,T,G}.
    */
    public static final Alphabet DNA            = new Alphabet("ACTG");

    /** This is the 26-letter alphabet {a,b,..,x,y,z}.
    */
    public static final Alphabet LOWERCASE      = new Alphabet("abcdefghijklmnopqrstuvwxyz");

    /** This is the 26-letter alphabet {A,B,..,X,Y,Z}.
     */
    public static final Alphabet UPPERCASE      = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

    /** This is the 20-letter alphabet {A,C,D,E,F,G,H,I,K,L,M,N,P,Q,R,S,T,V,W,Y}.
     */
    public static final Alphabet PROTEIN        = new Alphabet("ACDEFGHIKLMNPQRSTVWY");

    /** This is the 64-letter alphabet {A,B,..,Z,a,b,..,z,0,1,..,9,+,/}.
     */
    public static final Alphabet BASE64         = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");

    /** This is the 128-character ASCII alphabet.
     */
    public static final Alphabet ASCII          = new Alphabet(128);

    /** This is the 256-character extended ASCII alphabet.
     */
    public static final Alphabet EXTENDED_ASCII = new Alphabet(256);

    /** This is the 65536-character UNICODE alphabet.
     * (65536 = 2^16.)
     */
    public static final Alphabet UNICODE16      = new Alphabet(65536);


    // private variables
    private char[] alphabet;   // the characters in the alphabet
    private int[] inverse;     // indices
    private int R;             // radix (number of characters of the alphabet)

    // The index of a character is its position in the ordered alphabet. 
    // For example, if your alphabet is {E,A,Z,Y}
    // then the indices are as follows:
    //    character  A  E  Y  Z
    //    index      0  1  2  3  
    // For each character c in the alphabet, inverse[c] is the index of c.
    //
    // This array has 65536 elements! All objects that use the constructor 
    // do waste this much space!

    /** Constructor 1.
      * Creates a new Alphabet from chars in string alpha.
      * (Each char has 16 bits, of course.)
      * Note: private array inverse[] will use 65536 positions,
      * even for a small alphabet like DNA!
      */
    public Alphabet(String alpha) {
        // check that alphabet contains no duplicate chars
        boolean[] unicode = new boolean[Character.MAX_VALUE];
        for (int i = 0; i < alpha.length(); i++) {
            char c = alpha.charAt(i);
            if (unicode[c]) throw new IllegalArgumentException("Illegal alphabet: character = '" + c + "'");
            else unicode[c] = true;
        }

        alphabet = alpha.toCharArray();
        R = alpha.length();
        // since all characters distinct, R <= 2^{16} = 65,536
        inverse = new int[Character.MAX_VALUE];
        // Character.MAX_VALUE is 2^{16} = 65536
        for (int i = 0; i < inverse.length; i++)
            inverse[i] = -1;

        // can't use char for c since R can be as big as 65,536
        for (int c = 0; c < R; c++)
            inverse[alphabet[c]] = c;
    }

    // Private constructor.
    // Creates a new Alphabet containing Unicode chars 0 to R-1.
    private Alphabet(int R) {
        alphabet = new char[R];
        inverse = new int[R];
        this.R = R;

        // can't use char since R can be as big as 65,536
        for (int i = 0; i < R; i++)
            alphabet[i] = (char) i;
        for (int i = 0; i < R; i++)
            inverse[i] = i;
    }

    /** Constructor 2 (extended ASCII).
      * Creates a new Alphabet consisting of Unicode chars 0 to 255.
      */
    public Alphabet() {
        this(256);
    }

    /** Is character c in the alphabet?.
      * (Implementation detail: Code assumes that the length of the
      * private array inverse is 65536.
      * This may result in memory waste in case of the DNA alphabet,
      * for example, contrary to what the book says on page 700.)
      */
    public boolean contains(char c) {
        return inverse[c] != -1;
    }

    /** Returns radix R. 
      * This is the number of characters in the alphabet.
      */
    public int R() {
        return R;
    }

    /** Returns number of bits necessary to represent 
      * the index of a character.
      * (This is ceiling( log_2( R)).
      * Assumes, of course, that R >= 1.)
      */
    public int lgR() {
        int lgR = 0;
        for (int t = R-1; t >= 1; t /= 2)
            lgR++;
        return lgR;
    }

    /** Converts character c to a number in 0..R-1.
     * Each number in 0..R-1 corresponds to a unique character of this alphabet.
     */
    public int toIndex(char c) {
        if (c < 0 || c >= inverse.length || inverse[c] == -1) {
            throw new IllegalArgumentException("Character " + c + " not in alphabet");
        }
        return inverse[c];
    }

    /** Receives a string s over this alphabet and 
     * converts s into a base-R int array.
     * Result is an array x[0..N-1]
     * such that x[i] is toIndex(s.charAt(i)).
     * Of course N is s.length().
     */
    public int[] toIndices(String s) {
        char[] source = s.toCharArray();
        int[] target  = new int[s.length()];
        for (int i = 0; i < source.length; i++)
            target[i] = toIndex(source[i]);
        return target;
    }

    /** Converts an index between 0 and R-1 into a char over this alphabet.
     */
    public char toChar(int index) {
        if (index < 0 || index >= R) {
            throw new IndexOutOfBoundsException("Alphabet index out of bounds");
        }
        return alphabet[index];
    }

    /** Converts array indices[] that represents a base-R integer 
     * into a String over this alphabet.
     */
    public String toChars(int[] indices) {
        StringBuilder s = new StringBuilder(indices.length);
        for (int i = 0; i < indices.length; i++)
            s.append(toChar(indices[i]));
        return s.toString();
    }


    /** Test client (ignores command-line arguments).
     * Converts the string "NowIsTheTimeForAllGoodMen"
     * (over a BASE64 alphabet)
     * to indices, then back to characters, and prints the result.
     * Does the same for the string "AACGAACGGTTTACCCCG"
     * (over the DNA alphabet).
     * Does the same for the string "01234567890123456789"
     * (over the DECIMAL alphabet).
     */
    public static void main(String[] args) {
        int[] encoded1  = Alphabet.BASE64.toIndices("NowIsTheTimeForAllGoodMen");
        String decoded1 = Alphabet.BASE64.toChars(encoded1);
        StdOut.println(decoded1);
 
        int[] encoded2  = Alphabet.DNA.toIndices("AACGAACGGTTTACCCCG");
        String decoded2 = Alphabet.DNA.toChars(encoded2);
        StdOut.println(decoded2);

        int[] encoded3 = Alphabet.DECIMAL.toIndices("01234567890123456789");
        String decoded3 = Alphabet.DECIMAL.toChars(encoded3);
        StdOut.println(decoded3);
    }
}

