package me.reckter.CodeCreator.Code;

import me.reckter.CodeCreator.Code.Register.Register;
import me.reckter.CodeCreator.Code.Register.RegisterHandler;

/**
 * Created by reckter on 2/6/14.
 */
public class Branch extends Command {


    public Branch(String op) {
        this(op, RegisterHandler.getRandomRegister(), RegisterHandler.getRandomRegister(), "TODO");
    }



    public Branch(String op, Register reg1, Register reg2, String marker) {
        super();
        syntax = "<op> <reg1>, <reg2>, <marker>";
        elements.put("op", new StringSnipplet(op));
        elements.put("reg1", reg1);
        elements.put("reg2", reg2);
        elements.put("marker", new StringSnipplet(marker));
    }
}
