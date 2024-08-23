// Ben Davidian - 206844045

package intefaces;

import biuoop.DrawSurface;
import game.GameLevel;

/**
 * sprite interface.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d the surface
     */
    void drawOn(DrawSurface d);
    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
    /**
     * Adds the sprite to the given game level.
     * @param gameLevel The game level to add the sprite to.
     */
    void addToGame(GameLevel gameLevel);
}
