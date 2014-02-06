package me.reckter.CodeCreator;

/**
 * Created by reckter on 2/6/14.
 */
public class CodeTest {

    public static void main(String[] args) {
        CodeGenerator gen = new CodeGenerator();
        gen.generateCode();
        System.out.print(gen.getCode());
    }
}
