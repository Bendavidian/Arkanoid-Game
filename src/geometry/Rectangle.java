// Ben Davidian

package geometry;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

import biuoop.DrawSurface;


/**
 * A class representing a rectangle object, defined by an upper-left point,
 * a width and a height.
 * The rectangle is aligned with the axes.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Constructs a new rectangle with the specified upper-left point,
     * width and height.
     * @param upperLeft The upper-left point of the rectangle
     * @param width The width of the rectangle
     * @param height 00The height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Returns a list of intersection points between the rectangle
     * and the line.
     * @param line The line to check intersection points with
     * @return A list of intersection points between the rectangle and the line
     */
    public List<Point> intersectionPoints(Line line) {
        // Check for intersection with each of the four sides of the rectangle
        List<Line> lines = this.getRectLines();
        List<Point> finalPoints = new ArrayList<>();
        for (Line rectLine : lines) {
            Point intersectionPoint = rectLine.intersectionWith(line);
            if (intersectionPoint != null
                    && line.isPointBetween(intersectionPoint, rectLine)) {
                finalPoints.add(intersectionPoint);
            }
        }
        return finalPoints;
    }

    /**
     * Returns a list of the four lines that make up the rectangle.
     * @return A list of the four lines that make up the rectangle.
     */
    public java.util.List getRectLines() {
        List<Line> lines1 = new ArrayList<>();
        // top line
        lines1.add(new Line(getUpperLeft(), getUpperRight()));
        // left line
        lines1.add(new Line(getUpperLeft(), getLowerLeft()));
        // bottom line
        lines1.add(new Line(getLowerLeft(), getLowerRight()));
        // right line
        lines1.add(new Line(getUpperRight(), getLowerRight()));
        return lines1;
    }

    /**
     * Sets the upper-left point of the rectangle to the specified point.
     * @param upperLeft The new upper-left point of the rectangle.
     */
    public void setNewUpperLeft(Point upperLeft) {
        this.upperLeft = upperLeft;
    }

    /**
     * Returns the width of the rectangle.
     * @return The width of the rectangle
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the height of the rectangle.
     * @return The height of the rectangle
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     * @return The upper-left point of the rectangle
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * Returns the upper-right point of the rectangle.
     * @return The upper-right point of the rectangle.
     */
    public Point getUpperRight() {
        double x = upperLeft.getX() + width;
        double y = upperLeft.getY();
        return new Point(x, y);
    }

    /**
     * Returns the lower-left point of the rectangle.
     * @return The lower-left point of the rectangle
     */
    public Point getLowerLeft() {
        double x = upperLeft.getX();
        double y = upperLeft.getY() + height;
        return new Point(x, y);
    }

    /**
     * Returns the lower-right point of the rectangle.
     * @return The lower-right point of the rectangle
     */
    public Point getLowerRight() {
        double x = upperLeft.getX() + width;
        double y = upperLeft.getY() + height;
        return new Point(x, y);
    }

    /**
     * Draws a filled rectangle on the given drawing surface with the specified color.
     * The rectangle is defined by the provided Rectangle object.
     * @param d The drawing surface on which to draw the rectangle.
     * @param color The color of the filled rectangle.
     * @param rect The Rectangle object defining the position and dimensions of the rectangle.
     */
    public void drawOn(DrawSurface d, Color color, Rectangle rect) {
        int x = (int) rect.getUpperLeft().getX();
        int y = (int) (rect.getUpperLeft().getY() - rect.getHeight());
        int width = (int) rect.getWidth();
        int height = (int) rect.getHeight();

        d.setColor(Color.BLACK);
        d.drawRectangle(x, y, width, height);

        d.setColor(color);
        d.fillRectangle(x, y, width, height);
    }

}