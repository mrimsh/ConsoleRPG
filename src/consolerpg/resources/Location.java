package consolerpg.resources;

import java.util.ArrayList;
import java.util.List;

/**
 * Object that describes any location.
 *
 * @author mrimsh
 */
public class Location extends TypicalResource {

    public List<String> mobs = new ArrayList<String>();
    public List<String> npcs = new ArrayList<String>();
    public List<String> objects = new ArrayList<String>();
    public List<String> travels = new ArrayList<String>();

    public Location() {
    }
}
