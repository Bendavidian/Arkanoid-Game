package Backgrounds;

import biuoop.DrawSurface;

import geometry.Point;

import java.awt.Color;
import java.awt.Polygon;

/**
 * The Bird class represents a bird object that can be drawn on a DrawSurface.
 * The bird has a position, size, color, and speed.
 */
public class Bird {
    private static final int FOUR_DIVISOR = 4;
    private static final int TWO_DIVISOR = 2;
    private static final int NUM_OF_POINTS = 7;
    private static final int WING_POINTS = 4;
    private static final int WING_SIZE_DIVISOR = 8;
    private Point position;
    private int size;
    private Color color;
    private int speed;
    /**
     * Creates a new Bird object with the specified position, size, color, and speed.
     * @param position the position of the bird
     * @param size the size of the bird
     * @param color the color of the bird
     * @param speed the speed of the bird
     */
    public Bird(Point position, int size, Color color, int speed) {
        this.position = position;
        this.size = size;
        this.color = color;
        this.speed = speed;
    }

    /**
     * Draws the bird on the specified DrawSurface.
     * @param d the DrawSurface to draw on
     */
    public void draw(DrawSurface d) {
        d.setColor(color);
        int x = (int) position.getX();
        int y = (int) position.getY();

        // Define the points of the polygon
        int[] xPoints = {x + size / FOUR_DIVISOR, x + size / TWO_DIVISOR, x + size / FOUR_DIVISOR,
                x + size / WING_SIZE_DIVISOR, x - size / WING_SIZE_DIVISOR, x - size / FOUR_DIVISOR,
                x - size / TWO_DIVISOR };
        int[] yPoints = {y + size / FOUR_DIVISOR, y - size / FOUR_DIVISOR, y - size / TWO_DIVISOR,
                y - size / TWO_DIVISOR, y - size / FOUR_DIVISOR, y + size / FOUR_DIVISOR, y + size / TWO_DIVISOR };

        // Create and fill the polygon
        Polygon polygon = new Polygon(xPoints, yPoints, NUM_OF_POINTS);
        d.fillPolygon(polygon);

        // Define the points of the bird's wings
        int[] wingXPoints = {x - size / FOUR_DIVISOR, x - size / WING_SIZE_DIVISOR, x + size / WING_SIZE_DIVISOR,
                x + size / FOUR_DIVISOR };
        int[] wingYPoints = {y, y - size / FOUR_DIVISOR, y - size / FOUR_DIVISOR, y };

        // Create and fill the wings polygon
        Polygon wings = new Polygon(wingXPoints, wingYPoints, WING_POINTS);
        Color wingColor = color.darker();
        d.setColor(wingColor);
        d.fillPolygon(wings);
    }

    /**
     * Moves the bird according to its speed.
     */
    public void move() {
        position = new Point(position.getX() + speed, position.getY());
    }
}

