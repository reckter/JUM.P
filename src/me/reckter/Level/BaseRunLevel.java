package me.reckter.Level;

import me.reckter.Engine;
import me.reckter.Entities.BaseEntity;
import me.reckter.Interface.FPSlabel;
import me.reckter.Particles.BaseParticle;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by reckter on 2/6/14.
 */
public class BaseRunLevel extends BaseLevel {


    @Override
    public void init() {
        super.init();
        gravity = 1000;
        interfaces.add(new FPSlabel(this));
        WIDTH = 10000;
        player.setMovement(new Vector2f(100,0));
    }

    @Override
    public void checkBoundaries(BaseEntity entity) {
        if(entity.getHitBox().getMinX() < 0){
            entity.setX(0);
            entity.setMovement(new Vector2f(-entity.getMovement().x, entity.getMovement().y));
        } else if(entity.getHitBox().getMaxX() > WIDTH){
            entity.setX(WIDTH - entity.getHitBox().getWidth());
            entity.setMovement(new Vector2f(-entity.getMovement().x , entity.getMovement().y));
        }
        if(entity.getHitBox().getMinY() < 0){
            entity.setY(0);
            entity.setMovement(new Vector2f(entity.getMovement().x, -entity.getMovement().y * 0.6f));
        } else if(entity.getHitBox().getMaxY() > HEIGHT){
            entity.setY(HEIGHT - entity.getHitBox().getHeight());
            entity.setMovement(new Vector2f(entity.getMovement().x, -entity.getMovement().y * 0.6f));
        }
    }

    @Override
    public void checkBoundaries(BaseParticle particle) {
        super.checkBoundaries(particle);
    }

    @Override
    public void render(Graphics g) {

        if(player != null){
            camX = (int) - (player.getX() - Engine.SCREEN_WIDTH * 0.2f);
            camY = (int) - (player.getY() - Engine.SCREEN_HEIGHT * 0.6f);
        }
        g.translate(camX, camY);
        g.draw(new Rectangle(0, 0, WIDTH, HEIGHT));

        particles.render(g);

        entities.render(g);

        interfaces.render(g);
    }




}
