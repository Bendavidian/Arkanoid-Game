// Ben Davidian - 206844045
package geometry;

/**
 * A class representing a point in two-dimensional space.
 * @author Ben Davidian
 * @version 19.0.2 2023-03-23
 */
public class Point {
    // Constant threshold value
    private static final double THRESHOLD = 0.0001;
    private double x;
    private double y;

    /**
     * Constructs a point with the specified x and y coordinates.
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the distance between this point and another point.
     * @param other the other point to calculate the distance to
     * @return the distance between this point and the other point
     */
    public double distance(Point other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Determines whether this point is equal to another point.
     * @param other the other point to compare to.
     * @return true if this point is equal to the other point within the
     * threshold value, false otherwise.
     */
    public boolean equals(Point other) {
        double dx = Math.abs(this.x - other.x);
        double dy = Math.abs(this.y - other.y);
        return dx <= THRESHOLD && dy <= THRESHOLD;
    }

    /**
     * Gets the x coordinate of this point.
     * @return the x coordinate of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Gets the y coordinate of this point.
     * @return the y coordinate of this point
     */
    public double getY() {

        return this.y;
    }
}
