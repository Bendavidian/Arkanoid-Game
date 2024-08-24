// Ben Davidian

package intefaces;

import biuoop.DrawSurface;

/**
 * The Animation interface represents a single animation in the game.
 * An animation consists of a sequence of frames that are rendered on a drawing surface.
 */
public interface Animation {
     /**
      * Performs one frame of the animation.
      * @param d The drawing surface on which to draw the frame.
      */
     void doOneFrame(DrawSurface d);
     /**
      * Checks if the animation should stop.
      * @return true if the animation should stop, false otherwise.
      */
     boolean shouldStop();
}
