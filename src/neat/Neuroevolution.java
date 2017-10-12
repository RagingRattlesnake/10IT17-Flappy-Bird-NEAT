package neat;

import java.util.ArrayList;

public class Neuroevolution {
    public static Object[] network = new Object[]{1, new Object[]{1}, 1};
    public static int bevoelkerung = 50;
    public static double elitism = 0.2;
    public static double zufallVerhalten = 0.2;
    public static double mutationsRate = 0.1;
    public static double mutationRange = 0.5;
    public static int latestGeneration = 0;
    public static boolean lowLatestGeneration = false;
    public static int fitnessSort = -1;
    public static int newBreedChild = 1;
    public Neuroevolution self;
    public Generations generations = new Generations();

    public Neuroevolution() {
        self = this;
    }

    public static double activation(double weight) {
        double ap = (-weight) / 1;
        return (1 / (1 + Math.exp(ap)));
    }

    public static double randomGen() {
        return Math.random() * 2 - 1;
    }

    public void restart() {
        generations = new Generations();
    }

    public ArrayList<NeuralNetwork> nextGeneration() {
        ArrayList<Generation> network;
        if (generations.generations.size() == 0) {
            network = generations.firstGeneration();
        } else {
            network = generations.nextGeneration();
        }
        ArrayList<NeuralNetwork> nns = new ArrayList<>();
        for (Generation g : network) {
            for (Genom ge : g.genome) {
                NeuralNetwork nn = new NeuralNetwork();
                for (Layer l : ge.network.layers) {
                    nn.setNetwork(l);
                    nns.add(nn);
                }
            }
        }
        if (Neuroevolution.lowLatestGeneration) {
            if (generations.generations.size() >= 2) {
                ArrayList<Genom> genomes = generations.generations.get(generations.generations.size() - 2).genome;
                for (Genom g : genomes) {
                    g.network = null;
                }
            }
        }
        if (Neuroevolution.latestGeneration != -1) {
            if (generations.generations.size() > Neuroevolution.latestGeneration + 1) {
                generations.generations = new ArrayList<>();
            }
        }
        return nns;
    }

    public void setFitness(NeuralNetwork network, int fitness) {
        generations.addGenome(new Genom(fitness, network));
    }

}
