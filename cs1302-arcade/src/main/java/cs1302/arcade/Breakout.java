package cs1302.arcade;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuBar;
import javafx.stage.Modality;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
/**
 * A GUI interface application that will
 * simulate a simple Breakout game.
 *
 * @authors Harli Bott & Manasa Chimpiri
 * @since December 11, 2017
 * @version 1.0
 */
public class Breakout extends Application{
    
    Scene scene;
    Pane main = new Pane();
    Stage classStage = new Stage();
    HBox pane = new HBox();
    ArrayList<Shape> nodes;
    
    BreakoutBrick brick;
    BreakoutBall ball;
    BreakoutPaddle paddle;
   
    Breakout2 b2 = new Breakout2();
    Breakout3 b3 = new Breakout3();

    @Override
    public void start(Stage stage){
	
	classStage = stage;
	
	// sets background image
	Image bgImg = new Image("breakoutbackground.png", 850, 850, true, true);
	BackgroundImage background = new BackgroundImage(bgImg, null, null, null,null);
	main.setBackground(new Background(background));
	
	brick = new BreakoutBrick();
	ball = new BreakoutBall();
	paddle = new BreakoutPaddle();
	
	/* sets nodes */	
	nodes = new ArrayList<>();
	nodes.add(brick.getBrick(2,5));
	nodes.add(ball.displayBall());
	nodes.add(paddle.getPaddle());
	
	/* displays everything */
	main.getChildren().add(brick.displayBricks());
	main.getChildren().add(ball.displayBall());
	main.getChildren().add(paddle.getPaddle());
	main.getChildren().add(menu());
	main.getChildren().add(text());
	
	level1();
	main.getChildren().add(pane);


	// launches a new scene
	scene = new Scene(main, 847, 480);
	stage.setTitle("Breakout!");
        stage.setScene(scene);
	stage.sizeToScene();
        stage.show();
	
	// game run control
	runGame();	
    }//start	
  
    /**
     * Handles running level 2
     */
    void level1(){

	Button a = new Button("Lvl 2");
	Button b = new Button("LvL 3");

	pane.setPadding(new Insets(440,0,0,10));
	pane.getChildren().addAll(a, new Text("  "),b);
	
	a.setOnMouseClicked(event -> {
		Stage x = new Stage();
		x.initModality(Modality.WINDOW_MODAL);
		b2.start(x);
		classStage.close();
	    });
	
	b.setOnMouseClicked(event ->{
		Stage y = new Stage();
		y.initModality(Modality.WINDOW_MODAL);
		b3.start(y);
		classStage.close();
	    });
   }// level1
    
    /**
     * Handles running level 3
     */
    void level2(){

	Button b = new Button("Lvl 3");

	pane.setPadding(new Insets(440,0,0,10));
	pane.getChildren().add(b);
	
	b.setOnMouseClicked(event -> {
		Stage y = new Stage();
		y.initModality(Modality.WINDOW_MODAL);
		b3.start(y);
		classStage.close();
		});
    }// level 2


    /**
     * Handles the menu bar.
     */
    MenuBar menu(){
	Menu fileMenu = new Menu("_File");
	MenuItem exitItem = new MenuItem("E_xit Arcade App");
	
	exitItem.setOnAction(event -> { 
		Platform.exit();
		System.exit(0);
	    });
	
	MenuItem endBreakout = new MenuItem("E_nd Breakout");
	endBreakout.setOnAction(event -> {
		classStage.close();
	    });
	
	fileMenu.getItems().addAll(exitItem, endBreakout);
	
	//Info > About
	Menu infoMenu = new Menu("_Info");
	MenuItem infoItem = new MenuItem("About");
	
	infoItem.setOnAction(event -> {
		System.out.println("you clicked the ABOUT!");
		imageCreds();
	    }); // info item, img attrributses
	
	infoMenu.getItems().add(infoItem);
	
	MenuBar menuBar = new MenuBar();
	menuBar.getMenus().addAll(fileMenu, infoMenu);
	
	return menuBar;    
    }// menu
    
    /**
     * Creates text to keep up with scoring, levels, and balls remaining
     *
     * @return an HBOX of text
     */
    HBox text(){
	HBox text = new HBox();
	
	Text score = new Text("Score: " + brick.score() + "  ");
	score.setFont(new Font(20));
	score.setFill(Color.WHITE);
	
	Text balls = new Text("Balls Left: " + brick.ballsLeft() + "  ");
	balls.setFont(new Font(20));
	balls.setFill(Color.WHITE);
	
	Text level = new Text("Level: " + brick.level());
	level.setFont(new Font(20));
	level.setFill(Color.WHITE);
	// aligns tezt
	text.setAlignment(Pos.BOTTOM_LEFT);
	text.setPadding(new Insets(440,0,0,540));
	text.getChildren().addAll(score, balls, level);
	
	return text;
	
    }//text    
    /**
     * Handles the image attributes pop up window. 
     */
    void imageCreds() {
	
	Stage infoDialog = new Stage();
	
	Text imageInfo = new Text("Image Attributions\n");
	imageInfo.setFont(new Font(20));
	imageInfo.setUnderline(true);
	// sets text
	Text bg1 = new Text("Background Image https://2.bp.blogspot.com/-RGHHme2BlY0/WIOKrLUFA0I/AAAAAAAAAYI/KOC-EMQkI6MB5eOwNTY7X0CCuMlV7HBuACLcB/s1600/800px-1400844463-Cover-Website-Breakout.jpeg");
	bg1.setFont(new Font(15));
	
	VBox v2 = new VBox();
	v2.getChildren().addAll(imageInfo, bg1);
	//launches new scene
	Scene sceneInfo = new Scene(v2, 600, 200);
	infoDialog.initModality(Modality.WINDOW_MODAL);
	infoDialog.setTitle("Image Attributions");
	infoDialog.setScene(sceneInfo);
	infoDialog.sizeToScene();
	infoDialog.show();

    }// about, img attributes

    /**
     * a Method that handles the game play.
     */
    void runGame(){
	scene.setOnKeyPressed(e -> {
		switch (e.getCode()) {
		case LEFT:
		    paddle.getPaddle().setLayoutX(paddle.getPaddle().getLayoutX() - 5);
		    break;
		    
		case RIGHT:
		    paddle.getPaddle().setLayoutX(paddle.getPaddle().getLayoutX() + 5);
		    break;
		    
		case SPACE:
		    boolean collision = false;
		    
		    for(Shape other : nodes){
			while(collision != true){
			    if(ball.displayBall().intersects(other.getBoundsInParent())){
				//	main.getChildren().remove(other); // removes brick when collided
				brick.updateScore(); // updates the score
				collision = true;
			    }else{
				brick.updateBalls(); // removes balls if ball is not caught by paddle
			    }
			    
			    if(collision == true){ 
				break;
			    }
			    else{
				ball.displayBall().setCenterY(ball.displayBall().getCenterY() - .01);
			    }		
			}
		    }
		    break;
		}
	    });
    }// run game

    /**
     * Launches the application. 
     *
     * @param String[]  arguments
     */
    public static void main(String[] args) {
	try {
	    Application.launch(args);
	} catch (UnsupportedOperationException e) {
	    System.out.println(e);
	    System.err.println("If this is a DISPLAY problem, then your X server connection");
	    System.err.println("has likely timed out. This can generally be fixed by logging");
	    System.err.println("out and logging back in.");
	    System.exit(1);
	} // try
    } // main
}//breakout
