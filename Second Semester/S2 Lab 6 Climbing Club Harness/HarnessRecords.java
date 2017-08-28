import java.util.ArrayList;

public class HarnessRecords {
	private ArrayList<Harness> harnessList;
	private int harnessRecordsValue=0;
	
	HarnessRecords()
	{
		harnessList = new ArrayList<Harness>();
	}
	HarnessRecords(In harnessRecords)
	{
		if (harnessRecords.hasNextLine())
		{			
			String harnesses = harnessRecords.readAll();
			String[] harnessArray = harnesses.split("-");
			harnessList = new ArrayList<Harness>();
			for(int index=0;index<harnessArray.length;index++)
			{
				String[] harnessDetails = harnessArray[index].split(",");
				String make = harnessDetails[0];
				int modelNumber = Integer.parseInt(harnessDetails[1]);
				String lastInstructorSafetyCheck = harnessDetails[2];
				if(harnessDetails.length>3)
				{
					int numberOfTimesUsed = Integer.parseInt(harnessDetails[3]);
					boolean harnessOnLoan = harnessDetails[4].equals("false")? false:true;
					String clubMemberBorrowing = harnessDetails[5];
					Harness newHarness = new Harness(make,modelNumber,numberOfTimesUsed,lastInstructorSafetyCheck,harnessOnLoan,clubMemberBorrowing);
					this.harnessList.add(newHarness); 
					harnessRecordsValue++;
				}
				else
				{
					Harness newHarness = new Harness(make,modelNumber,lastInstructorSafetyCheck);
					harnessList.add(newHarness); 
					harnessRecordsValue++;
				}
			}
		}
	}
	public boolean isEmpty() {
		if (this.harnessList.size()<=0)
		{
			return true;
		}
		else
		{
			return false;
		}	
	}
	Harness addHarness(Harness newHarness) {
		Harness comparisionHarness;
		for (int index=0; index<harnessList.size(); index++) 
		{
			comparisionHarness = harnessList.get(index);

			if (newHarness.getMake().equals(comparisionHarness.getMake()) && newHarness.getModelNumber() == comparisionHarness.getModelNumber())
			{
				System.out.print("\nAnother harness with the same model and model number already exists."
						+ "Unable to add harness with identical make and model number.");
				return null;
			}
		}
		harnessList.add(newHarness);
		harnessRecordsValue++;
		return newHarness;
	}
	Harness findHarness(String make, int modelNumber) {
		
		if (!isEmpty())
		{
			for (int index=0; index<harnessList.size(); index++) {
				Harness comparisionHarness = harnessList.get(index);
				
				if (make.equals(comparisionHarness.getMake()) && modelNumber == comparisionHarness.getModelNumber())
				{
					return comparisionHarness;
				}					
			}
		}
		return null;
	}
	Harness checkHarness(String instructorsName,String harnessMake,int modelNumber)
	{
		if (!isEmpty())
		{
			Harness harnessToCheck = findHarness(harnessMake,modelNumber);
			if(harnessToCheck!=(null))
			{
				if(!harnessToCheck.isHarnessOnLoan())
				{
					harnessToCheck.checkHarness(instructorsName);
					return harnessToCheck;
				}
				else
				{
					return null;
				}
			}
			else
			{
				return null;
			}
		}
		else
		{
			return null;
		}
	}
	Harness loanHarness(String clubMemberName)
	{
		for (int index=0; index<harnessList.size(); index++)
		{
			if(harnessList.get(index).canHarnessBeLoaned())
			{
				harnessList.get(index).loanHarness(clubMemberName);
				this.harnessRecordsValue--;
				System.out.print(harnessList.get(index).toString());
				return harnessList.get(index);
			}
		}
		return null;
	}
	Harness returnHarness(String make, int modelNumber)
	{
		Harness harnessToReturn = findHarness(make,modelNumber);
		if(harnessToReturn!=(null))
		{
			harnessToReturn.returnHarness();
			return harnessToReturn;
		}
		else
		{
			return null;
		}
	}
	Harness removeHarness(String make,int modelNumber)
	{
		if (!isEmpty())
		{
			Harness harnessToRemove= findHarness(make,modelNumber);
			if(harnessToRemove!=(null))
			{
				   for(int index=0;index<harnessList.size();index++)
				   {
				        if(harnessList.get(index).getMake().equals(make)&&(harnessList.get(index).getModelNumber()==modelNumber)&&(harnessList.get(index).canHarnessBeLoaned()))
				        {
				        	harnessList.remove(index);
				        	return harnessToRemove;
				        }
				   }
			}
			else
			{
				return null;
			}
		}
		else
		{
			return null;
		}
		return null;
	}
}

