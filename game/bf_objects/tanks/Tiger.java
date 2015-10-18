package game.bf_objects.tanks;

import game.*;
import game.bf_objects.*;

import java.awt.*;
import java.util.Random;

public class Tiger extends AbstractTank {

    int i = 0;
    int k = 5;

    public Tiger(int x, int y, Direction tankDirection, BattleField bf){
        super(x, y, tankDirection, bf);
        this.armor = 4;
        this.speed += 10;
        this.tankColor = new Color(101, 115, 70);
    }

    @Override
    public Action setUp() {
        if (i < 128 && i >= 0) {
            i++;
            return Action.MOVE_DOWN;
        } else if (i >= 128 && i < 256) {
            i++;
            return Action.MOVE_RIGHT;
        } else if (i >= 256 && i < 384) {
            i++;
            return Action.MOVE_UP;
        }
        return Action.MOVE_LEFT;
    }

    @Override
    public String getNameTank() {
        return "Tiger";
    }
}
