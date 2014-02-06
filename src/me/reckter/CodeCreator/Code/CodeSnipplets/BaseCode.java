package me.reckter.CodeCreator.Code.CodeSnipplets;

import me.reckter.CodeCreator.Code.BaseCodeSnipplet;

import java.util.LinkedList;

/**
 * Created by reckter on 2/6/14.
 */
public class BaseCode extends BaseCodeSnipplet {


    LinkedList<BaseCodeSnipplet> code;

    public BaseCode(LinkedList<BaseCodeSnipplet> code) {
        this.code = code;
    }

    public BaseCode() {
        code = new LinkedList<BaseCodeSnipplet>();
    }


    public void generateCode() {

    }


    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();

        for(BaseCodeSnipplet snipplet: code) {
            out.append(snipplet.toString()).append("\n");
        }
        return out.toString();
    }
}
