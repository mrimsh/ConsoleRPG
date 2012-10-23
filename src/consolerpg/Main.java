package consolerpg;

import com.apple.crypto.provider.Debug;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mrimsh
 */
public class Main {

    Scanner scaner = new Scanner(System.in);
    String scenarioFile = "";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length > 0) {
            Main main;
            main = new Main(args[0]);
        } else {
            System.out.println("Input path to scenario folder");
        }
    }

    public Main(String scenarioPath) {
        // Set encoding according to OS
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.indexOf("win") >= 0) {
                System.setOut(new java.io.PrintStream(System.out, true, "Cp866"));
            } else {
                System.setOut(new java.io.PrintStream(System.out, true, "UTF8"));
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (new File(scenarioPath).exists())
        {
            LoadedData loadScenario = ScenarioLoader.getInstance().loadScenario(scenarioPath);
        }
    }
}
