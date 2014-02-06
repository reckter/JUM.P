package me.reckter.CodeCreator.Code;

import java.util.HashMap;

/**
 * Created by reckter on 2/6/14.
 */
public class BaseCodeSnipplet {

    protected HashMap<String, BaseCodeSnipplet> elements;


    public BaseCodeSnipplet() {
        elements = new HashMap<String, BaseCodeSnipplet>();
    }

    protected String opperation;
    protected String syntax;



    public String toString(){
        String[] elems = syntax.split(">");
        StringBuilder out = new StringBuilder();
        for(String elem: elems) {
            if(elem.contains("<")) {
                String temp[] = elem.split("<");

                out.append(temp[0]).append(elements.get(temp[1]));
            }
            else {
                out.append(elem);
            }
        }

        return out.toString();
    }

    public HashMap<String, BaseCodeSnipplet> getElements() {
        return elements;
    }

    public void setElements(HashMap<String, BaseCodeSnipplet> elements) {
        this.elements = elements;
    }
}
