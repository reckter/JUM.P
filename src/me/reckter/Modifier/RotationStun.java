package me.reckter.Modifier;

/**
 * Created by mediacenter on 28.12.13.
 */
public class RotationStun extends BaseModifier{
    public RotationStun(int timeToLive, float flatModifier, float percentageModifier) {
        super(timeToLive, flatModifier, percentageModifier);
    }

    public RotationStun(int timeToLive) {
        super(timeToLive, 0, 0);
    }

    public RotationStun(){
        super(0, 0, 0);
        diesAfterTime = false;
    }



    @Override
    public boolean canRotate() {
        return false;
    }
}
