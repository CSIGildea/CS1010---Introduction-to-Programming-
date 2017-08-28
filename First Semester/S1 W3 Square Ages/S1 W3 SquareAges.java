import java.util.Calendar;

public class SquareAges
{ 
	public static final int MAX_LIVING_YEARS = 123;
	
	public static void main(String[] args)
	{
	int squareAgeYear;
	int year = Calendar.getInstance().get(Calendar.YEAR);
	
		for (int personsAge = 0; (personsAge<=MAX_LIVING_YEARS); personsAge++)
		{
		squareAgeYear = personsAge * personsAge;
			//if possible to be alive in 2016 (Born before 2016)
			if ((squareAgeYear-personsAge>=(year-MAX_LIVING_YEARS)) && (squareAgeYear-personsAge<=year))
			{
			System.out.println("Anyone turning " + personsAge + " years old," + " in the year " + squareAgeYear 
							+ ", will experience a square age year. This means they were born in "+ (squareAgeYear-personsAge) + ".");
			}
		}
	}
}
