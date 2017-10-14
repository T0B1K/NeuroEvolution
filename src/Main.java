
public class Main {

    public static void main(String[] args) {


        NeuralNetwork nn = new NeuralNetwork(3, 4, 2);
        //Pi/3 ist ganz oben und -pi/3 ist ganz unten
        double winkel = (Math.PI * 2 / 3 * Math.random()) - Math.PI / 3;
        double betrag = 50* Math.random();
        double[] erwuenschterZug;
        //todo hier noch n bischen zwug machen
        //todo jetzt noch trainieren

        double[] inp = {winkel, betrag, 3};
        double[] outp = nn.fullBatchTraining(inp);

        for (int i = 0; i < outp.length; i++) {
            System.out.println(outp[i]);
        }

    }


}
