package me.reckter.Particles;

import me.reckter.Engine;
import me.reckter.Level.BaseLevel;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;

/**
 * Created by mediacenter on 31.12.13.
 */
public class ParticleHandler {

    protected ArrayList<BaseParticle> particles;
    protected ArrayList<BaseParticle> particlesToAdd; //the entitties that need to be added to the level at the end of he tick
    protected ArrayList<BaseParticle> particlesToRemove;


    protected ArrayList<BaseParticle> backgroundParticles;
    protected ArrayList<BaseParticle> backgroundParticlesToAdd;
    protected ArrayList<BaseParticle> backgroundParticlesToRemove;

    protected int timeTillBackgroundUpdate;
    protected int MAX_Background_update = 100;

    protected BaseLevel level;

    public ParticleHandler(BaseLevel level) {
        this.level = level;
        particles = new ArrayList<BaseParticle>();
        particlesToRemove = new ArrayList<BaseParticle>();
        particlesToAdd = new ArrayList<BaseParticle>();


        backgroundParticles = new ArrayList<BaseParticle>();
        backgroundParticlesToAdd = new ArrayList<BaseParticle>();
        backgroundParticlesToRemove = new ArrayList<BaseParticle>();

    }

    public void add(BaseParticle particle){
        if(particle instanceof BackgroundParticle){
            backgroundParticlesToAdd.add(particle);
        } else {
            particlesToAdd.add(particle);
        }
        particle.init();
    }

    public void del(BaseParticle particle){
        if(particle instanceof BackgroundParticle){
            backgroundParticlesToRemove.add(particle);
        } else {
            particlesToRemove.add(particle);
        }
    }

    public void updateparticleList(){
        particles.addAll(particlesToAdd);
        particlesToAdd = new ArrayList<BaseParticle>();

        particles.removeAll(particlesToRemove);
        particlesToRemove = new ArrayList<BaseParticle>();

        backgroundParticles.addAll(backgroundParticlesToAdd);
        backgroundParticlesToAdd = new ArrayList<BaseParticle>();

        backgroundParticles.removeAll(backgroundParticlesToRemove);
        backgroundParticlesToRemove = new ArrayList<BaseParticle>();
    }

    public void logic(int delta){

        updateparticleList();

        for(BaseParticle particle: particles){
            particle.logic(delta);
        }

        timeTillBackgroundUpdate+= delta;
        if(timeTillBackgroundUpdate >= MAX_Background_update){

            timeTillBackgroundUpdate = 0;
            for(BaseParticle particle: backgroundParticles){
                particle.logic(delta + timeTillBackgroundUpdate);
            }
        }


        //removing all dead particles
        int fps = level.getFps();
        fps /= 3;
        int i = 0;
        for(BaseParticle particle: particles){
            if(!particle.isAlive()){
                particlesToRemove.add(particle);
            } else {
                i++;
                if(i >= fps * fps && particle.canBeRemovedForSpeed()){
                    i = 0;
                    particlesToRemove.add(particle);
                }
            }
        }

        for(BaseParticle particle: backgroundParticles){
            if(!particle.isAlive()){
                backgroundParticlesToRemove.add(particle);
            } else {
                i++;
                if(i >= fps * fps && particle.canBeRemovedForSpeed()){
                    i = 0;
                    backgroundParticlesToRemove.add(particle);
                }
            }
        }

        updateparticleList();
    }

    public void render(Graphics g) {
        for(BaseParticle particle: backgroundParticles){

            if(new Rectangle(particle.getX(), particle.getY(), 1,1).intersects(new Rectangle(level.getRealX(0), level.getRealY(0), Engine.SCREEN_WIDTH, Engine.SCREEN_HEIGHT))) {
                particle.render(g);
            }
        }

        for(BaseParticle particle: particles){

            if(new Rectangle(particle.getX(), particle.getY(), 1,1).intersects(new Rectangle(level.getRealX(0), level.getRealY(0), Engine.SCREEN_WIDTH, Engine.SCREEN_HEIGHT))) {
                particle.render(g);
            }
        }
    }


    public ArrayList<BaseParticle> getParticles() {
        return particles;
    }

    public void setParticles(ArrayList<BaseParticle> particles) {
        this.particles = particles;
    }

    public ArrayList<BaseParticle> getBackgroundParticles() {
        return backgroundParticles;
    }

    public void setBackgroundParticles(ArrayList<BaseParticle> backgroundParticles) {
        this.backgroundParticles = backgroundParticles;
    }
}
