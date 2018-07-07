

public class IPLookUp8124329 {

    String address;

    public IPLookUp8124329(String stuff) {
        address = stuff;
    }

    public long ipToInt() {

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

        /* mesmo que "ipnum" apresente resultado negativo,
        conseguimos retirar os bytes originais pelo seguinte metodo:
        */
        //StdOut.println("tentaremos recuperar");
        long b4 = ipnum & 0xFF;
        long b3 = (ipnum >> 8) & 0xFF;
        long b2 = (ipnum >> 16) & 0xFF;
        long b1 = (ipnum >> 24) & 0xFF;

        return ipnum;
    }


    public static void main(String[] args) {

        In in = new In(args[0]);
        String[] database = in.readAllLines();
        StdRandom.shuffle(database);

        ST<String, String> st = new ST<String, String>();
        //ST<String, String, String, String, String, String> st 
        //= new ST<String, String, String, String, String, String>();

        for (int i = 0; i < database.length; i++) {
            String[] tokens = database[i].split(",");
            String inicio = tokens[0];
            String fim = tokens[1];
            String intInicial = tokens[2];
            String intFim = tokens[3];
            String codePais = tokens[4];
            String pais = tokens[5];
            //st.put(inicio, fim, intInicial, intFim, codePais, pais);
            //st.put(inicio, fim);
            //st.put(pais, intFim);
            st.put(intFim, pais);
        }
        
        for (String s : st.keys())
            //StdOut.println(s + " " + st.get(s));
            StdOut.println(s + st.get(s));

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            IPLookUp8124329 address = new IPLookUp8124329(s);
            long n = address.ipToInt();
            StdOut.println(n);


            StdOut.println(st.ceil(n));
        }
    }

}
