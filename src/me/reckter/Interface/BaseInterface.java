package me.reckter.Interface;

import me.reckter.Level.BaseLevel;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created with IntelliJ IDEA.
 * User: me.reckter
 * Date: 10/1/13
 * Time: 7:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class BaseInterface {

	public int x;
	public int y;
	public int width;
	public int height;
	protected BaseLevel level;

	public BaseInterface(BaseLevel level){
		this.level = level;
	}

	public Rectangle getBoundaries(){
		return new Rectangle(x,y,width,height);
	}

	public boolean isAlive(){
		return true;
	}

	public void logic(int delta){

	}

	public void render(Graphics g){

	}

}
