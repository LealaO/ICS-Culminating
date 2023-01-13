package OpaoPham;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUIDriver extends Application {

    @Override
    public void start(Stage stage) throws Exception{
    	VBox root = new VBox();
		root.setSpacing(30);

		// background
		Scene scene = new Scene(root, 650, 650, Color.ALICEBLUE);

		// Title of program
		stage.setTitle("Graphical Memory Game OP");

		// VBRow 1
		Text message = new Text("*Welcome to Memory Game*");
		message.setFont(Font.font("Helvetica", 40));
		root.setAlignment(Pos.TOP_CENTER);
		
		// VBRow 2
		GridPane gridpaneVB2 = new GridPane();
		gridpaneVB2.setHgap(10);
		gridpaneVB2.setVgap(10);
		gridpaneVB2.setAlignment(Pos.CENTER);
		
		//1st line of instructions
		Label message1 = new Label("Find and match all the cards before the timer runs out!");
		message1.setFont(Font.font("Helvetica", 20));
		// message1.setAlignment(Pos.CENTER);
		gridpaneVB2.add(message1, 0, 0);
		
		//2nd line of instructions
		Label message2 = new Label("***Scoring Mechanism***");
		message2.setFont(Font.font("Helvetica", 20));
		// message2.setAlignment(Pos.CENTER);
		gridpaneVB2.add(message2, 0, 1);
		
		// Buttons (Reduce, Add, Multiply)
		GridPane gridpaneVB3 = new GridPane();
		gridpaneVB3.setHgap(10);
		gridpaneVB3.setVgap(10);
		gridpaneVB3.setAlignment(Pos.BOTTOM_CENTER);
		
		//Start button
		Button startbtn = new Button("Start Game!");
		startbtn.setFont(Font.font("Helvetica", 30));
		gridpaneVB3.add(startbtn, 0, 0);
		
		// logic 
		startbtn.setOnAction(e -> {
			
			
		});
		
		// Adds everything
		root.getChildren().add(message);
		root.getChildren().addAll(gridpaneVB2);
		root.getChildren().addAll(gridpaneVB3);
		
		//DONT TOUCH
		stage.setScene(scene);
		stage.show();
    }

    
    //ALSO DONT TOUCH
    public static void main(String[] args) {
        launch(args);
    }
}

