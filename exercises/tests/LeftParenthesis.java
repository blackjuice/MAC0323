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

public class LeftParenthesis {
	
	public static void main (String[] args) {
		Stack<String> alpha = new Stack<String>();

        String s = StdIn.readLine();

        int n = s.length();
        StdOut.println("n = " + n);
        for (int i = 0; i < n; i++) {
            String x = Character.toString(s.charAt(i));
            if (x == " ") continue;

            if (x != ")") {
                alpha.push(x);
                continue;
            }

            StdOut.println(s);
            
            StdOut.println("ENTROU CARAIO");
            String a = alpha.pop();
            String b = alpha.pop();
            String c = alpha.pop();

            alpha.push("( " + c + " " + b + " " + a + " )");
        
        }

        StdOut.println(alpha.pop());
	}
}
