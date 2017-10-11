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
        }
        return datas;
    }
}

