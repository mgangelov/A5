package algorithms.implementedAlgorithms;

import algorithms.structures.GenericCollection;
import algorithms.structures.HeuristicHolder;
import algorithms.structures.MyStack;
import controller.AlgorithmController;
import graph.Connection;
import graph.Node;

import java.util.*;

/**
 * @author dgj470
 *         A generic search class that takes in the type of frontier that it searches with
 */
public abstract class GenericSearch<A> extends algorithms.AbstractAlgorithm {


    protected GenericCollection frontier;
    private LinkedHashMap<Node<A>, Node<A>> parentTraceMap = new LinkedHashMap<>();
    private int stepCount = 0;


    /**
     * @param a        The algorithm controller that the algorithm runs on
     * @param frontier The frontier that is used for this instance of search
     */
    public GenericSearch(GenericCollection frontier, AlgorithmController a) {
        super(a);
        this.frontier = frontier;

    }

    /**
     * Run the algorithm, and put the result in data and path.
     */
    public void runAlgo() {
        super.runAlgo();
        Set<Node<A>> visited = new HashSet<>();

        frontier.clear(); // make sure the frontier doesn't contain any stale nodes

        frontier.push(new HeuristicHolder(new Connection(0, new Node<>(-9999), from), -1, -1)); //the initial node, -9999 is so it's not drawn.
        while (!frontier.isEmpty()) {


            LinkedList<HeuristicHolder> frontiercopy = new LinkedList<>();


            List<Node> added = new LinkedList<>();

            for (HeuristicHolder h : frontier.getList()) { //get the current frontier to store, removing duplicates etc
                if (!visited.contains(h.getConnection().getDest())) {
                    if(!added.contains(h.getConnection().getDest())) {
                        frontiercopy.add(h);
                        added.add(h.getConnection().getDest());
                    }
                }
            }

            Connection con = frontier.pop().getConnection(); //get the next connection to explore


            // visit check
            if (!visited.contains(con.getDest())) {
                visited.add(con.getDest()); //if it's not been visited, visit it

                //store the current frontier
                for (int i = stepCount; i <= stepCount + con.getWeight(); i++) {
                    collectionMap.put(i, frontiercopy); //from the previous collection length to the length of the collection populate a map.
                    // This isn't too bad as it is optimised by the compiler and everything is stored by reference.
                }
                stepCount += con.getWeight(); //increment the step counter by the length of the connection


                data.add(con); //add it to data

                // goal check
                if (visited.contains(to)) {
                    parentTraceMap.put(to, con.getInit()); //put the initial node on
                    path = tracePath(to, parentTraceMap);
                    return;
                }


                // add children
                ArrayList<Connection> children = new ArrayList<>();
                g.getConnections().stream().forEach(c -> {
                    if (((Connection) c).getInit() == con.getDest()) {
                        children.add((Connection) c);
                    }
                });

                for (Connection child : children) { //for each child
                    if (!visited.contains(child.getDest())) { //if it's not been visited
                        frontier.push(new HeuristicHolder(child, -1, -1)); //add it to the frontier
                        if (parentTraceMap != null) {
                            if (!(parentTraceMap.containsKey(child.getDest()) && !(frontier instanceof MyStack))) { //really hacky but soz
                                parentTraceMap.put(child.getInit(), con.getInit()); //add it to the path tracer
                            }
                        }
                    }
                }
            }
        }
    }


    /**
     * @param node The end node
     * @param path The map of child->parent gathered from the search
     * @return The path
     */
    private List<Node<A>> tracePath(Node<A> node, Map<Node<A>, Node<A>> path) {

        List<Node<A>> completePath = new ArrayList<>();
        completePath.add(node); // the end node

        while (path.containsKey(node)) { //trace the map backward, so finding the route taken
            node = path.get(node);
            completePath.add(node);
        }

        reverse(completePath); //this gets the route in reverse

        completePath.remove(0); //the 'initial' node needs to be removed

        return completePath;
    }

    /**
     * Reverses the given list
     *
     * @param list A list to reverse
     */
    private void reverse(List<Node<A>> list) {
        for (int i = 0; i < list.size() / 2; i++) {
            Node<A> temp = list.get(i);
            int j = list.size() - i - 1;

            list.set(i, list.get(j));
            list.set(j, temp);
        }
    }


}