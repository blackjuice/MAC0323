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

// utilização de Splitter para a leitura do arquivo .csv
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class IPLookUpDeLuxeST implements Comparable<IPLookUpDeLuxeST>{

    private long    inicioInt;
    private long    fimInt;
    private String  address;

/*
    //private int count;
    //private static SortedIlicit obj;

    public class SortedIlicit {
        int count;
        long ipCorrespondente;
     
        public SortedIlicit (int count, int ipCorrespondente) {
            this.count = count;
            this.ipCorrespondente = ipCorrespondente;
        }
    }

    public static void setCount(int s) {
        //SortedIlicit obj = new SortedIlicit();
        obj.count = s;
    }
*/
  
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

        // representamos o IP como um inteiro do tipo long
        long ipnum =    ( (long)16777216 * Integer.parseInt(parts[0]) )
                    +   (    (long)65536 * Integer.parseInt(parts[1]) )
                    +   (      (long)256 * Integer.parseInt(parts[2]) )
                    +   (            (long)Integer.parseInt(parts[3]) );

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
        Stopwatch timer = new Stopwatch();
        ST<IPLookUpDeLuxeST, String> st = new ST<IPLookUpDeLuxeST, String>();
        //--------------------------------------------------------------------------
        // leitura de GeoIPCountryWhois.csv
        //---------------------------------
        String fileToParse = args[0];
        BufferedReader fileReader = null;
         
        // Delimitador usado em CSV
        final String DELIMITER = ",";
        try
        {
            String line = "";
            fileReader = new BufferedReader(new FileReader(fileToParse));
            // leitura linha por linha
            while ((line = fileReader.readLine()) != null)
            {
                //Get all tokens available in line
                String[] tokens = line.split(DELIMITER);
                long longInicio = ipToInt(tokens[0].replace("\"",""));  // inicio do IP
                long longFim    = ipToInt(tokens[1].replace("\"",""));  // fim do IP
                String location = tokens[4].replace("\"","") + ", "     //cityO
                                + tokens[3].replace("\"","") + ", "     //cityI
                                + tokens[2].replace("\"","");           //codePais

                IPLookUpDeLuxeST  address = new IPLookUpDeLuxeST(longInicio, longFim);
                st.put(address, location);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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
            ST<IPLookUpDeLuxeST, Integer> st_counter = new ST<IPLookUpDeLuxeST, Integer>();

            // lemos apenas o IP do arquivo .log e inserimos em st_counter
            while (!StdIn.isEmpty()) {
                String linhaLida = StdIn.readLine();
                // conversao do IP ilicito para um inteiro
                long ipIlicito = ipToInt(linhaLida.substring(linhaLida.lastIndexOf(" ") + 1)) ;
                
                IPLookUpDeLuxeST  address = new IPLookUpDeLuxeST(ipIlicito, ipIlicito);
                if (!st_counter.contains(address))  st_counter.put(address, 1);
                else                                st_counter.put(address, st_counter.get(address) + 1);
            }

            //SortedIlicit[] listSort = new SortedIlicit[st_counter.size()];
            // percorremos st_counter
            String[] impressao = new String[st_counter.size()];
            int i = 0;
            for (IPLookUpDeLuxeST s : st_counter.keys()) {
                //StdOut.println(s.inicioInt + " " + st_counter.get(s));
                //StdOut.println(st_counter.get(s)  + " " +  s.inicioInt);
                //StdOut.println(st_counter.get(s) + " " + s.count);
                IPLookUpDeLuxeST    ipLocation  = new IPLookUpDeLuxeST(s.inicioInt, s.inicioInt);
                if (st.contains(ipLocation))    {

                    //StdOut.println(st.get(ipLocation));
                    //impressao[i] = integer( st_counter.get(s) ).toString();
                    impressao[i] = String.valueOf( st_counter.get(s) ) + " " + st.get(ipLocation);
                    //StdOut.println(impressao[i]);
                    i++;

                }
                else                            StdOut.println("Not found");
            }

            Arrays.sort(impressao);

            for (int j = impressao.length - 1; j >= 0; j--)
                StdOut.println(impressao[j]);

/*

            int i = 0;
                i++;

            SortedIlicit here = new SortedIlicit();
            here.count = 0;
            StdOut.println("here.count = " + here.count);
*/            
        }
/*

*/
        StdOut.println("Elapsed Time: " + timer.elapsedTime());
    }
}
