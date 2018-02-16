package algorithms.implementedAlgorithms;

import algorithms.structures.MyStack;
import controller.AlgorithmController;

/**
 * @author dgj470
 * An implementation of the generic search that uses a stack
 */
public class DepthFirst extends GenericSearch {
    /**
     * @param a The algorithm controller the algorithm runs on
     */
    public DepthFirst(AlgorithmController a) {
        super(new MyStack(a), a);
    }

}
