import java.util.Arrays;
import java.util.Random;

public class Layer_Dense {
    //Attribute
    private Double[][] weights;
    private Double[] biases;
    private Double[] delta;
    private Double[][] net;
    private Double[][] out;
    private Double eta;



    /**
     * Konstruktoren
     *
     * To Use:
     * Layer_Dense(int input, int neurons)
     *      -> usual case. bias 0, weights random
     */
    Layer_Dense(int input, int neurons){ // input is the size of the prev. Layer and neurons is the size of the new Layer
        weights = new Double[input][neurons];
        setWeights_random(input, neurons);
        biases = new Double[neurons];
        setBiasese_random(neurons);
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
    public Double[][] forward(Double[][] input){ //Batchsize < 1
        net = new Double[input.length][input[0].length];
        this.net = math.add(math.dot(input, weights), biases);
        this.out = Activaction_function.sigmoid(net);
        return this.out;
    }

    public Double[] forward(Double[] input){
        net = new Double[1][weights[0].length];
        this.net[0]= math.add(math.dot(weights, input), biases);
        out = new Double[1][weights[0].length];
        this.out = Activaction_function.sigmoid(net);
        return this.out[0];
    }

    public void backward(Double[][] Y, Double[][] output){
        double sum = 0.0;
        for(int j = 0; j < net[0].length; j++) {

            for (int i = 0; i < delta.length; i++) {
              //  this.delta[i] = Activaction_function.sigmoidDerivative();
            }
        }
    }

    private void setWeights_random(int input, int neurons){
        Random rand = new Random();
        for (int i = 0; i < input; i++){
            for(int j = 0; j < neurons; j++){
                this.weights[i][j] = rand.nextDouble(-1,1);
            }
        }
    }

    private void setBiasese_random(int neurons){
        Random rand = new Random();
        for (int i = 0; i < neurons; i++){
            this.biases[i] = rand.nextDouble(-1,1);
        }
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
