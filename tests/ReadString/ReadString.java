/*****************************************
* 
* StdIn.readAll reads all the text
*
******************************************/

public class ReadString {

        public static void main (String[] args) {

                String s = StdIn.readAll();
                StdOut.print("array = " + s + "\n");

                int n = s.length();
                StdOut.println("length = " + n + "\n");
	}
}
