package algorithms.implementedAlgorithms;

import algorithms.structures.GreedyQueue;
import controller.AlgorithmController;

/**
 * @author dgj470
 * An implementation of the generic search that uses a priority queue sorted by heuristic
 */
public class GreedyBFS extends GenericSearch {
    /**
     * @param a The algorithm controller the algorithm runs on
     */
    public GreedyBFS(AlgorithmController a) {
        super(new GreedyQueue(a), a);
    }

}
