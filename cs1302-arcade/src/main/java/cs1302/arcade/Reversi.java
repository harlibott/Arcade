package cs1302.arcade;

/**
 * Uses GUI implementation to create a Reversi game
 *
 * @authors Harli Bott and Manasa Chimpiri
 * @version 1.0
 * @since 12/1/2017
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Reversi extends Application{
	
	public static final int TILE_SIZE = 50;
	public static final int WIDTH = 8;
	public static final int HEIGHT = 8;
	int mouseR, mouseC, t=0;
	char d;
	ReversiPlayer p1,p2,pTurn;
	ReversiTile tile;
	Shape[][] board = new Shape[HEIGHT][WIDTH];
	Group allTiles;
	Circle w1,w2,b1,b2,c;
	VBox scorePane;
	Text turnText,scoreText,p1score,p2score,finalScore;
	ReversiBoard r1 = new ReversiBoard();
	//NOTE: X=black, O=white
	
	/**
	 * Creates a black or white circle depending on the
	 * character parameter and places it at the x,y location.
	 * 
	 * @param BorW 	determines which color the circle should be; either B or W 
	 * @param x		the x location of the tile in which to place the circle
	 * @param y		the y location of the tile in which to place the circle
	 * @return		returns a black or white circle 
	 */
	public Circle makeCircle(char BorW,int x,int y){
		Paint color=null;
		if(BorW=='b'||BorW=='B'){ color= Color.BLACK; }
		else if(BorW=='w'||BorW=='W'){ color= Color.WHITE; }
		
		c = new Circle(x*TILE_SIZE,y*TILE_SIZE,20, color);
		c.setStroke(Color.BLACK);
		c.setStrokeWidth(TILE_SIZE*.03);
		
		c.setTranslateX((TILE_SIZE-TILE_SIZE*.5*2)/2 + TILE_SIZE*.5);
		c.setTranslateY((TILE_SIZE-TILE_SIZE*.5*2)/2 + TILE_SIZE*.5);
		
		return c;
	}
	
	/**
	 * Creates the initial 4 circles at the beginning at of the game.
	 */
	public void initial4pieces(){
		w1 = makeCircle('W',3,4);
		w2 = makeCircle('W',4,3);
		b1 = makeCircle('B',3,3);
		b2 = makeCircle('B',4,4);

		allTiles.getChildren().addAll(w1,w2,b1,b2);
	}//initial4pieces

	/**
	 * Creates the p1, p2, and pTurn Reversi players to refer to.
	 */
	public void setPlayers(){
		p1 = new ReversiPlayer('B');
		p2 = new ReversiPlayer('W');
		pTurn = new ReversiPlayer('B');
	}//setPlayers
	
	/**
	 * Sets the color of the pTurn player to the current player's color.
	 */
	public void switchPlayers(){
		if (pTurn.color == p1.color) {pTurn.color = p2.color;}
		else if (pTurn.color == p2.color) {pTurn.color = p1.color;}
	}//switchPlayers
	
	/**
	 * Makes the tile color of all valid moves lightgreen.
	 */
	public void lightgreenValidMoves(){
		for(int col=0; col<HEIGHT; col++){
			for(int row=0; row<WIDTH; row++){
				if(r1.validPlay(row, col)){
					board[row][col].setFill(Color.LIGHTGREEN);
					board[row][col].setStroke(Color.BLACK);
				}//if
				else{
					board[row][col].setFill(Color.GREEN);
					board[row][col].setStroke(Color.BLACK);
				}
			}//for
		}//for
	}//lightgreenValidMoves
	
	/**
	 * Places a circle of the current player's color after the
	 * player chose their move.
	 * 
	 * @param BorW	the color of the current player
	 */
	public void placeCircles(char BorW){
		for(int r=0; r<HEIGHT; r++)	{
			for(int c=0; c<WIDTH; c++) {
				if (r1.gameBoard[r][c] == BorW) {
					allTiles.getChildren().add(makeCircle(BorW,r,c));
				} //if
			} //for
		} //for
	}//makeCircles
	
	/**
	 * Creates a pane to display the current scores and updates when
	 * a player has won.
	 */
	public void createScorePane(){
		//Score Text
		scorePane = new VBox();
		turnText = new Text("Player B's turn");
		turnText.setFont(new Font(15));
		turnText.setFill(Color.NAVAJOWHITE);
		scoreText = new Text("Scores");
		scoreText.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
		scoreText.setUnderline(true);
		scoreText.setFill(Color.NAVAJOWHITE);
	    p1score = new Text("Black: "+r1.BScore+"\n");
	    p1score.setFont(new Font(15));
	    p1score.setFill(Color.NAVAJOWHITE);
	    p2score = new Text("White: "+r1.WScore+"\n");
	    p2score.setFont(new Font(15));
	    p2score.setFill(Color.NAVAJOWHITE);
	    finalScore = new Text("");
	    finalScore.setFont(new Font(15));
	    finalScore.setFill(Color.NAVAJOWHITE);

	    scorePane.getChildren().addAll(turnText,scoreText,p1score,p2score,finalScore);
	}//createScorePane
	
	/**
	 * Updates the score pane created after each turn.
	 */
	public void updateScorePane(){
		
		if(t==0) {turnText.setText("Player W's turn"); t=1;}
		else if(t==1) {turnText.setText("Player B's turn"); t=0;}
		p1score.setText("Black: "+r1.BScore+"\n");
		p2score.setText("White: "+r1.WScore+"\n");
		
	}//updateScorePane
	
	/**
	 * If no moves are available for the current player, the final
	 * score and winner is displayed. 
	 */
	public void gameOverMessage() {
		if(r1.getBScore() > r1.getWScore()) {
			finalScore.setText("Player B has won!"); 
		}//if
		else if(r1.getWScore() > r1.getBScore()) {
			finalScore.setText("Player W has won!"); 
		}//if
		else if(r1.getBScore() == r1.getWScore()) {
			finalScore.setText("Players have tied!");
		}//if
		
	} //gameOverMessage
	
	/**
	 * Launches the Reversi application.
	 * 
	 * @param stage	the initial stage of the Reversi Application
	 */
    public void start(Stage stage){

	    //OuterPane
		VBox outerPane = new VBox();
		outerPane.setPrefSize(WIDTH*TILE_SIZE, HEIGHT*TILE_SIZE);
		outerPane.setStyle("-fx-background-color: saddlebrown;");
	
		//############################################################
		//File > Exits
		Menu fileMenu = new Menu("_File");
		MenuItem exitArcade = new MenuItem("E_xit Arcade App");
		exitArcade.setOnAction(event -> { 
				Platform.exit();
				System.exit(0);
			});
		MenuItem endReversi = new MenuItem("E_nd Reversi");
		endReversi.setOnAction(event -> {
				stage.close();
			});
		fileMenu.getItems().addAll(exitArcade,endReversi);
		//MenuBar
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().add(fileMenu);
		outerPane.getChildren().add(menuBar);
		
		//############################################################
		//Pane (1st row under the Menu Bar)
		HBox pane = new HBox(5);
		pane.setStyle("-fx-background-color: saddlebrown;");
		pane.setAlignment(Pos.TOP_LEFT);
		pane.setPadding(new Insets(5));
		outerPane.getChildren().add(pane);
		
		Text label = new Text("OTHELLO/REVERSI");
		label.setFont(new Font(20));
		label.setFill(Color.NAVAJOWHITE);
		label.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		label.setTextAlignment(TextAlignment.CENTER);
		pane.getChildren().add(label);

		//############################################################
		//Create Tiles & Initial Pieces
		allTiles = new Group();
		pTurn = p1;
		//d = pTurn.color;
		for(int y=0; y<HEIGHT; y++){
			for(int x=0; x<WIDTH; x++){
				tile = new ReversiTile(x, y);
				board[x][y]=tile;
				allTiles.getChildren().add(tile);
				tile.setOnMouseClicked(e -> {
					System.out.println(e);
					mouseR = ((int)(e.getSceneY()/TILE_SIZE -.25)) -1;
					mouseC = (int) e.getSceneX()/TILE_SIZE;
					System.out.println("You clicked: ("+mouseR+", "+mouseC+")");
					System.out.println(p1.color+" "+p2.color+" "+pTurn.color);
					if(r1.validPlay(mouseC,mouseR)){
						r1.placeMove(pTurn, mouseC,mouseR);
						//TODO: create a pTurn.color circle wherever pTurn.color
						placeCircles(pTurn.color);
						r1.updateBoard();
						switchPlayers();
						System.out.println(p1.color+" "+p2.color+" "+pTurn.color);
						r1.showValidSpaces(pTurn);
						r1.updateScores();
						lightgreenValidMoves();
						updateScorePane();
						if(r1.gameOver(p1, p2)){
							gameOverMessage();
						}//if gameOver
					}//if
				});//setOnMouseClicked
			}//for
		}//for
		
		initial4pieces();		//creates starting screen
		//allTiles.getChildren().addAll(w1,w2,b1,b2);
		outerPane.getChildren().addAll(allTiles);
		
		//############################################################
		createScorePane();

		outerPane.getChildren().add(scorePane);
		//############################################################
		//Scene
		Scene scene = new Scene(outerPane, 410, 600);
		stage.setTitle("Reversi");
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
		
		//############################################################
		//Actual GAME Logic:
		r1.makeBoard();
		setPlayers();
		r1.showValidSpaces(p1);		//prints valid spaces
		lightgreenValidMoves();

		allTiles.requestFocus();
    }// start
    
	/**
	 * This is the entry point into the program. This main method implements
	 * the launch of the Application. It prints an error if connection is lost. 
     *
     * @param args the shell arguments provided to the program
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

}// reversi