package graph;

/**
 * A generic implementation of a node, containing X and Y coordinates, the value of the node and some additional properties for forces needed
 * for the force-generation drawing algorithm.
 *
 * @param <A> The type of the node
 * @author mxa487, nxb484
 */
public class Node<A> {
    private A value;
    private double X;
    private double Y;
    private double xNetForce; //for coords generation
    private double yNetForce; //for coords generation
    private double xVelocity; //for coords generation
    private double yVelocity; //for coords generation

    /**
     * A constructor for a leaf node.
     *
     * @param value The value of the node
     */
    public Node(A value) {
        this.setValue(value);
        this.X = 200 + Math.random() * 300;
        this.Y = 100 + Math.random() * 200;
        this.xNetForce = 0;
        this.yNetForce = 0;
        this.xVelocity = 0;
        this.yVelocity = 0;
    }

    /**
     * A constructor with X and Y coords for visualisation.
     *
     * @param value
     * @param X     The X coord of the node.
     * @param Y     The Y coord of the node.
     */
    public Node(A value, double X, double Y) {
        this.setValue(value);
        this.X = X;
        this.Y = Y;
    }

    /**
     * Returns the value of the Node.
     *
     * @return The value of the Node.
     */
    public A getValue() {
        return value;
    }

    /**
     * Sets the value for the Node.
     *
     * @param value The value for the Node.
     */
    public void setValue(A value) {
        this.value = value;
    }

    /**
     * Returns the X coordinate
     *
     * @return X coord
     */
    public double getX() {
        return X;
    }

    /**
     * Returns the Y coordinate
     *
     * @return Y coords
     */
    public double getY() {
        return Y;
    }

    /**
     * @param x the x to set
     */
    public void setX(double x) {
        X = x;
    }

    /**
     * @param y the y to set
     */
    public void setY(double y) {
        Y = y;
    }

    /**
     * @return the xNetForce
     */
    public double getxNetForce() {
        return xNetForce;
    }

    /**
     * @param xNetForce the xNetForce to set
     */
    public void setxNetForce(double xNetForce) {
        this.xNetForce = xNetForce;
    }

    /**
     * @return the yNetForce
     */
    public double getyNetForce() {
        return yNetForce;
    }

    /**
     * @param yNetForce the yNetForce to set
     */
    public void setyNetForce(double yNetForce) {
        this.yNetForce = yNetForce;
    }

    /**
     * @return the xVelocity
     */
    public double getxVelocity() {
        return xVelocity;
    }

    /**
     * @param xVelocity the xVelocity to set
     */
    public void setxVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    /**
     * @return the yVelocity
     */
    public double getyVelocity() {
        return yVelocity;
    }

    /**
     * @param yVelocity the yVelocity to set
     */
    public void setyVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }

    /**
     * @return the value stored within the node for visualisation
     */
    public String toString() {
        return "Value: " + value.toString() + " X: " + X + " Y: " + Y;
    }
	
	
	public boolean equals(Node n) {
		return this.getValue().equals(n.getValue()) && this.getX() == n.getX() && this.getY() == n.getY();	
	}
}