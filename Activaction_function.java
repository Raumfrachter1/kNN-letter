public class Activaction_function {
    public static <T extends Number> Double ReLU(Double input){
        return math.max(0, input);
    }
    public static <T extends Number> Double[] ReLU(Double[] input){
        Double[] output = new Double[input.length];
        for (int i = 0; i < input.length; i++){
            output[i] = math.max(0, input[i]);
        }
        return output;
    }
    public static <T extends Number> Double[][] ReLU(Double[][] input){
        Double[][] output = new Double[input.length][input[0].length];
        for (int i = 0; i < input.length; i++){
            output[i] = ReLU(input[i]);
        }
        return output;
    }

    public static <T extends Number> Double sigmoid(Double input){
        Double output = 1/(1 + Math.exp(-input));
        return output;
    }
    public static <T extends Number> Double[] sigmoid(Double[] input){
        Double[] output = new Double[input.length];
        for (int i = 0; i < input.length; i++){
            output[i] = sigmoid(input[i]);
        }
        return output;
    }
    public static <T extends Number> Double[][] sigmoid(Double[][] input){
        Double[][] output = new Double[input.length][input[0].length];
        for (int i = 0; i < input.length; i++){
            output[i] = sigmoid(input[i]);
        }
        return output;
    }

    public static Double[][] sigmoidDerivative(Double[][] output) {
        Double[][] result = new Double[output.length][output[0].length];
        for (int i = 0; i < output.length; i++) {
            for (int j = 0; j < output[0].length; j++) {
                result[i][j] = output[i][j] * (1 - output[i][j]);
            }
        }
        return result;
    }

    public static Double[] sigmoidDerivative(Double[] output) {
        Double[] result = new Double[output.length];
        for (int i = 0; i < output.length; i++) {
                result[i] = sigmoid(output[i]) * (1 - sigmoid(output[i]));
        }
        return result;
    }

    public static double sigmoidDerivative(double value){
        double tmp = sigmoid(value);
        return tmp * (1 - tmp);
    }
}
