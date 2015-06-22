
public class Tiger extends Tank {

    private int armor;

    public Tiger(int x, int y, Direction tankDirection, BattleField bf, ActionField af){
        super(x, y, tankDirection, bf, af);
        this.armor = 1;
    };

    public int getArmor() {
        return armor;
    }

    private void setArmor(int armor){
        this.armor = armor;
    }
}
