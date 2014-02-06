package me.reckter.Particles;

import me.reckter.Entities.BaseEntity;
import me.reckter.Level.BaseLevel;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by me.reckter on 1/1/14.
 */
public class RocketParticle extends BaseParticle {
    public RocketParticle(BaseLevel level, BaseEntity origin) {
        this(level, origin, 1);
    }

    public RocketParticle(BaseLevel level, BaseEntity origin, float sizeScale) {
        super(level, 0, 0, new Vector2f(origin.getAngle()).normalise().scale(-100).add(origin.getMovement()));
        Vector2f tail = new Vector2f(origin.getX(),origin.getY()).add(new Vector2f(origin.getAngle()).normalise().scale((float) -origin.getSize() * sizeScale));
        this.x = tail.x;
        this.y = tail.y;
    }

    @Override
    public void init() {
        super.init();
        movement.add(new Vector2f(Math.random() * 360).scale((float) Math.random() * 30));
    }
}
