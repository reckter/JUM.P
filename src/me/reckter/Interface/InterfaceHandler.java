package me.reckter.Interface;

import org.newdawn.slick.Graphics;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: me.reckter
 * Date: 10/1/13
 * Time: 7:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class InterfaceHandler {

    protected ArrayList<BaseInterface> interfaces;

    public InterfaceHandler() {
        interfaces = new ArrayList<BaseInterface>();
    }

    /**
     * adds a interface
     * @param face
     */
    public void add(BaseInterface face){
        interfaces.add(face);
    }

    /**
     * removes an interface
     * @param face
     */
    public void del(BaseInterface face){
        interfaces.remove(face);
    }

    /**
     * gets called every logic tick
     * @param delta
     */
    public void logic(int delta){

        ArrayList<BaseInterface> tempColl = new ArrayList<BaseInterface>();
        for(BaseInterface face: interfaces){

            face.logic(delta);

            if(!face.isAlive()){
                tempColl.add(face);
            }
        }
        interfaces.removeAll(tempColl);
    }

    /**
     * gets called every render
     * @param g
     */
    public void render(Graphics g){
        for(BaseInterface face: interfaces){
            face.render(g);
        }
    }
}
