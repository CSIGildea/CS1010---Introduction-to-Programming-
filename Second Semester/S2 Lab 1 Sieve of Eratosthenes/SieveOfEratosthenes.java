import java.util.Scanner;

import javax.swing.JOptionPane;

public class SieveOfEratosthenes{

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

	public static boolean[] crossOutHigherMultiples(boolean[] sequenceOfNumbers, int factor)
	{
		boolean[] isPrime = new boolean[sequenceOfNumbers.length];
		System.arraycopy( sequenceOfNumbers, 0, isPrime, 0, sequenceOfNumbers.length );
		for(int count = factor;(factor*count)<= sequenceOfNumbers.length+1;count++)
		{
			isPrime[((count*factor)-2)]=false;
			//Canceling non prime values via multiplication E.g. 2x2,2x3....etc
			//We minus 2 as isPrime[0] represents the value 2.
		}
		return isPrime;

	}
	public static boolean[] sieve(int inputtedNumber)
	{
		boolean[] sequenceOfNumbers = createSequence(inputtedNumber);
		System.out.println(""+sequenceToString(sequenceOfNumbers));
		for(int factor = 2;(factor<=(Math.sqrt(inputtedNumber)));factor++)
		{
			if(sequenceOfNumbers[factor-2]==true)
			{
				sequenceOfNumbers=crossOutHigherMultiples(sequenceOfNumbers, factor);
				System.out.println(""+sequenceToString(sequenceOfNumbers));
			}
		}
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
}