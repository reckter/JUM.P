package me.reckter.Level;

import me.reckter.CodeCreator.Code.CodeSnipplets.BaseCode;
import me.reckter.CodeCreator.Code.CodeSnipplets.Loop;
import me.reckter.CodeCreator.CodeGenerator;
import me.reckter.Engine;
import me.reckter.Entities.BaseEntity;
import me.reckter.Entities.CommandEntity;
import me.reckter.Interface.FPSlabel;
import me.reckter.Particles.BaseParticle;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.Log;

/**
 * Created by reckter on 2/6/14.
 */
public class BaseJUMPLevel extends BaseLevel {

    protected int clk;
    protected boolean clkTriggered;
    protected int MAX_CLK;

    public static final int STEP_HEIGHT = 40;

    protected CodeGenerator codegenerator;

    protected float minCOmmandY;

    public BaseJUMPLevel() {
        codegenerator = new CodeGenerator();
    }

    @Override
    public void init() {
        super.init();
        gravity = 1000;
        interfaces.add(new FPSlabel(this));
        WIDTH = 1000;
        MAX_CLK = 3 * 1000;
    }

    @Override
    public void populate() {
        super.populate();
        minCOmmandY = player.getX() + STEP_HEIGHT * 10 + 6;
    }

    @Override
    public void checkBoundaries(BaseEntity entity) {
    }

    @Override
    public void checkBoundaries(BaseParticle particle) {
        super.checkBoundaries(particle);
    }

    @Override
    public void logic(int delta) {
        clk += delta;
        clkTriggered = false;
        if(clk >= MAX_CLK){
            MAX_CLK *= 0.9f;
            if(MAX_CLK < 150) {
                MAX_CLK = 150;
            }


            clk = 0;
            clkTriggered = true;
        }

        while(player.getY() - minCOmmandY < HEIGHT * 3){
            CommandEntity tmp = new CommandEntity(this);
            tmp.init();
            tmp.setCommand(CodeGenerator.getRandomLine());
            tmp.setX(player.getX() + 10);
            tmp.setY(minCOmmandY - STEP_HEIGHT);
            minCOmmandY = tmp.getY();
            add(tmp);
        }

        super.logic(delta);
    }

    @Override
    public void render(Graphics g) {

        if(player != null){
            camX = (int) - (player.getX() - Engine.SCREEN_WIDTH * 0.2f);
            camY = (int) - (player.getY() - Engine.SCREEN_HEIGHT * 0.6f);
        }
        g.translate(camX, camY);

        particles.render(g);

        entities.render(g);

        interfaces.render(g);
    }


    public boolean clkTriggered() {
        return clkTriggered;
    }

    public int getClk() {
        return clk;
    }

    public int getMAX_CLK() {
        return MAX_CLK;
    }
}
