package me.reckter.Interface;

import me.reckter.Level.BaseLevel;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 * Created with IntelliJ IDEA.
 * User: me.reckter
 * Date: 10/2/13
 * Time: 10:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class Coordinates extends BaseText {
	int life = 3 * 1000;
	public Coordinates(BaseLevel engine, String text, int x, int y) {
		super(engine, text, x, y);
	}

	@Override
	public void logic(int delta) {
		life -= delta;
	}

	@Override
	public boolean isAlive() {
        return life > 0;
    }

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.drawString(text, x, y);
	}
}
