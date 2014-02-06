package me.reckter.CodeCreator;

import me.reckter.CodeCreator.Code.*;
import me.reckter.CodeCreator.Code.Marker.MarkerHandler;
import me.reckter.CodeCreator.Code.Register.Register;
import me.reckter.CodeCreator.Code.Register.RegisterHandler;

import java.util.LinkedList;

/**
 * Created by reckter on 2/6/14.
 */
public class CodeGenerator {

    LinkedList<BaseCodeSnipplet> code;
    RegisterHandler regHandler;
    MarkerHandler markerHandler;

    public CodeGenerator() {
        code = new LinkedList<BaseCodeSnipplet>();
        regHandler = new RegisterHandler();
        markerHandler = new MarkerHandler();
        MarkerHandler.addMarker("loop");
        MarkerHandler.addMarker("mem");
        MarkerHandler.addMarker("range");

    }

    public void generateCode(){
        code.clear();
        int length = 30;

        for(int i = 0; i < length; i++) {
            code.add(getRandomLine());
        }
    }

    public BaseCodeSnipplet getRandomLine(){
        Command ret = new Command();
        int random = (int) (Math.random() * 12f);
        switch (random) {
            case 0: ret = new IType("ori");
                break;
            case 1: ret = new RType("add");
                break;
            case 2: ret = new RType("and");
                break;
            case 3: ret = new RType("or");
                break;
            case 4: ret = new RType("xor");
                break;
            case 5: ret = new RType("sub");
                break;
            case 6: ret = new RType("add");
                break;
            case 7: ret = new RType("nor");
                break;
            case 8: ret = new Jump("jr", RegisterHandler.getRandomRegister());
                break;
            case 9: ret = new Jump("j", MarkerHandler.getRandomMarker());
                break;
            case 10: ret = new IType("addi");
                break;
            case 11: ret = new Store("lw");
                break;
            case 12: ret = new Store("sw");
                break;
        }
        if(Math.random() <= 0.1f) {
            ret.setMarker(MarkerHandler.getRandomMarker());
        }
        return ret;
    }

    public String getCode(){
        StringBuilder out = new StringBuilder();

        for(BaseCodeSnipplet snipplet: code) {
            out.append(snipplet.toString()).append("\n");
        }
        return out.toString();
    }
}