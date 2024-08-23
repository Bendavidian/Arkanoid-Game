// Ben Davidian - 206844045

package listeners.lObject;

import collisions.cObject.Block;
import game.GameLevel;
import listeners.Counter;
import intefaces.HitListener;
import sprites.sobject.Ball;

/**
 * The BlockRemover class is responsible for removing blocks from the game and keeping count of the remaining blocks.
 * It implements the HitListener interface to listen for hit events.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;
    public static final int DECREASE_COUNTER = 1;
    /**
     * Constructs a BlockRemover object with the given Game and Counter.
     * @param game the Game object to remove blocks from
     * @param removedBlocks the Counter object to keep track of the remaining blocks
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Handles the hit event by removing the beingHit block from the game.
     * It also removes this listener from the block being removed and decreases the remainingBlocks counter.
     * @param beingHit the Block that is being hit
     * @param hitter the Ball that caused the hit
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.game);
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(DECREASE_COUNTER);
    }
}
