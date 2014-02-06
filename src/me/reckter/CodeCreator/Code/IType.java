package me.reckter.CodeCreator.Code;

import me.reckter.CodeCreator.Code.Register.Register;
import me.reckter.CodeCreator.Code.Register.RegisterHandler;

/**
 * Created by reckter on 2/6/14.
 */
public class IType extends Command {

    public IType(String op) {
        this(op, RegisterHandler.getRandomRegister(), RegisterHandler.getRandomRegister(), new Immidiate());
    }

    public IType(String op, Register reg1, Register reg2, Immidiate immidiate) {
        super();
        syntax = "<op> <reg1>, <reg2>, <immidiate>";
        elements.put("op", new StringSnipplet(op));
        elements.put("reg1", reg1);
        elements.put("reg2", reg2);
        elements.put("immidiate", immidiate);
    }

}
