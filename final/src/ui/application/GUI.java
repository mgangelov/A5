package ui.application;

import algorithms.implementedAlgorithms.*;
import controller.AlgorithmController;
import graph.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static java.lang.System.exit;

/**
 *  @author dgj470,nxb484,mrt469
 */
public class GUI extends Application {

	private Pane visualiser;
	private Canvas canvas;
	private Stage legendDialog;
	private Stage algoDialog;
	public static Button play;
	public static Button refresh;


	/**
	 * Starting the GUI.
	 * @author mrt469
	 */
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Graph Search Visualiser");
		primaryStage.setOnCloseRequest(e -> exit(0));
		ExampleGraphs exGraph = new ExampleGraphs();
		Graph graph = exGraph.planarGraph15();
		BorderPane pane = new BorderPane();

		visualiser = new Pane();
		visualiser.setStyle("-fx-background-color: #ffffff");

		canvas = new Canvas(1000, 700);
		visualiser.getChildren().add(canvas);

		AlgorithmController a = new AlgorithmController();
		Visualiser v = new Visualiser(a, canvas, visualiser);
		v.getController().setGraph(graph);

		VBox optionsPanel = configureOptions(v);
		pane.setLeft(optionsPanel);


		pane.setCenter(visualiser);
		Scene scene = new Scene(pane, 1280, 800);
		primaryStage.setScene(scene);
		primaryStage.show();

		legend(primaryStage);
		algoDialog(primaryStage, v);

		refresh(v);

	}

	/**
	 * Display a legend
	 *
	 * @param primaryStage The primary stage to display the legend with
	 */
	public void legend(Stage primaryStage) {

		legendDialog = new Stage();
		legendDialog.setTitle("Legend");
		legendDialog.initModality(Modality.NONE);
		legendDialog.setX(primaryStage.getX() + 1280);
		legendDialog.setY(primaryStage.getY());
		legendDialog.initOwner(primaryStage);
		legendDialog.setResizable(false);

		Pane algoNames = new Pane();
		BorderPane pane = new BorderPane();
		Pane vis = new Pane();
		Canvas legend = new Canvas(100, 400);
		GraphicsContext gc = legend.getGraphicsContext2D();
		vis.getChildren().add(legend);
		pane.setLeft(vis);
		gc.setLineWidth(5);
		//Green Node
		gc.setFill(Color.GREEN);
		gc.fillOval(10, 30, 30, 30);
		Text green = new Text(0, 50, " Explored Node");
		//Red Node
		gc.setFill(Color.RED);
		gc.fillOval(10, 70, 30, 30);
		Text red = new Text(0, 88, " Unexplored Node");
		//Aqua Node
		gc.setFill(Color.AQUA);
		gc.fillOval(10, 110, 30, 30);
		Text aqua = new Text(0, 130, " Path Node");
		//Blue Line
		gc.setStroke(Color.DODGERBLUE);
		gc.strokeLine(10, 160, 40, 160);
		Text blue = new Text(0, 165, " Unexplored Line");


		Text bfs = new Text(0, 215, "Breadth First Search");
		gc.setStroke(Color.YELLOW);
		gc.strokeLine(10, 210, 40, 210);
		Text dfs = new Text(0, 235, "Depth First Search");
		gc.setStroke(Color.ORANGE);
		gc.strokeLine(10, 230, 40, 230);
		Text greedyBest = new Text(0, 255, "Greedy Best First Search");
		gc.setStroke(Color.BLACK);
		gc.strokeLine(10, 250, 40, 250);
		Text dijkstra = new Text(0, 275, "Dijkstras Algorithm");
		gc.setStroke(Color.PINK);
		gc.strokeLine(10, 270, 40, 270);
		Text astar = new Text(0, 295, "A* Search");
		gc.setStroke(Color.DARKGREEN);
		gc.strokeLine(10, 290, 40, 290);

		//Purple Line
		Text path = new Text(0, 335, "Final Path");
		gc.setStroke(Color.PURPLE);
		gc.strokeLine(10, 330, 40, 330);

		Text arrowHeads = new Text(0,360,"The size of the arrowheads is\nproportional to the cost of\nmoving in that direction.");
		
		algoNames.getChildren().addAll(green, red, aqua, blue, bfs, dfs, greedyBest, dijkstra, astar, path,arrowHeads);
		pane.setCenter(algoNames);
		Scene dialogScene = new Scene(pane, 300, 400);
		legendDialog.setScene(dialogScene);
	}


	/**
	 * The list of buttons,sliders,etc. for the GUI.
	 * @author mrt469
	 * @param v The visualiser that handles the backend
	 */
	public VBox configureOptions(Visualiser v) {
		Pane root = new Pane();
		VBox options = new VBox();
		options.setPadding(new Insets(50, 50, 50, 50));
		options.setSpacing(20);

		Graph graph = v.getController().getGraph();

		//Sets up checkboxes for selecting algorithms
		final CheckBox bfs = new CheckBox("Breadth First Search");
		final CheckBox dfs = new CheckBox("Depth First Search");
		final CheckBox greedyBFS = new CheckBox("Greedy Best First Search");
		final CheckBox dijkstra = new CheckBox("Dijkstra Search");
		final CheckBox astar = new CheckBox("A Star Search");

		//Sets tooltips for the algorithm checkboxes
		Tooltip bfsTT = new Tooltip();
		bfsTT.setText("Breadth First Search involves exploring every single neighboring node before moving on to explore the next, as such it will always find the path with the smallest numbder of nodes.");
		bfs.setTooltip(bfsTT);		
		Tooltip dfsTT = new Tooltip();
		dfsTT.setText("Depth First Search involves exploring one full path immediately, stepping back to the last fork in the road and taking another path each time a dead end is found.");
		dfs.setTooltip(dfsTT);		
		Tooltip greedyBFSTT = new Tooltip();
		greedyBFSTT.setText("Greedy Best First Search involves knowledge of where the endpoint exists within the graph and a heuristic algorithm to calculate how far each node in the frontier is from the goal node. Greedy Best First will attempt to move to the node closest to this endpoint in space, regardless of the weights of any path.");
		greedyBFS.setTooltip(greedyBFSTT);
		Tooltip dijkstraTT = new Tooltip();
		dijkstraTT.setText("Dijkstra Search involves exploring the nodes on the frontier that would result in the smallest overall path cost.");
		dijkstra.setTooltip(dijkstraTT);
		Tooltip astarTT = new Tooltip();
		astarTT.setText("A* Search onvolves a heuristic algorithm used to calculate the distance of a node from the goal node, whilst also taking into account path cost. It factors these together to search nodes on the frontier that have acceptable heuristic value and weight.");
		astar.setTooltip(astarTT);

		//Sets layout
		bfs.setLayoutX(25);
		bfs.setLayoutY(-25);
		dfs.setLayoutX(25);
		dfs.setLayoutY(0);
		greedyBFS.setLayoutX(25);
		greedyBFS.setLayoutY(25);
		dijkstra.setLayoutX(25);
		dijkstra.setLayoutY(50);
		astar.setLayoutX(25);
		astar.setLayoutY(75);

		Spinner<Integer> fromNode = new Spinner<>(0, graph.getNodes().size() - 1, 0);
		Spinner<Integer> toNode = new Spinner<>(0, graph.getNodes().size() - 1, 2);
		Label fromNodeL = new Label("From Node:");
		Label toNodeL = new Label("To Node:");
		fromNodeL.setLayoutX(25);
		fromNodeL.setLayoutY(100);
		toNodeL.setLayoutX(25);
		toNodeL.setLayoutY(145);
		fromNode.setLayoutX(75);
		fromNode.setLayoutY(120);
		fromNode.setEditable(true);
		toNode.setLayoutX(75);
		toNode.setLayoutY(165);
		toNode.setEditable(true);

		//Sets tooltips for the from and to nodes.
		Tooltip fromNodeTT = new Tooltip();
		fromNodeTT.setText("The initial node of the search. All searching algorithms will originate from this point.");
		fromNode.setTooltip(fromNodeTT);
		fromNodeL.setTooltip(fromNodeTT);
		Tooltip toNodeTT = new Tooltip();
		toNodeTT.setText("The destination node of the search. All searching algorithms will terminate upon finding this point.");
		toNode.setTooltip(toNodeTT);
		toNodeL.setTooltip(toNodeTT);

		final Slider speed = new Slider();
		speed.setMin(-0.3);
		speed.setMax(0.3);
		speed.setValue(0.15);
		speed.setLayoutX(25);
		speed.setLayoutY(260);
		speed.setShowTickMarks(true);
		speed.setMajorTickUnit(0.5);
		v.setSpeed(speed.getValue());

		play = new Button("Play");
		final Button pause = new Button("Pause");
		refresh = new Button("Refresh");
		play.setLayoutX(25);
		play.setLayoutY(195);
		pause.setLayoutX(75);
		pause.setLayoutY(195);
		pause.setDisable(true);
		refresh.setLayoutX(145);
		refresh.setLayoutY(195);

		refresh.setOnAction(e -> {
			refresh(v);
			pause.setDisable(true);
			play.setDisable(false);
		});

		speed.valueProperty().addListener((observable, oldValue, newValue) -> {
			v.setSpeed((Double) newValue);
		});

		play.setOnAction(e -> {



			Graph g = v.getController().getGraph();

			Node fNode = (Node) g.getNodes().get(getValue(fromNode));
			Node tNode = (Node) g.getNodes().get(getValue(toNode));


			if (fNode == tNode) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("The Start and Final nodes are the same!");
				alert.setContentText("Please select different start and end nodes!");

				//alert.show();
				return;
			}


			AlgorithmController a = v.getController();
			a.clearAlgorithms();

			//Adds selected algorithms to the algorithm controller
			if (bfs.isSelected()) {
				a.addAlgo(new BreadthFirst(a));
			}
			if (dfs.isSelected()) {
				a.addAlgo(new DepthFirst(a));
			}
			if (greedyBFS.isSelected()) {
				a.addAlgo(new GreedyBFS(a));
			}
			if (dijkstra.isSelected()) {
				a.addAlgo(new DijkstraSearch(a));
			}
			if (astar.isSelected()) {
				a.addAlgo(new AStarSearch(a));
			}


			if (a.getAlgoCount() == 0) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("No algorithm selected!");
				alert.setContentText("Please select an algorithm and press play again!");

				//alert.show();
			} else {

				refresh(v);
				pause.setDisable(false);
				a.setGraph(g);
				a.setTo(tNode);
				a.setFrom(fNode);
				a.runAlgorithms();
				System.out.println("running");
				play(v);
			}


		});

		pause.setOnAction(p -> {
			pause.setDisable(false);
			play.setDisable(true);
			final Button resume = new Button("Resume");
			resume.setLayoutX(75);
			resume.setLayoutY(195);
			root.getChildren().add(resume);
			v.setPause(true);
			resume.setOnAction(r -> {
				pause.setVisible(true);
				resume.setVisible(false);
				v.setPause(false);
			});
		});

		final Label speedLabel = new Label("Playback Speed");
		speedLabel.setLayoutX(25);
		speedLabel.setLayoutY(235);

		Tooltip speedTT = new Tooltip();
		speedTT.setText("Sets the speed of the visualisation. Can be both forward and backward");
		speed.setTooltip(speedTT);
		speedLabel.setTooltip(speedTT);

		ToggleButton legend = new ToggleButton("Legend");
		legend.setLayoutX(25);
		legend.setLayoutY(290);
		legend.setOnAction(e -> {
			if (!legendDialog.isShowing()) {
				legendDialog.show();
			} else {
				legendDialog.close();
			}
		});

		Button algoDialogBut = new Button("Algorithm Info");
		algoDialogBut.setLayoutX(90);
		algoDialogBut.setLayoutY(290);
		algoDialogBut.setOnAction(e -> {
			if (!algoDialog.isShowing()) {
				algoDialog.show();
			} else {
				algoDialog.close();
			}
		});
		
		Tooltip algoDialogTT = new Tooltip();
		algoDialogTT.setText("Displays a dialog to select which frontiers/heuritics/costs to view");
		algoDialogBut.setTooltip(algoDialogTT);

		Tooltip legendTT = new Tooltip();
		legendTT.setText("Displays a legend");
		legend.setTooltip(legendTT);

		Label graphGenTitle = new Label("Graph Generation");
		Spinner<Integer> noOfNodes = new Spinner<>(2, 20, 10);
		Label noOfNodesL = new Label("Number of nodes:");
		Spinner<Integer> connectivity = new Spinner<>(0, 100, 50);
		Label connectivityL = new Label("Connectivity:");
		Spinner<Integer> directedness = new Spinner<>(0, 100, 50);
		Label directednessL = new Label("Directedness:");
		CheckBox weighted = new CheckBox("Weighted");
		CheckBox planar = new CheckBox("Planar");
		CheckBox directednessCheck = new CheckBox("Directed");
		noOfNodes.setEditable(true);
		connectivity.setEditable(true);
		directedness.setEditable(true);

		Tooltip noOfNodesTT = new Tooltip();
		noOfNodesTT.setText("Select the number of nodes the generated graph will have");
		noOfNodes.setTooltip(noOfNodesTT);
		noOfNodesL.setTooltip(noOfNodesTT);
		Tooltip connectivityTT = new Tooltip();
		connectivityTT.setText("Select the connectivity of the graph (1 is not conencted, 100 is fully connected)");
		connectivity.setTooltip(connectivityTT);
		connectivityL.setTooltip(connectivityTT);
		Tooltip directednessTT = new Tooltip();
		directednessTT.setText("Select the directedness of the graph (1 is no directed edges, 100 is all edges directed)");
		directedness.setTooltip(directednessTT);
		directednessL.setTooltip(directednessTT);
		Tooltip weightedTT = new Tooltip();
		weightedTT.setText("Sets whether the generated graph will be weighted");
		weighted.setTooltip(weightedTT);		
		Tooltip planarTT = new Tooltip();
		planarTT.setText("Sets whether the generated graph will be planar");
		planar.setTooltip(planarTT);		
		Tooltip directednessCheckTT = new Tooltip();
		directednessCheckTT.setText("Sets whether the generated graph will be directed");
		directednessCheck.setTooltip(directednessCheckTT);

		graphGenTitle.setLayoutX(25);
		graphGenTitle.setLayoutY(340);
		graphGenTitle.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		noOfNodesL.setLayoutX(25);
		noOfNodesL.setLayoutY(375);
		noOfNodes.setLayoutX(75);
		noOfNodes.setLayoutY(400);
		connectivityL.setLayoutX(25);
		connectivityL.setLayoutY(430);
		connectivity.setLayoutX(75);
		connectivity.setLayoutY(455);
		directednessL.setLayoutX(25);
		directednessL.setLayoutY(485);
		directedness.setLayoutX(75);
		directedness.setLayoutY(515);
		directednessCheck.setLayoutX(25);
		directednessCheck.setLayoutY(570);
		directedness.setDisable(true);
		directednessCheck.setSelected(false);
		directednessCheck.setOnMouseClicked(e -> {
			if (directednessCheck.isSelected()) {
				directedness.setDisable(false);
			} else {
				directedness.setDisable(true);
			}
		});
		weighted.setLayoutX(25);
		weighted.setLayoutY(590);
		planar.setLayoutX(25);
		planar.setLayoutY(610);

		Button generate = new Button("Generate");
		Button _import = new Button("Import");
		Button export = new Button("Export");

		//Defines File Choosers for Import and Export
		FileChooser fileChooserI = new FileChooser();
		fileChooserI.setTitle("Import graph");
		FileChooser fileChooserE = new FileChooser();
		fileChooserE.setTitle("Export graph");
		fileChooserI.getExtensionFilters().add(new FileChooser.ExtensionFilter("Graph files", "*.csv"));
		fileChooserE.getExtensionFilters().add(new FileChooser.ExtensionFilter("Graph files", "*.csv"));
		fileChooserI.getExtensionFilters().add(new FileChooser.ExtensionFilter("All Files", "*"));

		Tooltip generateTT = new Tooltip();
		generateTT.setText("Generates a new graph using the attributes specified above");
		generate.setTooltip(generateTT);
		Tooltip _importTT = new Tooltip();
		_importTT.setText("Import a graph");
		_import.setTooltip(_importTT);
		Tooltip exportTT = new Tooltip();
		exportTT.setText("Export the current graph");
		export.setTooltip(exportTT);

		generate.setLayoutX(25);
		generate.setLayoutY(640);
		generate.setMinWidth(220);
		_import.setLayoutX(25);
		_import.setLayoutY(670);
		_import.setMinWidth(110);
		export.setLayoutX(135);
		export.setLayoutY(670);
		export.setMinWidth(110);

		generate.setOnAction(e -> {
			GraphGenerator generator = new GraphGenerator();
			Graph graph1 = generator.genSquared(noOfNodes.getValue(), connectivity.getValue(), weighted.isSelected(), directednessCheck.isSelected(), directedness.getValue());
			if (planar.isSelected()) {
				graph1 = generator.genPlanar(noOfNodes.getValue(), connectivity.getValue(), weighted.isSelected(), directednessCheck.isSelected(), directedness.getValue());
			}

            SpinnerValueFactory TOsvf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,graph1.getNumberOfNodes()-1,Math.min(toNode.getValue(),graph1.getNumberOfNodes()-1));
            SpinnerValueFactory FROMsvf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,graph1.getNumberOfNodes()-1, Math.min(fromNode.getValue(),graph1.getNumberOfNodes()-1));


            toNode.setValueFactory(TOsvf);
            fromNode.setValueFactory(FROMsvf);

			v.getController().clearAlgorithms();
			v.getController().setGraph(graph1);
			refresh(v);
		});

		_import.setOnAction(e -> {
			try {
				v.getController().setGraph(GraphStorage.importGraph(fileChooserI.showOpenDialog(legendDialog).getAbsolutePath()));
			} catch (Exception e1) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Import Failed!");
				alert.setContentText("Please select an appropriate file to import. (.csv)");

				//alert.show();
				e1.printStackTrace();
			}
			refresh(v);
		});

		export.setOnAction(e -> {
			try {
				GraphStorage.exportIntegerGraph(v.getController().getGraph(), fileChooserE.showSaveDialog(legendDialog).getAbsolutePath());
			} catch (Exception e1) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Export Failed!");
				alert.setContentText("Please select an appropriate export location.");

				//alert.show();
				e1.printStackTrace();
			}
			refresh(v);
		});


		root.getChildren().addAll(bfs, dfs, greedyBFS, dijkstra, astar, fromNodeL, toNodeL, fromNode, toNode);
		root.getChildren().addAll(play, pause, refresh, speedLabel, speed, legend, graphGenTitle);
		root.getChildren().addAll(noOfNodesL, noOfNodes, connectivityL, connectivity, directednessCheck, directednessL, directedness, weighted, planar);
		root.getChildren().addAll(generate, _import, export, algoDialogBut);
		options.getChildren().add(root);
		return options;
	}

	/**
	 * Configures the dialog that lets the user select which frontiers/costs/heuristics to view
	 * @param primaryStage The stage that will display the dialog
	 * @param v The visualiser where the data comes from
	 * @author mrt469
	 */
	public void algoDialog(Stage primaryStage, Visualiser v) {

		algoDialog = new Stage();
		algoDialog.setTitle("Algorithms");
		algoDialog.initModality(Modality.NONE);
		algoDialog.setX(primaryStage.getX() + 1000);
		algoDialog.setY(primaryStage.getY() + 400);
		algoDialog.initOwner(primaryStage);
		algoDialog.setHeight(200);

		BorderPane root = new BorderPane();

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0, 10, 0, 10));

		//add labels
		Text algorithms = new Text("Algorithms");
		grid.add(algorithms, 0, 0);
		Text frontier = new Text("Frontier");
		grid.add(frontier, 1, 0);
		Text heuristic = new Text("Heuristic");
		grid.add(heuristic, 2, 0);
		Text cost = new Text("Cost");
		grid.add(cost, 3, 0);

		Text bfsT = new Text("Breadth First");
		grid.add(bfsT, 0, 1);
		Text dfsT = new Text("Depth First");
		grid.add(dfsT, 0, 2);
		Text greedyBFST = new Text("Greedy Best First");
		grid.add(greedyBFST, 0, 3);
		Text dijkstraT = new Text("Dijkstra");
		grid.add(dijkstraT, 0, 4);
		Text astarT = new Text("A*");
		grid.add(astarT, 0, 5);


		//add the tickboxes
		final CheckBox bfsF = new CheckBox();
		grid.add(bfsF, 1, 1);
		final CheckBox dfsF = new CheckBox();
		grid.add(dfsF, 1, 2);
		final CheckBox greedyBFSF = new CheckBox();
		grid.add(greedyBFSF, 1, 3);
		final CheckBox dijkstraF = new CheckBox();
		grid.add(dijkstraF, 1, 4);
		final CheckBox astarF = new CheckBox();
		grid.add(astarF, 1, 5);

		final CheckBox greedyH = new CheckBox();
		grid.add(greedyH, 2, 3);
		final CheckBox astarH = new CheckBox();
		grid.add(astarH, 2, 5);

		final CheckBox dijkstraC = new CheckBox();
		grid.add(dijkstraC, 3, 4);
		final CheckBox astarC = new CheckBox();
		grid.add(astarC, 3, 5);

		root.setCenter(grid);
		Scene dialogScene = new Scene(root, 300, 400);
		algoDialog.setScene(dialogScene);


		//set listeners up for when the tickboxes change
		bfsF.selectedProperty().addListener((observable, oldValue, newValue) -> {
			v.setShowFrontier(0, newValue);
		});
		dfsF.selectedProperty().addListener((observable, oldValue, newValue) -> {
			v.setShowFrontier(1, newValue);
		});
		greedyBFSF.selectedProperty().addListener((observable, oldValue, newValue) -> {
			v.setShowFrontier(2, newValue);
		});
		dijkstraF.selectedProperty().addListener((observable, oldValue, newValue) -> {
			v.setShowFrontier(3, newValue);
		});
		astarF.selectedProperty().addListener((observable, oldValue, newValue) -> {
			v.setShowFrontier(4, newValue);
		});

		greedyH.selectedProperty().addListener((observable, oldValue, newValue) -> {
			v.setShowHeuristic(2, newValue);
		});
		astarH.selectedProperty().addListener((observable, oldValue, newValue) -> {
			v.setShowHeuristic(4, newValue);
		});

		dijkstraC.selectedProperty().addListener((observable, oldValue, newValue) -> {
			v.setShowCost(3, newValue);
		});
		astarC.selectedProperty().addListener((observable, oldValue, newValue) -> {
			v.setShowCost(4, newValue);
		});


	}

	/**
	 * Refreshes the visualiser
	 * @param v The visualiser to refresh
	 */
	private void refresh(Visualiser v) {
		visualiser.getChildren().clear();
		v.drawShapes();
		v.drawEdges();
		visualiser.getChildren().add(canvas);
	}


	/**
	 * Get the editor value of a spinner if it is valid
	 * @author dgj470
	 * @param spinner The spinner
	 * @return the editor value (as normally you get the environment value - but this requires you to press enter)
	 */
	private int getValue(Spinner<Integer> spinner) {
		// Get the spinner editor value
		String value = spinner.getEditor().getCharacters().toString();

		//parse it
		try {
			spinner.getValueFactory().setValue(Integer.parseInt(value));
		} catch (Exception ee) {
			spinner.getValueFactory().setValue(spinner.getValue());
		}

		return spinner.getValue();

	}

	/**
	 * Run the animation in the visualiser
	 * @param v
	 */
	private void play(Visualiser v) {
		Thread r = new Thread(v);
		r.start();
	}

	/**
	 * Main method, is only executed if launch cant be found
	 * @param args The program arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
