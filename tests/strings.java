/**************************************************************************
 *   author:        Lucas Sung Jun Hong  
 *   n.USP:         812 4329
 * 
 *   Dependencies:  Stack.java
 *   exercise:      4.3.13
 *   http://introcs.cs.princeton.edu/java/43stack/
 * 
 *   % java-introcs 
 *   1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) ) 
 *   ( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) ) )
 *
**************************************************************************/

public class strings {
    
    public static void main (String[] args) {

        String s = StdIn.readLine();
        String x = (s.substring(0, 1));

        if (x == " ")   StdOut.println("x == ****");

        if (x.equals(" ")) StdOut.println("GOT IT");

    }
}
