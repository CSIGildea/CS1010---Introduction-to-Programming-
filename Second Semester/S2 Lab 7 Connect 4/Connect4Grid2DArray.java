
public class Connect4Grid2DArray implements Connect4Grid{
	private static final int BOARD_HEIGHT = 6;
	private static final int BOARD_WIDTH = 7;
	public static final char BLANK = ' ';
	char[][] board;
	Connect4Grid2DArray()
	{
		board =  new char[BOARD_HEIGHT][BOARD_WIDTH];
		emptyGrid();
	}
	@Override
	public String toString() {
		String boardString = "";
		for(int row = 0;row<BOARD_HEIGHT;row++)
		{
			boardString+="\n|";
			for(int column = 0;(column<BOARD_WIDTH); column++)
			{
				boardString+=(board[row][column]);
				boardString+=("|");
			}
		}
		boardString+=("\n---------------");
		return boardString+=("\n 1 2 3 4 5 6 7 \n");
	}
	
	@Override
	public void emptyGrid() 
	{
		{
			for(int row=0; row<BOARD_HEIGHT;row++)
			{
				for(int column= 0; column<BOARD_WIDTH;column++)
				{
					board[row][column]=BLANK;
				}
			}
	
		}
	}

	@Override
	public boolean isValidColumn(int column) {
		if((column>=0)&&(column<BOARD_WIDTH))
		{
			if(!isColumnFull(column))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isColumnFull(int column) {
		for(int row = 0; row<board.length;row++)
		{
			if(board[row][column]==BLANK)
			{
				return false;
			}
		}
		return true;
	}

	@Override
	public void dropPiece(ConnectPlayer player, int column) {
		// TODO Auto-generated method stub
		int lastIndex = 0;
		for(int row=0;row<BOARD_HEIGHT;row++)
		{
			// If we found something, which means if the character is not
			// zero character
			if (board[row][column] != BLANK) {
				// Put the disk on top of the current one.
				board[row-1][column] = player.getPiece();
				return;
			}
		}
		board[5][column] = player.getPiece();
		return;
	}

	@Override
	public boolean didLastPieceConnect4() {
		for (int row = 0; row < BOARD_HEIGHT; row++) 
		{
			for (int col = 0; col < BOARD_WIDTH; col++)
			{
				char connect4SlotToCheck = board[row][col];
				if (connect4SlotToCheck == BLANK)
				{
					continue;
				}
				if (col + 3 < BOARD_WIDTH &&
						connect4SlotToCheck == board[row][col+1] && 
						connect4SlotToCheck == board[row][col+2] &&
						connect4SlotToCheck == board[row][col+3])
				{
					return true;
				}
				if (row + 3 < BOARD_HEIGHT) 
				{
					if (connect4SlotToCheck == board[row+1][col] &&
							connect4SlotToCheck == board[row+2][col] &&
							connect4SlotToCheck == board[row+3][col])
					{
						return true;
					}
					if (col + 3 < BOARD_WIDTH &&
							connect4SlotToCheck == board[row+1][col+1] &&
							connect4SlotToCheck == board[row+2][col+2] &&
							connect4SlotToCheck == board[row+3][col+3])
					{
						return true;
					}
					if (col - 3 >= 0 &&
							connect4SlotToCheck == board[row+1][col-1] &&
							connect4SlotToCheck == board[row+2][col-2] &&
							connect4SlotToCheck == board[row+3][col-3])
					{
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean isGridFull() {
		//Check for the presence of a blank character, if present, board is not full, else return true;
		for(int col = 0; col<board[0].length;col++)
		{
			if(board[0][col]==BLANK)
			{
				return false;
			}
		}
		return true;
	}

}
