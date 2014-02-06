package me.reckter.Level;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

/**
 * Created by mediacenter on 26.12.13.
 */
public class SplashScreen extends BaseLevel {

    protected BaseLevel levelToLoad; //the level that will be loaded after the splash screen
    protected String stringToDisplay; // the string that will be displayed

    protected int time; //the time that the splash screen is alive
    protected int MAX_TIME = 5 * 1000;

    public SplashScreen(BaseLevel levelToLoad, String stringToDisplay) {
        super();
        this.stringToDisplay = stringToDisplay;
        this.levelToLoad = levelToLoad;
    }

    @Override
    public void processInput(Input input) {
    }

    @Override
    public void populate() {
    }

    @Override
    public void init() {
    }

    /**
     * gets called every logic tick
     * @param delta the time in ms since the last logic tick
     */
    @Override
    public void logic(int delta) {
        time += delta;
        if(time >= MAX_TIME){
            levelToLoad.populate();
            levelToLoad.init();
            setLevelToLoad(levelToLoad);
        }
    }

    /**
     * gets called every ender
     * @param g the graphics object on which should be drawn
     */
    @Override
    public void render(Graphics g) {
        g.setBackground(Color.white);
        g.setColor(Color.black);
        g.drawString(stringToDisplay,WIDTH / 2 - (stringToDisplay.length() * 5), HEIGHT / 2 - 10);
    }


    public BaseLevel getLevelToLoad() {
        return levelToLoad;
    }

    public void setLevelToLoad(BaseLevel levelToLoad) {
        this.levelToLoad = levelToLoad;
    }


}
