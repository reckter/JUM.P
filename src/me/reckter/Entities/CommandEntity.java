package me.reckter.Entities;

import me.reckter.CodeCreator.Code.Commands.Branch;
import me.reckter.CodeCreator.Code.Commands.Command;
import me.reckter.CodeCreator.Code.Commands.Jump;
import me.reckter.CodeCreator.Code.Marker.Marker;
import me.reckter.Level.BaseLevel;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 * Created by reckter on 2/6/14.
 */
public class CommandEntity extends BaseEntity {

    protected Command command;

    protected boolean isSelected;
    protected boolean gotJumped;


    protected int selectedFor;
    protected int MAX_selectedFor;

    public CommandEntity(BaseLevel level) {
        super(level);
        isSelected = false;
    }


    @Override
    public Rectangle getAAHitBox() {
        return new Rectangle(x - 5, y, 300, 25);
    }

    @Override
    public Shape getHitBox() {
        return getAAHitBox();
    }

    @Override
    public void init() {
        super.init();
        command = new Command(new Marker(""));
        MAX_selectedFor = 20;

        width = 300;
        height = 30;
        size = 20;

    }

    @Override
    public void onCollision(BaseEntity with) {
        if(with instanceof Player && !((Player) with).isFlying) {
            isSelected = true;
            selectedFor = MAX_selectedFor;
        }
    }

    public boolean needsJump() {
        return command instanceof Jump;
    }

    public boolean canBeJumped() {
        return command instanceof Branch || needsJump();
    }

    @Override
    public void logic(int delta) {
        super.logic(delta);

        if(level.getPlayer().isDead()){
            if(!isSelected) {
                onDeath(level.getPlayer());
            }
            return;
        }
        selectedFor -= delta;
        if(selectedFor < 0 && isSelected) {
            selectedFor = Integer.MAX_VALUE;
            isSelected = false;
            if(needsJump() && !gotJumped) {
                level.getPlayer().onDeath(this);
                isSelected = true;
            }
        }
    }

    @Override
    public void render(Graphics g) {

        if(isSelected) {
            g.setColor(Color.white);
        } else {
            g.setColor(Color.gray);
        }
        g.drawString(command.toString(), x, y);

        //super.render(g);
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }
}
