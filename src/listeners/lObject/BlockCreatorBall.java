// Ben Davidian - 206844045

package listeners.lObject;

import collisions.cObject.Block;
import game.GameLevel;
import intefaces.HitListener;
import listeners.Counter;
import sprites.Velocity;
import sprites.sobject.Ball;
import geometry.Point;

import java.awt.Color;

/**
 * A listener that creates a new ball and adds it to the game when a block is hit. After a certain number of hits,
 * removes the block from the game.
 */
public class BlockCreatorBall implements HitListener {
    private GameLevel game;
    private Counter ballsCounter;
    private Counter hitCounter;
    private static final int BALL_RADIUS = 5;
    private static final int HITS_THRESHOLD = 2;

    /**
     * Constructs a BlockCreatorBall.
     * @param game the game
     * @param ballsCounter the counter for tracking the number of balls
     */
    public BlockCreatorBall(GameLevel game, Counter ballsCounter) {
        this.game = game;
        this.ballsCounter = ballsCounter;
        this.hitCounter = new Counter(0);
    }

    /**
     * Creates a new ball and adds it to the game.
     * @param beingHit the block being hit
     * @param hitter the ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // Create a new ball and add it to the game
        double newBallX = 420;
        double newBallY = 550;
        Ball newBall = new Ball(new Point(newBallX, newBallY), BALL_RADIUS, Color.CYAN, game.getEnvironment());
        newBall.setVelocity(Velocity.fromAngleAndSpeed(55, 6));
        newBall.addToGame(game);
        ballsCounter.increase(1);
        // Increment the hit counter
        hitCounter.increase(1);

        // Remove the block after it has been hit three times
        if (hitCounter.getValue() >= HITS_THRESHOLD) {
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(game);
        }
    }
}
