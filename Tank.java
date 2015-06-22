import java.util.Random;

public class Tank {
    protected int speed = 6;
    private int x;
    private int y;
    private Direction tankDirection;

    private ActionField af;
    private BattleField bf;

    public Direction getDirection() {
        return tankDirection;
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

    public void setDirection(Direction tankDirection) throws Exception {
        this.tankDirection = tankDirection;
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

    public void turn(Direction tankDirection) throws Exception {
        this.tankDirection = tankDirection;
        af.processTurn(this);
    }

    public void move() throws Exception {
        af.processMove(this);
    }

    public void fire()throws Exception {
        Bullet bullet = new Bullet(x + 27, y + 27, tankDirection);
        af.processFire(bullet);
    }

    void moveToQuadrantXY(int xQuad, int yQuad) throws Exception {
        int newX = af.getCoordPixel(xQuad);
        int newY = af.getCoordPixel(yQuad);

        while (!tankAtNewCoordinate(newX, newY)) {
            Direction newDirection = defineDirection(newX, newY);
            setDirection(newDirection);
            af.processMove(this);
        }
    }

    private Direction defineDirection(int newX, int newY) {
        Direction newDirection = Direction.NONE;

        if (newX > this.x) {
            newDirection = Direction.RIGHT;
        }
        if (newX < this.x) {
            newDirection = Direction.LEFT;
        }
        if (newY > this.y) {
            newDirection = Direction.DOWN;
        }
        if (newY < this.y) {
            newDirection = Direction.UP;
        }
        return newDirection;
    }

    boolean tankAtNewCoordinate(int newX, int newY){
        if (this.x == newX && this.y == newY){
            return true;
        } else {
            return false;
        }
    }

    public void destroy(){
        x = -100;
        y = -100;
    }

    public void randomClean() throws Exception {
        Random rand = new Random();
        for (int y = 0; y < bf.getDimentionY(); y++) { // y
            int x = rand.nextInt(bf.getDimentionX()); // quadrat x 0-8
            if (bf.scanQuadrant(x, y).equals(" ")) {
                continue;
            } else {
                moveToQuadrantXY(x, y);
            } // if
        }// for y
    }

        public Tank(int x, int y, Direction tankDirection, BattleField bf, ActionField af){
        this.x = x;
        this.y = y;
        this.tankDirection = tankDirection;
        this.bf = bf;
        this.af = af;
    }

    public Tank(BattleField bf, ActionField af){
//        this.x = x;
//        this.y = y;
//        this.tankDirection = tankDirection;
        this.bf = bf;
        this.af = af;
    }
}