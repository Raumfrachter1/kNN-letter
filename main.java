import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

//testcomment
public class main {

    public static void main(String[] args) throws IOException {
        testExcel();
       // testMNIST();
    }

    private static void testExcel(){
        Double[][] X ={{1.0, 0.0, 0.0}};
        Double[] Y = {1.0, 0.0, 0.0, 0.0};

        Double[][] weights1 = {{-0.081, 0.06, -0.01},
                {0.08, 0.02, 0.003},
                {-0.04, -0.003, -0.09}};
        Double[][] weights2 = {{-0.008, 0.06, 0.04},
                {0.01, -0.06, 0.06},
                {0.01, -0.027, 0.08},
                {0.00029, -0.01, 0.08}};

        Double[] biases1 = new Double[]{0.08, -0.09, -0.05};
        Double[] biases2 = new Double[]{-0.08, 0.06, 0.09, -0.001};

        network n2 = new network(new int[]{X.length, 3, 4},
                new Double[][][]{math.transponieren(weights1), math.transponieren(weights2)},
                new Double[][]{biases1, biases2});

        show2D(n2.calculus(X));
    }

    public static void testMNIST() {
        String path_img = "C:\\Users\\Philipp Schneider\\OneDrive - smail.inf.h-brs.de\\Studium\\Studieren und so\\SS24\\Projektseminar\\Dataset\\archive\\train-images.idx3-ubyte";
        String path_label = "C:\\Users\\Philipp Schneider\\OneDrive - smail.inf.h-brs.de\\Studium\\Studieren und so\\SS24\\Projektseminar\\Dataset\\archive\\train-labels.idx1-ubyte";
        try {
            List<Double[][]> loadedImages = MNISTLoader.loadImages(path_img);
            Double[][] X = new Double[loadedImages.size()][];
            for (int i = 0; i < loadedImages.size(); i++) {
                X[i] = flatten(loadedImages.get(i));
            }

            List<Integer> loadedLabels = MNISTLoader.loadLabels(path_label);
            Integer[] Y_preprep = loadedLabels.toArray(new Integer[0]);
            Double[][] Y = new Double[Y_preprep.length][10];
            for (int i = 0; i < Y.length; i++){
                Arrays.fill(Y[i], 0.0);
            }
            for (int i = 0; i < Y.length; i++){
                Y[i][Y_preprep[i]] = 1.0;
            }

            Double[][] X1 = {X[0], X[1]};
            Double[][] Y1 = {Y[0], Y[1]};

            network n1 = new network(new int[]{784, 6, 6, 10});
            long startTime = System.nanoTime();
            for (int i = 0; i < 100; i++) {
                n1.calculus(X);
            }
            long endTime = System.nanoTime();
            System.out.println("Elapsed time in nanoseconds: " + (endTime-startTime));

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


    public static <T extends Number> void show1D(T[] show){
        for (int i = 0; i < show.length; i++){
            System.out.print(show[i] + " \t");
        }
    }

    public static <T extends Number> void show2D(T[][] show){
        for (int i = 0; i < show.length; i++){
            for (int j = 0; j < show[0].length; j++){
                System.out.print(show[i][j] + " \t");
            }
            System.out.println("");
        }
    }
}

