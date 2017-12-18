package cs1302.arcade;

/**
 * Creates a ReversiBoard and its supporting methods.
 *
 * @authors Harli Bott and Manasa Chimpiri
 * @version 1.0
 * @since 12/1/2017
 */

public class ReversiBoard {
	
	char gameBoard[][];
	final int rows = 8;
	final int cols = 8;
	int BScore=2;
	int WScore=2;
	int empty;

	/**
	 * Constructor for the ReversiBoard creates a 2D char array
	 * to keep track of B and W locations and valid moves.
	 */
	public ReversiBoard() {
		gameBoard = new char [rows][cols];
	}//ReversiBoard
	
	/**
	 * Returns the score of the B player (p1)
	 * 
	 * @return int  the number of black pieces
	 */
	public int getBScore(){
		return BScore;
	} //getBScore

	/**
	 * Returns the score of the W player (p2)
	 * 
	 * @return int	the number of white pieces
	 */
	public int getWScore(){
		return WScore;
	} //getWScore
	
	/**
	 * Returns the number of empty tiles on the grid.
	 * 
	 * @return int	the number of empty tiles
	 */
	public int getempty(){
		return empty;
	} //getempty

	/**
	 * Initializes the 2D char array gameBoard
	 */
	public void makeBoard() {
		for(int i=0; i<gameBoard.length; i++)
		{
			for(int j=0; j<gameBoard[0].length; j++)
			{
				gameBoard[i][j] = '.';
			}
		} //for
		gameBoard[3][3]='B';
		gameBoard[4][4]='B';
		gameBoard[4][3]='W';
		gameBoard[3][4]='W';
	}//makeBoard	

	/**
	 * Prints the contents of the 2D char array gameBoard along with 
	 * the axis for easy reading.
	 */
	public void printBoard() {
		
		System.out.println("    0 1 2 3 4 5 6 7");
		
		for(int i=0; i<8; i++)
		{
			System.out.print("  "+ (i)); 
			for(int j=0; j<8; j++)
			{
				System.out.print(" "+gameBoard[i][j]);
			}
			System.out.println();
		} //for
		System.out.println();
	}//printBoard
	
	/**
	 * Checkers whether the parameter location is a valid 
	 * move for a player or not.
	 * 
	 * @param r		      the row location
	 * @param c		      the col location
	 * @return boolean	whether a location is a valid move
	 */
	boolean validPlay(int r, int c) {
		boolean valid = false;
		if (gameBoard[r][c]=='_')
		{
			valid= true;
		}
		return valid;
	} //validPlay
	
	/**
	 * Places a '_' at every valid possible move for the current player.
	 * 
	 * @param thisPlayer	the current player
	 */
	public void showValidSpaces(ReversiPlayer thisPlayer) {
		char thisColor= thisPlayer.color;
		char oppColor='?';
		if(thisPlayer.color=='W')
		{
			oppColor='B';
		} //if
		else if(thisPlayer.color=='B')
		{
			oppColor='W';
		} //if
		
		for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(gameBoard[i][j] == oppColor){
                    int I = i;
                    int J = j;  
                    if(i-1>=0 && j-1>=0 && gameBoard[i-1][j-1] == '.')
                    { 
                        i = i+1;
                        j = j+1;
                        while(i<rows-1 && j<cols-1 && gameBoard[i][j] == oppColor) { i++; j++; }
                        if(i<rows && j<cols && gameBoard[i][j] == thisColor) { gameBoard[I-1][J-1]='_'; }
                    } 
                    i=I;
                    j=J;
                    if(i-1>=0 && gameBoard[i-1][j] == '.')
                    {
                        i = i+1;
                        while(i<rows-1 && gameBoard[i][j] == oppColor) i++;
                        if(i<rows && gameBoard[i][j] == thisColor) gameBoard[I-1][J]='_';
                    } 
                    i=I;
                    if(i-1>=0 && j+1<cols && gameBoard[i-1][j+1] == '.')
                    {
                        i = i+1; j = j-1;
                        while(i<rows-1 && j>0 && gameBoard[i][j] == oppColor){i++;j--;}
                        if(i<rows && j>=0 && gameBoard[i][j] == thisColor) gameBoard[I-1][J+1]='_';
                    }  
                    i=I;j=J;
                    if(j-1>=0 && gameBoard[i][j-1] == '.')
                    {
                        j = j+1;
                        while(j<rows-1 && gameBoard[i][j] == oppColor)j++;
                        if(j<rows && gameBoard[i][j] == thisColor) gameBoard[I][J-1]='_';
                    }
                    j=J;
                    if(j+1<rows && gameBoard[i][j+1] == '.')
                    {
                        j=j-1;
                        while(j>0 && gameBoard[i][j] == oppColor)j--;
                        if(j>=0 && gameBoard[i][j] == thisColor) gameBoard[I][J+1]='_';
                    }
                    j=J;
                    if(i+1<rows && j-1>=0 && gameBoard[i+1][j-1] == '.')
                    {
                        i=i-1;
                        j=j+1;
                        while(i>0 && j<cols-1 && gameBoard[i][j] == oppColor){i--;j++;}
                        if(i>=0 && j<cols && gameBoard[i][j] == thisColor) gameBoard[I+1][J-1]='_';
                    }
                    i=I;
                    j=J;
                    if(i+1<rows && gameBoard[i+1][j] == '.')
                    {
                        i=i-1;
                        while(i>0 && gameBoard[i][j] == oppColor) i--;
                        if(i>=0 && gameBoard[i][j] == thisColor) gameBoard[I+1][J]='_';
                    }
                    i=I;
                    if(i+1<rows && j+1 < cols && gameBoard[i+1][j+1] == '.')
                    {
                        i=i-1;
                        j=j-1;
                        while(i>0 && j>0 && gameBoard[i][j] == oppColor){i--;j--;}
                        if(i>=0 && j>=0 && gameBoard[i][j] == thisColor) gameBoard[I+1][J+1]='_';
                    }
                    i=I;j=J;
                } //if
            } //for 
        } //for
		
		this.printBoard();
		
	} //showValidLocations
	
	/**
	 * Places the current players char at the desired location and 
	 * flips all the pieces in between.
	 * 
	 * @param p	the current player
	 * @param i	the row location of the desired move
	 * @param j the col location of the desired move
	 */
	public void placeMove(ReversiPlayer p, int i, int j) {
		
		char oppColor='?';
		if(p.color=='W') {oppColor='B';}
		else if(p.color=='B') {oppColor='W';}
		//------------------------------
		gameBoard[i][j]= p.color;
		//------------------------------
		
		int I = i, J = j; 
		
		if(i-1>=0 && j-1>=0 && gameBoard[i-1][j-1] == oppColor){ 
            i = i-1;
            j = j-1;
            while(i>0 && j>0 && gameBoard[i][j] == oppColor) { i--; j--;}
            if(i>=0 && j>=0 && gameBoard[i][j] == p.color) {
            	while(i!=I-1 && j!=J-1) {
            		gameBoard[++i][++j]=p.color;
            	} //while
            } //if
        } //if
        i=I;
        j=J; 
        if(i-1>=0 && gameBoard[i-1][j] == oppColor){
            i = i-1;
            while(i>0 && gameBoard[i][j] == oppColor) { i--;}
            if(i>=0 && gameBoard[i][j] == p.color) {
            	while(i!=I-1) {
            		gameBoard[++i][j]=p.color;
            	} //while
            } //if
        } //if
        i=I; 
        if(i-1>=0 && j+1<cols && gameBoard[i-1][j+1] == oppColor){
            i = i-1; 
            j = j+1;
            while(i>0 && j<cols-1 && gameBoard[i][j] == oppColor) { i--;j++;}
            if(i>=0 && j<cols && gameBoard[i][j] == p.color) {
            	while(i!=I-1 && j!=J+1) {
            		gameBoard[++i][--j] = p.color;
            	} //while
            } //if
        } //if 
        i=I;j=J;
        if(j-1>=0 && gameBoard[i][j-1] == oppColor){
            j = j-1;
            while(j>0 && gameBoard[i][j] == oppColor) { j--;}
            if(j>=0 && gameBoard[i][j] == p.color) {
            	while(j!=J-1) {
            		gameBoard[i][++j] = p.color;
            	} //while
            } //if
        } //if
        j=J; 
        if(j+1<cols && gameBoard[i][j+1] == oppColor){
            j=j+1;
            while(j<cols-1 && gameBoard[i][j] == oppColor) { j++;}
            if(j<cols && gameBoard[i][j] == p.color) {
            	while(j!=J+1) {
            		gameBoard[i][--j] = p.color;
            	} //while
            } //if
        } //if
        j=J; 
        if(i+1<rows && j-1>=0 && gameBoard[i+1][j-1] == oppColor){ 
            i=i+1;j=j-1;
            while(i<rows-1 && j>0 && gameBoard[i][j] == oppColor) {i++; j--;}
            if(i<rows && j>=0 && gameBoard[i][j] == p.color) {
            	while(i!=I+1 && j!=J-1) {
            		gameBoard[--i][++j] = p.color;
            	} //while
            } //if
        } //if
        i=I;
        j=J; 
        if(i+1 <rows && gameBoard[i+1][j] == oppColor){ 
            i=i+1;
            while(i<rows-1 && gameBoard[i][j] == oppColor) { i++;}
            if(i<rows && gameBoard[i][j] == p.color) {
            	while(i!=I+1) {
            		gameBoard[--i][j] = p.color;
            	} //while
            } //if
        } //if
        i=I;
        if(i+1 <rows && j+1 <cols && gameBoard[i+1][j+1] == oppColor){
            i=i+1;j=j+1;
            while(i<rows-1 && j<cols-1 && gameBoard[i][j] == oppColor) {i++;j++;}
            if(i<rows && j<cols && gameBoard[i][j] == p.color) {
            	while(i!=I+1 && j!=J+1) {
            		gameBoard[--i][--j] = p.color;
            	} //while
            } //if
        }  //if
        
		this.printBoard();
	} //placeMove
	
	/**
	 * Replaces all the '_' indicating valid moves back to '.'
	 */
	public void updateBoard() {
		// TODO Auto-generated method stub
		for(int i=0; i<rows; i++)
		{
			for(int j=0; j<cols; j++)
			{
				if (gameBoard[i][j] == '_')
				{
					gameBoard[i][j] = '.';
				} //if
			} //for
		} //for
	}//updateBoard
	
	/**
	 * Recounts the number of W and B spaces to update the score.
	 */
	public void updateScores()
	{
        BScore = 0; WScore = 0; empty = 0;
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if(gameBoard[i][j]=='B') BScore++;
                else if(gameBoard[i][j]=='W') WScore++;
                else empty++;
            }
        }
    } //updateScores

	/**
	 * Determines whether the game is over or not based on valid moves.
	 * 
	 * @param p1	player 1
	 * @param p2	player 2
	 * @return boolean		true or false whether the game is over or not
	 */
	boolean gameOver(ReversiPlayer p1, ReversiPlayer p2)
	{
		boolean validSpaces = false;
		boolean over = false;
		for(int i=0;i<8;i++) {
            for(int j=0;j<8;j++) {
                if(gameBoard[i][j] == '_') {
                	validSpaces = true;
                }
            }
		}
		if (validSpaces==false || getempty()==0  || getBScore()==0 || getWScore()==0) {
			over = true;
		}
		return over;
	} //gameOver
	
}//ReversiBoard
