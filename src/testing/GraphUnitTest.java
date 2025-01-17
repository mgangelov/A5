package testing;

import graph.Graph;
import graph.GraphGenerator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for the graph generation algorithm. It does generate a number of random graphs
 * and checks if their properties are what are required.
 *
 * @author martin
 */
public class GraphUnitTest {

    @Before
    public void setUp() throws Exception {
    }

    /**
     * A test about the genSimple() generator.
     */
    @Test
    public void connectivityTest() {
        for (int i = 0; i < 100; i++) {
            System.out.println("Test: " + i);
            double randNumber = Math.random();
            randNumber = Math.round(randNumber * 10);
            double connectivity = randNumber / 10;
            Graph g = GraphGenerator.genSquared(10, (int) (connectivity*100), false, false, 50);
            int numberOfNodes = g.getNodes().size();
            int maxConnections = (numberOfNodes * (numberOfNodes - 1));
            System.out.println("Number of nodes: " + numberOfNodes + " Max connections: " + maxConnections);
            System.out.println("Number of connections: " + g.getConnections().size());
            double actConnectivity = (double) g.getConnections().size() / maxConnections;
            actConnectivity = Math.round(actConnectivity * 10);
            actConnectivity = actConnectivity / 10;
            System.out.println(connectivity + " Actual: " + actConnectivity);
            assertTrue(this.inRange(actConnectivity, connectivity - 0.21, connectivity + 0.21));
        }
    }

    public boolean inRange(double number, double from, double to) {
        return (from < number) && (number < to);
    }

}
