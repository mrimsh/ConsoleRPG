package consolerpg;

import consolerpg.resources.*;
import java.util.Scanner;

/**
 *
 * @author mrimsh
 */
public class GameProcessor implements Runnable {

    Scanner scanner = new Scanner(System.in);
    private GameState gameState = GameState.Location;
    private boolean isToExit = false;
    private LoadedData loadedScenario;
    // <editor-fold defaultstate="collapsed" desc="Location fields">
    private Location loc_currentLocation;
    // </editor-fold>

    public GameProcessor() {
    }

    GameProcessor(LoadedData loadedScenario) {
        this.loadedScenario = loadedScenario;
        loc_currentLocation = loadedScenario.locations.get(loadedScenario.scenarioInfo.startLocation);
    }

    public void run() {
        throw new UnsupportedOperationException("Not supported yet.");

    }

    public void processGame() {
        while (!isToExit) {
            switch (gameState) {
                default:
                    return;
                case Battle:
                    processBattleState();
                    break;
                case Dialog:
                    processDialogState();
                    break;
                case Location:
                    processLocationState();
                    break;
            }
        }
    }

    private void processBattleState() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void processDialogState() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void processLocationState() {
        System.out.println(loc_currentLocation.description);
        System.out.println("\nВы можете отправиться в следующие места:");
        for (int i = 0; i < loc_currentLocation.travels.size(); i++)
        {
            System.out.println(loc_currentLocation.travels.get(i));
        }
        System.out.println("\nЧто вы выберете?\n");

        String result;
        result = scanner.nextLine();
        if (result.startsWith(":")) {
            RunCommand(result);
        } else {
        }
    }

    private void RunCommand(String result) {
        if (result.equalsIgnoreCase(":exit")) {
            isToExit = true;
        }
    }
}

enum GameState {

    Battle,
    Dialog,
    Location
}