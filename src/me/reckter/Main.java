package me.reckter;

import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: me.reckter
 * Date: 17.11.13
 * Time: 19:52
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) throws SlickException {



        Log.setConsoleLevel(Log.DEBUG);
        System.setProperty("org.lwjgl.librarypath", new File(new File(System.getProperty("user.dir"), "lib/native"), LWJGLUtil.getPlatformName()).getAbsolutePath());
        Log.debug(System.getProperty("org.lwjgl.librarypath"));

        Engine dsm = new Engine("SPAAAAAACE!");

        AppGameContainer app = new AppGameContainer(dsm);

        app.setUpdateOnlyWhenVisible(false);
        app.setAlwaysRender(true);
        app.setVSync(false);
       // app.setSmoothDeltas(true);

        //app.setMaximumLogicUpdateInterval(10);

        app.setShowFPS(false);
        //app.setTargetFrameRate(60);

        app.setDisplayMode(Engine.SCREEN_WIDTH, Engine.SCREEN_HEIGHT, false);
        app.start();
    }
}
