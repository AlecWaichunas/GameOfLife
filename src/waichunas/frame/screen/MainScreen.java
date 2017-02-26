package waichunas.frame.screen;

import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Created by Alec on 2/25/2017.
 */
public class MainScreen extends Canvas {

    private Dimension size;

    public MainScreen(int width, int height){
        size = new Dimension(width, height);
        setPreferredSize(size);
        setMinimumSize(size);
    }

    public void update(){

    }

    public void render(){
        //gets the screens buffer so it paints the screen as
        //the graphics instead of a white rectangle
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            //creates the buffer if there isn't one
            createBufferStrategy(2);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, size.width, size.height);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 100, 100);

        bs.show();//show the buffer
        bs.dispose();//empty it from memory
    }

}
