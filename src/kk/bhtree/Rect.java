package kk.bhtree;

public class Rect {

    private final double x;
    private final double y;
    private final double width;
    private final double height;

    public Rect(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public boolean inside(Point p) {
        return inside(p.getX(), p.getY());
    }

    public boolean inside(double x, double y) {
        return x >= this.x
                && x < this.x + width
                && y >= this.y
                && y < this.y + height;
    }
}
