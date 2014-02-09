package me.reckter.CodeCreator;

import me.reckter.CodeCreator.Code.*;
import me.reckter.CodeCreator.Code.CodeSnipplets.BaseCode;
import me.reckter.CodeCreator.Code.CodeSnipplets.Loop;
import me.reckter.CodeCreator.Code.Commands.*;
import me.reckter.CodeCreator.Code.Marker.MarkerHandler;
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
        MarkerHandler.addMarker("JUM.P");
        MarkerHandler.addMarker("reckter");

    }

    public void generateCode(){
        code.clear();
        int length = 30;

        for(int i = 0; i < length; i++) {
            code.add(getRandomLine());
        }
    }


    public static Command getRandomJumpInstruction() {
        Command ret = new Command();
        int random = (int) (Math.random() * 3f);
        switch (random) {
            case 0: ret = new Jump("jr", RegisterHandler.getRandomRegister());
                break;
            case 1: ret = new Jump("j", MarkerHandler.getRandomMarker());
                break;
            case 2: ret = new Jump("jal", MarkerHandler.getRandomMarker());
                break;
        }
        return ret;
    }

    public static Command getRandomBranchInstruction() {
        Command ret = new Command();
        int random = (int) (Math.random() * 2f);
        switch (random) {
            case 0: ret = new Branch("beq");
                break;
            case 1: ret = new Branch("bne");
                break;
        }
        return ret;
    }

    public static Command getRandomBasicInstruction() {
        Command ret = new Command();
        int random = (int) (Math.random() * 10f);
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
            case 8: ret = new Store("lw");
                break;
            case 9: ret = new Store("sw");
                break;
        }
        return ret;
    }

    public static Command getRandomLine(){ //TODO diffrientiate between simply type commands and jumps / branches etc
        Command ret = new Command();
        double random = Math.random();
        int randIns = 0;
        if(random <= 0.15f) {
            randIns = 0;
        } else if(random <= 0.4f) {
            randIns = 1;
        } else if(random <= 1f) {
            randIns = 2;
        }
        switch (randIns) {
            case 0: ret = getRandomJumpInstruction();
                break;
            case 1: ret = getRandomBranchInstruction();
                break;
            case 2: ret = getRandomBasicInstruction();
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
            out.append(snipplet.toString());
        }
        return out.toString();
    }
}
