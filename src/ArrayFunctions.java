import java.io.*;

public abstract class ArrayFunctions {
    private static final String filename = "hiddenWeights.txt";
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public enum fileData {
        HIDDEN_WEIGHTS {
            public String toString() {
                return "hiddenWeights.txt";
            }
        },

        OUTPUT_WEIGHTS {
            public String toString() {
                return "outputWeights.txt";
            }
        }
    }

    /**
     * saves the Arraylist to a File
     *
     * @param saveToFile
     */
    protected void saveToFile(double[][] saveToFile, fileData fd) {
        try {
            this.oos = new ObjectOutputStream(new FileOutputStream(fd.toString()));
            this.oos.writeObject(saveToFile);
            this.oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * reads the arraylist from the file
     *
     * @return array
     */
    protected double[][] readFromFile(fileData fd) {
        double[][] retArray = null;
        try {
            this.ois = new ObjectInputStream(new FileInputStream(fd.toString()));
            retArray = (double[][]) this.ois.readObject();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return retArray;
    }


    /**
     * Matrix multiplication method.
     *
     * @param m1 Multiplicand
     * @param m2 Multiplier
     * @return Product
     */
    public static double[][] matrixMultiplication(double[][] m1, double[][] m2) {
        int m1ColLength = m1[0].length; // m1 columns length
        int m2RowLength = m2.length;    // m2 rows length
        if (m1ColLength != m2RowLength) return null; // matrix multiplication is not possible
        int mRRowLength = m1.length;    // m result rows length
        int mRColLength = m2[0].length; // m result columns length
        double[][] mResult = new double[mRRowLength][mRColLength];
        for (int i = 0; i < mRRowLength; i++) {         // rows from m1
            for (int j = 0; j < mRColLength; j++) {     // columns from m2
                for (int k = 0; k < m1ColLength; k++) { // columns from m1
                    mResult[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return mResult;
    }

    /**
     * returns a random number of the given length with use of the square
     *
     * @param length
     * @return
     */
    protected static int randNr(int length) {
        double RANDOM_FACTOR = 0.25;
        double randomDouble = Math.sqrt(length - 1) * Math.random();
        return (int) Math.round(randomDouble);
    }


}
