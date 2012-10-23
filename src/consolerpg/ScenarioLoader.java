package consolerpg;

import consolerpg.resources.Location;
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
            // loadCreatures(path + System.getProperty("path.separator") + "creatures.data");
            // loadDialogues(path + System.getProperty("path.separator") + "dialogues.data");
            // loadItems(path + System.getProperty("path.separator") + "items.data");
            loadLocations(path + System.getProperty("file.separator") + "locations.data");
            // loadSpells(path + System.getProperty("path.separator") + "spells.data");
            // loadSpells(path + System.getProperty("path.separator") + "spells.data");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ScenarioLoader.class.getName()).log(Level.SEVERE, null, ex);
            return null;
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

        Location newLocation = new Location();
        Scanner scanner = new Scanner(new FileReader(new File(path)));
        try {
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                if (nextLine.startsWith("#") || nextLine.isEmpty()) {
                    // if comment symbol or empty string - do nothing
                } else if (nextLine.equalsIgnoreCase("-=-")) {
                    // if single location delimeter there - save old location and create new one
                    ret.put(newLocation.name, newLocation);
                    newLocation = new Location();
                } else {
                    // else read values for old location
                    // split string to key/value
                    String[] keyValue = nextLine.split("=", 2);
                    // and save value according to the key
                    if (keyValue[0].equalsIgnoreCase("name")) {
                        newLocation.name = keyValue[1];
                    } else if (keyValue[0].equalsIgnoreCase("desc")) {
                        newLocation.description = keyValue[1];
                    } else if (keyValue[0].equalsIgnoreCase("mob")) {
                        newLocation.mobs.add(keyValue[1]);
                    } else if (keyValue[0].equalsIgnoreCase("npc")) {
                        newLocation.npcs.add(keyValue[1]);
                    } else if (keyValue[0].equalsIgnoreCase("object")) {
                        newLocation.objects.add(keyValue[1]);
                    } else if (keyValue[0].equalsIgnoreCase("travel")) {
                        newLocation.travels.add(keyValue[1]);
                    }
                }
            }
            ret.put(newLocation.name, newLocation);
        } finally {
            scanner.close();
        }
        return ret;
    }

    private void loadObjects(String path) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void loadSpells(String path) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}