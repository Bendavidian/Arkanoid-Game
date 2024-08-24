// Ben Davidian

package game;

import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import intefaces.Animation;

/**
 * The AnimationRunner class is responsible for running animations.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;
    public static final int FRAMES_PER_SECOND = 60;
    public static final int SUM_OF_FRAMES = 1000;
    /**
     * Constructs an AnimationRunner object with the given GUI and Sleeper.
     * @param gui the GUI object for displaying the animations
     * @param sleeper the Sleeper object for controlling the frame rate
     */
    public AnimationRunner(GUI gui, Sleeper sleeper) {
        this.gui = gui;
        this.framesPerSecond = FRAMES_PER_SECOND;
        this.sleeper = sleeper;
    }

    /**
     * Runs the given animation.
     * @param animation the animation to be run
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = SUM_OF_FRAMES / this.framesPerSecond;

        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);

            //Show current frame.
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
    /**
     * Returns the GUI instance used by the AnimationRunner.
     * @return The GUI instance.
     */
    public GUI getGui() {
        return gui;
    }
}
