package waichunas.frame;

import waichunas.frame.screen.MainScreen;

/**
 * Created by Alec on 2/25/2017.
 */
public class Engine {

    private MainScreen mainScreen;
    private boolean isRunning = false;

    public Engine(MainScreen mainScreen){
        this.mainScreen = mainScreen;
    }

    public void start(){
        isRunning = true;
        run();
    }

    public void stop(){
        isRunning = false;
    }

    public void run(){
        long timer = System.nanoTime();
        double second = 1000000000/60D;

        while(isRunning){
            long now = System.nanoTime();
            boolean render = false;
            if((now - timer)/second >= 1){
                mainScreen.update();
                render = true;
            }
            if(render)
                mainScreen.render();
        }
    }

}
