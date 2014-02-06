package me.reckter.Entities;

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



    protected final float MOMENTUM_GAIN = 200;
    protected Sound testSound;


    // input variables
    protected int angularGain;
    protected int speedGain;

    public Player(BaseLevel level) {
        super(level);
    }

    @Override
    public Rectangle getAAHitBox() {
        return new Rectangle(x,y,50,150);
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
        speedGain = 0;
        MAX_SPEED = 800;
        MAX_ANGULAR_MOMENTUM = 180;

       /* try {
            testSound = new Sound("res/Sounds/pew.wav");
        } catch (SlickException e) {
            e.printStackTrace();
        }*/

        switch ((int) (Math.random() * 5)){
            case 0:
                weapon = "shoot";
                break;
            case 1:
                weapon = "laser";
                break;
            case 2:
                weapon = "grenade";
                break;
            case 3:
                weapon = "missile";
                break;
            case 4:
                weapon = "bomb";
                break;
            default:
                Log.error("invalid Weapon type!");
        }
;

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

        if(input.isKeyDown(Input.KEY_J)){
            speedGain = 1;
        } else {
            speedGain = 0;
        }

        if(input.isKeyDown(Input.KEY_P)){
            //TODO jumping here ^^
        }

    }

    @Override
    public void onDeath(BaseEntity murderer) {
        Log.info("The Player just died!");
        super.onDeath(murderer);
    }

    @Override
    public void onCollision(BaseEntity with) {

    }

    @Override
    public void logic(int delta) {
        if(speedGain == 1){
            level.add(new RocketParticle(level, this));
        }

        movement.add(new Vector2f(angle).scale(MOMENTUM_GAIN).scale(speedGain).scale((float) delta / 1000));
        super.logic(delta);
    }

    @Override
    public void render(Graphics g) {

        g.setColor(Color.white);
        //g.fill(new Circle(x, y, size));

        g.fill(getHitBox());

        super.render(g);
    }
}
