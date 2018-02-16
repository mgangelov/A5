package controller.structures;

import graph.Graph;

import java.util.List;

/**
 * @author dgj470
 * A data class that contains a graph and a list of algorithm status data
 * Acts like a view in MVC
 */

// This class is passed from the Algorithm controller back to the ui.
public class StatusData {
    private List<AlgorithmStatusData> algorithms;
    private Graph g;

    public StatusData(List<AlgorithmStatusData> algorithms, Graph g) {
        this.algorithms = algorithms;
        this.g = g;
    }


    public Graph getGraph() {
        return g;
    }


    public List<AlgorithmStatusData> getStatusData() {
        return algorithms;
    }

    public String toString() {
        String s = "[";
        for (AlgorithmStatusData a : algorithms) {
            s += "[" + a.toString() + "]";
        }
        s += "]";
        return s;
    }

    /**
     * @param i The algorithm # to get data
     * @return That data of algorithm i
     */
    public AlgorithmStatusData getAlgorithmInfo(int i) {
        return getStatusData().get(i);
    }
}
