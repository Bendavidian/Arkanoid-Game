// Ben Davidian
package geometry;

import java.util.List;

/**
 * The geometry.Line class represents a line in 2D space.
 */
public class Line {
    // The start point of the line.
    private Point start;
    // The end point of the line.
    private Point end;
    // The tolerance threshold used for comparing double values.
    private static final double EPSILON = 0.0001;
    /**
     * Instantiates a new geometry.Line with the given start and end points.
     * @param start the start point of the line.
     * @param end the end point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Instantiates a new geometry.Line with the given start and end coordinates.
     * @param x1 the x-coordinate of the start point.
     * @param y1 the y-coordinate of the start point.
     * @param x2 the x-coordinate of the end point.
     * @param y2 the y-coordinate of the end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        if (x1 == x2) {
            this.start = new Point(x1, Math.min(y1, y2));
            this.end = new Point(x1, Math.max(y1, y2));
        } else {
            this.start = new Point(x1, y1);
            this.end = new Point(x2, y2);
        }
    }

    /**
     * Return the length of the line.
     * @return the length
     */
    public double length() {
        // use distance method to do so
        return this.start.distance(end);
    }

    /**
     * Returns the middle point of the line.
     * @return the middle point of the line.
     */
    public Point middle() {
        if (start.equals(end)) {
            return start;
        } else {
            double x = (start.getX() + end.getX()) / 2;
            double y = (start.getY() + end.getY()) / 2;
            return new Point(x, y);
        }
    }

    /**
     * Returns the start point of the line.
     * @return the start point of the line.
     */
    public Point getStart() {
            return start;
        }

    /**
     * Returns the end point of the line.
     * @return the end point of the line.
     */
    public Point getEnd() {
        return end;
    }


    /**
     * Determines whether this line intersects another line.
     * @param other the other line to check for intersection.
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        // Calculate the parameters of the intersection point between the
        // two lines
        Point point = intersectionWith(other);
        // Check if the intersection point is valid (not null) and within the
        // bounds of both lines
        return point != null;
    }

    /**
     * Returns the intersection point of this line with the given line, if it
     * exists.
     * If the lines are parallel or do not intersect, returns null.
     *
     * @param other the other line to find the intersection point with
     * @return the intersection point of this line with the given line, or
     * null if the lines are parallel or do not intersect
     */
    public Point intersectionWith(Line other) {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        double x3 = other.start.getX();
        double y3 = other.start.getY();
        double x4 = other.end.getX();
        double y4 = other.end.getY();
        // Calculate the determinant of the matrix representing the lines
        double den = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
        // Check for special cases where lines share a common point
        if (this.start.equals(other.start) || this.start.equals(other.end)) {
            return this.start;
        }
        if (this.end.equals(other.start) || this.end.equals(other.end)) {
            return this.end;
        }
        // Check if the lines are parallel
        if (den == 0) {
            return null;
        }
        // Calculate the intersection point
        double p1 = ((x3 - x4) * (x1 * y2 - y1 * x2) - (x1 - x2)
                * (x3 * y4 - y3 * x4)) / den;
        double p2 = ((y3 - y4) * (x1 * y2 - y1 * x2) - (y1 - y2)
                * (x3 * y4 - y3 * x4)) / den;
        // Check if the intersection point is within the bounds of both lines
        if (isWithinBounds(p1, p2, x1, y1, x2, y2) && isWithinBounds(p1,
                p2, x3, y3, x4, y4)) {
            // Point of intersection is within the bounds of both lines
            return new Point(p1, p2);
        } else {
            // Point of intersection is outside the bounds of one or both lines
            return null;
        }
    }

    /**
     * Returns whether the given point is within the bounds of the line
     * specified by the given endpoints.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     * @param x1 the x-coordinate of the first endpoint of the line
     * @param y1 the y-coordinate of the first endpoint of the line
     * @param x2 the x-coordinate of the second endpoint of the line
     * @param y2 the y-coordinate of the second endpoint of the line
     * @return true if the point is within the bounds of the line,
     * false otherwise
     */
    private boolean isWithinBounds(double x, double y, double x1, double y1,
                                   double x2, double y2) {
        return x >= Math.min(x1, x2) && x <= Math.max(x1, x2)
                && y >= Math.min(y1, y2) && y <= Math.max(y1, y2);
    }

    /**
     * Equals boolean.
     * @param other the other
     * @return the boolean
     */
    public boolean equals(Line other) {
        return (start.equals(other.start) && end.equals(other.end))
                || (start.equals(other.end) && end.equals(other.start));
    }

    /**
     * check if point is on this line.
     * @param p the point to check
     * @return true if point is between line, false otherwise
     */
    public boolean isBetween(Point p) {
        boolean x = false;
        boolean y = false;
        double minX = Math.min(start.getX(), end.getX()) - EPSILON;
        double maxX = Math.max(start.getX(), end.getX()) + EPSILON;
        double minY = Math.min(start.getY(), end.getY()) - EPSILON;
        double maxY = Math.max(start.getY(), end.getY()) + EPSILON;
        // true if x is on this line
        if ((p.getX() >= minX && p.getX() <= maxX) || (p.getX()
                <= maxX && p.getX() >= minX)) {
            x = true;
        }
        // true if y is on this line
        if ((p.getY() >= minY && p.getY() <= maxY) || (p.getY()
                <= maxY && p.getY() >= minY)) {
            y = true;
        }
        return x && y;
    }

    /**
     * Checks if a point is between two endpoints of a line segment.
     * @param point the point to check
     * @param line the line segment to check against
     * @return true if the point is between the two endpoints of the line
     * segment, false otherwise
     */
    public boolean isPointBetween(Point point, Line line) {
        return isValueBetween(point.getX(), line.getStart().getX(),
                line.getEnd().getX())
                && isValueBetween(point.getY(), line.getStart().getY(),
                line.getEnd().getY());
    }

    /**
     * Checks if a value is between two other values, with a small
     * tolerance added/subtracted to avoid precision errors.
     * @param value the value to check
     * @param start the start value of the range
     * @param end the end value of the range
     * @return true if the value is between the start and end values,
     * false otherwise
     */
    private boolean isValueBetween(double value, double start, double end) {
        return ((value > Math.min(start, end) - EPSILON)
                && (value < Math.max(start, end) + EPSILON))
                || Math.abs(value - start) < EPSILON
                || Math.abs(value - end) < EPSILON;
    }

    /**
     * Returns the closest intersection point to the start of the line,
     * if it intersects with the given rectangle.
     * Otherwise, returns null.
     * @param rect The rectangle to check for intersection with the line.
     * @return The closest intersection point to the start of the line,
     * or null if there is no intersection.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersections = rect.intersectionPoints(this);
        // If there are no intersections, return null.
        if (intersections.isEmpty()) {
            return null;
        } else if (intersections.size() == 1) {
            return intersections.get(0);
        } else {
            // Find the intersection point closest to the start of the line.
            Point closestIntersection = intersections.get(0);
            double closestDistance = start.distance(closestIntersection);
            for (int i = 1; i < intersections.size(); i++) {
                Point intersection = intersections.get(i);
                double distance = start.distance(intersection);
                if (distance < closestDistance) {
                    closestIntersection = intersection;
                    closestDistance = distance;
                }
            }
            return closestIntersection;
        }
    }
}
