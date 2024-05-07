import java.util.Arrays;
import java.util.Objects;
import java.math.*;

public class network {
    private Layer_Dense[] net;

    /**
     * Konstruktoren
     *
     * To use:
     * network(int[] neurons)
     *
     * For Test Reasons
     * network(int[] neurons, Double[][][] weights)
     * network(int[] neurons, Double[][][] weights, Double[][] biases)
     *      -> they allow u to set weights (and biases) by your own.
     *
     * For Fun Reasons
     * network()
     *      -> yeah i am a friendly Person :)
     */
    network(){
        System.out.println("You rly want to create an network with zero Layers ? Are you stupid or something like that ? ");
    }
    network(int[] neurons){
        net = new Layer_Dense[neurons.length];
        create_network(neurons);
    }
    network(int[] neurons, Double[][][] weights){
        net = new Layer_Dense[neurons.length];
        create_networt(neurons, weights);
    }
    network(int[] neurons, Double[][][] weights, Double[][] biases){
        net = new Layer_Dense[neurons.length];
        create_networt(neurons, weights, biases);
    }


    /**
     *
     * I will need them for the different constructors
     * No other use
     *
     * Normal Use:
     * private void create_network(int[] neurons)
     *
     * Test Use:
     * private void create_networt(int[] neurons, Double[][][] weights)
     * private void create_networt(int[] neurons, Double[][][] weights, Double[][] biases)
     *      ->
     */
    private void create_network(int[] neurons){
        net[0] = new Layer_Dense(1, neurons[0]);
        for (int i = 1; i < net.length; i++){
            net[i] = new Layer_Dense(neurons[i-1], neurons[i]);
        }
    }
    private void create_networt(int[] neurons, Double[][][] weights){
        net[0] = new Layer_Dense(1, neurons[0]);
        for (int i = 1; i < net.length; i++){
            net[i] = new Layer_Dense(neurons[i-1], neurons[i], weights[i-1]);
        }
    }
    private void create_networt(int[] neurons, Double[][][] weights, Double[][] biases){
        /**
         * i cant explain why i coded it like this, but the weights and biases of n[0] have no usage.
         * To get no problems with my code i build weights and biases of the correct size (?)
         *
         * i rly should figure out what i did there ...
         * okay didnt fix the problem xD
         */
        Double[][] dummy_weights = new Double[weights.length][weights[0].length];
        Double[] dummy_biases = new Double[weights[0].length];
        for (int j = 0; j < weights.length; j++) {
            for (int i = 0; i < dummy_biases.length; i++) {
                dummy_biases[i] = 0.0;
                dummy_weights[j][i] = 0.0;
            }
        }
        net[0] = new Layer_Dense(1, neurons[0], dummy_weights, dummy_biases);
        for (int i = 1; i < net.length; i++){
            net[i] = new Layer_Dense(neurons[i-1], neurons[i], weights[i-1], biases[i-1]);
        }
    }

    /**
     *
     *  Should send a Batch of Input Data through the network using the forward method of Layer_Dense
     *
     * @params Double[size of the batch][single input vector] input
     * @return Values of the Outputlayer
     *
     */

    public Double[][] calculus (Double[][] input){
        Double[][][] output = new Double[net.length][][];
        output[0] = input;

        for (int i = 1; i < net.length-1; i++){
            output[i] = net[i].forward(output[i-1]);
        }
        output[net.length-1] = net[net.length-1].forward_out(output[net.length-2]);

        return output[net.length-1];
    }

    public Double[][] calculus (Double[][] X, Integer[] Y){
        /**
         * Attribute
         * Double[][][] output = new Double[number of layer]
         *                                 [number of inputs at the same time(Batchsize)]
         *                                 [length of the input vector]
         * Double[][] loss = new Double [Batch]
         *
         */
        Double[][][] output = new Double[net.length][][];

        // Forward
        output[0] = X;
        for (int i = 1; i < net.length-1; i++){
            output[i] = net[i].forward(output[i-1]);
        }
        output[net.length-1] = net[net.length-1].forward_out(output[net.length-2]);

        Double[] loss = new Double[output[0].length];
        Arrays.fill(loss, 0.0);

        //calc loss
        for (int i = 0; i < output[net.length-1].length; i++) {
            for (int j = 0; j < output[net.length - 1][0].length; j++) {
                loss[i] = -Math.log(output[net.length - 1][i][Y[i]]);
            }
        }

        //backpropagation

        return output[net.length-1];
    }
}
