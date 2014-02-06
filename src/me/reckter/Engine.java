package me.reckter;

import me.reckter.Level.BaseJUMPLevel;
import me.reckter.Level.BaseLevel;
import org.newdawn.slick.*;

/**
 * Created by mediacenter on 25.12.13.
 */
public class Engine extends BasicGame {

    protected BaseLevel level;

    public final static int SCREEN_HEIGHT = 720;
    public final static int SCREEN_WIDTH = 1080;


    protected Input input;

    public Engine(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        level = new BaseJUMPLevel();
        level.populate();
        level.init();
    }

    @Override
    public void update(GameContainer gameContainer, int delta) throws SlickException {

        level.setFps( gameContainer.getFPS());
        level.setInput(gameContainer.getInput());
        input = gameContainer.getInput();

        if(level.getNextLevel() != null){
            level = level.getNextLevel();
            level.populate();
            level.init();
        }
        if(input.isKeyPressed(Input.KEY_R)){
            this.init(gameContainer);
        }

        if(!input.isKeyDown(Input.KEY_Q)){
            level.processInput(input);
            level.logic(delta);
        } else {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        level.render(graphics);
    }

    public BaseLevel getLevel() {
        return level;
    }

    public void setLevel(BaseLevel level) {
        this.level = level;
    }

    public Input getInput() {
        return input;
    }

    public void setInput(Input input) {
        this.input = input;
    }

}
