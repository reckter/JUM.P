package me.reckter.CodeCreator.Code;

/**
 * Created by reckter on 2/6/14.
 */
public class StringSnipplet extends BaseCodeSnipplet {

    protected String string;

    public StringSnipplet(String string) {
        super();
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}
