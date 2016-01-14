package game.bf_objects;

import java.awt.*;

public abstract class AbstractObjectsBF implements BFObjects {

    protected int x;

    protected int y;

    protected Color color;
    protected boolean isDestroyed = false;

    public AbstractObjectsBF(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    @Override
    public void draw(Graphics g) {
        if(!isDestroyed) {
            g.setColor(this.color);
            g.fillRect(this.x, this.y, 64, 64);
            g.setColor(new Color(0, 0, 0));
            g.drawRect(this.x, this.y, 64, 64);
        }
    }

    @Override
    public void destroy() {
        this.isDestroyed = true;
    }

    @Override
    public boolean isDestroyed() {
        return isDestroyed;
    }
}
