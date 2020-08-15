package Strategies;

import javafx.scene.paint.Paint;

public class Replicator implements Strategies {
    @Override
    public Paint determineLive(int neighbours) {
        if(neighbours%2!=0){
            return Paint.valueOf("WHITE");
        } else {
            return Paint.valueOf("BLACK");
        }
    }

    @Override
    public Paint determineDead(int neighbours) {
        if(neighbours%2!=0){
            return Paint.valueOf("BLACK");
        } else {
            return Paint.valueOf("WHITE");
        }
    }
}
