
public class IPLookUp implements Comparable<IPLookUp>{
    //public class miniEP6 implements Comparable<miniEP6> { ...
    //String address;

    private long inicioInt;
    private long fimInt;
    
    public IPLookUp(long inicioInt, long fimInt) {
        this.inicioInt = inicioInt;
        this.fimInt = fimInt;
        //address = stuff;
    }

    public int compareTo(IPLookUp that) {
        //if      (this.inicioInt <= that.inicioInt || this.fimInt >= that.inicioInt) return 1;
        //if      (this.inicioInt <= that.inicioInt || this.fimInt >= that.inicioInt) return 1;
        //else                            return  0;
        if (this.inicioInt < that.inicioInt) return-1;
        if (this.fimInt > that.fimInt) return 1;
        else return 0;
    }
/*
    public String ipToInt() {

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

        ///* mesmo que "ipnum" apresente resultado negativo,
        conseguimos retirar os bytes originais pelo seguinte metodo:
        // * /
        long b4 = ipnum & 0xFF;
        long b3 = (ipnum >> 8) & 0xFF;
        long b2 = (ipnum >> 16) & 0xFF;
        long b1 = (ipnum >> 24) & 0xFF;

        String repres = Long.toString(ipnum);
        return repres;
        //return ipnum;
    }
*/
    public static void main(String[] args) {

        //StdOut.println("got");
        ST<IPLookUp, String> st = new ST<IPLookUp, String>();
        //IPLookUp address = new IPLookUp();
        //ST<IPLookUp, String> st = new ST<IPLookUp, String>();

        In in = new In(args[0]);
        String[] database = in.readAllLines();
        //StdRandom.shuffle(database);

        //ST<String, String, String, String, String, String> st 
        //= new ST<String, String, String, String, String, String>();

        for (int i = 0; i < database.length; i++) {
            String[] tokens = database[i].split(",");
            String inicio = tokens[0];
            String fim = tokens[1];
            String stringInicial = tokens[2];
            String stringFim = tokens[3];
            String codePais = tokens[4];
            String pais = tokens[5];
            //st.put(inicio, fim, stringInicial, stringFim, codePais, pais);
            //st.put(inicio, stringInicial);
            //st.put(inicio, pais);
            //st.put(fim, pais);
            //st.put(pais, stringFim);
            //st.put(stringFim, pais);
            //st.put(stringInicial, pais);
            //st.put(stringInicial, stringFim);
            //st.put(stringInicial, stringFim);
            //st.put(pais, stringInicial);
            //address.inicioInt = stringInicial;
            //address.fimInt = stringFim;
            
            stringInicial = stringInicial.replace("\"","");
            stringFim = stringFim.replace("\"","");
            long longInicial = Long.parseLong(stringInicial);
            long longFim = Long.parseLong(stringFim);
            IPLookUp address = new IPLookUp(longInicial, longFim);
            st.put(address, pais);
            //StdOut.println(stringInicial);
            //StdOut.println(pais);
        }
        StdOut.println("min key: " + st.min().inicioInt);

        //16777216
        //IPLookUp n = new IPLookUp (16777216, 16777471);
        IPLookUp n = new IPLookUp (16777216, 16777216);
        //String n = "16777216";
        if (st.contains(n)) StdOut.println(st.get(n));
        else                StdOut.println("Not found");

        //StdOut.println(st.compareTo(n));




        for (IPLookUp s : st.keys()) {
            //IPLookUp q = st.get(s);
            StdOut.println(s.inicioInt + " " + s.fimInt + " " + st.get(s));
        }

        /*
        StdOut.println("min key: " + st.min());
        String fucka = st.min();
        StdOut.println(fucka + " " + st.get(fucka));
        String here = "1.0.0.0";
        String herec = "Australia";
        StdOut.println(here + " * " + st.get("1.0.0.0"));
        StdOut.println(st.get(herec));

            //StdOut.println(s + " " + st.get(s));
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            IPLookUp address = new IPLookUp(s);
            //String n = address.ipToInt();
            StdOut.println("string s long = " + s);
            //String n = address.ipToInt();
            if (st.contains(s)) StdOut.println(st.get(s));
            else                StdOut.println("Not found");
        */
            //StdOut.println(st.ceil(s));
            //StdOut.println(st.floor(s));
            //StdOut.println(st.floor(n));
            //StdOut.println(st.get("16777216"));

/*

            if (st.contains(n)) StdOut.println(st.get(n));
            else                StdOut.println("Not found");
            StdOut.println(st.ceil(n));
            //StdOut.println(st.floor(n));
            //StdOut.println(st.get("16777216"));
        }
*/

    }

}
