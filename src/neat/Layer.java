package neat;

import java.util.ArrayList;

class Layer {
    ArrayList<Neuron> neurons;
    private String id;

    Layer(String id) {
        this.id = id;
    }

    void populate(int neurons, int synapse) {
        this.neurons = new ArrayList<>();
        for (int i = 0; i < neurons; i++) {
            Neuron n = new Neuron();
            n.populate(synapse);
            this.neurons.add(n);
        }
    }

}
