package game.bf_objects.tanks;

import game.Direction;
import game.bf_objects.Destroyable;
import game.bf_objects.Drawable;

public interface Tank extends Drawable, Destroyable {

    public Action setUp();

    public void move();

    public Bullet fire();

    public int getX();

    public int getY();

    public void setX(int x);

    public void setY(int y);

    public void updateX(int x);

    public void updateY(int y);

    public int getSpeed();

    public int getArmor();

    public void setArmor(int value);

    public void updateArmor(int value);

    public Direction getDirection();

    public int getMovePath();

}
