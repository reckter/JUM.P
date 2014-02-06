package me.reckter.CodeCreator.Code.Marker;

import me.reckter.CodeCreator.Code.Register.Register;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by reckter on 2/6/14.
 */
public class MarkerHandler {

    static protected HashMap<String, Marker> markers;

    public MarkerHandler() {
        markers = new HashMap<String, Marker>();
    }

    public static Marker getMarker(String str) {
        if(markers.get(str) == null) {
            markers.put(str, new Marker(str));
        }
        return markers.get(str);
    }

    public static void addMarker(String str) {
        markers.put(str, new Marker(str));
    }

    public static Marker getRandomMarker() {
        if(markers.values().size() == 0) {
            return new Marker("NULL");
        }

        int size = markers.values().size();
        int random = (int) (Math.random() * size);
        Marker ret = markers.values().iterator().next();
        Iterator<Marker> iter = markers.values().iterator();

        for(int i = 0; i < random; i++) {
            ret = iter.next();
        }
        if(ret.toString() == ""){
            return getRandomMarker(); //TODO make this bretier (so not recursive)
        }
        return ret;
    }
}
