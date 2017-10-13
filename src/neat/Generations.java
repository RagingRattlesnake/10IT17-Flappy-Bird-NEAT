package neat;

public class Generations {
    private static Generation prevGeneration;
    private static Generation currGeneration;

    public static Generation getPrevGeneration() {
        return prevGeneration;
    }

    public static void setPrevGeneration(Generation prevGeneration) {
        Generations.prevGeneration = prevGeneration;
    }

    public static Generation getCurrGeneration() {
        return currGeneration;
    }

    public static void setCurrGeneration(Generation currGeneration) {
        Generations.currGeneration = currGeneration;
    }
}
