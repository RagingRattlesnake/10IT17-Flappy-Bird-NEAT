package neat;

import java.util.ArrayList;

public class Neuron {
    private ArrayList<Synapse> inputs;
    private ArrayList<Synapse> outputs;
    private double weightSum;

    public Neuron() {
        weightSum = 0.7;
        inputs = new ArrayList<>();
    }


    public double activate() {
        weightSum = 0;
        for (Synapse synapse : inputs) {
            weightSum += synapse.getWeight() * synapse.getBaseNeuron().activate();
        }
        return sigmoid(weightSum);
    }

    private static double sigmoid(double weight) {
        return 1.0 / (1 + Math.exp(-1.0 * weight));
    }


}
