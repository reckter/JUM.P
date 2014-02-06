package me.reckter.CodeCreator.Code.CodeSnipplets;

import me.reckter.CodeCreator.Code.BaseCodeSnipplet;
import me.reckter.CodeCreator.Code.Commands.Branch;
import me.reckter.CodeCreator.Code.Commands.Command;
import me.reckter.CodeCreator.Code.Commands.Jump;
import me.reckter.CodeCreator.Code.Marker.Marker;
import me.reckter.CodeCreator.Code.Marker.MarkerHandler;
import me.reckter.CodeCreator.Code.Register.RegisterHandler;
import me.reckter.CodeCreator.CodeGenerator;

import java.util.LinkedList;

/**
 * Created by reckter on 2/6/14.
 */
public class Loop extends BaseCode {

    int numOfLoop;
    public Loop(LinkedList<BaseCodeSnipplet> code) {
        super(code);
    }

    public Loop(int numOfLoop) {
        super();
        this.numOfLoop = numOfLoop;
    }

    @Override
    public void generateCode() {
        Marker loopMarker = new Marker("Loop" + numOfLoop);
        Marker endMarker = new Marker("Exit" + numOfLoop);
        Command tmp = new Branch("beq", RegisterHandler.getRandomRegister(), RegisterHandler.getRandomRegister(), endMarker);
        tmp.setMarker(loopMarker);
        code.add(tmp);
        int ranodm = (int) (Math.random() * 10 + 1);
        for(int i = 0; i <= ranodm; i++){
            if(Math.random() <= 0.05f) {
                BaseCode tmp2 = new Loop((int) (Math.random() * 100));
                tmp2.generateCode();
                code.add(tmp2);
                continue;
            }
            tmp = CodeGenerator.getRandomLine();
            tmp.setMarker(MarkerHandler.getMarker(""));
            code.add(tmp);
        }

        tmp = new Jump("j", loopMarker);
        code.add(tmp);

        tmp = CodeGenerator.getRandomLine();
        tmp.setMarker(endMarker);
        code.add(tmp);

    }
}
