package me.reckter.Particles;

import me.reckter.Level.BaseLevel;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by me.reckter on 1/1/14.
 */
public class BackgroundParticle extends BaseParticle { //TODO usa an Image for that, this is slow as fuck!
    public BackgroundParticle(BaseLevel level, float x, float y) {
        super(level, x, y, new Vector2f(0,0));
    }

    @Override
    public void init() {
        super.init();
        canBeRemovedForSpeed = false;
        timeToLive = (int) (5 * 1000 * Math.random() + 2 * 1000);
    }

    @Override
    public void logic(int delta) {
        super.logic(delta);
        if(timeToLive <= 0){
            Vector2f newPos = new Vector2f(x,y).add(new Vector2f(Math.random() * 360).normalise().scale((float) Math.random() * 50));
            level.add(new BackgroundParticle(level, newPos.x, newPos.y));
        }
    }
}
