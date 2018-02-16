package ui.application;

import algorithms.structures.HeuristicHolder;
import controller.AlgorithmController;
import controller.structures.AlgorithmStatusData;
import controller.structures.ExploredStatusData;
import controller.structures.StatusData;
import graph.Connection;
import graph.Graph;
import graph.Node;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author dgj470
 *         A class that displays the visualisation
 */
public class Visualiser extends Thread {
    private Canvas canvas;
    private double speed = 0.4;
    private boolean paused = false;
    private Pane pane;
    private AlgorithmController a;
    private boolean[] showFrontier = new boolean[5];
    private boolean[] showCost = new boolean[5];
    private boolean[] showHeuristic = new boolean[5];

    /**
     * @param a      The algorithm controller to visualise
     * @param canvas The canvas to draw on
     * @param pane   The window
     */
    public Visualiser(AlgorithmController a, Canvas canvas, Pane pane) {
        this.canvas = canvas;
        this.pane = pane;
        this.a = a;
        Arrays.fill(showFrontier, false); //set the arrays of what to display to false
        Arrays.fill(showCost, false);
        Arrays.fill(showHeuristic, false);
    }

    /**
     * When this is called, a new thread is started which runs the visualiser
     */
    public void run() {
        GUI.play.setDisable(true); //display the play and refresh buttons
        GUI.refresh.setDisable(true);
        a.set(0); //set the algorithm controller to the beginning
        while (a.getPos() < a.getPlayBackLength()) {
            if (!paused)
                a.step(speed * 15);

            StatusData s = a.getState();


            Platform.runLater(() -> drawData(s)); //Runs the update
            try {
                TimeUnit.MILLISECONDS.sleep(25); //sleep for 25 ms ~40fps
            } catch (InterruptedException e1) {
            } //don't do anything if the sleep fails
        }
        GUI.play.setDisable(false);
        GUI.refresh.setDisable(false);
        try {
            TimeUnit.MILLISECONDS.sleep(100); //wait for a bit before drawing the path
        } catch (InterruptedException e1) {
        } //don't do anything if the sleep fails

        drawPath(); //draw the path
    }

    /**
     * Draw the path
     */
    private void drawPath() {

        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setStroke(Color.PURPLE);

        int shortest = 20;

        for (int j = 0; j < a.getAlgoCount(); j++) { //find the shortest path
            int nodes = a.getState().getAlgorithmInfo(j).getPath().size();
            if (nodes < shortest) {
                shortest = j;
            }
        }
        List<Node> path = a.getState().getAlgorithmInfo(shortest).getPath(); //get the path


        for (int i = 0; i < a.getState().getAlgorithmInfo(shortest).getData().size(); i++) { //draw the lines of the path
            Connection con = a.getState().getAlgorithmInfo(shortest).getData().get(i).getConnection();
            Node init = con.getInit();
            Node dest = con.getDest();
            if (path.contains(init) && path.contains(dest)) {
                gc.strokeLine(init.getX() * 50, init.getY() * 50, dest.getX() * 50, dest.getY() * 50);
            }
        }

        int i = 1;
        for (Node pnode : path) { //draw the nodes of the path
            gc.setFill(Color.AQUA);
            gc.fillOval(pnode.getX() * 50 - 17, pnode.getY() * 50 - 17, 35, 35);
            gc.setFill(Color.BLACK);
            gc.fillText(i + "", pnode.getX() * 50 - 5, pnode.getY() * 50 + 5);
            i++;

        }
    }

    /**
     * Draw the data every frame
     *
     * @param data the data to draw
     */
    public void drawData(StatusData data) {

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Connection c;
        double xdiff;
        double ydiff;

        gc.setLineWidth(3);

        gc.setStroke(Color.YELLOW);
        gc.setFill(Color.GREEN);

        ArrayList<Paint> colours = new ArrayList<>();
        colours.add(Color.YELLOW); //breath first
        colours.add(Color.ORANGE); //depth first
        colours.add(Color.BLACK); // greedy best first
        colours.add(Color.PINK); // Dijkstras
        colours.add(Color.DARKGREEN); //A*


        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); //clear the canvas

        drawShapes(); //draw the graph

        gc.setFill(Color.GREEN);

        Node from = a.getFrom();
        gc.fillOval((from.getX()) * 50 - 15, (from.getY()) * 50 - 15, 31, 31); //draw the starting node


        int frontierCount = 0; //count of frontiers to draw

        for (int i = 0; i < data.getStatusData().size(); i++) { //for every algorithm

            AlgorithmStatusData asd = data.getStatusData().get(i); //get the data for algorithm i

            gc.setStroke(colours.get(asd.getAlgoType())); //get the correct colour

            if (showFrontier[asd.getAlgoType()]) { //if the frontier is to be drawn
                drawFrontier(data.getAlgorithmInfo(i), 20 + (170 * frontierCount), 550, gc); //draw it
                frontierCount++;
            }


            for (ExploredStatusData esd : asd.getData()) { //for every line in the current algorithm
                c = esd.getConnection(); //get the line

                xdiff = c.getDest().getX() - c.getInit().getX();
                ydiff = c.getDest().getY() - c.getInit().getY();

                double angle = Math.atan(xdiff / ydiff); //calculate the angle of the line

                double xShift = i * Math.sin(angle + Math.PI / 2) * 4;
                double yShift = i * Math.cos(angle + Math.PI / 2) * 4; //calculate the line gradient in terms of x and y

                double lineStartX = c.getInit().getX(); //get the start
                double lineStartY = c.getInit().getY();
                double lineEndX = lineStartX + (esd.getProgress() / c.getWeight() * xdiff); //calculate where the end of the line is
                double lineEndY = lineStartY + (esd.getProgress() / c.getWeight() * ydiff);


                gc.strokeLine(lineStartX * 50 + xShift, lineStartY * 50 + yShift, lineEndX * 50 + xShift, lineEndY * 50 + yShift); //draw it

                gc.setFill(Color.GREEN); //draw all points that have been explored
                if (esd.getProgress() / c.getWeight() == 1) {
                    gc.fillOval((c.getDest().getX()) * 50 - 16, (c.getDest().getY()) * 50 - 16, 33, 33);
                }

            }

            drawCostHeuristic(gc, asd, true); //draw the cost readout
            drawCostHeuristic(gc, asd, false); //draw the heuristic readout

        }
    }

    /**
     * @param gc       The graphics context to draw on
     * @param asd      The algorithm statusdata to draw
     * @param costheur if true, draw the cost, if false draw the heuristic
     * @author dgj470
     */
    private void drawCostHeuristic(GraphicsContext gc, AlgorithmStatusData asd, boolean costheur) {

        boolean[] array = (costheur ? showCost : showHeuristic); //get the correct array

        if (array[asd.getAlgoType()]) {
            List<HeuristicHolder> frontier = asd.getFrontier(); //get the frontier

            if (frontier != null) { //if it contains things

                double startx = asd.getPath().get(costheur ? 0 : asd.getPath().size() - 1).getX() * 50;//if the heuristic, draw to the end
                double starty = asd.getPath().get(costheur ? 0 : asd.getPath().size() - 1).getY() * 50;//if the cost draw from the start

                for (HeuristicHolder h : frontier) { //for everything in the frontier
                    drawDataLink(startx, starty, h.getConnection().getDest().getX() * 50, h.getConnection().getDest().getY() * 50, //draw the cost/heuristic
                            (costheur ? "Cost" : "Heuristic") + ": " + (costheur ? h.getCost() : Math.round(h.getHeuristic()*100.0)/100.0) , gc);
                }
            }
        }
    }


    /**
     * Draw the nodes and the edges between them.
     */
    public void drawShapes() {
        Graph g = a.getGraph();

        List<Node> nodes = g.getNodes();

        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.clearRect(0, 0, 1280, 700);
        int i = 0;
        for (Node<Integer> node : nodes) {
            gc.setFill(Color.RED);
            gc.fillOval(((node.getX() * 50) - 15), ((node.getY() * 50) - 15), 31, 31);
            gc.setFill(Color.WHITE);
            gc.fillText(i + "", node.getX() * 50 - Double.toString(node.getValue()).length() * 3 + 5, node.getY() * 50 + 5);
            i++;
        }

    }

    /**
     * Draws the edges with an arrowline between the nodes for each connectin in the graph
     */
    public void drawEdges() {
        List<Connection> connections = a.getGraph().getConnections();

        for (Connection con : connections) {
            Line l = new Line(con.getInit().getX() * 50, con.getInit().getY() * 50, con.getDest().getX() * 50, con.getDest().getY() * 50);
            l.setStroke(Color.DODGERBLUE);
            l.setStrokeWidth(3);
            pane.getChildren().add(l);
        }

        for (Connection con : connections) {
            Node<Integer> init = con.getInit();
            Node<Integer> dest = con.getDest();
            ArrowHead arrowhead = new ArrowHead(init.getX() * 50, init.getY() * 50, dest.getX() * 50, dest.getY() * 50, Math.log(con.getWeight()) + 3);
            pane.getChildren().add(arrowhead.create());
        }
    }

    /**
     * Sets whether the program is paused
     *
     * @param b Whether the program should be paused or not
     */
    public void setPause(boolean b) {
        this.paused = b;
    }

    /**
     * Gets the algorithm controller
     *
     * @return The algorithm controller
     */
    public AlgorithmController getController() {
        return a;
    }

    /**
     * Sets the speed of the animation
     *
     * @param s The speed of the animation
     */
    public void setSpeed(double s) {
        this.speed = s;
    }

    /**
     * Gets the connections in the graph
     *
     * @return A list of the connections in the graph
     */
    public List<Connection> getConnections() {
        return a.getGraph().getConnections();
    }


    /**
     * @param x1   The starting x position
     * @param y1   The starting y position
     * @param x2   The end x position
     * @param y2   The end y position
     * @param data The data to draw
     * @param gc   The GC to draw it on
     * @author dgj470
     * <p>
     * Draw a line between two circles with a label on the line
     */
    private void drawDataLink(double x1, double y1, double x2, double y2, String data, GraphicsContext gc) {

        if (x1 == x2 && y1 == y2) //if start and end are equal, do not draw
            return;

        if (x1 > x2) { //if the text would be upside down

            double temp = y1; //flip it all
            y1 = y2;
            y2 = temp;

            temp = x1;
            x1 = x2;
            x2 = temp;
        }

        int circlesize = 45;

        Paint prevColor = gc.getStroke(); //get the previous colour
        double prevWidth = gc.getLineWidth(); //get the previous width

        double angle = Math.atan2(y1 - y2, x1 - x2); //calculate the angle

        int offsetx = (int) ((circlesize / 2.) * Math.cos(angle)); //get the offsets for the line
        int offsety = (int) ((circlesize / 2.) * Math.sin(angle));

        gc.setStroke(Color.BLACK); //set the colour
        gc.setLineWidth(1); //set the width of the lines

        gc.strokeOval(x1 - circlesize / 2, y1 - circlesize / 2, circlesize, circlesize); //draw circles at the correct location
        gc.strokeOval(x2 - circlesize / 2, y2 - circlesize / 2, circlesize, circlesize);
        gc.strokeLine(x1 - offsetx, y1 - offsety, x2 + offsetx, y2 + offsety); //draw a line between them

        gc.translate((x2 + (x1 - x2) / 2), (y2 + (y1 - y2) / 2)); //center it on 0,0 for text drawing
        gc.rotate(180 + Math.toDegrees(angle)); //rotate so text can be drawn flat

        gc.setLineWidth(1); //set parameters for drawing the text outline
        gc.strokeText(data, -data.length() * 4, -7); //draw the text outline offset up and by the length of the string

        gc.rotate(180 - Math.toDegrees(angle)); //rotate it back so it's the right orientation
        gc.translate(-(x2 + (x1 - x2) / 2), -(y2 + (y1 - y2) / 2)); //translate back to the correct position

        gc.setLineWidth(prevWidth); //return values to default
        gc.setStroke(prevColor);
    }

    /**
     * @param a  The algorithm status data
     * @param x  the x position to draw at
     * @param y  the y position to draw at
     * @param gc the graphics context to draw on
     * @author dgj470
     * <p>
     * Draw the frontier at a specified location
     */
    private void drawFrontier(AlgorithmStatusData a, int x, int y, GraphicsContext gc) {
        Paint prevColor = gc.getStroke(); //get the previous colour
        double prevWidth = gc.getLineWidth(); //get the previous width

        gc.setStroke(Color.BLACK); //set the colours I want
        gc.setLineWidth(1);

        List<HeuristicHolder> frontier = a.getFrontier(); //get the frontier


        int blockheight = 20;
        int blockwidth = 160;

        String algoType = null;
            /*
            0 = breadth first
            1 = depth first
            2 = Greedy best first
            3 = Dijkstras
            4 = A*
            */
        switch (a.getAlgoType()) { //get the algorithm name to write
            case 0:
                algoType = "Breadth First";
                break;
            case 1:
                algoType = "Depth First";
                break;
            case 2:
                algoType = "Greedy BFS";
                break;
            case 3:
                algoType = "Djikstras";
                break;
            case 4:
                algoType = "A*";
                break;
            default:
                algoType = "Unknown";
                break;
        }

        gc.strokeText(algoType, x, y); //write the algotithm name


        gc.strokeText("Node", x, y + 15); //write some headings
        gc.strokeText("Heuristic", x + 40, y + 15);
        gc.strokeText("Cost", x + 108, y + 15);


        if (frontier != null) { //if the frontier is not null

            for (int i = 0; i < frontier.size(); i++) {
                HeuristicHolder h = frontier.get(i);

                String cost = Double.toString(h.getCost()).substring(0, (Double.toString(h.getCost()).length() > 5 ? 5 : Double.toString(h.getCost()).length())); //get the cost (maximum length 5)
                String heuristic = Double.toString(h.getHeuristic()).substring(0, (Double.toString(h.getHeuristic()).length() > 5 ? 5 : Double.toString(h.getHeuristic()).length())); //get the heuristic (maximum length 5)

                if (i < 4) { //for the first 4
                    gc.strokeRect(x, y + i * (blockheight + 4) + 20, blockwidth, blockheight); //draw the rectangle
                    gc.strokeText(h.getConnection().getDest().getValue().toString(), x + 4, y + i * (blockheight + 4) + 35); //draw the node name
                    gc.strokeText(heuristic.equals("-1.0") ? "-" : heuristic, x + 44, y + i * (blockheight + 4) + 35); //draw the heuristic value (if present)
                    gc.strokeText(cost.equals("-1.0") ? "-" : cost, x + 112, y + i * (blockheight + 4) + 35); //draw the cost value (if present)
                } else {
                    if (i < 5) //on the 5th one, write ... as no more space on screen
                        gc.strokeText("...", x + 44, y + i * (blockheight + 4) + 35);
                }
            }
        } else { //if frontier is empty, draw a single empty block
            gc.strokeRect(x, y + 20, blockwidth, blockheight);
            gc.strokeText("-", x + 12, y + 35);
            gc.strokeText("-", x + 64, y + 35);
            gc.strokeText("-", x + 122, y + 35);
        }
        gc.setLineWidth(prevWidth); //return values to default
        gc.setStroke(prevColor);

    }

    /**
     * @param x
     * @param b
     * @author dgj470
     * Set which algorithms to show the frontier on
     */
    public void setShowFrontier(int x, boolean b) {
        showFrontier[x] = b;
    }

    /**
     * @param x
     * @param b
     * @author dgj470
     * Set which algorithms to show the cost on
     */
    public void setShowCost(int x, boolean b) {
        showCost[x] = b;
    }

    /**
     * @param x
     * @param b
     * @author dgj470
     * Set which algorithms to show the heuristic on
     */
    public void setShowHeuristic(int x, boolean b) {
        showHeuristic[x] = b;
    }
}
