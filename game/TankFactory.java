package game;

import game.bf_objects.BattleField;
import game.bf_objects.tanks.*;

public class TankFactory {

    Tank tank;
    BattleField bf;

    int x;
    int y;

    public TankFactory(BattleField bf){
        this.bf = bf;
    }

    public Tank createTank(TypeTank typeTank){

        switch (typeTank){
            case AGRESSOR:
                tank = createTigerTank();
                break;
            case DEFFENDER:
                tank = createT34Tank();
                break;
            default:
                throw new IllegalArgumentException();
        }

        return tank;
    }

    private Tank createTigerTank(){
        tank = new Tiger(this.x, this.y, Direction.DOWN, bf);
        return tank;
    }

    private Tank createT34Tank(){
        tank = new T34(this.x, this.y, Direction.DOWN, bf);
        return tank;
    }

    private Tank createBT7Tank(){
        tank = new BT7(this.x, this.y, Direction.DOWN, bf);
        return tank;
    }
}
