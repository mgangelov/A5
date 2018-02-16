package algorithms.structures;

import controller.AlgorithmController;

import java.util.LinkedList;

/**
 * @author dgj470
 * A basic queue
 */
public class MyQueue extends GenericCollection {

    public MyQueue(AlgorithmController a) {
        super(new LinkedList<>(), a); //pass the base collection to the superconstructor
    }

    /**
     * @return The element at the front of the queue
     */
    @Override
    public HeuristicHolder pop() {
        return collection.poll();
    }

    /**
     * @param item Add the item to back of the queue
     */
    @Override
    public void push(HeuristicHolder item) {
        collection.add(item);
    }

}

