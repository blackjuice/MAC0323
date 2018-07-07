/*************************************************************************
 *
 *  nome:           LUCAS SUNG JUN HONG
 *  n.USP:          812
 *
 *  exercise:       3.2.44
 *  Compilation:    javac-algs4 ModFrequencyCounter.java
 *  Execution:      java-algs4 ModFrequencyCounter L < input.txt
 *  Dependencies:   BST.java ResizingArrayQueue.java VisualAccumulator.java TestVisualAccumulator.java
 *
 *  O cliente ModFrequencyCounter usa ResizingArrayQueue. O array é do tipo
 *  Integer, que guarda o seguinte:
 *      A palavra i-esima lida do arquivo.txt ocupará uma altura h da arvore binaria 'st'
 *      O indice i-esimo do vetor 'q' guarda h, altura em que i ocupa em 'st'
 *
 *  ilustracao de uma posicao no vetor q:
 *
 *     0    1     2    -----> indices do vetor q que indicam a i-esima palavra lida do texto
 *    ---- ---- ----
 *   | 2  | 10 |  7 |  -----> inteiro que indica altura h ocupada pela i-esima palava
 *    ---- ---- ----
 *
 *
 *************************************************************************/

public class ModFrequencyCounter {

    public static void main(String[] args) {
        int distinct = 0, words = 0;
        int minlen  = Integer.parseInt(args[0]);
        BST<String, Integer>    st          = new BST<String, Integer>();
        ResizingArrayQueue<Integer> q = new ResizingArrayQueue<Integer>();

        // computacao da contagem da frequencia
        while (!StdIn.isEmpty()) {
            int comparison = 0;
            String key = StdIn.readString();
            // key = key.toLowerCase();
            // key = key.replaceAll("[^a-zA-Z]", "");
            if (key.length() < minlen) continue;
            words++;
            if (st.contains(key)) {
                st.put(key, st.get(key) + 1);
            }
            else {
                st.put(key, 1);
                distinct++;
            }

            // aproximacao do rank(key) para o valor que da altura que key ocupa
            double n = Math.ceil ((Math.log(st.rank(key) + 1) / Math.log(1.44) + 1e-10 ) );
            q.enqueue((int)n);
        }

        // impressao de key com a maior frequencia
        String max = "";
        st.put(max, 0);
        for (String word : st.keys())
            if (st.get(word) > st.get(max))
                max = word;

        /* Inicio da criacao da imagem.
         * Feita a incrementacao de st.height() por motivo puramente estético.
         */
        VisualAccumulator a = new VisualAccumulator(words, st.height() + 1);
        for (int t = 1; t <= words; t++) {
            a.addDataValue( q.dequeue() );
        }

        StdOut.println(a);                          // impressao da media
        StdOut.println(max + " " + st.get(max));
        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);

        // listagem das palavras dentro da arvore st, 
        // em caso de inclusao de mais um argumento
        if (args.length > 1)
            for (String word : st.keys())
                StdOut.println(word);
    }
}