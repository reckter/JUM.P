package me.reckter.Interface.HUD;

import me.reckter.Interface.BaseInterface;
import me.reckter.Level.BaseLevel;
import org.newdawn.slick.Graphics;

/**
 * Created with IntelliJ IDEA.
 * User: me.reckter
 * Date: 10/1/13
 * Time: 11:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class BaseHudItem extends BaseInterface {

	protected int fixedX;
	protected int fixedY;

	public BaseHudItem(BaseLevel level) {
		super(level);
	}

    public void calculateFixedCoordinates(){
        x = fixedX - level.getCamX();
        y = fixedY - level.getCamY();
    }

	@Override
	public void render(Graphics g) {
        calculateFixedCoordinates();
	}

    public int getFixedX() {
        return fixedX;
    }

    public void setFixedX(int fixedX) {
        this.fixedX = fixedX;
    }

    public int getFixedY() {
        return fixedY;
    }

    public void setFixedY(int fixedY) {
        this.fixedY = fixedY;
    }
}
