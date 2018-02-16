package algorithms.structures;

import graph.Connection;

/**
 * @author dgj470
 * A class to hold a connection, a cost and a heuristic
 */
public class HeuristicHolder {
    Connection c;
    double cost, heuristic;

    public HeuristicHolder(Connection c, double cost, double heuristic) {
        this.c = c;
        this.cost = cost;
        this.heuristic = heuristic;
    }

    public void setCost(double c) {
        cost = c;
    }

    public void setHeuristic(double h) {
        heuristic = h;
    }

    public double getCost() {
        return cost;
    }

    public double getHeuristic() {
        return heuristic;
    }

    public Connection getConnection() {
        return c;
    }

    public double getCostHeuristic() {
        return getHeuristic() + getCost();
    }

    public String toString() {
        return "[Connection: " + c + " Heuristic: " + heuristic + " Cost: " + cost + "]";
    }
}
