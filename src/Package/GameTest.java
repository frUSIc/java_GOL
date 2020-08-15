package Package;

import javafx.scene.paint.Paint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Game game;
    private Game gameWrap;

    @BeforeEach
    void setUp() {
        game = new Game(false);
        gameWrap = new Game(true);
    }

    @Test
    void testBoardInit() {
        for (int i = 0; i < game.size(); i++){
            for (int j = 0; j < game.size(); j++){
                assertEquals(game.cellState(i,j),Paint.valueOf("WHITE"));
            }
        }
    }

    @Test
    void testBoardIterate() {
        game.setCell(15,15,Paint.valueOf("BLACK"));
        game.setCell(15,16,Paint.valueOf("BLACK"));
        game.setCell(15,14,Paint.valueOf("BLACK"));
        System.out.println(game.toString());
        game.boardIterate();
        System.out.println(game.toString());
        assertTrue(game.cellState(14,15)==Paint.valueOf("BLACK")&&
                game.cellState(15,15)==Paint.valueOf("BLACK")&&
                game.cellState(16,15)==Paint.valueOf("BLACK"));
        assertFalse(game.cellState(15,16)==Paint.valueOf("BLACK")&&
                game.cellState(15,14)==Paint.valueOf("BLACK"));
    }

    @Test
    void testBoardWrap() {
        gameWrap.setCell(14,0,Paint.valueOf("BLACK"));
        gameWrap.setCell(15,0,Paint.valueOf("BLACK"));
        gameWrap.setCell(16,0,Paint.valueOf("BLACK"));
        System.out.println(gameWrap.toString());
        gameWrap.boardIterate();
        System.out.println(gameWrap.toString());
        assertTrue(gameWrap.cellState(15,29)==Paint.valueOf("BLACK")&&
                gameWrap.cellState(15,0)==Paint.valueOf("BLACK")&&
                gameWrap.cellState(15,1)==Paint.valueOf("BLACK"));
        assertFalse(gameWrap.cellState(14,0)==Paint.valueOf("BLACK") &&
                gameWrap.cellState(16,0)==Paint.valueOf("BLACK"));
        gameWrap.boardIterate();
        System.out.println(gameWrap.toString());
        assertFalse(gameWrap.cellState(15,29)==Paint.valueOf("BLACK") &&
                gameWrap.cellState(15,1)==Paint.valueOf("BLACK"));
        assertTrue(gameWrap.cellState(14,0)==Paint.valueOf("BLACK")&&
                gameWrap.cellState(16,0)==Paint.valueOf("BLACK")&&
                gameWrap.cellState(15,0)==Paint.valueOf("BLACK"));

        game.setCell(14,0,Paint.valueOf("BLACK"));
        game.setCell(15,0,Paint.valueOf("BLACK"));
        game.setCell(16,0,Paint.valueOf("BLACK"));
        System.out.println(game.toString());
        game.boardIterate();
        System.out.println(game.toString());
        game.boardIterate();
        System.out.println(game.toString());
        assertFalse(game.cellState(14,0)==Paint.valueOf("BLACK")&&
                game.cellState(16,0)==Paint.valueOf("BLACK")&&
                game.cellState(15,29)==Paint.valueOf("BLACK")&&
                game.cellState(15,0)==Paint.valueOf("BLACK")&&
                game.cellState(15,1)==Paint.valueOf("BLACK"));
    }
}