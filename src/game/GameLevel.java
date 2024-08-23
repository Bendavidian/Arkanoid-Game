// Ben Davidian - 206844045

package game;

import GameAnimations.CountdownAnimation;
import GameAnimations.PauseScreen;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collisions.cObject.Block;
import intefaces.Animation;
import intefaces.Collidable;
import collisions.cObject.Paddle;
import geometry.Point;
import geometry.Rectangle;
import intefaces.LevelInformation;
import listeners.Counter;
import listeners.lObject.BallRemover;
import listeners.lObject.BlockCreatorBall;
import listeners.lObject.BlockRemover;
import listeners.lObject.KillingBlock;
import sprites.sobject.Ball;
import intefaces.Sprite;
import sprites.SpriteCollection;
import sprites.sobject.NameIndicator;
import sprites.sobject.ScoreIndicator;
import sprites.sobject.ScoreTrackingListener;

import java.awt.Color;

/**
 * The type games.Game represents the game environment and runs the game.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private biuoop.GUI gui;
    private KeyboardSensor keyboard;
    private Counter remainingBlocks;
    private Counter ballsCounter;
    private Counter scoreCounter;
    private AnimationRunner runner;
    private LevelInformation levelInfo;
    private boolean running;
    // Constants
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private static final int PADDLE_Y_LOC = 575;
    private static final int PADDLE_HEIGHT = 15;
    private static final int BALL_RADIUS = 5;
    public static final int HALF = 2;
    public static final int BONUS_SCORE = 100;
    public static final int SPACIAL_BLOCK1 = 19;
    public static final int SPACIAL_BLOCK2 = 11;
    private static final Rectangle LEFT_BORDER = new Rectangle(new Point(0, 30), 21, 600);
    private static final Rectangle RIGHT_BORDER = new Rectangle(new Point(780, 30), 20, 600);
    private static final Rectangle UPPER_BORDER = new Rectangle(new Point(0, 24), 800, 30);
    private static final Rectangle BOTTOM_BORDER = new Rectangle(new Point(0, 620), 800, 20);

    /**
     * Instantiates a new Game.
     * @param levelInfo The level information for the game.
     * @param runner The animation runner for running the game animations.
     * @param keyboard The keyboard sensor for receiving input.
     * @param scoreCounter The score counter
     */
    public GameLevel(LevelInformation levelInfo,  AnimationRunner runner, KeyboardSensor keyboard,
                     Counter scoreCounter) {
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        this.gui = runner.getGui();
        this.runner = runner;
        this.keyboard = keyboard;
        this.levelInfo = levelInfo;
        this.scoreCounter = scoreCounter;
        currLevelCounters(levelInfo);
        this.levelInfo.getBackground().addToGame(this);
    }

    /**
     * Adds a Collidable object to the game environment.
     * @param c the Collidable object to add
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Adds a Sprite object to the game environment.
     * @param s the Sprite object to add
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Gets the collection of sprites.
     * @return the collection of sprites
     */
    public SpriteCollection getSprites() {
        return this.sprites;
    }

    /**
     * Gets the game environment.
     * @return the game environment
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     * Removes a Sprite object from the game environment.
     * @param s the Sprite object to remove
     */
    public void removeSprite(Sprite s) {
        this.getSprites().removeSprite(s);
    }

    /**
     * Removes a Collidable object from the game environment.
     * @param c the Collidable object to remove
     */
    public void removeCollidable(Collidable c) {
        this.getEnvironment().removeCollidable(c);
    }
    /**
     * Returns the remaining number of blocks in the game.
     * @return the remaining number of blocks
     */
    public int getRemainingBlocks() {
        return this.remainingBlocks.getValue();
    }
    /**
     * Returns the remaining number of balls in the game.
     * @return the remaining number of balls
     */
    public int getBallCounter() {
        return this.ballsCounter.getValue();
    }
    /**
     * Returns the Counter object that tracks the number of balls in the game.
     * @return the Counter object for the balls count
     */
    public Counter getBallCounterInCounter() {
        return this.ballsCounter;
    }
    /**
     * Sets the counters for the current level based on the given LevelInformation object.
     * @param levelInfo The LevelInformation object representing the current level.
     */

    public void currLevelCounters(LevelInformation levelInfo) {
        this.remainingBlocks = new Counter(levelInfo.numberOfBlocksToRemove());
        this.ballsCounter = new Counter(levelInfo.numberOfBalls());
    }
    /**
     * Initializes the game environment, creates the game objects and adds
     * them to the game.
     */
    public void initialize() {
        this.addSprite(this.levelInfo.getBackground());
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        // add the tracking listener of the score
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.scoreCounter);
        // create blocks according to the appropriate level of the game
        BlockRemover blockRemover = new BlockRemover(this, remainingBlocks);
        for (int i = 0; i < this.levelInfo.numberOfBlocksToRemove(); i++) {
            Block currBlock = this.levelInfo.blocks().get(i);
            if (this.levelInfo.levelName().equals("Green & Building") && i == SPACIAL_BLOCK1) {
                // Create a KillingBlock listener and add it to the killing block
                    KillingBlock killingBlockListener = new KillingBlock(this, this.getBallCounterInCounter());
                    currBlock.addHitListener(killingBlockListener);
            }
            if (this.levelInfo.levelName().equals("Green & Building") && i == SPACIAL_BLOCK2) {
                // Create a BlockCreatorBall listener and add it to the ball block
                    BlockCreatorBall blockCreatorBallListener = new BlockCreatorBall(this,
                            this.getBallCounterInCounter());
                    currBlock.addHitListener(blockCreatorBallListener);
            }
            currBlock.addHitListener(scoreTrackingListener);
            currBlock.addHitListener(blockRemover);
            currBlock.addToGame(this);
        }
        // create the borders, the indicators and the balls
        this.addIndicators();
        this.createBorders();
        this.createBalls();
    }

    /**
     * Adds the score indicator and name indicator to the game.
     * Create instances of ScoreIndicator and NameIndicator and adds them to the game.
     */
    public void addIndicators() {
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.scoreCounter);
        this.sprites.addSprite(scoreIndicator);
        scoreIndicator.addToGame(this);
        NameIndicator nameIndicator = new NameIndicator(this.levelInfo.levelName());
        this.addSprite(nameIndicator);
    }
    /**
     * Creates two balls for the game.
     * The balls are of color black and are added to the game environment.
     * Each ball is set with a velocity and added to the game environment.
     */
    private void createBalls() {
        for (int i = 0; i < this.levelInfo.numberOfBalls(); i++) {
            double initialX = (double) WIDTH / 2;
            double initialY = 567;
            Ball currBall = new Ball(new Point(initialX, initialY), BALL_RADIUS, Color.WHITE, this.environment);
            currBall.setVelocity(this.levelInfo.initialBallVelocities().get(i));
            currBall.addToGame(this);
        }
    }

    /**
     * Creates the border blocks of the game.
     * Four blocks are created: top, left, right, bottom.
     * Each block is of color gray and is added to the game environment.
     */
    private void createBorders() {
        Block left = new Block(LEFT_BORDER, Color.gray);
        Block right = new Block(RIGHT_BORDER, Color.gray);
        Block upper = new Block(UPPER_BORDER, Color.gray);
        Block bottom = new Block(BOTTOM_BORDER, Color.gray);
        bottom.addHitListener(new BallRemover(this, this.ballsCounter));
        left.addToGame(this);
        right.addToGame(this);
        upper.addToGame(this);
        bottom.addToGame(this);
    }

    /**
     * Creates the paddle for the game.
     * The paddle is of color black and is added to the game environment.
     * @param keyboard - the keyboard sensor for controlling the paddle
     */
    private void createPaddle(biuoop.KeyboardSensor keyboard) {
        Rectangle paddleRect = new Rectangle(new Point((double) WIDTH / HALF
                - (double) this.levelInfo.paddleWidth() / HALF, PADDLE_Y_LOC),
                this.levelInfo.paddleWidth(), PADDLE_HEIGHT);
        Block paddleBlock = new Block(paddleRect, Color.BLACK);
        Paddle paddle = new Paddle(paddleBlock, keyboard, Color.ORANGE, environment);
        paddle.addToGame(this);
    }
    /**
     * Performs one frame of the animation.
     * @param d The drawing surface on which to draw the frame.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        // draw the sprites on the screen
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        // if a stop key is pressed
        if (this.keyboard.isPressed("P") || this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }
        // end the game if no more blocks
        if (this.remainingBlocks.getValue() == 0 || this.sprites.getSprites().size()
                - this.environment.getSize() == 1) {
            scoreCounter.increase(BONUS_SCORE);
            this.running = false;
        }
        // end the game if no more balls
        if (ballsCounter.getValue() == 0) {
            this.running = false;
        }
    }

    /**
     * Checks if the game should stop.
     * @return true if the game should stop, false otherwise
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Runs the animation.
     * This method initializes the balls, sets the running state to true,
     * and starts the animation by running the current animation using the runner.
     */
    public void run() {
        this.createPaddle(keyboard);
        // countdown before start the game
        this.runner.run(new CountdownAnimation(2, 3, this.getSprites()));
        this.running = true;
        // run the current animation
        this.runner.run(this);
    }
}
