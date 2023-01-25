package OpaoPham;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * A memory card game made in JavaFX using buttons and scene changes.
 * @author Opao, Leala & Pham, Julie
 *
 */
public class GUIDriver extends GameControl {

	private static Stage stage;
	private static Text timerMsg = new Text(530, 30, ""); 
	private static Text flipGameMsg = new Text(5, 30, "");
	private static final double WIDTH = 650; 
	private static final double HEIGHT = 650;

	/**
	 * Stage for the JavaFX program and creates the display.
	 */
	@Override
	public void start(Stage stage) throws Exception {
		GUIDriver.stage = stage;
		Scene initialScene = setupNodesForInitialScene(stage);

		// DONT TOUCH
		displayScene(stage, initialScene);
	}

	/**
	 * This is for setting up the Nodes for Initial Scene
	 * @param stage - Stage for the Scene to perform on
	 * @return titleScene - Shows the title screen on the display
	 */
	public static Scene setupNodesForInitialScene(Stage stage) {
		Pane root = new Pane();

		// Title of program
		stage.setTitle("Graphical Memory Game OP");

		// Title
		Text titleBox = new Text(185, 50, "Memory Game");
		titleBox.setFont(Font.font("Helvetica", 45));

		// Footer
		Text footerMessage = new Text(220, 610, "Ready to begin?");
		footerMessage.setFont(Font.font("Helvetica", 30));

		VBox rootVB = new VBox();
		rootVB.setSpacing(30);

		VBox rootVB2 = new VBox();
		rootVB2.setSpacing(30);

		// VBRow 1
		GridPane gridpaneVB1 = new GridPane();
		gridpaneVB1.setHgap(20);
		gridpaneVB1.setVgap(20);
		gridpaneVB1.setAlignment(Pos.CENTER);

		Label message1 = new Label("Find and match all the cards");
		message1.setFont(Font.font("Helvetica", 25));
		message1.setAlignment(Pos.CENTER);
		gridpaneVB1.add(message1, 0, 0);

		// VBRow 2
		GridPane gridpaneVB2 = new GridPane();
		gridpaneVB2.setHgap(20);
		gridpaneVB2.setVgap(20);
		gridpaneVB2.setAlignment(Pos.CENTER);

		Label message2 = new Label("***Scoring Mechanism***");
		message2.setFont(Font.font("Helvetica", 30));
		gridpaneVB2.add(message2, 0, 0);
		gridpaneVB2.setAlignment(Pos.CENTER);

		// VBRow 3
		GridPane gridpaneVB3 = new GridPane();
		gridpaneVB3.setHgap(5);
		gridpaneVB3.setVgap(1);
		gridpaneVB3.setAlignment(Pos.CENTER);

		Text message3 = new Text("1. The number of times you flip open a pair is a deduction from your total score.");
		message3.setFont(Font.font("Helvetica", 17));
		gridpaneVB3.add(message3, 0, 1);

		Text message4 = new Text("2. The time it takes for you to finish is also a deduction from your total socre.");
		message4.setFont(Font.font("Helvetica", 17));
		gridpaneVB3.add(message4, 0, 2);

		// VBRow 4
		GridPane gridpaneVB4 = new GridPane();
		gridpaneVB4.setHgap(5);
		gridpaneVB4.setVgap(1);
		gridpaneVB4.setAlignment(Pos.CENTER);

		//Preview Images
		Image Image0 = new Image(
				"https://i.imgur.com/fHghMHm.png", 100, 0, true,
				false);
		gridpaneVB4.add(new ImageView(Image0), 0, 0);

		Image Image2 = new Image(
				"https://i.imgur.com/jT39UWS.png", 100, 0, true,
				false);
		gridpaneVB4.add(new ImageView(Image2), 1, 0);
		
		Image Image4 = new Image(
				"https://i.imgur.com/8oepfm1.png", 100, 0, true,
				false);
		gridpaneVB4.add(new ImageView(Image4), 2, 0);
		
		Image Image6 = new Image(
				"https://i.imgur.com/WHdpld6.png", 100, 0, true,
				false);
		gridpaneVB4.add(new ImageView(Image6), 3, 0);
		
		Image Image8 = new Image(
				"https://i.imgur.com/pzs7TSp.png", 100, 0, true,
				false);
		gridpaneVB4.add(new ImageView(Image8), 4, 0);
		
		Image Image10 = new Image(
				"https://i.imgur.com/q6lgvZz.png", 100, 0, true,
				false);
		gridpaneVB4.add(new ImageView(Image10), 5, 0);
		
		//Start game button
		GridPane gridpaneVBB = new GridPane();
		gridpaneVBB.setHgap(20);
		gridpaneVBB.setVgap(1);
		gridpaneVBB.setAlignment(Pos.BOTTOM_CENTER);

		// Start button
		Button startbtn = new Button("Start Game!");
		startbtn.setFont(Font.font("Helvetica", 30));
		gridpaneVBB.add(startbtn, 0, 30);

		// Adds Objects to Layout
		rootVB.setLayoutX(20);
		rootVB.setLayoutY(70);
		rootVB.setAlignment(Pos.CENTER);
		rootVB.getChildren().addAll(gridpaneVB1);
		rootVB.getChildren().addAll(gridpaneVB2);
		rootVB.getChildren().addAll(gridpaneVB3);
		rootVB.getChildren().addAll(gridpaneVB4);
		rootVB.getChildren().addAll(gridpaneVBB);

		root.getChildren().add(titleBox);
		root.getChildren().addAll(rootVB); // add Vbox inside pane
		root.getChildren().add(footerMessage);		
		
		// background
		Scene titleScene = new Scene(root, WIDTH, HEIGHT, Color.MEDIUMPURPLE);

		// logic
		startbtn.setOnAction(e -> {
			Scene endScene = setupNodesForEndScene();
			Scene currentScene = setupNodesForPlayingScene(stage, endScene);
			executeStartGame(stage, timerMsg, currentScene);

		});
		return titleScene;
	}
	
	/**
	 * Sets up Nodes for the Playing Scene
	 * @param stage - Stage for the Scene to perform on
	 * @return Scene currentPlayingScene - Updates the playing scene based on player-input
	 */
	public static Scene setupNodesForPlayingScene(Stage stage, Scene endscene) {
		Pane root = new Pane();
		GridPane gridPane = new GridPane();
		
		createCardPairs();
		constructDeck(stage, endscene, flipGameMsg);
		addCardsToGridNode(gridPane);
		
		resetNuberOfFlippedCards();

		gridPane.setMinSize(200, 200);
		gridPane.setPadding(new Insets(20, 20, 20, 50));
		gridPane.setVgap(50);
		gridPane.setHgap(50);

		// Setting the Grid alignment
		gridPane.setAlignment(Pos.CENTER);

		Text matchMsg = new Text(250, 610, "");
		matchMsg.setId("matchMessage");
		
		timerMsg.setId("timerMessage");
		timerMsg.setFont(Font.font("Helvetica", 30));

		flipGameMsg.setText("Number of Flips: 0");
		flipGameMsg.setId("timerMessage");
		flipGameMsg.setFont(Font.font("Helvetica", 30));
		
		root.getChildren().add(gridPane);
		root.getChildren().add(timerMsg);
		root.getChildren().add(flipGameMsg);
		root.getChildren().add(matchMsg);

		Scene currentPlayingScene = new Scene(root, WIDTH, HEIGHT);

		return currentPlayingScene;
	}
	
	/**
	 * Setup the Nodes for the End Screen Scene.
	 * @return scene - Returns the end screen scene.
	 */
	public static Scene setupNodesForEndScene() {
		Pane root = new Pane();
		
		Text titleBox = new Text(120, 50, "MEMORY GAME: COMPLETE");
		titleBox.setFont(Font.font("Helvetica", 30));
		
		Text midMessage = new Text(150, 200, "you got ALL THE PAIRS!");
		midMessage .setFont(Font.font("Helvetica", 30));

		Text numFlipsMessage = new Text(150, 250, "");
		numFlipsMessage.setId("FlippedMessage");
		numFlipsMessage.setFont(Font.font("Helvetica", 30));

		Text timerSeconds = new Text(150, 300, "");
		timerSeconds.setId("TimerMessage");
		timerSeconds.setFont(Font.font("Helvetica", 30));

		Text totalScore = new Text(150, 350, "");
		totalScore .setId("ScoreMessage");
		totalScore.setFont(Font.font("Helvetica", 30));

		Text finalMessage = new Text(220, 450, "Thanks for playing!");
		finalMessage.setFont(Font.font("Helvetica", 30));
		
		Button replaybtn = new Button("Play Again?");
		replaybtn.setFont(Font.font("Helvetica", 30));
		replaybtn.setLayoutX(220);
	    replaybtn.setLayoutY(500);
	    replaybtn.setOnAction(e-> {
	    	displayScene(stage, setupNodesForInitialScene(stage));
	    	
	    });

	    root.getChildren().add(titleBox);
		root.getChildren().add(numFlipsMessage);
		root.getChildren().add(timerSeconds);
		root.getChildren().add(totalScore);
		root.getChildren().add(midMessage);
		root.getChildren().add(finalMessage);
		root.getChildren().add(replaybtn);

		Scene scene = new Scene(root, WIDTH, HEIGHT);
		
		return scene;
	}
	
	/**
	 * Launches the JavaFX Application.
	 * @param args
	 */
	// ALSO DONT TOUCH
	public static void main(String[] args) {
		launch(args);
	}

}