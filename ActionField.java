import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class ActionField extends JPanel {

    private BattleField bf;
    private Bullet bullet;
    private AbstractTank deffender;
    private AbstractTank agressor;

    private int signX = 0;
    private int signY = 0;

    public void runTheGame() throws Exception {
        while (true) {
            deffender.randomClean();
        }
    }

    public void processMove(AbstractTank tank) throws Exception {
        this.deffender = tank;
        moveOneStep(tank);
    }

    private void moveOneStep(AbstractTank tank) throws Exception {
        createSign(tank.getDirection());
        checkObstruction(tank, signX, signY);
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

    private void checkObstruction(AbstractTank tank, int correctX, int correctY) throws Exception {
        if ((tank.getX() % bf.getStep() != 0) || (tank.getY() % bf.getStep() != 0)) {
            return;
        }
        int checkQuadratX = getCoordQuadrant(tank.getX()) + correctX;
        int checkQuadratY = getCoordQuadrant(tank.getY()) + correctY;
        if (!bf.scanQuadrant(checkQuadratX, checkQuadratY).equals(" ")) {
            System.out.println("FIRE!");
            tank.fire();
        }
    }

    private void createSign(Direction direction) {
        int[] mask = {1, -1, 1, -1}; // [0][1] for Y; [2][3] for X
        mask[direction.ordinal()] = 0;
        signX = mask[2] + mask[3]; // x (-1 or 0 or +1)
        signY = mask[0] + mask[1]; // y (-1 or 0 or +1)
    }

    public void processTurn(AbstractTank tank) throws Exception {
        repaint();
    }

    public void processFire(Bullet bullet) throws Exception {
        this.bullet = bullet;
        while (!bulletOutOfBattleField()) {
            moveOneStep(bullet);
            if (processInterception()) { // hit
                explioson();
            }
            repaint();
        } // while
    }

    private void explioson() {
        int x = getCoordQuadrant(bullet.getX());
        int y = getCoordQuadrant(bullet.getY());
        if (scanTankAtQuadrant(x, y)) {
            agressor.destroy();
            //agressor.rebuilding();
        } else {
            bf.updateQuadrant(x, y, " ");
        }
        bullet.destroy();
    }

    private boolean bulletOutOfBattleField() {
        //10 size bullet
        if ((bullet.getX() > -10 && bullet.getX() < bf.getBF_WIDTH() + 10) &&
                (bullet.getY() > -10 && bullet.getY() < bf.getBF_HEIGHT() + 10)) {
            return false;
        } else {
            return true;
        }
    }

    private boolean processInterception() {
        //bullet.getX() < bf.getBF_WIDTH() && bullet.getY() < bf.getBF_HEIGHT()
        if (bullet.getX() < bf.getBF_WIDTH() && bullet.getY() < bf.getBF_HEIGHT()) {
            int x = getCoordQuadrant(bullet.getX());
            int y = getCoordQuadrant(bullet.getY());
            if (!bf.scanQuadrant(x, y).equals(" ") || scanTankAtQuadrant(x, y)) {
                return true;
            }
        }
        return false;
    }

    private boolean scanTankAtQuadrant(int quadrantBulletX, int quadrantBulletY) {
        int quadrantAgressorX = getCoordQuadrant(agressor.getX());
        int quadrantAgressorY = getCoordQuadrant(agressor.getY());
        if (quadrantBulletX == quadrantAgressorX && quadrantBulletY == quadrantAgressorY) {
            return true;
        }
        return false;
    }

    public int getCoordQuadrant(int coordPixel) {
        return (int) (coordPixel / bf.getStep());  // 0 (0-63px); 1 (64-127px) etc.
    }

    public int getCoordPixel(int coordQuadrant) {
        return coordQuadrant * bf.getStep();
    }

    public ActionField() throws Exception {
        bf = new BattleField();
        bullet = new Bullet(-100, -100, Direction.DOWN);
        deffender = new T34(0, 0, Direction.DOWN, bf, this);
        agressor = new Tiger(512, 512, Direction.DOWN, bf, this);

        JFrame frame;
        frame = new JFrame("** WORD OF TANKS **");
        frame.setLocation(500, 150);
        frame.setMinimumSize(new Dimension(576, 576 + 22));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        bf.paintComponent(g);
        bullet.paintComponent(g);
        deffender.paintComponent(g);
        agressor.paintComponent(g);
    }
}
