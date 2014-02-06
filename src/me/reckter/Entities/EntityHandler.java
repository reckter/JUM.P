package me.reckter.Entities;

import me.reckter.Level.BaseLevel;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

/**
 * Created by mediacenter on 29.12.13.
 */
public class EntityHandler {


    protected ArrayList<BaseEntity> entities;
    protected ArrayList<BaseEntity> entitiesToAdd; //the entitties that need to be added to the level at the end of he tick
    protected ArrayList<BaseEntity> entitiesToRemove;

    protected BaseLevel level;

    protected int maxId;

    public EntityHandler(BaseLevel level) {
        this.level = level;
        entities = new ArrayList<BaseEntity>();
        entitiesToRemove = new ArrayList<BaseEntity>();
        entitiesToAdd = new ArrayList<BaseEntity>();
        maxId = 0;
    }

    public void add(BaseEntity entity){
        entitiesToAdd.add(entity);
        entity.setId(maxId++);
    }

    public void del(BaseEntity entity){
        entity.setDead(true);
        //entitiesToRemove.add(entity);
    }

    public void updateEntityList(){
        entities.addAll(entitiesToAdd);
        entitiesToAdd = new ArrayList<BaseEntity>();

        entities.removeAll(entitiesToRemove);
        entitiesToRemove = new ArrayList<BaseEntity>();
    }

    public BaseEntity get(int id){
        for(BaseEntity entity: entities){
            if(entity.getId() == id){
                return entity;
            }
        }
        throw new IndexOutOfBoundsException("the Entity with that id doesn't exist!");
    }

    public void logic(int delta) {

        updateEntityList();

        for(BaseEntity entity: entities){
            entity.logic(delta);
        }

        //collision checking
        BaseEntity entityA, entityB;
        for(int i = 0; i < entities.size(); i++){
            entityA = entities.get(i);
            if(entityA.isDead()){
                continue;
            }

            for(int j = i + 1; j < entities.size(); j++){
                entityB = entities.get(j);
                if(entityA.getId() == entityB.getId()){
                    entitiesToRemove.add(entityB);
                } else if(!entityB.isDead() && entityA.checkCollision(entityB)){

                    entityA.onCollision(entityB);
                    entityB.onCollision(entityA);

                }
            }
        }


        //removing all dead entities
        for(BaseEntity entity: entities){
            if(entity.isDead() && entity.getIsDeadFor() >= entity.getMAX_isDeadFor()){
                entitiesToRemove.add(entity);
            }
        }

        updateEntityList();
    }

    public void render(Graphics g){
        for(BaseEntity entity: entities){
           // if(entity.getAAHitBox().intersects(new Rectangle(level.getRealX(0), level.getRealY(0), Engine.SCREEN_WIDTH, Engine.SCREEN_HEIGHT))) {
                entity.render(g);
            //}
        }
    }


    public ArrayList<BaseEntity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<BaseEntity> entities) {
        this.entities = entities;
    }
}
