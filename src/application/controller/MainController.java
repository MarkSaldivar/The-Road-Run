package application.controller;

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
//import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;

/**
 * The MainController class is a class used to listen for events in the Main.fxml document
 * 
 * 
 * @author Spenser Wright (azu874)
 *
 */
public class MainController implements EventHandler<ActionEvent>, Initializable {
	
	@FXML
	ImageView backgroundImage;
	@FXML
	Button playButton;
	@FXML
	Button scoreboardButton;
	@FXML
	Button quitButton;
	
	/**
	 * This is an initializing method that is used to populate some of the GUI components of the Main View.
	 * 
	 * @author Spenser Wright (azu874)
	 * 
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Image myImage = new Image("file:images/mainMenuImages/The Road Run Main Menu.png");
		backgroundImage.setImage(myImage);
	}
	
	
	/**
	 * This handle method will quit the user out of the game
	 * 
	 * @author Spenser Wright (azu874)
	 * 
	 */
	@Override //we will use this one for the quit button.
	public void handle(ActionEvent event) {
		//System.out.println("Quit Button Works");
		
		//don't know if I need a try catch here
		try {
			Main.stage.close();
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * This handle method will change the user to the difficulty select screen
	 * 
	 * @author Spenser Wright (azu874) 
	 * 
	 */
	public void playHandle(ActionEvent event) {
		//System.out.println("Play Button Works");
		
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
	 * This handle method will change the user to the scoreboard screen.
	 * 
	 * @author Spenser Wright (azu874) 
	 * 
	 */
	public void scoreboardHandle(ActionEvent event) {
		//System.out.println("Scoreboard Button Works");
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/Scoreboard.fxml"));
			Main.stage.setScene(new Scene(root, 800, 800));
			Main.stage.show();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}