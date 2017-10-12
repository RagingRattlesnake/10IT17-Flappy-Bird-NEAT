package neat;

import java.util.ArrayList;
import java.util.HashMap;

public class NeuralNetwork {
    public ArrayList<Layer> layers = new ArrayList<>();
    public NeuralNetwork(){
        layers = new ArrayList<>();
    }

    public void perceptronGeneration(int input, int[] hiddens, int output) {
        int index = 0;
        int previousNeurons = 0;
        Layer layer = new Layer(index);
        layer.populate(input, previousNeurons);
        previousNeurons = input;
        layers.add(layer);
        index++;
        for (int i : hiddens) {
            layer = new Layer(index);
            layer.populate(hiddens[i], previousNeurons);
            previousNeurons = hiddens[i];
            layers.add(layer);
            index++;
        }
        layer = new Layer(index);
        layer.populate(output, previousNeurons);
        layers.add(layer);
    }

    public HashMap<String, ArrayList<Double>> copyNetwork() {
        HashMap<String, ArrayList<Double>> data = new HashMap<>();
        data.put("neurons", new ArrayList<>());
        data.put("weights", new ArrayList<>());
        for (Layer i : layers) {
            data.get("neurons").add((double) i.neurons.size());
            for (Neuron j : i.neurons) {
                for (double k : j.weights) {
                    data.get("weights").add(k);
                }

            }
        }
        return data;
    }

    public void setNetwork(Layer net) {
        int previousNeurons = 0;
        int index = 0;
        int indexWeights = 0;
        Layer layer = null;
        layers = new ArrayList<>();
        for (Neuron j : net.neurons) {
            layer = new Layer(index);
            layer.populate(net.neurons.size(), previousNeurons);
            for (Neuron k : layer.neurons) {
                for (double m : k.weights) {
                    m = j.weights.get(indexWeights);
                    indexWeights++;
                }
            }
            previousNeurons = net.neurons.size();
            index++;
            layers.add(layer);
        }

    }

    public ArrayList<Double> compute(int[] inputs) {
        for (int i : inputs) {
            if (layers.get(0) != null && layers.get(0).neurons.get(i) != null) {
                layers.get(0).neurons.get(i).wert = i;
            }
        }

        Layer prevLayer = layers.get(0);
        for(int i = 1; i < layers.size(); i++){
            for(Neuron j: layers.get(i).neurons){
                double sum = 0;
                for(Neuron k: prevLayer.neurons){
                    for (int m = 0; m < j.weights.size(); m++){
                        sum += k.wert * j.weights.get(m);
                    }
                }
                j.wert = Neuroevolution.activation(sum);
            }
            prevLayer = layers.get(i);
        }
        ArrayList<Double> out = new ArrayList<>();
        Layer lastLayer = layers.get(layers.size()-1);
        for (Neuron i: lastLayer.neurons){
            out.add(i.wert);
        }
        return out;
    }
}
