

public class IPLookUp8124329 {

    String address;

    public IPLookUp8124329(String stuff) {
        address = stuff;
    }

    public long iptoInt() {
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
        StdOut.println(partInteger1);
        StdOut.println(partInteger2);
        StdOut.println(partInteger3);
        StdOut.println(partInteger4);

        long ipnum =    ( (long)16777216 * partInteger1 )
                    +   (    (long)65536 * partInteger2 )
                    +   (      (long)256 * partInteger3 )
                    +                (long)partInteger4;
        StdOut.println(ipnum + " after");
        StdOut.println();

        /* mesmo que "ipnum" apresente resultado negativo,
        conseguimos retirar os bytes originais pelo seguinte metodo:
        */
        //StdOut.println("tentaremos recuperar");
        long b4 = ipnum & 0xFF;
        long b3 = (ipnum >> 8) & 0xFF;
        long b2 = (ipnum >> 16) & 0xFF;
        long b1 = (ipnum >> 24) & 0xFF;

        StdOut.println(b1);
        StdOut.println(b2);
        StdOut.println(b3);
        StdOut.println(b4);

        return ipnum;
    }

    public static void main(String[] args) {
        //IPLookUp8124329 address = new IPLookUp8124329("174.36.207.186");
        //IPLookUp8124329 address = new IPLookUp8124329("192.168.0.1");
        //IPLookUp8124329 address = new IPLookUp8124329("1.0.0.0");
        //long n = address.iptoInt();
        //StdOut.println("n = " + n);



        In in = new In(args[0]);
        String[] database = in.readAllLines();
        StdRandom.shuffle(database);

        /*
        beginning of IP address range, 
        ending of IP address range, 
        two character country code, 
        three character country code, and country nam

        int begin = 0;
        int end = 1; //ate 5

        IP inicial do intervalo, 
        IP final do intervalo, 
        valor inteiro do IP inicial, valor inteiro do IP final, 
        país (duas letras), país.

        */

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
            st.put(pais, intFim);
            //st.put(intFim, pais);
        }

        StdOut.println("min key: " + st.min());
        StdOut.println("max key: " + st.max());
        StdOut.println("size:    " + st.size());
        StdOut.println();
        
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
/*
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (st.contains(s)) StdOut.println(st.get(s));
            else                StdOut.println("Not found");
        }
*/

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            IPLookUp8124329 address = new IPLookUp8124329(s);
            long n = address.iptoInt();
        }
    }

}







    public void intToIP() {

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

        StdOut.println(b4 + "." + b3 + "." + b2 + "." + b1);
    }