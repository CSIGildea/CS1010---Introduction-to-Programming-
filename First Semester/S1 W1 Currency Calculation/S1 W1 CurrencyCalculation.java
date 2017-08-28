import java.util.Scanner;
import javax.swing.JOptionPane;

public class CurrencyCalculation {
	
	public static final int OLD_SHILLINGS_PER_OLD_POUND = 20;
	public static final int OLD_PENNIES_PER_OLD_SHILLING = 12;
	public static final int NEW_PENNIES_PER_OLD_PENNY = 67;
	public static final int NEW_PENNIES_IN_NEW_POUND = 100;
	
	public static void main(String[] args) {

		String oldCurrencyInput = JOptionPane.showInputDialog("Please enter the amount of old pounds, followed by the amount of old shillings, "
															+ "followed by the amount of old pennies you possess." + "\nInclude a space between "
															+ "each figure." + "\n" + "\nE.G." + "\n3 2 5 = 3 old pounds, 2 old shillings and 5 old pence." 
															+ "\n1 0 0 = 1 old pound, 0 old shillings and 0 old pence");
		Scanner inputScanner = new Scanner( oldCurrencyInput );
		int userOldPounds = inputScanner.nextInt();
		int userOldShillings = inputScanner.nextInt();
		int userOldPennies = inputScanner.nextInt();
		inputScanner.close();
		
		int totalNewPennies = userOldPounds * OLD_SHILLINGS_PER_OLD_POUND * OLD_PENNIES_PER_OLD_SHILLING * NEW_PENNIES_PER_OLD_PENNY 
							   + userOldShillings * OLD_PENNIES_PER_OLD_SHILLING * NEW_PENNIES_PER_OLD_PENNY 
							   + userOldPennies * NEW_PENNIES_PER_OLD_PENNY;
		int newPennyValue = totalNewPennies%NEW_PENNIES_IN_NEW_POUND;
		int newPoundValue = totalNewPennies/NEW_PENNIES_IN_NEW_POUND;
		
		JOptionPane.showMessageDialog(null,"Your old currency is now worth £" + newPoundValue + " and " + newPennyValue + "p.");
	}

}