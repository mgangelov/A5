package graph;

/**
 * A class to implement a weighted directed connection between two nodes.
 * This version does not use generics so it can only be used for its sole purpose of
 * a connection.
 *
 * @author mxa487, nxb484
 */
public class Connection {
    private double weight;
    private Node init;
    private Node dest;

    /**
     * Initialise a connection to a destination Node with a weight.
     *
     * @param weight The weight of the route.
     * @param init   The initial node.
     * @param dest   The destination of the Node.
     */
    public Connection(double weight, Node init, Node dest) {
        setWeight(weight);
        setInit(init);
        setDest(dest);
    }

    /**
     * Returns the weight of the currrent rout
     *
     * @return Weight of current route.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets the weight of the route. Negative weights are not allowed, so
     * they are instead substituted with 0.
     *
     * @param weight A number bigger than 0.
     */
    public void setWeight(double weight) {
        if (weight >= 0) {
            this.weight = weight;
        } else {
            System.out.println("Weight<0, therefore weight set to 0.");
            this.weight = 0;
        }

    }


    /**
     * Get the initial point of the route.
     *
     * @return The initial Node.
     */
    public Node getInit() {
        return init;
    }

    /**
     * Get the destination of the route.
     *
     * @return The destination Node.
     */
    public Node getDest() {
        return dest;
    }

    /**
     * Set the destination of the route.
     *
     * @param dest A destination Node.
     */
    public void setInit(Node init) {
        this.init = init;
    }

    /**
     * Set the destination of the route.
     *
     * @param dest A destination Node.
     */
    public void setDest(Node dest) {
        this.dest = dest;
    }

    /**
     * Outputs a textual representation of the connection
     */
    public String toString() {
        return "(From: " + init.toString() + ", To: " + dest.toString() + ", Weight: " + weight + ")";
    }
}
