package testing;

import algorithms.implementedAlgorithms.AStarSearch;
import controller.AlgorithmController;
import controller.structures.AlgorithmStatusData;
import controller.structures.ExploredStatusData;
import controller.structures.StatusData;
import graph.Connection;
import graph.ExampleGraphs;
import graph.Graph;
import graph.Node;

import java.util.List;

/**
 * @author dgj470
 *
 * A class that I used to test all aspects of the backend data, and as examples of how it should be used
 */
public class AlgorithmTest {
    public static void main(String[] args) {
        gettingDataExample();
        //drawExample();

    }

    private static void gettingDataExample() {
        ExampleGraphs e = new ExampleGraphs();
        Graph<Integer> g = e.planarGraph15();
        Node<Integer> first = g.getNodes().get(0);
        Node<Integer> last = g.getNodes().get(4); //working out what data to use


        AlgorithmController a = new AlgorithmController(); //add the algorithm(s) to the controller
        a.addAlgo(new AStarSearch(a));

        StatusData s = a.getState(); //get the state, only needs to be run

        System.out.println("Everything! " + s); //print everything out
        System.out.println("Algorithm 1: " + s.getAlgorithmInfo(0).getData()); //print algorithm 0 out
        System.out.println("The third node: " + s.getAlgorithmInfo(0).getData().get(3)); //print out the third part of it
        System.out.println("The progress to the third node: " + s.getAlgorithmInfo(0).getData().get(3).getProgress()); //get the progress of the third part

        for (int i = 0; i < 200; i++) {
            a.step(25);
            System.out.println((i + 1) * 1 + " steps in = " + s.getAlgorithmInfo(0)); //can step 25 at a time (speed 25?)
        }

        System.out.println();

        a.set(365);
        System.out.println("365 steps in = " + s.getAlgorithmInfo(0)); //go to position 365 (going to a specific position?)

        System.out.println();

        for (int i = 0; i < 20; i++) {
            a.step(-10);
            System.out.println((365 - (i + 1) * 10) + " steps in = " + s.getAlgorithmInfo(0)); //going back 10 at a time (rewinding?)
        }

        System.out.println();

        System.out.println("The total runtime of the algorithms is: " + a.getPlayBackLength()); //how long will it take to run (going to a specific position, end position?)

        a.set(a.getPlayBackLength() / 2); //go to halfway thtough

        System.out.println();

        System.out.println("I'm right in the middle! YAY! :" + s.getAlgorithmInfo(0));


        System.out.println(s.getAlgorithmInfo(0).getPath()); //get the path

        a.set(0);
        for (int i = 0; i < a.getPlayBackLength(); i += 100) {
            System.out.println("Frontier: " + a.getState().getAlgorithmInfo(0).getFrontier());
            a.step(100);
        }
    }


    public static void drawExample() {

        Graph graph = (new ExampleGraphs().sampleGraph1());

        List<Node> nodes = graph.getNodes();

        Node fNode = nodes.get(0);
        Node tNode = nodes.get(graph.getNumberOfNodes() - 1);


        AlgorithmController a = new AlgorithmController(); //add the algorithm(s) to the controller

        a.addAlgo(new AStarSearch(a));

        StatusData s = a.getState(); //get the state, only needs to be run once

        Connection c;
        Connection toDraw;
        double xdiff;
        double ydiff;

        for (int i = 0; i <= a.getPlayBackLength() / 25; i++) {
            System.out.println("Next Step:");
            for (AlgorithmStatusData asd : s.getStatusData()) { //for every algorithm
                System.out.println("    Next Algorithm: ");
                for (ExploredStatusData esd : asd.getData()) { //for every line in the current algorithm
                    System.out.println("        " + esd);
                    c = esd.getConnection();
                    xdiff = c.getDest().getX() - c.getInit().getX();
                    ydiff = c.getDest().getY() - c.getInit().getY();

                    toDraw = new Connection(esd.getProgress(), c.getInit(),
                            new Node(c.getDest(),
                                    c.getInit().getX() + ((double) esd.getProgress() / (double) c.getWeight() * (double) xdiff),
                                    c.getInit().getY() + ((double) esd.getProgress() / (double) c.getWeight() * (double) ydiff)
                            )
                    );
                    System.out.println("                  toDraw = from: (" + toDraw.getInit().getX() + "," + toDraw.getInit().getY() + "), to: (" + toDraw.getDest().getX() + "," + toDraw.getDest().getY() + ")");
                    System.out.println("              connection = from: (" + c.getInit().getX() + "," + c.getInit().getY() + "), to: (" + c.getDest().getX() + "," + c.getDest().getY() + ")");

                    //TODO: Draw toDraw!
                    //It should work, I hope! :)
                }
            }
            a.step(25); //step once

        }
    }
}
