import java.io.IOException;
import java.util.List;
import java.util.Random;

//testcomment
public class main {

    public static void main(String[] args) throws IOException {
        //testExcel();
        testMNIST();
        //testVideo();

    }

    private static void testExcel(){
        Double[][] X = {{1.0, 0.0, 0.0}};

        Double[][] weights1 = {{-0.081, 0.06, -0.01},
                               {0.08, 0.02, 0.003},
                               {-0.04, -0.003, -0.09}};
        Double[][] weights2 = {{-0.008, 0.06, 0.04},
                               {0.01, -0.06, 0.06},
                               {0.01, -0.027, 0.08},
                               {0.00029, -0.01, 0.08}};

        Double[] biases1 = new Double[]{0.08, -0.09, -0.05};
        Double[] biases2 = new Double[]{-0.08, 0.06, 0.09, -0.001};

        network n2 = new network(new int[]{X[0].length, 3, 4},
                new Double[][][]{math.transponieren(weights1), math.transponieren(weights2)},
                new Double[][]{biases1, biases2});

        show2D(n2.calculus(X));
    }

    public static void testVideo(){

        Double[][] X = new Double[][]{{1.0, 2.0, 3.0, 2.5},
                {2.0, 5.0, -1.0, 2.0},
                {-1.5, 2.7, 3.3, -0.8}};
        Integer[] Y = new Integer[]{1, 2, 0};

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

    }

    public static void testMNIST() throws IOException {
        String path_img = "C:\\Users\\Philipp\\OneDrive - smail.inf.h-brs.de\\Studium\\Studieren und so\\SS24\\Projektseminar\\Dataset\\archive\\train-images.idx3-ubyte";
        String path_label = "C:\\Users\\Philipp\\OneDrive - smail.inf.h-brs.de\\Studium\\Studieren und so\\SS24\\Projektseminar\\Dataset\\archive\\train-labels.idx1-ubyte";
        try {
            List<Double[][]> loadedImages = MNISTLoader.loadImages(path_img);
            Double[][] X = new Double[loadedImages.size()][];
            for (int i = 0; i < loadedImages.size(); i++) {
                X[i] = flatten(loadedImages.get(i));
            }

            List<Integer> loadedLabels = MNISTLoader.loadLabels(path_label);
            Integer[] Y = loadedLabels.toArray(new Integer[0]);


            Double[][] X1 = {X[0], X[1]};
            Integer[] Y1 = {Y[0], Y[1]};

            network n1 = new network(new int[]{784, 6, 6, 10});
            n1.calculus(X1, Y1);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Double[] flatten(Double[][] image) {
        Double[] flat = new Double[image.length * image[0].length];
        int index = 0;
        for (Double[] row : image) {
            for (Double val : row) {
                flat[index++] = val;
            }
        }
        return flat;
    }

    public static <T extends Number> void show2D(T[][] show){
        for (int i = 0; i < show.length; i++){
            for (int j = 0; j < show[0].length; j++){
                System.out.print(show[i][j] + " \t");
            }
            System.out.println("");
        }
    }
    public static <T extends Number> void show1D(T[] show){
        for (int i = 0; i < show.length; i++){
            System.out.print(show[i] + " \t");
        }
    }
}



/**


 Double[][] X = new Double[][]{{1.0, 2.0, 3.0, 2.5},
 {2.0, 5.0, -1.0, 2.0},
 {-1.5, 2.7, 3.3, -0.8}};
 Integer[] Y = new Integer[]{1, 2, 0};

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

 show2D(n2.calculus(X));
 show1D(n2.calculus(X, Y));



 Double[][] X = new Double[][]{{1.0, 2.0, 3.0, 2.5},
 {2.0, 5.0, -1.0, 2.0},
 {-1.5, 2.7, 3.3, -0.8}};
 */
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