package me.reckter.CodeCreator.Code;

import me.reckter.CodeCreator.Code.Marker.Marker;
import me.reckter.CodeCreator.Code.Marker.MarkerHandler;

/**
 * Created by reckter on 2/6/14.
 */
public class Command extends BaseCodeSnipplet {
    protected Marker marker;

    public Command() {
        this(MarkerHandler.getMarker(""));
    }

    public Command(Marker marker) {
        this.marker = marker;
    }

    @Override
    public String toString() {
        String ret = "          ";
        if(marker.toString() != ""){
            ret = marker.toString() + ": ";
        }
        return ret + super.toString();
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }
}
