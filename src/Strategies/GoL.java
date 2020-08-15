package Strategies;

import javafx.scene.paint.Paint;

public class GoL implements Strategies {


    @Override
    public Paint determineLive(int neighbours) {
        if(neighbours<2 || neighbours>3){
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
