public class main {
    public static void main(String[] args) {
        Double[][] inputs = new Double[][]{{1.0, 2.0, 3.0, 2.5},
                                        {2.0, 5.0, -1.0, 2.0},
                                        {-1.5, 2.7, 3.3, -0.8}};
        Double[][] weights = new Double[][]{{0.2, 0.8, -0.5, 1.0},
                                            {0.5, -0.91, 0.26, -0.5},
                                            {-0.26, -0.27, 0.17, 0.87}};
        Double[] biases = new Double[]{2.0, 3.0, 0.5};
        Double[][] output = math.add(math.dot(weights, inputs), biases);
        for (int i = 0; i < output.length; i++){
            for(int j = 0; j < output[0].length; j++){
                System.out.print(output[i][j]);
            }
            System.out.println("");
        }
    }
}
