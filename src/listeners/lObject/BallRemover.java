// Ben Davidian - 206844045

package listeners.lObject;

import collisions.cObject.Block;
import game.GameLevel;
import listeners.Counter;
import intefaces.HitListener;
import sprites.sobject.Ball;

/**
 * The BallRemover class is responsible for removing balls from the game when they hit a block.
 * It implements the HitListener interface to listen for hit events.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;
    public static final int DECREASE_COUNTER = 1;
    /**
     * Constructs a BallRemover object with the given game and remaining balls counter.
     * @param game The game from which balls will be removed.
     * @param removedBalls The counter that keeps track of the remaining balls.
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    /**
    * Handles the hit event when a ball hits a block.
    * Removes the ball from the game and decreases the remaining balls counter.
    * @param beingHit The block that was hit by the ball.
    * @param hitter The ball that hit the block.
    */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.remainingBalls.decrease(DECREASE_COUNTER);
        hitter.removeFromGame(game);
        hitter.removeHitListener(this);
    }
}
