package me.reckter.Particles;

import me.reckter.Entities.BaseEntity;
import me.reckter.Level.BaseLevel;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by me.reckter on 1/1/14.
 */
public class DeathParticle extends BaseParticle {
    public DeathParticle(BaseLevel level, BaseEntity origin) {
        super(level, origin.getX(), origin.getY(), origin.getMovement());
        double random = Math.random();
        this.movement = origin.getMovement().copy().add(new Vector2f((float) Math.random() * 360).scale((float) (random * random * origin.getSize() * 20)));

    }


    @Override
    public void init() {
        super.init();
        timeToLive = (int) (Math.random() * 45);
        timeToLive *= timeToLive;
    }
}
