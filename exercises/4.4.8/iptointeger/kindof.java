

public class IPLookUp8124329 {

	String address;

    public IPLookUp8124329(String stuff) {
    	address = stuff;
    }

    public void iptoInt() {
        StdOut.println(address);

        address = address.replace(".","");
        long ipnum = Long.parseLong(address);
        StdOut.println(ipnum + " before");

        //String[] parts = address.split("\\.");

/*
        String part1 = parts[0];
        String part2 = parts[1];
        String part3 = parts[2];
        String part4 = parts[3];
        StdOut.println(part1);
        StdOut.println(part2);
        StdOut.println(part3);
        StdOut.println(part4);
        int partInteger1 = Integer.parseInt(part1);
        int partInteger2 = Integer.parseInt(part2);
        int partInteger3 = Integer.parseInt(part3);
        int partInteger4 = Integer.parseInt(part4);
        StdOut.println(partInteger1);
        StdOut.println(partInteger2);
        StdOut.println(partInteger3);
        StdOut.println(partInteger4);
*/
        long b1 = ipnum & 0xFF;
        long b2 = (ipnum >> 8) & 0xFF;
        long b3 = (ipnum >> 16) & 0xFF;
        long b4 = (ipnum >> 24) & 0xFF;
        StdOut.println(b1);
        StdOut.println(b2);
        StdOut.println(b3);
        StdOut.println(b4);

        ipnum =    ( 16777216 * b1 )
                    +   (    65536 * b2 )
                    +   (      256 * b3 )
                    +                b4;
        StdOut.println(ipnum + " after");

        /* mesmo que "ipnum" apresente resultado negativo,
        conseguimos retirar os bytes originais pelo seguinte metodo:
        */

        b1 = ( ipnum / 16777216 ) % 256;
        b2 = ( ipnum / 65536    ) % 256;
        b3 = ( ipnum / 256      ) % 256;
        b4 = ( ipnum            ) % 256;
         
        StdOut.println(b1);
        StdOut.println(b2);
        StdOut.println(b3);
        StdOut.println(b4);


        /*
        




         
        */



    }

    public static void main(String[] args) {
    	IPLookUp8124329 address = new IPLookUp8124329("174.36.207.186");
    	address.iptoInt();
    }

}
