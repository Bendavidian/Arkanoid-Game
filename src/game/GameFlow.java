// Ben Davidian

package game;

import GameAnimations.EndScreen;
import GameAnimations.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;
import intefaces.LevelInformation;
import listeners.Counter;
import biuoop.GUI;

import java.util.List;

/**
 * The GameFlow class is responsible for running the game flow and managing the levels.
 */
public class GameFlow {
    private KeyboardSensor keyboard;
    private Counter score;
    private AnimationRunner runner;
    private GUI gui;
    public static final int EMPTY = 0;
    /**
     * Constructs a new GameFlow object.
     * @param gui the GUI object for the game
     * @param ks the keyboard sensor
     * @param runner the animation runner
     */
    public GameFlow(GUI gui, KeyboardSensor ks, AnimationRunner runner) {
        this.gui = gui;
        this.keyboard = ks;
        this.runner = runner;
        this.score = new Counter(EMPTY);

    }
    /**
     * Runs the specified list of levels.
     * @param levels the list of LevelInformation objects representing the levels to be played
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.runner, this.keyboard, this.score);

            level.initialize();
            // run the level until there are no remaining blocks or balls
            while (level.getRemainingBlocks() > 0 && level.getBallCounter() > 0) {
                level.run();
            }
            // check if the player has lost the game
            if (level.getBallCounter() == 0) {
                this.runner.run(new KeyPressStoppableAnimation(new EndScreen("Game Over.", this.score),
                        this.keyboard, KeyboardSensor.SPACE_KEY));
                this.gui.close();
                break;
            }
        }
        // if the player has finished all the levels, run the end screen
        runner.run(new KeyPressStoppableAnimation(new EndScreen("You Win!", this.score),
                this.keyboard, KeyboardSensor.SPACE_KEY));
        this.gui.close();
    }
}
