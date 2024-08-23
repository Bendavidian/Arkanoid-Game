// Ben Davidian - 206844045

import Levels.DirectHit;
import Levels.GreenAndBuilding;
import Levels.WideEasy;
import biuoop.GUI;
import biuoop.Sleeper;
import game.AnimationRunner;
import game.GameFlow;
import game.GameLevel;
import intefaces.LevelInformation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The main class for running the Arkanoid game.
 */
public class Ass6Game {
    public static final String NAME_OF_GAME = "Arkanoid";
    /**
     * The main entry point of the game.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        GUI gui = new GUI(NAME_OF_GAME, GameLevel.WIDTH, GameLevel.HEIGHT);
        GameFlow gameFlow = new GameFlow(gui, gui.getKeyboardSensor(), new AnimationRunner(gui, new Sleeper()));
        List<LevelInformation> levels = createLevels();

        if (args.length > 0) {
            levels = filterLevels(levels, args);
        }

        if (levels.isEmpty()) {
            levels = createLevels();
        }

        gameFlow.runLevels(levels);
        gui.close();
    }

    /**
     * Creates a list of the default levels.
     * @return A list of LevelInformation objects representing the default levels.
     */
    private static List<LevelInformation> createLevels() {
        return new ArrayList<>(Arrays.asList(new DirectHit(), new WideEasy(), new GreenAndBuilding()));
    }
    /**
     * Filters the levels based on the command-line arguments.
     * @param levels The list of LevelInformation objects to filter.
     * @param args The command-line arguments.
     * @return The filtered list of LevelInformation objects.
     */
    private static List<LevelInformation> filterLevels(List<LevelInformation> levels, String[] args) {
        List<LevelInformation> filteredLevels = new ArrayList<>();
        boolean allArgsInvalid = true;

        for (String arg : args) {
            try {
                int levelNumber = Integer.parseInt(arg) - 1;
                if (levelNumber >= 0 && levelNumber < levels.size()) {
                    filteredLevels.add(levels.get(levelNumber));
                    allArgsInvalid = false;
                }
            } catch (NumberFormatException ignored) {
                // ignore non-numeric arguments
            }
        }
        if (allArgsInvalid) {
            filteredLevels.addAll(levels);
        }
        return filteredLevels;
    }
}
