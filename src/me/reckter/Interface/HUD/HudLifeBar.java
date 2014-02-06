package me.reckter.Interface.HUD;

import me.reckter.Engine;
import me.reckter.Level.BaseLevel;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created with IntelliJ IDEA.
 * User: mediacenter
 * Date: 03.10.13
 * Time: 00:43
 * To change this template use File | Settings | File Templates.
 */
public class HudLifeBar extends BaseHudItem {

    double health;
    double healthDisplayed;
    double maxHealth;

    public HudLifeBar(BaseLevel level) {
        super(level);
        height = 20;
        width = 300;

        maxHealth = level.getPlayer().getMaxLife();
        health = level.getPlayer().getLife();
        healthDisplayed = 0;

        fixedX = Engine.SCREEN_WIDTH / 2 - width / 2;
        fixedY = Engine.SCREEN_HEIGHT - height;
    }

    @Override
    public boolean isAlive() {
        return true;
    }


    @Override
    public void logic(int delta) {
        maxHealth = level.getPlayer().getMaxLife();
        health = level.getPlayer().getLife();

        if(healthDisplayed >= maxHealth){
            healthDisplayed = maxHealth;
        }
        healthDisplayed += (health - healthDisplayed) / (800 / delta);
    }

    @Override
    public void render(Graphics g) { //TODO immages and filling in and stuff
        super.render(g);

        g.setColor(Color.red);
       // g.draw(new SHx, y, (float) (width * (healthDisplayed / maxHealth)), height);
        g.fill(new Rectangle(x, y, (float) ((healthDisplayed / maxHealth) * (double) width), height));
        g.setColor(Color.white);
        g.drawString("" + (int) health, x + width / 2 - 10, y);
    }
}
