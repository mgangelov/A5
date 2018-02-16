package graph;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Example graph classes.
 * 
 * @author mxa487, nxb484
 */
public class ExampleGraphs {

    public ExampleGraphs() {

    }

    /**
     * This returns an example graph to anything that calls it. This graph has 5 nodes, is planar, resembling a box with a node suspended within it.
     * @return A 5 node planar sample graph.
     */
    public Graph planarGraph5() {
        ArrayList<Node> nodes = new ArrayList<Node>();
        nodes.add(new Node(0, 2, 2));
        nodes.add(new Node(1, 6, 2));
        nodes.add(new Node(2, 4, 4));
        nodes.add(new Node(3, 2, 6));
        nodes.add(new Node(4, 6, 6));
        ArrayList<Connection> connections = new ArrayList<Connection>();
        connections.add(new Connection(100, nodes.get(0), nodes.get(1)));
        connections.add(new Connection(100, nodes.get(0), nodes.get(2)));
        connections.add(new Connection(100, nodes.get(0), nodes.get(3)));
        connections.add(new Connection(100,nodes.get(0),nodes.get(4)));
        connections.add(new Connection(100, nodes.get(1), nodes.get(0)));
        connections.add(new Connection(100, nodes.get(1), nodes.get(2)));
        connections.add(new Connection(100, nodes.get(1), nodes.get(3)));
        connections.add(new Connection(100, nodes.get(1), nodes.get(4)));
        connections.add(new Connection(100, nodes.get(2), nodes.get(1)));
        connections.add(new Connection(100, nodes.get(2), nodes.get(0)));
        connections.add(new Connection(100, nodes.get(2), nodes.get(3)));
        connections.add(new Connection(100, nodes.get(3), nodes.get(1)));
        connections.add(new Connection(100, nodes.get(3), nodes.get(2)));
        connections.add(new Connection(100, nodes.get(3), nodes.get(0)));
        connections.add(new Connection(100,nodes.get(3),nodes.get(4)));
        connections.add(new Connection(100, nodes.get(4), nodes.get(1)));
        connections.add(new Connection(100,nodes.get(4),nodes.get(3)));
        connections.add(new Connection(100,nodes.get(4),nodes.get(0)));
        return new Graph(nodes, connections);
    }

    /**
     * This returns an example graph to anything that calls it. This graph has 15 nodes, is planar and provides some gaps for demonstration of algorithms.
     * @return A 15 node planar sample graph.
     */
    public Graph planarGraph15() {
        ArrayList<Node> nodes = new ArrayList<Node>();
        nodes.add(new Node(0, 2, 2));
        nodes.add(new Node(1, 6, 2));
        nodes.add(new Node(2, 4, 4));
        nodes.add(new Node(3, 2, 6));
        nodes.add(new Node(4, 6, 6));
        nodes.add(new Node(5, 8, 2));
        nodes.add(new Node(6, 8, 4));
        nodes.add(new Node(7, 8, 6));
        nodes.add(new Node(8, 10, 6));
        nodes.add(new Node(9, 10, 8));
        nodes.add(new Node(10, 8, 8));
        nodes.add(new Node(11, 6, 8));
        nodes.add(new Node(12, 4, 8));
        nodes.add(new Node(13, 2, 8));
        nodes.add(new Node(14, 5, 10));
        ArrayList<Connection> connections = new ArrayList<Connection>();
        connections.add(new Connection(100, nodes.get(0), nodes.get(1)));
        connections.add(new Connection(100, nodes.get(0), nodes.get(2)));
        connections.add(new Connection(100, nodes.get(0), nodes.get(3)));
        connections.add(new Connection(100, nodes.get(1), nodes.get(0)));
        connections.add(new Connection(100, nodes.get(1), nodes.get(2)));
        connections.add(new Connection(100, nodes.get(1), nodes.get(3)));
        connections.add(new Connection(100, nodes.get(1), nodes.get(4)));
        connections.add(new Connection(100, nodes.get(1), nodes.get(5)));
        connections.add(new Connection(100, nodes.get(1), nodes.get(6)));
        connections.add(new Connection(100, nodes.get(2), nodes.get(1)));
        connections.add(new Connection(100, nodes.get(2), nodes.get(0)));
        connections.add(new Connection(100, nodes.get(2), nodes.get(3)));
        connections.add(new Connection(100, nodes.get(2), nodes.get(12)));
        connections.add(new Connection(100, nodes.get(3), nodes.get(1)));
        connections.add(new Connection(100, nodes.get(3), nodes.get(2)));
        connections.add(new Connection(100, nodes.get(3), nodes.get(0)));
        connections.add(new Connection(100, nodes.get(3), nodes.get(13)));
        connections.add(new Connection(100, nodes.get(4), nodes.get(1)));
        connections.add(new Connection(100, nodes.get(5), nodes.get(1)));
        connections.add(new Connection(100, nodes.get(5), nodes.get(6)));
        connections.add(new Connection(100, nodes.get(5), nodes.get(8)));
        connections.add(new Connection(100, nodes.get(6), nodes.get(1)));
        connections.add(new Connection(100, nodes.get(6), nodes.get(5)));
        connections.add(new Connection(100, nodes.get(7), nodes.get(8)));
        connections.add(new Connection(100, nodes.get(7), nodes.get(10)));
        connections.add(new Connection(100, nodes.get(8), nodes.get(5)));
        connections.add(new Connection(100, nodes.get(8), nodes.get(7)));
        connections.add(new Connection(100, nodes.get(8), nodes.get(9)));
        connections.add(new Connection(100, nodes.get(9), nodes.get(8)));
        connections.add(new Connection(100, nodes.get(9), nodes.get(10)));
        connections.add(new Connection(100, nodes.get(10), nodes.get(7)));
        connections.add(new Connection(100, nodes.get(10), nodes.get(9)));
        connections.add(new Connection(100, nodes.get(10), nodes.get(11)));
        connections.add(new Connection(100, nodes.get(10), nodes.get(14)));
        connections.add(new Connection(100, nodes.get(11), nodes.get(10)));
        connections.add(new Connection(100, nodes.get(12), nodes.get(2)));
        connections.add(new Connection(100, nodes.get(12), nodes.get(14)));
        connections.add(new Connection(100, nodes.get(13), nodes.get(3)));
        connections.add(new Connection(100, nodes.get(13), nodes.get(14)));
        connections.add(new Connection(100, nodes.get(14), nodes.get(10)));
        connections.add(new Connection(100, nodes.get(14), nodes.get(12)));
        connections.add(new Connection(100, nodes.get(14), nodes.get(13)));
        return new Graph(nodes, connections);
    }

    //A simple sample graph
    public Graph<Integer> sampleGraph1() {
        ArrayList<Node<Integer>> nodes = new ArrayList<Node<Integer>>();
        ArrayList<Connection> connections = new ArrayList<Connection>();
        nodes.add(new Node<Integer>(1, 2.3, 6.5));
        nodes.add(new Node<Integer>(2, 6.2, 6.3));
        nodes.add(new Node<Integer>(3, 1.2, 4.9));
        nodes.add(new Node<Integer>(4, 3, 7));
        nodes.add(new Node<Integer>(5, 5, 1));
        nodes.add(new Node<Integer>(6, 8, 1));
        nodes.add(new Node<Integer>(7, 10, 2));
        connections.add(new Connection(100, nodes.get(0), nodes.get(1)));
        connections.add(new Connection(100, nodes.get(0), nodes.get(3)));
        connections.add(new Connection(100, nodes.get(0), nodes.get(2)));
        connections.add(new Connection(100, nodes.get(0), nodes.get(5)));
        connections.add(new Connection(100, nodes.get(1), nodes.get(0)));
        connections.add(new Connection(100, nodes.get(3), nodes.get(0)));
        connections.add(new Connection(100, nodes.get(2), nodes.get(0)));
        connections.add(new Connection(100, nodes.get(5), nodes.get(0)));
        connections.add(new Connection(100, nodes.get(3), nodes.get(1)));
        connections.add(new Connection(100, nodes.get(1), nodes.get(3)));
        connections.add(new Connection(100, nodes.get(2), nodes.get(4)));
        connections.add(new Connection(100, nodes.get(4), nodes.get(2)));
        connections.add(new Connection(100, nodes.get(4), nodes.get(5)));
        connections.add(new Connection(100, nodes.get(5), nodes.get(4)));
        connections.add(new Connection(100,nodes.get(4),nodes.get(7)));
        connections.add(new Connection(100,nodes.get(7),nodes.get(4)));
        connections.add(new Connection(100, nodes.get(4), nodes.get(6)));
        connections.add(new Connection(100, nodes.get(6), nodes.get(4)));

        return new Graph<Integer>(nodes, connections);

    }

    //Deprecated. A method to test imports and exports, this exports planargraph15 at a given filepath, then reimports it from there.
    public Graph importExportTest(String filePath) {
        try {
            GraphStorage.exportIntegerGraph(this.planarGraph15(), filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return GraphStorage.importGraph(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
