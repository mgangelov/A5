package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Implementation of Delaunay triangulation needed for generating a planar graph.
 *
 * @author mxa487
 */
public class DTriangulation {
    private List<Node> points;

    /**
     * Empty constructor for initialisation.
     */
    public DTriangulation() {

    }

    /**
     * Constructor which takes a list of the nodes which the triangulation should use.
     * @param points
     */
    public DTriangulation(List<Node> points) {
        this.points = points;
    }

    /**
     * A method which performs triangulation on a given list of nodes and a flag whether
     * it should generate a weighted graph.
     * @param points The list of nodes to be used
     * @param weighted Whether the generated graph should be weighted or not.
     * @return A List of connections.
     */
    public List<Connection> triangulate(List<Node> points, boolean weighted) {
        this.points = points;
        return triangulate(weighted);
    }

    /**
     * Triangulates the list of nodes set as a class variable.
     * @param weighted Flag whether the generated graph should be weighted or not. 
     * @return A List of connections
     */
    public List<Connection> triangulate(boolean weighted) {
        List<Connection> connections = new ArrayList<Connection>();
        int n = points.size();
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                for (int k = j + 1; k < n; k++) {
                    boolean isTriangle = true;
                    for (int a = 0; a < n; a++) {
                        if (a == i || a == j || a == k)
                            continue;
                        if (this.inside(points.get(a), points.get(i), points.get(j), points.get(k))) {
                            isTriangle = false;
                            break;
                        }
                    }
                    if (isTriangle) {
                        Connection con1 = null;
                        Connection con2 = null;
                        Connection con3 = null;
                        if (weighted) {
                            Random random = new Random();
                            con1 = new Connection(random.nextInt(100) + 1, points.get(i), points.get(j));
                            con2 = new Connection(random.nextInt(100) + 1, points.get(i), points.get(k));
                            con3 = new Connection(random.nextInt(100) + 1, points.get(j), points.get(k));
                        } else {
                            con1 = new Connection(100, points.get(i), points.get(j));
                            con2 = new Connection(100, points.get(i), points.get(k));
                            con3 = new Connection(100, points.get(j), points.get(k));
                        }
                        connections.add(con1);
                        connections.add(con2);
                        connections.add(con3);
                    }

                }
        return connections;
    }

    /**
     * A method which returns the circumcenter of a triangle formed of 3 nodes.
     * @param a First node
     * @param b Second node
     * @param c Third node
     * @return A MyPoint object
     */
    public MyPoint getTriangleCircumcenter1(Node a, Node b, Node c) {
        double dA = Math.pow(a.getX(), 2) + Math.pow(a.getY(), 2);
        double dB = Math.pow(b.getX(), 2) + Math.pow(b.getY(), 2);
        double dC = Math.pow(c.getX(), 2) + Math.pow(c.getY(), 2);
        double ox1 = dA * (c.getY() - b.getY()) + dB * (a.getY() - c.getY()) + dC * (b.getY() - a.getY());
        double ox2 = 2 * (a.getX() * (c.getY() - b.getY()) + b.getX() * (a.getY() - c.getY())
                + c.getX() * (b.getY() - a.getY()));

        double oy1 = -(dA * (c.getX() - b.getX()) + dB * (a.getX() - c.getX()) + dC * (b.getX() - a.getX()));
        double oy2 = ox2;

        double ox = ox1 / ox2;
        double oy = oy1 / oy2;

        return new MyPoint(ox, oy);

    }

    
    /**
     * A method which checks if a node i is in the space formed from nodes a, b and c
     * @param i Node object
     * @param a Node object
     * @param b Node object
     * @param c Node object
     * @return True if inside, false if not.
     */
    public boolean inside(Node i, Node a, Node b, Node c) {
        MyPoint center = this.getTriangleCircumcenter1(a, b, c);
        System.out.println("Center: " + center.getX() + " " + center.getY());
        double distance = center.distanceTo(new MyPoint(a.getX(), a.getY()));
        System.out.println("Distance: " + distance);
        System.out.println("center.x - dist: " + (center.getX() - distance));
        System.out.println("center.x + dist: " + (center.getX() + distance));
        System.out.println("center.y - dist: " + (center.getY() - distance));
        System.out.println("center.y + dist: " + (center.getY() + distance));
        System.out.println("First: " + (i.getX() > (center.getX() - distance)));
        System.out.println("Second: " + (i.getX() < (center.getX() + distance)));
        System.out.println("Third: " + (i.getY() > (center.getY() - distance)));
        System.out.println("Fourth: " + (i.getY() < (center.getY() + distance)));
        if ((i.getX() > (center.getX() - distance) && i.getX() < (center.getX() + distance))
                && (i.getY() > (center.getY() - distance) && i.getY() < (center.getY() + distance))) {
            return true;
        }
        return false;
    }
}

/**
 * A simple class which represents a point implementation. 
 * @author martin
 *
 */
class MyPoint {
    private double x;
    private double y;

    public MyPoint(double x, double y) {
        this.setX(x);
        this.setY(y);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    /**
     * Returns the distance from this instance of Point to some other Point
     * @param t Another Point object.
     * @return The distance to the parameter point.
     */
    public double distanceTo(MyPoint t) {
        double xx = Math.pow(x - t.getX(), 2);
        double yy = Math.pow(y - t.getY(), 2);
        return Math.sqrt(xx + yy);
    }
}