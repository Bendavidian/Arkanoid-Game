// Ben Davidian - 206844045

package sprites.sobject;

import biuoop.DrawSurface;
import listeners.Counter;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import intefaces.Sprite;

import java.awt.Color;

/**
 * The ScoreIndicator class is responsible for displaying the current score on the game screen.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    static final int SCREEN_WIDTH = 800;
    static final int REC_HEIGHT = 30;

    /**
     * Constructs a ScoreIndicator object with the given score counter.
     * @param score the score counter
     */
    public ScoreIndicator(Counter score) {
        this.score = score;

    }

    /**
     * Adds the ScoreIndicator to the game.
     * @param game the game to add the ScoreIndicator to
     */
    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    /**
     * Draws the ScoreIndicator on the given DrawSurface.
     * @param d the DrawSurface to draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        Rectangle rectangle = new Rectangle(new Point(0, 0), 600, 20);
        Point upperLeft = rectangle.getUpperLeft();

        d.drawRectangle(0, 0, SCREEN_WIDTH, REC_HEIGHT);
        d.setColor(Color.LIGHT_GRAY);
        d.fillRectangle(0, 0, SCREEN_WIDTH, REC_HEIGHT);
        d.setColor(Color.BLACK);
        d.drawText(350, 20, "Score: " + this.score.getValue(), 20);
    }

    /**
     * Performs the timePassed action for the ScoreIndicator (no action needed).
     */
    @Override
    public void timePassed() { }
}
