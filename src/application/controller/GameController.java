package application.controller;

import javafx.animation.AnimationTimer;
//import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
//import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import application.Main;
import application.model.Bird;
import application.model.ObstaclesHandler;
//import application.model.Scoreboard;


/**
 * The GameController class is a class responsible for controlling the game functions, and tying game engine functions together
 * 
 * 
 * @author Spenser Wright (azu874)
 * @author Tyler Unruh (hsg211)
 * @author John Moran (qvv333)
 * @author Mark Saldivar (bpr926)
 *
 */
public class GameController implements Initializable {

    AnimationTimer gameLoop;

    @FXML
    private AnchorPane plane;

    @FXML
    private Rectangle bird;
   // @FXML
  //  private Rectangle road;
    
    @FXML
    private ImageView backgroundImage;

    @FXML
    private Text score;
    
   

    private double accelerationTime = 0;
    private int gameTime = 0;
    public static int scoreCounter = 0;
    private boolean isJumping = false;
    private Boolean resetTheGame = DifficultyController.resetGame;
    //private boolean resetDifficulty = true;
    
    
    Random rand = new Random();
    private int difficultyModifier = 0;

    private Bird birdComponent;
    private ObstaclesHandler obstaclesHandler;

    ArrayList<Rectangle> obstacles = new ArrayList<>();

    //Image roadImg = new Image("file:images/levelImages/backgrounds/roadTexture.png");
	
    /**
     * This is an initializing method that is used to populate some of the GUI components in the game. This method also sets some of the data for the game engine.
     * 
     * @author Spenser Wright (azu874)
     * @author Tyler Unruh (hsg211)
     * @author Mark Saldivar (bpr926)
     */
    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {

    	//Image roadRunnerImg = new Image ("file:images/levelImages/characters/characterRoadRunner.png");
    	//Image roadImg = new Image ("file:images/levelImages/backgrounds/roadTexture.png");
    	Image backgroundImg = new Image("file:images/levelImages/backgrounds/sanAntonioBackground.png");
    	backgroundImage.setImage(backgroundImg);
    	//road.setFill(new ImagePattern(roadImg));
    	//birdComponent.setFill(new ImagePattern(roadRunnerImg));
    	//Rectangle testRoad = new Rectangle(400, 400, 100, 100);
    	//Image roadImg = new Image ("file:images/levelImages/backgrounds/grassTexture.png");
    	//testRoad.setFill(new ImagePattern(roadImg));
    	//road.setFill(Color.BLACK);
    	
    	Image birdImg = new Image("file:images/levelImages/characters/characterRoadRunner.png");
    	bird.setFill(new ImagePattern(birdImg));
    	
    	
        int jumpHeight = 250;
        birdComponent = new Bird(bird, jumpHeight);
        
        //double planeHeight = 800;
        double planeWidth = 800;
       // obstaclesHandler = new ObstaclesHandler(plane, planeHeight, planeWidth);
        obstaclesHandler = new ObstaclesHandler(plane, planeWidth);

        gameLoop = new AnimationTimer() {
            /**
             * This handle method is used to call the update() method at the beginning of the game
             * 
             * @author Spenser Wright (azu874)
             */
        	@Override
            public void handle(long l) {
                update();
            }
        };

        load();

        gameLoop.start();
    }
   
    /**
     * the pressed method is used to allow the character in game to jump.
     * 
     * @author Spenser Wright (azu874)
     * @author Tyler Unruh (hsg211)
     * 
     * @param event
     */
    @FXML
    private void pressed(KeyEvent event) {
       
    	//if(event.getCode() == KeyCode.SPACE) {
    	if((event.getCode() == KeyCode.SPACE) && (!isJumping)){
            //birdComponent.fly(plane); //maybe get rid of plane
        	birdComponent.jump();
            accelerationTime = 0;
            //isJumping = true;
        }
    	else {
    		accelerationTime = 0;
    	}
    }
    
   

    /**
     * The update method constantly updates every game frame to check the status of the bird character in relation to it's environment
     * 
     * @author Spenser Wright (azu874)
     * @author Tyler Unruh (hsg211)
     * @author John Moran (qvv333)
     * @author Mark Saldivar (bpr926)
     */
    //Called every game frame
    private void update() {
        gameTime++;
        accelerationTime++;
        double yDelta = 0;
        //birdComponent.moveBirdY(yDelta * accelerationTime);

        if(resetTheGame) {
        	//System.out.println("restart = true");
        	resetGame();
        	resetTheGame = false;
        }
        /*
        else {
        	System.out.println("restart = false");
        }
        */
        
        
        
        if(pointChecker(obstacles, bird)){
            scoreCounter++;
            score.setText(String.valueOf(scoreCounter));
        }

        obstaclesHandler.moveObstacles(obstacles);
        
        /*
        if(resetDifficulty == true) {
        	if(gameTime % 500 == 0) {
        		obstacles.addAll(obstaclesHandler.createObstacles());
        	}
        	resetDifficulty = false;
        }
        */
        
        if(DifficultyController.EasyButtonPressed == true) {
        	difficultyModifier = rand.nextInt(500 - 100) + 100;
        }
        else if(DifficultyController.MediumButtonPressed == true) {
        	difficultyModifier = rand.nextInt(500 - 300) + 300;
        	//difficultyModifier = 200;
        }
        else if(DifficultyController.HardButtonPressed == true) {
        	difficultyModifier = rand.nextInt(200 - 100) + 100;
        }
        else {
        	difficultyModifier = 500;
        }
        
        if(gameTime % difficultyModifier == 0) {
        	obstacles.addAll(obstaclesHandler.createObstacles());
        }
        
        
        /*
        if(DifficultyController.EasyButtonPressed == true) {
        	difficultyModifier = rand.nextInt(500 - 200) + 200;
        	if(gameTime % difficultyModifier == 0){
        		obstacles.addAll(obstaclesHandler.createObstacles());
        	}
        	//System.out.println("Difficulty Mod" + difficultyModifier);
        }
        else if(DifficultyController.MediumButtonPressed == true) {
        //if(DifficultyController.MediumButtonPressed == true) {
        	difficultyModifier = rand.nextInt(400 - 200) + 200;
            if(gameTime % difficultyModifier == 0){
                obstacles.addAll(obstaclesHandler.createObstacles());  
            }
        }
        else if(DifficultyController.HardButtonPressed == true) {
        //if(DifficultyController.HardButtonPressed == true) {
        	difficultyModifier = rand.nextInt(400 - 100) + 100;
            if(gameTime % difficultyModifier == 0){
                obstacles.addAll(obstaclesHandler.createObstacles());
               
            }
        }
        else {
        	if(gameTime % 500 == 0) {
        		obstacles.addAll(obstaclesHandler.createObstacles());
        	}
        	//resetDifficulty = false;
        }
        */
       /*
        else {
        	if(gameTime % 500 == 0) {
        		obstacles.addAll(obstaclesHandler.createObstacles());
        	}
        }
        */
/*
        if(birdComponent.isGround(plane)) {
        	System.out.println("ground");
        	yDelta = 0;
        }
  */
        if(birdComponent.isGround(obstacles, plane)) {
        	yDelta = 0;
        	birdComponent.moveBirdY(yDelta * accelerationTime);        	
        	//System.out.println("is ground");
        	isJumping = false;
        }
        else {
        	yDelta = 0.05;
        	birdComponent.moveBirdY(yDelta * accelerationTime);  
        	//System.out.println("is not ground");
        	isJumping = true;
        }
        
        
        
        if(birdComponent.isBirdDead(obstacles, plane)){
            /*
        	String playerScore = score.getText();
            
            System.out.println("Score: " + playerScore);
        	
        	resetGame();
        	*/
        	gameLoop.stop();
        	
        	try {
    			Parent root = FXMLLoader.load(getClass().getResource("../view/EndScreen.fxml"));
    			//Parent root = FXMLLoader.load(getClass().getResource("../view/LevelSelect.fxml"));
    			//Main.stage.getScene().getRoot().requestFocus();
    			Main.stage.setScene(new Scene(root, 800, 800));
    			Main.stage.show();
    			
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    		
        }
    }

    /**
     * The load method creates obstacles for the game
     * 
     * @author Spenser Wright (azu874)
     */
    //Everything called once, at the game start
    private void load(){
    	obstacles.addAll(obstaclesHandler.createObstacles());
    	
       // scoreCounter = 0;
        
    }

    /**
     * The reset method will reset the character position, reset score, and reset / clear obstacles off of the screen
     * 
     * @author Spenser Wright (azu874)
     * @author Tyler Unruh (hsg211)
     * @author John Moran (qvv333)
     * @author Mark Saldivar (bpr926)
     */
    private void resetGame(){
        bird.setY(550);
        plane.getChildren().removeAll(obstacles);
        obstacles.clear();
        gameTime = 0;
        accelerationTime = 0;
        //Scoreboard.newScore("data/data.txt", scoreCounter);
        //File scoreboard = new File("data/scoreboard.txt");
        //BufferedWriter writer = new BufferedWriter(new FileWriter("data/scoreboard.txt"));
        
        //lol the game breaks without these two jump commands
        birdComponent.jump();
        birdComponent.jump();
       // birdComponent.jump();
        scoreCounter = 0;
        score.setText(String.valueOf(scoreCounter));
        //resetDifficulty = true;
    }


    /*
    private void endGame() {
        plane.getChildren().removeAll(obstacles);
        obstacles.clear();
        gameTime = 0;
        accelerationTime = 0;
    }
    */
    
    

    /**
     * The pointChecker method is used to determine the character position with obstacle position to determine if the character gets a point or not.
     * 
     * @author Spenser Wright (azu874)
     * 
     * @param obstacles
     * @param bird
     * @return boolean that represents if the player gets a point or not.
     */
    private boolean pointChecker(ArrayList<Rectangle> obstacles, Rectangle bird){
        for (Rectangle obstacle: obstacles) {
            int birdPositionX = (int) (bird.getLayoutX() + bird.getX());
            if(((int)(obstacle.getLayoutX() + obstacle.getX()) == birdPositionX)){
                return true;
            }
        }
        return false;
    }
}

/*
package application.controller;

import javafx.animation.AnimationTimer;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;



public class GameController implements Initializable {
	
	AnimationTimer gameLoop;
	
	@FXML
	AnchorPane pane;
	@FXML
	Rectangle raodRunner;
//	@FXML
	//Text score;
	
	
	
	
	
}
*/
	/*
	private BooleanProperty upArrow = new SimpleBooleanProperty();
	private BooleanProperty rightArrow = new SimpleBooleanProperty();
	private BooleanProperty leftArrow = new SimpleBooleanProperty();
	
	private BooleanBinding keyPressed = upArrow.or(rightArrow).or(leftArrow);
	
	private int movementVariable = 2;
	
	@FXML
	Button resetButton;
	
	@FXML
	Rectangle roadSurface;
	//Image road = new Image("./images/Level Images/Road Textrue.png");
	Image road = new Image("file:images/levelImages/roadTextrue.png");
	//Image road = new Image("/images/Level Images/Road Textrue.png");
	
	@FXML
	Rectangle roadRunner;
	//Image character = new Image("/images/Level Images/Character Road Runner.png");
	Image character = new Image("file:images/levelImages/characterRoadRunner.png");
	
	@FXML
	Rectangle person;
	//Image personObstacle = new Image("/images/Level Images/Obstacle Person.png");
	Image personObstacle = new Image("file:images/levelImages/obstaclePerson.png");
	
	@FXML
	Rectangle car;
	//Image carObstacle = new Image("/images/Level Images/Obstacle Car.png");
	Image carObstacle = new Image("file:images/levelImages/obstacleCar.png");
	
	@FXML
	AnchorPane scene;
	//Image background = new Image ("file:images/levelImages/sanAntonioBackground.png");
	
	@FXML
	ImageView backgroundImage;
	Image back = new Image ("file:images/levelImages/sanAntonioBackground.png");
	
	
	
	@FXML
	void restartLevel(ActionEvent event) {
		roadRunner.setLayoutX(14);
		roadRunner.setLayoutY(533);
	}
	
	AnimationTimer timer = new AnimationTimer() {
		@Override
		public void handle(long timestamp) {
			if(upArrow.get()) {
				roadRunner.setLayoutY(roadRunner.getLayoutY() - movementVariable);
			}
			//else { //This will always pull the roadRunner down??? like gravity
			//	roadRunner.setLayoutY(roadRunner.getLayoutY() + movementVariable);
			//}
			if(leftArrow.get()) {
				roadRunner.setLayoutY(roadRunner.getLayoutX() - movementVariable);
			}
			if(rightArrow.get()) {
				roadRunner.setLayoutY(roadRunner.getLayoutX() + movementVariable);
			}
			
		}
	};
	
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) { //so in here call to a model class that loads a level... kinda like in pacman setup??
		
		//here is where it fills in based on the type of rectangle (person = person picture) and whatnot.
		//but then have a class that loads in csv file with those 1 and 0s and stuff, and populate a level???
		roadSurface.setFill(new ImagePattern(road));
		roadRunner.setFill(new ImagePattern(character));
		person.setFill(new ImagePattern(personObstacle));
		car.setFill(new ImagePattern(carObstacle));
		backgroundImage.setImage(back);
		
		
	}
	
	
	
}
*/