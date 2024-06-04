public class network {
    private Layer_Dense[] net;

    /**
     * Konstruktoren
     */
    network(int[] neurons) {
        net = new Layer_Dense[neurons.length];
        create_network(neurons);
    }


    /**
     * I will need them for the different constructors
     * No other use
     * <p>
     * Normal Use:
     * private void create_network(int[] neurons)
     * <p>
     * Test Use:
     * private void create_networt(int[] neurons, Double[][][] weights)
     * private void create_networt(int[] neurons, Double[][][] weights, Double[][] biases)
     * ->
     */
    private void create_network(int[] neurons) {
        net[0] = new Layer_Dense();
        for (int i = 1; i < net.length; i++) {
            net[i] = new Layer_Dense(neurons[i - 1], neurons[i]);
        }
    }

    /**
     * Should send a Batch of Input Data through the network using the forward method of Layer_Dense
     *
     * @return Values of the Outputlayer
     * @params Double[size of the batch][single input vector] input
     */

    public void train(Double[][] X, Double[][] Y) {
        int episoden = 1000000000;
        for (int j = 0; j < episoden; j++) {
            // Forward
            net[0].setOut(X);
            for (int i = 1; i < net.length; i++) {
                net[i].forward(net[i - 1].getOut());
            }
            //Loss
            if (j % 100 == 0) {
                net[net.length - 1].printLoss(Y);
                System.out.println(" Fortschritt: "j/episoden * 100 + "%");
            }

            //Backward
            net[net.length - 1].backward_outlayer(Y, net[net.length - 2].getOut());
            for (int i = net.length - 2; i > 1; i--) {
                net[i].backward(net[i + 1].getDelta(), net[i - 1].getOut(), net[i + 1].getWeights());
            }
        }
    }
}
