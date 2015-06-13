public class Tank {
    private int speed = 6;
    private int x;
    private int y;
    private int direction;

    private ActionField af;
    private BattleField bf;

    /* directions of motions */
    private final int UP = 0;
    private final int DOWN = 1;
    private final int LEFT = 2;
    private final int RIGHT = 3;

    public int getDirection() {
        return direction;
    }

    public int getSpeed() {
        return speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setDirection(int direction) throws Exception {
        this.direction = direction;
        af.processTurn(this);
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void updateX(int x){
        this.x += x;
    }

    public void updateY(int y){
        this.y += y;
    }

    public void turn(int direction) throws Exception {
        this.direction = direction;
        af.processTurn(this);
    }

    public void move() throws Exception {
        System.out.println("move");
        af.processMove(this);
    }

    public void fire()throws Exception {
        Bullet bullet = new Bullet(x + 27, y + 27, direction);
        af.processFire(bullet);
    }

    public void moveRandom() {

    }

    public void moveToQuadrant(int x, int y) {

    }

    public Tank(int x, int y, int direction, BattleField bf, ActionField af){
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.bf = bf;
        this.af = af;
    }
}