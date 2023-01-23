package application.model;

import javafx.scene.shape.Rectangle;
import java.util.ArrayList;

/**
 * The CollisionHandler class is used to determine if the bird has collided / intersected with an obstacle or platform
 * 
 * @author John Moran (qvv333)
 * @author Mark Saldivar (bpr926)
 *
 */
public class CollisionHandler {

	/**
	 * The collisionDetection method is used to determine if the bird object has intersected with the bounds of an obstacle... more specifically the hitbox
	 * which is determined by the width of the rectangle obstacle.
	 * 
	 * @author John Moran (qvv333)
	 * @author Mark Saldivar (bpr926)
	 * 
	 * @param obstacles
	 * @param bird
	 * @return a boolean: True if the bird has intersected the bounds of an obstacle, false if not.
	 */
    public boolean collisionDetection(ArrayList<Rectangle> obstacles , Rectangle bird){
        for (Rectangle rectangle: obstacles) {
        	
        	if(rectangle.getWidth() == 2) {
        		if(rectangle.getBoundsInParent().intersects(bird.getBoundsInParent())){
        			//System.out.println("hitbox");
        			return true;
        		}
        	}
        	else {
        		if(rectangle.getBoundsInParent().intersects(bird.getBoundsInParent())){
        			//System.out.println("not hitbox");
        			return false;
        		}
        	}       		
        }
        
        return  false;
    }
    
    
    /**
     * The platformDetect method is similar, but rather than determining if the bird has collided with a hitbox, it will determine if the bird is "standing" on an obstacle as a platform
     * 
     * @author Mark Saldivar (bpr926)
     * 
     * @param obstacles
     * @param bird
     * 
     * @return a boolean: True if the bird is standing on a non-hitbox rectangle object, false otherwise.
     */
    public boolean platformDetect(ArrayList<Rectangle> obstacles , Rectangle bird){
        boolean ret = false;
    	
    	for (Rectangle rectangle: obstacles) {
        	
        	if(rectangle.getWidth() == 2) {
        		if(rectangle.getBoundsInParent().intersects(bird.getBoundsInParent())){
        			//System.out.println("hitbox");
        			ret = false;
        		}
        	}
        	else {
        		if(rectangle.getBoundsInParent().intersects(bird.getBoundsInParent())){
        			//System.out.println("not hitbox");
        			ret = true;
        		}
        	}       		
        }
        
        return ret;
    }
    

}