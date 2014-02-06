package me.reckter.CodeCreator.Code.Marker;

import me.reckter.CodeCreator.Code.StringSnipplet;

/**
 * Created by reckter on 2/6/14.
 */
public class Marker extends StringSnipplet{

    public Marker(String string) {
        super(string);
        if(string == "") {
            return;
        }


        if(this.string.length() > 9) {
            this.string = string.substring(0,9);

        } else if(this.string.length() < 8) {
            while (this.string.length() < 8) {
                this.string += " ";
            }
        }

    }
}
