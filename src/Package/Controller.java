package Package;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.util.Duration;

public class Controller {
    public Button tick;
    public Button play;
    public GridPane grid;
    public ProgressIndicator progressSymbol;
    public CheckBox wrapCheck;
    public ChoiceBox<String> strategyChoice;
    public Button clear;
    public CheckBox eraseCheck;
    public CheckBox drawCheck;
    private Game game;
    private Timeline time;
    private BooleanProperty erase;
    private BooleanProperty draw;

    public Controller() {
        this.game = new Game(false);
        this.time = new Timeline(new KeyFrame(Duration.millis(150), e -> {
            game.boardIterate();
        }));
        this.time.setCycleCount(Animation.INDEFINITE);
        this.erase = new SimpleBooleanProperty(false);
        this.draw = new SimpleBooleanProperty(false);

    }

    public void initialize() {
        eraseCheck.selectedProperty().bindBidirectional(erase);
        drawCheck.selectedProperty().bindBidirectional(draw);
        wrapCheck.selectedProperty().bindBidirectional(game.WrapProperty());

        strategyChoice.setItems(game.getStrategyList());
        strategyChoice.setValue("GoL");
        strategyChoice.getSelectionModel().selectedItemProperty().addListener((
                observableValue, s, t1) -> game.changeStrategy(t1));

        Rectangle cell;
        for (int i = 0; i < game.size(); i++) {
            for (int j = 0; j < game.size(); j++) {
                cell = new Rectangle();
                cell.setHeight(600.0/game.size());
                cell.setWidth(600.0/game.size());
                cell.setFill(Paint.valueOf("WHITE"));
                cell.fillProperty().bindBidirectional(game.cellProperty(i,j));

                Rectangle finalCell = cell;
                cell.setOnMouseEntered(mouseEvent -> {
                    if (erase.get()) {
                        finalCell.setFill(Paint.valueOf("WHITE"));
                    } else if (draw.get()) {
                        finalCell.setFill(Paint.valueOf("BLACK"));
                    }
                });

                cell.setOnMouseClicked(mouseEvent -> {
                    if (finalCell.getFill()==Paint.valueOf("WHITE")){
                        finalCell.setFill(Paint.valueOf("BLACK"));
                    } else {
                        finalCell.setFill(Paint.valueOf("WHITE"));
                    }
                });
                grid.add(cell,i,j);
            }
        }
    }

    public void wrapPress() {
        game.setWrapOption();
        if (wrapCheck.isSelected()) {
            wrapCheck.setSelected(false);
        } else {
            wrapCheck.setSelected(true);
        }
    }

    public void tickPress() {
        game.boardIterate();
    }

    public void playPress() {
        if (play.getText().equals("Play")) {
            time.play();
            play.setText("Stop");
            progressSymbol.setVisible(true);
        } else {
            time.stop();
            play.setText("Play");
            progressSymbol.setVisible(false);
        }
    }

    public void clearPress() {
        game.reset();
    }

    public void erasePress() {
        if (eraseCheck.isSelected()) {
            disableDraw();
        }
    }

    private void disableErase(){
        eraseCheck.setSelected(false);
    }

    public void drawPress() {
        if (drawCheck.isSelected()) {
            disableErase();
        }
    }

    private void disableDraw(){
        drawCheck.setSelected(false);
    }
}
