package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import application.model.Scoreboard;

/**
 * The EndScreenController class is a class used to listen for events in the EndScreen.fxml document
 * 
 * @author Spenser Wright (azu874)
 *
 */
public class EndScreenController implements EventHandler<ActionEvent>, Initializable {
	
	//public static boolean resetGame = false;
	private int score = GameController.scoreCounter; //might need to change to static to work with scoreboard.
	private String userName = "";
	private String fileName = ("./data/Scoreboard/scoreBoard.txt");

	@FXML
	ImageView backgroundImage;
	@FXML
	Label endScreenMessage;
	@FXML
	Label playerScore;
	@FXML
	Label scoreSubmittedLabel;
	@FXML
	TextField userNameTextField;
	@FXML
	Button submitToScoreboardButton;
	@FXML
	Button retryButton;
	@FXML
	Button returnToMenuButton;
	
	/**
	 * This is an initializing method that is used to populate some of the GUI components of the End Screen View.
	 * 
	 * @author Spenser Wright (azu874)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		if(score >= 10) {
			Image winImage = new Image("file:images/EndScreens/CongratsView.png");
			backgroundImage.setImage(winImage);
			
			endScreenMessage.setText("Congratulations! You've made it to class on time!");
		}
		else {
			Image loseImage = new Image("file:images/EndScreens/TryAgainView.png");
			backgroundImage.setImage(loseImage);
			
			endScreenMessage.setText("Try Again! You got stuck in traffic and are late for class :(");
		}
		
		playerScore.setText(String.valueOf(score));
		
	}

	/**
	 * This handle method is responsible for taking in user input for usernames in the endscreen and passing that data into the scoreboard.java class
	 * 
	 * @author Spenser Wright (azu874)
	 */
	@Override
	public void handle(ActionEvent event) {
	
		userName = userNameTextField.getText();
		
		try {
			Scoreboard.loadNewScore(fileName, userName, score);
			userNameTextField.setText("");
			userNameTextField.setEditable(false);
			scoreSubmittedLabel.setText("Score Submitted, Thanks for Playing!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	
	/**
	 * This handle method is responsible for directing the user to the difficulty view
	 * 
	 * @author Spenser Wright (azu874)
	 */
	public void retry(ActionEvent event) {
		//resetGame = true;
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/Difficulty.fxml"));
			//Parent root = FXMLLoader.load(getClass().getResource("../view/LevelSelect.fxml"));
			//Main.stage.requestFocus();
			
			Main.stage.setScene(new Scene(root, 800, 800));
			Main.stage.getScene().getRoot().requestFocus();
			Main.stage.show();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * This handle method is responsible for directing the user to the Main view
	 * 
	 * @author Spenser Wright (azu874)
	 */
	public void quit(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/Main.fxml"));
			Main.stage.setScene(new Scene(root, 800, 800));
			Main.stage.show();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
