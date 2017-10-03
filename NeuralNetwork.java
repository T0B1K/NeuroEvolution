package NeuralNetwork;

import basicMath.*;

public class NeuralNetwork {
    private Vector iV;
    private Vector hV;
    private Vector oV;

    private Matrix hw;
    private Matrix ow;

    public NeuralNetwork(int inputUnits, int hiddenUnits, int outputUnits) {
        //hier wird die Länge der Vektoren definiert
        this.iV = new Vector(inputUnits);
        this.hV = new Vector(hiddenUnits);
        this.oV = new Vector(outputUnits);

        this.hw = new Matrix(inputUnits, hiddenUnits);
        this.ow = new Matrix(hiddenUnits, outputUnits);
    }

    private double sigmoid(int xValue) {
        /*
        Dasi ist die Sigmoid Funktion, sie liefert Werte zwischen 0 und 1
        dadurch kann man immer gut schauen, wo man sich am Graphen befindet und es ist einfacher sie zu trainieren
         */
        return 1 / (1 + Math.pow(Math.E, -xValue));
    }

    private double sigderiv(int xValue) {
        /*
        Das ist die Ableitung der Sigmoid funktion  (Die gaußsche Glockenkurve aka. normalverteilung)
         */
        return Math.pow(Math.E, -(xValue * xValue));
    }

    public void initRandomWeights() {

    }

    public double[] dError(Vector outputs) {
        return Vector.sub(this.oV, outputs);
    }
}
