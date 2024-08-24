// Ben Davidian

package Backgrounds;

import biuoop.DrawSurface;
import geometry.Rectangle;

import java.awt.Color;
/**
 * The Building class represents a building background in the game.
 * It extends the GeneralBackground class and implements the drawOn method to draw the building animation.
 */
public class Building extends GeneralBackground {
    private static final int BUILDING_COLOR_R = 70;
    private static final int BUILDING_COLOR_G = 400;
    private static final int BUILDING_COLOR_WIDTH = 130;
    private static final int BUILDING_COLOR_HEIGHT = 600;
    private static final int WINDOW_COLOR_R = 120;
    private static final int WINDOW_COLOR_G = 350;
    private static final int WINDOW_COLOR_WIDTH = 35;
    private static final int WINDOW_COLOR_HEIGHT = 50;
    private static final int BAR_COLOR_R = 130;
    private static final int BAR_COLOR_G = 220;
    private static final int BAR_COLOR_WIDTH = 15;
    private static final int BAR_COLOR_HEIGHT = 195;
    private static final int WINDOW_START_X = 80;
    private static final int WINDOW_START_Y = 410;
    private static final int WINDOW_WIDTH = 10;
    private static final int WINDOW_HEIGHT = 30;
    private static final int WINDOW_GAP_X = 20;
    private static final int WINDOW_GAP_Y = 50;
    private static final int SUN_CENTER_X = 137;
    private static final int SUN_CENTER_Y = 210;
    private static final int SUN_RADIUS = 13;
    private static final int SUN_SMALL_RADIUS = 9;
    private static final int SUN_DOT_RADIUS = 4;
    /**
     * Constructs a Building object with the specified rectangle and color.
     * @param rect  the rectangle representing the background area
     * @param color the color of the background
     */
    public Building(Rectangle rect, Color color) {
        super(rect, color);
    }

    /**
     * Draws the building animation on the given DrawSurface.
     * @param d the DrawSurface to draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        super.drawOn(d);
        this.drawBuildingAnimation(d);
    }
    /**
     * Draws the building animation on the given DrawSurface.
     * @param d the DrawSurface to draw on
     */
    private void drawBuildingAnimation(DrawSurface d) {
        d.setColor(Color.DARK_GRAY);
        d.fillRectangle(BUILDING_COLOR_R, BUILDING_COLOR_G, BUILDING_COLOR_WIDTH, BUILDING_COLOR_HEIGHT);
        d.fillRectangle(WINDOW_COLOR_R, WINDOW_COLOR_G, WINDOW_COLOR_WIDTH, WINDOW_COLOR_HEIGHT);
        d.fillRectangle(BAR_COLOR_R, BAR_COLOR_G, BAR_COLOR_WIDTH, BAR_COLOR_HEIGHT);

        // draws the windows inside the building
        int windowX = WINDOW_START_X;
        int windowY = WINDOW_START_Y;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                d.setColor(Color.WHITE);
                d.fillRectangle(windowX, windowY, WINDOW_WIDTH, WINDOW_HEIGHT);
                windowX += WINDOW_GAP_X;
            }
            windowX = WINDOW_START_X;
            windowY += WINDOW_GAP_Y;
        }
        // draws the light bulbs
        d.setColor(Color.ORANGE);
        d.fillCircle(SUN_CENTER_X, SUN_CENTER_Y, SUN_RADIUS);
        d.setColor(Color.RED);
        d.fillCircle(SUN_CENTER_X, SUN_CENTER_Y, SUN_SMALL_RADIUS);
        d.setColor(Color.WHITE);
        d.fillCircle(SUN_CENTER_X, SUN_CENTER_Y, SUN_DOT_RADIUS);
        d.setColor(Color.BLACK);
        d.drawText(38, 80, "There are two blocks hidden here,\n"
                + " one that will add a ball to you, ", 17);
        d.setColor(Color.BLACK);
        d.drawText(38, 95, "And a block that will remove the ball you hit. Can "
                + "you guess where they are located?", 17);
    }
}
