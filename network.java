import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

public class network {
    private Layer_Dense[] net;
    public Double[] loss;

    public network(int[] neurons) {
        net = new Layer_Dense[neurons.length];
        create_network(neurons);
    }

    private void create_network(int[] neurons) {
        net[0] = new Layer_Dense();
        for (int i = 1; i < net.length; i++) {
            net[i] = new Layer_Dense(neurons[i - 1], neurons[i]);
        }
    }

    public static Double[][][] splitArray(Double[][] data, int batchSize) {
        int totalEntries = data.length;
        int numFullBatches = totalEntries / batchSize;
        int remainingEntries = totalEntries % batchSize;
        int numBatches = remainingEntries > 0 ? numFullBatches + 1 : numFullBatches;

        Double[][][] batches = new Double[numBatches][][];

        for (int i = 0; i < numFullBatches; i++) {
            batches[i] = new Double[batchSize][];
            for (int j = 0; j < batchSize; j++) {
                batches[i][j] = data[i * batchSize + j];
            }
        }

        if (remainingEntries > 0) {
            batches[numFullBatches] = new Double[remainingEntries][];
            for (int j = 0; j < remainingEntries; j++) {
                batches[numFullBatches][j] = data[numFullBatches * batchSize + j];
            }
        }

        return batches;
    }

    public void train(Double[][] X_data, Double[][] Y_data) {
        int episoden = 1000000; //10^6l
        loss = new Double[episoden / 1000];
        double progress;
        int batchsize = 10;

        Double[][][] X = splitArray(X_data, batchsize);
        Double[][][] Y = splitArray(Y_data, batchsize);

        for (int i = 0; i < episoden; i++) {
            net[0].setOut(X[i % (X.length - 1)]);
            for (int j = 1; j < net.length; j++) {
                net[j].forward(net[j - 1].getOut());
            }
            if (i % 1000 == 0) {
                int lossIndex = i / 1000;
                loss[lossIndex] = net[net.length - 1].printLoss(Y[i % (Y.length - 1)]);
                progress = (double) i / episoden * 100;
                System.out.println("Loss: " + loss[lossIndex] / batchsize + " Fortschritt: " + progress + "%");
            }

            net[net.length - 1].backward_outlayer(Y[i % (Y.length - 1)], net[net.length - 2].getOut());
            for (int j = net.length - 2; j > 0; j--) {
                net[j].backward(net[j + 1].getDelta(), net[j - 1].getOut(), net[j + 1].getWeights());
            }
        }

        plotLoss();
    }

    private void plotLoss() {
        double[] xData = new double[loss.length];
        double[] yData = new double[loss.length];

        for (int i = 0; i < loss.length; i++) {
            xData[i] = i * 1000;
            yData[i] = loss[i];
        }

        XYChart chart = QuickChart.getChart("Training Loss Over Iterations", "Iteration", "Loss", "loss", xData, yData);
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setMarkerSize(5);

        new SwingWrapper<>(chart).displayChart();
    }
}
