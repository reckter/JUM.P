package me.reckter.Interface;

import me.reckter.Entities.BaseEntity;
import me.reckter.Level.BaseLevel;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 * Created with IntelliJ IDEA.
 * User: me.reckter
 * Date: 10/1/13
 * Time: 11:29 AM
 * To change this template use File | Settings | File Templates.
 */
public class DamageText extends BaseText {

	protected int life;
	protected int MAX_LIFE = 3 * 1000;
	public boolean isDamage;

	public DamageText(BaseLevel level, int dmg){
		super(level, "-" + dmg);
		isDamage = true;
		life = MAX_LIFE;
		if(dmg < 0){
			dmg = -dmg;
			text = "+" + dmg;
			isDamage = false;
		}

	}

	public DamageText(BaseLevel level, int dmg, BaseEntity on){
		this(level, dmg);
		this.x = (int) (on.getX() - on.getSize());
		this.y = (int) (on.getY() - on.getSize());
	}


	@Override
	public boolean isAlive() {
        return life > 0;
    }

	@Override
	public void logic(int delta) {
		life -= delta;
	}

	@Override
	public void render(Graphics g) {
		if(isDamage){
			g.setColor(new Color(255,0,0, life / 10));
			g.drawString(text, x, y + life / 200);
		} else {
			g.setColor(new Color(0,255,0, life / 10));
			g.drawString(text, x, y + life / 200);
		}
	}
}
