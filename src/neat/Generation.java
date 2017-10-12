package neat;

import java.util.ArrayList;

public class Generation {
    public ArrayList<Genom> genome;

    public Generation() {
        this.genome = new ArrayList<>();
    }

    public void addGenome(Genom genome) {
        for (int i = 0; i < this.genome.size(); i++) {
            if (Neuroevolution.fitnessSort < 0) {
                if (genome.fitness > this.genome.get(i).fitness) {
                    continue;
                }
            } else {
                if (genome.fitness < this.genome.get(i).fitness) {
                    continue;
                }
            }
            this.genome.add(i, genome);
        }
    }

    public ArrayList<Genom> breed(Genom g1, Genom g2, int nbChilds) {
        ArrayList<Genom> datas = new ArrayList<>();
        Genom data;
        for (int nb = 0; nb < nbChilds; nb++) {
            data = g1;
            for(Layer l: g2.network.layers){
                for(Neuron n: l.neurons){
                    for(double d: n.weights){
                        if (Math.random() <= 0.5){
                            data.network.layers.get(g2.network.layers.indexOf(l)).neurons.get(l.neurons.indexOf(n)).weights.set(n.weights.indexOf(d), d);
                        }
                    }
                }
            }

            for (Layer l: data.network.layers){
                for(Neuron n: l.neurons){
                    for(double d: n.weights){
                        if(Math.random() <= Neuroevolution.mutationsRate){
                            d += Math.random() * Neuroevolution.mutationRange * 2 - Neuroevolution.mutationRange;
                        }
                    }
                }
            }
            datas.add(data);
        }
        return datas;
    }

    public ArrayList<Genom> generateNewGeneration(){
        ArrayList<Genom> next = new ArrayList<>();
        for(int i = 0; i < Math.round(Neuroevolution.elitism * Neuroevolution.bevoelkerung); i++){
            if(next.size() < Neuroevolution.bevoelkerung){
                next.add(this.genome.get(i));
            }
        }
        for(int i = 0; i < Math.round(Neuroevolution.zufallVerhalten * Neuroevolution.bevoelkerung); i++){
            Genom nn = this.genome.get(0);
            for (Layer l: nn.network.layers){
                for(Neuron n: l.neurons){
                    for (int k = 0; k < n.weights.size(); k++){
                        n.weights.set(k, Neuroevolution.randomGen());
                    }
                }
            }
            if (next.size() < Neuroevolution.bevoelkerung){
                next.add(nn);
            }
        }
        int max = 0;
        while(true){
            for(int i = 0; i < max; i++){
                ArrayList<Genom> childs = breed(this.genome.get(i),this.genome.get(max), (Neuroevolution.newBreedChild > 0 ? Neuroevolution.newBreedChild : 1));
                next.addAll(childs);
                if(next.size() >= Neuroevolution.bevoelkerung){
                    return next;
                }
            }
            max++;
            if(max >= this.genome.size() - 1){
                max = 0;
            }
        }
    }
}