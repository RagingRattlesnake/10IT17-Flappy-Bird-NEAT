package neat;

import java.util.ArrayList;

public class Neuron {
    private double wert;
    private ArrayList<Double> weights;

    Neuron() {
        this.wert = 0;
        this.weights = new ArrayList<>();
    }

    void populate(int synapse) {
        weights = new ArrayList<>();
        for (int i = 0; i < synapse; i++) {
            weights.add(NeuralNetwork.randomGen());
        }
    }

    double getWert() {
        return wert;
    }

    void setWert(double wert) {
        this.wert = wert;
    }

    ArrayList<Double> getWeights() {
        return weights;
    }

}