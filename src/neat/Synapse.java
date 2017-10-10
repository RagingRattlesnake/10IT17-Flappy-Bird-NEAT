package neat;

public class Synapse {
    private Neuron baseNeuron;
    private Neuron destinationNeuron;
    private double weight;

    public Synapse(Neuron baseNeuron, Neuron destinationNeuron, double weight) {
        this.baseNeuron = baseNeuron;
        this.destinationNeuron = destinationNeuron;
        this.weight = weight;
    }

    public Neuron getBaseNeuron() {
        return baseNeuron;
    }

    public Neuron getDestinationNeuron() {
        return destinationNeuron;
    }

    public double getWeight() {
        return weight;
    }

    public void setBaseNeuron(Neuron baseNeuron) {
        this.baseNeuron = baseNeuron;
    }

    public void setDestinationNeuron(Neuron destinationNeuron) {
        this.destinationNeuron = destinationNeuron;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
