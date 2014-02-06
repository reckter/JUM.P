package me.reckter.Interface;

import me.reckter.Interface.HUD.BaseHudItem;
import me.reckter.Level.BaseJUMPLevel;
import me.reckter.Level.BaseLevel;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 * Created with IntelliJ IDEA.
 * User: me.reckter
 * Date: 10/1/13
 * Time: 11:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class FPSlabel extends BaseHudItem {

	private int updatesShown;
	private int updates;
	private int updatesTime;

	private int frames;
	private int framesShown;
	private long framesTime;

	private int delta;

	public FPSlabel(BaseLevel enginge) {
		super(enginge);
		updates = 0;
		updatesTime = 0;
		updatesShown = 0;

		frames = 0;
		framesShown = 0;
		framesTime = 0;

		fixedX = 10;
		fixedY = 10;
	}


	@Override
	public void logic(int delta) {
		this.delta = delta;
		updatesTime += delta;
		updates++;
		if(updatesTime >= 1000){
			updatesShown = updates;
			updates = 0;
			updatesTime = 0;
		}
		super.logic(delta);
	}

	@Override
	public void render(Graphics g) {

		frames ++;
		if(framesTime < System.currentTimeMillis() - 1000){
			framesShown = frames;
			framesTime = System.currentTimeMillis();
			frames = 0;
		}
		super.render(g);
		g.setColor(Color.white);
		g.drawString("UPS: " + updatesShown + "; " + (int) (((float) updates / (float) updatesTime) * 1000) + ", " + (int) (1000f / (float) delta) + "; delta: " + delta, x, y);
		g.drawString("FPS: " +  framesShown, x, y + 20);
		g.drawString("(" + (int) level.getPlayer().getX() + "|" + (int) level.getPlayer().getY() + ") Speed: " + level.getPlayer().getMovement().length() , x, y + 40);
		g.drawString("Entities: " + level.getEntities().size() + " Particles: " + level.getParticles().getParticles().size() + " BackgroundParticles: " + level.getParticles().getBackgroundParticles().size(), x, y + 60);

        if(level.getInput() != null){
            g.drawString("Mouse (" + level.getInput().getMouseX() + "|" + level.getInput().getMouseY() + ")|" + level.getRealX(level.getInput().getMouseX()) +"|" + level.getRealY(level.getInput().getMouseY()) + "]", x, y + 80);
	    }
        if(level instanceof BaseJUMPLevel) {
        g.drawString("clk: " + ((BaseJUMPLevel) level).getClk() + "/" + ((BaseJUMPLevel) level).getMAX_CLK(), x , y + 100);
        }
    }
}
