package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A class which evenly generates coordinates for a list of nodes or a set
 * number of nodes.
 *
 * @author mxa487
 */
public class CoordGenerator {

	/**
	 * Empty constructor for the class.
	 */
    public CoordGenerator() {

    }

    /**
     * Method which generates a list of nodes with coordinates based on random positions
     * in a square grid. 
     * @param numberOfNodes The number of nodes to be generated.
     * @return A List of Node objects.
     */
    public List<Node> genBySquare(int numberOfNodes) {
        List<Integer> visitedX = new ArrayList<Integer>();
        List<Integer> visitedY = new ArrayList<Integer>();
        List<Node> nodes = new ArrayList<Node>();
        Random rand = new Random();
        int[][] square;
        if (numberOfNodes <= 13) {
            square = new int[2 * (numberOfNodes + 1)][2 * (numberOfNodes + 1)];
        } else {
            square = new int[28][28]; //So that the coords fit the screen. We will *2 the randomly generated x and y.
        }
        for (int i = 0; i < square.length; i++)
            for (int j = 0; j < square.length; j++) {
                square[i][j] = 0;
            }
        int counter = 0;
        while (counter < numberOfNodes) {
            double x, y;
            if (numberOfNodes <= 13) {
                x = rand.nextInt(numberOfNodes) + 1;
                y = rand.nextInt(numberOfNodes) + 1;
            } else {
                x = rand.nextInt(14) + 0.5;
                y = rand.nextInt(14) + 0.5;
            }
            while (visitedX.contains(2 * x) || visitedY.contains(2 * y)) {
                if (visitedX.contains((2 * x))) {
                    if (numberOfNodes <= 13) {
                        x = rand.nextInt(numberOfNodes) + 1;
                    } else {
                        x = rand.nextInt(14) + 0.5;
                        y = rand.nextInt(14) + 0.5;
                    }
                }
                if (visitedY.contains((2 * y))) {
                    if (numberOfNodes <= 13) {
                        y = rand.nextInt(numberOfNodes) + 1;
                    } else {
                        y = rand.nextInt(14) + 0.5;
                    }
                }
            }
            if (square[(int) x * 2][(int) y * 2] == 0) {
                Node n = new Node(counter, x, y);
                nodes.add(n);
                counter++;
                square[(int) (2 * x)][(int) (2 * y)] = 1;
                visitedX.add((int) (2 * x));
                visitedY.add((int) (2 * y));
            }
        }
        System.out.println(nodes);

        return nodes;
    }

    
}