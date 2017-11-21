package neat;

import game.Bird;
import game.Settings;

import java.util.ArrayList;
import java.util.Comparator;

public class Generation {
    private static ArrayList<Bird> birds;

    public Generation() {
        if (birds != null) {
            Generations.setPrevGeneration(birds);
        }
        birds = generateNewGeneration();
    }

    private static void sortBirdsFitnessDesc() {
        birds.sort(new Comparator<Bird>() {
            @Override
            public int compare(Bird o1, Bird o2) {
                return -(o1.getFitness()-o2.getFitness());
            }
        });
    }

    public static ArrayList<Bird> generateNewGeneration() {
        Settings.ANZAHL_VOEGEL = Settings.POPULATION;
        ArrayList<Bird> birdNew = new ArrayList<>();
        if (Generations.getPrevGeneration() == null) {
            for (int i = 0; i < Settings.POPULATION; i++) {
                birdNew.add(new Bird());
            }
            return birdNew;
        }
        sortBirdsFitnessDesc();
        for (int i = 0; i < Math.round(Settings.ELITISM * Settings.POPULATION); i++) {
            if (birdNew.size() < Settings.POPULATION) {
                birdNew.add(new Bird(birds.get(i).getNetwork()));
            }
        }

        for (int i = 0; i < Math.round(Settings.ZUFALL_VERHALTEN * Settings.POPULATION); i++) {
            birdNew.add(new Bird());
        }

        int totalFitness = 0;
        for(Bird b: birds){
            totalFitness += b.getFitness();
        }
        for(Bird b: birds){
            b.setProbability(b.getFitness()/(double)totalFitness);
        }
        for(int i = 0; i < birds.size(); i++){
            if(i == 0){
                birds.get(i).setLowProb(0);
                birds.get(i).setHighProb(birds.get(i).getProbability());
                continue;
            }
            birds.get(i).setLowProb(birds.get(i-1).getHighProb());
            birds.get(i).setHighProb(birds.get(i-1).getHighProb() + birds.get(i).getProbability());
        }
        ArrayList<Bird> birdParent = new ArrayList<>();
        while(birdParent.size() < 2){
            double random = Math.random();
            for(Bird b: birds){
                if(random >= b.getLowProb() && random <= b.getHighProb()){
                    birdParent.add(b);
                }
            }
        }
        while (birdNew.size() < Settings.POPULATION) {
            Bird childBird = breed(birdParent.get(0), birdParent.get(1));
            birdNew.add(new Bird(childBird.getNetwork()));
        }
        return birdNew;
    }

    private static Bird breed(Bird bird1, Bird bird2) {

        for (int i = 0; i < bird1.getNetwork().getInputLayer().neurons.get(0).getWeights().size(); i++) {
            if (Math.random() <= Settings.CROSSOVER_RATE) {
                bird1.getNetwork().getInputLayer().neurons.get(0).getWeights().set(i, bird2.getNetwork().getInputLayer().neurons.get(0).getWeights().get(i));
            }
            if (Math.random() <= Settings.CROSSOVER_RATE) {
                bird1.getNetwork().getInputLayer().neurons.get(1).getWeights().set(i, bird2.getNetwork().getInputLayer().neurons.get(1).getWeights().get(i));
            }
        }
        for (int i = 0; i < bird1.getNetwork().getHiddenLayer().neurons.size(); i++) {
            if (Math.random() <= Settings.CROSSOVER_RATE) {
                bird1.getNetwork().getHiddenLayer().neurons.get(i).getWeights().set(0, bird2.getNetwork().getHiddenLayer().neurons.get(i).getWeights().get(0));
            }
        }

        for (int i = 0; i < bird1.getNetwork().getInputLayer().neurons.get(0).getWeights().size(); i++) {
            if (Math.random() <= Settings.MUTATIONS_RATE) {
                bird1.getNetwork().getInputLayer().neurons.get(0).getWeights().set(i, bird1.getNetwork().getInputLayer().neurons.get(0).getWeights().get(i) + Math.random() * Settings.MUTATIONS_RANGE * 2 - Settings.MUTATIONS_RANGE);
            }
            if (Math.random() <= Settings.MUTATIONS_RATE) {
                bird1.getNetwork().getInputLayer().neurons.get(1).getWeights().set(i, bird1.getNetwork().getInputLayer().neurons.get(1).getWeights().get(i) + Math.random() * Settings.MUTATIONS_RANGE * 2 - Settings.MUTATIONS_RANGE);
            }
        }
        for (int i = 0; i < bird1.getNetwork().getHiddenLayer().neurons.size(); i++) {
            if (Math.random() <= Settings.MUTATIONS_RATE) {
                bird1.getNetwork().getHiddenLayer().neurons.get(i).getWeights().set(0, bird1.getNetwork().getHiddenLayer().neurons.get(i).getWeights().get(0) + Math.random() * Settings.MUTATIONS_RANGE * 2 - Settings.MUTATIONS_RANGE);
            }
        }
        return new Bird(bird1.getNetwork());

    }

    public int getMaxFitness() {
        sortBirdsFitnessDesc();
        return birds.get(0).getFitness();
    }

    public ArrayList<Bird> getBirds() {
        return birds;
    }

    public void setBirds(ArrayList<Bird> birdSet) {
        birds = birdSet;
    }
}