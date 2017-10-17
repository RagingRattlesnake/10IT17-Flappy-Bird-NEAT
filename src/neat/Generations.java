package neat;

import game.Bird;

import java.util.ArrayList;

public class Generations {
    private static ArrayList<Bird> prevGeneration;

    static ArrayList<Bird> getPrevGeneration() {
        return prevGeneration;
    }

    public static void setPrevGeneration(ArrayList<Bird> prevGeneration) {
        Generations.prevGeneration = prevGeneration;
    }

}
