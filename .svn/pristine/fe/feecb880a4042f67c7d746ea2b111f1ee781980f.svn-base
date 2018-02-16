package testing;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import graph.ExampleGraphs;
import graph.GraphStorage;

/**
 * @author Nicholas
 *
 */
public class GraphStorageTest {

	/**
	 * Test method for Graph Storage.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testGraphStorage() {
		String path = "F:\\test.csv"; //This test has nothing to do with acquiring a path to a file, so I have simply created a path, edit this to point to a file you have access to (The file does not yet have to exist, but it must be a .csv file).
		try {
			GraphStorage.exportIntegerGraph(ExampleGraphs.planarGraph15(), path); //Test using known graph planargraph15
			assertTrue(ExampleGraphs.planarGraph15().toString().equals(GraphStorage.importGraph(path).toString())); //ToString may be used here as only functionally similar graphs will return the same string.
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("IO Error");
		}
	}

}
