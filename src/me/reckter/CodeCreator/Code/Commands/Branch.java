package me.reckter.CodeCreator.Code.Commands;

import me.reckter.CodeCreator.Code.Commands.Command;
import me.reckter.CodeCreator.Code.Marker.Marker;
import me.reckter.CodeCreator.Code.Marker.MarkerHandler;
import me.reckter.CodeCreator.Code.Register.Register;
import me.reckter.CodeCreator.Code.Register.RegisterHandler;
import me.reckter.CodeCreator.Code.StringSnipplet;

/**
 * Created by reckter on 2/6/14.
 */
public class Branch extends Command {


    public Branch(String op) {
        this(op, RegisterHandler.getRandomRegister(), RegisterHandler.getRandomRegister(), MarkerHandler.getRandomMarker());
    }



    public Branch(String op, Register reg1, Register reg2, Marker marker) {
        super();
        syntax = "<op> <reg1>, <reg2>, <marker>";
        elements.put("op", new StringSnipplet(op));
        elements.put("reg1", reg1);
        elements.put("reg2", reg2);
        elements.put("marker", marker);
    }
}
