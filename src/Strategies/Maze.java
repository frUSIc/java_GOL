package Strategies;

import javafx.scene.paint.Paint;

public class Maze implements Strategies {
    @Override
    public Paint determineLive(int neighbours) {
        if(neighbours<1 || neighbours>5){
            return Paint.valueOf("WHITE");
        } else {
            return Paint.valueOf("BLACK");
        }
    }

    @Override
    public Paint determineDead(int neighbours) {
        if(neighbours==3){
            return Paint.valueOf("BLACK");
        } else {
            return Paint.valueOf("WHITE");
        }
    }
}
