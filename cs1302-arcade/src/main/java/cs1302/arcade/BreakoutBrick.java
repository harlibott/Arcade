package cs1302.arcade;

import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Bounds;
import javafx.scene.shape.Circle;
import javafx.scene.Node;
import javafx.scene.layout.Region;

/**
 * A class that handles and creates the breakout bricks. 
 *
 * @authors Harli Bott & Manasa Chimpiri
 * @since December 11, 2017
 * @version 1.0
 */
public class BreakoutBrick extends Breakout{

    int numBalls = 3;
    int score = 0;
    int level = 0;

    Rectangle [] lineOne = new Rectangle[11];
    Rectangle [] lineTwo = new Rectangle[11];
    Rectangle [] lineThree = new Rectangle[11];
    Rectangle [] lineFour = new Rectangle[11];

    HBox brickMap = new HBox();
    GridPane grid = new GridPane();

    /**
     * Constructor that makes the level 1 default brick. 
     */ 
    BreakoutBrick(){

	this.numBalls = 3;
	this.score = 0;
	this.level = 1;
	
	lineSetup();
    } //breakoutbrick

    /**
     * A method that initializes the array of rectangles
     */
    void lineSetup(){

	for(int i = 0; i < lineOne.length; i++){
	    lineOne[i] = new Rectangle(76,38);
	    lineOne[i].setFill(Color.rgb(169,218,255));
	    lineOne[i].setStroke(Color.BLACK);

	    lineTwo[i] = new Rectangle(76,38);
	    lineTwo[i].setFill(Color.rgb(116,166,136));
	    lineTwo[i].setStroke(Color.BLACK);

	    lineThree[i] = new Rectangle(76,38);
	    lineThree[i].setFill(Color.rgb(253,188,180));
	    lineThree[i].setStroke(Color.BLACK);

	    lineFour[i] = new Rectangle(76,38);
	    lineFour[i].setFill(Color.rgb(191,114,114));
	    lineFour[i].setStroke(Color.BLACK);

	}//for i
      } // line setup

    /**
     * Displays the bricks to GUI
     *
     * @return HBox of bricks
     */
    HBox displayBricks(){

	for(int i = 0; i < 11; i++){
	    grid.setConstraints(lineOne[i], i, 0);
	    grid.getChildren().addAll(lineOne[i]);
	}//for
	
	for(int i = 0; i < 11; i++){
	    grid.setConstraints(lineTwo[i], i, 1);
	    grid.getChildren().addAll(lineTwo[i]);
	}// for
	
	if(level == 2 || level == 3){
	    for(int i = 0; i < 11; i++){
		grid.setConstraints(lineThree[i],i,2);
		grid.getChildren().addAll(lineThree[i]);
	    }//for
	}//if

	if(level == 3){
	    for(int i = 0; i < 11; i++){
		grid.setConstraints(lineFour[i],i,3);
		grid.getChildren().addAll(lineFour[i]);
	    }//for 
	}//if

	brickMap.getChildren().add(grid);
	return brickMap;
    }//displayBricks

    /**
     * a getter method for the game score
     *
     * @return int  score
     */
    int score(){
	return score;
    }// score

    /**
     * a getter method for the number of balls remaining
     *
     * @return int   of balls remaining
     */
    int ballsLeft(){
	return numBalls;
    }// ballsLeft

    /**
     * a getter method for the current level.
     *
     * @return int  level 
     */ 
    int level(){
	return this.level;
    }// level    

    /**
     * a setter method to update the score
     */
    void updateScore(){
	score ++;
    }// update score

    /**
     * a setter method to update the balls
     */
    void updateBalls(){
	numBalls --;
    }// updat balls
   
    /**
     * a getter method that gets a rectangle from the array
     *
     * @return rectangle  from the array
     */
    Rectangle getBrick(int line, int column){
	if(line == 1){
	    return lineOne[column];
	}
	else if(line == 2){
	    return lineTwo[column];
	}
	else if(line == 3){
	    return lineThree[column];
	}
	else if(line == 4){
	    return lineFour[column];
	}
	else{
	    return null;
	}
    } // get brick
}// BreakoutBrick