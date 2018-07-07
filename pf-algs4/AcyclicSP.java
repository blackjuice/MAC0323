/*************************************************************************
 *  Compilation:  javac AcyclicSP.java
 *  Execution:    java AcyclicSP V E
 *  Dependencies: EdgeWeightedDigraph.java DirectedEdge.java Topological.java
 *  Data files:   http://algs4.cs.princeton.edu/44sp/tinyEWDAG.txt
 *
 *  Computes shortest paths in an edge-weighted acyclic digraph.
 *
 *  % java AcyclicSP tinyEWDAG.txt 5
 *  5 to 0 (0.73)  5->4  0.35   4->0  0.38   
 *  5 to 1 (0.32)  5->1  0.32   
 *  5 to 2 (0.62)  5->7  0.28   7->2  0.34   
 *  5 to 3 (0.61)  5->1  0.32   1->3  0.29   
 *  5 to 4 (0.35)  5->4  0.35   
 *  5 to 5 (0.00)  
 *  5 to 6 (1.13)  5->1  0.32   1->3  0.29   3->6  0.52   
 *  5 to 7 (0.28)  5->7  0.28   
 *
 *************************************************************************/

// The <tt>AcyclicSP</tt> class represents a data type for solving the
// single-source shortest paths problem in edge-weighted directed acyclic
// graphs (DAGs). The edge weights can be positive, negative, or zero.
// <p>
// This implementation uses a topological-sort based algorithm.
// The constructor takes time proportional to <em>V</em> + <em>E</em>,
// where <em>V</em> is the number of vertices and <em>E</em> is the number of edges.
// Afterwards, the <tt>distTo()</tt> and <tt>hasPathTo()</tt> methods take
// constant time and the <tt>pathTo()</tt> method takes time proportional to the
// number of edges in the shortest path returned.
// <p>
// For additional documentation, see 
// <a href="http://algs4.cs.princeton.edu/44sp/">Section 4.4</a> of
// <i>Algorithms, 4th Edition</i>, by Robert Sedgewick and Kevin Wayne.
//
// @author Robert Sedgewick
// @author Kevin Wayne

/**
 * Esta classe implementa um ADT para resolver o problema 
 * dos caminhos de peso mínimo 
 * em um dag (digrafo acíclico) com pesos nos arcos.
 * Todos os caminhos começam num dado vértice s.
 * (O sufixo "SP" é uma abreviatura de Shortest Path.)
 * Os pesos dos arcos são números reais arbitrários 
 * (positivos, negativos ou nulos).
 * <p>
 * Esta implementação usa um algoritmo de ordenação topológica 
 * para realizar a tarefa.
 * O construtor consome tempo proporcional a <em>V</em> + <em>E</em>,
 * sendo <em>V</em> o número de vértices
 * e <em>E</em> o número de arcos do digrafo.
 * Depois, os métodos <tt>distTo()</tt> e <tt>hasPathTo()</tt> 
 * consomem tempo constante 
 * e o método <tt>pathTo()</tt> consome tempo proporcional ao número de acos
 * do caminho devolvido.
 * <p>
 * (Esta implementação depende das classes EdgeWeightedDigraph.java,
 * DirectedEdge.java, Topological.java.)
 * <p>
 * Documentação adicional: veja 
 * <a href="http://algs4.cs.princeton.edu/44sp/">Section 4.4</a>
 * do livro Algorithms, 4th Edition, de Robert Sedgewick e Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */

public class AcyclicSP {

    private double[] distTo;         // distTo[v] = distance  of shortest s->v path
    private DirectedEdge[] edgeTo;   // edgeTo[v] = last edge on shortest s->v path


    // Compute a shortest paths tree from <tt>s</tt> to every other vertex in
    // the directed acyclic graph <tt>G</tt>.
    // @param G the acyclic digraph
    // @param s the source vertex
    // @throws IllegalArgumentException if the digraph is not acyclic
    // @throws IllegalArgumentException unless 0 &le; <tt>s</tt> &le; <tt>V</tt> - 1
    /**
     * Apartir da raiz s, 
     * calcula uma arborescência de caminhos de peso mínimo
     * no dag G.
     * (Para cada vértice v, essa arborescência contém 
     * um caminho de peso mínimo de s a v.)
     * @throws IllegalArgumentException se o digrafo G não for um dag.
     * @throws IllegalArgumentException se s não for um vértice válido
     *         ou seja, se não estiver em 0..V-1.
     */
    public AcyclicSP(EdgeWeightedDigraph G, int s) {
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;

        // visit vertices in toplogical order
        Topological topological = new Topological(G);
        if (topological.hasOrder())
            throw new IllegalArgumentException("Digraph is not acyclic.");
        for (int v : topological.order()) {
            for (DirectedEdge e : G.adj(v))
                relax(e);
        }
    }

    // relax edge e
    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
        }       
    }

    // Returns the length of a shortest path from the source vertex <tt>s</tt> to vertex <tt>v</tt>.
    // @param v the destination vertex
    // @return the length of a shortest path from the source vertex <tt>s</tt> to vertex <tt>v</tt>;
    //    +infinity if no such path
    /**
     * Devolve o comprimento de um caminho de peso mínimo
     * do vértice s ao vértice v.
     * Devolve +infinito se não existe caminho de s a v.
     */
    public double distTo(int v) {
        return distTo[v];
    }

    // Is there a path from the source <tt>s</tt> to vertex <tt>v</tt>?
    // @param v the destination vertex
    // @return true if there is a path from the source vertex <tt>s</tt> to vertex <tt>v</tt>; false otherwise
    /**
     * Existe caminho do vértice s ao vértice v?
     */
    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    // Returns a shortest path from the source <tt>s</tt> to vertex <tt>v</tt>.
    // @param v the destination vertex
    // @return a shortest path from the source <tt>s</tt> to vertex <tt>v</tt> as an iterable of edges
     /**
     * Devolve a sequência de vértices de 
     * um caminho de peso mínimo do vértice s ao vértice v.
     * O caminho é devolvido como um Iterable de arcos.
     */
   public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }


    // Unit tests the <tt>AcyclicSP</tt> data atype.
    /**
     * Teste de unidade.
     * Testa o funcionamento da ADT AcyclicSP.
     * Recebe na linha de comando um vértice s e o 
     * nome de arquivo que descreve um dag com pesos nos arcos
     * (A primeira linha do arquivo é o número de vértices do dag;
     * a segunda linha é o número de arcos do dag;
     * cada uma das linhas seguintes tem um par v w de inteiros
     * seguido de um número real p arbitrário,
     * o que representa um arco v-w com peso p.)
     * Para cada vértice v, calcula e imprime um caminho de peso mínimo
     * de s a v.
     * Como arquivo de entrada, você pode usar
     * http://algs4.cs.princeton.edu/44sp/tinyEWDAG.txt.
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        int s = Integer.parseInt(args[1]);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);

        // find shortest path from s to each other vertex in DAG
        AcyclicSP sp = new AcyclicSP(G, s);
        for (int v = 0; v < G.V(); v++) {
            if (sp.hasPathTo(v)) {
                StdOut.printf("%d to %d (%.2f)  ", s, v, sp.distTo(v));
                for (DirectedEdge e : sp.pathTo(v)) {
                    StdOut.print(e + "   ");
                }
                StdOut.println();
            }
            else {
                StdOut.printf("%d to %d         no path\n", s, v);
            }
        }
    }
}

