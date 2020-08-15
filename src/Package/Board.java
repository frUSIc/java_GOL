package Package;

import javafx.beans.property.Property;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.Arrays;

public class Board {
    private Rectangle[][] board;
    private int size;

    public Board(int size){
        this.size = size;
        this.board = new Rectangle[size][size];
        this.boardInit();
    }

    public void boardInit(){
        for (int i = 0; i<size ; i++){
            for (int j = 0; j<size; j++){
                board[i][j] = new Rectangle();
                board[i][j].setFill(Paint.valueOf("WHITE"));
            }
        }
    }

    public Paint cellState(int i, int j){
        return board[i][j].getFill();
    }

    public Property<Paint> cellProperty(int i, int j){
        return board[i][j].fillProperty();
    }

    public void setCell(int i, int j, Paint colour){
        board[i][j].setFill(colour);
    }

}
