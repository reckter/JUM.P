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

    public CommandEntity(BaseLevel level) {
        super(level);
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
    }

    @Override
    public void render(Graphics g) {

        g.setColor(Color.white);
        g.drawString(command.toString(), x, y);

        super.render(g);
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }
}
