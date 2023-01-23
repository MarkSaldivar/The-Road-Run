package application.model;

//import javafx.scene.image.Image;
//import javafx.geometry.Bounds;
import javafx.scene.layout.AnchorPane;
//import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

/**
 * The bird class is responsible for creating a bird object and it's properties.
 * 
 * @author Spenser Wright (azu874)
 * @author Tyler Unruh (hsg211)
 * @author John Moran (qvv333)
 * @author Mark Saldivar (bpr926)
 *
 */
public class Bird {

   // private Rectangle bird = new Rectangle(425, 33, 183,155);
    private Rectangle bird;
    
    
	private int jumpHeight;
    //private AnchorPane plane;
    CollisionHandler collisionHandler = new CollisionHandler();

   /**
    * This is a constructor method that initializes the bird object.
    * 
    * @author Spenser Wright (azu874)
    * 
    * @param bird
    * @param jumpHeight
    */
    public Bird(Rectangle bird, int jumpHeight) {
        this.bird = bird;
        this.jumpHeight = jumpHeight;
    }

   
    
    /**
     * The jump method calls the moveBirdY which changes the bird's vertical position, this method is called whenever the bird needs to jump, as movement is set to the bird's defined jump height
     * 
     *  @author Spenser Wright (azu874)
     * 
     */
    public void jump(){
       double movement = -jumpHeight;
        moveBirdY(movement);
    }
    
    
    
   /**
    * The isGround method is used to determine the position of the bird. Whether it's on the ground or on a platform.
    * 
    * @author Spenser Wright (azu874)
    * @author Tyler Unruh (hsg211)
    * @author John Moran (qvv333)
    * @author Mark Saldivar (bpr926)
    * 
    * @param obstacles
    * @param plane
    * @return a boolean: True if the bird is on the ground or a platform, False if it's in the air
    */
    //NOTE: I'm trying to combine isGround and isBirdDead to make the non-hitbox rectangle standable?????
    public boolean isGround(ArrayList<Rectangle> obstacles, AnchorPane plane) { //make sure to pass as parameter
    	//double movement = -jumpHeight;
    	//Bounds bounds = plane.getBoundsInLocal();
    	//boolean ground = bird.getLayoutY() >= (bounds.getMaxY() - bird.getY());
    	boolean ret = false;
    	
    	
    	//double birdY = bird.getLayoutY() + bird.getY();
    	
    	//if((bird.getLayoutY() + bird.getY()) >= (plane.getHeight() - bird.getLayoutY())) {
    	//if(((bird.getY() + bird.getLayoutY()) >= (plane.getHeight() - (bird.getY() + bird.getLayoutY()))) || (collisionHandler.collisionDetection(obstacles, bird))) {
    	/*
    	if((bird.getLayoutY() + bird.getY()) >= 480) {
    		ret = true;
    		//return true;
    		//System.out.println("ground");
    	}
    	else if(((bird.getLayoutY() + bird.getY()) >= 80) && (collisionHandler.collisionDetection(obstacles, bird))) {
    		ret = true;
    	}
    	//else if(((bird.getY() + bird.getLayoutY()) >= (rectangle.getHeight() - (bird.getY() + bird.getLayoutY()))){
    	*/
    	
    	if(((bird.getLayoutY() + bird.getY()) >= 80) && (collisionHandler.platformDetect(obstacles, bird))) {
    		ret = true;
    	}
    	else if((bird.getLayoutY() + bird.getY()) >= 582.5) {
    		ret = true;
    		//return true;
    		//System.out.println("ground");
    	}
    	//}
    	else {
    		ret = false;
    		
    	}
    	
    	
    	
    	return ret;
    	
    	
    	
    	/*
    	if(bird.getLayoutY() + bird.getY() >= 200) {
    		movement = 200;
    	}
    	*/
    	//moveBirdY(movement);
    }
    

    /**
     * The moveBirdY method changes the bird's vertical position
     * 
     * @author Spenser Wright (azu874) 
     * 
     * @param positionChange
     */
    public void moveBirdY(double positionChange){
        bird.setY(bird.getY() + positionChange);
    }
    
    /**
     * The isBirdDead() method determines the if the bird is "dead" or collides with an obstacles
     * 
     * @author John Moran (qvv333)
     * @author Mark Saldivar (bpr926)
     * 
     * @param obstacles
     * @param plane
     * @return a boolean that returns True if the bird is "dead" and false if it isn't.
     */
    public boolean isBirdDead(ArrayList<Rectangle> obstacles, AnchorPane plane){
        //double birdY = bird.getLayoutY() + bird.getY();

        if(collisionHandler.collisionDetection(obstacles, bird)){
            return  true;
        }
        else {
        	return false;
        }

        //System.out.println(plane.getHeight());
        //return birdY >= plane.getHeight();
    }
}
