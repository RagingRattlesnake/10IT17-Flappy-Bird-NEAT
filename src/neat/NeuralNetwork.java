package neat;

public class NeuralNetwork {
    private Layer inputLayer;
    private Layer hiddenLayer;
    private Layer outputLayer;

    public NeuralNetwork() {
        this.inputLayer = new Layer();
        inputLayer.populate(3, 10);
        inputLayer.neurons.get(2).setWert(1);
        this.hiddenLayer = new Layer();
        hiddenLayer.populate(11, 1);
        hiddenLayer.neurons.get(10).setWert(1);
        this.outputLayer = new Layer();
        outputLayer.populate(1, 0);
    }

    private static double sigmoid(double weight) {
        double ap = (-weight) / 1;
        return (1 / (1 + Math.exp(ap)));
    }

    private static double activate_ReLU(double weight){
        if(weight <= 0 ) return weight*0.01;
        else return weight;
    }

    public double activate(int heightDiff, int distance) {
        if (this.inputLayer != null) {
            this.inputLayer.neurons.get(0).setWert(heightDiff);
            this.inputLayer.neurons.get(1).setWert(distance);


            double sum = 0;
            for (int i = 0; i < this.inputLayer.neurons.get(0).getWeights().size(); i++) {
                hiddenLayer.neurons.get(i).setWert(activate_ReLU(this.inputLayer.neurons.get(0).getWert() * this.inputLayer.neurons.get(0).getWeights().get(i) +
                        this.inputLayer.neurons.get(1).getWert() * this.inputLayer.neurons.get(1).getWeights().get(i)));
            }
            for (int i = 0; i < this.hiddenLayer.neurons.size(); i++) {
                sum += hiddenLayer.neurons.get(i).getWert() * hiddenLayer.neurons.get(i).getWeights().get(0);
            }
            outputLayer.neurons.get(0).setWert(sigmoid(sum));
        }
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
