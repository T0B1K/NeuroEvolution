package basicMath;

public class Vector {
    private double[] vec;

    public Vector(int dimension) {
        this.vec = new double[dimension];
    }

    public void setValues(double[] val) {
        for (int i = 0; i < vec.length; i++) {
            vec[i] = (i > val.length) ? 0 : val[i];
        }
    }

    public int lenght() {
        return this.vec.length;
    }

    public double getValues(int i) {
        return this.vec[i];
    }

    public static double[] sub(Vector v1, Vector v2) {
        double[] ov = new double[v1.lenght()];

        if (v1.lenght() == v2.lenght()) {
            for (int i = 0; i < v1.lenght(); i++) {
                ov[i] = v1.getValues(i) - v2.getValues(i);
            }

        } else {
            System.out.println("Haben nicht die selbe Vektor dimension");
        }
        return ov;
    }

    public static double weight(Vector v1) {
        double discriminant = 0;
        for (int i = 0; i < v1.lenght(); i++) {
            discriminant += v1.getValues(i) * v1.getValues(i);
        }
        return Math.round(Math.sqrt(discriminant * 1000)) / 1000;
    }
}
//todo kreeuzprodukt; usw