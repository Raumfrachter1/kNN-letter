public class math {
    /**
     * This class should implement the necessary mathematical functions required for kNN.
     * The syntax should mirror that of NumPy (e.g., instead of np.dot, use math.dot).
     */

    /**
     * Source https://numpy.org/doc/stable/reference/generated/numpy.dot.html
     * numpy.dot
     * numpy.dot(a, b, out=None)
     * Dot product of two arrays. Specifically,
     * If both a and b are 1-D arrays, it is inner product of vectors (without complex conjugation).
     * If both a and b are 2-D arrays, it is matrix multiplication, but using matmul or a @ b is preferred.
     * If either a or b is 0-D (scalar), it is equivalent to multiply and using numpy.multiply(a, b) or a * b is preferred.
     * If a is an N-D array and b is a 1-D array, it is a sum product over the last axis of a and b.
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
        Double[][] output = new Double[0][0];
        output = new Double[a.length][b[0].length];
        double tmp = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < b.length; k++) {
                    tmp += a[i][k].doubleValue() * b[k][j].doubleValue();
                }
                output[i][j] = tmp;
                tmp = 0;
            }
        }
        return output;
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

    public static <T extends Number> Double[][] convolutional(T[][] matrix, T[][] filter){
        //
        if ((filter.length != filter[0].length) || (filter.length % 2 == 0)){
            throw new IllegalArgumentException("Dein Filter ist scheiße. Fehlermeldung verbessern");
        }
        // Auffüllen mit nullen
        int len = filter.length/2;
        Double[][] tmp = new Double[matrix.length + filter.length - 1][matrix[0].length + filter.length - 1];
        Double[][] out = new Double[matrix.length][matrix[0].length];

        for (int i = 0; i < tmp.length; i++){
            for (int j = 0; j < tmp[0].length; j++){
                if (i < len || i >= matrix.length + len || j < len || j >= matrix[0].length + len){
                    tmp[i][j] = 0.0;
                }
                else {
                    tmp[i][j] = matrix[i-len][j-len].doubleValue();
                }
            }
        }
        //Berechnen - geht schöner, hatte ich aber keine Lust drauf.
        for (int i = 0; i < out.length; i++){
            for (int j = 0; j < out[0].length; j++){
                double sum = 0;
                for(int k = 0; k < filter.length; k++){
                    for (int l = 0; l < filter[0].length; l++){
                        sum += filter[k][l].doubleValue() * tmp[i+k][j+l];
                    }
                }
                out[i][j] = sum;
            }
        }
        return out;
    }

    public static <T extends Number> Double[][] dot(T[][] a, T b){
        Double[][] output = new Double[a.length][a[0].length];
        for(int i = 0; i < a.length; i++){
            for (int j = 0; j < a[0].length; j++){
                output[i][j] = a[i][j].doubleValue() * b.doubleValue();
            }
        }
        return output;
    }

    public static <T extends Number> Double[] dot(T[] a, T b){
        Double[] out = new Double[a.length];
        for (int i = 0; i < a.length; i++){
            out[i] = a[i].doubleValue() * b.doubleValue();
        }
        return out;
    }

    /**
     * Addition von Matrixen und Vektoren
     * Case 1: Vektor Skalar -> each value in the vector will be added with the skalar
     * Case 2: Matrix Skalar -> each value in the matrix will be added with the skalar
     * Case 3: Vektor Vektor ->
     * Case 4: matrix vektor ->
     * Case 5: matrix matrix ->
    */

    public static <T extends Number> Double[] add(T[] vector, T skalar){
        Double[] output = new Double[vector.length];

        for (int i = 0; i < vector.length; i++){
            output[i] = vector[i].doubleValue() + skalar.doubleValue();
        }

        return output;
    }
    public static <T extends Number> Double[][] add(T[][] matrix, T skalar){
        Double[][] output = new Double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++){
                output[i][j] = matrix[i][j].doubleValue() + skalar.doubleValue();
            }
        }
        return output;
    }
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

    public static <T extends Number> Double[][] add(T[][] a, T[] b){
        if (a[0].length != b.length){
            throw new IllegalArgumentException("Brudi mach mal irgendwann einen guten Kommentar - du suchst in add Matrix Vektor");
        }

        Double[][] output = new Double[a.length][a[0].length];
        for (int i = 0; i < a.length; i++){
            output[i] = add(a[i], b);
        }

        return output;
    }

    public static Double[][] subtract(Double[][] a, Double[][] b) {
        Double[][] result = new Double[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                result[i][j] = a[i][j] - b[i][j];
            }
        }
        return result;
    }
    /**
     * Matrix Transponation
     * transponieren(matrix[i][j]) = matrix[j][i]
     */
    public static <T> T[][] transponieren(T[][] a){
        int m = a.length;    // Number of rows
        int n = a[0].length; // Number of columns

        @SuppressWarnings("unchecked")
        T[][] b = (T[][]) java.lang.reflect.Array.newInstance(a[0][0].getClass(), n, m);
        for (int i = 0; i < a.length; i++){
            for (int j = 0; j < a[0].length; j++){
                b[j][i] = a[i][j];
            }
        }
        return b;
    }

    /**
     * Maximum and Minimum Funktion
     */
    public static <T extends Number> Double max(T a, T b){
        if (a.doubleValue() <= b.doubleValue()) {
            return b.doubleValue();
        } else {
            return a.doubleValue();
        }
    }
    public static <T extends Number> Double max(T a){
        return max(0, a);
    }

    public static <T extends Number> Double min(T a, T b){
        if (a.doubleValue() >= b.doubleValue()){
            return b.doubleValue();
        }
        else {
            return a.doubleValue();
        }
    }
    public static <T extends Number> Double min(T a){
        return min(0, a);
    }

    public static <T extends Number> Double averageOfAVector(Double[] vector){
        double sum = 0;
        int len = vector.length;
        for (int i = 0; i < len; i++){
            sum += vector[i];
        }
        return sum/len;
    }
}
