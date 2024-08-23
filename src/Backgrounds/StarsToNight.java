package Backgrounds;

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Represents stars in the night background.
 */
public class StarsToNight {
    private static final Color STAR_COLOR = Color.WHITE;
    private int x;
    private int y;
    private int size;

    /**
     * Constructs a new star with the specified position and size.
     * @param x the x-coordinate of the star
     * @param y the y-coordinate of the star
     * @param size the size of the star
     */
    StarsToNight(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    /**
     * Draws the star on the given draw surface.
     * @param d the draw surface to draw on
     */
     void drawStars(DrawSurface d) {
        d.setColor(STAR_COLOR);
        d.fillRectangle(x, y, size, size);
        d.setColor(new Color(STAR_COLOR.getRed(), STAR_COLOR.getGreen(), STAR_COLOR.getBlue()));
        d.fillRectangle(x, y, size, size);
    }
}
