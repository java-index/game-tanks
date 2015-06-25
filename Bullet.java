import java.awt.*;

public class Bullet implements Drawable, Destroyable {
    private int speed = 3;
    private int x;
    private int y;
    private Direction bulletDirection;

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
        x = -100;
        y = -100;
    }

    public Bullet(int x, int y, Direction bulletDirection){
        this.x = x;
        this.y = y;
        this.bulletDirection = bulletDirection;
    }

    public int getSpeed() {
        return speed;
    }

    public Direction getDirection() {
        return bulletDirection;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(new Color(0, 0, 0));
        g.fillOval(this.x, this.y, 10, 10);
    }
}
