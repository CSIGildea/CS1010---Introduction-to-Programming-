import java.util.Scanner;

public class C4HumanPlayer extends ConnectPlayer{
	private static final char BOARD_WIDTH = 7;
	char playerPiece;
	public C4HumanPlayer(char player1Piece) {
		// TODO Auto-generated constructor stub
		this.playerPiece = player1Piece;
	}

	@Override
	public int columnToPlay() {
		boolean validNumberFound = false;
		Scanner input = new Scanner(System.in);
		int column = 0;
		while(!validNumberFound)
		{
			System.out.print("\nEnter move for " + this.getPiece() + " :");
			char[] inputCharacters = input.next().toCharArray();
			if ((inputCharacters.length >= 1) &&
					(inputCharacters[0] >= '1') && (inputCharacters[0] <= '0'+BOARD_WIDTH))
			{
				column = (int) (inputCharacters[0]-'1');
				validNumberFound = true;
				return column;
			}
			else System.out.println("Invalid entry.  You must enter a column number between 1 and 7.");
		}
		return column;
	}

	@Override
	public char getPiece() {
		return this.playerPiece;
	}

}
