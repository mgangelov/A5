package controller.structures;

import algorithms.structures.HeuristicHolder;
import graph.Node;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author dgj470
 * The holds all of the current data about the current state of one algorithm
 */
public class AlgorithmStatusData {
    List<ExploredStatusData> ex;
    List<Node> path;
    Map<Integer, LinkedList<HeuristicHolder>> h;
    LinkedList<HeuristicHolder> frontier;
    int algoType;


    /**
     * @param ex   A list of explored status data
     * @param path The path to the end
     */
    public AlgorithmStatusData(List<ExploredStatusData> ex, List<Node> path, Map<Integer, LinkedList<HeuristicHolder>> h, String algoType) {
        this.ex = ex;
        this.path = path;
        this.h = h;
        changeProgress(0);
        switch (algoType) { //set the algorithm type for processing later
            case "class algorithms.implementedAlgorithms.BreadthFirst":
                this.algoType = 0;
                break;
            case "class algorithms.implementedAlgorithms.DepthFirst":
                this.algoType = 1;
                break;
            case "class algorithms.implementedAlgorithms.GreedyBFS":
                this.algoType = 2;
                break;
            case "class algorithms.implementedAlgorithms.DijkstraSearch":
                this.algoType = 3;
                break;
            case "class algorithms.implementedAlgorithms.AStarSearch":
                this.algoType = 4;
                break;
        }
    }


    public List<ExploredStatusData> getData() {
        return ex;
    }

    public String toString() {
        return ex.toString();
    }

    /**
     * @param i The explored statusdata to get
     * @return The explored status data
     */
    public ExploredStatusData getData(int i) {
        return ex.get(i);
    }

    /**
     * @param i Change the current progress of the algorithm
     */
    public void changeProgress(double i) {
        frontier = h.get((int) i); //set the frontier to the right value

        for (ExploredStatusData e : ex) { //iterate through the ExploredStatusData and set their progress
            if (e.getLength() <= i) {
                e.setProgress(e.getLength());
                i -= e.getLength();
            } else {
                e.setProgress(i);
                i = 0;
            }

        }

    }

    public List<Node> getPath() {
        return path;
    }

    public List<HeuristicHolder> getFrontier() {
        return frontier;
    }

    /**
     * Returns the algorithm type
     * 0 = breadth first
     * 1 = depth first
     * 2 = Greedy best first
     * 3 = Dijkstras
     * 4 = A*
     */
    public int getAlgoType() {
        return algoType;
    }
}
