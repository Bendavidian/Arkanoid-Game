// Ben Davidian - 206844045

package sprites;

import geometry.Point;

/**
 * This class represents a velocity that specifies the change in position on
 * the x and the y axes.
 * It has two private fields dx and dy which represent the change in position
 * on the x and the y axes respectively.
 */
public class Velocity {
    // change in position on the x-axis
    private double dx;
    // change in position on the y-axis
    private double dy;
    // the default time step for calculating movement
    private static final double TIME_STEP = 1;

    /**
     * Constructs a new instance of the Velocity class with the specified
     * changes in position.
     * @param dx the change in position on the x-axis
     * @param dy the change in position on the y-axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Returns a new instance of the Velocity class with the specified angle
     * and speed.
     * @param angle the angle in degrees
     * @param speed the speed of the object
     * @return a new instance of the Velocity class
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // calculates using cos and sin functions and converts to radians
        double radians = Math.toRadians(angle);
        double dx = speed * Math.sin(radians);
        double dy = speed * Math.cos(radians) * (-1);
        return new Velocity(dx, dy);
    }

    /**
     * Returns the change in position on the x-axis.
     * @return the change in position on the x-axis
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Sets the change in position on the x-axis to the specified value.
     * @param dx the new value for the change in position on the x-axis.
     * @return the new value of the change in position on the x-axis.
     */
    public double setDx(double dx) {
        return dx;
    }

    /**
     * Sets the change in position on the y-axis to the specified value.
     * @param dy the new value for the change in position on the y-axis
     * @return the new value of the change in position on the y-axis
     */
    public double setDy(double dy) {
        return dy;
    }
    /**
     * Returns the change in position on the y-axis.
     * @return the change in position on the y-axis.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Takes a point with position (x,y) and returns a new point with position
     * (x+dx, y+dy).
     * @param p the point to apply the velocity to
     * @return a new point with position (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        double dt1 = TIME_STEP;
        double x = p.getX() + (this.dx * dt1);
        double y = p.getY() + (this.dy * dt1);
        return new Point(x, y);
    }
}