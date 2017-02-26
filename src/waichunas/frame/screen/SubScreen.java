package waichunas.frame.screen;

import java.awt.*;

/**
 * Created by Alec on 2/25/2017.
 */
public abstract class SubScreen {

    protected String name;
    protected Dimension size;

    public SubScreen(Dimension size){
        this.name = name;
        this.size = size;
    }

    public abstract void click(int x, int y);
    public abstract void update();
    public abstract void render(Graphics g);

}
