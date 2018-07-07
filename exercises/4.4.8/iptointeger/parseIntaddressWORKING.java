

public class IPLookUp8124329 {

	String address;

    public IPLookUp8124329(String stuff) {
    	address = stuff;
    }

    public void iptoInt() {
    	address = address.replace(".","");
    	//String string = "004.034556";

    	/*
    	String[] parts = address.split("\\.");

    	String part1 = parts[0];
    	String part2 = parts[1];
    	String part3 = parts[2];
    	String part4 = parts[3];

    	StdOut.println(part1);
    	StdOut.println(part2);
    	StdOut.println(part3);
    	StdOut.println(part4);
    	*/
    	StdOut.println(address);

    	int integer_ip = Integer.parseInt(address);
    	StdOut.println(integer_ip + "***");
    	
		int b1 = integer_ip & 0xFF;
		int b2 = (integer_ip >> 8) & 0xFF;
		int b3 = (integer_ip >> 16) & 0xFF;
		int b4 = (integer_ip >> 24) & 0xFF;

		StdOut.println(b1);
		StdOut.println(b2);
		StdOut.println(b3);
		StdOut.println(b4);
/*
    	int integer_ip =		( 16777216 * part1 )
							+ 	(    65536 * part2 )
    	             		+ 	(      256 * part3 )
    	             		+				 part4;

    	 */
    }

    public static void main(String[] args) {
    	IPLookUp8124329 address = new IPLookUp8124329("92.168.1.2");
    	address.iptoInt();
    }

}
