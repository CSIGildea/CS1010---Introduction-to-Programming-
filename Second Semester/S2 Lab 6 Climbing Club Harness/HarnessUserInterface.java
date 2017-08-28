import java.util.Scanner;

public class HarnessUserInterface {

	public static void main(String[] args)
	{
		In harnessRecords = new In("harnesses.txt");
		HarnessRecords harnessRecordsOnFile = new HarnessRecords(harnessRecords);
		boolean finished = false;
		System.out.print("This program is used to  to keep track of and update "
				+ "\nthe current characteristics of each climbing club harness.\n");
		Scanner scanner = new Scanner(System.in);
		while(!finished)	
		{
			System.out.println("\nPlease enter would you like to:"
					+ "\n-'Add' a record for a newly purchased harness."
					+ "\n-'Remove' a climbing harness from the club"
					+"\n-'Checked', record a harness being checked by an instructor to ensure it is safe."
					+"\n-'Loan' a harness to a club member if there is any available harnesses"
					+"\n-'Return' a harness is no longer in use by a club member"
					+"\n-'Quit' the program");
				if((scanner.hasNext("add"))||(scanner.hasNext("Add")))
				{
					add(harnessRecordsOnFile,scanner);
				}
				else if((scanner.hasNext("remove"))||(scanner.hasNext("Remove")))
				{
					remove(harnessRecordsOnFile,scanner);
				}
				else if((scanner.hasNext("checked"))||(scanner.hasNext("Checked")))
				{
					checked(harnessRecordsOnFile,scanner);
				}
				else if((scanner.hasNext("loan"))||(scanner.hasNext("Loan")))
				{
					loan(harnessRecordsOnFile,scanner);
				}
				else if((scanner.hasNext("return"))||(scanner.hasNext("Return")))
				{
					returned(harnessRecordsOnFile,scanner);
				}
				else if((scanner.hasNext("quit"))||(scanner.hasNext("Quit")))
				{
					System.out.println("Goodbye.");
					finished = true;
				}
				else
				{
					System.out.print("Invalid input, please retry entering the correct instruction.\n");
					System.out.print("Please enter either 'Add','Remove','Checked','Loan','Return' or 'Quit'.");
					scanner.nextLine();
				}
		}
		scanner.close();
	}

	private static void returned(HarnessRecords harnessRecordsOnFile, Scanner scanner) {
		String instruction = "Please enter the harness make:";
		scanner.nextLine();
		String harnessMake = getName(scanner, instruction);
		instruction = "Please enter the harness model number:";
		int modelNumber = getNumber(scanner,instruction);
		if(harnessRecordsOnFile.returnHarness(harnessMake,modelNumber)!=(null))
		{
			System.out.print("\nSuccessful returned the harness with make "+ harnessMake+ " and model number "+modelNumber+".");
		}
		else
		{
			System.out.print("Unable to return the harness with make "+ harnessMake+ " and model number "+modelNumber+". Harness must not exist in our records.");
		}
	}

	private static void loan(HarnessRecords harnessRecordsOnFile, Scanner scanner) {
		String instruction = "Please enter the club member's name:";
		scanner.nextLine();
		String clubMembersName = getName(scanner, instruction);
		if(harnessRecordsOnFile.loanHarness(clubMembersName)!=(null))
		{
			System.out.print("\nSuccessful loaned a harness to "+ clubMembersName+".");
		}
		else
		{
			System.out.print("\nUnable to loan a harness to "+ clubMembersName+". All harnesses must be in use.");
		}
	}
	private static void checked(HarnessRecords harnessRecordsOnFile, Scanner scanner) {
		String instruction = "Please enter the instructors name:";
		scanner.nextLine();
		String instructorsName = getName(scanner, instruction);
		instruction = "Please enter the harness make:";
		String harnessMake = getName(scanner, instruction);
		instruction = "Please enter the model number:";
		int modelNumber = getNumber(scanner,instruction);
		if(harnessRecordsOnFile.checkHarness(instructorsName,harnessMake,modelNumber)!=(null))
		{
			System.out.print("\nSuccessful checked a harness with make "+harnessMake+" and model number "+modelNumber+" for safety by "+ instructorsName+".");
		}
		else
		{
			System.out.print("\nUnable to check a harness with make "+harnessMake+" and model number "+modelNumber+" for safety by "+ instructorsName+"."
					+ "\nThis harness must not exist or is currently on loan.\n");
		}
	}
	private static void remove(HarnessRecords harnessRecordsOnFile, Scanner scanner) {
		String instruction = "Please enter the harness make:";
		scanner.nextLine();
		String harnessMake = getName(scanner, instruction);
		instruction = "Please enter the model number:";
		int modelNumber = getNumber(scanner,instruction);
		if(harnessRecordsOnFile.removeHarness(harnessMake,modelNumber)!=(null))
		{
			System.out.print("\nSuccessfully removed a harness with make "+harnessMake+" and model number "+modelNumber+".");
		}
		else
		{
			System.out.print("\nUnable to remove a harness with make "+harnessMake+" and model number "+modelNumber+".\nThis harness must not exist or is on loan.");
		}
	}

	static void add(HarnessRecords harnessRecordsOnFile, Scanner scanner) {
		String instruction = "Please enter the harness make:";
		scanner.nextLine();
		String harnessMake = getName(scanner, instruction);
		instruction = "Please enter the model number:";
		int modelNumber = getNumber(scanner,instruction);
		instruction = "Please enter the instructors name:";
		String instructorsName = getName(scanner, instruction);
		Harness newHarness = new Harness(harnessMake,modelNumber,instructorsName);
		if(harnessRecordsOnFile.addHarness(newHarness)!=null)
		{
			System.out.print("\nSuccessfully added a harness with make "+harnessMake+", model number "+modelNumber+" bought by instructor "+ instructorsName+".");
		}
		else
		{
			System.out.print("\nUnable to add harness with make "+harnessMake+", model number "+modelNumber+" bought by instructor "+ instructorsName+"."
					+ " One must already exist with these details.");
		}
	}
	private static String getName(Scanner scanner,String instruction) {
		boolean finishedGettingName = false;
		while(!finishedGettingName)
		{
			System.out.println(""+instruction);
			if(scanner.hasNextLine())
			{
				String inputtedPlainText = scanner.nextLine();
				boolean validName = inputtedPlainText.matches("[a-zA-Z ]+");
				if (validName&&inputtedPlainText!="")
				{
					return inputtedPlainText;
				}
				else if (!validName)
				{
					System.out.println("Invalid input.");
				}
			}
			}
		return null;	
	}
	private static int getNumber(Scanner scanner,String instruction) {
		boolean finishedGettingNumber = false;
		while(!finishedGettingNumber)
		{
			System.out.println(""+instruction);
			String number = scanner.nextLine();
				boolean validNumber = number.matches("[0-9]+");
				if (validNumber)
				{
					return Integer.parseInt(number);
				}
				else if (!validNumber)
				{
					System.out.println("Invalid input.");
				}
			}
		return -1;
	}
}
