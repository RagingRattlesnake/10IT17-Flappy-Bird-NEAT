package neat;

import java.util.ArrayList;

public class Neuron {
    private double wert;
    private ArrayList<Double> weights;

    public Neuron(){
        this.wert = 0;
        this.weights = new ArrayList<>();
    }

    public void populate(int synapse){
        weights = new ArrayList<>();
        for(int i = 0; i < synapse; i++){
            weights.add(NeuralNetwork.randomGen());
        }
    }

    public double getWert() {
        return wert;
    }

    public void setWert(double wert) {
        this.wert = wert;
    }

    public ArrayList<Double> getWeights() {
        return weights;
    }

    public void setWeights(ArrayList<Double> weights) {
        this.weights = weights;
    }

}