package me.reckter.Entities;

import me.reckter.Level.BaseJUMPLevel;
import me.reckter.Level.BaseLevel;
import me.reckter.Log;
import me.reckter.Particles.RocketParticle;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;


/**
 * Created by mediacenter on 25.12.13.
 */
public class Player extends BaseEntity {

    protected final float MOMENTUM_GAIN = 600;


    // input variables
    protected int angularGain;
    protected int speedGain;

    protected float oldPositionX;
    protected float oldPositionY;

    protected boolean isJumping;
    protected boolean isMoving;

    public Player(BaseLevel level) {
        super(level);
    }

    @Override
    public Rectangle getAAHitBox() {
        return new Rectangle(x, y, 350, 35);
    }

    @Override
    public Shape getHitBox() {
        return getAAHitBox();
    }

    @Override
    public void init() {
        super.init();
        type = 1;

        angle = 0;
        angularGain = 0;
        speedGain = 600;
        MAX_SPEED = 250;
        MAX_ANGULAR_MOMENTUM = 180;
        isJumping = false;
        movement = new Vector2f(0,0);

    }


    /**
     * gets called bevor every logic tick so the input can be handled
     * @param input
     */
    public void processInput(Input input){

        //angle
        Vector2f mouse = new Vector2f(level.getRealX(input.getMouseX() - (int) x), level.getRealY(input.getMouseY()) - (int) y);

        if(modifiers.canRotate()){
            angle = (float) mouse.getTheta();

        }

        if(input.isKeyDown(Input.KEY_P)){
             isJumping = true;
        } else {
            isJumping = false;
        }

    }

    @Override
    public void onDeath(BaseEntity murderer) {
        Log.info("The Player just died!");
        super.onDeath(murderer);
    }

    @Override
    public void onCollision(BaseEntity with) {
        if(with instanceof CommandEntity) {
            if(((CommandEntity) with).canBeJumped() && isJumping) {
                //TODO jumping logic here
            }
        }
    }

    @Override
    public void logic(int delta) {
        if(level instanceof BaseJUMPLevel) {
            if(((BaseJUMPLevel) level).clkTriggered()) {
                oldPositionX = x;
                oldPositionY = y;
                isMoving = true;
            }
        }

        if(isMoving) {
            if(oldPositionY <= y + BaseJUMPLevel.STEP_HEIGHT / 2) {
                movement.add(new Vector2f(0, -MOMENTUM_GAIN).scale((float) delta / 1000));
            } else if(oldPositionY > y + BaseJUMPLevel.STEP_HEIGHT / 2){
                movement.add(new Vector2f(0, MOMENTUM_GAIN).scale(0.9f).scale((float) delta / 1000));
            }
            if(oldPositionY > y + BaseJUMPLevel.STEP_HEIGHT) {
                movement.set(0,0);
                y = oldPositionY - BaseJUMPLevel.STEP_HEIGHT ;
                isMoving = false;
            }
            if(movement.length() > MAX_SPEED) {
                movement.normalise().scale(MAX_SPEED);
            }

        }
        super.logic(delta);
    }

    @Override
    public void render(Graphics g) {

        g.setColor(Color.white);
        //g.fill(new Circle(x, y, size));

        g.draw(getHitBox());

        //super.render(g);
    }
}
