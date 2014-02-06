package me.reckter.Modifier;

/**
 * Created with IntelliJ IDEA.
 * User: mediacenter
 * Date: 03.10.13
 * Time: 13:27
 * To change this template use File | Settings | File Templates.
 */
public class BaseModifier {
    protected int timeToLive;
    protected boolean diesAfterTime;

    protected float flatModifier; // just gets added;
    protected float percentageModifier; //gets multiplied so +10% is 1.1

    public BaseModifier(int timeToLive, float flatModifier, float percentageModifier){
        this.timeToLive = timeToLive;
        this.flatModifier = flatModifier;
        this.percentageModifier = percentageModifier;
        this.diesAfterTime = true;
    }


    /**
     * calculates the change of the input value
     * @param value to modify
     * @return modified value
     */
    protected double addModifier(double value){
        return (value + flatModifier) * percentageModifier;
    }

    /**
     * health modifiers
     * @param health  to modifiy
     * @return modified health
     */
    public double health(double health){
        return health;
    }

    /**
     * damage modifiers
     * @param damage to modify
     * @return modiefied damage
     */
    public double damage(double damage){
        return damage;
    }

    /**
     * speed modifiers
     * @param speed  to modify
     * @return modified speed
     */
    public double speed(double speed){
        return speed;
    }

    /**
     * cast speed modifiers
     * @param castTime to modify
     * @return modified castTime
     */
    public int castTime(int castTime){
        return castTime;
    }

    /**
     * returns false if the entity is stunned or silenced
     * @return if the entity can cast right now
     */
    public boolean canCast(){
        return true;
    }

    public boolean canRotate(){
        return true;
    }


    /**
     * checks if the modifiers is still active
     * @return
     */
    public boolean isAlive(){
        if(diesAfterTime){
            return timeToLive > 0;
        }
        return true;
    }

    public void logic(int delta){
        if(diesAfterTime){
            timeToLive -= delta;
        }
    }
}
