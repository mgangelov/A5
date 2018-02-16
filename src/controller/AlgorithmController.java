package controller;

import algorithms.AbstractAlgorithm;
import controller.structures.AlgorithmStatusData;
import controller.structures.ExploredStatusData;
import controller.structures.StatusData;
import graph.Connection;
import graph.Graph;
import graph.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author dgj470
 * The algorithm controller object, the GUI will talk to this object, and it will return the correct data.
 * Acts like a model in MVC
 */

public class AlgorithmController {
    private Graph g;
    private StatusData data;
    private double currentStep;
    private List<AbstractAlgorithm> algorithms;
    private Node to;
    private Node from;

    /**
     */
    public AlgorithmController() {

        algorithms = new LinkedList<>();

    }

    /**
     * Run the algorithms to get the data
     */
    public void runAlgorithms() {

        List<AlgorithmStatusData> lalgoStat = new ArrayList<>();

        for (AbstractAlgorithm a : algorithms) { //Run every algorithm
            a.runAlgo();
            List<ExploredStatusData> lexplored = new ArrayList<>();


            a.getData().stream().forEach(cc -> lexplored.add(new ExploredStatusData((Connection) cc, 0)));

            lalgoStat.add(new AlgorithmStatusData(lexplored, a.getPath(), a.getCollectionMap(), a.getClass().toString()));
        }

        data = new StatusData(lalgoStat, g);
    }


    public int getAlgoCount() {
        return algorithms.size();
    }

    public void addAlgo(AbstractAlgorithm a) {
        algorithms.add(a);
    }

    public void clearAlgorithms() {
        algorithms.clear();
    }

    /**
     * This should only be called once, as the existing object is updated!
     *
     * @return The algorithm status data wrapper
     */
    public StatusData getState() {
        return data;
    }

    /**
     * @return The maximum playback length of all of the algorithms
     */
    public int getPlayBackLength() {
        int max = 0;
        int temp;
        for (AlgorithmStatusData c : data.getStatusData()) {
            temp = getLength(c);
            if (temp > max)
                max = temp;
        }
        return max;
    }

    /**
     * @param x Number of steps to step,
     */
    public void step(double x) { //do x steps forward or backward (negative is backward)
        currentStep += x;
        set(currentStep);
    }

    /**
     * @param x Jump to a specific position
     */
    public void set(double x) {
        if (x < 0) {
            x = 0;
        }
        if (x > this.getPlayBackLength()) {
            x = this.getPlayBackLength();
        }
        currentStep = x;
        for (AlgorithmStatusData a : data.getStatusData()) {
            a.changeProgress(x);
        }
    }

    public double getPos() {
        return currentStep;
    }

    /**
     * @param c Get the length of the specified algorithm
     * @return The length
     */
    public int getLength(AlgorithmStatusData c) {
        final int[] total = {0};

        c.getData().stream().forEach(e -> {
            total[0] += e.getConnection().getWeight();
        });

        return total[0];
    }

    public Graph getGraph() {
        return g;
    }

    public void setGraph(Graph g) {
        this.g = g;
    }

    public Node getTo() {
        return to;
    }

    public Node getFrom() {
        return from;
    }

    public void setTo(Node to) {
        this.to = to;
    }

    public void setFrom(Node from) {
        this.from = from;
    }
}