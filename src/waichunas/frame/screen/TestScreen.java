package waichunas.frame.screen;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * Created by Alec on 2/25/2017.
 */
public class TestScreen extends SubScreen{

    private int tick = 0, max_ticks = 20;
    private BufferedImage board;
    private int[] pixels;
    private int[] boardPlay;

    private int[] glider1 = {1, 1, 0,
                            1, 0, 1,
                            1, 0, 0};

    private int[] glider2 = {1, 1, 1,
                            1, 0, 0,
                            0, 1, 0};

    private int[] diamond = {0, 1, 1,
                            1, 0, 1,
                            1, 1, 0};

    private int[] square = {1, 1, 0,
                            1, 1, 0,
                            0, 0, 0};

    private int[] line = {0 ,0 ,0,
                          1, 1, 1,
                            0, 0, 0};

    public TestScreen(Dimension size) {
        super(size);
        board = new BufferedImage(200, 150, BufferedImage.TYPE_INT_RGB);
        //gets direct memory link of the pixels associated with the buffered image
        pixels = ((DataBufferInt) board.getRaster().getDataBuffer()).getData();
        boardPlay = new int[size.width * size.height];

       // pasteStructure(glider1, 200, 200);
        //pasteStructure(diamond, 192, 198);
        //pasteStructure(square, 184, 198);
       // pasteStructure(diamond, 204, 196);
        //pasteStructure(square, 214, 196);
        //pasteStructure(glider1, 215, 202);
        //pasteStructure(glider2, 206, 206);

        pasteStructure(line, 100, 100);
        pasteStructure(line, 103, 100);
        pasteStructure(line, 106, 100);
        pasteStructure(line, 107, 100);

        updatePixels();
    }

    public int getTile(int x, int y){
        return boardPlay[y * board.getWidth() + x];
    }

    public void setTile(int x, int y, int deadOrAlive){
        int tile = getTile(x, y);
        boardPlay[y * board.getWidth() + x] = deadOrAlive;
    }

    public void pasteStructure(int[] structure, int x, int y){
        for(int xx = 0; xx < 3; xx++){
            for(int yy = 0; yy < 3; yy++){
                if(structure[yy * 3 + xx] == 1)
                    setTile(x + xx, y + yy, 1);
            }
        }
    }

    public void updateStructure(){
        for(int x = 0; x < board.getWidth(); x++){
            for(int y = 0; y < board.getHeight(); y++){
                if(getTile(x, y) == 1){
                    createLife(x, y);
                }
            }
        }

        for(int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                if(getTile(x, y) == 1) {
                    int tilesAround = -1;//exclude itself
                    for (int xx = -1; xx <= 1; xx++) {
                        for (int yy = -1; yy <= 1; yy++) {
                            int tile = getTile(xx + x, yy + y);
                            tilesAround += (tile == 1 || tile == -1) ? 1 : 0 ;
                        }
                    }
                    if (tilesAround >= 4 || tilesAround <= 1) setTile(x, y, -1);
                }
            }
        }

        for(int i = 0; i < boardPlay.length; i++)
            if(boardPlay[i] == 2) boardPlay[i] = 1;
            else if(boardPlay[i] == -1) boardPlay[i] = 0;
    }

    public void createLife(int x, int y){
        for(int xx = -1; xx <= 1; xx++){
            for(int yy = -1; yy <= 1; yy++){
                if(getTile(x + xx, y + yy) == 0){
                    int tilesAround = 0;
                    for(int xxx = -1; xxx <= 1; xxx++){
                        for(int yyy = -1; yyy <= 1; yyy++){
                            int tile = getTile(x + xx + xxx, y + yy + yyy);
                            tilesAround += (tile == 1) ? 1 : 0;
                        }
                    }

                    if(tilesAround == 3)
                        setTile(x + xx, y + yy, 2);
                }
            }
        }
    }

    @Override
    public void click(int x, int y) {

    }

    public void updatePixels(){
        for(int i = 0; i < pixels.length; i++)
            if(boardPlay[i] == 1)
                pixels[i] = 0xFFFFFF;
            else
                pixels[i] = 0;
    }

    @Override
    public void update() {
        tick++;
        if(tick % max_ticks == 0){
            //update tiles
            updateStructure();
            updatePixels();
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(board, 0, 0, size.width, size.height, null);
    }
}
