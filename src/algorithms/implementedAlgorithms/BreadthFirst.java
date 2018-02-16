package algorithms.implementedAlgorithms;

import algorithms.structures.MyQueue;
import controller.AlgorithmController;

/**
 * @author dgj470
 * An implementation of the generic search that uses a queue
 */
public class BreadthFirst extends GenericSearch {
    /**
     * @param a The algorithm controller the algorithm runs on
     */
    public BreadthFirst(AlgorithmController a) {
        super(new MyQueue(a), a);
    }

}
