package neat;

import java.util.ArrayList;

public class Neuron {
    public double wert;
    public ArrayList<Double> weights;

    public Neuron(){
        this.wert = 0;
        this.weights = new ArrayList<>();
    }

    public void populate(int nb){
        weights = new ArrayList<>();
        for(int i = 0; i < nb; i++){
            weights.add(Neuroevolution.randomGen());
        }
    }
}