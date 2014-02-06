package me.reckter.Particles;

import me.reckter.Level.BaseLevel;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by mediacenter on 31.12.13.
 */
public class BaseParticle {

    protected BaseLevel level;

    protected int timeToLive;

    protected boolean diesAfterTime;

    protected float x;
    protected float y;

    protected float size;

    protected Vector2f movement;

    protected boolean canBeRemovedForSpeed;

    public BaseParticle(BaseLevel level, float x, float y, Vector2f movement) {
        this.level = level;
        this.x = x;
        this.y = y;
        this.movement = movement;
    }

    public void init(){
        diesAfterTime = true;
        timeToLive = 500;
        size = 1;
        canBeRemovedForSpeed = true;
    }

    public boolean isAlive(){
        return !diesAfterTime || timeToLive > 0;
    }

    public void logic(int delta){
        if(diesAfterTime){
            timeToLive -= delta;
            if(Math.random() <= (float) delta / 400 && canBeRemovedForSpeed()){
                timeToLive = 0;
            }
        }
        Vector2f tmp = movement.copy();
        tmp.scale((float)delta / 1000);

        x += tmp.getX();
        y += tmp.getY();
        level.checkBoundaries(this);

    }

    public void render(Graphics g){
        g.setColor(Color.white);

        g.draw(new Line(x, y, x + 1, y)); //TODO actually draw a point!
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public Vector2f getMovement() {
        return movement;
    }

    public void setMovement(Vector2f movement) {
        this.movement = movement;
    }

    public int getTimeToLive() {
        return timeToLive;
    }

    public void setTimeToLive(int timeToLive) {
        this.timeToLive = timeToLive;
    }

    public boolean isDiesAfterTime() {
        return diesAfterTime;
    }

    public void setDiesAfterTime(boolean diesAfterTime) {
        this.diesAfterTime = diesAfterTime;
    }

    public boolean canBeRemovedForSpeed() {
        return canBeRemovedForSpeed;
    }

    public void setCanBeRemovedForSpeed(boolean canBeRemovedForSpeed) {
        this.canBeRemovedForSpeed = canBeRemovedForSpeed;
    }
}
