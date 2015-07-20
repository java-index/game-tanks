package game.bf_objects;

import java.awt.*;
import java.util.Random;

public class BattleField implements Drawable {

    private boolean COLORDED_MODE = false;

    /* size game.bf_objects.BattleField */
    private final int gtyQuadrantX = 9; // lets odd
    private final int gtyQuadrantY = 9;

    private final int step = 64;

    private final int BF_WIDTH = gtyQuadrantX * step;
    private final int BF_HEIGHT = gtyQuadrantY * step;

    private String BRICK = "B";
    private String BLANK = "-";
    private String ROCK  = "R";
    private String EAGLE = "E";
    private String WATER = "W";

    BFObjects[][] bfObjects = new BFObjects[gtyQuadrantY][gtyQuadrantX];

    String [][] battleField = {
            {"R", "-", "-", "-", "W", "W", "W", "-", "B"},
            {"R", "-", "-", "-", "W", "W", "W", "-", "-"},
            {"R", "-", "-", "-", "-", "-", "-", "-", "-"},
            {"R", "B", "B", "B", "B", "B", "-", "-", "-"},
            {"R", "-", "-", "-", "-", "-", "-", "-", "R"},
            {"-", "-", "-", "-", "-", "-", "-", "-", "R"},
            {"-", "-", "-", "R", "R", "R", "-", "-", "R"},
            {"-", "-", "-", "R", "-", "R", "-", "-", "R"},
            {"-", "-", "-", "R", "E", "R", "-", "-", "R"}
    };

    public BFObjects scanQuadrant(int x, int y){
        return bfObjects[y][x];
    }

    public void updateQuadrant(int x, int y, String value){
        battleField[y][x] = value;
    }

    public int getDimentionX (){
        return gtyQuadrantX;
    }

    public int getStep() {
        return step;
    }

    public int getDimentionY(){
        return gtyQuadrantY;
    }

    public int getBF_WIDTH() {
        return BF_WIDTH;
    }

    public int getBF_HEIGHT() {
        return BF_HEIGHT;
    }

    private void initialArrayBF(){
        for (int y = 0; y < gtyQuadrantY; y++) {
            for (int x = 0; x < gtyQuadrantX; x++) {
                String typeBFObject = battleField[y][x];
                if (typeBFObject.equals(BRICK)) bfObjects[y][x] = new Brick(x*step, y*step);
                if (typeBFObject.equals(ROCK))  bfObjects[y][x] = new Rock(x*step, y*step);
                if (typeBFObject.equals(EAGLE)) bfObjects[y][x] = new Eagle(x*step, y*step);
                if (typeBFObject.equals(WATER)) bfObjects[y][x] = new Water(x*step, y*step);
                if (typeBFObject.equals(BLANK)) bfObjects[y][x] = new Blank(x*step, y*step);
            }
        }
    }

    public BattleField(){
        initialArrayBF();
    }

    @Override
    public void draw(Graphics g) {
        for (int y = 0; y < gtyQuadrantY; y++) { // y
            for (int x = 0; x < gtyQuadrantX; x++) { // x
                    bfObjects[y][x].draw(g);
            } // for k
        } // for j
    } // draw
}
