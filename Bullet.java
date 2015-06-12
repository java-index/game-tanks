public class Bullet {
    private int speed;
    private int x;
    private int y;
    private int direction;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void updateX(int value){
        x =+ value;
    }

    public void updateY(int value){
        y += value;
    }

    public void destroy(){
        x = -100;
        y = -100;
    }
}
