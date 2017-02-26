package waichunas.frame;

import waichunas.frame.screen.MainScreen;

import java.awt.*;

/**
 * Created by Alec on 2/25/2017.
 */
public class MainFrame extends Frame {

    private static final String NAME = "Conway's Game of Life";

    public MainFrame(){
        super(NAME);

        this.addWindowListener(new FrameEvents());
        MainScreen ms = new MainScreen(900, 600);
        Engine engine = new Engine(ms);

        add(ms, BorderLayout.CENTER);
        pack();
        setResizable(false);
        setVisible(true);

        engine.start();
    }
}
