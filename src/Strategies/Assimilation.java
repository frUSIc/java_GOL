package Strategies;

import javafx.scene.paint.Paint;

public class Assimilation implements Strategies {
    @Override
    public Paint determineLive(int neighbours) {
        if(neighbours<4 || neighbours>7){
            return Paint.valueOf("WHITE");
        } else {
            return Paint.valueOf("BLACK");
        }
    }

    @Override
    public Paint determineDead(int neighbours) {
        if(neighbours>2 && neighbours<6){
            return Paint.valueOf("BLACK");
        } else {
            return Paint.valueOf("WHITE");
        }
    }
}
