package Strategies;

import javafx.scene.paint.Paint;

public class Diamoeba implements Strategies {
    @Override
    public Paint determineLive(int neighbours) {
        if(neighbours<5 || neighbours>8){
            return Paint.valueOf("WHITE");
        } else {
            return Paint.valueOf("BLACK");
        }
    }

    @Override
    public Paint determineDead(int neighbours) {
        if(neighbours>4 && neighbours<9 || neighbours==3){
            return Paint.valueOf("BLACK");
        } else {
            return Paint.valueOf("WHITE");
        }
    }
}
