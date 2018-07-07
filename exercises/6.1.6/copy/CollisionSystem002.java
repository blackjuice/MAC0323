/*************************************************************************
 *  Compilation:  javac CollisionSystem.java
 *  Execution:    java CollisionSystem N               (N random particles)
 *                java CollisionSystem < input.txt     (from a file) 
 *  
 *  Creates N random particles and simulates their motion according
 *  to the laws of elastic collisions.
 *
 *************************************************************************/

import java.awt.Color;

public class CollisionSystem {
    private MinPQ<Event> pq;        // the priority queue
    private double t  = 0.0;        // simulation clock time
    private double hz = 0.5;        // number of redraw events per clock tick
    private Particle[] particles;   // the array of particles


    //private Bag<Double>[] particleBag = (Bag<Double>[])new Bag[2];              // ✖‿✖
    private Bag<double[]> particleBag = new Bag<double[]>();
    //private double sumVx = 0.0, sumVy = 0.0;
    private double soma = 0.0;


    // create a new collision system with the given set of particles
    public CollisionSystem(Particle[] particles) {
        this.particles = particles.clone();   // defensive copy
    }

    // updates priority queue with all new events for particle a
    private void predict(Particle a, double limit) {
        if (a == null) return;

        // particle-particle collisions
        for (int i = 0; i < particles.length; i++) {
            double dt = a.timeToHit(particles[i]);
            if (t + dt <= limit)
                pq.insert(new Event(t + dt, a, particles[i]));
        }

        // particle-wall collisions
        double dtX = a.timeToHitVerticalWall();
        double dtY = a.timeToHitHorizontalWall();
        if (t + dtX <= limit) pq.insert(new Event(t + dtX, a, null));
        if (t + dtY <= limit) pq.insert(new Event(t + dtY, null, a));
    }

    // redraw all particles
    private void redraw(double limit) {
        StdDraw.clear();
        for (int i = 0; i < particles.length; i++) {
            particles[i].draw();
        }
        StdDraw.show(20);
        if (t < limit) {
            pq.insert(new Event(t + 1.0 / hz, null, null));
        }


        double[] particleSpeed = new double[2];
        particleSpeed[0] = particles[0].vx;
        particleSpeed[1] = particles[0].vy;
        particleBag.add(particleSpeed);

        //StdOut.println(particleSpeed[0]);
        //StdOut.println(particleBag.particleSpeed[0]);
        /*
        // inserindo vx e vy de cada particle[i]
        for (int i = 0; i < particles.length; i++) {                            // ✖‿✖
            particleBag[0].add(particles[i].vx);
            particleBag[1].add(particles[i].vy);
        }
    */

/*        
        StdOut.println("particleSpeed[0] = " + particleSpeed[0]);
        //StdOut.println("particleSpeed[1] = " + particleSpeed[1]);
        soma += particleSpeed[0];
        //sumVy += particleSpeed[1];
*/

    }

    public void statistics() {

        int Bagsize = particleBag.size();
        //double meanVx = sumVx/Bagsize;
        //double meanVy = sumVy/Bagsize;

/*
        for ( double[] x : particleBag ) {
            StdOut.println("stat[0] = " + x[0]);
        //    StdOut.println("stat[1] = " + x[1]);
        }
            //StdOut.println("stat[0] = " + x[0]);
*/
        // Calculo da media de vx
        double sumVx = 0.0;
        for ( double[] x : particleBag )
            sumVx += x[0];
        double meanVx = sumVx/Bagsize;

        // calculo da desvio padrao de vx
        sumVx = 0.0;
        for ( double[] x : particleBag )
            sumVx += (x[0] - meanVx) * (x[0] - meanVx);
        double stdVx = Math.sqrt(sumVx/(Bagsize - 1));

        // Calculo da media de vy
        double sumVy = 0.0;
        for ( double[] x : particleBag )
            sumVy += x[1];
        double meanVy = sumVy/Bagsize;

        // calculo da desvio padrao de vy
        sumVy = 0.0;
        for ( double[] x : particleBag )
            sumVy += (x[1] - meanVy) * (x[1] - meanVy);
        double stdVy = Math.sqrt(sumVy/(Bagsize - 1));

        StdOut.println("MeanVx = " + meanVx);
        StdOut.println("StdVx = " + stdVx);
        StdOut.println("MeanVy = " + meanVy);
        StdOut.println("StdVy = " + stdVy);

        
//        StdOut.println("sumVx = " + sumVx);
  //      StdOut.println("soma = " + soma);
    //    StdOut.println("int Bagsize = particleBag.size() = " + particleBag.size() );

        //for ( int x = 0; x < particleBag.size(); x++) {
            //StdOut.println(particleBag.particleSpeed[0]);
        //    StdOut.println("sumVx = " + sumVx);
        //    StdOut.println("sumVy = " + sumVy);
        //}

        //-------------------------------------------------------------------------------// ✖‿✖
        //particleBag = (Bag<Double>[])new Bag[2];
        // calculo das estatisticas
        //private Bag<double[]> particleBag = new Bag<double[]>();
        //particleBag = new Bag<double[]>();
        //private Bag<double[]> particleBag = new Bag<double[]>();
        //particleSpeed = new double[2];
        //particleBag = new Bag<double[]>();
        /*int Bagsize = particleBag.size();
        //--------------------vx--------------------
        // Calculo da media de vx
        double sumVx = 0.0;
        for ( double x : particleSpeed[0].particleBag )
            sumVx += x;
        double meanVx = sumVx/Bagsize;

        // calculo da desvio padrao de vy
        sumVx = 0.0;
        for ( double x : particleBag.particleSpeed[0] ) {
            sumVx += (x - meanVx) * (x - meanVx);
        }
        double stdVx = Math.sqrt(sumVx/(Bagsize - 1));

        //--------------------vy--------------------
        // Calculo da media de vy
        double sumVy = 0.0;
        for ( double x : particleBag.particleSpeed[1] )
            sumVy += x;
        double meanVy = sumVy/Bagsize;

        // calculo da desvio padrao de vy
        sumVy = 0.0;
        for ( double x : particleBag.particleSpeed[1] ) {
            sumVy += (x - meanVy) * (x - meanVy);
        }
        double stdVy = Math.sqrt(sumVy/(Bagsize - 1));

        StdOut.println("MeanVx:    %.2f\n", meanVx);
        StdOut.println("StdVx dev: %.2f\n", stdVx);
        StdOut.println("MeanVy:    %.2f\n", meanVy);
        StdOut.println("StdVy dev: %.2f\n", stdVy);

*/

    }

      
   /********************************************************************************
    *  Event based simulation for limit seconds
    ********************************************************************************/
    public void simulate(double limit) {
        
                                                                                                // ✖‿✖
        // criacao de 2 bags:
        //  particleBag[0] guarda vx e particleBag[1] guarda vy
        //Bag<Double>[] particleBag = (Bag<Double>[])new Bag[2];
        //Queue<Integer>[] queues = (Queue<Integer>[]) new Queue[args.length];
        //for (int j = 0; j < 2; j++) particleBag[j] = new Bag<Double>();


        // initialize PQ with collision events and redraw event
        pq = new MinPQ<Event>();
        for (int i = 0; i < particles.length; i++) {
            predict(particles[i], limit);
        }
        pq.insert(new Event(0, null, null));        // redraw event


        // the main event-driven simulation loop
        while (!pq.isEmpty()) { 

            // get impending event, discard if invalidated
            Event e = pq.delMin();
            if (!e.isValid()) continue;
            Particle a = e.a;
            Particle b = e.b;

            // physical collision, so update positions, and then simulation clock
            for (int i = 0; i < particles.length; i++)
                particles[i].move(e.time - t);
            t = e.time;

            // process event
            if      (a != null && b != null) a.bounceOff(b);              // particle-particle collision
            else if (a != null && b == null) a.bounceOffVerticalWall();   // particle-wall collision
            else if (a == null && b != null) b.bounceOffHorizontalWall(); // particle-wall collision
            else if (a == null && b == null) {                            // redraw event
                redraw(limit);
            }

            // update the priority queue with new collisions involving a or b
            predict(a, limit);
            predict(b, limit);
        }
    }


   /*************************************************************************
    *  An event during a particle collision simulation. Each event contains
    *  the time at which it will occur (assuming no supervening actions)
    *  and the particles a and b involved.
    *
    *    -  a and b both null:      redraw event
    *    -  a null, b not null:     collision with vertical wall
    *    -  a not null, b null:     collision with horizontal wall
    *    -  a and b both not null:  binary collision between a and b
    *
    *************************************************************************/
    private static class Event implements Comparable<Event> {
        private final double time;         // time that event is scheduled to occur
        private final Particle a, b;       // particles involved in event, possibly null
        private final int countA, countB;  // collision counts at event creation
                
        
        // create a new event to occur at time t involving a and b
        public Event(double t, Particle a, Particle b) {
            this.time = t;
            this.a    = a;
            this.b    = b;
            if (a != null) countA = a.count();
            else           countA = -1;
            if (b != null) countB = b.count();
            else           countB = -1;
        }

        // compare times when two events will occur
        public int compareTo(Event that) {
            if      (this.time < that.time) return -1;
            else if (this.time > that.time) return +1;
            else                            return  0;
        }
        
        // has any collision occurred between when event was created and now?
        public boolean isValid() {
            if (a != null && a.count() != countA) return false;
            if (b != null && b.count() != countB) return false;
            return true;
        }
   
    }



   /********************************************************************************
    *  Sample client
    ********************************************************************************/
    public static void main(String[] args) {

        StdDraw.setCanvasSize(800, 800);

        // remove the border
        // StdDraw.setXscale(1.0/22.0, 21.0/22.0);
        // StdDraw.setYscale(1.0/22.0, 21.0/22.0);

        // turn on animation mode
        StdDraw.show(0);

        // the array of particles
        Particle[] particles;

        // create N random particles
        if (args.length == 1) {
            int N = Integer.parseInt(args[0]);
            particles = new Particle[N];
            for (int i = 0; i < N; i++) particles[i] = new Particle();
        }

        // or read from standard input
        else {
            int N = StdIn.readInt();
            particles = new Particle[N];
            for (int i = 0; i < N; i++) {
                double rx     = StdIn.readDouble();
                double ry     = StdIn.readDouble();
                double vx     = StdIn.readDouble();
                double vy     = StdIn.readDouble();
                double radius = StdIn.readDouble();
                double mass   = StdIn.readDouble();
                int r         = StdIn.readInt();
                int g         = StdIn.readInt();
                int b         = StdIn.readInt();
                Color color   = new Color(r, g, b);
                particles[i] = new Particle(rx, ry, vx, vy, radius, mass, color);
            }
        }

        // create collision system and simulate
        CollisionSystem system = new CollisionSystem(particles);
        //system.simulate(10000);
        system.simulate(100);
        system.statistics();
    }
      
}