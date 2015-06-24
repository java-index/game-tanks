import java.awt.*;

public class BT7 extends AbstractTank {
    public BT7(int x, int y, Direction tankDirection, BattleField bf, ActionField af){
        super(x, y, tankDirection, bf, af);
        speed = speed - 6;
    }
}
