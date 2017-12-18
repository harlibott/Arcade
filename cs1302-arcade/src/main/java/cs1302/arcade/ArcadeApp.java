package cs1302.arcade;

/**
 * Uses GUI implementation to create an Arcade Application with
 * two game options. Human user can choose between Breakout and
 * Othello/Reversi.
 *
 * @authors Harli Bott and Manasa Chimpiri
 * @version 1.0
 * @since 12/1/2017
 */

import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Group;
import javafx.scene.effect.InnerShadow;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class ArcadeApp extends Application {
    
    Reversi r1 = new Reversi();
    Breakout b1 = new Breakout();
    
    /**
    * Begins a game of Reversi by opening the Reversi 
    * Application in a new stage.   
    */
    void reversi(){
        Stage ReversiStage = new Stage();
        ReversiStage.initModality(Modality.WINDOW_MODAL);
        r1.start(ReversiStage);
    }// reversi

    /**
    * Begins a game of Breakout by opening the Breakout 
    * Application in a new stage.   
    */
    void breakout(){
	Stage BreakoutStage = new Stage();
	BreakoutStage.initModality(Modality.WINDOW_MODAL);
	b1.start(BreakoutStage);
    }// breakout
    
    /**
    * Creates a window with Image Attributions listed.  
    */
    void imageCreds() {
    	
	Stage infoDialog = new Stage();
	
	Text imageInfo = new Text("Image Attributions\n");
	imageInfo.setFont(new Font(20));
	imageInfo.setUnderline(true);
    	
      Text bg1 = new Text("Background Image: https://www.videoblocks.com/video/retro-80s\n-vhs-tape-video-game-intro-landscape-vector-arcade-wireframe\n-terrain-4k-hf0yxps4ipdkrsug/\n");
    	bg1.setFont(new Font(15));
    	
	Text b1 = new Text("Breakout Image: Harli Bott");
    	b1.setFont(new Font(15));
    	
      Text or1 = new Text("Othello Image: http://www.ultraboardgames.com/othello/game-rules.php");
    	or1.setFont(new Font(15));
    	
      VBox v2 = new VBox();
    	v2.getChildren().addAll(imageInfo,bg1, b1, or1);
    	
      Scene sceneInfo = new Scene(v2, 600, 200);
    	infoDialog.initModality(Modality.WINDOW_MODAL);
    	infoDialog.setTitle("Image Attributions");
    	infoDialog.setScene(sceneInfo);
    	infoDialog.sizeToScene();
    	infoDialog.show();
    }//imageCreds

    /**
    * Creates a window with authors' information.  
    */
    void authors(){

	Stage authorsDialog = new Stage();

	Text author = new Text("\nAuthors\n");
	author.setFont(new Font(20));
	author.setUnderline(true);

	Text harli = new Text("Harli Bott \nEmail: hjb15032@uga.edu \nStudent Number: 811840080 \n");
	harli.setFont(new Font(15));

	Text manasa = new Text("Manasa Chimpiri \nEmail: mc48186@uga.edu \nStudent Number: 811052989 \n");
	manasa.setFont(new Font(15));

	Text gameHeading = new Text("Breakout & Reversi");
	gameHeading.setFont(new Font(20));
	gameHeading.setUnderline(true);

	Text game = new Text("Since: Dec, 8 2017 \nVersion: 1.0 \n");
	game.setFont(new Font(15));

	VBox vbox = new VBox();
	vbox.getChildren().addAll(author, harli, manasa, gameHeading, game);

	Scene sceneAuthors = new Scene(vbox, 500, 350);

	authorsDialog.initModality(Modality.WINDOW_MODAL);
	authorsDialog.setTitle("mec for prez");
	authorsDialog.setScene(sceneAuthors);
	authorsDialog.sizeToScene();
	authorsDialog.show();

    }// authors

    @Override
    public void start(Stage stage) {

        VBox root = new VBox();
        
        //Intro message
        Text msg = new Text("Welcome to the cs1302-arcade! by: mec for prez (Harli Bott & Manasa Chimpiri)");
        //msg.setTextOrigin(VPos.TOP);
	msg.setFont(Font.font(null, FontWeight.BOLD,24));
        msg.setFill(Color.RED);
	
        //root.getChildren().add(msg);
        
        //#####################################################
        //File > Exit
      	Menu fileMenu = new Menu("_File");
      	MenuItem exitItem = new MenuItem("E_xit");

      	exitItem.setOnAction(event -> { 
      			Platform.exit();
  				System.exit(0);
      		});

      	fileMenu.getItems().add(exitItem);

      	//Info > About
      	Menu infoMenu = new Menu("_Info");
      	MenuItem infoItem = new MenuItem("About");

      	infoItem.setOnAction(event -> {
      		System.out.println("you clicked the ABOUT!");
          imageCreds();
	    }); // info item, img attrributses

	// Info > Authors
	MenuItem authorItem = new MenuItem("Authors");
	authorItem.setOnAction(event -> {
		System.out.println("You clicked AUTHORS");
		authors();
	    });// authoritem

	infoMenu.getItems().addAll(authorItem, infoItem);

      	//MenuBar
      	MenuBar menuBar = new MenuBar();
      	menuBar.getMenus().addAll(fileMenu, infoMenu);
        
        root.getChildren().addAll(menuBar,msg);
       
        //#####################################################
        Image bgImg = new Image("background.png", 850, 850, true, true); // https://www.videoblocks.com/video/retro-80s-vhs-tape-video-game-intro-landscape-vector-arcade-wireframe-terrain-4k-hf0yxps4ipdkrsug/
        BackgroundImage background = new BackgroundImage(bgImg, null, null, null,null);
        ImageView breakout = new ImageView("breakout2.png"); // http://playsterr.com/wp-content/uploads/2016/01/breakout-voyager_img1.png
        ImageView reversi = new ImageView("reversi.png"); // http://www.ultraboardgames.com/othello/game-rules.php

        HBox introPane = new HBox();
        HBox imgPane = new HBox();
        HBox buttonPane = new HBox();
        
        introPane.setPadding(new Insets(50, 0, 0, 150));
        Text introText = new Text("Welcome to the Arcade!");
        introText.setFill(Color.WHITE);
        introText.setFont(Font.font("Verdana", FontWeight.BOLD, 40));
        introText.setStroke(Color.MIDNIGHTBLUE);
        introText.setTextAlignment(TextAlignment.CENTER);
        introPane.getChildren().add(introText);

        Button reversiB = new Button("Play!");
        Button breakoutB = new Button("Play!");

        reversiB.setOnAction(event -> reversi());
        breakoutB.setOnAction(event -> breakout());

        buttonPane.setPadding(new Insets(0, 0, 0, 200));
        buttonPane.setSpacing(350);

        imgPane.setPadding(new Insets(100,50,25,50));
        imgPane.setSpacing(65);

        breakout.setFitWidth(350);
        breakout.setFitHeight(350);
        breakout.setPreserveRatio(true);

        reversi.setFitWidth(350);
        reversi.setFitHeight(350);
        reversi.setPreserveRatio(true);
        
        buttonPane.getChildren().addAll(breakoutB, reversiB);
        imgPane.getChildren().addAll(breakout, reversi);

        root.setBackground(new Background(background));
        root.getChildren().addAll(introPane, imgPane, buttonPane);

        Scene scene = new Scene(root, 850, 550);
	      stage.setTitle("Arcade!");
        stage.setScene(scene);
	      stage.sizeToScene();
        stage.show();
        //##########################################################
        //Intro Message ANIMATION
        double sceneWidth = scene.getWidth();
        double msgWidth = msg.getLayoutBounds().getWidth();

        KeyValue initKeyValue = new KeyValue(msg.translateXProperty(), sceneWidth);
        KeyFrame initFrame = new KeyFrame(Duration.ZERO, initKeyValue);

        KeyValue endKeyValue = new KeyValue(msg.translateXProperty(), -1.0* msgWidth);
        KeyFrame endFrame = new KeyFrame(Duration.seconds(10), endKeyValue);

        Timeline timeline = new Timeline(initFrame, endFrame);

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    } // start

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

} // ArcadeApp

