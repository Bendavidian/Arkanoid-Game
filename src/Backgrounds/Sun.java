// Ben Davidian

package Backgrounds;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;

/**
 * The Sun class represents a sun background object.
 * It draws a sun animation consisting of lines and circles.
 */
public class Sun extends GeneralBackground {
    private static final int WIDTH = 830;
    private static final int HEIGHT = 620;
    public static final int NUM_OF_LINES = 50;
    public static final int CENTER_OF_SUN = 150;
    public static final int FIRST_ANGLE_GAP = 20;
    public static final int NEXT_GAP = 10;
    public static final int RADIUS = 70;
    public static final int FIFTY = 50;
    public static final int SAND_Y = 440;
    public static final int HALF = 2;
    public static final int LINE_END_Y = 260;
    public static final int WATER_HEIGHT = HEIGHT / 4;
    private static final Color SKY_COLOR = new Color(135, 206, 235);
    private static final Color SAND_COLOR = new Color(238, 214, 175);
    private static final Color WATER_COLOR = new Color(0, 119, 190);
    private static final Color LINE_COLOR = new Color(217, 175, 53);
    private static final Color BIRD_COLOR = new Color(87, 78, 60);
    private static final Color MIDDLE_CIRCLE_COLOR = new Color(192, 178, 47);
    private static final Color INNER_CIRCLE_COLOR = Color.YELLOW;
    private final Bird bird1 = new Bird(new Point(120, 180), 24, BIRD_COLOR, 1);
    private final Bird bird2 = new Bird(new Point(100, 220), 22, BIRD_COLOR, 1);
    private final Bird bird3 = new Bird(new Point(100, 240), 20, BIRD_COLOR, 1);

    /**
     * Constructs a new Sun object with the specified rectangle and color.
     * @param rect  the rectangle defining the position and size of the sun
     * @param color the color of the sun
     */
    public Sun(Rectangle rect, Color color) {
        super(rect, color);
    }

    /**
     * Draws the sun animation consisting of lines and circles on the specified DrawSurface.
     * @param d the DrawSurface to draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        super.drawOn(d);
        this.drawSunAnimation(d);
    }
    /**
     * Draws the sun animation consisting of lines and circles on the specified DrawSurface.
     * @param d the DrawSurface to draw on
     */
    private void drawSunAnimation(DrawSurface d) {
        int gap = FIRST_ANGLE_GAP;
        // Draw sky
        d.setColor(SKY_COLOR);
        d.fillRectangle(0, 0, WIDTH, HEIGHT);

        // Draw water
        d.setColor(WATER_COLOR);
        d.fillRectangle(0, WATER_HEIGHT, WIDTH, HEIGHT / HALF + FIFTY);

        // Draw sand
        d.setColor(SAND_COLOR);
        d.fillRectangle(0, SAND_Y, WIDTH, HEIGHT / HALF);

        // draws the lines from the sun
        for (int i = 0; i < NUM_OF_LINES; i++) {
            d.setColor(LINE_COLOR);
            d.drawLine(CENTER_OF_SUN, CENTER_OF_SUN, gap, LINE_END_Y);
            gap += NEXT_GAP;
        }
        // draws the 3 circles
        d.setColor(LINE_COLOR);
        d.fillCircle(CENTER_OF_SUN, CENTER_OF_SUN, RADIUS);
        d.setColor(MIDDLE_CIRCLE_COLOR);
        d.fillCircle(CENTER_OF_SUN, CENTER_OF_SUN, RADIUS - NEXT_GAP);
        d.setColor(INNER_CIRCLE_COLOR);
        d.fillCircle(CENTER_OF_SUN, CENTER_OF_SUN, RADIUS - FIRST_ANGLE_GAP);

        // Draw the birds
        bird1.draw(d);
        bird2.draw(d);
        bird3.draw(d);
    }
    /**
     * Moves the birds in each frame of the animation.
     */
    @Override
    public void timePassed() {
        bird1.move();
        bird2.move();
        bird3.move();
    }
}
