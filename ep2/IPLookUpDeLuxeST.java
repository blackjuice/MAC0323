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

public class IPLookUpDeLuxeST implements Comparable<IPLookUpDeLuxeST>{

    private long    inicioInt;
    private long    fimInt;
    private String  address;
    private int count;

    public class SortedIlicit {
        int count;
        long ipCorrespondente;

        public SortedIlicit (int count, int ipCorrespondente) {
            this.count = count;
            this.ipCorrespondente = ipCorrespondente;
        }

    }

    public static void setCount(int s) {
        SortedIlicit obj = new SortedIlicit();
        obj.count = s;
    }
  
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
                tokens[0] = tokens[0].replace("\"",""); // inicio do IP
                tokens[1] = tokens[1].replace("\"",""); // fim do IP
                tokens[2] = tokens[2].replace("\"",""); // codePais (codigo do Pais)
                tokens[3] = tokens[3].replace("\"",""); // cityI
                tokens[4] = tokens[4].replace("\"",""); // cityO

                long longInicio = ipToInt(tokens[0]);
                long longFim = ipToInt(tokens[1]);
                String location = tokens[4] + ", " + tokens[3] + ", " + tokens[2];

                IPLookUpDeLuxeST  address = new IPLookUpDeLuxeST(longInicio, longFim);
                st.put(address, location);

                //StdOut.println(longInicio + " " + longFim + " " + location);
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
            //ST<Integer, Long> st_counter_sort = new ST<Integer, Long>();
            //<Integer, Long> st_counter_sort = new ST<Integer, Long>();
            //int x;
            while (!StdIn.isEmpty()) {
                // lemos apenas o IP do arquivo .log
                String linhaLida = StdIn.readLine();
                // conversao do IP para um inteiro
                long ipIlicito = ipToInt(linhaLida.substring(linhaLida.lastIndexOf(" ") + 1)) ;
                
                IPLookUpDeLuxeST  address = new IPLookUpDeLuxeST(ipIlicito, ipIlicito);
                if (!st_counter.contains(address)) {
                    st_counter.put(address, 1);
                    //StdOut.println(">> not added " + st_counter.get(address));
                }
                else {
                    st_counter.put(address, st_counter.get(address) + 1);
                    //StdOut.println("added >>" + st_counter.get(address));
                    
                }


                    //st_counter.put(address, x++);
                    //x++;
                    //ipIlicito++;
                    //st_counter.put((address.count)++, "*");
                    //(address.count)++;
                    //StdOut.println(">>" + address.count);
//                    st_counter.put(address, 0);
                //for (String s : st_counter.keys())
                //    StdOut.println(s + " " + st_counter.get(s));

                    //st_counter.put( (address.count)++ , "");

                //(ipLocation.count)++;
                //StdOut.println(" ** * " + (ipLocation.count)) ;
            }

            SortedIlicit[] listSort = new SortedIlicit[st_counter.size()];
            int i = 0;
            for (IPLookUpDeLuxeST s : st_counter.keys()) {
                listSort[i].count = st_counter.get(s);
                //int x = st_counter.get(s);
                //listSort[i].count = 1;
                //listSort[i].ipCorrespondente = s.inicioInt;
                StdOut.println(x + " " + i + " "+ s.inicioInt + " " + st_counter.get(s));
                i++;
                //st_counter_sort.put(st_counter.get(s) , s.inicioInt);

            }
            StdOut.println();


            //StdOut.println("size sort = "+st_counter_sort.size());
            StdOut.println("size normal = "+st_counter.size());




/*
            for (int i = 0; i < st_counter_sort.size(); i++) {
            //for (Integer s : st_counter_sort.keys()) {
                //StdOut.println(s + " " + st_counter_sort.get(s));
                StdOut.println(i + " " + st_counter_sort.get(i));
                //st_counter_sort.put( st_counter.get(s) , s.inicioInt);

            }
            StdOut.println();
                for (int i; i < st_counter.size(); i++)
                    StdOut.println(st_counter.get(i));
*/


            
        }

/*
                //String ipIlicito = linhaLida.substring(linhaLida.lastIndexOf(" ") + 1);
                //StdOut.println(coisa + " >> " + ipIlicito);
                //StdOut.println(" >> " + ipIlicito);
        In in = new In(args[0]);
        String[] database = in.readAllLines();
        Scanner scanner = new Scanner(new File(args[0]));
        //scanner.useDelimiter("\"(,\")");
        scanner.useDelimiter(",");


        while (scanner.hasNext())
        {
            String inicio           = scanner.next();
            StdOut.println( "(ini) " + inicio + " | ");
        }
         
        //Do not forget to close the scanner 
        scanner.close();
*/
            /*
            System.out.print(scanner.nextLine() + "|");
            String inicio           = scanner.nextLine();
            String fim              = scanner.next();
            String codePais         = scanner.next();
            String cityI            = scanner.next();
            String cityO            = scanner.next();
            fim = fim.replace("\"","");
            codePais = codePais.replace("\"","");
            cityI = cityI.replace("\"","");
            cityO = cityO.replace("\"","");
            StdOut.println( "(ini) " + inicio + " | (fim) " + fim + " | (codePais) "  + codePais + " | (cityI) "  + cityI + " | (cityO) " + cityO + " ");
            */








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
