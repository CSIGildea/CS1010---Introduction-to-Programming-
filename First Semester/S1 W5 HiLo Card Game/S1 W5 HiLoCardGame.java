import java.util.Random;
import java.util.Scanner;

public class HiLoCardGame {
	
	public static final int CARD_GENERATOR_MAX=14;
	public static final int CARD_GENERATOR_MIN=2;
	public static final int CORRECT_NUMBER_OF_GUESSES_TO_WIN=4;
	public static final int LAST_GUESS=0;
	public static final int JACK_CARD=11;
	public static final int QUEEN_CARD=12;
	public static final int KING_CARD=13;
	public static final int ACE_CARD=14;
	
	public static void main(String[] args) {
		
		int count = 0;
		int userGuessesLeft = 4;
		boolean stillWinning = true;
		String computersCardWasString="";
		String usersNewCardString = "";
		
		System.out.println("In this game of Hi-Lo you are presented with a card"
				+ ", you must then guess correctly\nwhether the card the computer draws is higher (Hi),"
				+ "lower (Lo) or equal (Eq) to your card."
				+ "\nAfter you guess correctly your card is replaced with "
				+ "the card the computer had previously played.\nThe computer draws a new card"
				+ " and the game is then repeated."
				+ "\nIf you guess correctly, four times in a row, you win!\n"
				+ "The order of these cards, from low to high, is as follows:\n2,3,4,5,6,7,8,9,10,"
				+ "Jack(11),Queen(12),King (13) and Ace(14)\n");
		Random generator = new Random();
		int usersCard = generator.nextInt(CARD_GENERATOR_MAX-CARD_GENERATOR_MIN+1)+CARD_GENERATOR_MIN;
		if ((usersCard>1)&&(usersCard<=10))
		{
			System.out.println("Your card is "+ usersCard+".");
		}
		else if (usersCard==11)
		{
			System.out.println("Your card is a Jack (11).");
		}
		else if (usersCard==12)
		{
			System.out.println("Your card is a Queen (12).");
		}
		else if (usersCard==13)
		{
			System.out.println("Your card is a King (13).");
		}
		else if (usersCard==14)
		{
			System.out.println("Your card is an Ace (14).");
		}
		while ((count<CORRECT_NUMBER_OF_GUESSES_TO_WIN)&&(stillWinning))
		{
			System.out.println("Do you believe the computer's next card is higher (Hi),lower (Lo) or equal (Eq) "
								+ "to your card?"+"\nTo make your guess enter 'Hi','Lo' or 'Eq' into the console."
								+ " Enter 'quit' to exit the game.");
			Scanner inputScanner = new Scanner(System.in);
			String usersInput = inputScanner.next();								
			int computersCard = generator.nextInt(CARD_GENERATOR_MAX-CARD_GENERATOR_MIN+1)+CARD_GENERATOR_MIN;	
			userGuessesLeft--;
			//Creating Strings for Result
			if ((computersCard<JACK_CARD))
			{
				computersCardWasString="\nThe computer's card was a "+ computersCard+".";
				usersNewCardString="Your card is now a "+ computersCard+".";
			}
			else if (computersCard==JACK_CARD)
			{
				computersCardWasString="\nThe computer's card was a Jack (11).";
				usersNewCardString="Your card is now a Jack (11).";
			}
			else if (computersCard==QUEEN_CARD)
			{
				computersCardWasString="\nThe computer's card was a Queen(12).";
				usersNewCardString="Your card is now a Queen (12).";
			}
			else if (computersCard==KING_CARD)
			{
				computersCardWasString="\nThe computer's card was a King (13).";
				usersNewCardString="Your card is now a King (13).";
			}
			else if (computersCard==ACE_CARD)
			{
				computersCardWasString="\nThe computer's card was an Ace (14).";
				usersNewCardString="Your card is now an Ace (14).";
			}
			if ((usersInput.equals("hi"))||(usersInput.equals("Hi")))
			{
				if ((usersCard<computersCard)&&(usersCard!=computersCard))
				{
					if (userGuessesLeft==LAST_GUESS)
					{
						System.out.println("Congratulations you won the game! Restart to play again."+computersCardWasString+"");
					}
					else
					{
						System.out.println("Congratulations! You guessed correctly, guess correctly "+ userGuessesLeft+" more times to win!"
										+computersCardWasString+usersNewCardString+"");
						usersCard=computersCard;
					}
				}
				else
				{
					System.out.println("You failed, better luck next time! Restart to play again"+computersCardWasString+"");
					stillWinning=false;
				}
			}
			else if ((usersInput.equals("lo"))||(usersInput.equals("Lo")))
			{
				if ((usersCard>computersCard)&&(usersCard!=computersCard))
				{
					if (userGuessesLeft==LAST_GUESS)
					{								
						System.out.println("Congratulations you won the game! Restart to play again."+computersCardWasString+"");
					}
					else
					{
						System.out.println("Congratulations! You guessed correctly, guess correctly "+userGuessesLeft 
										+" more times to win!"+computersCardWasString+usersNewCardString+"");
						usersCard=computersCard;
					}
				}
				else
				{
					System.out.println("You failed, better luck next time! Restart to play again"+computersCardWasString+"");
					stillWinning=false;
				}
			}
			else if ((usersInput.equals("eq"))||(usersInput.equals("Eq")))
			{
				if (usersCard==computersCard)
				{
					if (userGuessesLeft==LAST_GUESS)
					{								
						System.out.println("Congratulations you won the game! Restart to play again."+computersCardWasString+"");
					}
					else
					{
						System.out.println("Congratulations! You guessed correctly, guess correctly "+ userGuessesLeft 
										+" more times to win!"+computersCardWasString+usersNewCardString+"");
					}
				}
				else
				{
					System.out.println("You failed, better luck next time! Restart to play again."+computersCardWasString+"");
					stillWinning=false;
				}
			}
			else if (((usersInput.equals("quit")||(usersInput.equals("Quit"))||(usersInput.equals("exit"))||(usersInput.equals("Exit")))))
			{
				System.out.println("Goodbye! Thanks for playing!");
				stillWinning=false;
			}
			else
			{
				System.out.println("Invalid input, please restart and only enter either 'Hi' or 'Lo' or 'Eq'.");
				stillWinning=false;
			}
			count++;
		}
	}
}


