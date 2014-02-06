package me.reckter.Interface;

import me.reckter.Entities.BaseEntity;
import me.reckter.Level.BaseLevel;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created by mediacenter on 30.12.13.
 */
public class LifeBar extends BaseInterface {


    double health;
    double maxHealth;

    protected BaseEntity on;
    public LifeBar(BaseLevel level, BaseEntity on) {
        super(level);
        this.on = on;

        height = 2;
        width = (int) (on.getSize() * 1.8f);
    }


    @Override
    public boolean isAlive() {
        return !on.isDead();
    }


    @Override
    public void logic(int delta) {

        maxHealth = on.getMaxLife();
        health = on.getLife();
        width = (int) (on.getSize() * 1.8f);

        x = (int) (on.getX() - width / 2);
        y = (int) (on.getY() - (on.getSize() + 3));

    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        g.setColor(Color.white);
        g.fill(new Rectangle(x , y , width , height));

        g.setColor(Color.red);
        // g.draw(new SHx, y, (float) (width * (healthDisplayed / maxHealth)), height);
        g.fill(new Rectangle(x, y, (float) ((health / maxHealth) * (double) width), height));
        g.setColor(Color.white);
    }
}
