package waichunas.frame.screen;

import java.awt.*;

/**
 * Created by Alec on 2/25/2017.
 */
public abstract class SubScreen {

    private String name;
    private Dimension size;
    

    public abstract void click(int x, int y);
    public abstract void update();
    public abstract void render();

}
