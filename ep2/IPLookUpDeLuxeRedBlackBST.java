/*************************************************************************
 *  nome:   LUCAS SUNG JUN HONG
 *  n.USP:  812 432 9
 *
 *  exercicio 4.4.8
 *
 *  Compilation:    javac-algs4 IPLookUpDeLuxeRedBlackBST.java
 *  Execution:      java-algs4 IPLookUpDeLuxeRedBlackBST dbip-city-2015-05h.csv < file
 *  Dependencies:   ST.java, BST.java, RedBlackBST.java, dbip-cityI-2015-05h.csv
 *
 *************************************************************************/

// utilização de Splitter para a leitura do arquivo .csv
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class IPLookUpDeLuxeRedBlackBST implements Comparable<IPLookUpDeLuxeRedBlackBST>{

    private long    inicioInt;
    private long    fimInt;
    private String  address;

    /*  IPLookUpDeLuxeRedBlackBST possui dois long: long inicioInt, long fimInt
     *  long inicioInt: guarda valor inteiro do IP inicial
     *  long fimInt:    guarda valor inteiro do IP final
     */
    public IPLookUpDeLuxeRedBlackBST(long inicioInt, long fimInt) {
        this.inicioInt =    inicioInt;
        this.fimInt =       fimInt;
    }

    public int compareTo(IPLookUpDeLuxeRedBlackBST that) {                     
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
        ST<IPLookUpDeLuxeRedBlackBST, String> st = new ST<IPLookUpDeLuxeRedBlackBST, String>();
        //--------------------------------------------------------------------------
        // leitura de GeoIPCountryWhois.csv
        //---------------------------------
        String fileToParse = args[0];
        BufferedReader fileReader = null;
        try
        {
            String line = "";
            fileReader = new BufferedReader(new FileReader(fileToParse));
            // leitura linha por linha
            while ((line = fileReader.readLine()) != null)
            {
                String[] tokens = line.split(",");
                long longInicio = ipToInt(tokens[0].replace("\"",""));  // inicio do IP
                long longFim    = ipToInt(tokens[1].replace("\"",""));  // fim do IP
                String location = tokens[4].replace("\"","") + ", "     //cityO
                                + tokens[3].replace("\"","") + ", "     //cityI
                                + tokens[2].replace("\"","");           //codePais

                IPLookUpDeLuxeRedBlackBST  address = new IPLookUpDeLuxeRedBlackBST(longInicio, longFim);
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

        // Lemos o arquivo que contem os IPs que devem ser localizados
        if (args.length == 1) {
            // impressao do pais correspondente ao IP
            while (!StdIn.isEmpty()) {
                String      address     = StdIn.readString();
                long        ipAsInteger = ipToInt(address);
                IPLookUpDeLuxeRedBlackBST    ipLocation  = new IPLookUpDeLuxeRedBlackBST(ipAsInteger, ipAsInteger);

                if (st.contains(ipLocation))    StdOut.println(st.get(ipLocation));
                else                            StdOut.println("Not found");
            }

        }

        /* Lemos apenas o IP do arquivo .log e inserimos em st_counter
        *  assim, st_counter contem lista dos IPs ilicitos.
        *  Incrementamos o valor dessa tabela, que indica # tentativas de acesso
        *  caso for identificado um mesmo IP
        */
        else {
            ST<IPLookUpDeLuxeRedBlackBST, Integer> st_counter = new ST<IPLookUpDeLuxeRedBlackBST, Integer>();

            while (!StdIn.isEmpty()) {
                String linhaLida = StdIn.readLine();
                // conversao do IP ilicito para um inteiro
                long ipIlicito = ipToInt(linhaLida.substring(linhaLida.lastIndexOf(" ") + 1)) ;
                IPLookUpDeLuxeRedBlackBST  address = new IPLookUpDeLuxeRedBlackBST(ipIlicito, ipIlicito);
                if (!st_counter.contains(address))  st_counter.put(address, 1);
                else                                st_counter.put(address, st_counter.get(address) + 1);
            }

            // percorremos st_counter que contem uma lista de IPs ilicitos
            // e verificamos se ela esta em .csv
            String[] impressao = new String[st_counter.size()];
            int i = 0;
            for (IPLookUpDeLuxeRedBlackBST s : st_counter.keys()) {
                IPLookUpDeLuxeRedBlackBST    ipLocation  = new IPLookUpDeLuxeRedBlackBST(s.inicioInt, s.inicioInt);
                if (st.contains(ipLocation))    {
                    impressao[i] = String.valueOf( st_counter.get(s) ) + " " + st.get(ipLocation);
                    i++;
                }
                else StdOut.println("Not found");
            }

            // ordenacao por numero de tentivas
            Arrays.sort(impressao);
            for(int x = 0; x < impressao.length; x++)
            {
                for (int y = x + 1 ; y < impressao.length; y++)
                {
                    String[] partsX = impressao[x].split(" ", 2);
                    String[] partsY = impressao[y].split(" ", 2);

                    if( Integer.parseInt(partsY[0]) > Integer.parseInt(partsX[0]))
                    {
                        String temp = impressao[x];
                        impressao[x] = impressao[y]; 
                        impressao[y] = temp;
                    }
                }
            }    
            // retiramos semelhantes
            for(int x = 0; x < impressao.length; x++)
            {
                String novaImpressao = new String();
                String novaImpressao2 = new String();
                for (int y = x + 1 ; y < impressao.length; y++)
                {
                    String[] partsX = impressao[x].split(" ", 2);
                    String[] partsY = impressao[y].split(" ", 2);

                    if ( partsX[1].compareTo(partsY[1]) == 0) {
                        int present = Integer.parseInt(partsX[0]);
                        int past = Integer.parseInt(partsY[0]);
                        present += past;
                        String novaPartX = new String();
                        novaPartX = novaPartX.valueOf(present);
                        impressao[x] = novaPartX + " " + partsX[1];

                        String velhaPartY = new String();
                        velhaPartY = velhaPartY.valueOf(0);
                        impressao[y] = velhaPartY + " " + partsY[1];
                    }
                    else impressao[x] = partsX[0] + " " + partsX[1];
                }
            }
            // ordenamos novamente
            for(int x = 0; x < impressao.length; x++)
            {
                for (int y = x + 1 ; y < impressao.length; y++)
                {
                    String[] partsX = impressao[x].split(" ", 2);
                    String[] partsY = impressao[y].split(" ", 2);

                    if( Integer.parseInt(partsY[0]) > Integer.parseInt(partsX[0]))
                    {
                        String temp = impressao[x];
                        impressao[x] = impressao[y]; 
                        impressao[y] = temp;
                    }
                }
                String[] seforZero = impressao[x].split(" ", 2);
                if (!seforZero[0].equals("0"))
                    StdOut.println(impressao[x]);
            }
        }


        StdOut.println("Elapsed Time: " + timer.elapsedTime());
    }
}
