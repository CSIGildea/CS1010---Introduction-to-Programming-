import java.util.Scanner;
import javax.swing.JOptionPane;

public class DayOfTheWeek {
	
	final static String[] MONTH_NAMES = {"January","February","March","April", "May", "June","July",
										"August","September","October","November","December"};
	final static String[] DAY_NAMES = {"Sunday","Monday","Tuesday","Wednesday", 
									   "Thursday", "Friday","Saturday"};
	public static final int DAYS_IN_APRIL_JUNE_SEPT_NOV = 30;
	public static final int DAYS_IN_FEBRUARY_NORMALLY = 28;
	public static final int DAYS_IN_FEBRUARY_IN_LEAP_YEAR = 29;
	public static final int DAYS_IN_ALL_OTHER_MONTHS = 31;
	public static final int NUMBER_OF_MONTHS = 12;
	public static final int DAYS_IN_A_WEEK = 7;
	public static final int ELEVENTH = 11;
	public static final int TWELFTH = 12;
	public static final int THIRTEENTH = 13;
	public static final int JANUARY = 1;
	public static final int FEBRUARY = 2;
	public static final int APRIL = 4;
	public static final int JUNE = 6;
	public static final int SEPTEMBER = 9;
	public static final int NOVEMBER = 11;
	public static final int LOWEST_POSSIBLE_DAY_VALUE = 0;
	
	public static void main(String[] args) {
		
		// The following functions validDate(), daysInMonth () and isLeapYear (), was
		// written by Dr. Kenneth Dawson-Howe and the Teaching Assistants within CS1010, at Trinity College Dublin.
		
		try
		{
			String userInput = JOptionPane.showInputDialog("Enter your date (day/month/year):");
			Scanner scanner = new Scanner( userInput );
			scanner.useDelimiter("/");
			int day = scanner.nextInt();
			int month = scanner.nextInt();
			int year = scanner.nextInt();

			if (validDate( day, month, year))
			{
				JOptionPane.showMessageDialog(null, dayOfTheWeek(day,month,year)+", "+ day + numberEnding(day) 
												+ " " + monthName(month) + " "+ year) ;
			}
			else
			{
				JOptionPane.showMessageDialog(null, "" + day + "/" + month + "/" + year + " is not a valid date.",
																				"Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		catch (NullPointerException exception)
		{
		}
		catch (java.util.NoSuchElementException exception)
		{
			JOptionPane.showMessageDialog(null, "No number has been entered.",
								"Error", JOptionPane.ERROR_MESSAGE);
		}
	
	}
	
	//This code for this function validDate() was written by Dr. Kenneth Dawson-Howe.
	public static boolean validDate( int day, int month, int year)
	{
		return ((year >= 0) && (month >= 1) && (month <= NUMBER_OF_MONTHS) &&
				(day >= 1) && (day <= daysInMonth( month, year )));
	}
	
	//This code for this function daysInMonth() was written by Dr. Kenneth Dawson-Howe.
	public static int daysInMonth( int month, int year )
	{
		int numberOfDaysInMonth = DAYS_IN_ALL_OTHER_MONTHS;
		switch (month)
		{
		case FEBRUARY:
			numberOfDaysInMonth = isLeapYear( year ) ? DAYS_IN_FEBRUARY_IN_LEAP_YEAR :
				                                       DAYS_IN_FEBRUARY_NORMALLY;
			break;
		case APRIL:
		case JUNE:
		case SEPTEMBER:
		case NOVEMBER:
			numberOfDaysInMonth = DAYS_IN_APRIL_JUNE_SEPT_NOV;
			break;
		default:
			numberOfDaysInMonth = DAYS_IN_ALL_OTHER_MONTHS;
			break;
		}
		return numberOfDaysInMonth;
	}
	
	//This code for this function isLeapYear() was written by Dr. Kenneth Dawson-Howe.
	public static boolean isLeapYear( int year )
	{
		return (((year%4 == 0) && (year%100 != 0)) || (year%400 == 0));
	}
	
	public static String numberEnding(int day)
	{	
		String numberEnding = "";
		int digitEnd = day%10;
		
		
		switch (digitEnd)
		{
		case 1:
				numberEnding = (day==ELEVENTH)? "th": "st";
				break;
		case 2:
				numberEnding = (day==TWELFTH)? "th": "nd";
				break;
		case 3:
				numberEnding = (day==THIRTEENTH)? "th": "rd";
				break;
		default:
				numberEnding = "th";
				break;
		}
		return numberEnding;
	}
	
	public static String monthName(int month)
	{
		String monthName = "";
		monthName = MONTH_NAMES[month-1];
		return monthName;
	
	}
	
	public static String dayOfTheWeek( int day, int month, int year)
	{
		String dayOfTheWeekName = "";
		int inputtedYear = year;
		int dayOfTheWeekNo = 0;
		if (month==JANUARY||month==FEBRUARY)
		{
			inputtedYear = year-1;
		}
		int lastTwoDigitsOfYear = inputtedYear%100;
		int firstTwoDigitsOfYear = inputtedYear/100;
		dayOfTheWeekNo = (int) ((day + Math.floor((2.6*(((month+9)%12)+1)-0.2)) + lastTwoDigitsOfYear + Math.floor(lastTwoDigitsOfYear/4)
								+ Math.floor(firstTwoDigitsOfYear/4) - (2*(firstTwoDigitsOfYear)))%(7));
		if (dayOfTheWeekNo<LOWEST_POSSIBLE_DAY_VALUE)
		{
			dayOfTheWeekNo+=DAYS_IN_A_WEEK;
		}
		dayOfTheWeekName = DAY_NAMES[dayOfTheWeekNo];
		return dayOfTheWeekName;
	}
}