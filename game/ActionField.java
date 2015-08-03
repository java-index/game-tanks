package game;

import game.bf_objects.*;
import game.bf_objects.tanks.*;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class ActionField extends JPanel {

    private BattleField bf;
    private Bullet bullet;
    private Tank deffender;
    private Tank agressor;

    private int signX = 0;
    private int signY = 0;

    public void runTheGame() throws Exception {
        while (true) {
            System.out.println("DEFFENDER");
            processAction(deffender.setUp(), deffender);
            System.out.println("AGRESSOR");
            processAction(agressor.setUp(), agressor);
        }
    }

    private void processAction(Action a, Tank tank) throws Exception {
        if (a == Action.MOVE) {
            processMove(tank);
        } else if (a == Action.FIRE) {
            processFire(tank.fire(), tank);
        }
    }

    public void processMove(Tank tank) throws Exception {
        moveOneStep(tank);
    }

    private void moveOneStep(Tank tank) throws Exception {
        createSign(tank.getDirection());
        checkInterception(tank, signX, signY);
        tank.updateX(signX);
        tank.updateY(signY);
        repaint();
        Thread.sleep(tank.getSpeed());
    }

    private void moveOneStep(Bullet bullet) throws Exception {
        createSign(bullet.getDirection());
        bullet.updateX(signX);
        bullet.updateY(signY);
        repaint();
        Thread.sleep(bullet.getSpeed());
    }

    private void createSign(Direction direction) {
        int[] mask = {1, -1, 1, -1}; // [0][1] for Y; [2][3] for X
        mask[direction.ordinal()] = 0;
        signX = mask[2] + mask[3]; // x (-1 or 0 or +1)
        signY = mask[0] + mask[1]; // y (-1 or 0 or +1)
    }

    private void checkInterception(Tank tank, int correctX, int correctY) throws Exception {
        if ((tank.getX() % bf.getStep() != 0) || (tank.getY() % bf.getStep() != 0)) {
            return;
        }
        int checkQuadratX = getCoordQuadrant(tank.getX()) + correctX;
        int checkQuadratY = getCoordQuadrant(tank.getY()) + correctY;
        if (!bf.scanQuadrant(checkQuadratX, checkQuadratY).isDestroyed()) {
            System.out.println("FIRE!");
            processFire(tank.fire(), tank);
        }
    }

    public void processTurn(Tank tank) throws Exception {
        repaint();
    }

    public void processFire(Bullet bullet, Tank bulletOwner) throws Exception {
        this.bullet = bullet;
        this.bullet.setOwner(bulletOwner);
        while (!bulletOutOfBattleField()) {
            moveOneStep(bullet);
            if (processInterception(bulletOwner)) { // hit
                destroy();
            }
            repaint();
        } // while
    }

    private boolean processInterception(Tank bulletOwner) {
        int x = getCoordQuadrant(bullet.getX());
        int y = getCoordQuadrant(bullet.getY());
        if (!bf.scanQuadrant(x, y).isDestroyed() || checkInterceptionTank(bullet.getX(), bullet.getY() )) {
            return true;
        }
        return false;
    }

    private boolean checkInterceptionTank(int bulletX, int bulletY) {
        return (bulletX >= agressor.getX() && bulletY >= agressor.getY() &&
                bulletX <= agressor.getX()+bf.getStep() && bulletY <= agressor.getY()+bf.getStep());
    }

    private void destroy() {
        int x = getCoordQuadrant(bullet.getX());
        int y = getCoordQuadrant(bullet.getY());
        if (checkInterceptionTank(bullet.getX(), bullet.getY() )) {
            agressor.updateArmor(-1);
            if(agressor.getArmor() == 0) agressor.destroy();
        } else {
            bf.scanQuadrant(x, y).destroy();
        }
        bullet.destroy();
    }

    private boolean bulletOutOfBattleField() {
        //number 10 is size bullet
        if ((bullet.getX() > -10 && bullet.getX() < bf.getBF_WIDTH()-1) &&
            (bullet.getY() > -10 && bullet.getY() < bf.getBF_HEIGHT()-1)) {
            return false;
        } else {
            return true;
        }
    }

    public int getCoordQuadrant(int coordPixel) {
        return (int) (coordPixel / bf.getStep());  // 0 (0-63px); 1 (64-127px) etc.
    }

    public int getCoordPixel(int coordQuadrant) {
        return coordQuadrant * bf.getStep();
    }

    /*************/

    void moveToQuadrantXY(int xQuad, int yQuad, Tank t) throws Exception {
        int newX = getCoordPixel(xQuad);
        int newY = getCoordPixel(yQuad);

        while (!tankAtNewCoordinate(newX, newY, t)) {
            Direction newDirection = defineDirection(newX, newY, t);
            //setDirection(newDirection);
            //processMove(t);
        }
    }

    private Direction defineDirection(int newX, int newY, Tank tank) {
        Direction newDirection = Direction.NONE;

        if (newX > tank.getX()) {
            newDirection = Direction.RIGHT;
        }
        if (newX < tank.getX()) {
            newDirection = Direction.LEFT;
        }
        if (newY > tank.getY()) {
            newDirection = Direction.DOWN;
        }
        if (newY < tank.getY()) {
            newDirection = Direction.UP;
        }
        return newDirection;
    }

    boolean tankAtNewCoordinate(int newX, int newY, Tank tank){
        return (tank.getX() == newX && tank.getY() == newY);
    }

    /******************/

    public ActionField() throws Exception {
        bf = new BattleField();
        bullet = new Bullet(-100, -100, Direction.DOWN);
        deffender = new T34(0, 448, Direction.RIGHT, bf);
        agressor = new Tiger(370, 448, Direction.RIGHT, bf);

        JFrame frame;
        frame = new JFrame("** WORD OF TANKS **");
        frame.setLocation(500, 150);
        frame.setMinimumSize(new Dimension(576, 576 + 22));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBackground(new Color(255, 246, 192));
        frame.getContentPane().add(BorderLayout.CENTER, this);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        bf.draw(g);
        deffender.draw(g);
        agressor.draw(g);
        bullet.draw(g);
    }
}
