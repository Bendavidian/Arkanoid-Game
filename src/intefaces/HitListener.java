// Ben Davidian

package intefaces;

import collisions.cObject.Block;
import sprites.sobject.Ball;

/**
 * The HitListener interface represents an object that listens for hit events.
 * Classes that implement this interface can respond to hit events between blocks and balls.
 */
public interface HitListener {
    /**
     * Called when a block is hit by a ball.
     * @param beingHit The block that was hit.
     * @param hitter The ball that hit the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
