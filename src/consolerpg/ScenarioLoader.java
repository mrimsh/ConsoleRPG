package consolerpg;

import consolerpg.resources.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mrimsh
 */
public class ScenarioLoader {

    // <editor-fold defaultstate="collapsed" desc="Singleton">
    private static ScenarioLoader instance;

    public static ScenarioLoader getInstance() {
        if (instance == null) {
            instance = new ScenarioLoader();
        }
        return instance;
    }
    // </editor-fold>

    private ScenarioLoader() {
    }

    public LoadedData loadScenario(String path) {
        LoadedData ret = new LoadedData();
        try {
            ret.scenarioInfo = loadScenarioProperies(path + System.getProperty("file.separator") + "scenario.data");
            // loadCreatures(path + System.getProperty("path.separator") + "creatures.data");
            // loadDialogues(path + System.getProperty("path.separator") + "dialogues.data");
            // loadItems(path + System.getProperty("path.separator") + "items.data");
            ret.locations = loadLocations(path + System.getProperty("file.separator") + "locations.data");
            // loadSpells(path + System.getProperty("path.separator") + "spells.data");
            // loadSpells(path + System.getProperty("path.separator") + "spells.data");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ScenarioLoader.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        return ret;
    }

    private ScenarioInfo loadScenarioProperies(String path) throws FileNotFoundException {
        ScenarioInfo ret = new ScenarioInfo();

        String clearedData = "";
        clearedData = RemoveComments(new Scanner(new FileReader(new File(path))));

        String[] params;
        params = clearedData.split("\\n\\$+?");

        for (int i = 0; i < params.length; i++) {
            String s = params[i];
            if (!s.isEmpty()) {

                String[] keyValue = s.split("=", 2);

                if (keyValue[0].equalsIgnoreCase("name")) {
                    ret.name = keyValue[1];
                } else if (keyValue[0].equalsIgnoreCase("desc")) {
                    ret.description = keyValue[1];
                } else if (keyValue[0].equalsIgnoreCase("startloc")) {
                    ret.startLocation = keyValue[1];
                }
            }
        }

        return ret;
    }

    private void loadCreatures(String path) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void loadDialogues(String path) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void loadItems(String path) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private HashMap<String, Location> loadLocations(String path) throws FileNotFoundException {
        HashMap<String, Location> ret = new HashMap<String, Location>();

        String clearedData;
        clearedData = RemoveComments(new Scanner(new FileReader(new File(path))));

        String[] locationBlocks;
        locationBlocks = clearedData.split("\\n\\$-");
        for (int i = 0; i < locationBlocks.length; i++) {
            if (locationBlocks[i].startsWith("$")) {
                locationBlocks[i] = "\n" + locationBlocks[i];
            }
            Location newLocation = parseLocation(locationBlocks[i]);
            ret.put(newLocation.name, newLocation);
        }

        return ret;
    }

    private Location parseLocation(String locationBlock) {
        Location ret = new Location();
        String[] params;
        params = locationBlock.split("\\n\\$+?");

        for (int i = 0; i < params.length; i++) {
            String s = params[i];
            if (!s.isEmpty()) {

                String[] keyValue = s.split("=", 2);

                if (keyValue[0].equalsIgnoreCase("name")) {
                    ret.name = keyValue[1];
                } else if (keyValue[0].equalsIgnoreCase("desc")) {
                    ret.description = keyValue[1];
                } else if (keyValue[0].equalsIgnoreCase("mob")) {
                    ret.mobs.add(keyValue[1]);
                } else if (keyValue[0].equalsIgnoreCase("npc")) {
                    ret.npcs.add(keyValue[1]);
                } else if (keyValue[0].equalsIgnoreCase("object")) {
                    ret.objects.add(keyValue[1]);
                } else if (keyValue[0].equalsIgnoreCase("travel")) {
                    ret.travels.add(keyValue[1]);
                }
            }
        }

        return ret;
    }

    private void loadObjects(String path) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void loadSpells(String path) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private String RemoveComments(Scanner scanner) {
        String clearedData = "";
        try {
            String s;
            while (scanner.hasNextLine()) {
                s = scanner.nextLine();
                if (!s.startsWith("#") && !s.isEmpty()) {
                    clearedData += s + "\n";
                }
            }
            clearedData = clearedData.substring(0, clearedData.length() - 1);
        } finally {
            scanner.close();
        }

        return clearedData;
    }
}