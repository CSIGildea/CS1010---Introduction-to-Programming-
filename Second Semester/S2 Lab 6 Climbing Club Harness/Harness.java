
public class Harness {

	private String make;
	private int modelNumber;
	private int numberOfTimesUsed;
	private String lastSafetyCheckInstructor;
	private boolean onLoan;
	private String clubMemberBorrowing;

	Harness(String make,int modelNumber,int numberOfTimesUsed, String lastSafetyCheckInstructor,boolean onLoan,String clubMemberBorrowing) 
	{
		this.make = make;
		this.modelNumber = modelNumber;
        if (numberOfTimesUsed < 0) 
        {
            throw new IllegalArgumentException("The number of times used since a safety inspection cannot be below zero.");
        }
		this.numberOfTimesUsed = numberOfTimesUsed;
		this.lastSafetyCheckInstructor = lastSafetyCheckInstructor;
		this.onLoan = onLoan;
		if(onLoan)
		{
			this.clubMemberBorrowing = clubMemberBorrowing;
		}
		else if(!onLoan)
		{
			this.clubMemberBorrowing = null;
		}
	}
	
	Harness(String make,int modelNumber,String lastSafetyCheckInstructor) 
	{
		this.make = make;
		this.modelNumber = modelNumber;
		this.numberOfTimesUsed = 0;
		this.lastSafetyCheckInstructor = lastSafetyCheckInstructor;
		this.onLoan = false;
		this.clubMemberBorrowing = null;
	}
	
	void checkHarness(String lastSafetyCheckInstructorName)
	{
		if(lastSafetyCheckInstructorName!=null)
		{
			if(!this.onLoan)
			{
				this.lastSafetyCheckInstructor = lastSafetyCheckInstructorName;
				this.numberOfTimesUsed = 0;
			}
		} 
		else 
		{
			throw new NullPointerException();
		}
	}
	
	boolean isHarnessOnLoan()
	{
		return this.onLoan;
	}
	
	boolean canHarnessBeLoaned()
	{
		if (!this.isHarnessOnLoan())
		{
			if(this.numberOfTimesUsed<25)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	void loanHarness(String clubMemberName)
	{
		if(clubMemberName != null)
		{
			if(this.canHarnessBeLoaned())
			{

				this.onLoan = true;
				this.clubMemberBorrowing = clubMemberName;

			}
		}
		else
		{
			throw new NullPointerException();
		}
	}
	void returnHarness()
	{
		if(this.isHarnessOnLoan())
		{
			this.onLoan = false;
			this.numberOfTimesUsed++;
			this.clubMemberBorrowing = null;
		}
	}
	public String toString()
	{
		String harnessDescription="";
		harnessDescription+="Make: "+this.make+"\nModel Number: "+this.modelNumber;
		if(this.isHarnessOnLoan())
		{
			harnessDescription+= "\nThis harness is on loan to :"+this.clubMemberBorrowing;	
		}
		else if(!this.isHarnessOnLoan())
		{
			harnessDescription+= "\nThis harness is not loan";
		}
		harnessDescription += "\nThis harness has been used: "+this.numberOfTimesUsed+" times.";
		harnessDescription +=  "\nThis harness was last safety checked by the instructor: "+this.lastSafetyCheckInstructor+"\n";
		return harnessDescription;
	}
	
	//Extra Method, in case we wanted to save the data we are adjusting, in the future. Unnecessary for this assignment.
	public String toSaveableString()
	{
		String harnessDescription="";
		harnessDescription+=this.make+","+this.modelNumber+","+this.numberOfTimesUsed+","+this.onLoan+",";
		if(this.isHarnessOnLoan())
		{
			harnessDescription+= ","+this.clubMemberBorrowing;	
		}
		else if(!this.isHarnessOnLoan())
		{
			harnessDescription+= ",null";
		}
		harnessDescription = ","+this.numberOfTimesUsed;
		harnessDescription = ","+this.lastSafetyCheckInstructor+"-";
		return harnessDescription;
	}
	String getMake(){
		return this.make;
	}
	int getModelNumber(){
		return this.modelNumber;
	}
	int getNumberOfTimesUsed(){
		return this.numberOfTimesUsed;
	}
	String getLastSafetyInspector(){
		return this.lastSafetyCheckInstructor;
	}
	String getClubMemberBorrowingHarness(){
		return this.clubMemberBorrowing;
	}
}
