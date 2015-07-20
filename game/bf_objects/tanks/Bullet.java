package game.bf_objects.tanks;

import game.bf_objects.Destroyable;
import game.bf_objects.Drawable;
import game.Direction;

import java.awt.*;

public class Bullet implements Drawable, Destroyable {
    private int speed = 3;
    private int x;
    private int y;
    private Direction bulletDirection;
    private Tank owner;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void updateX(int x){
        this.x += x;
    }

    public void updateY(int y){
        this.y += y;
    }

    @Override
    public void destroy(){
        this.x = -100;
        this.y = -100;
        System.out.println("bullet destoy");
    }

    public Bullet(int x, int y, Direction bulletDirection){
        this.x = x;
        this.y = y;
        this.bulletDirection = bulletDirection;
    }

    public Tank getOwner() {
        return owner;
    }

    public void setOwner(Tank owner) {
        this.owner = owner;
    }

    public int getSpeed() {
        return speed;
    }

    public Direction getDirection() {
        return bulletDirection;
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(255, 0, 16));
        g.fillOval(this.x, this.y, 10, 10);
    }
}
