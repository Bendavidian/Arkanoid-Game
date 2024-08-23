// Ben Davidian - 206844045

package sprites.sobject;

import biuoop.DrawSurface;
import game.GameLevel;
import intefaces.Sprite;

import java.awt.Color;

/**
 * The NameIndicator class represents a sprite that displays the level name on the screen.
 */
public class NameIndicator implements Sprite {
    private String levelName;
    /**
     * Constructs a NameIndicator with the specified level name.
     * @param levelName The name of the level to be displayed.
     */
    public NameIndicator(String levelName) {
        this.levelName = levelName;
    }
    /**
     * Draws the NameIndicator on the given DrawSurface.
     * @param d The DrawSurface on which to draw the NameIndicator.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(520, 20, "Level Name: " + this.levelName, 20);
    }
    /**
     * Performs the timePassed action for the ScoreIndicator (no action needed).
     */
    @Override
    public void timePassed() { }
    /**
     * Adds the NameIndicator to the given game level.
     * @param gameLevel The game level to add the NameIndicator to.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}
