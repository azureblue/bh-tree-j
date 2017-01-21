package kk.bhtree;

public class MassCenter implements PointMass {
    private double x = 0;
    private double y = 0;
    private double mass = 0;

    public MassCenter() {
    }

    public MassCenter(PointMass m) {
        reset(m);
    }

    public void add(PointMass m) {
        update(m.getX(), m.getY(), m.getMass());
    }

    public void subtract(PointMass m) {
        update(m.getX(), m.getY(), -m.getMass());
    }

    public void reset(PointMass m) {
        this.x = m.getX();
        this.y = m.getY();
        this.mass = m.getMass();
    }

    private void update(double x, double y, double mass) {
        double sum = this.mass + mass;
        if (sum != 0) {
            this.x = (this.x * this.mass + x * mass) / sum;
            this.y = (this.y * this.mass + y * mass) / sum;
        }
        this.mass = sum;
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
}
