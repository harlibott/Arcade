package cs1302.arcade;

import javafx.scene.shape.Circle;
import javafx.scene.layout.Pane;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.Group;
import javafx.scene.Node;
import java.util.Random;

/**
 * A class that represents and handles a Breakout Ball
 *
 * @authors Harli Bott & Manasa Chimpiri
 * @since Dec 11, 2017
 * @version 1.0
 */
public class BreakoutBall3 extends BreakoutBrick3{

    Circle ball = new Circle();
    Group coord = new Group();
    double x;
    double y;

    /**
     * a constructor that sets up a circle representing a ball
     */
    BreakoutBall3(){

	ball.setFill(Color.rgb(255,237,230));
	ball.setStroke(Color.BLACK);
	ball.setCenterX(424.0f);
	ball.setCenterY(370.0f);
	ball.setRadius(10.0f);

    }// breakoutball

    /**
     * a getter method that represents X
     *
     * @return double  x
     */
    double getTranslateX(){
	return this.ball.getTranslateX();
    }// getTranslateX

    /**
     * a getter method that represents Y
     *
     * @return double  y
     */
    double getTranslateY(){
	return this.ball.getTranslateY();
    }// get TranslateY

    /**
     * a setter method that sets X
     */
    void setTranslateX(double x){
	this.ball.setTranslateX(x);
    }// setTranslateX

    /**
     * a setter method that sets Y
     */
    void setTranslateY(double y){
	this.ball.setTranslateY(y);
    }//setTranslate Y

    /**
     * a getter method that returns a circle representing ball
     *
     * @return a circle
     */
    Circle displayBall(){
	return this.ball;
    }// displayBall
}// BreakoutBall