package me.reckter.Entities;

import me.reckter.Interface.DamageText;
import me.reckter.Level.BaseLevel;
import me.reckter.Modifier.BaseModifier;
import me.reckter.Modifier.ModifierHandler;
import me.reckter.Particles.DeathParticle;
import me.reckter.Sound.SoundEngine;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.Log;

/**
 * Created by mediacenter on 25.12.13.
 */
public class BaseEntity {
    protected BaseLevel level;
    protected SoundEngine soundEngine;

    public int type = 0;

    protected float x;
    protected float y;

    protected float size; // for easy AAHHB calculation

    protected Vector2f movement; //the movement vectorr
    protected boolean isAccelerating;

    protected boolean isShooting;
    protected boolean isDead = false;
    protected int isDeadFor;
    protected int MAX_isDeadFor;

    protected float life;
    protected float MAX_LIFE;

    protected int imuneToDamage;
    protected int MAX_IMUNE_TO_DAMAGE;

    protected long lastUpdated;

    protected int id;

    protected String weapon;

    protected float angle;

    protected float angularMomentum;
    protected float angularMomentumTemp; // for loosing angularMomentum when the ship is roationStuned

    protected float MAX_ANGULAR_MOMENTUM = 5000 * 1000;

    protected boolean needsCollisionChecking;

    protected float MAX_SPEED = 100;

    protected ModifierHandler modifiers;

    public BaseEntity(BaseLevel level){
        this.level = level;
        soundEngine = level.getSoundEngine();

        id = -1;

        modifiers = new ModifierHandler();
    }


    /**
     * checks if the entity collides with the given one
     * @param with
     * @return
     */
    public boolean checkCollision(BaseEntity with){
        if(with.getAAHitBox().intersects(getAAHitBox())){
            return with.getHitBox().intersects(getHitBox());
        }
        return false;
    }

    /**
     * gets called when the entity onCollision with the given one
     * @param with
     */
    public void onCollision(BaseEntity with){

    }

    /**
     * gets called when the entity dies
     * @param murderer
     */
    public void onDeath(BaseEntity murderer){
        isDead = true;
        for(int i = 0; i < size * 20; i++){ //TODO particle number
            level.add(new DeathParticle(level, this));
        }
    }

    /**
     * gets called when the entity gets dmg
     * @param from
     */
    public void onDamage(BaseEntity from, float dmg){
        if(imuneToDamage <= 0){
            imuneToDamage = MAX_IMUNE_TO_DAMAGE;
            life -= dmg;
            level.add(new DamageText(level,(int) dmg, this));
            if(life <= 0){
                onDeath(from);
            }
        }
    }

    /**
     * adds a modifier
     * @param modifier
     */
    public void add(BaseModifier modifier){
        modifiers.add(modifier);
    }

    /**
     * removes a modifier
     * @param modifier
     */
    public void del(BaseModifier modifier){
        modifiers.del(modifier);
    }

    /**
     * gets the X distance from th entity "from"
     * @param from
     * @return
     */
    public float getXDistance(BaseEntity from){
        return from.getX() - x;
    }

    /**
     * gets the Y distance from th entity "from"
     * @param from
     * @return
     */
    public float getYDistance(BaseEntity from){
        return from.getY() - y;
    }

    /**
     * gets the squared distance from the given entity
     * @param from
     * @return
     */
    public float getDistanceSquared(BaseEntity from){
        return getXDistance(from) * getXDistance(from) + getYDistance(from) * getYDistance(from);
    }


    /**
     * gets the distance from the given entity
     * @param from
     * @return
     */
    public float getDistance(BaseEntity from){
        return (float) Math.sqrt(getDistanceSquared(from));
    }



    /**
     * returns the Axis Aligned Hit Box for faster collision calculation
     * @return
     */
    public Rectangle getAAHitBox(){
        return new Rectangle((int) (x - size), (int)(y - size),(int) (size * 2),(int) (size * 2));
    }

    /**
     * returns the real Hitbox This can be any subtype of shape
     * @return
     */
    public Shape getHitBox(){
        return new Rectangle((int) (x - size), (int)(y - size),(int) (size * 2),(int) (size * 2));
    }

    /**
     * gets called when the unit is initiaized
     */
    public void initForClient(){
        MAX_IMUNE_TO_DAMAGE = 500;

        lastUpdated = System.currentTimeMillis();

        MAX_isDeadFor = 10;

        movement = new Vector2f(0,0);
        x = 0;
        y = 0;

        size = 10;

        needsCollisionChecking = true;
        isDead = false;

        MAX_LIFE = 100;

        life = MAX_LIFE;

        weapon = "shoot";
    }

    /**
     * gets called when the unit is initiaized
     */
    public void init(){
        MAX_IMUNE_TO_DAMAGE = 500;

        MAX_isDeadFor = 10;

        movement = new Vector2f(0,0);
        x = 0;
        y = 0;

        size = 10;

        needsCollisionChecking = true;
        isDead = false;

        MAX_LIFE = 100;

        life = MAX_LIFE;

        weapon = "shoot";
    }

    /**
     * gets called every logic tick
     * @param delta the time since the last logic tick
     */
    public void logic(int delta){


        if(isDead()) {
            isDeadFor++;
        }

        modifiers.logic(delta);



        if(Math.abs(angularMomentum) >= MAX_ANGULAR_MOMENTUM){
            if(angularMomentum >= 0){
                angularMomentum = MAX_ANGULAR_MOMENTUM;
            } else{
                angularMomentum = - MAX_ANGULAR_MOMENTUM;
            }
        }

        if(!modifiers.canRotate()){
            if(angularMomentumTemp == 0){
                angularMomentumTemp = angularMomentum;
            }
            if((angularMomentum <= 0 && angularMomentumTemp >= 0) || (angularMomentum >= 0 && angularMomentumTemp <= 0)){
                angularMomentum = 0;
            } else {
                angularMomentum -= angularMomentumTemp * 2 * ((float) delta / 1000);
            }
        } else {
            angularMomentumTemp = 0;
        }

        angle += angularMomentum * ((float) delta / 1000);
        angle += 360;
        angle = angle % 360;


        movement.add(new Vector2f(0, level.getGravity()).scale((float) delta / 1000f));

        Vector2f tmp = movement.copy();
        tmp.scale((float)delta / 1000);

        x += tmp.getX();
        y += tmp.getY();
        level.checkBoundaries(this);

        if(imuneToDamage > 0){
            imuneToDamage -= delta;
        }

    }


    /**
     * gets called every render
     * @param g the graphics object on which should be drawn
     */
    public void render(Graphics g){
        g.setColor(Color.red);
       g.draw(getHitBox());
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean isDead) {
        this.isDead = isDead;
    }

    public float getMaxLife() {
        return MAX_LIFE;
    }

    public void setMaxLife(float MAX_LIFE) {
        this.MAX_LIFE = MAX_LIFE;
    }

    public float getLife() {
        return life;
    }

    public void setLife(float life) {
        this.life = life;
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

    public double getSize() {
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

    public boolean needsCollisionChecking() {
        return needsCollisionChecking;
    }

    public float getAngularMomentum() {
        return angularMomentum;
    }

    public void setAngularMomentum(float angularMomentum) {
        this.angularMomentum = angularMomentum;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public void setNeedsCollisionChecking(boolean needsCollisionChecking) {
        this.needsCollisionChecking = needsCollisionChecking;
    }

    public BaseLevel getLevel() {
        return level;
    }

    public void setLevel(BaseLevel level) {
        this.level = level;
    }

    public float getMAX_SPEED() {
        return MAX_SPEED;
    }

    public void setMAX_SPEED(float MAX_SPEED) {
        this.MAX_SPEED = MAX_SPEED;
    }

    public boolean isAccelerating() {
        return isAccelerating;
    }

    public void setAccelerating(boolean isAccelerating) {
        this.isAccelerating = isAccelerating;
    }

    public boolean isShooting() {
        return isShooting;
    }

    public void setShooting(boolean isShooting) {
        this.isShooting = isShooting;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public int getType() {
        return type;
    }

    public int getMAX_isDeadFor() {
        return MAX_isDeadFor;
    }

    public void setMAX_isDeadFor(int MAX_isDeadFor) {
        this.MAX_isDeadFor = MAX_isDeadFor;
    }

    public int getIsDeadFor() {
        return isDeadFor;
    }

    public void setIsDeadFor(int isDeadFor) {
        this.isDeadFor = isDeadFor;
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
