package me.reckter.Particles;

import me.reckter.Entities.BaseEntity;
import me.reckter.Level.BaseLevel;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import java.util.Random;

/**
 * Created by me.reckter on 1/1/14.
 */
public class DeathParticle extends BaseParticle {

    public DeathParticle(BaseLevel level,Rectangle box) {
        super(level, box.getX(), box.getY(), new Vector2f(0,0));
        Random rand = new Random();
        this.x += rand.nextInt((int) box.getWidth());
        this.y += rand.nextInt((int) box.getHeight()) ;
        double random = Math.random();
        this.movement = new Vector2f((float) Math.random() * 360).scale((float) (random * random * ((box.getWidth() + box.getHeight()) / 2) * 20));
    }

    public DeathParticle(BaseLevel level, BaseEntity origin) {
        super(level, origin.getX(), origin.getY(), origin.getMovement());
        Random rand = new Random();
        this.x += rand.nextInt((int) origin.getWidth());
        this.y += rand.nextInt((int) origin.getHeight()) - origin.getHeight();
        double random = Math.random();
        this.movement = new Vector2f((float) Math.random() * 360).scale((float) (random * random * origin.getSize() * 20));

    }


    @Override
    public void init() {
        super.init();
        timeToLive = (int) (Math.random() * 45);
        timeToLive *= timeToLive;
    }
}
