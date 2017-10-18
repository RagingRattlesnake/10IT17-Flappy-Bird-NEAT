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
                if (o1.getFitness() < o2.getFitness()) {
                    return 1;
                }
                if (o1.getFitness() > o2.getFitness()) {
                    return -1;
                }
                return 0;
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
        for (int i = 0; i < Math.round(Settings.elitism * Settings.POPULATION); i++) {
            if (birdNew.size() < Settings.POPULATION) {
                birdNew.add(new Bird(birds.get(i).getNetwork()));
            }
        }

        for (int i = 0; i < Math.round(Settings.zufallVerhalten * Settings.POPULATION); i++) {
            birdNew.add(new Bird());
        }

        int max = 0;
        while (true) {
            for (int i = 0; i < max; i++) {
                Bird childBird = breed(birds.get((int)(Math.random()*(Math.round(Settings.elitism * Settings.POPULATION)))), birds.get((int)(Math.random()*(Math.round(Settings.elitism * Settings.POPULATION)))));
                birdNew.add(new Bird(childBird.getNetwork()));
                if (birdNew.size() >= Settings.POPULATION) {
                    return birdNew;
                }
            }
            max++;
            if (max >= birds.size() - 1) {
                max = 0;
            }
        }
    }

    private static Bird breed(Bird bird1, Bird bird2) {

        for (int i = 0; i < bird1.getNetwork().getInputLayer().neurons.get(0).getWeights().size(); i++) {
            if (Math.random() <= Settings.crossoverRate) {
                bird1.getNetwork().getInputLayer().neurons.get(0).getWeights().set(i, bird2.getNetwork().getInputLayer().neurons.get(0).getWeights().get(i));
                bird1.getNetwork().getInputLayer().neurons.get(1).getWeights().set(i, bird2.getNetwork().getInputLayer().neurons.get(1).getWeights().get(i));
            }
        }
        for (int i = 0; i < bird1.getNetwork().getHiddenLayer().neurons.size(); i++) {
            if (Math.random() <= Settings.crossoverRate) {
                bird1.getNetwork().getHiddenLayer().neurons.get(i).getWeights().set(0, bird2.getNetwork().getHiddenLayer().neurons.get(i).getWeights().get(0));
            }
        }

        for (int i = 0; i < bird1.getNetwork().getInputLayer().neurons.get(0).getWeights().size(); i++) {
            if (Math.random() <= Settings.mutationsRate) {
                bird1.getNetwork().getInputLayer().neurons.get(0).getWeights().set(i, bird1.getNetwork().getInputLayer().neurons.get(0).getWeights().get(i) + Math.random() * Settings.mutationRange * 2 - Settings.mutationRange);
                bird1.getNetwork().getInputLayer().neurons.get(1).getWeights().set(i, bird1.getNetwork().getInputLayer().neurons.get(1).getWeights().get(i) + Math.random() * Settings.mutationRange * 2 - Settings.mutationRange);
            }
        }
        for (int i = 0; i < bird1.getNetwork().getHiddenLayer().neurons.size(); i++) {
            if (Math.random() <= Settings.mutationsRate) {
                bird1.getNetwork().getHiddenLayer().neurons.get(i).getWeights().set(0, bird1.getNetwork().getHiddenLayer().neurons.get(i).getWeights().get(0) + Math.random() * Settings.mutationRange * 2 - Settings.mutationRange);
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