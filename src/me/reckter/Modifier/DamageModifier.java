package me.reckter.Modifier;

/**
 * Created with IntelliJ IDEA.
 * User: mediacenter
 * Date: 03.10.13
 * Time: 14:25
 * To change this template use File | Settings | File Templates.
 */
public class DamageModifier extends BaseModifier{
    public DamageModifier(int timeToLive, float flatModifier, float percentageModifier) {
        super(timeToLive, flatModifier, percentageModifier);
    }

    @Override
    public double damage(double damage) {
        return addModifier(damage);
    }
}
