package neat;

import java.io.*;

public class NeuralNetwork implements Serializable{
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

    public static double sigmoid(double weight) {
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
        for (int i = 0; i < this.hiddenLayer.neurons.size(); i++){
            sum += hiddenLayer.neurons.get(i).getWert() * hiddenLayer.neurons.get(i).getWeights().get(0);
        }
        outputLayer.neurons.get(0).setWert(sigmoid(sum));

        return outputLayer.neurons.get(0).getWert();
    }

    public static double randomGen() {
        return Math.random() * 2 - 1;
    }

    public static void saveNetwork(NeuralNetwork nn){
        try{
            FileOutputStream fos = new FileOutputStream("network.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(nn);
            oos.close();
        }catch(Exception e){
            e.printStackTrace();
        };
    }

    public static NeuralNetwork readNetwork(){
        try{
            FileInputStream fis = new FileInputStream("network.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            return (NeuralNetwork) ois.readObject();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Layer getInputLayer() {
        return inputLayer;
    }

    public void setInputLayer(Layer inputLayer) {
        this.inputLayer = inputLayer;
    }

    public Layer getHiddenLayer() {
        return hiddenLayer;
    }

    public void setHiddenLayer(Layer hiddenLayer) {
        this.hiddenLayer = hiddenLayer;
    }

    public Layer getOutputLayer() {
        return outputLayer;
    }

    public void setOutputLayer(Layer outputLayer) {
        this.outputLayer = outputLayer;
    }
}
