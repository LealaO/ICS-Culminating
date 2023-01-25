package OpaoPham;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Class that creates the game mechanics and gimmicks
 * @author Opao, Leala & Pham, Julie
 *
 */
public class GameControl extends Application  {
	private static Timer gameTimer = new Timer(); 
	private static int timerCounter = 0;
	private static int numFlipCards = 0;

	private static String folderPath = System.getProperty("user.dir");
	
	private static final int NUM_ROWS = 3;
	private static final int NUM_COLS = 4;

	private static Deck deck = null;
	
	private static Card[][] slots = new Card[NUM_ROWS][NUM_COLS];
	
	private static Card lastCard = null;

	@Override
	public void start(Stage arg0) throws Exception {
		// Not used in Game Control
		
	}
	
	/**
	 * Resets number of flipped-up cards to 0.
	 */
	protected static void resetNumberOfFlippedCards() {
		numFlipCards = 0;
	}

	/**
	 * Starts the Game when the Start Game Button is pressed
	 * @param stage - Display for the JavaFX
	 * @param scene - Chosen scene to be displayed in the application
	 */
	protected static void executeStartGame(Stage stage, Text timerMsg, Scene scene) {
		gameTimer = new Timer();
		gameTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
		    	timerCounter++;		    	
		    	timerMsg.setText("Timer: " + String.valueOf(timerCounter));					
			}				
		}, 0, 1000l);
		timerCounter = 0;
		displayScene(stage, scene);
	}


	/**
	 * This renders and displays the Scene after all Nodes have been setup
	 * @param stage - Display for the JavaFX
	 * @param scene - Chosen scene to be displayed in the application
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
	 * @param stage - Display for the JavaFX
	 */
	public static void constructDeck(Stage stage, Scene endscene, Text flipGameMsg) {
		int k = 0;
		// setup slots as NewButton objects, creating button
		for (int i = 0; i < NUM_ROWS; i++) {
			for (int j = 0; j < NUM_COLS; j++) {
				Card cardButton = deck.getDeck().get(k);
				cardButton.setRowColumn(i, k);
				slots[i][j] = cardButton;

				slots[i][j].setMinSize(100, 150);
				slots[i][j].setMaxSize(100, 150);
				
				slots[i][j].setOnAction(e -> {
					Card currentCard = (Card) e.getSource();
					executeCardActions(currentCard, stage, endscene, flipGameMsg);
				});

				k++;
			}
		}
	}

	/**
	 * This contains all actions that happens when a card is pressed / clicked
	 * @param currentCard - Current card that was selected
	 * @param stage - Display for the JavaFX
	 */
	public static void executeCardActions(Card currentCard, Stage stage, Scene endscene, Text flipGameMsg) {
		
		//Iterates items in Parent Node until it finds specific card
		Text matchMsg = pullCardFromNode(currentCard);

		//Validate that cards are not clicked simultaneously and check only for facedown cards
		if (!currentCard.equals(lastCard) && currentCard.getGraphic() == null) {
			// Check if previous two cards are matching and set IsCardMatched flag in each card
			setMatchedFlagOnCards();

			// validate if 2 less cards are faceup
			if (deck.getFaceUpCardsStatus()) {

				faceUpCard(currentCard); // UI
				int unmatchedCount = deck.getUnmatchedCount();

				if (unmatchedCount == 2 && !deck.isCardMatch()) {
					matchMsg.setText("Not Match!");
					matchMsg.setFont(new Font("Helvetica", 30));
					countFlipCards(flipGameMsg);
				} else if (deck.isCardMatch()) {
					matchMsg.setText("Match!");
					matchMsg.setFont(new Font("Helvetica", 30));
					countFlipCards(flipGameMsg);
					
					Button faceUpCards = new Button();
					for (int i=0; i <deck.getFacedUpCards().size(); i++) {
						faceUpCards = deck.getFacedUpCards().get(i);
						faceUpCards.setDisable(true);
					}
					
				}

			} else {
				// Place Last Two Cards Facedown if they don't match
				faceDownCards(currentCard);
			}

			checkEndGame(stage, endscene);
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
	 * @param currentCard - Current card that was selectesd
	 * @return matchMsg - Label that states if the cards are matched or not
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
	
	/**
	 * 
	 * @param flipGameMsg - Change the integer value of number of Flips in this Text.
	 */
	private static void countFlipCards(Text flipGameMsg) {
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
	 * Faces up a specific card.
	 * @param currentCard - Current card that was selected
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
	 * Faces down a specific card.
	 * @param currentCard - Current card that was seleceted
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

	/**
	 * Sets a graphic to the button.
	 * @param cardbutton
	 */
	public static void faceDownCard(Card cardbutton) {
		cardbutton.setGraphic(null);
	}

	/**
	 * Checks if the game has ended or not.
	 * @param stage - Display for the JavaFX
	 * @param endscene - Chosen scene to be displayed in the application
	 */
	public static void checkEndGame(Stage stage, Scene endscene) {
		// if FaceUpCards == 12, checkEndGame = true
		if(deck.getFacedUpCards().size() == 12 && numFlipCards >= 6) {
			
			Parent rootView = endscene.getRoot();
			ObservableList<Node> children = ((Pane)rootView).getChildren();
			for (int a = 0; a < children.size(); a++) {
				Node child = children.get(a);
				if (child.getId() == "FlippedMessage") {
					Text numFlipsMessage = ((Text)child);
					numFlipsMessage.setText("Number of Flips: " + numFlipCards);
				}
				else if (child.getId() == "TimerMessage") {
					Text timerSeconds = ((Text)child);
					timerSeconds.setText("Number of Seconds: " + timerCounter);
				}
				else if (child.getId() == "ScoreMessage") {
					Text totalScore = ((Text)child);
					totalScore.setText("Total Score : " + (1000 - numFlipCards - timerCounter));
				}
			}

			displayScene(stage, endscene);
			gameTimer.cancel();
		}
	}			
}