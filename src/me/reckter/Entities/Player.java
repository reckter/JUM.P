package me.reckter.Entities;

import me.reckter.Engine;
import me.reckter.Level.BaseJUMPLevel;
import me.reckter.Level.BaseLevel;
import me.reckter.Log;
import me.reckter.Particles.DeathParticle;
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
    protected boolean isFlying;
    protected boolean isMoving;

    protected float flyingDestinationX;
    protected float flyingDestinationY;


    public Player(BaseLevel level) {
        super(level);
    }

    @Override
    public Rectangle getAAHitBox() {
        return new Rectangle(x , y - height / 2, width, height);
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

        width = 310;
        height = 10;
        size = 40;

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
        super.onDeath(murderer);
        Log.info("The Player just died!");

        int random = (int) (Math.random() * 5);
        Rectangle box = new Rectangle(level.getRealX(0), level.getRealY(0), Engine.SCREEN_WIDTH, Engine.SCREEN_HEIGHT);
        for(int i = 0; i < ( (box.getWidth() + box.getHeight()) / 2) * random; i++){ //TODO particle number
            level.add(new DeathParticle(level, box));
        }
    }

    @Override
    public void onCollision(BaseEntity with) {
        if(with instanceof CommandEntity) {
            if(((CommandEntity) with).canBeJumped() && isJumping && !((CommandEntity) with).gotJumped) {
               ((CommandEntity) with).gotJumped = true;
                level.getScore().addScore(20);

                //isFlying = true;

                //oldPositionX = x;
               // oldPositionY = y;

                flyingDestinationX = 0;
                flyingDestinationY = (int) (Math.random() * 20 - 10) * BaseJUMPLevel.STEP_HEIGHT;
            }
            if(!((CommandEntity) with).canBeJumped() && isJumping && !((CommandEntity) with).gotJumped) {
                level.getScore().addScore(-10);
                ((CommandEntity) with).gotJumped = true;
                onDeath(with);
                ((CommandEntity) with).isSelected = true;
                ((CommandEntity) with).selectedFor = Integer.MAX_VALUE;
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

        //DODO bugy as hell
        if(isFlying) {
            isFlying = false;
        }
        /*
            if(Math.abs(oldPositionY - y) <= flyingDestinationY / 2) {
                Vector2f temp = new Vector2f(0,MOMENTUM_GAIN).normalise().scale(flyingDestinationX).normalise().scale(MOMENTUM_GAIN);

                movement.add(temp.scale((float) delta / 1000));

            } else if(Math.abs(oldPositionY - y)  > flyingDestinationY / 2){

                Vector2f temp = new Vector2f(0,MOMENTUM_GAIN).normalise().scale(-flyingDestinationX).normalise().scale(MOMENTUM_GAIN);

                movement.add(temp.scale(0.9f).scale((float) delta / 1000));
            }
            if(Math.abs(oldPositionY - y) >= flyingDestinationY) {
                movement.set(0,0);
                y = oldPositionY - flyingDestinationY ;
                isFlying = false;
            }
            if(movement.length() > MAX_SPEED) {
                movement.normalise().scale(MAX_SPEED);
            }

        }
        */

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

        //g.draw(getHitBox());
        Vector2f normal = new Vector2f(x, y);
        g.draw(new Line(normal, normal.copy().add(new Vector2f(-40, 0))));
        g.draw(new Line(normal.copy().add(new Vector2f(width, 0)), normal.copy().add(new Vector2f(width, 0).add(new Vector2f(40,0)))));

        //super.render(g);
    }
}
