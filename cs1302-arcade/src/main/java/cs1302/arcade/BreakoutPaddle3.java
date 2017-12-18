package cs1302.arcade; 

import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * A class that represents a breakout paddle
 *
 * @authors Harli Bott & Manasa Chimpiri
 * @since Dec 11, 2017
 * @version 1.0
 */
public class BreakoutPaddle3 extends BreakoutBall3{
    
    Rectangle paddle = new Rectangle(100, 10);
   
    /**
     * A constructor that sets up a paddle
     */ 
    BreakoutPaddle3(){
	paddle.setFill(Color.WHITE);
	paddle.setStroke(Color.BLACK);
	paddle.setLayoutX(374);
	paddle.setLayoutY(380);
    
    }
    //constructor

    /**
     * a getter method that sends back a paddle
     *
     * @return rectangle
     */
    Rectangle getPaddle(){
	return this.paddle;
    }

}// breakout paddle