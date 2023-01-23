package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

/**
 * The Main class is responsible for running the application
 * Team: I Don't Know, I'm Not Perfect At Making Team Names (I.D.K.I.N.P.A.M.T.N)
 * Project: The Road Run
 * 
 * @author Spenser Wright (azu874)
 * @author Tyler Unruh (hsg211)
 * @author John Moran (qvv333)
 * @author Mark Saldivar (bpr926)
 *
 */
public class Main extends Application {
	
	public static Stage stage;
	
	/**
	 * The Start method starts the program
	 * 
	 * @param the primary stage
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			// give access to the other controllers to this primary stage!
			stage = primaryStage;
			
			// Connect to the FXML (contains our layout) and load it in
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation( Main.class.getResource("view/Main.fxml") );
			AnchorPane layout = (AnchorPane) loader.load();
			
			// Put the layout onto the scene
			Scene scene = new Scene( layout );
			//scene.getRoot().requestFocus(); //might not need??
			
			// Set the scene on the stage
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * The main method
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}