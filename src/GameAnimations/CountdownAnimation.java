// Ben Davidian - 206844045

package GameAnimations;

import intefaces.Animation;
import sprites.SpriteCollection;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * The CountdownAnimation class is responsible for displaying a countdown animation on the screen.
 * It implements the Animation interface, allowing it to be used as an animation in the game.
 */
public class CountdownAnimation implements Animation {
    private double numOfSecond;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private int passedCount;
    public static final int HUNDRED = 100;
    public static final int HALF = 2;

    /**
     * Constructs a new CountdownAnimation.
     * @param numOfSecond The total duration of the countdown animation in seconds.
     * @param countFrom The number to count down from.
     * @param gameScreen The SpriteCollection representing the game screen.
     */
    public CountdownAnimation(double numOfSecond, int countFrom, SpriteCollection gameScreen) {
        this.numOfSecond = numOfSecond;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.passedCount = 0;
    }

    /**
     * Performs one frame of the countdown animation.
     * @param d The drawing surface on which to draw the frame.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        if (countFrom == passedCount) {
            this.stop = true;
        }
        // draws the screen
        Sleeper sleep = new Sleeper();
        long millisecondsPerFrame = (long) ((this.numOfSecond / countFrom) * 1000);
        this.gameScreen.drawAllOn(d);
        // set the color of the numbers on the screen
        d.setColor(Color.ORANGE);
        // draws the current number on the screen
        d.drawText(d.getWidth() / HALF, (d.getHeight() / HALF) + HUNDRED,
                Integer.toString(this.countFrom - this.passedCount), HUNDRED);
        sleep.sleepFor(millisecondsPerFrame);
        // increase to the next number in the count
        this.passedCount++;
    }

    /**
     * Checks if the countdown animation should stop.
     * @return true if the animation should stop, false otherwise.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
