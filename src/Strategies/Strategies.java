package Strategies;

import javafx.scene.paint.Paint;

public interface Strategies {
    Paint determineLive(int neighbours);
    Paint determineDead(int neighbours);
}