package me.reckter.CodeCreator.Code;

/**
 * Created by reckter on 2/6/14.
 */
public class Immidiate extends StringSnipplet {

    public Immidiate(){
        super("");
        int random = (int) (Math.random() * Integer.MAX_VALUE);
        String str = Integer.toHexString(random);
        string = "0x" + str.substring(0, 4);
    }

    public Immidiate(String string) {
        super(string);
    }
}
