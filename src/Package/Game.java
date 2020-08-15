package Package;

import Strategies.Strategies;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Paint;

public class Game {
    private BooleanProperty wrapOption;
    private Board board;
    private int size;
    Strategies strategy;

    public Game(boolean wrapOption){
        this.size = 50;
        this.board = new Board(this.size);
        this.wrapOption = new SimpleBooleanProperty(wrapOption);
        this.strategy = Factory.createStrategy("");
    }

    public void reset(){
        Board newBoard = new Board(size);
        for (int i = 0; i<size ; i++){
            for (int j = 0; j<size; j++){
                newBoard.setCell(i,j,Paint.valueOf("WHITE"));
            }
        }
        changeBoard(newBoard, board);
    }

    public BooleanProperty WrapProperty(){
        return wrapOption;
    }

    public Paint cellState(int i, int j){
        return (board.cellState(i, j));
    }

    public Property<Paint> cellProperty(int i, int j){
        return board.cellProperty(i,j);
    }

    public void setCell(int i, int j, Paint colour){
        board.setCell(i, j, colour);
    }

    public void boardIterate(){
        Board newBoard = new Board(size);
        for (int i = 0; i<size ; i++){
            for (int j = 0; j<size; j++){
                if (board.cellState(i,j)==Paint.valueOf("BLACK")){
                    newBoard.setCell(i,j,strategy.determineLive(liveNeighbours(i,j)));
                } else {
                    newBoard.setCell(i,j,strategy.determineDead(liveNeighbours(i,j)));
                }
            }
        }
        changeBoard(newBoard, board);
    }

    private void changeBoard(Board copy, Board current){
        for (int i = 0; i<size ; i++){
            for (int j = 0; j<size; j++){
                if (copy.cellState(i,j)==Paint.valueOf("BLACK")){
                    current.setCell(i,j,Paint.valueOf("BLACK"));
                } else {
                    current.setCell(i,j,Paint.valueOf("WHITE"));
                }
            }
        }
    }

    public int liveNeighbours(int x, int y){
        int count = 0;
        if (wrapOption.get()){
            for (int i = -1; i < 2 ; i++) {
                for (int j = -1; j < 2; j++) {
                    if (board.cellState(calcPos(x + i), calcPos(y + j))
                            ==Paint.valueOf("BLACK")) {
                        count++;
                    }
                }
            }
        } else {
            for (int i = -1; i<2 ; i++){
                for (int j = -1; j<2; j++){
                    if (x+i >= 0 && y+j >= 0 && x+i < size && y+j <size){
                        if (board.cellState(x+i,y+j)
                                ==Paint.valueOf("BLACK")){
                            count++;
                        }
                    }
                }
            }
        }
        if (board.cellState(x,y)==Paint.valueOf("BLACK")){
            count--;
        }
        return count;
    }

    public int size(){
        return size;
    }

    public int calcPos(int a){
        if (a < 0){
            return (size-1);
        }
        return a%size;
    }

    public void setWrapOption(){
        if (wrapOption.get()){
            wrapOption.set(false);
        } else {
            wrapOption.set(true);
        }
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i<size ; i++){
            for (int j = 0; j<size; j++){
                if (board.cellState(i,j)==Paint.valueOf("BLACK")){
                    text.append("+");
                } else {
                    text.append("-");
                }
            }
            text.append("\n");
        }
        return text.toString();
    }

    public ObservableList<String> getStrategyList() {
        return FXCollections.observableArrayList(
                "GoL", "Assimilation", "Diamoeba", "Maze", "Replicator");
    }

    public void changeStrategy(String strategyName){
        this.strategy = Factory.createStrategy(strategyName);
    }
}
