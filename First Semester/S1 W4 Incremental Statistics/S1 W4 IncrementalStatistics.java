import java.util.Scanner;

public class IncrementalStatistics {

	public static void main(String[]args){
		double numberEntered=0;
		double average=0;
		double oldAverage=0;
		double variance=0;
		int count=1;
		int oldCount=0;

		System.out.println("This program computes the average and variance of all numbers entered.\n"
							+ "Enter a number(or type 'exit'):");
		Scanner numberInput = new Scanner (System.in);
		while (numberInput.hasNextDouble())
		{		
			numberEntered = numberInput.nextDouble();
			average = (oldAverage)+(numberEntered-oldAverage)/count;
			variance = ((variance*(oldCount)+(numberEntered-oldAverage)*(numberEntered-average))/count);
			System.out.println("So far the average is " + average + " and the variance is "+ variance);
			System.out.println("Enter a number(or type 'exit'):");
			oldAverage = average;
			oldCount=count;
			count++;
		}
		if((numberInput.hasNext("exit"))||(numberInput.hasNext("quit")))
		{
			System.out.println("Goodbye.");
		}
		else
		{
			System.out.println("You have entered an invalid input, please restart and try again.");
		}
		numberInput.close();
	}
}
