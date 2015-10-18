package game.bf_objects.tanks;

import game.Direction;
import game.bf_objects.BattleField;

import java.awt.*;

public abstract class AbstractTank implements Tank {
    protected int speed = 8;
    protected int armor = 2;
    protected int x;
    protected int y;
    protected Direction tankDirection;
    protected Color tankColor;
    protected boolean isDestroyed = false;
    protected BattleField bf;

    public AbstractTank(int x, int y, Direction tankDirection, BattleField bf){
        this.x = x;
        this.y = y;
        this.tankDirection = tankDirection;
        this.bf = bf;
    }

    public AbstractTank(BattleField bf){
        this.bf = bf;
    }

    public void setDirection(Direction tankDirection) {
        this.tankDirection = tankDirection;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void turn(Direction tankDirection) {
        this.tankDirection = tankDirection;
    }

    @Override
    public Direction getDirection() {
        return this.tankDirection;
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void updateX(int x){
        this.x += x;
    }

    @Override
    public void updateY(int y){
        this.y += y;
    }

    @Override
    public int getArmor() {
        return this.armor;
    }

    @Override
    public void setArmor(int armor){
        this.armor = armor;
    }

    @Override
    public void updateArmor(int value){
        this.armor += value;
    }

    @Override
    public void move() {
    }

    @Override
    public Bullet fire() {
        return new Bullet(this.x + 27, this.y + 27, tankDirection);
    }

    @Override
    public void destroy(){
        this.x = -100;
        this.y = -100;
        this.isDestroyed = true;
    }

    @Override
    public int getMovePath() {
        return 0;
    }

    @Override
    public boolean isDestroyed() {
        return isDestroyed;
    }

    @Override
    public void draw(Graphics g) {
        int tankX = x;
        int tankY = y;
        g.setColor(tankColor);
        g.fillRect(tankX, tankY, 64, 64);
        g.setColor(new Color(255, 0, 16));

        if (tankDirection == Direction.UP) {
            g.fillRect(tankX + 20, tankY, 24, 34);
        } else if (tankDirection == Direction.DOWN) {
            g.fillRect(tankX + 20, tankY + 30, 24, 34);
        } else if (tankDirection == Direction.LEFT) {
            g.fillRect(tankX, tankY + 20, 34, 24);
        } else { // right
            g.fillRect(tankX + 30, tankY + 20, 34, 24);
        }
    }
}