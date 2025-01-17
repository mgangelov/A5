package graph;

import java.util.ArrayList;
import java.util.List;

/**
 * A generic graph structure consisting of a list of nodes. Each node has its successors
 * remembered within the Node object.
 *
 * @author mxa487, nxb484
 */
public class Graph<A> {
    private List<Node<A>> nodes;
    private int numberOfNodes;
    private List<Connection> connections;
    private int numberOfConnections;
    //more properties to the graph to be added.

    /**
     * Constructs the graph by taking a list of all contained nodes and connections within it.
     *
     * @param nodes
     * @param connections
     */
    public Graph(List<Node<A>> nodes, List<Connection> connections) {
        this.nodes = nodes;
        numberOfNodes = nodes.size();
        this.connections = connections;
        numberOfConnections = connections.size();
    }

    /**
     * Returns the nodes from the graph.
     *
     * @return A list of nodes.
     */
    public List<Node<A>> getNodes() {
        return nodes;
    }

    /**
     * Returns the connections from the graph.
     *
     * @return A list of connections.
     */
    public List<Connection> getConnections() {
        return connections;
    }

    /**
     * Returns how many nodes are there in the graph.
     *
     * @return the numberOfNodes
     */
    public int getNumberOfNodes() {
        numberOfNodes = nodes.size();
        return numberOfNodes;
    }

    /**
     * Returns how many connections are there in the graph.
     *
     * @return the numberOfConnections
     */
    public int getNumberOfConnections() {
        numberOfConnections = connections.size();
        return numberOfConnections;
    }

    /**
     * Adds a new 'leaf' to the graph.
     *
     * @param value The value of the leaf.
     */
    public void addNode(A value) {
        Node<A> node = new Node<A>(value);
        nodes.add(node);
    }

    /**
     * Returns all connections from a given node
     *
     * @param node The initial node
     * @return All connections with the given node as the originator
     */
    public List<Connection> getNodeConnections(Node<A> node) {
        ArrayList<Connection> nodeConnections = new ArrayList<>(connections);
        for (int i = 0; i < nodeConnections.size(); i++) {
            if (nodeConnections.get(i).getInit() != node) {
                nodeConnections.remove(i);
                i--;
            }
        }
        return nodeConnections;
    }

    /**
     * Adds a new node - cannot add a node with connections pre-etablished.
     *
     * @param node A Node object.
     */
    public void addNode(Node<A> node) {

        nodes.add(node);
    }

    /**
     * Removes a node and the connections to it from the graph.
     * Unsafe, can potentially separate the graph into parts.
     *
     * @param value The value of the node to be removed.
     */
    public void removeNode(A value) {
        for (Connection connection : connections) {
            if (connection.getInit().getValue() == value || connection.getDest().getValue() == value) {
                connections.remove(connection);
            }
        }
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).getValue() == value)
                nodes.remove(i);
        }
    }

    /**
     * Adds a connection to the graph, checks also if the target and
     * destination nodes exist.
     *
     * @param value  The value of the target node.
     * @param weight The weight of the connection between the two nodes.
     * @param dest   The destination node.
     */

    public void addConnectionToNode(A value, int weight, A dest) { //Not finished
        boolean notFoundFlag = true;
        boolean destNotFoundFlag = true;
        int initIndex = 0;
        int destIndex = 0;

        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).getValue() == value)
                notFoundFlag = false;
            initIndex = i;
            if (nodes.get(i).getValue() == dest)
                destNotFoundFlag = true;
            destIndex = i;
        }

        if (!notFoundFlag && !destNotFoundFlag) {
            connections.add(new Connection(weight, nodes.get(initIndex), nodes.get(destIndex)));
        }

        if (notFoundFlag)
            System.err.println("Couldn't find target node.");
        if (destNotFoundFlag)
            System.err.println("Couldn't find dest node.");
    }

    /**
     * Removes a connection from the graph, if it exists
     *
     * @param init The initial node of the connection.
     * @param dest The destination node.
     */
    public void removeConnection(A init, A dest) {
        for (Connection connection : connections) {
            if (connection.getInit().getValue() == init && connection.getDest().getValue() == dest) {
                connections.remove(connection);
            }
        }
    }

    /**
     * Checks if the input connection is contained in the graph.
     *
     * @param c A Connection object.
     * @return True if the connection is contained, false if not.
     */
    public boolean isContained(Connection c) {
        for (Connection a : connections) {
            if (a.getInit().getValue() == c.getInit().getValue() && a.getDest().getValue() == c.getDest().getValue())
                return true;
        }
        return false;
    }

    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("Number of Nodes: " + nodes.size() + " \n");
        for (int i = 0; i < nodes.size(); i++) {
            output.append("Node[" + i + "] X: " + nodes.get(i).getX() + " Y: " + nodes.get(i).getY() + " \n");
        }
        output.append("Number of Connections: " + connections.size() + " \n");
        for (int i = 0; i < connections.size(); i++) {
            output.append("From Node " + connections.get(i).getInit().getValue() + " to Node " + connections.get(i).getDest().getValue() + " \n");
        }

        return output.toString();
    }
}
