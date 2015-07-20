package game.bf_objects.tanks;

import game.*;
import game.bf_objects.*;

import java.awt.*;

public class Tiger extends AbstractTank {

    public Tiger(int x, int y, Direction tankDirection, BattleField bf){
        super(x, y, tankDirection, bf);
        this.armor = 4;
        this.tankColor = new Color(101, 115, 70);
    }

    @Override
    public Action setUp() {
        if(this.armor == 1) this.tankColor = new Color(155, 31, 0);
        return Action.NONE;
    }
}
