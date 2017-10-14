public class NeuralNetwork {
    private double[][] hiddenweights;
    private double[][] outputweights;
    private double bias = 1.0;
    public double error;

    public NeuralNetwork(int inputneurons, int hiddenneurons, int outputneurons) {
        /*
        [
        [0,0,0] <- erster Array für x1
        [0.1.0] <- zweiter Array für x2
        ]
        bei x1 X x2 matrix
         */
        this.hiddenweights = new double[inputneurons][hiddenneurons];
        this.outputweights = new double[hiddenneurons][outputneurons];

        for (int i = 0; i < hiddenweights.length; i++) {
            for (int j = 0; j < hiddenweights[0].length; j++) {
                hiddenweights[i][j] = Math.random() * 2 - 1;
            }
        }

        for (int i = 0; i < outputweights.length; i++) {
            for (int j = 0; j < outputweights[0].length; j++) {
                outputweights[i][j] = Math.random() * 2 - 1;
            }
        }
    }

    private double sigmoid(double x) {
        return 1 / (1 + Math.pow(Math.E, -x));
    }

    public double[] fullBatchTraining(double[] _inputs) {
        double[] inputs = _inputs;
        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = sigmoid(inputs[i]);
        }

        double[] hiddenperceptrons = dotProduct(inputs, hiddenweights);
        double[] output = dotProduct(hiddenperceptrons, outputweights);

        return output;
    }

    private double[] dotProduct(double[] x1, double[][] x2) {
        double[] ergebnis = new double[x2[0].length];
        if (x1.length == x2.length) {
            for (int i = 0; i < x2[0].length; i++) {
                for (int j = 0; j < x2.length; j++) {
                    ergebnis[i] += x1[j] * x2[j][i];
                }
                ergebnis[i] = sigmoid(ergebnis[i]);
                //System.out.println(ergebnis[i]);
            }

        }
        return ergebnis;

    }

    public double calcerror(double[] erwarteterOutput, double[] output) {
        if (erwarteterOutput.length == output.length) {
            for (int i = 0; i < output.length; i++) {
                this.error += Math.abs(erwarteterOutput[i]) - Math.abs(output[i]);
            }
        }
        return this.error;
    }
}
