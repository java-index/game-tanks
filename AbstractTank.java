import java.awt.*;
import java.util.Random;

public abstract class AbstractTank implements Drawable, Destroyable {
    protected int speed = 10;
    protected int x;
    protected int y;
    protected Direction tankDirection;

    protected ActionField af;
    protected BattleField bf;

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

    public void setDirection(Direction tankDirection) throws Exception {
        this.tankDirection = tankDirection;
        af.processTurn(this);
    }

    public void setSpeed(int speed) {
        this.speed = speed;
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

    @Override
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

        public AbstractTank(int x, int y, Direction tankDirection, BattleField bf, ActionField af){
        this.x = x;
        this.y = y;
        this.tankDirection = tankDirection;
        this.bf = bf;
        this.af = af;
    }

    public AbstractTank(BattleField bf, ActionField af){
        this.bf = bf;
        this.af = af;
    }

    @Override
    public void paintComponent(Graphics g) {
        int tankX = x;
        int tankY = y;
        g.setColor(new Color(70, 70, 70));
        g.fillRect(tankX+10, tankY+10, 44, 44);
        g.setColor(new Color(130, 130, 130));
        g.fillOval(tankX+15, tankY+15, 34, 34);

        if (tankDirection == Direction.UP) {
            g.fillRect(tankX + 26, tankY, 12, 34);
            g.fillRect(tankX+2, tankY+2, 8, 58); // left track
            g.fillRect(tankX+bf.getStep()-10, tankY+2, 8, 58); // right track

        } else if (tankDirection == Direction.DOWN) {
            g.fillRect(tankX + 26, tankY + 30, 12, 34);
            g.fillRect(tankX+2, tankY+2, 8, 58); // left track
            g.fillRect(tankX+bf.getStep()-10, tankY+2, 8, 58); // right track

        } else if (tankDirection == Direction.LEFT) {
            g.fillRect(tankX, tankY + 26, 34, 12);
            g.fillRect(tankX+2, tankY+2, 58, 8); // left track
            g.fillRect(tankX+2, tankY+bf.getStep()-10, 58, 8); // right track

        } else { // right
            g.fillRect(tankX + 30, tankY + 26, 34, 12);
            g.fillRect(tankX+2, tankY+2, 58, 8); // left track
            g.fillRect(tankX+2, tankY+bf.getStep()-10, 58, 8); // right track
        }
    }
}