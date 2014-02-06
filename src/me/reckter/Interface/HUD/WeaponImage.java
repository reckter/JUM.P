package me.reckter.Interface.HUD;

import me.reckter.Engine;
import me.reckter.Level.BaseLevel;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by mediacenter on 05.01.14.
 */
public class WeaponImage extends BaseHudItem {

    String weapon;
    public WeaponImage(BaseLevel level) {
        super(level);
        weapon = "";

        height = 25;
        width = 25;

        fixedX = Engine.SCREEN_WIDTH / 2 - 150 - width;
        fixedY = Engine.SCREEN_HEIGHT - height;
    }

    @Override
    public void logic(int delta) {

        weapon = level.getPlayer().getWeapon();
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        g.setColor(Color.white);
        g.draw(new Rectangle(x, y, width, height));
        if(weapon == "shoot"){
            g.fill(new Circle(x + (width / 2), y + (height / 2), (float) width  * 0.2f));

        } else if(weapon == "laser"){
            g.draw(new Line(x + 6, y + height - 6, x + width - 6, y + 6));
            Vector2f point = new Vector2f(x + width - 6, y + 6);
            for(int i = 1; i < 7; i++){
                g.draw(new Line(point, point.copy().add(new Vector2f(i * 360f / 7f).normalise().scale(4))));
            }
        } else if(weapon == "grenade"){
            g.fill(new Circle(x + (width / 2), y + ((float) height * 0.65f), (float) width  * 0.2f));
            g.fill(new Rectangle(x + 10, y + 5, 5, 10));
            g.draw(new Line(x + 12, y + 5, x + 18, y + 5));

        } else if(weapon == "bomb"){
            g.fill(new Circle(x + (width / 2), y + ((float) height * 0.65f), (float) width  * 0.25f));
            g.fill(new Rectangle(x + 10, y + 5, 5, 10));
            g.fill(new Rectangle(x + 9, y + 9, 7, 6));
        } else if(weapon == "missile"){


            Vector2f point = new Vector2f(0).normalise().scale(width * 0.3f);
            Vector2f side = point.copy().add(90);

            Vector2f neutral = new Vector2f(x + width / 2,y + height / 2);

            g.setColor(Color.white);

            g.draw(new Line(neutral.copy().add(point.copy().scale(-1)).add(side.copy().scale(0.3f)), neutral.copy().add(point.copy().scale(-1)).add(side.copy().scale(-0.3f)) ));

            g.draw(new Line(neutral.copy().add(point.copy().scale(-1)).add(side.copy().scale(0.3f)), neutral.copy().add(point.copy().scale(0.5f)).add(side.copy().scale(0.3f))));
            g.draw(new Line(neutral.copy().add(point.copy().scale(-1)).add(side.copy().scale(-0.3f)), neutral.copy().add(point.copy().scale(0.5f)).add(side.copy().scale(-0.3f))));

            g.draw(new Line(neutral.copy().add(point.copy().scale(0.5f)).add(side.copy().scale(0.3f)), neutral.copy().add(point.copy().scale(1f))));
            g.draw(new Line(neutral.copy().add(point.copy().scale(0.5f)).add(side.copy().scale(-0.3f)), neutral.copy().add(point.copy().scale(1f))));

        }
    }
}
