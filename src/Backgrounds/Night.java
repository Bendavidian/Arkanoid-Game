// Ben Davidian - 206844045

package Backgrounds;

import biuoop.DrawSurface;
import geometry.Rectangle;

import java.awt.Color;

/**
 * Represents a night background displayed in the game.
 * Extends the GeneralBackground class and adds night-specific drawing.
 */
public class Night extends GeneralBackground {
    private static final int WIDTH_OF_FRAME = 830;
    private static final int HEIGHT_OF_FRAME = 620;
    private static final int NUM_OF_STARS = 100;
    private static final int MIN_STAR_SIZE = 1;
    private static final int MAX_STAR_SIZE = 5;
    private static final int MIN_STAR_BRIGHTNESS = 150;
    private static final int MAX_STAR_BRIGHTNESS = 256;
    private StarsToNight[] stars;

    /**
     * Constructs a Night object with the specified rectangle and color.
     * @param rect The rectangle representing the background area.
     * @param color The color of the background.
     */
    public Night(Rectangle rect, Color color) {
        super(rect, color);
        generateStars();
    }

    /**
     * Draws the night background on the provided drawing surface.
     * Overrides the drawOn method in the GeneralBackground class.
     * @param d The drawing surface on which to draw the night background.
     */
    @Override
    public void drawOn(DrawSurface d) {
        super.drawOn(d);
        this.drawNightAnimation(d);
    }
    /**
     * Generates the stars for the night background.
     */
    private void generateStars() {
        stars = new StarsToNight[NUM_OF_STARS];
        // gives a random size and color to the stars
        for (int i = 0; i < NUM_OF_STARS; i++) {
            int x = (int) (Math.random() * WIDTH_OF_FRAME);
            int y = (int) (Math.random() * HEIGHT_OF_FRAME);
            int size = (int) (Math.random() * (MAX_STAR_SIZE - MIN_STAR_SIZE + 1)) + MIN_STAR_SIZE;
            int brightness = (int) (Math.random() * (MAX_STAR_BRIGHTNESS - MIN_STAR_BRIGHTNESS + 1))
                    + MIN_STAR_BRIGHTNESS;
            stars[i] = new StarsToNight(x, y, size);
        }
    }

    /**
     * Draws the night animation on the provided drawing surface.
     * @param d The drawing surface on which to draw the night animation.
     */
    private void drawNightAnimation(DrawSurface d) {
        // Draw stars
        for (StarsToNight star : stars) {
            star.drawStars(d);
        }
    }
}

