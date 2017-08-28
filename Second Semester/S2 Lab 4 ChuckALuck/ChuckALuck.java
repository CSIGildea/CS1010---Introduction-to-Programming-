import java.util.Scanner;

public class ChuckALuck {
	
	public final static int FACES_ON_DICES = 6;
	public final static int TRIPLE_LOWEST = 3;
	public final static int TRIPLE_HIGHEST = 18;
	
	public static void main(String args[])
	{
		Scanner scanner = new Scanner(System.in);
		Wallet usersWallet = new Wallet();
		boolean userInputValidCash = false;
		boolean userStopsPlaying = false;
		boolean firstTimeBetting = true;
		String userInput = null;
		double usersOriginalCashAmount=-1;
		while(!userInputValidCash)
		{
			System.out.println("Please enter the amount of euros in your wallet:");
			if(!scanner.hasNextDouble())
			{
				System.out.println("Invalid input. Please enter valid input.");
				scanner.nextLine();
			}
			else if(scanner.hasNextDouble())
			{
				usersOriginalCashAmount = scanner.nextDouble();
				usersOriginalCashAmount = roundCash(usersOriginalCashAmount);
				usersWallet.put(usersOriginalCashAmount);
				if(usersOriginalCashAmount>0)
				{
					userInputValidCash = true;
					userStopsPlaying = false;
				}
				System.out.println("Your wallet contains: €"+String.format("%.2f",usersWallet.check()));
			}
			if(usersOriginalCashAmount<=0)
			{
				userStopsPlaying = true;
				System.out.print("You have no money to play this game.\n");
			}
		}
		while(!userStopsPlaying)
		{
			if(firstTimeBetting)
			{
				System.out.println("\nIn Chuck-a-Luck, three die are thrown and there are four different bets you can make on their outcome:"
						+ "\nTriple - All 3 dice show the same number(but not 1s or 6s) - Payout: 30:1"
						+ "\nField - Total of the 3 dice totalled must be greater than 8 but less than 12 - Payout 1:1"
						+ "\nHigh - Total of 3 dice greater than 10(but not a Triple) Payout - 1:1"
						+ "\nLow - Total of 3 dice less than 11(but not a Triple) Payout - 1:1\n");
				firstTimeBetting=false;
			}
			System.out.println("Please enter the bet you would like to make.");
			System.out.print("Please enter either 'Triple','Field'.'High','Low' or 'Quit':\n");
			userInput = scanner.next();
			if((userInput.equals("Triple"))||(userInput.equals("triple")))
			{
				resolveBet("Triple",usersWallet);
			}
			else if((userInput.equals("Field"))||(userInput.equals("field")))
			{
				resolveBet("Field",usersWallet);
			}
			else if(userInput.equals("High")||(userInput.equals("high")))
			{
				resolveBet("High",usersWallet);
			}
			else if(userInput.equals("Low")||(userInput.equals("low")))
			{
				resolveBet("Low",usersWallet);
			}
			else if(userInput.equals("Quit")||(userInput.equals("quit")))
			{
				userStopsPlaying = true;
				System.out.println("You started with €"+String.format("%.2f",usersOriginalCashAmount)+" and you "
								+ "now have €"+String.format("%.2f",usersWallet.check())+cashCompare(usersOriginalCashAmount,usersWallet));
				System.out.print("Goodbye.");
			}
			else
			{
				System.out.println("Invalid Input. Please enter either 'Triple','Field'.'High','Low' or 'Quit'");
			}
			if(usersWallet.check()==0)
			{
				userStopsPlaying=true;
				System.out.println("You started with €"+String.format("%.2f",usersOriginalCashAmount)+" and you "
						+ "now have €"+String.format("%.2f",usersWallet.check())+cashCompare(usersOriginalCashAmount,usersWallet));
				System.out.println("You have ran out of money, unlucky. Goodbye");
			}
		}
	}
	public static void resolveBet(String gameMode,Wallet usersWallet)
	{
		Scanner userInputScanner = new Scanner(System.in);
		boolean userBetValid = false;
		while(!userBetValid)
		{
			System.out.println("Your wallet contains: €"+String.format("%.2f",usersWallet.check()));
			System.out.println("How much euros would you like to bet?");
			if(userInputScanner.hasNextDouble())
			{
				double betAmount = userInputScanner.nextDouble();
				betAmount = roundCash(betAmount);
				if(usersWallet.get(betAmount))
				{
					userBetValid = true;
					System.out.println("Betting €"+String.format("%.2f",betAmount)+" on "+ gameMode+".");
					Dice dice1 = new Dice(FACES_ON_DICES);
					Dice dice2 = new Dice(FACES_ON_DICES);
					Dice dice3 = new Dice(FACES_ON_DICES);
					dice1.roll();
					dice2.roll();
					dice3.roll();
					boolean userTriple = false;
					boolean userField = false;
					boolean userHigh = false;
					boolean userLow = false;
					boolean outcomeTriple = false;
					int total = dice1.topFace()+dice2.topFace()+dice3.topFace();
					if((dice1.topFace()==dice2.topFace())&&(dice2.topFace()==dice3.topFace()))
					{
						outcomeTriple = true;
						if((total==TRIPLE_HIGHEST)||(total==TRIPLE_LOWEST))
						{
						}
						else
						{
							userTriple = true;
						}
					}
					if(((total<8)||(total>12)))
					{
						userField = true;
					}
					if((total>10)&&(!outcomeTriple))
					{
						userHigh = true;
					}
					if((total<11)&&(!outcomeTriple))
					{
						userLow = true;
					}
					System.out.println("\nDice One rolled:"+ dice1.topFace()
									  +"\nDice Two rolled:"+ dice2.topFace()
									  +"\nDice Three rolled:"+ dice3.topFace()
									  +"\nTotal:"+total+"\n");
					switch(gameMode)
					{
					case "Triple":
							if(userTriple)
							{
								System.out.println("Congratulations you won, your bet on triple!");
								usersWallet.put((betAmount*30)+betAmount);
								System.out.println("You won €"+String.format("%.2f",betAmount*30));
							}
							else
							{
								System.out.print("You lost, your bet on triple!");
								System.out.println("You lost €"+String.format("%.2f",betAmount));
							}
							break;
					case "Field":
							if(userField)
							{
								System.out.println("Congratulations you won, your bet on field!");
								usersWallet.put(betAmount+betAmount);
								System.out.println("You won €"+String.format("%.2f",betAmount));
							}
							else
							{
								System.out.print("You lost, your bet on field!");
								System.out.println("You lost €"+String.format("%.2f",betAmount));
							}						
							break;
					case "High":
							if(userHigh)
							{
								System.out.println("Congratulations you won, your bet on high!");
								usersWallet.put(betAmount+betAmount);
								System.out.println("You won €"+String.format("%.2f",betAmount));
							}
							else
							{
								System.out.print("You lost, your bet on high!");
								System.out.println("You lost €"+String.format("%.2f",betAmount));
							}
							break;
					case "Low":
							if(userLow)
							{
								System.out.println("Congratulations you won, your bet on low!");
								usersWallet.put(betAmount+betAmount);
								System.out.println("You won €"+String.format("%.2f",betAmount));
							}
							else
							{
								System.out.print("You lost, your bet on low!");
								System.out.println("You lost €"+String.format("%.2f",betAmount));
							}
							break;
					default:
							break;
					}
					System.out.println("The current amount in your wallet is: €"+String.format("%.2f",usersWallet.check()));
					return;
				}
				else
				{
					System.out.println("There is not enough money in your wallet to make this bet.");
					userInputScanner.nextLine();
				}
			}
			else
			{
				System.out.println("Invalid Input");
				userInputScanner.nextLine();
			}
		}
	}	
	public static double roundCash(double inputtedAmount)
	{
		double cashRounded=inputtedAmount*100;
		cashRounded = Math.round(cashRounded);
		cashRounded = cashRounded/100;
		return cashRounded;
	}
	public static String cashCompare(double usersOriginalCashAmount, Wallet usersWallet)
	{
		double cashDifference;
		String cashCompared;
		if(usersOriginalCashAmount>usersWallet.check())
		{
			cashDifference = usersOriginalCashAmount-usersWallet.check();
			cashDifference = roundCash(cashDifference);
			cashCompared="\nOverall, you lost €"+String.format("%.2f",cashDifference)+".";
			return cashCompared;
		}
		else if(usersOriginalCashAmount<usersWallet.check())
		{
			cashDifference = usersWallet.check()-usersOriginalCashAmount;
			cashDifference = roundCash(cashDifference);
			cashCompared="\nOverall, you won €"+String.format("%.2f",cashDifference)+".";
			return cashCompared;
		}
		return "";	
	}
}



