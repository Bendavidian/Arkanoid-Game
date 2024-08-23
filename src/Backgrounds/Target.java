// Ben Davidian - 206844045

package Backgrounds;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;

/**
 * Represents a target background displayed in the game.
 * Extends the GeneralBackground class and adds target-specific drawing.
 */
public class Target extends GeneralBackground {
    private Rectangle rect;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int GAP = 30;
    private static final int RADIUS = 70;
    private static final int VERTICAL_LINE_Y_START = 50;
    private static final int VERTICAL_LINE_Y_END = 350;
    private static final int HORIZONTAL_LINE_X_START = 250;
    private static final int HORIZONTAL_LINE_X_END = 550;
    private static final int WIDTH_CENTER = WIDTH / 2;
    private static final int HEIGHT_CENTER = HEIGHT / 3;
    private static final int GAP_MULTIPLIER = 2;
    private final Point center = new Point((double) WIDTH / 2, (double) HEIGHT / 3);

    /**
     * Constructs a Target object with the specified rectangle and color.
     * @param rect  The rectangle representing the background area.
     * @param color The color of the background.
     */
    public Target(Rectangle rect, Color color) {
        super(rect, color);
        this.rect = rect;
    }

    /**
     * Draws the target background on the provided drawing surface.
     * Overrides the drawOn method in the GeneralBackground class.
     * @param d The drawing surface on which to draw the target background.
     */
    @Override
    public void drawOn(DrawSurface d) {
        super.drawOn(d);
        Night night = new Night(rect, Color.BLACK);
        night.drawOn(d);
        this.drawTargetAnimation(d);
    }

    /**
     * Draws the target animation on the provided drawing surface.
     * @param d The drawing surface on which to draw the target animation.
     */
    private void drawTargetAnimation(DrawSurface d) {
        int centerX = (int) center.getX();
        int centerY = (int) center.getY();

        d.setColor(Color.BLUE);
        d.drawCircle(centerX, centerY, RADIUS);
        d.drawCircle(centerX, centerY, RADIUS + GAP);
        d.drawCircle(centerX, centerY, RADIUS + GAP * GAP_MULTIPLIER);
        d.drawLine(WIDTH_CENTER, VERTICAL_LINE_Y_START, WIDTH_CENTER, VERTICAL_LINE_Y_END);
        d.drawLine(HORIZONTAL_LINE_X_START, HEIGHT_CENTER, HORIZONTAL_LINE_X_END, HEIGHT_CENTER);
    }
}
