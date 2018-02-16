package algorithms.structures;

/**
 * @author dgj470
 */

import controller.AlgorithmController;

import java.util.LinkedList;
import java.util.List;

/**
 * A basic generic collection
 */
public abstract class GenericCollection {
    protected LinkedList<HeuristicHolder> collection;

    public GenericCollection(LinkedList<HeuristicHolder> collection, AlgorithmController a) {
        this.collection = collection;
    }

    /**
     * @return The next element
     */
    public abstract HeuristicHolder pop();

    /**
     * @param item Add the item to the collection
     */
    public abstract void push(HeuristicHolder item);

    /**
     * @return the string form of the collection
     */
    public String toString() {
        //return collection.toString();
        List<Integer> dest = new LinkedList<>();
        for (HeuristicHolder h:this.getList()){
            dest.add((int)h.getConnection().getDest().getValue());
        }
        return dest.toString();
    }

    /**
     * @param o An item in the collection
     * @return whether it is contained in the collection
     */
    public boolean contains(Object o) {
        return collection.contains(o);
    }

    /**
     * @return if the collection is empty
     */
    public boolean isEmpty() {
        return collection.isEmpty();
    }

    /**
     * @return the size of the collection
     */
    public int size() {
        return collection.size();
    }

    /**
     * Empty the collection
     */
    public void clear() {
        collection.clear();
    }

    /**
     * @return the collection in list form
     */
    public List<HeuristicHolder> getList() {
        return collection;
    }
}