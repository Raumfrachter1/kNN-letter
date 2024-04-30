public class math {
    /**
     * This class should implement the necessary mathematical functions required for kNN.
     * The syntax should mirror that of NumPy (e.g., instead of np.dot, use math.dot).
     */

    /**
     * Source https://numpy.org/doc/stable/reference/generated/numpy.dot.html
     *
     * numpy.dot
     * numpy.dot(a, b, out=None)
     * Dot product of two arrays. Specifically,
     *
     * If both a and b are 1-D arrays, it is inner product of vectors (without complex conjugation).
     *
     * If both a and b are 2-D arrays, it is matrix multiplication, but using matmul or a @ b is preferred.
     *
     * If either a or b is 0-D (scalar), it is equivalent to multiply and using numpy.multiply(a, b) or a * b is preferred.
     *
     * If a is an N-D array and b is a 1-D array, it is a sum product over the last axis of a and b.
     *
     * If a is an N-D array and b is an M-D array (where M>=2), it is a sum product over the last axis of a and the second-to-last axis of b:
     */
    //Case 1: If both a and b are 1-D arrays, it is inner product of vectors (without complex conjugation).
    public static <T extends Number> Double dot(T[] a, T[] b){
        if (a.length != b.length){
            throw new IllegalArgumentException("Die Vektoren sind unterschiedlich lang");
        }

        Double result = Double.valueOf(0); //Der Datentyp soll die Genauigkeit bewahren und zudem die Berechnung ermöglichen
        for(int i = 0; i < a.length; i++){
            result += a[i].doubleValue() * b[i].doubleValue();
        }

        return result;
    }

    //Case 2: If both a and b are 2-D arrays, it is matrix multiplication, but using matmul or a @ b is preferred.
    public static <T extends Number> Double[][] dot(T[][] a, T[][] b){
        if (a[0].length != b.length){
            throw new IllegalArgumentException("Value Error: shape (" + a[0].length + ", " + a[0].length + ") and " + b.length + " doesnt fit.");
        }


    }


    //Case 4: If a is an N-D array and b is a 1-D array, it is a sum product over the last axis of a and b.
    public static <T extends Number> Double[] dot(T[][] a, T[] b){
        if (a[0].length != b.length){
            throw new IllegalArgumentException("Value Error: shape (" + a[0].length + ", " + a[0].length + ") and " + b.length + " doesnt fit.");
        }

        Double[] result = new Double[a.length];

        for (int i = 0; i < a.length; i++){
            result[i] = dot(a[i], b);
        }

        return result;
    }


    /**
     * Addition von Matrixen und Vektoren
     */
    public static <T extends Number> Double[] add(T[] a, T[] b){
        if (a.length != b.length){
            throw new IllegalArgumentException("The vectors have not the same length");
        }

        Double[] result = new Double[a.length];
        for (int i = 0; i < a.length; i++){
            result[i] = a[i].doubleValue() + b[i].doubleValue();
        }
        return result;
    }
}
