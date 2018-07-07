public class IpLookUp8124329 {

    /*

    b4.b3.b2.b1

    int b1 = ip & 0xFF
    int b2 = (ip >> 8) & 0xFF
    int b3 = (ip >> 16) & 0xFF
    int b4 = (ip >> 24) & 0xFF

    */
    public void iptoInt() {

        //StdOut.println(address);
        StdOut.println("here");

    }

    public static void main(String[] args) {

        //String address = "192.168.0.1";
        String address = new String();
        address = "192.168.0.1";

        address.iptoInt();

    }

}
