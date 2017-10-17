package neat;

import java.io.*;

public class NeuralNetwork implements Serializable {
    private Layer inputLayer;
    private Layer hiddenLayer;
    private Layer outputLayer;

    public NeuralNetwork() {
        this.inputLayer = new Layer("input");
        inputLayer.populate(2, 10);
        this.hiddenLayer = new Layer("hidden");
        hiddenLayer.populate(10, 1);
        this.outputLayer = new Layer("output");
        outputLayer.populate(1, 0);
    }

    private static double sigmoid(double weight) {
        double ap = (-weight) / 1;
        return (1 / (1 + Math.exp(ap)));
    }

    public double activate(int heightDiff, int distance) {
        if (this.inputLayer != null) {
            this.inputLayer.neurons.get(0).setWert(heightDiff);
            this.inputLayer.neurons.get(1).setWert(distance);
        }

        double sum = 0;
        for (int i = 0; i < this.inputLayer.neurons.get(0).getWeights().size(); i++) {
            hiddenLayer.neurons.get(i).setWert(sigmoid(this.inputLayer.neurons.get(0).getWert() * this.inputLayer.neurons.get(0).getWeights().get(i) +
                    this.inputLayer.neurons.get(1).getWert() * this.inputLayer.neurons.get(1).getWeights().get(i)));
        }
        for (int i = 0; i < this.hiddenLayer.neurons.size(); i++) {
            sum += hiddenLayer.neurons.get(i).getWert() * hiddenLayer.neurons.get(i).getWeights().get(0);
        }
        outputLayer.neurons.get(0).setWert(sigmoid(sum));

        return outputLayer.neurons.get(0).getWert();
    }

    static double randomGen() {
        return Math.random() * 2 - 1;
    }

    Layer getInputLayer() {
        return inputLayer;
    }

    Layer getHiddenLayer() {
        return hiddenLayer;
    }
}
