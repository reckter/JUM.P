package me.reckter.Modifier;

/**
 * Created with IntelliJ IDEA.
 * User: mediacenter
 * Date: 03.10.13
 * Time: 14:26
 * To change this template use File | Settings | File Templates.
 */
public class SpeedModifier extends BaseModifier {
    public SpeedModifier(int timeToLive, float flatModifier, float percentageModifier) {
        super(timeToLive, flatModifier, percentageModifier);
    }

    @Override
    public double speed(double speed) {
        return addModifier(speed);
    }
}
