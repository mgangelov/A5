package algorithms.structures;

import controller.AlgorithmController;
import graph.Connection;

import java.util.Collections;

/**
 * An greedy priority queue that sorts by the minimum heuristic
 * @author dgj470
 */
public class GreedyQueue extends APriorityQueue {

    public GreedyQueue(AlgorithmController a) {
        super(a);
    }


    /**
     * Sort and the return the best element
     * @return the best element
     */
    public HeuristicHolder pop() {
        sort();
        return collection.poll();
    }

    /**
     * sort by the minimum heuristic
     */
    private void sort() {
        Collections.sort( collection, (c1, c2) -> (int) (c1.getHeuristic() - c2.getHeuristic()));
    }

    /**
     * The distance is always -1 as it doesn't exist in this context
     * @param c The connection
     * @return -1
     */
    @Override
    protected double distance(Connection c) {
        return -1;
    }
}