public class Bullet {
    private int speed = 2;
    private int x;
    private int y;
    private int direction;

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

    public void destroy(){
        x = -100;
        y = -100;
    }

    public Bullet(int x, int y, int direction){
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDirection() {
        return direction;
    }
}
