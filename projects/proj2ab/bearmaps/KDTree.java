package bearmaps;

import java.util.List;

public class KDTree implements PointSet {
    private class Node {
        private Point item;
        private int level = 0;
        private Node left, right;
        public Node(Point p) {
            item = p;
        }

        public Node(Point p, int level) {
            item = p;
            this.level = level;
        }

        private void add(Point p, int level) {
            if (level % 2 == 0) {
                if (p.getX() < item.getX()) {
                    if (left == null) {
                        left = new Node(p, level + 1);
                    } else {
                        left.add(p, level + 1);
                    }
                } else {
                    if (right == null) {
                        right = new Node(p, level + 1);
                    } else {
                        right.add(p, level + 1);
                    }
                }
            } else {
                if (p.getY() < item.getY()) {
                    if (left == null) {
                        left = new Node(p, level + 1);
                    } else {
                        left.add(p, level + 1);
                    }
                } else {
                    if (right == null) {
                        right = new Node(p, level + 1);
                    } else {
                        right.add(p, level + 1);
                    }
                }
            }
        }

        public void add(Point p) {
            add(p, 0);
        }

        public double distance(Point goal) {
            return Point.distance(item, goal);
        }
    }

    private Node kdTree;
    public KDTree(List<Point> points) {
        kdTree = new Node(points.get(0));
        for (int i = 1; i < points.size(); i++) {
            kdTree.add(points.get(i));
        }
    }

    private Node nearest(Node n, Point goal, Node best) {
        Node goodSide, badSide;
        if (n == null) {
            return best;
        } else if (n.distance(goal) < best.distance(goal)) {
            best = n;
        }
        if (n.level % 2 == 0) {
            if (goal.getX() < n.item.getX()) {
                goodSide = n.left;
                badSide = n.right;
            } else {
                goodSide = n.right;
                badSide = n.left;
            }
        } else {
            if (goal.getY() < n.item.getY()) {
                goodSide = n.left;
                badSide = n.right;
            } else {
                goodSide = n.right;
                badSide = n.left;
            }
        }
        best = nearest(goodSide, goal, best);
        if (n.level % 2 == 0) {
            if (Math.pow(n.item.getX() - goal.getX(), 2) < best.distance(goal)) {
                best = nearest(badSide, goal, best);
            }
        } else {
            if (Math.pow(n.item.getY() - goal.getY(), 2) < best.distance(goal)) {
                best = nearest(badSide, goal, best);
            }
        }
        return best;
    }

    @Override
    public Point nearest(double x, double y) {
        return nearest(kdTree, new Point(x, y), kdTree).item;
    }

    public static void main(String[] args) {
        Point A = new Point(2, 3);
        Point B = new Point(4, 2);
        Point C = new Point(4, 5);
        Point D = new Point(3, 3);
        Point E = new Point(1, 5);
        Point F = new Point(4, 4);

        KDTree k = new KDTree(List.of(A, B, C, D, E, F));
        Point nearest = k.nearest(0, 7);
        System.out.println(nearest.getX());
        System.out.println(nearest.getY());
    }
}
