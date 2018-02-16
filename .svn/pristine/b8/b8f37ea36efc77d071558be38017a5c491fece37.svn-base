package controller.structures;

import graph.Connection;

/**
 * @author dgj470
 * A data class that contains a connection, and how much it is completed and a few getters
 */
public class ExploredStatusData {
    Connection c;
    double completed;

    public ExploredStatusData(Connection c, double completed) {
        this.c = c;
        this.completed = completed;
    }

    public Connection getConnection() {
        return c;
    }

    public double getProgress() {
        return completed;
    }

    protected double getLength() {
        return c.getWeight();
    }

    public String toString() {
        return "[" + c + "," + completed + "]";
    }

    protected void setProgress(double i) {
        this.completed = i;
    }
}
