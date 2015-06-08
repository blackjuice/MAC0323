/*************************************************************************
 *
 *  nome:           LUCAS SUNG JUN HONG
 *  n.USP:          812 432 9
 *
 *  exercise:       EP3
 *  Compilation:    javac-algs4 Maze.java
 *  Execution:      java-algs4 Maze N [seed] < input.txt
 *  Dependencies:   
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *************************************************************************/
import java.util.Random;

public class Maze {
    private int N;                 // dimension of maze
    private boolean[][] north;     // is there a wall to north of cell i, j
    private boolean[][] east;
    private boolean[][] south;
    private boolean[][] west;
    private boolean[][] visited;
    private boolean done = false;
    // extras para definir semente
    private static Random   random;
    private static long     seed;
    // extras para definir as coordenadas de inicio e fim
    private static int      startX;
    private static int      startY;
    private static int      endX;
    private static int      endY;

    // gerador com semente
    public static void setSeed(long s) {
        seed    = s;
        random  = new Random(seed);
    }
    // coordenadas para o inicio e fim do caminho
    public static void setStartX(int s) { startX  = s; }
    public static void setStartY(int s) { startY  = s; }
    public static void setEndX(int s)   { endX    = s; }
    public static void setEndY(int s)   { endY    = s; }

    public Maze(int N) {
        this.N = N;
        StdDraw.setXscale(0, N+2);
        StdDraw.setYscale(0, N+2);
        init();
        generate();
    }

    private void init() {
        // initialize border cells as already visited
        visited = new boolean[N+2][N+2];
        for (int x = 0; x < N+2; x++) visited[x][0] = visited[x][N+1] = true;
        for (int y = 0; y < N+2; y++) visited[0][y] = visited[N+1][y] = true;

        // initialize all walls as present
        north = new boolean[N+2][N+2];
        east  = new boolean[N+2][N+2];
        south = new boolean[N+2][N+2];
        west  = new boolean[N+2][N+2];
        for (int x = 0; x < N+2; x++)
            for (int y = 0; y < N+2; y++)
                north[x][y] = east[x][y] = south[x][y] = west[x][y] = true;
    }


    // generate the maze
    private void generate(int x, int y) {
        visited[x][y] = true;

        // while there is an unvisited neighbor
        while (!visited[x][y+1] || !visited[x+1][y] || !visited[x][y-1] || !visited[x-1][y]) {

            // pick random neighbor (could use Knuth's trick instead)
            while (true) {

                // gerando r a partir da seed
                double r = random.nextDouble();

                if (r < 0.25 && !visited[x][y+1]) {
                    north[x][y] = south[x][y+1] = false;
                    //StdOut.println("north["+x+"]["+y+"] + south["+x+"]["+ (y+1) +"]");
                    //north[x][y] = south[x][y+1] = true;
                    generate(x, y + 1);
                    break;
                }
                else if (r >= 0.25 && r < 0.50 && !visited[x+1][y]) {
                    east[x][y] = west[x+1][y] = false;
                    //east[x][y] = west[x+1][y] = true;
                    generate(x+1, y);
                    break;
                }
                else if (r >= 0.5 && r < 0.75 && !visited[x][y-1]) {
                    south[x][y] = north[x][y-1] = false;
                    //south[x][y] = north[x][y-1] = true;
                    generate(x, y-1);
                    break;
                }
                else if (r >= 0.75 && r < 1.00 && !visited[x-1][y]) {
                    west[x][y] = east[x-1][y] = false;
                    //west[x][y] = east[x-1][y] = true;
                    generate(x-1, y);
                    break;
                }
            }
        }
    }

    // generate the maze starting from lower left
    private void generate() {
        generate(1, 1); 
    }

    // solve the maze using depth-first search
    private void solve(int x, int y) {
        if (x == 0 || y == 0 || x == N+1 || y == N+1) return;
        if (done || visited[x][y]) return;
        visited[x][y] = true;

        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.filledCircle(x + 0.5, y + 0.5, 0.25);
        StdDraw.show(30);

        // ponto de chegada alcançada
        if (x == endX && y == endY) done = true;

        if (!north[x][y]) solve(x, y + 1);
        if (!east[x][y])  solve(x + 1, y);
        if (!south[x][y]) solve(x, y - 1);
        if (!west[x][y])  solve(x - 1, y);

        if (done) return;

        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.filledCircle(x + 0.5, y + 0.5, 0.25);
        StdDraw.show(30);
    }

    // solve the maze starting from the start state
    public void solve() {
        for (int x = 1; x <= N; x++)
            for (int y = 1; y <= N; y++)
                visited[x][y] = false;
        done = false;
        solve(startX, startY);
    }

    // draw the maze
    public void draw() {
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(endX + 0.5, endY + 0.5, 0.375);
        StdDraw.filledCircle(startX + 0.5, startY + 0.5, 0.375);
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= N; y++) {
                if (south[x][y]) StdDraw.line(x, y, x + 1, y);
                if (north[x][y]) StdDraw.line(x, y + 1, x + 1, y + 1);
                if (west[x][y])  StdDraw.line(x, y, x, y + 1);
                if (east[x][y])  StdDraw.line(x + 1, y, x + 1, y + 1);
            }
        }
        StdDraw.show(1000);
    }


    // a test client
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        // recolhendo semente
        Maze.setSeed(Long.parseLong(args[1]));
        // leitura do arquivo .txt
        String[] posicoes = StdIn.readAllStrings();
        int n_posicoes = posicoes.length;

        for (int i = 0; i < n_posicoes; i++) {
            // definindo posicoes de inicio e fim
            int startX = Integer.parseInt(posicoes[i]);
            int startY = Integer.parseInt(posicoes[++i]);
            int endX = Integer.parseInt(posicoes[++i]);
            int endY = Integer.parseInt(posicoes[++i]);
            Maze.setStartX(startX);
            Maze.setStartY(startY);
            Maze.setEndX(endX);
            Maze.setEndY(endY);

            Maze maze = new Maze(N);
            StdDraw.show(0);
            maze.draw();
            maze.solve();
            StdDraw.clear();

            // intervalo de tempo entre uma solução e outra (definido 3s)
            try {
                Thread.sleep(500);
                //Thread.sleep(3000);
            }catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        StdOut.println(" * FIM DO PROGRAMA *");       

    }

}
