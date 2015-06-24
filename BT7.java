import java.awt.*;

public class BT7 extends AbstractTank {
    public BT7(int x, int y, Direction tankDirection, BattleField bf, ActionField af){
        super(x, y, tankDirection, bf, af);
        speed = speed - 6;
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
