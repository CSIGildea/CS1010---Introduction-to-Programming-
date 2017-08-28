import java.util.Scanner;
import java.util.Arrays;
import java.util.InputMismatchException;

public class HighScores {

	public static void main(String[] args)
	{
		boolean finished = false;
		boolean numberOfHighScoresFound = false;
		int numberOfScores = 0;
		Scanner input = new Scanner( System.in );
		try
		{
			System.out.print("How many high scores do you want to maintain?");
			while(!numberOfHighScoresFound)
			{
				if(input.hasNextInt())
				{
					numberOfScores = input.nextInt();
					numberOfHighScoresFound = true;
				}
				else
				{
					System.out.print("Invalid Input, please enter only integer digits.\nHow many high scores do you want to maintain?");
					input.next();
				}
			}
			while(numberOfScores<=0)
			{
				System.out.print("The amount of high scores you want to maintain must be an integer higher than or equal to 1."
								+ "\nHow many high scores do you want to maintain?");
				numberOfScores = input.nextInt();
			}
			if(numberOfScores>0)
			{
				int [] highScore = new int[numberOfScores];
				initialiseHighScores(highScore);
				System.out.print("Your scores must be positive integers.\nPlease enter a high score, or enter 'Quit':");
				Scanner scoreInput = new Scanner( System.in );

				while((!finished))
				{	
					if(scoreInput.hasNextInt())
					{
						int newHighScore = scoreInput.nextInt();
						if (newHighScore<0)
						{
							System.out.print("Your high score value must be positive.\n");
						}
						if(higherThan(highScore, newHighScore))
						{
							insertScore(highScore,newHighScore);
						}
							printHighScores(highScore,newHighScore);
							System.out.print("\nPlease enter your next high score, or enter 'Quit':");
					}
					else if (scoreInput.hasNext("Quit"))
					{
						finished = true;
					}
					else
					{
						System.out.print("You need to enter an integer\nPlease enter a high score:");
						scoreInput.next();
					}
				}
			input.close();
			}
			else
			{
					System.out.print("The amount of values you want to maintain must be above 0.\n");
			}
			
		}
		catch (InputMismatchException exception)
		{
			System.out.print("Invalid Input, please enter only integer digits.");
		}
}

	
	public static void initialiseHighScores(int[] highScore)
	{
		int counter = 0;
		while(counter<highScore.length)
		{
			highScore[counter] = 0;
			counter++;
		}
	}
	
	public static void printHighScores(int[] highScore, int newHighScore)
	{
		if(highScore!=null)
		{
			int index = highScore.length-1;
			int count = 0;
			System.out.print("The high scores are ");
			while(count<=index)
			{
				System.out.print(""+highScore[index]);
				if(index>0)
				{
					System.out.print(", ");
				}
				index--;
			}	
		}
	}
	
	public static boolean higherThan(int[] highScore, int newHighScore) 
	{
		if(highScore!=null)
		{
			if(newHighScore>=highScore[0])
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return true;
			
		}
	}
	
	public static void insertScore(int[] highScore, int newHighScore)
	{
		highScore[0]=newHighScore;
		Arrays.sort(highScore);
	}
}
