import java.util.Random;

public class BattleField {

    /* size BattleField */
    private final int gtyQuadrantX = 9; // lets odd
    private final int gtyQuadrantY = 9;
    private final int step = 64;
    private final int BF_WIDTH = gtyQuadrantX * step;

    private final int BF_HEIGHT = gtyQuadrantY * step;

    String[][] battleField = null;
    Random random = new Random();

    public String scanQuadrant(int x, int y){
        return battleField[y][x];
    }

    public void updateQuadrant(int x, int y, String value){
        battleField[y][x] = value;
    }

    int getDimentionX (){
        return gtyQuadrantX;
    }

    public int getStep() {
        return step;
    }

    int getDimentionY(){
        return gtyQuadrantY;
    }

    public int getBF_WIDTH() {
        return BF_WIDTH;
    }

    public int getBF_HEIGHT() {
        return BF_HEIGHT;
    }

    private void initialArrayBF(){
        battleField = new String[gtyQuadrantY][];  // y
        for (int i = 0; i < battleField.length; i++){
            battleField[i] = new String [gtyQuadrantX]; // x
        }
    }

    private void createRandomPattern() {
        for (int y = 0; y < battleField.length; y++){ // У
            for (int x = 0; x < battleField[y].length; x++){ // Х
                if(random.nextInt(3) == 2){ // 0-1-2
                    updateQuadrant(x, y, "B");
                } else {
                    updateQuadrant(x, y, " ");
                }
            } // for x
        } // for y
    }

    public String getEmptyQuadrant(){

    }

    public BattleField(){
        initialArrayBF();
        createRandomPattern();
    }

}
