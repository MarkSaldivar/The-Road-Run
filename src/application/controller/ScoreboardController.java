package application.controller;

//import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Main;
import application.model.Scoreboard;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;

/**
 * The ScoreboardController class is a class used to listen for events in the Scoreboard.fxml document
 * 
 * 
 * @author Tyler Unruh (hsg211)
 * @author John Moran (qvv333)
 * @author Mark Saldivar (bpr926)
 *
 */
public class ScoreboardController implements EventHandler<ActionEvent>, Initializable {
	
	@FXML
	Button backButton;
	//@FXML
	//Button LevelSelect;
	@FXML
	TextArea KeepGoingQuotes;
	@FXML
	Label KeepgoingLabel;
	@FXML
	ListView<String> scores;
	ObservableList<String> items = FXCollections.observableArrayList();
	
	/**
	 * This initialize method is used to populate the scores in the scoreboard
	 * 
	 * @author Tyler Unruh (hsg211)
	 * @author John Moran (qvv333)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		int randomNumber = Scoreboard.getRandNumber();
		
		KeepgoingLabel.setText(Scoreboard.loadQuote("./data/Scoreboard/keepGoing.txt",randomNumber).toString());
		
		
		ArrayList<String> scoresList = Scoreboard.getScores("./data/Scoreboard/scoreBoard.txt");
		items.addAll(scoresList);
		scores.getItems().addAll(items);
		
		
	}
	
	
	/**
	 * This handle method will return the user to the Main menu
	 *
	 * @author Mark Saldivar (bpr926)
	 */
	@Override 
	public void handle(ActionEvent event) {
		//System.out.println("Scoreboard Back Button Works");
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/Main.fxml"));
			Main.stage.setScene(new Scene(root, 800, 800));
			Main.stage.show();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This handle method will move the user to the difficulty selection sccreen
	 * 
	 * @author Mark Saldivar (bpr926) 
	 */
	public void handle2(ActionEvent event) {
		//System.out.println("Scoreboard Level select Button Works");
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/Difficulty.fxml"));
			Main.stage.setScene(new Scene(root, 800, 800));
			Main.stage.getScene().getRoot().requestFocus();
			Main.stage.show();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


	
	
	//add more handle methods here for the different buttons in the scoreboard. Remember javadocs!!
		
}