import javax.swing.JOptionPane;

public class UmbrellaChecker {

	public static void main(String[] args) 
	{
		int rainyDayAnswer = JOptionPane.showConfirmDialog(null, "Is it raining or does it look like it might rain later on?",
																	"Umbrella Checker", JOptionPane.YES_NO_OPTION);
		boolean rainyDay = (rainyDayAnswer == JOptionPane.YES_OPTION);
		
		if (rainyDay)
		{
			JOptionPane.showMessageDialog(null, "You should take your umbrella for when you go out.");
			
			int rainingNowAnswer = JOptionPane.showConfirmDialog(null, "Is it raining now?", 
																		"Umbrella Checker", JOptionPane.YES_NO_OPTION);
			boolean rainingNow = (rainingNowAnswer==JOptionPane.YES_OPTION);
			
			if (rainingNow)
			{
				JOptionPane.showMessageDialog(null, "You should put up your umbrella.");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "You have no need to put up your umbrella.");
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "There is no need to take an umbrella today.");
		}	
	}
}
