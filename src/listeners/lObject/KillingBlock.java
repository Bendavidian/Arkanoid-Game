// Ben Davidian

package listeners.lObject;

import collisions.cObject.Block;
import game.GameLevel;
import intefaces.HitListener;
import listeners.Counter;
import sprites.sobject.Ball;

/**
 * A listener that removes the ball that hits the killing block and removes the killing block after it has been hit
 * a certain number of times.
 */
public class KillingBlock implements HitListener {
    private GameLevel game;
    private Counter ballsCounter;
    private int hitsCount;

    /**
     * Constructs a KillingBlock.
     * @param game the game
     * @param ballsCounter the counter for tracking the number of balls
     */
    public KillingBlock(GameLevel game, Counter ballsCounter) {
        this.game = game;
        this.ballsCounter = ballsCounter;
        this.hitsCount = 0;
    }

    /**
     * Removes the ball that hit the killing block.
     * @param beingHit the block being hit
     * @param hitter the ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // Remove the ball that hit the killing block
        hitter.removeFromGame(game);
        ballsCounter.decrease(1);

        // Increment the hit count
        hitsCount++;

        // Check if the block has been hit twice
        if (hitsCount >= 2) {
            beingHit.removeFromGame(game);
        }
    }
}
