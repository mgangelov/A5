package algorithms.implementedAlgorithms;

import algorithms.structures.APriorityQueue;
import controller.AlgorithmController;

/**
 * @author dgj470
 * An implementation of the generic search that uses a priority queue sorted by cost and heuristic
 */
public class AStarSearch extends GenericSearch {
    /**
     * @param a The algorithm controller the algorithm runs on
     */
    public AStarSearch(AlgorithmController a) {
        super(new APriorityQueue(a), a);
    }
}
