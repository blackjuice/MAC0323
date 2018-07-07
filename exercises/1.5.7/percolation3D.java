/*************************************************************************
 *  ano:            2015
 *  aluno:          Lucas Sung Jun Hong
 *  n.USP:          812 432 9
 *
 *  exercise:       1.5.7
 *
 *  Dependencies:   WeightedQuickUnionUF.java
 *  Compilation:    javac-algs4 miniEP3.java
 *
 *************************************************************************/

public class miniEP3 {

    public static double Percolates3D(int N, double p, int M) {
        int counter = 0;
      
        for (int count_time = 0; count_time < M; count_time++) {

            WeightedQuickUnionUF UnionFind = new WeightedQuickUnionUF(N * N * N);

            boolean[][][] sponge = randomBool3D(N, p);
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (int k = 0; k < N; k++) {
                        if (i % N != N - 1) {
                            /* i, j, k varia de 0..1 
                             * Consideramos conexoes entre dois pontos como posicoes em um array
                             * Para isso, fazemos: f(x, y, z) = x + n * y + (n^2) * x
                             */
                            
                            /* Verificando conexoes em eixo 0x */
                            int m = i + (j * N) + (k * N * N);
                            int n = i + (j * N) + (k * N * N) + 1;
                            
                            if (UnionFind.connected(m, n)) continue;
                            if(sponge[i][j][k] == true && sponge[i+1][j][k] == true)
                                UnionFind.union(m, n);
                        }
                        
                        if (j % N != N - 1) {
                            /* Verificando conexoes em eixo 0y */
                            int m = i + (j * N) + (k * N * N);
                            int n = i + (j * N) + (k * N * N) + N;
                            
                            if (UnionFind.connected(m, n)) continue;
                            if(sponge[i][j][k] == true && sponge[i][j+1][k] == true)
                                UnionFind.union(m, n);
                        }

                        if (k % N != N - 1) {
                            /* Verificando conexoes em eixo 0z */
                            int m = i + (j * N) + (k * N * N);
                            int n = i + (j * N) + (k * N * N) + N * N;
                            if (UnionFind.connected(m, n)) continue;
                            if(sponge[i][j][k] == true && sponge[i][j][k+1] == true)
                                UnionFind.union(m, n);
                        }
                    }
                }
            }
            int cont = counter;
    	    for (int start = 0; start < N*N; start++) {
                for (int end = N*N*N - N*N; end < N*N*N; end++) {
                    if (UnionFind.connected(start, end)) {
                        counter++;
                        break;
                    }
                }
    		    if (counter > cont) break;
            }
        }
        return (double) counter / M;
    }

    /* Array com 3 dimensions usando Bernoulli distribution */
    boolean[][][] RndBool = new boolean[N][N][N];
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            for (int k = 0; k < N; k++) {
                RndBool[i][j][k] = StdRandom.bernoulli(p);
                return RndBool;
            }
        }
    }
    /* main */
    public static void main(String[] args) {
        int N       = Integer.parseInt(args[0]);
        double p    = Double.parseDouble(args[1]);
        int M       = Integer.parseInt(args[2]);
        double q    = Percolates3D(N, p, M);
        
        StdOut.println(q);
    }
}
