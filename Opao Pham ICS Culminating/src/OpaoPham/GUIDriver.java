package OpaoPham;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * 
 * @author Opao, Leala & Pham, Julie
 *
 */
public class GUIDriver extends Application {

	private static String folderPath = System.getProperty("user.dir");
	
	private static Timer gameTimer = new Timer();
	private static Text timerMsg = new Text(530, 30, "");
	private static Text flipGameMsg = new Text(5, 30, "");

	private static final double WIDTH = 650;
	private static final double HEIGHT = 650;

	private static final int NUM_ROWS = 3;
	private static final int NUM_COLS = 4;

	private static Deck deck = null;
	
	private static int numFlipCards = 0;

	private static Card[][] slots = new Card[NUM_ROWS][NUM_COLS];
	
	private static Card lastCard = null;

	private static int timerCounter = 0;
	/**
	 * Stage for the JavaFX program and creates the display.
	 */
	@Override
	public void start(Stage stage) throws Exception {
		Scene initialScene = setupNodesForInitialScene(stage);

		// DONT TOUCH
		displayScene(stage, initialScene);
	}

	private static TimerTask gameTask = new TimerTask() {
	    public void run() {
	    	timerCounter++;
	    	timerMsg.setText("Timer: " + String.valueOf(timerCounter));
	    }

	};


	/**
	 * This is for setting up the Nodes for Initial Scene
	 * @param stage
	 * @param root
	 */
	public static Scene setupNodesForInitialScene(Stage stage) {
		Pane root = new Pane();

		// background
		Scene titleScene = new Scene(root, WIDTH, HEIGHT, Color.MEDIUMPURPLE);

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

		Text message3 = new Text("1. The number of times you have flipped a card is a deduction to your total score");
		message3.setFont(Font.font("Helvetica", 17));
		gridpaneVB3.add(message3, 0, 1);

		Text message4 = new Text("2. You also have set number of lives");
		message4.setFont(Font.font("Helvetica", 17));
		gridpaneVB3.add(message4, 0, 2);

		// VBRow 4
		GridPane gridpaneVB4 = new GridPane();
		gridpaneVB4.setHgap(5);
		gridpaneVB4.setVgap(1);
		gridpaneVB4.setAlignment(Pos.CENTER);

		// Left Image
		Image leftImage = new Image(
				"https://cdn0.iconfinder.com/data/icons/symbols-symbols-add-on-3-vol-1/48/v-29-512.png", 150, 0, true,
				false);
		gridpaneVB4.add(new ImageView(leftImage), 0, 0);

		// Right Image
		Image rightImage = new Image(
				"https://cdn0.iconfinder.com/data/icons/symbols-symbols-add-on-3-vol-1/48/v-29-512.png", 150, 0, true,
				false);
		gridpaneVB4.add(new ImageView(rightImage), 1, 0);

		// Buttons (Reduce, Add, Multiply)
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
		
		// logic
		startbtn.setOnAction(e -> {
	    	gameTimer.scheduleAtFixedRate(gameTask, 0, 1000l);
			displayScene(stage, setupNodesForPlayingScene(stage));

		});
		return titleScene;
	}
		
	/**
	 * Sets up Nodes for Play Scene
	 * @param stage
	 * @return
	 */
	public static Scene setupNodesForPlayingScene(Stage stage) {
		Pane root = new Pane();
		GridPane gridPane = new GridPane();
		
		createCardPairs();
		constructDeck(stage);
		addCardsToGridNode(gridPane);
		
		numFlipCards = 0;

		gridPane.setMinSize(200, 200);
		gridPane.setPadding(new Insets(20, 20, 20, 20));
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

		Scene scene = new Scene(root, WIDTH, HEIGHT);

		return scene;
	}
	
	

	/**
	 * Setup the Nodes for End Scene
	 * @return
	 */
	public static Scene setupNodesForEndScene() {
		Pane root = new Pane();
				
		Text titleBox = new Text(100, 50, "CONGRATS!!! U DONE GAME");
		titleBox.setFont(Font.font("Helvetica", 30));
		
		Text midMessage = new Text(150, 300, "you got ALL THE PAIRS!");
		midMessage .setFont(Font.font("Helvetica", 30));

		
		Text numFlipsMessage = new Text(150, 350, "Number of Flips: " + numFlipCards);
		numFlipsMessage.setFont(Font.font("Helvetica", 30));

		Text timerSeconds = new Text(150, 400, "Number of Seconds: " + timerCounter);
		timerSeconds.setFont(Font.font("Helvetica", 30));

		Text totalScore = new Text(150, 450, "Total Score : " + (1000 - numFlipCards - timerCounter));
		totalScore.setFont(Font.font("Helvetica", 30));

		Text finalMessage = new Text(220, 610, "THX for playing!");
		finalMessage.setFont(Font.font("Helvetica", 30));

		root.getChildren().add(titleBox);
		root.getChildren().add(numFlipsMessage);
		root.getChildren().add(timerSeconds);
		root.getChildren().add(totalScore);
		root.getChildren().add(midMessage);
		root.getChildren().add(finalMessage);

		Scene scene = new Scene(root, WIDTH, HEIGHT);

		gameTimer.cancel();	//Stop the timer
		
		return scene;
	}

	/**
	 * This renders and displays the Scene after all Nodes have been setup
	 * @param stage
	 * @param Scene
	 */
	public static void displayScene(Stage stage, Scene scene) {
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Creates card pairs, assigns images to the card pairs and shuffles the deck.
	 */
	public static void createCardPairs() {
		ArrayList<Card> randomCards = new ArrayList<Card>();
		for (int i = 0; i < 11; i += 2) {
			Card card1 = new Card(folderPath + "\\" + i + ".png");
			randomCards.add(card1);
			Card card2 = new Card(folderPath + "\\" + i + ".png");
			randomCards.add(card2);

			// System.out.print(folderPath + "\\" + i + ".png");
		}
		Collections.shuffle(randomCards);

		deck = new Deck(randomCards); // Initialize the Deck
	}

	/**
	 * Constructs the Deck of Cards and defines what Columns or Rows it belows to.
	 * Also configures the Action when the Card is Clicked
	 * @param stage
	 */
	public static void constructDeck(Stage stage) {
		int k = 0;
		// setup slots as NewButton objects, creating button
		for (int i = 0; i < NUM_ROWS; i++) {
			for (int j = 0; j < NUM_COLS; j++) {
				Card cardButton = deck.getDeck().get(k);
				cardButton.setRowColumn(i, k);
				slots[i][j] = cardButton;
				// slots[i][j].setMinSize(WIDTH / NUM_COLS, HEIGHT / NUM_ROWS);
				// slots[i][j].setMaxSize(WIDTH / NUM_COLS, HEIGHT / NUM_ROWS);

				slots[i][j].setMinSize(100, 150);
				slots[i][j].setMaxSize(100, 150);
				
				slots[i][j].setOnAction(e -> {
					Card currentCard = (Card) e.getSource();
					executeCardActions(currentCard, stage);
				});

				k++;
			}
		}
	}

	/**
	 * This contains all actions that happens when a card is pressed / clicked
	 * @param currentCard
	 * @param stage
	 */
	public static void executeCardActions(Card currentCard, Stage stage) {
		
		//Iterates items in Parent Node until it finds specific card
		Text matchMsg = pullCardFromNode(currentCard);

		//Validate that cards are not clicked simultaneously
		if (!currentCard.equals(lastCard)) {
			// Check if previous two cards are matching and set IsCardMatched flag in each
			// card
			setMatchedFlagOnCards();

			// validate if 2 less cards are faceup
			if (deck.getFaceUpCardsStatus()) {

				faceUpCard(currentCard); // UI
				int unmatchedCount = deck.getUnmatchedCount();

				if (unmatchedCount == 2 && !deck.isCardMatch()) {
					matchMsg.setText("Not Match!");
					matchMsg.setFont(new Font("Helvetica", 30));
					countFlipCards();
				} else if (deck.isCardMatch()) {
					matchMsg.setText("Match!");
					matchMsg.setFont(new Font("Helvetica", 30));
					countFlipCards();
				}

			} else {
				// Place Last Two Cards Facedown if they don't match
				faceDownCards(currentCard);
			}

			checkEndGame(stage);
		}
		
		lastCard = currentCard;
	}

	/**
	 * Iterates through all Faced Up Cards and sets the IsMatched Flag
	 */
	public static void setMatchedFlagOnCards() {
		if (deck.isCardMatch()) {
			ArrayList<Card> cards = deck.getFacedUpCards();
			for (int c = cards.size() - 2; c < cards.size(); c++) {
				cards.get(c).setIsCardMatched();
			}						
		}
	}

	/**
	 * Iterates items in Parent Node until it finds specific card
	 * @param currentCard
	 * @return
	 */
	public static Text pullCardFromNode(Card currentCard) {
		//Find the child "matchMessage" in order to set Text
		Pane pane = (Pane)currentCard.getParent().getParent();					
		Text matchMsg = null; //Placeholder for match message			
		ObservableList<Node> children = pane.getChildren();
		for (int a = 0; a < children.size(); a++) {
			Node child = children.get(a);
			if (child.getId() == "matchMessage") {
				matchMsg = ((Text)child);
			}
		}
		return matchMsg;
	}

	private static void countFlipCards() {
		numFlipCards++;
		flipGameMsg.setText("Number of Flips: " + String.valueOf(numFlipCards));
	}

	/**
	 * Adds cards to Grid Pane
	 * @param gridPane
	 */
	public static void addCardsToGridNode(GridPane gridPane) {
		for (int i = 0; i < NUM_ROWS; i++) {
			for (int j = 0; j < NUM_COLS; j++) {
				gridPane.add(slots[i][j], j, i + 1);
			}
		}
	}

	/**
	 * 
	 * @param currentCard
	 */
	public static void faceUpCard(Card currentCard) {
		int butsize = 80;
		Image img = new Image(currentCard.getCardLocName());
		ImageView view = new ImageView(img);
		view.setFitHeight(butsize);
		view.setPreserveRatio(true);
		currentCard.setPrefSize(butsize, butsize);
		// Setting a graphic to the button
		currentCard.setGraphic(view);

		deck.addFacedUpCard(currentCard);
	}

	/**
	 * 
	 * @param currentCard
	 */
	public static void faceDownCards(Card currentCard) {
		ArrayList<Card> faceUpCards = deck.getFacedUpCards();
		int startpos = faceUpCards.size() - 2;
		int endpos = faceUpCards.size();
		for (int i = startpos; i < endpos; i++) {
			faceDownCard(faceUpCards.get(startpos));
			faceUpCards.remove(startpos);
		}
		faceUpCard(currentCard);
	}

	// Faces down a specific card
	/**
	 * 
	 * @param cardbutton
	 */
	public static void faceDownCard(Card cardbutton) {
		// Setting a graphic to the button
		cardbutton.setGraphic(null);
	}

	
	public static void checkEndGame(Stage stage) {
		// if FaceUpCards == 12, checkEndGame = true
		System.out.print(deck.getFacedUpCards().size());
		if(deck.getFacedUpCards().size() == 12 && numFlipCards >= 12 ) {
			displayScene(stage, setupNodesForEndScene());
		}
	}

	/**
	 * 
	 * @param args
	 */
	// ALSO DONT TOUCH
	public static void main(String[] args) {
		launch(args);
	}

}