package neat;

import java.util.HashMap;

public class Neuroevolution {
    public Object[] network = new Object[]{1, new Object[]{1}, 1};
    public int bevoelkerung = 50;
    public double elitism = 0.2;
    public double zufallVerhalten = 0.2;
    public double mutationsRate = 0.1;
    public double mitationRange = 0.5;
    public int latestGeneration = 0;
    public boolean lowLatestGeneration = false;
    public static int fitnessSort = -1;
    public int newBreedChild = 1;

    public Neuroevolution(){

    }

    public static double activation(double weight){
        double ap = (-weight)/1;
        return (1/(1+Math.exp(ap)));
    }

    public static double randomGen(){
        return Math.random() * 2 - 1;
    }


}
