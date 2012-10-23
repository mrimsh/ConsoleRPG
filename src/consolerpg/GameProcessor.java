package consolerpg;

import consolerpg.resources.Location;
import java.util.Scanner;

/**
 *
 * @author mrimsh
 */
public class GameProcessor implements Runnable {

    Scanner scaner = new Scanner(System.in);
    private GameState gameState = GameState.Location;
    private boolean isToExit = false;
    // <editor-fold defaultstate="collapsed" desc="Location fields">
    private String loc_currentName = "StartLocation";
    // </editor-fold>

    public GameProcessor() {
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
        throw new UnsupportedOperationException("Not yet implemented");
    }
}

enum GameState {

    Battle,
    Dialog,
    Location
}