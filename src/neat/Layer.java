package neat;

import java.util.ArrayList;

public class Layer {
    public ArrayList<Neuron> neurons;
    public String id;

    public Layer(String id){
        this.id = id;
    }

    public void populate(int neurons, int synapse){
        this.neurons = new ArrayList<>();
        for(int i = 0; i < neurons; i++){
            Neuron n = new Neuron();
            n.populate(synapse);
            this.neurons.add(n);
        }
    }

}
