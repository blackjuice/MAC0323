/*************************************************************************
 *  Compilation:  javac DepthFirstOrder.java
 *  Execution:    java DepthFirstOrder filename.txt
 *  Dependencies: Digraph.java Queue.java Stack.java StdOut.java
 *                EdgeWeightedDigraph.java DirectedEdge.java
 *  Data files:   http://algs4.cs.princeton.edu/42directed/tinyDAG.txt
 *                http://algs4.cs.princeton.edu/42directed/tinyDG.txt
 *
 *  Compute preorder and postorder for a digraph or edge-weighted digraph.
 *  Runs in O(E + V) time.
 *
 *  % java DepthFirstOrder tinyDAG.txt
 *     v  pre post
 *  --------------
 *     0    0    8
 *     1    3    2
 *     2    9   10
 *     3   10    9
 *     4    2    0
 *     5    1    1
 *     6    4    7
 *     7   11   11
 *     8   12   12
 *     9    5    6
 *    10    8    5
 *    11    6    4
 *    12    7    3
 *  Preorder:  0 5 4 1 6 9 11 12 10 2 3 7 8 
 *  Postorder: 4 5 1 12 11 10 9 6 0 3 2 7 8 
 *  Reverse postorder: 8 7 2 3 0 6 9 10 11 12 1 5 4 
 *
 *************************************************************************/

/** 
* This program computes the preorder and postorder 
* of a digraph or edge-weighted digraph
* (ignoring the weights).
* This is a client of the following ADTs:
* {@link Digraph},
* {@link EdgeWeightedDigraph},
* {@link DirectedEdge},
* {@link Queue}, and
* {@link Stack}.
*
*  @author Robert Sedgewick
*  @author Kevin Wayne
*/
// Was 
// {@link Digraph.java},
// {@link EdgeWeightedDigraph.java},
// {@link DirectedEdge.java},
// {@link Queue.java}, and
// {@link Stack.java}.
// PF fixed syntax error
//
public class DepthFirstOrder {

    private boolean[] marked;          // marked[v] = has v been marked in dfs?
    private int[] pre;                 // pre[v]    = preorder  number of v
    private int[] post;                // post[v]   = postorder number of v
    private Queue<Integer> preorder;   // vertices in preorder
    private Queue<Integer> postorder;  // vertices in postorder
    private int preCounter;            // counter for preorder numbering
    private int postCounter;           // counter for postorder numbering

    // depth-first search preorder and postorder in a digraph
    /** <tt>
      * Constructor.
      * Computes the depth-first search preorder and postorder 
      * of digraph G.
      * The vertices are put into a queue in preorder.
      * The vertices are put into another queue in postorder.
      * Runs in O(E + V) time.
      * <p>
      * Implementation details: 
      * The method calls dfs(), where most of the work is done.)</tt>
      */
    public DepthFirstOrder(Digraph G) {
        pre    = new int[G.V()];
        post   = new int[G.V()];
        postorder = new Queue<Integer>();
        preorder  = new Queue<Integer>();
        marked    = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marked[v]) dfs(G, v);
    }

    // depth-first search preorder and postorder in an edge-weighted digraph
    public DepthFirstOrder(EdgeWeightedDigraph G) {
        pre    = new int[G.V()];
        post   = new int[G.V()];
        postorder = new Queue<Integer>();
        preorder  = new Queue<Integer>();
        marked    = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marked[v]) dfs(G, v);
    }

    // run DFS in digraph G from vertex v 
    // and compute preorder/postorder
    private void dfs(Digraph G, int v) {
        marked[v] = true;
        pre[v] = preCounter++;
        preorder.enqueue(v);
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
        postorder.enqueue(v);
        post[v] = postCounter++;
    }

    // run DFS in edge-weighted digraph G from vertex v 
    // and compute preorder/postorder
    private void dfs(EdgeWeightedDigraph G, int v) {
        marked[v] = true;
        pre[v] = preCounter++;
        preorder.enqueue(v);
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (!marked[w]) {
                dfs(G, w);
            }
        }
        postorder.enqueue(v);
        post[v] = postCounter++;
    }

    /** <tt>
      * Returns the preorder number of vertex v.</tt>
      */
    public int pre(int v) {
        return pre[v];
    }

    /** <tt>
      * Returns the postorder number of vertex v.</tt>
      */
    public int post(int v) {
        return post[v];
    }

    // return vertices in postorder as an Iterable
    /** <tt>
      * Returns vertices in postorder as an Iterable.</tt>
      */
    public Iterable<Integer> post() {
        return postorder;
    }

    // return vertices in preorder as an Iterable
    /** <tt>
      * Returns vertices in preorder as an Iterable.</tt>
      */
    public Iterable<Integer> pre() {
        return preorder;
    }

    // return vertices in reverse postorder as an Iterable
    public Iterable<Integer> reversePost() {
        Stack<Integer> reverse = new Stack<Integer>();
        for (int v : postorder)
            reverse.push(v);
        return reverse;
    }


    // check that pre() and post() are consistent with pre(v) and post(v)
    private boolean check(Digraph G) {

        // check that post(v) is consistent with post()
        int r = 0;
        for (int v : post()) {
            if (post(v) != r) {
                StdOut.println("post(v) and post() inconsistent");
                return false;
            }
            r++;
        }

        // check that pre(v) is consistent with pre()
        r = 0;
        for (int v : pre()) {
            if (pre(v) != r) {
                StdOut.println("pre(v) and pre() inconsistent");
                return false;
            }
            r++;
        }

        return true;
    }


    /** <tt>
      * Receives, on the command-line, the name of a file 
      * containing the description of a digraph or an edge-weighted digraph
      * (but ignores the weights of the edges).
      * Prints the preorder and postorder of the verices of the digraph.
      * <p>
      * Here is the result of a sample run:
      * <pre>
      * % java DepthFirstOrder tinyDAG.txt
      *    v  pre post
      * --------------
      *    0    0    8
      *    1    3    2
      *    2    9   10
      *    3   10    9
      *    4    2    0
      *    5    1    1
      *    6    4    7
      *    7   11   11
      *    8   12   12
      *    9    5    6
      *   10    8    5
      *   11    6    4
      *   12    7    3
      * Preorder:  0 5 4 1 6 9 11 12 10 2 3 7 8 
      * Postorder: 4 5 1 12 11 10 9 6 0 3 2 7 8 
      * Reverse postorder: 8 7 2 3 0 6 9 10 11 12 1 5 4 
      * </pre>
      * Data files may be found in
      * http://algs4.cs.princeton.edu/42directed/tinyDAG.txt
      * and 
      * http://algs4.cs.princeton.edu/42directed/tinyDG.txt
      * </tt>
      */
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);

        DepthFirstOrder dfs = new DepthFirstOrder(G);
        StdOut.println("   v  pre post");
        StdOut.println("--------------");
        for (int v = 0; v < G.V(); v++) {
            StdOut.printf("%4d %4d %4d\n", v, dfs.pre(v), dfs.post(v));
        }

        StdOut.print("Preorder:  ");
        for (int v : dfs.pre()) {
            StdOut.print(v + " ");
        }
        StdOut.println();

        StdOut.print("Postorder: ");
        for (int v : dfs.post()) {
            StdOut.print(v + " ");
        }
        StdOut.println();

        StdOut.print("Reverse postorder: ");
        for (int v : dfs.reversePost()) {
            StdOut.print(v + " ");
        }
        StdOut.println();
    }

}

