
/*************************************************************************
 *  Compilation:  javac ColoredBall.java
 *  Execution:    java ColoredBall
 *  Dependencies: StdDraw.java
 *
 *  Implementation of a 2-d ball moving in square with coordinates
 *  between -1 and +1. Bounces off the walls upon collision.
 *  
 *
 *************************************************************************/

import java.awt.Color;

public class ColoredBall { 
    private double rx, ry;         // position
    private double vx, vy;         // velocity
    private final double radius;   // radius
    private final Color color;     // color

    // constructor
    public ColoredBall() {
        rx = 0.0;
        ry = 0.0;
        vx = 0.015 - Math.random() * 0.03; 
        vy = 0.015 - Math.random() * 0.03; 
        radius = 0.025 + Math.random() * 0.05;

        // random color of the spectrum
        // see http://java.sun.com/j2se/1.5.0/docs/api/java/awt/Color.html#getHSBColor(float,%20float,%20float)
        color = Color.getHSBColor((float) Math.random(), .8f, .8f);
    }
   
    // move the ball one step
    public void move() {
        if (Math.abs(rx + vx) + radius > 1.0) { vx = -vx; }
        if (Math.abs(ry + vy) + radius > 1.0) { vy = -vy; }
        rx = rx + vx;
        ry = ry + vy;
    }

    // draw the ball
    public void draw() { 
        StdDraw.setPenColor(color);
        StdDraw.filledCircle(rx, ry, radius);
    }



    // test client
    public static void main(String[] args) {

        // create and initialize two balls
        ColoredBall b1 = new ColoredBall();
        ColoredBall b2 = new ColoredBall();
        
        // animate them
        StdDraw.setXscale(-1.0, +1.0);
        StdDraw.setYscale(-1.0, +1.0);

        while (true) {
            b1.move();
            b2.move();
            StdDraw.clear(StdDraw.GRAY);
            b1.draw();
            b2.draw();
            StdDraw.show(50);
        }
    }
}
