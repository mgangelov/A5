package algorithms;

import algorithms.structures.HeuristicHolder;
import controller.AlgorithmController;
import graph.Connection;
import graph.Graph;
import graph.Node;

import java.util.*;

/**
 * @author dgj470
 * An abstract implementation of an algorithm
 */

public abstract class AbstractAlgorithm<A> {
    protected Graph g;
    protected Node from;
    protected Node to;
    protected AlgorithmController a;
    protected ArrayList<Connection> data;
    protected List<Node<A>> path;
    public Map<Integer, LinkedList<HeuristicHolder>> collectionMap;

    /**
     * @param a The controller the algorithm runs on
     */
    public AbstractAlgorithm(AlgorithmController a) {
        this.data = new ArrayList<>();
        this.path = new ArrayList<>();
        collectionMap = new LinkedHashMap<>();
        this.a = a;
    }

    /**
     * create an array of connections in the order they are explored
     * The first connection will be from null, and to the startNode, the last will be to null, and from the endNode
     * (creating not returning as it means we can get the data multiple times)
     */
    public void runAlgo() {
        this.g = a.getGraph();
        this.from = a.getFrom();
        this.to = a.getTo();
    }

    ; //assign the result to data

    /**
     * @return The List of Connection Data
     */
    public List<Connection> getData() {
        return data;
    }

    /**
     * @return The path
     */
    public List<Node<A>> getPath() {
        return path;
    }

    /**
     * @return the map that stores the frontier at different states
     */
    public Map<Integer, LinkedList<HeuristicHolder>> getCollectionMap() {
        return collectionMap;
    }
}