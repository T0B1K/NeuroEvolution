public class NeuralNetwork implements Comparable {
    private double[][] hiddenweights;
    private double[][] outputweights;
    private double bias = 1.0;
    private double fittness = 0;

    /**
     * FOr generating the weight Matrices
     *
     * @param input
     * @param hidden
     * @param output
     */
    public NeuralNetwork(int input, int hidden, int output) {
        this.hiddenweights = new double[input][hidden];
        this.outputweights = new double[hidden][output];

        this.hiddenweights = this.initRandomValues(this.hiddenweights);
        this.outputweights = this.initRandomValues(this.outputweights);
    }

    /**
     * constructor with loaded data
     *
     * @param hiddenWeights
     * @param outputWeights
     */
    public NeuralNetwork(double[][] hiddenWeights, double[][] outputWeights) {
        this.hiddenweights = hiddenWeights;
        this.outputweights = outputWeights;
    }

    /**
     * constructor with 2 param nets
     *
     * @param hiddenWeights1
     * @param outputWeights1
     * @param hiddenWeights2
     * @param outputWeights2
     */
    public NeuralNetwork(double[][] hiddenWeights1, double[][] outputWeights1, double[][] hiddenWeights2, double[][] outputWeights2) {
        this.hiddenweights = shuffle(hiddenWeights1, hiddenWeights2);
        this.outputweights = shuffle(outputWeights1, outputWeights2);
    }

    /**
     * this method shuffles two arrays with an random factor for mutation
     *
     * @param array1
     * @param array2
     * @return
     */
    private double[][] shuffle(double[][] array1, double[][] array2) {
        if (array1.length != array2.length || array1[0].length != array2[0].length) {
            System.out.println("hier wurden falsche Matrizen geshufflet");
            return null;
        }
        final int RANDOM_FACTOR = 100;
        double temp;
        double[][] retArray = new double[array1.length][array1[0].length];
        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array1[0].length; j++) {
                temp = Math.random() * 10;
                if (temp < 4) {
                    temp = array1[i][j];
                } else if (temp < 8) {
                    temp = array2[i][j];
                } else {
                    if (Math.random() > 0.45) {
                        temp = array1[i][j] + Math.random() * RANDOM_FACTOR - RANDOM_FACTOR / 2;
                    } else if (Math.random() > 0.8) {
                        temp = array2[i][j] + Math.random() * RANDOM_FACTOR - RANDOM_FACTOR / 2;
                    } else {
                        temp = Math.random() * RANDOM_FACTOR - RANDOM_FACTOR / 2;
                    }
                }
                retArray[i][j] = temp;
            }
        }
        return retArray;
    }

    /**
     * inits Random Values
     *
     * @param m matrix
     * @return m with random values
     */
    private double[][] initRandomValues(double[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                m[i][j] = Math.random() * 100 - 50;
            }
        }
        return m;
    }

    private double sigmoid(double d) {
        return 1 / (1 + Math.pow(Math.E, -d));
    }

    public double[] output(double[] input) {
        if (input.length != hiddenweights.length) {
            System.out.println("Falsch länge des Inputs");
        }

        double[][] inputTransp = new double[1][input.length];
        for (int i = 0; i < input.length; i++) {
            inputTransp[0][i] = input[i];
        }

        double[][] hidden = Main.matrixMultiplication(inputTransp, this.hiddenweights);
        for (int i = 0; i < hidden[0].length; i++) {
            hidden[0][i] = this.sigmoid(hidden[0][i]);
        }

        double[][] temp = Main.matrixMultiplication(hidden, this.outputweights);
        double[] output = new double[temp.length];
        for (int i = 0; i < temp[0].length; i++) {
            output[i] = this.sigmoid(temp[0][i]);
        }
        //System.out.println(output[0]);
        return output;
    }


    public double[][] getOutputweights() {
        return this.outputweights;
    }

    public double[][] getHiddenweights() {
        return this.hiddenweights;
    }

    @Override
    public int compareTo(Object o) {
        NeuralNetwork other = (NeuralNetwork) o;
        if (this.fittness < other.fittness) {
            return -1;
        } else if (this.fittness == other.fittness) {
            return 0;
        } else {
            return 1;
        }
    }

    public void addFittness(double value) {
        this.fittness += value;
    }

    public double getFittness() {
        return this.fittness;
    }

    public void setFittness(double fittness) {
        this.fittness = fittness;
    }
}
