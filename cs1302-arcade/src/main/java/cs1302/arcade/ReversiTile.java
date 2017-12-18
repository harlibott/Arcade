package cs1302.arcade;

/**
 * Creates a Reversi Tile
 *
 * @authors Harli Bott and Manasa Chimpiri
 * @version 1.0
 * @since 12/1/2017
 */

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ReversiTile extends Rectangle{

	/**
	 * Contructor for the ReversiTile. Creates a tile of a certain size,
	 * fills its darkgreen, adds a black border, and relocates it to 
	 * the desired location.
	 * 
	 * @param x	desired row location
	 * @param y	desired col location
	 */
	public ReversiTile(int x, int y) {
		setWidth(Reversi.TILE_SIZE);
		setHeight(Reversi.TILE_SIZE);
		
		relocate(x*Reversi.TILE_SIZE, y*Reversi.TILE_SIZE);

		setFill(Color.DARKGREEN);
		setStroke(Color.BLACK);
	}//Tile
	
	

}//Tile
