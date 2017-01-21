package kk.bhtree;

public class Tree {

    final Node root;
    private final int maxDepth;
    private int depth = 1;
    private int nodes = 0;

    public Tree(Rect bounds, int maxDepth) {
        if (maxDepth < 1)
            throw new IllegalArgumentException("Invalid maxDepth: " + maxDepth);

        root = new Node(bounds, 0);
        this.maxDepth = maxDepth;
    }

    class Node extends MassCenter {

        final Rect bounds;
        final int level;

        Node ul, ur, ll, lr;
        boolean external = true;

        Node(Rect bounds, int level) {
            this.bounds = bounds;
            this.level = level;

            if (depth < level + 1)
                depth = level + 1;

            nodes++;
        }

        Node prepareSubTreeFor(PointMass m) {
            double nw = bounds.getWidth() / 2;
            double nh = bounds.getHeight() / 2;
            double x = bounds.getX();
            double y = bounds.getY();
            double mx = x + nw;
            double my = y + nh;

            if (m.getX() < mx)
                if (m.getY() < my) {
                    if (ul == null) {
                        ul = new Node(new Rect(x, y, nw, nh), level + 1);
                        external = false;
                    }
                    return ul;
                } else {
                    if (ll == null) {
                        ll = new Node(new Rect(x, y + nh, nw, nh), level + 1);
                        external = false;
                    }
                    return ll;
                }

            if (m.getY() < my) {
                if (ur == null) {
                    ur = new Node(new Rect(x + nw, y, nw, nh), level + 1);
                    external = false;
                }
                return ur;
            } else {
                if (lr == null) {
                    lr = new Node(new Rect(x + nw, y + nh, nw, nh), level + 1);
                    external = false;
                }
                return lr;
            }
        }
    }

    public void add(PointMass m) {
        Node node = root;

        if (!node.bounds.inside(m.getX(), m.getY())) {
            throw new IllegalArgumentException("kk.bhtree.Point out of bounds: (" + m.getX() + ", " + m.getY() + ")");
        }

        while (!node.external) {
            node.add(m);
            node = node.prepareSubTreeFor(m);
        }

        if (node.getMass() == 0 || node.level == maxDepth - 1) {
            node.add(m);
            return;
        }

        PointMass old = PointMass.copyPointMass(node);
        node.add(m);

        Node oldTree = node.prepareSubTreeFor(old);
        Node mTree = node.prepareSubTreeFor(m);

        while (oldTree == mTree && oldTree.level != maxDepth - 1) {
            oldTree.reset(node);
            oldTree = oldTree.prepareSubTreeFor(old);
            mTree = mTree.prepareSubTreeFor(m);
        }

        oldTree.add(old);
        mTree.add(m);
    }

    public int getDepth() {
        return depth;
    }

    public int getNodes() {
        return nodes;
    }
}
