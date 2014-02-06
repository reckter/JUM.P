package me.reckter.Level;

import me.reckter.Engine;
import me.reckter.Entities.BaseEntity;
import me.reckter.Entities.EntityHandler;
import me.reckter.Entities.Player;
import me.reckter.Interface.BaseInterface;
import me.reckter.Interface.HUD.Score;
import me.reckter.Interface.InterfaceHandler;
import me.reckter.Log;
import me.reckter.Particles.BaseParticle;
import me.reckter.Particles.ParticleHandler;
import me.reckter.Sound.SoundEngine;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import java.net.SocketException;
import java.util.ArrayList;

/**
 * Created by mediacenter on 25.12.13.
 */
public class BaseLevel {



    protected int camX;
    protected int camY;

    protected int fps;

    protected Input input; //workaroung till the real input is implemented

    protected SoundEngine soundEngine;

    protected EntityHandler entities;

    protected BaseLevel nextLevel;

    protected ParticleHandler particles;

    public InterfaceHandler interfaces;

    protected float gravity;

    protected Player player; // the player

    protected Score score;

    public int HEIGHT = 400;
    public int WIDTH = 640;


    public BaseLevel(){

        this.entities = new EntityHandler(this);

        this.interfaces = new InterfaceHandler();

        this.particles = new ParticleHandler(this);

        this.score = new Score(this);

        this.nextLevel = null;

        this.soundEngine = new SoundEngine();
    }

    /**
     * populates the Level
     */
    public void populate(){
        Log.info("Populting level...");
        player = new Player(this);
        player.init();
        player.setSize(10);

        add(player);
    }

    /**
     * gets called bevor every logic tick
     * @param input
     */
    public void processInput(Input input){
        player.processInput(input);
    }

    /**
     * gets called when Level is initialized
     */
    public void init(){
        Log.info("initializing level...");

        interfaces.add(score);
        entities.updateEntityList();
    }

    /**
     * ads an entity to the level
     * @param entity
     */
    public void add(BaseEntity entity){
        entities.add(entity);
    }

    /**
     * adds an interface
     * @param face the interface to be added
     */
    public void add(BaseInterface face){
        interfaces.add(face);
    }


    /**
     * adds a particle
     * @param particle the interface to be added
     */
    public void add(BaseParticle particle){
        particles.add(particle);
    }


    /**
     * delets an entity
     * @param entity the entity to be deleted
     */
    public void del(BaseEntity entity){
        entities.del(entity);
    }



    /**
     * delets a particle
     * @param particle the entity to be deleted
     */
    public void del(BaseParticle particle){
        particles.del(particle);
    }

    /**
     * delets an Interface
     * @param face the particle to be deleted
     */
    public void del(BaseInterface face){
        interfaces.del(face);
    }




    /**
     * checks if the given entity is in the boundaries and if not raps it around.
     * @param entity
     */
    public void checkBoundaries(BaseEntity entity){
        if(entity.getX() < 0){
            entity.setX(WIDTH);
        } else if(entity.getX() > WIDTH){
            entity.setX(0);
        }
        if(entity.getY() < 0){
            entity.setY(HEIGHT);
        } else if(entity.getY() > HEIGHT){
            entity.setY(0);
        }
    }


    /**
     * checks if the given entity is in the boundaries and if not raps it around.
     * @param particle
     */
    public void checkBoundaries(BaseParticle particle){
        if(particle.getX() < 0){
            particle.setX(WIDTH);
        } else if(particle.getX() > WIDTH){
            particle.setX(0);
        }
        if(particle.getY() < 0){
            particle.setX(HEIGHT);
        } else if(particle.getY() > HEIGHT){
            particle.setX(0);
        }
    }

    /**
     * gets called every logic tick
     * @param delta the time in ms since the last logic tick
     */
    public void logic(int delta) {

        interfaces.logic(delta);

        particles.logic(delta);

        entities.logic(delta);
    }

    /**
     * gets called evey render
     * @param g the graphics object on which should be drawn
     */
    public void render(Graphics g){

        if(player != null){
            camX = (int) - (player.getX() - Engine.SCREEN_WIDTH / 2);
            camY = (int) - (player.getY() - Engine.SCREEN_HEIGHT / 2);
        }
        g.translate(camX, camY);

        particles.render(g);

        entities.render(g);

        interfaces.render(g);
    }

    /**
     * returns the X position on the field (from an x position on the screen)
     * @param x
     * @return
     */
    public float getRealX(float x){
        return x + ( - camX);
    }

    /**
     * returns the X position on the Screen (from an x position on the field)
     * @param x
     * @return
     */
    public float getScreenX(float x){
        return x - ( - camX);
    }

    /**
     * returns the Y position on the field (from an y position on the screen)
     * @param y
     * @return
     */
    public float getRealY(float y){
        return y + ( - camY);
    }

    /**
     * returns the Y position on the Screen (from an y position on the field)
     * @param y
     * @return
     */
    public float getScreenY(float y){
        return y - ( - camY);
    }



    public ArrayList<BaseEntity> getEntities() {
        return entities.getEntities();
    }

    public void setEntities(ArrayList<BaseEntity> entities) {
        this.entities.setEntities(entities);
    }

    public BaseEntity getEntityById(int id){
        for(BaseEntity entity: getEntities()) {
            if(entity.getId() == id) {
                return entity;
            }
        }
        throw new IndexOutOfBoundsException("no entity with id: " + id);
    }

    public ParticleHandler getParticles() {
        return particles;
    }

    public void setParticles(ParticleHandler particles) {
        this.particles = particles;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getCamX() {
        return camX;
    }

    public void setCamX(int camX) {
        this.camX = camX;
    }

    public int getCamY() {
        return camY;
    }

    public void setCamY(int camY) {
        this.camY = camY;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public BaseLevel getNextLevel() {
        return nextLevel;
    }

    public void setNextLevel(BaseLevel nextLevel) {
        this.nextLevel = nextLevel;
    }

    public int getFps() {
        return fps;
    }

    public void setFps(int fps) {
        this.fps = fps;
    }

    public Input getInput() {
        return input;
    }

    public void setInput(Input input) {
        this.input = input;
    }

    public SoundEngine getSoundEngine() {
        return soundEngine;
    }

    public void setSoundEngine(SoundEngine soundEngine) {
        this.soundEngine = soundEngine;
    }

    public float getGravity() {
        return gravity;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }
}

