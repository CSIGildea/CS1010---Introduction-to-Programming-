import java.util.Scanner;

public class RationalNumbers 
{
	public static void main(String args[])
	{
		boolean finished = false;
		int rationalNumbersInputted = 0;
		String userInput;
		Scanner scanner = new Scanner(System.in);
		Rational firstRational = null;
		Rational secondRational = null;
		while(!finished)
		{
			if(rationalNumbersInputted==0)
			{
				System.out.println("Enter your first rational number in the form 'numerator/demoninator' or 'numerator', or enter 'Quit'.E.G. '3/4' or '4':");
			}
			else if(rationalNumbersInputted==1)
			{
				System.out.println("Enter your second rational number in the form 'numerator/demoninator' or 'numerator'. or enter 'Quit'.E.G. '3/4' or '4':");
			}
			userInput = scanner.nextLine();
			if (userInput.equals("Quit")||userInput.equals("quit"))
			{
				finished = true;
				System.out.println("Goodbye!");
				scanner.close();
			}
			else if(userInput.contains("/"))
			{
				int fractionLocation = userInput.indexOf("/");
				if (userInput.substring(0,fractionLocation).matches("-?[0-9]+"))
				{
					int firstNum = Integer.parseInt(userInput.substring(0,fractionLocation));
					if (userInput.substring(fractionLocation+1,userInput.length()).matches("-?[0-9]+"))
					{
						int firstDen = Integer.parseInt(userInput.substring(fractionLocation+1,userInput.length()));
						if(firstDen==0)
						{
							System.out.println("Rational Numbers cannot contain the value zero as their denominator.");
						}
						else if(rationalNumbersInputted==0)
						{
							firstRational = new Rational(firstNum,firstDen);
							rationalNumbersInputted++;
						}
						else if(rationalNumbersInputted==1)
						{
							secondRational = new Rational(firstNum,firstDen);
							rationalNumbersInputted++;
						}
					}
					else
					{
						System.out.println("Invalid Input. Please enter values in the correct format.");
					}
				}
				else
					System.out.println("Invalid Input. Please enter values in the correct format.");
			}
			else if (userInput.matches("-?[0-9]+"))
			{
				int firstNum = Integer.parseInt(userInput);
				if(rationalNumbersInputted==0)
				{
					firstRational = new Rational(firstNum);
				}
				else if(rationalNumbersInputted==1)
				{
					secondRational = new Rational(firstNum);
				}
				rationalNumbersInputted++;
			}
			else
			{
				System.out.println("Invalid Input. Please enter values in the correct format.");
			}
			if(rationalNumbersInputted==2)
			{
				System.out.print("Results of Computations:\n");
				firstRational.simplify();
				secondRational.simplify();
				System.out.println(""+firstRational.toString()+" + "+secondRational.toString()+" = "+firstRational.add(secondRational).toString()+"\n"
									 +firstRational.toString()+" x "+secondRational.toString()+" = "+firstRational.multiply(secondRational).toString());
				if(secondRational.getNumerator()==0)
				{
					System.out.println("Cannot divide by zero.");
				}
				else
				{
					System.out.print(firstRational.toString()+" / "+secondRational.toString()+" = "+firstRational.divide(secondRational).toString()+"\n");
				}
				System.out.println("Does "+firstRational.toString()+" = "+secondRational.toString()+"?: "+(firstRational.equals(secondRational)?"Yes":"No")+"\n"
									 +"Is "+firstRational.toString()+" less than "+secondRational.toString()+"?: "+(firstRational.isLessThan(secondRational)?"Yes":"No"));
				rationalNumbersInputted=0;
			}
		}
	}
}
