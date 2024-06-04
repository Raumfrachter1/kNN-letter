import java.util.Random;

public class Layer_Dense {
    //Attribute
    private Double[][] weights;
    private Double[] biases;
    private Double[][] delta;
    private Double[][] net;
    private Double[][] out;
    private double eta = 0.01;
    private int batchSize;




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
    }

    Layer_Dense(Double[][] weights, Double[] biases){
        this.weights = weights;
        this.biases = biases;
    }

    Layer_Dense(){

    }
    /**
     *
     * forward is used if the next layer is a hidden layer
     *
     * forward_out is used if the next layer is the output layer. I will use the different methods to normalize my outputvector what isn't nessary at the hidden layers
     *
     */
    public void forward(Double[][] input){ //Batchsize < 1
        net = new Double[input.length][input[0].length];
        this.net = math.add(math.dot(input, weights), biases);
        this.out = Activaction_function.sigmoid(net);
    }

    public void forward(Double[] input){
        net = new Double[1][weights[0].length];
        this.net[0]= math.add(math.dot(weights, input), biases);
        out = new Double[1][weights[0].length];
        this.out = Activaction_function.sigmoid(net);
    }

    public void backward_outlayer(Double[][] y, Double[][] outPrev){
        batchSize = out.length;
        delta = new Double[batchSize][out[0].length];

        double sumOfDeltas;
        double derivativOfTheLoss;
        double derivativOfTheActivactionFunction;
        Double[][][] deltaweights = new Double[batchSize][out[0].length][outPrev[0].length];

        for (int j = 0; j < batchSize; j++){
            derivativOfTheLoss = 0.0;
            derivativOfTheActivactionFunction = 0.0;
            for (int i = 0; i < out[0].length; i++){
                derivativOfTheLoss += out[j][i] - y[j][i];
                derivativOfTheActivactionFunction += Activaction_function.sigmoidDerivative(net[j][i]);
                delta[j][i] = derivativOfTheActivactionFunction * derivativOfTheLoss;
                for (int k = 0; k < outPrev[0].length;  k++){
                    deltaweights[j][i][k] = -eta * delta[j][i] * outPrev[j][k];
                }
            }
        }

        for (int i = 0; i < outPrev[0].length; i++){
            for (int j = 0; j < out[0].length; j++){
                sumOfDeltas = 0.0;
                for (int k = 0; k < batchSize; k++){
                    sumOfDeltas += deltaweights[k][j][i];
                }
                weights[i][j] += sumOfDeltas/batchSize;
            }
        }
    }

    public void backward(Double[][] deltaPrev, Double[][] outPrev, Double[][] weightsPrev){
        batchSize = out.length;

        delta = new Double[batchSize][out[0].length];
        double derivativOfTheActivactionFunction;
        double sumOfDeltas;
        Double[][][] deltaweights = new Double[batchSize][out[0].length][outPrev[0].length];

        for (int j = 0; j < batchSize; j++){
            derivativOfTheActivactionFunction = 0.0;
            for (int i = 0; i < out[0].length; i++){
                sumOfDeltas = 0.0;
                for (int k = 0; k < deltaPrev[0].length; k++){
                    sumOfDeltas += deltaPrev[j][k] * weightsPrev[i][k];
                }
                derivativOfTheActivactionFunction += Activaction_function.sigmoidDerivative(net[j][i]);
                delta[j][i] = derivativOfTheActivactionFunction * sumOfDeltas;
                for (int k = 0; k < outPrev[0].length;  k++){
                    deltaweights[j][i][k] = -eta * delta[j][i] * outPrev[j][k];
                }
            }
        }

        for (int i = 0; i < outPrev[0].length; i++){
            for (int j = 0; j < out[0].length; j++){
                sumOfDeltas = 0.0;
                for (int k = 0; k < batchSize; k++){
                    sumOfDeltas += deltaweights[k][j][i];
                }
                weights[i][j] += sumOfDeltas/batchSize;
            }
        }
    }

    public double printLoss(Double[][] y){
        double sum = 0.0;

        for (int i = 0; i < y.length; i++){
            for (int j = 0; j < y[0].length; j++){
                sum += (out[i][j] - y[i][j]) * (out[i][j] - y[i][j]);
            }
        }

        return sum/2;
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

    public Double[][] getDelta(){
        return delta;
    }

    public void setDelta(Double[][] a){
        delta = a;
    }

    public Double[][] getNet(){
        return this.net;
    }

    public Double[][] getOut(){
        return this.out;
    }

    public void setOut(Double[][] out){
        this.out = out;
    }
}
