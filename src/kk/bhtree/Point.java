package kk.bhtree;

public interface Point {
    double getX();
    double getY();

    default double distSq(Point p) {
        double dx = p.getX() - getX();
        double dy = p.getY() - getY();

        return (dx * dx + dy * dy);
    }
}
