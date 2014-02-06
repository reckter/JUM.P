package me.reckter.Interface.HUD;

import me.reckter.Engine;
import me.reckter.Level.BaseLevel;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 * Created by mediacenter on 28.12.13.
 */
public class Score extends BaseHudItem {

    protected int score = 0;


    public Score(BaseLevel engine) {
        super(engine);

        fixedY = 15;
    }

    @Override
    public void logic(int delta) {

    }

    @Override
    public void render(Graphics g) {

        String str = "Score: " + score;
        fixedX = Engine.SCREEN_WIDTH - 50 - str.length() * 5;
        super.render(g);

    g.setColor(Color.white);
        g.drawString(str,x , y);
    }

    public void addScore(int add){
        score += add;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
