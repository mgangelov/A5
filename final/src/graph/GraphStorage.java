package graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A class for the purpose of reading in a graph from file.
 *
 * @author nxb484
 */
public class GraphStorage {
    /**
     * A method for storing String Graphs in a csv file
     *
     * @param graph    The String graph to store.
     * @param FileName The location (including filename) to write to.
     * @throws IOException Any generic exceptions will be passed to the UI for handling.
     */
    @SuppressWarnings("unchecked")
    public static void exportStringGraph(Graph<String> graph, String FileName) throws IOException {
        FileWriter writer = new FileWriter(FileName);
        writer.append("String" + "\n");
        for (Node<String> node : graph.getNodes()) {
            writer.append(node.getValue() + "," +
            		Double.toString(node.getX()) + "," +
            		Double.toString(node.getY())+ "\n");
        }
        writer.append("\n");
        for (Connection connection : graph.getConnections()) {
            writer.append(Double.toString(connection.getWeight()) + "," +
            		Integer.toString(findNodeIndexS(connection.getInit(), graph.getNodes())) + "," +
            		Integer.toString(findNodeIndexS(connection.getDest(), graph.getNodes())) + "\n");
        }
        writer.append("\n" + "End of file.");

        writer.flush();
        writer.close();
    }

    /**
     * A method for storing Integer Graphs in a csv file
     *
     * @param graph    The String graph to store.
     * @param FileName The location (including filename) to write to.
     * @throws IOException Any generic exceptions will be passed to the UI for handling.
     */
    @SuppressWarnings("unchecked")
    public static void exportIntegerGraph(Graph<Integer> graph, String FileName) throws IOException {
        FileWriter writer = new FileWriter(FileName);
        writer.append("Integer" + "\n");
        for (Node<Integer> node : graph.getNodes()) {
            writer.append(Integer.toString(node.getValue()) + "," +
            		Double.toString(node.getX()) + "," +
            		Double.toString(node.getY())+ "\n");
        }
        writer.append("\n");
        for (Connection connection : graph.getConnections()) {
            writer.append(Double.toString(connection.getWeight()) + "," +
            		Integer.toString(findNodeIndexI(connection.getInit(), graph.getNodes())) + "," +
            		Integer.toString(findNodeIndexI(connection.getDest(), graph.getNodes())) + "\n");
        }
        writer.append("\n" + "End of file.");

        writer.flush();
        writer.close();
    }

    /**
     * A method for storing Double Graphs in a csv file
     *
     * @param graph    The String graph to store.
     * @param FileName The location (including filename) to write to.
     * @throws IOException Any generic exceptions will be passed to the UI for handling.
     */
    @SuppressWarnings("unchecked")
    public static void exportDoubleGraph(Graph<Double> graph, String FileName) throws IOException {
        FileWriter writer = new FileWriter(FileName);
        writer.append("Double" + "\n");
        for (Node<Double> node : graph.getNodes()) {
            writer.append(Double.toString(node.getValue()) + "," +
            		Double.toString(node.getX()) + "," +
            		Double.toString(node.getY())+ "\n");
        }
        writer.append("\n");
        for (Connection connection : graph.getConnections()) {
            writer.append(Double.toString(connection.getWeight()) + "," +
            		Integer.toString(findNodeIndexD(connection.getInit(), graph.getNodes())) + "," +
            		Integer.toString(findNodeIndexD(connection.getDest(), graph.getNodes())) + "\n");
        }
        writer.append("\n" + "End of file.");

        writer.flush();
        writer.close();
    }

    /**
     * A method to find the index of given nodes for easier graph reconstruction
     *
     * @param node  The node to find
     * @param nodes The list of nodes to find it in
     * @return The index of the node in the list
     */
    private static int findNodeIndexI(Node<Integer> node, List<Node<Integer>> nodes) {
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i) == node) {
                return i;
            }
        }
        return 0;
    }

    /**
     * A method to find the index of given nodes for easier graph reconstruction
     *
     * @param node  The node to find
     * @param nodes The list of nodes to find it in
     * @return The index of the node in the list
     */
    private static int findNodeIndexS(Node<String> node, List<Node<String>> nodes) {
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i) == node) {
                return i;
            }
        }
        return 0;
    }

    /**
     * A method to find the index of given nodes for easier graph reconstruction
     *
     * @param node  The node to find
     * @param nodes The list of nodes to find it in
     * @return The index of the node in the list
     */
    private static int findNodeIndexD(Node<Double> node, List<Node<Double>> nodes) {
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i) == node) {
                return i;
            }
        }
        return 0;
    }

    /**
     * A method for importing graphs of any reasonably expected type.
     *
     * @param filePath The location (including filename) to read from.
     * @return The imported graph
     * @throws IOException If the file is not present, correctly formatted, or is damaged during execution.
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static Graph importGraph(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String graphType = reader.readLine();
        String line = "";
        ArrayList<Node> nodes = new ArrayList<Node>();
        while ((line = reader.readLine()).contains(",")) {
            String[] node = line.split(",");
            switch (graphType) {
                case "String":
                    nodes.add(new Node(node[0], Double.valueOf(node[1]), Double.valueOf(node[2])));
                    break;
                case "Double":
                    nodes.add(new Node(Double.valueOf(node[0]), Double.valueOf(node[1]), Double.valueOf(node[2])));
                    break;
                case "Integer":
                    nodes.add(new Node(Integer.valueOf(node[0]), Double.valueOf(node[1]), Double.valueOf(node[2])));
                    break;
            }
        }
        ArrayList<Connection> connections = new ArrayList<Connection>();
        while ((line = reader.readLine()).contains(",")) {
            String[] connection = line.split(",");
            connections.add(new Connection(Double.valueOf(connection[0]), nodes.get(Integer.valueOf(connection[1])), nodes.get(Integer.valueOf(connection[2]))));
        }
        reader.close();
        return new Graph(nodes, connections);
    }
}
