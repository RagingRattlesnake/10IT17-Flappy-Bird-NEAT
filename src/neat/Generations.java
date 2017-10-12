package neat;

import java.util.ArrayList;

public class Generations {
    public ArrayList<Generation> generations;
    public Generation currentGeneration;

    public Generations(){
        generations = new ArrayList<>();
        currentGeneration = new Generation();
    }

    public ArrayList<Generation> firstGeneration(){
        ArrayList<Generation> out = new ArrayList<>();
        for(int i = 0; i < Neuroevolution.bevoelkerung; i++){
            NeuralNetwork nn = new NeuralNetwork();
            nn.perceptronGeneration(2, new int[2], 1);
        }
        this.generations.add(new Generation());
        return out;
    }

    public ArrayList<Generation> nextGeneration() throws IllegalArgumentException{
        if(generations.size() == 0){
            throw new IllegalArgumentException("Need to create first Generation");
        }
        Generation g = new Generation();
        g.genome = generations.get(generations.size()-1).generateNewGeneration();
        generations.add(new Generation());
        ArrayList<Generation> gen = new ArrayList<>();
        gen.add(g);
        return gen;
    }

    public void addGenome(Genom genome)throws IllegalArgumentException{
        if(generations.size() == 0){
            throw new IllegalArgumentException("There are no Generations to add Genomes");
        }
        generations.get(generations.size()-1).addGenome(genome);
    }
}
