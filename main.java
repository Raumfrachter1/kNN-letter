import java.util.Random;

public class main {
    public static void main(String[] args) {
        Double[][] X = new Double[][]{{1.0, 2.0, 3.0, 2.5},
                                        {2.0, 5.0, -1.0, 2.0},
                                        {-1.5, 2.7, 3.3, -0.8}};
/**
        Double[][] weights1 = new Double[][]{{0.2, 0.8, -0.5, 1.0},
                                             {0.5, -0.91, 0.26, -0.5},
                                             {-0.26, -0.27, 0.17, 0.87}};

        Double[][] weights2 = new Double[][]{{0.1, -0.14, 0.5},
                                             {-0.5, 0.12, -0.33},
                                             {-0.44, 0.73, -0.13}};

        Double[] biases1 = new Double[]{2.0, 3.0, 0.5};
        Double[] biases2 = new Double[]{-1.0, 2.0, -0.5};

        network n2 = new network(new int[]{X[0].length, 3, 3},
                                 new Double[][][]{math.transponieren(weights1), math.transponieren(weights2)},
                                 new Double[][]{biases1, biases2});

        show(n2.calculus(X));
*/
/**
        Layer_Dense layer1 = new Layer_Dense(4,5);
        Layer_Dense layer2 = new Layer_Dense(5,2);

        Double[][] output_layer1 = layer1.forward(X);
        Double[][] output_layer2 = layer2.forward(output_layer1);
        show(output_layer2);
*/
        network n1 = new network(new int[]{X[0].length, 5, 2});
        show(n1.calculus(X));
    }

    public static void show(Double[][] show){
        for (int i = 0; i < show.length; i++){
            for (int j = 0; j < show[0].length; j++){
                System.out.print(show[i][j] + " \t");
            }
            System.out.println("");
        }
    }
}
