package algorithms.structures;

import controller.AlgorithmController;
import graph.Node;

import java.util.Collections;

/**
 * @author dgj470
 */

public class DijkstraQueue extends APriorityQueue {

    public DijkstraQueue(AlgorithmController a) {
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
     * sort by the cost so far
     */
    private void sort() {
        Collections.sort(collection, (c1, c2) -> (int) (c1.getCost() - c2.getCost()));
    }

    /**
     * The heuristic is always -1 as it doesn't exist in this context
     * @param a The node
     * @return -1
     */
    @Override
    protected double heuristic(Node a) {
        return -1;
    }
}