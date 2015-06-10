/*************************************************************************
 *  nome:   LUCAS SUNG JUN HONG
 *  n.USP:  812 432 9
 *
 *  exercicio 4.4.8
 *
 *  Compilation:    javac-algs4 IPLookUpDeLuxeST.java
 *  Execution:      java-algs4 IPLookUpDeLuxeST dbip-city-2015-05h.csv < file
 *  Dependencies:   ST.java, BST.java, RedBlackBST.java, dbip-cityI-2015-05h.csv
 *
 *************************************************************************/

// utilização de Scanner para a leitura do arquivo .csv
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class IPLookUpDeLuxeST implements Comparable<IPLookUpDeLuxeST>{

    private long    inicioInt;
    private long    fimInt;
    private String  address;
  
    /*  IPLookUpDeLuxeST possui dois long: long inicioInt, long fimInt
     *  long inicioInt: guarda valor inteiro do IP inicial
     *  long fimInt:    guarda valor inteiro do IP final
     */
    public IPLookUpDeLuxeST(long inicioInt, long fimInt) {
        this.inicioInt =    inicioInt;
        this.fimInt =       fimInt;
    }

    public int compareTo(IPLookUpDeLuxeST that) {                     
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
    public static void main(String[] args)  throws FileNotFoundException {
        Stopwatch timer = new Stopwatch();
        ST<IPLookUpDeLuxeST, String> st = new ST<IPLookUpDeLuxeST, String>();
        //--------------------------------------------------------------------------
        // leitura de GeoIPCountryWhois.csv
        //---------------------------------

/*
        In in = new In(args[0]);
        String[] database = in.readAllLines();
*/

        Scanner scanner = new Scanner(new File(args[0]));
        //scanner.useDelimiter("\"(,\")");
        scanner.useDelimiter(",");


        while (scanner.hasNext())
        {
            String inicio           = scanner.next();
            StdOut.println( "(ini) " + inicio + " | ");
            /*
            System.out.print(scanner.nextLine() + "|");
            String inicio           = scanner.nextLine();
            String fim              = scanner.next();
            String codePais         = scanner.next();
            String cityI            = scanner.next();
            String cityO            = scanner.next();
            inicio = inicio.replace("\"","");
            fim = fim.replace("\"","");
            codePais = codePais.replace("\"","");
            cityI = cityI.replace("\"","");
            cityO = cityO.replace("\"","");
            StdOut.println( "(ini) " + inicio + " | (fim) " + fim + " | (codePais) "  + codePais + " | (cityI) "  + cityI + " | (cityO) " + cityO + " ");
            */
        }
         
        //Do not forget to close the scanner 
        scanner.close();







/*
        for (int i = 0; i < database.length; i++) {
            String[] tokens         = database[i].split("\"(,\")");
            //String[] tokens         = database[i].split(",(?=([^\"]*\"[^\"]*\")*(?![^\"]*\"))");
            //String[] tokens         = database[i].split(",");
            //\"?,(?=(?:(?:[^\"]*\"){2})*[^\"]*$)\"?    "\""
            //String[] tokens         = database[i].split("\"?,(?=(?:(?:[^\"]*\"){2})*[^\"]*$)\"?");
            //String stringInicial    = tokens[2];
            //String stringFim        = tokens[3];
            // retiramos quotes da String lida do arquivo GeoIPCountryWhois.csv
            //stringInicial           = stringInicial.replace("\"","");
            //stringFim               = stringFim.replace("\"","");
            //pais                    = pais.replace("\"","");
            // inserimos os ips, convertidos em inteiros sem quotes em st
            //long        longInicial = Long.parseLong(stringInicial);
            //long        longFim     = Long.parseLong(stringFim);
            //long        longInicio = Long.parseLong(stringInicio);
            //long        longFim = Long.parseLong(stringFim);
            long        longInicio = ipToInt(inicio);
            long        longFim = ipToInt(fim);
            String location = cityO + ", " + cityI + ", " + codePais;
            //StdOut.println(inicio);
            //StdOut.println(location);

            IPLookUpDeLuxeST    address     = new IPLookUpDeLuxeST(longInicio, longFim);
            st.put(address, location);
        }
        //--------------------------------------------------------------------------


        // sem argumento extra
        if (args.length == 1) {
            // impressao do pais correspondente ao IP
            while (!StdIn.isEmpty()) {
                String      address     = StdIn.readString();
                long        ipAsInteger = ipToInt(address);
                IPLookUpDeLuxeST    ipLocation  = new IPLookUpDeLuxeST(ipAsInteger, ipAsInteger);

                if (st.contains(ipLocation))    StdOut.println(st.get(ipLocation));
                else                            StdOut.println("Not found");
            }



        }

        // leitura do arquivo com dados de usuarios ilicitos
        else {
            StdOut.println("FUCK");

            while (!StdIn.isEmpty()) {
                String      address     = StdIn.readString();
                long        ipAsInteger = ipToInt(address);
                IPLookUpDeLuxeST    ipLocation  = new IPLookUpDeLuxeST(ipAsInteger, ipAsInteger);

                if (st.contains(ipLocation))    StdOut.println(st.get(ipLocation));
                else                            StdOut.println("Not found");
            }
            
        }
*/







        /*
        */

        StdOut.println("Elapsed Time: " + timer.elapsedTime());
    }
}
