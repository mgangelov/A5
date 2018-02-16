package algorithms.structures;

/**
 * @author dgj470
 */


import controller.AlgorithmController;

import java.util.LinkedList;


public class MyStack extends GenericCollection {


    public MyStack(AlgorithmController a) {
        super(new LinkedList<>(), a); //pass the base collection to the superconstructor
    }

    /**
     * @return The top element on the stack
     */
    @Override
    public HeuristicHolder pop() {
        return collection.pop();
    }

    /**
     * @param item put the element on the top of the stack
     */
    @Override
    public void push(HeuristicHolder item) {
        collection.push(item);
    }

}
