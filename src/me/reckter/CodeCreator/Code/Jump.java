package me.reckter.CodeCreator.Code;

import me.reckter.CodeCreator.Code.Marker.Marker;
import me.reckter.CodeCreator.Code.Register.Register;

/**
 * Created by reckter on 2/6/14.
 */
public class Jump extends Command {


    public Jump(String op, Register reg) {
        super();
        syntax = "<op> <reg>";
        elements.put("op", new StringSnipplet(op));
        elements.put("reg", reg);
    }

    public Jump(String op, Marker marker) {
        super();
        syntax = "<op> <whereTo>";
        elements.put("op", new StringSnipplet(op));
        elements.put("whereTo", marker);
    }
}
