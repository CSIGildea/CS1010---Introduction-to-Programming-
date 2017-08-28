
public class TriangularStars {
	
	public static final int zeroWraparound = 0;

	public static void main(String[] args) 
	{
		int number = 1;
		boolean reachedMaxInt=false;
		int triangularNumber = 0;
		int lastTriangularNumber = 0;
		
		while (!reachedMaxInt)
		{
			triangularNumber = (lastTriangularNumber)+ number;
			//If the new triangular number is less than the last triangular number, 
			//the ints have wrapped around, we need to end the program.
			if (triangularNumber<lastTriangularNumber)
			{
				reachedMaxInt = true;
			}
			if(isStarNumber (triangularNumber))
			{
				System.out.println(""+triangularNumber);
			}
			lastTriangularNumber = triangularNumber;
			number++;
		}
	}
	public static boolean isStarNumber(int number)
	{
		boolean notFinished = true;
		boolean starNumber = false;
		int starNumberTest= 1;
		int numberTest = 1;
		while ((notFinished))
		{
			starNumberTest = determineStarNumber(numberTest);
			if (starNumberTest == number)
			{
				notFinished = false;
				starNumber = true;
				return starNumber;
			}
			else if (number<starNumberTest)
			{
				notFinished = false;
				starNumber = false;
				return starNumber;
			}
			numberTest++;
		}
		return starNumber;
	}
	public static int determineStarNumber(int number)
	{
		int starNumber = (((6)*(number))*(number-1))+1;
		return starNumber; 
	}
}
