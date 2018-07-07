/*************************************************************************
 *  ano:            2015
 *  aluno:          Lucas Sung Jun Hong
 *  n.USP:          812 432 9
 *
 *  exercise:       1.3.38
 *
 *  Dependencies:   Queue.java
 *  Compilation:    javac-algs4 miniEP2.java
 *  Execution:      java-algs4 miniEP2 query1 query2 ... < input.txt
 *
 *************************************************************************/

public class miniEP2 {
    public static void main(String[] args) {
        String[]        words   = StdIn.readAllStrings();
        int             n_words = words.length;
        int             n_args  = args.length;

        /* criacao das filas para cada query */
        Queue<Integer>[] queues = (Queue<Integer>[]) new Queue[args.length];
        for (int j = 0; j < n_args; j++) queues[j] = new Queue<Integer>();

        boolean full = true;
        int bestlo  = -1, besthi = n_words;

        /* percorre-se String[] words e verifica se query ocorre na substring */
        for (int i = 0; i < n_words; i++) {
            for (int j = 0; j < n_args; j++) {
                int high, low = n_words - 1;

                /* se substring word for igual a query, adicionamos o indice na fila do query[j] com a seguinte condicao:
                 * 0 - se a fila do query[j] estiver vazia, adicionamos o indice na fila;
                 * 1 - caso contrario, retiramos o indice anterior e adicionamos o novo em seguida.
                 */
                if (words[i].equals(args[j])) {
                    if (queues[j].isEmpty())
                        queues[j].enqueue(i);
                    else {
                        queues[j].dequeue();
                        queues[j].enqueue(i);
                    }

                    /* verificamos se todos os query possuem um elemento:
                     * se pelo menos um query estiver com fila vazia, teremos um break, e saimos do loop;
                     * caso contrario, retiramos o menor elemento dentre todas as filas.
                     */
                    for (int t = 0; t < n_args; t++) {
                        if (!queues[t].isEmpty()) {
                            full = true;
                            if (queues[t].peek() < low) low = queues[t].peek();
                        }
                        else {
                            full = false;
                            break;
                        }
                    }

                    /* caso todos os query possuirem um elemento, o indice do word[i] observado
                     * passa ser a maior posicao (high). Logo, verificamos se o intervalo obtido possui o menor valor. 
                     * se sim, atualizamos besthi e bestlo;
                     * caso contrario, nao se faz nada */
                    if (full) {
                        high = queues[j].peek();
                        if (high - low < besthi - bestlo) {
                            besthi = high;
                            bestlo = low;
                        }
                    }
                }
            }
        }

        /* se o intervalo obtido for valido, imprime resultado. Caso contrario, nao existe intervalo */
        if (bestlo >= 0) {
            for (int i = bestlo; i <= besthi; i++)
                StdOut.print(words[i] + " ");
            StdOut.println();
        }
        else
            StdOut.println("NAO ENCONTRADO!");
    }
}
