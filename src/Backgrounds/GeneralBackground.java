// Ben Davidian - 206844045

package Backgrounds;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Rectangle;
import intefaces.Sprite;

import java.awt.Color;

/**
 * The GeneralBackground class represents a background for the game.
 * It implements the Sprite interface, allowing it to be drawn on the game screen.
 * The background is defined by a rectangle shape and a color.
 */
public class GeneralBackground implements Sprite {
    private Rectangle rectangle;
    private Color color;

    /**
     * Constructs a GeneralBackground object with the specified rectangle and color.
     * @param rectangle The rectangle defining the background's shape and position.
     * @param color The color of the background.
     */
    public GeneralBackground(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
    }

    /**
     * Adds the background to the game.
     * @param game The GameLevel to which the background will be added.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    /**
     * Draws the background on the provided drawing surface.
     * @param d The drawing surface on which to draw the background.
     */
    @Override
    public void drawOn(DrawSurface d) {
        this.rectangle.drawOn(d, this.color, this.rectangle);
    }

    /**
     * This method is empty since the background does not change over time.
     */
    @Override
    public void timePassed() { }
}
