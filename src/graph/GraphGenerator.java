package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A graph generator which generates random graph based on given properties.
 * example properties: Number of Nodes, Connectedness(probability),
 * Planar(bool), Weightedness(bool), loops possible(bool), acyclic(bool)
 *
 * @author mxa487
 */
public class GraphGenerator {

    /**
     * A method which generates a planar graph using a Delaunay triangulation.
     *
     * @param numberOfNodes The number of nodes.
     * @param dLimit        The limit of the random distance between nodes.
     * @param sLimit        The size of the square canvas where the nodes are generated.
     * @return
     */
    public static Graph genPlanar(int numberOfNodes, int connectivity, boolean weighted,
                                  boolean directed, int directedness) {
        DTriangulation dt = new DTriangulation();
        CoordGenerator g = new CoordGenerator();
        List<Node> nodes = g.genBySquare(numberOfNodes);
        List<Connection> connections = dt.triangulate(nodes, weighted);
        List<Connection> temp = new ArrayList<Connection>();
        double connectivityCoef = (double) connectivity / 100;
        System.out.println("Connectivity coef: " + connectivityCoef);
        int targetNumberOfConnections = (int) (connections.size() * connectivityCoef);
        System.out.println("number of cons: " + targetNumberOfConnections + " actual: " + connections.size());
        while (connections.size() != targetNumberOfConnections) {
            Random rand = new Random();
            connections.remove(rand.nextInt(connections.size()));
        }
        if (!directed) {
            for (Connection c : connections) {
                Connection rev = new Connection(c.getWeight(), c.getDest(), c.getInit());
                temp.add(rev);
            }
        }
        else {
        	double directednessCoef = (double) directedness / 100;
        	System.out.println("Directedness: " + directednessCoef);
        	Random rand = new Random();
        	for (Connection c : connections) {
        		if (rand.nextDouble() > directednessCoef) {
        			Connection rev = new Connection(c.getWeight(), c.getDest(), c.getInit());
        			temp.add(rev);
        		}
        	}
        }
        connections.addAll(temp);
        return new Graph(nodes, connections);
    }
    

    /**
     * Method which generates a graph with certain properties.
     * @param numberOfNodes The number of nodes in the generated graph.
     * @param connectivity The coefficient of connectivity between nodes for the graph.
     * @param weighted Is the graph weighted or not.
     * @param directed Is the graph directed or not.
     * @param directedness The coefficient of directedness for the connections in the graph
     * (only used if directed is true)
     * @return A graph with the properties set as parameters for the method.
     */
    public static Graph genSquared(int numberOfNodes, int connectivity, boolean weighted, boolean directed, int directedness) {
        Random rand = new Random();
        CoordGenerator cGen = new CoordGenerator();
        List<Node> nodes = cGen.genBySquare(numberOfNodes);
        
        int maxConnections = (numberOfNodes * (numberOfNodes - 1)) / 2;
        double connectivityCoef = (double) connectivity / 100;
        int permConnections = (int) (connectivityCoef * maxConnections);
        List<Connection> connections = new ArrayList<Connection>();

        System.out.println("Permitted: " + permConnections);

        while (connections.size() < permConnections) {
            Connection con = null;
            int init = rand.nextInt(nodes.size());
            int dest = rand.nextInt(nodes.size());

            if (!weighted) {
                con = new Connection(100, nodes.get(init), nodes.get(dest));
            } else {
                con = new Connection(rand.nextInt(100) + 1, nodes.get(init), nodes.get(dest));
            }

            connections.add(con);
        }

        System.out.println("Numbers of cons: " + connections.size());
        List<Connection> tempCon = new ArrayList<Connection>();
        if (!directed) {
            for (int i = 0; i < connections.size(); i++) {
                Connection con = connections.get(i);
                Connection temp = new Connection(con.getWeight(), con.getDest(), con.getInit());
                tempCon.add(temp);
            }
        }
        else {
        	double directednessCoef = (double) directedness / 100;
        	System.out.println("Directedness: " + directednessCoef);
        	//Random rand = new Random();
        	for (Connection c : connections) {
        		if (rand.nextDouble() > directednessCoef) {
        			Connection rev = new Connection(c.getWeight(), c.getDest(), c.getInit());
        			tempCon.add(rev);
        		}
        	}
        }
        connections.addAll(tempCon);

        return new Graph(nodes, connections);
    }
}
