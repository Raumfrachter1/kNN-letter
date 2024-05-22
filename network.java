public class network {
    private Layer_Dense[] net;
    private double learningRate = 0.01;
    /**
     * Konstruktoren
     */
    network(int[] neurons){
        net = new Layer_Dense[neurons.length];
        create_network(neurons);
    }

    network(int[] neurons, Double[][][] weights, Double[][] biases){
        net = new Layer_Dense[neurons.length];
        create_network(neurons, weights, biases);
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
        for (int i = 1; i < net.length; i++){
            net[i] = new Layer_Dense(neurons[i-1], neurons[i]);
        }
    }

    private void create_network(int[] neurons, Double[][][] weights, Double[][] bias){
        for (int i = 1; i < net.length; i++){
            net[i] = new Layer_Dense(neurons[i-1], neurons[i], weights[i-1], bias[i-1]);
        }
    }
    /**
     *  Should send a Batch of Input Data through the network using the forward method of Layer_Dense
     *
     * @params Double[size of the batch][single input vector] input
     * @return Values of the Outputlayer
     *
     */

    public void calculus (Double[][] X, Double[][] Y){
        /**
         * Attribute
         * Double[][][] output = new Double[number of layer]
         *                                 [number of inputs at the same time(Batchsize)]
         *                                 [length of the output (i hope vorher stand da input) vector]
         * Double[][] loss = new Double [Batch]
         *
         */
        Double[][][] output = new Double[net.length][][];

        for (int k = 0; k < 100; k++) {
            // Forward
            output[0] = X;
            for (int i = 1; i < net.length; i++) {
                output[i] = net[i].forward(output[i - 1]);
            }

            //print loss
            double loss = 0.0;
            for (int i = 0; i < output[0].length; i++){
                for(int j = 0; j < output[0].length; j++){
                    //System.out.printf("  " + output[net.length-1][i][j]);
                    loss += (output[net.length-1][i][j] - Y[i][j])* (output[net.length-1][i][j] - Y[i][j]);
                }
            }
            loss /= 2;
            System.out.println("Epoche " + k + " loss: " + loss/output[0].length);

            //Backpropagation
            //1. Calc Loss for BP
            loss = 0.0;
            for (int i = 0; i < output[0].length; i++){
                for(int j = 0; j < output[0].length; j++){
                    //System.out.printf("  " + output[net.length-1][i][j]);
                    loss += (output[net.length-1][i][j] - Y[i][j]);
                }
            }
            loss /= output[0].length;

            //BP Hidden Layer

        }
    }

    public void calculus(Double[] x, Double[] y){

        for (int i = 0; i < 100; i++){
            for (int j = 1; j < net.length; j++) {
                net[j].forward(x);
            }
        }
    }

    public Double[][] calculus(Double[][] x){
        Double[][][] output = new Double[net.length][][];
        output[0] = x;

        for (int i = 1; i < net.length; i++){
            output[i] = net[i].forward(output[i-1]);
        }

        return output[net.length-1];
    }

    /**
     *
     * @param predicted
     * @param actual
     * @return 1/2 sum_{all output}(actual-predicted)
     * Loss Function is based on the loss function Wikipedia explained
     */
    public static double calculateLoss(Double[][] predicted, Double[][] actual) {
        double loss = 0.0;
        int samples = predicted.length;
        int outputs = predicted[0].length;

        for (int i = 0; i < samples; i++) {
            for (int j = 0; j < outputs; j++) {
                double error = actual[i][j] - predicted[i][j];
                loss += error * error;
            }
        }

        return loss / 2;
    }

    //ANPASSEN !
    public void backpropagation(Double[][] inputs, Double[][] expectedOutputs) {
        // Schritt 1: Forward Pass
        Double[][][] layerOutputs = new Double[net.length][][];
        layerOutputs[0] = inputs;
        for (int i = 1; i < net.length; i++) {
            layerOutputs[i] = net[i].forward(layerOutputs[i - 1]);
        }

        // Schritt 2: Loss Berechnung (bereits vorhanden)

        // Schritt 3: Backward Pass
        Double[][] error = math.subtract(layerOutputs[net.length - 1], expectedOutputs); // Fehler am Ausgang
        for (int i = net.length - 1; i > 0; i--) {
            Double[][] delta = math.dot(error, Activaction_function.sigmoidDerivative(layerOutputs[i])); // Delta für aktuelle Schicht
            Double[][] gradient = math.dot(math.transponieren(layerOutputs[i - 1]), delta); // Gradientenberechnung
            Double[][] weightUpdate = math.dot(gradient, learningRate);
            net[i].updateWeights(weightUpdate);
            error = math.dot(delta, math.transponieren(net[i].getWeights()));
        }
    }
}
