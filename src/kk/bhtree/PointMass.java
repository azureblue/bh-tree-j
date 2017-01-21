package kk.bhtree;

public interface PointMass extends Point {

    double getMass();

    static PointMass copyPointMass(PointMass m) {
        return pointMass(m.getX(), m.getY(), m.getMass());
    }

    static PointMass pointMass(double x, double y, double mass) {
        return new PointMass() {

            @Override
            public String toString() {
                return "kk.bhtree.PointMass("+ x + ", " + y + ", m=" + mass + ")";
            }

            @Override
            public double getX() {
                return x;
            }

            @Override
            public double getY() {
                return y;
            }

            @Override
            public double getMass() {
                return mass;
            }
        };
    }
}
