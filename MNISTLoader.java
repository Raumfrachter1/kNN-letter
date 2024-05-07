/**
 * The following Code is written by ChatGPT-4
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MNISTLoader {

    public static List<Double[][]> loadImages(String imagePath) throws IOException {
        DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(imagePath)));
        int magic = dis.readInt();
        if (magic != 2051) {
            throw new IOException("Invalid magic number in image file!");
        }

        int numImages = dis.readInt();
        int numRows = dis.readInt();
        int numCols = dis.readInt();

        List<Double[][]> images = new ArrayList<>();

        for (int i = 0; i < numImages; i++) {
            Double[][] image = new Double[numRows][numCols];
            for (int row = 0; row < numRows; row++) {
                for (int col = 0; col < numCols; col++) {
                    image[row][col] = (double) dis.readUnsignedByte();
                }
            }
            images.add(image);
        }

        dis.close();
        return images;
    }

    public static List<Integer> loadLabels(String labelPath) throws IOException {
        DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(labelPath)));
        int magic = dis.readInt();
        if (magic != 2049) {
            throw new IOException("Invalid magic number in label file!");
        }

        int numLabels = dis.readInt();
        List<Integer> labels = new ArrayList<>();

        for (int i = 0; i < numLabels; i++) {
            labels.add((int) dis.readUnsignedByte());
        }

        dis.close();
        return labels;
    }

    /**
    public static void main(String[] args) {
        try {
            List<double[][]> trainImages = loadImages("C:\\Users\\Philipp Schneider\\OneDrive - smail.inf.h-brs.de\\Studium\\Studieren und so\\SS24\\Projektseminar\\Dataset\\archive\\train-images.idx3-ubyte");
            List<Integer> trainLabels = loadLabels("C:\\Users\\Philipp Schneider\\OneDrive - smail.inf.h-brs.de\\Studium\\Studieren und so\\SS24\\Projektseminar\\Dataset\\archive\\train-labels.idx1-ubyte");

            System.out.println("Loaded " + trainImages.size() + " images and " + trainLabels.size() + " labels.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     */

}

