package neat;

import game.Bird;

import java.util.ArrayList;

public class Generations {
    private static ArrayList<Bird> prevGeneration;
    private static ArrayList<Bird> currGeneration;

    public static ArrayList<Bird> getPrevGeneration() {
        return prevGeneration;
    }

    public static void setPrevGeneration(ArrayList<Bird> prevGeneration) {
        Generations.prevGeneration = prevGeneration;
    }

    public static ArrayList<Bird> getCurrGeneration() {
        return currGeneration;
    }

    public static void setCurrGeneration(ArrayList<Bird> currGeneration) {
        Generations.currGeneration = currGeneration;
    }
}
