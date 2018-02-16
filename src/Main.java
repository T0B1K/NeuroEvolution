import java.util.Arrays;

public class Main extends ArrayFunctions {
    private static final double[][] trainingData = {{1, 1}, {1, 0}, {0, 1}, {0, 0}};
    private static final double[] trainingResult = {0, 1, 1, 0};
    private static final double KEEP_ALLIVE = 1 / 3;
    private static final double NEW_NETS = 1 / 3;

    public static void main(String[] args) {
        NeuralNetwork finalNet = train(1000, 2, 3, 1, 1000);
        System.out.println(finalNet.getFittness());

        double[][] trainingData = {{1, 1}, {1, 0}, {0, 1}, {0, 0}};
        long sensitivity = Long.MAX_VALUE;
        System.out.println("1,1 0\t" + (finalNet.output(trainingData[0])[0] * sensitivity) / sensitivity);
        System.out.println("1,0 1\t" + (finalNet.output(trainingData[1])[0] * sensitivity) / sensitivity);
        System.out.println("0,1 1\t" + (finalNet.output(trainingData[2])[0] * sensitivity) / sensitivity);
        System.out.println("0,0 0\t" + (finalNet.output(trainingData[3])[0] * sensitivity) / sensitivity);

    }

    public static NeuralNetwork trainNets(NeuralNetwork[] networks, int iterations) {
        scoreNets(networks);
        NeuralNetwork[] nets = selection(networks);
        scoreNets(networks);
        if (iterations % 20 == 0)
            System.out.println(iterations + "  " + networks[0].getFittness());

        if (iterations == 0) {
            Main m = new Main();
            m.saveToFile(nets[0].getHiddenweights(), fileData.HIDDEN_WEIGHTS);
            m.saveToFile(nets[0].getOutputweights(), fileData.OUTPUT_WEIGHTS);
            return nets[0];
        } else {
            return trainNets(nets, (iterations - 1));
        }
    }

    private static NeuralNetwork[] scoreNets(NeuralNetwork[] networks) {
        double temp;
        //TODO hier eine gescheite funktion für beliebige output dimensionen
        for (NeuralNetwork net : networks) {
            net.setFittness(0);
            for (int iterations = 0; iterations < trainingData.length; iterations++) {
                temp = (trainingResult[iterations] - net.output(trainingData[iterations])[0]) * 1000;
                temp *= temp;
                net.addFittness(temp);
            }
        }
        Arrays.sort(networks);
        return networks;
    }


    /**
     * this is the main training function
     *
     * @param numberOfNNs
     * @param input
     * @param hidden
     * @param output
     * @return
     */
    private static NeuralNetwork train(int numberOfNNs, int input, int hidden, int output, int trainingIterations) {
        NeuralNetwork[] networks = newNNs(numberOfNNs, input, hidden, output);
        NeuralNetwork tempnet = trainNets(networks, trainingIterations);
        System.out.println(tempnet.getFittness());
        return tempnet;
    }

    /**
     * this method creates new nns
     *
     * @param numberOfNNs
     * @return
     */
    private static NeuralNetwork[] newNNs(int numberOfNNs, int input, int hidden, int output) {
        NeuralNetwork[] nns = new NeuralNetwork[numberOfNNs];
        Main m = new Main();
        nns[0] = new NeuralNetwork(m.readFromFile(fileData.HIDDEN_WEIGHTS), m.readFromFile(fileData.OUTPUT_WEIGHTS));
        for (int i = 1; i < numberOfNNs; i++) {
            nns[i] = new NeuralNetwork(input, hidden, output);
        }

        return nns;
    }

    /**
     * this method creates new nets e.g.
     *
     * @param networks
     * @return
     */
    private static NeuralNetwork[] selection(NeuralNetwork[] networks) {
        NeuralNetwork[] tempnets = new NeuralNetwork[2];
        int length = networks.length;
        NeuralNetwork[] returnnets = networks;
        //generate Networks with "parent Nets"
        for (int i = (int) Math.ceil(KEEP_ALLIVE*length); i < (int) Math.floor((1.0 - NEW_NETS)*length); i++) {
            tempnets[0] = networks[randNr(length)];
            tempnets[1] = networks[randNr(length)];
            returnnets[i] = new NeuralNetwork(tempnets[0].getHiddenweights(), tempnets[0].getOutputweights(), tempnets[1].getHiddenweights(), tempnets[1].getOutputweights());
        }
        //generate new networks
        for (int i = (int) Math.floor((1.0 - NEW_NETS)*length); i < length; i++) {
            returnnets[i] = new NeuralNetwork(networks[0].getHiddenweights().length, networks[0].getHiddenweights()[0].length, networks[0].getOutputweights()[0].length);
        }
        return returnnets;
    }

}
