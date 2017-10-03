package basicMath;

public class Matrix {
    private double[][] mat;

    public Matrix(int a, int b) {
        //um eine   A x B  matrix zu erstellen
        /*
        [ [0,1,2],
          [0,2,1] ];
          das ist eine 2 x 3 Matrix  also height = 2 und width = 3
         */
        mat = new double[a][b];
    }

    public void setValues(double[][] inpMat) {
        if (inpMat.length == mat.length && inpMat[0].length == mat[0].length) {

            for (int A = 0; A < mat.length; A++) {
                for (int B = 0; B < mat[0].length; B++) {
                    mat[A][B] = inpMat[A][B];
                }
            }
        } else {
            System.out.println("Input Matrix != Output Matrix");
        }
    }

    public double getValues(int height, int width) {
        return this.mat[height][width];
    }

    public int lenght(boolean height) {
        //gibt entweder die breite oder höhe aus
        return height ? this.mat.length : this.mat[0].length;
    }

    public static double[] dotProductVM(Vector v, Matrix m) {
        double[] output = new double[m.lenght(false)];
        if (v.lenght() == m.lenght(true)) {
            /*
            [x1, x2]
            [y1, y2]
            [z1, z2]
            = [v1*x1 + v2*y1  + v3*z1,   v1*x2 + v2*y2 + v3*z2]
             */
            for (int width = 0; width < m.lenght(false); width++) {
                for (int height = 0; height < m.lenght(true); height++) {
                    output[width] += v.getValues(height) * m.getValues(height, width);
                }
            }
        }

        return output;
    }
}
//todo m.transpose usw