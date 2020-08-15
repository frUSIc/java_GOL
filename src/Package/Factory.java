package Package;

import Strategies.*;

abstract public class Factory {
    public static Strategies createStrategy(String strategyName){
        switch (strategyName) {
            case "Assimilation":
                return new Assimilation();
            case "Diamoeba":
                return new Diamoeba();
            case "Maze":
                return new Maze();
            case "Replicator":
                return new Replicator();
            default:
                return new GoL();
        }
    }
}
