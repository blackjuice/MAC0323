
public class IPLookUp implements Comparable<IPLookUp>{

    private long inicioInt;
    private long fimInt;
    private static String address;
    
    public IPLookUp(long inicioInt, long fimInt) {
        this.inicioInt = inicioInt;
        this.fimInt = fimInt;
    }

    //public IPLookUp(String stuff) {
    //    address = stuff;
    //}

    public int compareTo(IPLookUp that) {
                           
        if (this.inicioInt < that.inicioInt) return -1;
        if (this.fimInt > that.fimInt) return 1;
        else return 0;
    }

    public static long ipToInt(String address) {

        StdOut.println(address);
        String[] parts = address.split("\\.");

        String part1 = parts[0];
        String part2 = parts[1];
        String part3 = parts[2];
        String part4 = parts[3];

        int partInteger1 = Integer.parseInt(part1);
        int partInteger2 = Integer.parseInt(part2);
        int partInteger3 = Integer.parseInt(part3);
        int partInteger4 = Integer.parseInt(part4);

        long ipnum =    ( (long)16777216 * partInteger1 )
                    +   (    (long)65536 * partInteger2 )
                    +   (      (long)256 * partInteger3 )
                    +                (long)partInteger4;

        long b4 = ipnum & 0xFF;
        long b3 = (ipnum >> 8) & 0xFF;
        long b2 = (ipnum >> 16) & 0xFF;
        long b1 = (ipnum >> 24) & 0xFF;

        return ipnum;

    }

    public static void main(String[] args) {

        ST<IPLookUp, String> st = new ST<IPLookUp, String>();

        In in = new In(args[0]);
        String[] database = in.readAllLines();

        for (int i = 0; i < database.length; i++) {
            String[] tokens = database[i].split(",");
            String inicio = tokens[0];
            String fim = tokens[1];
            String stringInicial = tokens[2];
            String stringFim = tokens[3];
            String codePais = tokens[4];
            String pais = tokens[5];
            
            stringInicial = stringInicial.replace("\"","");
            stringFim = stringFim.replace("\"","");
            long longInicial = Long.parseLong(stringInicial);
            long longFim = Long.parseLong(stringFim);
            IPLookUp address = new IPLookUp(longInicial, longFim);
            st.put(address, pais);
        }

/*
        long x = 759054080;
        IPLookUp n = new IPLookUp (x, x);
        if (st.contains(n)) StdOut.println(st.get(n));
        else                StdOut.println("Not found");

*/
        while (!StdIn.isEmpty()) {
            String address = StdIn.readString();
            //long number = address.ipToInt();
            long number = ipToInt(address);
            IPLookUp searching = new IPLookUp(number, number);

            if (st.contains(searching)) StdOut.println(st.get(searching));
            else                StdOut.println("Not found");
        }




        /*
        for (IPLookUp s : st.keys()) {
            StdOut.println(s.inicioInt + " " + s.fimInt + " " + st.get(s));
        }

        */
    }
}
