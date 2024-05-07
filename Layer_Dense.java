import java.util.Random;

public class Layer_Dense {
    //Attribute
    private Double[][] weights;
    private Double bias;
    private Double[] biases;
    private int input;
    private int neurons;

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
        this.input = input;
        this.neurons = neurons;
        bias = 0.0;
    }
    Layer_Dense(int input, int neurons, Double[][] weights){
        //this.weights = new Double[input][neurons];
        //setWeights(weights);
        this.weights = weights;
        this.input = input;
        this.neurons = neurons;
        bias = 0.0;
    }
    Layer_Dense(int input, int neurons, Double[][] weights, Double bias){
        //this.weights = new Double[input][neurons];
        //setWeights(weights);
        this.weights = weights;
        this.input = input;
        this.neurons = neurons;
        this.bias = bias;
    }
    Layer_Dense(int input, int neurons, Double[][] weights, Double[] biases){
        //this.weights = new Double[input][neurons];
        //setWeights(weights);
        this.weights = weights;
        this.input = input;
        this.neurons = neurons;
        this.biases = biases;
    }


    /**
     *
     * Methods
     * getNeurons()
     *      -> what do u guess ?
     * yes I will write down the rest later
     */
    public int getNeurons(){
        return neurons;
    }

    /**
     *
     * forward is used if the next layer is a hidden layer
     *
     * forward_out is used if the next layer is the output layer. I will use the different methods to normalize my outputvector what isn't nessary at the hidden layers
     *
     */
    public Double[][] forward(Double[][] input){
        Double[][] output = math.add(math.dot(input, weights), biases);
        return Activaction_function.sigmoid(output);
    }
    public Double[][] forward_out(Double[][] input){
        Double[][] output = forward(input);
        double sum = 0;
        for (int i = 0; i < output.length; i++) {
            for (int j = 0; j < output[0].length; j++){
                sum += output[i][j];
            }
            for (int j = 0; j < output[0].length; j++){
                output[i][j] /= sum;
            }
            sum = 0;
        }
        return output;
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
}
