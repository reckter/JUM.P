package me.reckter.CodeCreator.Code.Register;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by reckter on 2/6/14.
 */
public class RegisterHandler {

    static HashMap<String, me.reckter.CodeCreator.Code.Register.Register> register;

    public RegisterHandler() {
        register = new HashMap<String, me.reckter.CodeCreator.Code.Register.Register>();
    }


    static public void setupRegisters() {
        register.clear();

        register.put("0", new me.reckter.CodeCreator.Code.Register.Register("$0"));
        register.put("at", new me.reckter.CodeCreator.Code.Register.Register("$at"));

        register.put("v0", new me.reckter.CodeCreator.Code.Register.Register("$v0"));
        register.put("v1", new me.reckter.CodeCreator.Code.Register.Register("$v1"));

        register.put("a0", new me.reckter.CodeCreator.Code.Register.Register("$a0"));
        register.put("a1", new me.reckter.CodeCreator.Code.Register.Register("$a1"));
        register.put("a2", new me.reckter.CodeCreator.Code.Register.Register("$a2"));
        register.put("a3", new me.reckter.CodeCreator.Code.Register.Register("$a3"));

        register.put("s0", new me.reckter.CodeCreator.Code.Register.Register("$s0"));
        register.put("s1", new me.reckter.CodeCreator.Code.Register.Register("$s1"));
        register.put("s2", new me.reckter.CodeCreator.Code.Register.Register("$s2"));
        register.put("s3", new me.reckter.CodeCreator.Code.Register.Register("$s3"));
        register.put("s4", new me.reckter.CodeCreator.Code.Register.Register("$s4"));
        register.put("s5", new me.reckter.CodeCreator.Code.Register.Register("$s5"));
        register.put("s6", new me.reckter.CodeCreator.Code.Register.Register("$s6"));
        register.put("s7", new me.reckter.CodeCreator.Code.Register.Register("$s7"));

        register.put("t0", new me.reckter.CodeCreator.Code.Register.Register("$t0"));
        register.put("t1", new me.reckter.CodeCreator.Code.Register.Register("$t1"));
        register.put("t2", new me.reckter.CodeCreator.Code.Register.Register("$t2"));
        register.put("t3", new me.reckter.CodeCreator.Code.Register.Register("$t3"));
        register.put("t4", new me.reckter.CodeCreator.Code.Register.Register("$t4"));
        register.put("t5", new me.reckter.CodeCreator.Code.Register.Register("$t5"));
        register.put("t6", new me.reckter.CodeCreator.Code.Register.Register("$t6"));
        register.put("t7", new me.reckter.CodeCreator.Code.Register.Register("$t7"));
        register.put("t8", new me.reckter.CodeCreator.Code.Register.Register("$t8"));
        register.put("t9", new me.reckter.CodeCreator.Code.Register.Register("$t9"));

        register.put("k0", new me.reckter.CodeCreator.Code.Register.Register("$k0"));
        register.put("k1", new me.reckter.CodeCreator.Code.Register.Register("$k1"));

        register.put("gp", new me.reckter.CodeCreator.Code.Register.Register("$gp"));
        register.put("sp", new me.reckter.CodeCreator.Code.Register.Register("$sp"));
        register.put("fp", new me.reckter.CodeCreator.Code.Register.Register("$fp"));
        register.put("ra", new me.reckter.CodeCreator.Code.Register.Register("$ra"));

    }


    public me.reckter.CodeCreator.Code.Register.Register getRegister(String in) {
        return register.get(in);
    }

    public static me.reckter.CodeCreator.Code.Register.Register getRandomRegister(){
        if(register.values().size() == 0) {
            setupRegisters();
        }

        int size = register.values().size();
        int random = (int) (Math.random() * size);
        me.reckter.CodeCreator.Code.Register.Register ret = register.values().iterator().next();
        Iterator<me.reckter.CodeCreator.Code.Register.Register> iter = register.values().iterator();
        for(int i = 0; i < random; i++) {
            ret = iter.next();
        }
        return ret;
    }
}
