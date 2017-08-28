import java.awt.Color;
import java.awt.Font;
import java.util.Random;
import java.util.Scanner;

public class GraphicalSieveOfEratosthenes{
	
	public final static double UNIT_SIZE = 1;
	public final static double TOP_PADDING = 0.1;
	public final static double PRIME_PADDING = 0.05;
	public final static double LEFT_PADDING = 0.002;
	public final static double PRIME_DISPLAY_ROOM = 0.3;
	public final static double PRIMES_PER_ROW = 5;
	public static int primeCounter = 0;
	

	public static void main(String[] args) 
	{
		boolean finished = false;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter 'quit' to exit");
		while(!finished)	
		{
			System.out.print("Enter int >= 2:");
			if(scanner.hasNextInt())
			{
				int inputtedNumber = scanner.nextInt();
				if(inputtedNumber>=2)
				{
					primeCounter = 0;
					StdDraw.setPenColor(StdDraw.WHITE);
					StdDraw.filledSquare(0.5,0.5,1);
					fontSize(inputtedNumber);
					displayNumbers2ToN(inputtedNumber);
					System.out.println(""+nonCrossedOutSubseqToString(sieve(inputtedNumber)));
				}
				else if(inputtedNumber<2)
				{
				}
			}
			else if(scanner.hasNext("quit"))
			{
				finished = true;
				System.out.print("Goodbye.");
			}
			else
			{
				System.out.println("Invalid Input.Please enter valid number input.");
				scanner.next();
			}
		}
		scanner.close();
	}

	public static boolean [] createSequence(int inputtedNumber)
	{
		//We minus 1 from the inputted number as 1 isn't
		//a prime number and not part of any of our lists.
		boolean [] sequenceOfNumbers = new boolean[inputtedNumber-1];
		for(int index=2;(inputtedNumber>=index);index++)
		{
			//Minus 2 from index value each time, as the value "2" is in at seqeucenOfNumbers[0]
			sequenceOfNumbers[index-2]= true;
		}
		return sequenceOfNumbers;
	}

	public static boolean[] crossOutHigherMultiples(boolean[] sequenceOfNumbers, int factor, int inputtedNumber)
	{
		boolean[] isPrime = new boolean[sequenceOfNumbers.length];
		System.arraycopy( sequenceOfNumbers, 0, isPrime, 0, sequenceOfNumbers.length );
		for(int count = factor;(factor*count)<= sequenceOfNumbers.length+1;count++)
		{
			if(isPrime[((count*factor)-2)]==true)
			{
				isPrime[((count*factor)-2)]=false;
				displayComposite(count*factor,primeCounter,inputtedNumber);
				//Canceling non prime values via multiplication E.g. 2x2,2x3....etc
				//We minus 2 as isPrime[0] represents the value 2.
			}
		}
		return isPrime;

	}
	public static boolean[] sieve(int inputtedNumber)
	{
		boolean[] sequenceOfNumbers = createSequence(inputtedNumber);
		System.out.println(""+sequenceToString(sequenceOfNumbers));
		for(int factor = 2;(factor<=inputtedNumber);factor++)
		{
			if(sequenceOfNumbers[(factor)-2]==true)
			{
				primeCounter++;
				displayPrime(factor,primeCounter);
			}
			sequenceOfNumbers=crossOutHigherMultiples(sequenceOfNumbers,factor,inputtedNumber);
		}
		System.out.println(""+sequenceToString(sequenceOfNumbers));
		return sequenceOfNumbers;
	}

	public static String sequenceToString(boolean[] sequenceOfNumbers)
	{
		String sequenceCrossedAndNonCrossed = "";
		for(int index = 2;index<=sequenceOfNumbers.length+1; index++)
		{
			if(sequenceOfNumbers[(index)-2]==true)
			{
				if(index-2==0)
				{
					sequenceCrossedAndNonCrossed += index;
				}
				else
				{
					sequenceCrossedAndNonCrossed += ","+index;
				}	
			}
			else if(sequenceOfNumbers[(index)-2]==false)
			{
				sequenceCrossedAndNonCrossed += ",["+index+"]";
			}
		}
		return sequenceCrossedAndNonCrossed;

	}
	public static String nonCrossedOutSubseqToString(boolean[] sequenceOfNumbers)
	{
		String primeNumbersList = "";
		for(int index = 2;index<=sequenceOfNumbers.length+1; index++)
		{
			if(sequenceOfNumbers[(index)-2]==true)
			{

				if(index-2==0)
				{
					primeNumbersList += index;
				}
				else
				{
					primeNumbersList += ","+index;
				}
			}
		}
		return primeNumbersList;
	}
	public static void displayNumber(int numberToDisplay,Color numberColor, int largestInputtedNumber)
	{
		int squareMaker = (int) Math.sqrt(largestInputtedNumber);
		int endOfLine = squareMaker;
		double xAndYIncrement = scalingBoxes(largestInputtedNumber);
        double gap = xAndYIncrement*0.25;
        xAndYIncrement = xAndYIncrement*0.75;
		double xPosition = LEFT_PADDING+gap;
	    double yPosition = UNIT_SIZE-TOP_PADDING;
		StdDraw.setPenColor(numberColor);
        
		int numberToPrint = numberToDisplay;
		int numberPosition = numberToDisplay;
		
		while(numberPosition>endOfLine)
		{
			numberPosition-=endOfLine;
			yPosition-=(xAndYIncrement+gap);
		}
		while (numberPosition>1)
		{
			numberPosition--;
			xPosition+=(xAndYIncrement+gap);
		}
		StdDraw.filledSquare(xPosition, yPosition, (xAndYIncrement/2));
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(xPosition,yPosition, ""+numberToPrint);
	}
	public static void displayNumbers2ToN(int largestInputtedNumber)
	{
		StdDraw.setPenColor(StdDraw.BLACK);
		double xPosition = UNIT_SIZE-(PRIME_DISPLAY_ROOM/2)+PRIME_PADDING-LEFT_PADDING;
		double yPosition = UNIT_SIZE-TOP_PADDING;
		StdDraw.text(xPosition,yPosition, "Prime Numbers");
		for (int count = 2; count<=largestInputtedNumber;count++)
		{       
			displayNumber(count,StdDraw.GRAY, largestInputtedNumber);
		}
	}
	public static void displayComposite(int numberToDisplay, int primeCounter, int largestInputtedNumber)
	{
		Color factorColor = findFactorColor(primeCounter);
		//StdDraw.show(50);
		try {
		    Thread.sleep(50);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		displayNumber(numberToDisplay,factorColor,largestInputtedNumber);
	}
	public static void displayPrime(int primeNumber, int primeCount)
	{
		StdDraw.setPenColor(StdDraw.BLACK);
		int primePositionCount = primeCount;
		double xPosition = UNIT_SIZE-((4*(PRIME_DISPLAY_ROOM/5)));
		double yPosition = UNIT_SIZE-TOP_PADDING-PRIME_PADDING;
		while(primePositionCount>PRIMES_PER_ROW)
		{
			primePositionCount-=PRIMES_PER_ROW;
			yPosition-=(PRIME_PADDING);
		}
		while (primePositionCount>1)
		{
			primePositionCount--;
			xPosition+=(PRIME_DISPLAY_ROOM/5);
		}
		StdDraw.setPenColor(StdDraw.BLACK);
		//StdDraw.show(50);
		try {
		    Thread.sleep(50);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		StdDraw.text(xPosition,yPosition,""+primeNumber);
	}
	public static void fontSize(int largestInputtedNumber)
	{
		if(largestInputtedNumber<=20)
		{
			Font arial_italic_5pt = new Font("Arial", Font.ITALIC, 15);
			StdDraw.setFont(arial_italic_5pt);
		}
		else if (largestInputtedNumber<=100)
		{
			Font arial_italic_5pt = new Font("Arial", Font.ITALIC, 10);
			StdDraw.setFont(arial_italic_5pt);
		}
		else if (largestInputtedNumber<=350)
		{
			Font arial_italic_5pt = new Font("Arial", Font.ITALIC, 8);
			StdDraw.setFont(arial_italic_5pt);
		}
		else if (largestInputtedNumber<=500)
		{
			Font arial_italic_5pt = new Font("Arial", Font.ITALIC, 6);
			StdDraw.setFont(arial_italic_5pt);
		}
		else if (largestInputtedNumber<=700)
		{
			Font arial_italic_5pt = new Font("Arial", Font.ITALIC, 4);
			StdDraw.setFont(arial_italic_5pt);
		}
		else if (largestInputtedNumber<=1000)
		{
			Font arial_italic_5pt = new Font("Arial", Font.ITALIC, 3);
			StdDraw.setFont(arial_italic_5pt);
		}
		else
		{
			Font arial_italic_5pt = new Font("Arial", Font.ITALIC, 2);
			StdDraw.setFont(arial_italic_5pt);
		}
	}
	public static double scalingBoxes(int largestInputtedNumber)
	{
		int squareMaker = (int) Math.sqrt(largestInputtedNumber);
		double xAndYIncrement = (((UNIT_SIZE-(PRIME_DISPLAY_ROOM+LEFT_PADDING+LEFT_PADDING))/(squareMaker)));
		return xAndYIncrement;
	}
	public static Color findFactorColor(int primeCounter)
	{
		Color newColor = StdDraw.GRAY;
		switch(primeCounter)
		{
		case 1:
				newColor = StdDraw.BLUE;
				break;
		case 2:
				newColor = StdDraw.BOOK_LIGHT_BLUE;
				break;
		case 3:
				newColor = StdDraw.GREEN;
				break;
		case 4:
				newColor = StdDraw.MAGENTA;
				break;
		case 5:
				newColor = StdDraw.ORANGE;
				break;
		case 6:
				newColor = StdDraw.PINK;
				break;
		case 7:
				newColor = StdDraw.RED;
				break;
		default:
			Random generator = new Random();
			int red = generator.nextInt(255);
			int green = generator.nextInt(255);
			int blue= generator.nextInt(255);
			newColor = new Color(red,green,blue);
			break;
		}
		return newColor;
	}
}