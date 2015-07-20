package game.bf_objects.tanks;

import game.*;
import game.bf_objects.*;

import java.awt.*;

public class T34 extends AbstractTank {
    public T34(int x, int y, Direction tankDirection, BattleField bf){
        super(x, y, tankDirection, bf);
        this.tankColor = new Color(255, 168, 0);
    }

    @Override
    public Action setUp() {
        return Action.FIRE;
    }
}
