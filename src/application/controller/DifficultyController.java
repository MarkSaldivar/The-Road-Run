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
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The Difficulty Controller is a class used to listen for events in the Difficulty.fxml document
 * 
 * @author John Moran (qvv333)
 * @author Tyler Unruh (hsg211)
 *
 */
public class DifficultyController implements EventHandler<ActionEvent>, Initializable {
	    @FXML
	    public Button EasyButton;
	    @FXML
	    public Button MediumButton;
	    @FXML
	    public Button HardButton;
	    @FXML
	    ImageView backgroundImage;
	    
	    public static Boolean EasyButtonPressed = false;
	    public static Boolean MediumButtonPressed = false;
	    public static Boolean HardButtonPressed = false;
	    public static Boolean resetGame = false;
	    


	    
	    
	    /**
		 * This is an initializing method that is used to populate some of the GUI components of the Difficulty View.
		 * Note: Doesn't work lmao 11-28-22 - SW
		 * 
		 * @author Spenser Wright (azu874)
		 */
	    @Override
		public void initialize(URL location, ResourceBundle resources) {
	    	Image difficultySelectImg = new Image("file:images/difficultySelectImages/difficulty selection screen.png");
	    	backgroundImage.setImage(difficultySelectImg);
	    }
	    
	    
	/**
	 * This handle method is responsible for setting the game difficulty to easy
	 * 
	 * @author John Moran (qvv333)
	 * @author Tyler Unruh (hsg211)
	 */
	@Override
	public void handle(ActionEvent event) {
		resetGame = true;
		EasyButtonPressed = true;
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/Game.fxml"));
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
	 * This handle method is responsible for setting the game difficulty to medium
	 * 
	 * @author John Moran (qvv333)
	 * @author Tyler Unruh (hsg211)
	 */
	public void handle2(ActionEvent event) {
		resetGame = true;
		MediumButtonPressed = true;
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/Game.fxml"));
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
	 * This handle method is responsible for setting the game difficulty to hard
	 * 
	 * @author John Moran (qvv333)
	 * @author Tyler Unruh (hsg211)
	 */
	public void handle3(ActionEvent event) {
		resetGame = true;
		HardButtonPressed = true;
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/Game.fxml"));
			//Parent root = FXMLLoader.load(getClass().getResource("../view/LevelSelect.fxml"));
			//Main.stage.requestFocus();
			
			Main.stage.setScene(new Scene(root, 800, 800));
			Main.stage.getScene().getRoot().requestFocus();
			Main.stage.show();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
