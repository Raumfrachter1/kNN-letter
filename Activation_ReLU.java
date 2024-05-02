public class Activation_ReLU {
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

    public static <T extends Number> Double[][] ReLu(Double[][] input){
        Double[][] output = new Double[input.length][input[0].length];
        for (int i = 0; i < input.length; i++){
            output[i] = ReLU(input[i]);
        }
        return output;
    }
}
