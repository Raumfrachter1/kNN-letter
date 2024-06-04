import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

//testcomment
public class main {

    public static void main(String[] args) throws IOException {
       testMNIST();
    }

    public static void testMNIST() {
        String path_img = "C:\\Users\\Philipp\\OneDrive - smail.inf.h-brs.de\\Studium\\Studieren und so\\SS24\\Projektseminar\\Dataset\\archive\\train-images.idx3-ubyte";
        String path_label = "C:\\Users\\Philipp\\OneDrive - smail.inf.h-brs.de\\Studium\\Studieren und so\\SS24\\Projektseminar\\Dataset\\archive\\train-labels.idx1-ubyte";
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

            Double[][] X1 = new Double[1000][X[0].length];
            Double[][] Y1 = new Double[1000][Y[0].length];

            for (int i = 0; i < X1.length; i++){
                X1[i] = X[i];
                Y1[i] = Y[i];
            }
            network n1 = new network(new int[]{784, 6, 6, 10});
            long startTime = System.nanoTime();

            n1.train(X1, Y1);

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
}

