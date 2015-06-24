import java.awt.*;
import java.util.Random;

public class BattleField implements Drawable {

    private boolean COLORDED_MODE = true;
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

//    public String getEmptyQuadrant(){
//
//    }

    public BattleField(){
        initialArrayBF();
        createRandomPattern();
    }

    @Override
    public void paintComponent(Graphics g) {
        int i = 0;
        Color cc;
        for (int v = 0; v < gtyQuadrantY; v++) {
            for (int h = 0; h < gtyQuadrantX; h++) {
                if (COLORDED_MODE) {
                    if (i % 2 == 0) {
                        cc = new Color(240, 240, 240);
                    } else {
                        cc = new Color(200, 240, 20);
                    }
                } else {
                    cc = new Color(220, 220, 220);
                }
                i++;
                g.setColor(cc);
                g.fillRect(h * step, v * step, step, step);
            } // for h
        } // for v

		/* fill brick */
        for (int j = 0; j < gtyQuadrantY; j++) { // y
            for (int k = 0; k < gtyQuadrantX; k++) { // x
                if (scanQuadrant(k, j).equals("B")) {
                    int y = j * step;
                    int x = k * step;

                    g.setColor(new Color(190, 110, 70)); // color briks
                    g.fillRect(x, y, step, step);

					/* draw briks wall */
                    g.setColor(new Color(0, 0, 0));
					/* horizontal line */
                    g.drawLine(x, y+16, x+step-1, y+16);
                    g.drawLine(x, y+32, x+step-1, y+32);
                    g.drawLine(x, y+48, x+step-1, y+48);
                    g.drawLine(x, y+63, x+step-1, y+63);
					/* vertical line */
                    g.drawLine(x+16, y, x+16, y+step-1);
                    g.drawLine(x+32, y, x+32, y+step-1);
                    g.drawLine(x+48, y, x+48, y+step-1);
                    g.drawLine(x+63, y, x+63, y+step-1);
					/* lighter vertical line */
                    g.setColor(new Color(230, 170, 110));
                    g.drawLine(x, y, x, y+step-1);
                    g.drawLine(x+16+1, y, x+16+1, y+step-1);
                    g.drawLine(x+32+1, y, x+32+1, y+step-1);
                    g.drawLine(x+48+1, y, x+48+1, y+step-1);
					/* lighter horizontal line */
                    g.drawLine(x, y, x+step-1, y);
                    g.drawLine(x, y+16+1, x+step-1, y+16+1);
                    g.drawLine(x, y+32+1, x+step-1, y+32+1);
                    g.drawLine(x, y+48+1, x+step-1, y+48+1);
                } // if
            } // for k
        } // for j
    }
}
