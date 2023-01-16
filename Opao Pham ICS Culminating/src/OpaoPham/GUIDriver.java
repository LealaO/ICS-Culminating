package OpaoPham;

import java.util.ArrayList;
import java.util.Collections;

// hi

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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


public class GUIDriver extends Application {

	private static final double WIDTH = 650;
	private static final double HEIGHT = 650;

	private static final int NUM_ROWS = 3;
	private static final int NUM_COLS = 4;

	private static Deck deck = null;
	
	private static Card[][] slots = new Card[NUM_ROWS][NUM_COLS];

	@Override
	public void start(Stage stage) throws Exception {
		Pane root = new Pane();		
		VBox rootVB = new VBox();
		rootVB.setSpacing(30);

		// background
		Scene scene = new Scene(root, WIDTH, HEIGHT, Color.MEDIUMPURPLE);

		// Title of program
		stage.setTitle("Graphical Memory Game OP");

		// message
		Text message = new Text(185, 50, "Memory Game");
		message.setFont(Font.font("Helvetica", 45));
		

		// VBRow 1
		GridPane gridpaneVB1 = new GridPane();
		gridpaneVB1.setHgap(20);
		gridpaneVB1.setVgap(20);
		gridpaneVB1.setAlignment(Pos.CENTER);

		Label message1 = new Label("Find and match all the cards before the timer runs out!");
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
		
		//Left Image
		Image leftImage = new Image("https://cdn0.iconfinder.com/data/icons/symbols-symbols-add-on-3-vol-1/48/v-29-512.png", 150, 0, true, false);
		gridpaneVB4.add(new ImageView(leftImage), 0, 0);
		
		//Right Image
		Image rightImage = new Image("https://cdn0.iconfinder.com/data/icons/symbols-symbols-add-on-3-vol-1/48/v-29-512.png", 150, 0, true, false);
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
		
		// VBRow 4
		Text message5 = new Text(220, 610, "Ready to begin?");
		message5.setFont(Font.font("Helvetica", 30));
		
		// logic
		startbtn.setOnAction(e -> {

		});

		//Adds Objects to Layout
		rootVB.setLayoutX(20);
		rootVB.setLayoutY(70);
		rootVB.setAlignment(Pos.CENTER);
		rootVB.getChildren().addAll(gridpaneVB1);
		rootVB.getChildren().addAll(gridpaneVB2);
		rootVB.getChildren().addAll(gridpaneVB3);
		rootVB.getChildren().addAll(gridpaneVB4);
		rootVB.getChildren().addAll(gridpaneVBB);
		
		root.getChildren().add(message);
		root.getChildren().addAll(rootVB);  //add Vbox inside pane
		root.getChildren().add(message5);
		
		// DONT TOUCH
		stage.setScene(scene);
		stage.show();
	}
	

	// ALSO DONT TOUCH
	public static void main(String[] args) {
		createCardPairs();
		setUpCardLayout();
		launch(args);
	}
	
	public static void createCardPairs() {
		ArrayList<Card> randomCards = new ArrayList<Card>();
		for(int i = 0; i < 11; i+=2) {
			Card card = new Card(i + ".png");
			randomCards.add(card);
			randomCards.add(card);
		}
		Collections.shuffle(randomCards);
		
		deck = new Deck(randomCards); //Initialize the Deck
	}
	
	public static void setUpCardLayout() {
		int k = 0;
		// setup slots as NewButton objects, creating button
		for (int i = 0; i < NUM_ROWS; i++) {
			for (int j = 0; j < NUM_COLS; j++) {
				Card cardButton = deck.getDeck().get(k);
				cardButton.setRowColumn(i, k);
				slots[i][j] = cardButton;
				slots[i][j].setMinSize(WIDTH/NUM_COLS, HEIGHT/NUM_ROWS);
				slots[i][j].setMaxSize(WIDTH/NUM_COLS, HEIGHT/NUM_ROWS);
				
				slots[i][j].setOnAction(e ->{
					Card currentCard = (Card)e.getSource();
					
					//Logic to faceUp & FaceDown
					//validate if 2 less cards are faceup
					if(deck.getFaceUpCardsStatus()) {
						faceUpCard(currentCard);	//UI
						deck.addFacedUpCard(currentCard);
					}
					else
					{
						faceDownCards();
					}
				});
				k++;
			}
		}
	}
	
	public static void addCardsToGrid(GridPane gridPane) {
		for (int i = 0; i < NUM_ROWS; i++) {
			for (int j = 0; j < NUM_COLS; j++) {
				gridPane.add(slots[i][j], j,i+1);
			}
		}
	}
	
	public static void faceUpCard(Card cardbutton) {
		int butsize = 80;
		Image img = new Image(cardbutton.getCardLocName());
		ImageView view = new ImageView(img);
		view.setFitHeight(butsize);
		view.setPreserveRatio(true);		
		cardbutton.setPrefSize(butsize, butsize);				
		//Setting a graphic to the button
		cardbutton.setGraphic(view);
	}

	public static void faceDownCards() {
		ArrayList<Card> faceUpCards = deck.getFacedUpCards();
		for	(int i =0; i < faceUpCards.size(); i++) {
			faceDownCard(faceUpCards.get(i));
		}
	}
	
	
	//Faces down a specific card
	public static void faceDownCard(Card cardbutton) {
		//Setting a graphic to the button
		cardbutton.setGraphic(null);
	}

}
