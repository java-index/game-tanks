package game.bf_objects;

import java.awt.*;

/**
 * Created by rabota on 19.07.15.
 */
public class Blank extends AbstractObjectsBF {
    public Blank(int x, int y){
        super(x, y);
        this.isDestroyed = true;
        this.color = Color.GREEN;
    }
}
