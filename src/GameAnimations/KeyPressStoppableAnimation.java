// Ben Davidian - 206844045

package GameAnimations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import intefaces.Animation;

/**
 * A class representing a key-press stoppable animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private boolean isAlreadyPressed;
    private boolean stop;
    private  Animation animation;
    private KeyboardSensor keyboard;
    private  String key;

    /**
     * Constructs a KeyPressStoppableAnimation.
     * @param animation The underlying animation to run.
     * @param keyboard The keyboard sensor to listen for key presses.
     * @param pressed The key that stops the animation when pressed.
     */
    public KeyPressStoppableAnimation(Animation animation, KeyboardSensor keyboard, String pressed) {
        this.animation = animation;
        this.keyboard = keyboard;
        this.key = pressed;
        this.stop = false;
        this.isAlreadyPressed = true;
    }
    /**
     * Performs one frame of the animation.
     * @param d The draw surface to draw on.
     */
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.keyboard.isPressed(this.key)) {
            if (!isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            this.isAlreadyPressed = false;
        }
    }
    /**
     * Checks if the animation should stop.
     * @return true if the animation should stop, false otherwise.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
