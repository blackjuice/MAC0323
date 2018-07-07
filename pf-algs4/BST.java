/*************************************************************************
 *  Compilation:  javac BST.java
 *  Execution:    java BST
 *  Dependencies: StdIn.java StdOut.java  
 *  Data files:   http://algs4.cs.princeton.edu/32bst/tinyST.txt  
 *
 *  A symbol table implemented with a binary search tree.
 * 
 *  % more tinyST.txt
 *  S E A R C H E X A M P L E
 *  
 *  % java BST < tinyST.txt
 *  A 8
 *  C 4
 *  E 12
 *  H 5
 *  L 11
 *  M 9
 *  P 10
 *  R 3
 *  S 0
 *  X 7
 *
 *************************************************************************/

import java.util.NoSuchElementException;

/** This is an implementation of a symbol table whose keys are comparable.
 * The keys (and values) are kept in a (not necessarily balanced) 
 * binary search tree (BST).
 * Following our usual convention for symbol tables, 
 * the keys are pairwise distinct.
 * <p>
 * Esta é uma implementação de tabela de símbolos cujas são comparáveis.
 * As chaves (e os valores) são mantidos m uma árvore binária de busca (BST) 
 * não necessariamente balanceada.
 * Seguindo a convenção usual para tabelas de símbolos,
 * as chaves são distintas duas a duas.
 * <p>
 * For additional documentation, see 
 * <a href="http://algs4.cs.princeton.edu/32bst/">Section 3.2</a> 
 * of "Algorithms, 4th Edition" (p.396 of paper edition), 
 * by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */

public class BST<Key extends Comparable<Key>, Value> {

    private Node root;             // root of BST

    private class Node {
        private Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees
        private int N;             // number of nodes in subtree

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    /** Is this symbol table empty?<p>
     * Esta tabela de símbolos está vazia?
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /** Returns the number of (key,value) pairs in this symbol table
     * (i.e., the number of node os the tree).
     * <p>
     * Devolve o número de pares (chave,valor) nesta tabela de símbolos
     * (ou seja, o número de nós da árvore).
     */
    public int size() {
        return size(root);
    }

    // returns number of (key,value) pairs in BST rooted at x
    //
    // devolve o número de pares (chave,valor) na BST com raiz x
    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    /** Does this symbol table contain a (key,value) pair 
     * with the given key?
     * <p>
     * Esta tabela de símbolos tem um par (chave,valor) cuja chave é key?
     */
    public boolean contains(Key key) {
        return get(key) != null;
    }

   /** Returns the value associated with the given key.
    * If key is not in the symbol table, returns null.
    * <p>
    * Devolve o valor associado com a chave key
    * (se key não estiver na tabela, devolve null).
    */
    public Value get(Key key) {
        return get(root, key);
    }

    // returns the value associated with the given key 
    // in the subtree rooted at x; null if no such key
    // 
    // devolve o valor associado com a chave key
    // na subárvore cuja raiz é x; 
    // devolve null se a key não está na árvore
    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else              return x.val;
    }

   /** Inserts (key,value) pair into the BST representing this symbol table.
    * If key already exists, update with new value.
    * <p>
    * Insere o par (key,val) na BST que representa esta tabela de símbolos.
    * Se a chave key já está na tabela de símbolos, 
    * muda o correspondente valor para val. 
    */
    public void put(Key key, Value val) {
        if (val == null) { delete(key); return; }
        root = put(root, key, val);
        assert check();
    }

    // inserts (key,value) pair into the BST rooted at x
    // and returns the root of the resulting BST
    // (if key already exists, update with new value)
    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left,  key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.val   = val;
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }

    /** Deletes the smallest key (and the corresponding value) 
     * from this symbol table.
     * The symbol table must be nonempty.
     * <p>
     * Remove a menor chave (e seu valor associado) 
     * da tabela de símbolos.
     * A tabela de símbolos deve ser não vazia.
     */
    public void deleteMin() {
        if (isEmpty()) 
            throw new NoSuchElementException("Symbol table underflow");
        root = deleteMin(root);
        assert check();
    }

    // deletes the node containing the smallest key  
    // from the (nonempty) subtree rooted at x
    // and returns the root of the new subtree
    // also updates field N of the nodes of the subtree
    //
    // remove o nó que contém a menor chave
    // da subárvore (não vazia) que tem raiz x
    // e devolve a raiz da subárvore resultante
    // também atualiza o campo N dos nós da subárvore
    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    /** Deletes the largest key (and the corresponding value) 
     * from this symbol table.
     * The symbol table must be nonempty.
     */
    public void deleteMax() {
        if (isEmpty()) 
            throw new NoSuchElementException("Symbol table underflow");
        root = deleteMax(root);
        assert check();
    }

    // deletes the node containing the largest key  
    // from the (nonempty) subtree rooted at x
    // and returns the root of the new subtree
    // also updates field N of the nodes of the subtree
    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    /** Deletes from this symbol table 
     * the given key (and the corresponding value).
     * If the tree is empty, does nothing.
    * <p>
     * Remove desta tabela de símbolos 
     * a chave key (e o correspondente valor).
     * Se a tabela estiver vazia, não faz nada.
     */
    public void delete(Key key) {
        root = delete(root, key);
        assert check();
    }

    // deletes from the subtree rooted at x
    // the node containing the given key
    // and returns the root of the new subtree
    // (if key in not in the subtree, returns x)
    // also updates the N fields of the subtree
    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = delete(x.left,  key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else { 
            if (x.right == null) return x.left;
            if (x.left  == null) return x.right;
            Node t = x;
            x = min(t.right); // checkstyle warning...
            x.right = deleteMin(t.right);
            x.left = t.left;
        } 
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    } 


   /** Returns the smallest key in the symbol table
    * (or null if the table is empty).
    * <p>
    * Devolve a menor chave da tabela de símbolos
    * (ou null se a table estiver vazia).
    */
    public Key min() {
        if (isEmpty()) return null;
        return min(root).key;
    } 

    // returns the node containing the smallest key 
    // in the subtree rooted at x
    // (assumes x is nonnull)
    //
    // devolve o nó que contém a menor chave 
    // da subárvore cuja raiz é x
    // (supõe que x não é null)
    private Node min(Node x) { 
        if (x.left == null) return x; 
        else                return min(x.left); 
    } 

   /** Returns the largest key in the symbol table
    * (or null if the table is empty).
    * <p>
    * Devolve a maior chave da tabela de símbolos
    * (ou null se a table estiver vazia).
    */
    public Key max() {
        if (isEmpty()) return null;
        return max(root).key;
    } 

    // returns the node containing the largest key 
    // in the subtree rooted at x
    // (assumes x is nonnull)
    //
    // devolve o nó que contém a maior chave
    // da subárvore cuja raiz é x
    // (supõe que x não é null)
    private Node max(Node x) { 
        if (x.right == null) return x; 
        else                 return max(x.right); 
    } 


   /** Returns the largest key in the symbol table that is 
    * smaller than or equal to the given key.
    * Returns null if all keys in the table 
    * are greater than the given key.
    * <p>
    * Devolve a maior chave na tabela de símbolos que é
    * menor que ou igual a key.
    * Devolve null se todas as chaves da tabela 
    * forem maiores que key.
    */
    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        else return x.key;
    } 

    // returns the node containing the largest key 
    // among those smaller than or equal to 
    // the given key in the subtree rooted at x
    // if no such node, returns null
    //
    // devolve o nó que contém a maior chave 
    // dentre as que são menores que ou iguais à 
    // chave key na subárvore com raiz x
    // se tal nó não existe, devolve null
    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp <  0) return floor(x.left, key);
        Node t = floor(x.right, key); 
        if (t != null) return t;
        else return x; 
    } 

    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x == null) return null;
        else return x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) { 
            Node t = ceiling(x.left, key); 
            if (t != null) return t;
            else return x; 
        } 
        return ceiling(x.right, key); 
    } 

   /***********************************************************************
    *  Rank and selection
    ***********************************************************************/

   /** Returns the key whose rank is k.
    * In other words, returns the key such that 
    * there are exactly k smaller keys in the symbol table.
    * Returns null if k is negative or greater than size-1.
    * <p>
    * Devolve a chave cujo posto é k, ou seja,
    * a chave tal que há k chaves menores na tabela de símbolos.
    * Devolve null se k for negativo ou maior que size-1.
    */
    public Key select(int k) {
        if (k < 0 || k >= size()) return null;
        Node x = select(root, k);
        return x.key;
    }

    // Returns the node containing the key of rank k
    // in the subtree rooted at x.
    //
    // Devolve o nó que contém a chave de posto k
    // na subárvore cuja raiz é x.
    private Node select(Node x, int k) {
        if (x == null) return null; 
        int t = size(x.left); 
        if      (t > k) return select(x.left,  k); 
        else if (t < k) return select(x.right, k-t-1); 
        else            return x; 
    } 

   /** Returns the rank of the given key.
    * In other words, returns the number of keys
    * that are strictly smaller than the given key.
    * <p>
    * Devolve o posto da chave key, isto é, 
    * o número de chaves que são estritamente menores que key.
    */
    public int rank(Key key) {
        return rank(key, root);
    } 

    // Number of keys in the subtree rooted at x
    // that are strictly smaller than the given key.
    //
    // Número de chaves na subárvore cuja raiz é x
    // que são estritamente menores que a key.
    private int rank(Key key, Node x) {
        if (x == null) return 0; 
        int cmp = key.compareTo(x.key); 
        if      (cmp < 0) return rank(key, x.left); 
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right); 
        else              return size(x.left); 
    } 

   /***********************************************************************
    *  Range count and range search.
    ***********************************************************************/
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, lo, hi);
        return queue;
    } 

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) { 
        if (x == null) return; 
        int cmplo = lo.compareTo(x.key); 
        int cmphi = hi.compareTo(x.key); 
        if (cmplo < 0) keys(x.left, queue, lo, hi); 
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key); 
        if (cmphi > 0) keys(x.right, queue, lo, hi); 
    } 

    public int size(Key lo, Key hi) {
        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else              return rank(hi) - rank(lo);
    }


    /** Returns the height of this BST 
     * (a one-node tree has height 0).
     * <p>
     * Devolve a altura desta BST 
     * (uma árvore com apenas um nó tem altura 0). 
     */
    public int height() { 
        return height(root); 
    }

    // returns the height of the subtree whose root is x
    private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }


    // level order traversal
    public Iterable<Key> levelOrder() {
        Queue<Key> keys = new Queue<Key>();
        Queue<Node> queue = new Queue<Node>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node x = queue.dequeue();
            if (x == null) continue;
            keys.enqueue(x.key);
            queue.enqueue(x.left);
            queue.enqueue(x.right);
        }
        return keys;
    }

  /*************************************************************************
    *  Check integrity of BST data structure
    *************************************************************************/
    private boolean check() {
        if (!isBST())            StdOut.println("Not in symmetric order");
        if (!isSizeConsistent()) StdOut.println("Subtree counts not consistent");
        if (!isRankConsistent()) StdOut.println("Ranks not consistent");
        return isBST() && isSizeConsistent() && isRankConsistent();
    }

    // does this binary tree satisfy symmetric order?
    // Note: this test also ensures that data structure 
    // is a binary tree since order is strict
    private boolean isBST() {
        return isBST(root, null, null);
    }

    // is the tree rooted at x a BST with all keys strictly between min and max
    // (if min or max is null, treat as empty constraint)
    // Credit: Bob Dondero's elegant solution
    private boolean isBST(Node x, Key min, Key max) {
        if (x == null) return true;
        if (min != null && x.key.compareTo(min) <= 0) return false;
        if (max != null && x.key.compareTo(max) >= 0) return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    } 

    // are the size fields correct?
    private boolean isSizeConsistent() { return isSizeConsistent(root); }
    private boolean isSizeConsistent(Node x) {
        if (x == null) return true;
        if (x.N != size(x.left) + size(x.right) + 1) return false;
        return isSizeConsistent(x.left) && isSizeConsistent(x.right);
    } 

    // check that ranks are consistent
    private boolean isRankConsistent() {
        for (int i = 0; i < size(); i++)
            if (i != rank(select(i))) return false;
        for (Key key : keys())
            if (key.compareTo(select(rank(key))) != 0) return false;
        return true;
    }


   /** Teste client.
    * Reads string keys from standard input and inserts them 
    * into an initially empty BST.
    * the value associated with i-th read key is i.
    * When input is exhausted,
    * prints the (key,value) pairs in level order.
    * <p>
    * Lê strings da entrada padrão e associa valor i 
    * à i-ésima string lida. Insere os pares (chave,valor) 
    * numa BST inicialmente vazia. Exaurida a entrada padrão,
    * imprime os nós da BST por níveis.
    */
    public static void main(String[] args) { 
        BST<String, Integer> st = new BST<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        for (String s : st.levelOrder())
            StdOut.println(s + " " + st.get(s));

        StdOut.println();

        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
    }
}

