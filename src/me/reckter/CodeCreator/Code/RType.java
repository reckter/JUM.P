package me.reckter.CodeCreator.Code;

import me.reckter.CodeCreator.Code.Register.Register;
import me.reckter.CodeCreator.Code.Register.RegisterHandler;

/**
 * Created by reckter on 2/6/14.
 */
public class RType extends Command {

    public RType(String op) {
        this(op, RegisterHandler.getRandomRegister(), RegisterHandler.getRandomRegister(), RegisterHandler.getRandomRegister());
    }

    public RType(String op, Register reg1, Register reg2, Register reg3) {
        super();
        syntax = "<op> <reg1>, <reg2>, <reg3>";
        elements.put("op", new StringSnipplet(op));
        elements.put("reg1", reg1);
        elements.put("reg2", reg2);
        elements.put("reg3", reg3);
    }
}
