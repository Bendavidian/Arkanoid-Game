package intefaces;

import collisions.cObject.Block;
import sprites.Velocity;

import java.util.List;

/**
 * The LevelInformation interface represents the information of a game level.
 * It provides methods to retrieve various parameters and components of the level.
 */
public interface LevelInformation {
    /**
     * Returns the number of balls in the level.
     * @return The number of balls.
     */
    int numberOfBalls();
    /**
     * Returns a list of initial velocities for the balls.
     * The size of the list should match the number of balls.
     * @return The list of initial velocities.
     */
    List<Velocity> initialBallVelocities();
    /**
     * Returns the speed of the paddle.
     * @return The paddle speed.
     */
    int paddleSpeed();
    /**
     * Returns the width of the paddle.
     * @return The paddle width.
     */
    int paddleWidth();
    /**
     * Returns the name of the level.
     * The name will be displayed at the top of the screen.
     * @return The level name.
     */
    String levelName();
    /**
     * Returns the background sprite of the level.
     * @return The background sprite.
     */
    Sprite getBackground();
    /**
     * Returns a list of blocks that make up the level.
     * @return The list of blocks.
     */
    List<Block> blocks();
    /**
     * Returns the number of blocks that should be removed
     * before the level is considered to be "cleared".
     * @return The number of blocks to remove.
     */
    int numberOfBlocksToRemove();
}
