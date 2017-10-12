package neat;

import java.util.ArrayList;

public class Layer {
    public ArrayList<Neuron> neurons;
    public int id = 0;

    public Layer(int i){
        this.id = i;
    }

    public void populate(int newBreedNeurons, int newBreedInputs){
        this.neurons = new ArrayList<>();
        for(int i = 0; i < newBreedNeurons; i++){
            Neuron n = new Neuron();
            n.populate(newBreedInputs);
            this.neurons.add(n);
        }
    }

}
