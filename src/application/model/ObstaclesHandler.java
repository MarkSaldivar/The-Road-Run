package application.model;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import application.controller.DifficultyController;

/**
 * The ObstaclesHandler class represents the obstacle objects and their function.
 * 
 * @author Spenser Wright (azu874)
 * @author Tyler Unruh (hsg211)
 * @author John Moran (qvv333)
 * @author Mark Saldivar (bpr926)
 *
 */
public class ObstaclesHandler {

    private AnchorPane plane;
    //private double planeHeight;
    private double planeWidth;
    private int difficultyModifier = 0;
    Random random = new Random();

    /**
     * This is a constructor method to initialize obstacle objects
     * 
     * @author Spenser Wright (azu874)
     * 
     * @param plane
     * @param planeWidth
     */
    public ObstaclesHandler(AnchorPane plane, double planeWidth) {
        this.plane = plane;
        this.planeWidth = planeWidth;
    }
    /*
    public ObstaclesHandler(AnchorPane plane, double planeHeight, double planeWidth) {
        this.plane = plane;
        this.planeHeight = planeHeight;
        this.planeWidth = planeWidth;
    }
	*/
    /**
     * The createObstacles method is used to create and load obstacles into an array list to be called in game.
     * 
     * @author Spenser Wright (azu874)
     * @author Tyler Unruh (hsg211)
     * @author John Moran (qvv333)
     * @author Mark Saldivar (bpr926)
     * 
     * @return an array list of Rectangle objects
     */
    public ArrayList<Rectangle> createObstacles(){
    	
    	
      //  int width = 25;
        double xPos = planeWidth;
        double yPos = 550;
        int rn = random.nextInt(2 - 1 + 1) + 1;
       // double space = 200;
       // double recTopHeight = random.nextInt((int)(planeHeight - space - 100)) + 50;
       // double recBottomHeight = planeHeight - space - recTopHeight;
		
        //                                     x      y   width   height
       // Rectangle rectangleTop = new Rectangle(xPos,0,width,recTopHeight);
       // Rectangle rectangleBottom = new Rectangle(xPos, recTopHeight + space, width, recBottomHeight);
    	
        Rectangle personHitBox = new Rectangle(xPos-5, yPos, 2, 100);
    	personHitBox.setFill(Color.TRANSPARENT);
    	
    	
        Rectangle person = new Rectangle(xPos, yPos, 150, 165);
        Image personObstacle = new Image("file:images/levelImages/obstacles/obstaclePerson.png");
    	person.setFill(new ImagePattern(personObstacle));
    	
    	/*
    	Rectangle groupPeople = new Rectangle(xPos, yPos, 340, 200);
    	Image groupOfPeopleImg = new Image("file:images/levelImage/obstacles/groupOfPeople.png");
    	groupPeople.setFill(new ImagePattern(groupOfPeopleImg));
        */
        
        Rectangle carHitBox = new Rectangle(xPos-5, yPos, 2, 100);
        carHitBox.setFill(Color.TRANSPARENT);
    	
        Rectangle car = new Rectangle(xPos, yPos, 340, 185);
        Image carObstacle = new Image("file:images/levelImages/obstacles/obstacleCar.png");
        car.setFill(new ImagePattern(carObstacle));
    	
       // System.out.println(rn);
    	
    	
    	if(rn == 1) {
    		plane.getChildren().add(personHitBox);
    		plane.getChildren().add(person);
    		//System.out.println("person");
    	}
    	else {
    		plane.getChildren().add(carHitBox);
    		plane.getChildren().add(car);
    		//System.out.println("car");
    	}
        //plane.getChildren().addAll(person,car);
        return new ArrayList<>(Arrays.asList(personHitBox, person, carHitBox, car));
    	//return new ArrayList<>(Arrays.asList(person,car));
    }

    /**
     * The moveObstacles method is responsible for moving the obstacles at a set speed and removing the obstacles once they're off screen
     * 
     * @author Spenser Wright (azu874)
     * @author Tyler Unruh (hsg211)
     * @author John Moran (qvv333)
     * @author Mark Saldivar (bpr926) 
     * 
     * @param obstacles
     */
    public void moveObstacles(ArrayList<Rectangle> obstacles){

        ArrayList<Rectangle> outOfScreen = new ArrayList<>();

        for (Rectangle rectangle: obstacles) {
        	if(DifficultyController.EasyButtonPressed == true) {
        		//System.out.println("Easy button pressed");
        		//moveRectangle(rectangle, - 2);
        		difficultyModifier = 2;
        	}
        	else if(DifficultyController.MediumButtonPressed == true) {
        		//System.out.println("medium button pressed");
        		 //moveRectangle(rectangle, - 6);
        		difficultyModifier = 6;
        	}
        	else if(DifficultyController.HardButtonPressed == true) {
        		//System.out.println("hard button pressed");
        		//moveRectangle(rectangle, - 6);
        		difficultyModifier = 6;
        	}
        	else {
        		difficultyModifier = 6;
        	}
        	
        	moveRectangle(rectangle, - difficultyModifier);

            if(rectangle.getX() <= -rectangle.getWidth()){
                outOfScreen.add(rectangle);
            }
        }
        obstacles.removeAll(outOfScreen);
        plane.getChildren().removeAll(outOfScreen);
    }

    /**
     * the moveRectanlge() method is used to move the rectangle objects / obstacles along the x axis of the screen at a rate set by the moveObstacles() method
     * 
     * @author Spenser Wright (azu874)
     * @author Tyler Unruh (hsg211)
     * @author John Moran (qvv333)
     * @author Mark Saldivar (bpr926) 
     * 
     * @param rectangle
     * @param amount
     */
    private void moveRectangle(Rectangle rectangle, double amount){
        rectangle.setX(rectangle.getX() + amount);
    }
}
