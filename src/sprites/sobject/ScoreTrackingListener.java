// Ben Davidian

package sprites.sobject;

import collisions.cObject.Block;
import listeners.Counter;
import intefaces.HitListener;

/**
 * The ScoreTrackingListener class is responsible for tracking and updating the score when a block is hit.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currScore;
    public static final int INCREASE_SCORE = 5;

    /**
     * Constructs a ScoreTrackingListener object with the given score counter.
     * @param scoreCounter the score counter to track
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currScore = scoreCounter;
    }

    /**
     * Updates the score when a block is hit.
     * @param beingHit the block being hit
     * @param hitter the ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currScore.increase(INCREASE_SCORE);
    }

}
