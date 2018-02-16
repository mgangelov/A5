package algorithms.structures;

import controller.AlgorithmController;
import graph.Connection;
import graph.Node;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * An A* priority queue that sorts by euclidean distance to the destination + the cost so far
 * @author dgj470
 */
public class APriorityQueue extends GenericCollection {

    LinkedHashMap<Node, Double> cost = new LinkedHashMap<>(); //cost tracer
    AlgorithmController a;

    /**
     *  Create a priority queue to search with
     * @param a The algorithm controller that uses this
     */
    public APriorityQueue(AlgorithmController a) {
        super(new LinkedList<>(), a); //pass the base collection to the superconstructor
        this.a = a;
    }

    /**
     * Every time pop is called, the list is sorted
     * @return The best element according to the cost and heurisic
     */
    @Override
    public HeuristicHolder pop() {
        sort();
        return collection.poll();
    }

    /**
     * Sort the collection according to the cost and heurisic
     */
    private void sort() {
        Collections.sort(collection, (c1, c2) -> (int) (c1.getCostHeuristic() - c2.getCostHeuristic()));
    }

    /**
     * Returns the cost of a given node
     * @param node
     * @return The cost to get to it
     */
    private double getCost(Node node) {
        if (cost.containsKey(node)) {
            return cost.get(node);
        }
        return -1; //not found
    }

    /**
     * Add an element to the collection
     * @param element the element to add
     */
    @Override
    public void push(HeuristicHolder element) {

        if (!cost.containsKey(element.getConnection().getInit())) { //if the initial part of the connection is not found it must be the start node
            cost.put(element.getConnection().getInit(), 0.); //so cost so far is 0.
        }

        double distance = distance(element.getConnection()); //the distance to get to this element

        cost.put(element.getConnection().getDest(), distance); //add it in the cost tracer
        element.setCost(distance); //set the cost in the element
        element.setHeuristic(heuristic(element.getConnection().getDest())); //set the heuristic in the element

        collection.add(element); //add it to the collection
    }

    /**
     * Calculate the euclidian distance from a node to the end
     * @param current The node to calculate the distance from to the end
     * @return The distance
     */
    protected double heuristic(Node current) {
        return Math.sqrt(Math.pow(current.getX() - a.getTo().getX(), 2) + Math.pow(current.getY() - a.getTo().getY(), 2)) * 100;
    }

    /**
     * Calculate the shortest route to an element
     * @param c The connection to calculate the route to
     * @return The shortest route so far
     */
    protected double distance(Connection c) {
        if (getCost(c.getDest()) > c.getWeight() + getCost(c.getInit()) || getCost(c.getDest()) == -1) { //if the stored cost is greater than the calculated cost
            return (c.getWeight() + getCost(c.getInit())); //return the calculated cost
        } else {
            return getCost(c.getDest()); //return the stored cost
        }

    }

    /**
     * Sort the list before returning it
     * @return The list that all the elements are in
     */
    @Override
    public List<HeuristicHolder> getList() {
        sort();
        return super.getList();
    }
}
