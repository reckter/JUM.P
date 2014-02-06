package me.reckter.Modifier;

/**
 * Created with IntelliJ IDEA.
 * User: mediacenter
 * Date: 03.10.13
 * Time: 14:24
 * To change this template use File | Settings | File Templates.
 */
public class HealthModifier extends BaseModifier {
    public HealthModifier(int timeToLive, float flatModifier, float percentageModifier) {
        super(timeToLive, flatModifier, percentageModifier);
    }

    @Override
    public double health(double health) {
        return addModifier(health);
    }
}
