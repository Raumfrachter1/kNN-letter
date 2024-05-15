import java.util.Arrays;
import java.util.Random;

public class Layer_Dense {
    //Attribute
    private Double[][] weights;
    private Double bias;
    private Double[] biases;

    private Double[] delta;
    private Double[][] net;



    /**
     * Konstruktoren
     *
     * To Use:
     * Layer_Dense(int input, int neurons)
     *      -> usual case. bias 0, weights random
     *
     * Testing your network:
     * Layer_Dense(int input, int neurons, Double[][] weights)
     *      -> set weights by your own
     * Layer_Dense(int input, int neurons, Double[][] weights, Double bias)
     *      -> set weights and bias by yourself, Bias is a skalar
     * Layer_Dense(int input, int neurons, Double[][] weights, Double[] biases)
     *      -> set weights and bias by yourself. Bias is a vector
     */
    Layer_Dense(int input, int neurons){ // input is the size of the prev. Layer and neurons is the size of the new Layer
        weights = new Double[input][neurons];
        setWeights_random(input, neurons);
        Random rand = new Random();
        bias = rand.nextDouble(0,1);
        delta = new Double[neurons];
    }
    Layer_Dense(int input, int neurons, Double[][] weights){
        this.weights = weights;
        Random rand = new Random();
        bias = rand.nextDouble(0,1);
        delta = new Double[neurons];
    }
    Layer_Dense(int input, int neurons, Double[][] weights, Double bias){
        this.weights = weights;
        this.bias = bias;
        delta = new Double[neurons];
    }
    Layer_Dense(int input, int neurons, Double[][] weights, Double[] biases){
        this.weights = weights;
        this.biases = biases;
        delta = new Double[neurons];
    }




    /**
     *
     * forward is used if the next layer is a hidden layer
     *
     * forward_out is used if the next layer is the output layer. I will use the different methods to normalize my outputvector what isn't nessary at the hidden layers
     *
     */
    public Double[][] forward(Double[][] input){
        net = new Double[input.length][input[0].length];
        this.net = math.add(math.dot(input, weights), bias);
        return Activaction_function.sigmoid(net);
    }


    private void setWeights_random(int input, int neurons){
        Random rand = new Random();
        for (int i = 0; i < input; i++){
            for(int j = 0; j < neurons; j++){
                this.weights[i][j] = rand.nextDouble(-1,1);
            }
        }
    }

    private void setWeights(Double[][] weights){
        if (this.weights.length != weights.length || this.weights[0].length != weights[0].length){
            System.out.println("You have the following Dimensions: " +
                    "(" + this.weights.length + ", " + this.weights[0].length + ") and (" + weights.length + ", " + weights[0].length + ").");
            throw new IllegalArgumentException("Dimension of your weights are incorrect.");
        }

        this.weights = weights;
    }

    public void updateWeights(Double[][] weightUpdate) {
        for (int i = 0; i < weights.length; i++) {
            for (int j = 0; j < weights[i].length; j++) {
                weights[i][j] -= weightUpdate[i][j]; // Update weights using gradient descent
            }
        }
    }

    public Double[][] getWeights() {
        return weights;
    }

    public Double[] getDelta(){
        return delta;
    }

    public void setDelta(Double[] a){
        delta = a;
    }

    public Double[][] getNet(){
        return this.net;
    }
}
