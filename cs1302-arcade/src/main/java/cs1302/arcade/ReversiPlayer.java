package cs1302.arcade;

/**
 * Creates a ReversiPlayer and its supporting methods.
 *
 * @authors Harli Bott and Manasa Chimpiri
 * @version 1.0
 * @since 12/1/2017
 */

public class ReversiPlayer {

	char color;
	
	/**
	 * Constructor which determines the player's color
	 *  
	 * @param BorW	input either B or W
	 */
	public ReversiPlayer(char BorW){
		color = BorW;
	}//ReversiPlayer
	
	/**
	 * Changes the current player's char color at any 
	 * point during the Application's run.
	 * 
	 * @param BorW	input either B or W
	 */
	void setColor(char BorW){
		color = BorW;
	}//setColor
	
	/**
	 * Returns the current player's color
	 * 
	 * @return char either B or W
	 */
	char getPlayerColor(){
		return color;
	}//getColor

}//ReversiPlayer
