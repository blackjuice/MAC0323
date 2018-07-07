/*************************************************************************
 *  nome:   LUCAS SUNG JUN HONG
 *  n.USP:  812 432 9
 *
 *  exercicio 4.4.8
 *
 *  Compilation:    javac-algs4 IPLookUp.java
 *  Execution:      java-algs4 IPLookUp GeoIPCountryWhois.csv < file
 *  Dependencies:   ST.java, GeoIPCountryWhois.csv
 *
 *************************************************************************/

public class IPLookUp implements Comparable<IPLookUp>{

    private long    inicioInt;
    private long    fimInt;
    private String  address;
  
    /*  IPLookUp possui dois long: long inicioInt, long fimInt
     *  long inicioInt: guarda valor inteiro do IP inicial
     *  long fimInt:    guarda valor inteiro do IP final
     */
    public IPLookUp(long inicioInt, long fimInt) {
        this.inicioInt =    inicioInt;
        this.fimInt =       fimInt;
    }

    public int compareTo(IPLookUp that) {                     
        if (this.inicioInt < that.inicioInt)    return -1;
        if (this.fimInt > that.fimInt)          return 1;
        else                                    return 0;
    }
    /*  ipToInt recebe String address e converte esse IP em um inteiro do tipo long
     *  e retorna esse long
     */
    public static long ipToInt(String address) {
        String[] parts = address.split("\\."); // retiramos "." do IP do arquivo IPs (entrada)

        String part1 = parts[0];
        String part2 = parts[1];
        String part3 = parts[2];
        String part4 = parts[3];
        // convertemos String para Integer e guardamos seus respectivos valores
        int partInteger1 = Integer.parseInt(part1);
        int partInteger2 = Integer.parseInt(part2);
        int partInteger3 = Integer.parseInt(part3);
        int partInteger4 = Integer.parseInt(part4);
        // representamos o IP como um inteiro do tipo long
        long ipnum =    ( (long)16777216 * partInteger1 )
                    +   (    (long)65536 * partInteger2 )
                    +   (      (long)256 * partInteger3 )
                    +                (long)partInteger4;

        long b4 = ipnum & 0xFF;
        long b3 = (ipnum >> 8) & 0xFF;
        long b2 = (ipnum >> 16) & 0xFF;
        long b1 = (ipnum >> 24) & 0xFF;
        // retornamos o IP representado como inteiro
        return ipnum;
    }

    /*  O cliente main estrutura a Tabela de Simbolos com 2 keys:
     *  - valor inteiro do IP inicial;
     *  - valor inteiro do IP final;
     *  E tem como Value o String pais.
     *
     *  Em seguida, lemos o arquivo IPs e convertemos o IP lido em um inteiro()
     *  e verificamos em qual intervalo esse inteiro pertence. O intervalo correspondente
     *  equivale ao pais.
     */
    public static void main(String[] args) {

        ST<IPLookUp, String> st = new ST<IPLookUp, String>();
        //--------------------------------------------------------------------------
        // leitura de GeoIPCountryWhois.csv
        //---------------------------------
        In in = new In(args[0]);
        String[] database = in.readAllLines();

        for (int i = 0; i < database.length; i++) {
            String[] tokens =       database[i].split(",");
            String inicio =         tokens[0];
            String fim =            tokens[1];
            String stringInicial =  tokens[2];
            String stringFim =      tokens[3];
            String codePais =       tokens[4];
            String pais =           tokens[5];
            // retiramos quotes da String lida do arquivo GeoIPCountryWhois.csv
            stringInicial   = stringInicial.replace("\"","");
            stringFim       = stringFim.replace("\"","");
            pais            = pais.replace("\"","");
            // inserimos os valores sem quotes em st
            long        longInicial = Long.parseLong(stringInicial);
            long        longFim     = Long.parseLong(stringFim);
            IPLookUp    address     = new IPLookUp(longInicial, longFim);
            st.put(address, pais);
        }
        //--------------------------------------------------------------------------

        // impressao do pais correspondente ao IP
        while (!StdIn.isEmpty()) {
            String      address     = StdIn.readString();
            long        ipAsInteger = ipToInt(address);
            IPLookUp    ipLocation  = new IPLookUp(ipAsInteger, ipAsInteger);

            if (st.contains(ipLocation))    StdOut.println(st.get(ipLocation));
            else                            StdOut.println("Not found");
        }
    }
}
