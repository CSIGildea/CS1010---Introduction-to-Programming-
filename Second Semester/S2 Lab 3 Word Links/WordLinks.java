import java.util.Arrays;
import java.util.Scanner;

public class WordLinks {

	public static void main(String[] args) 
	{
		boolean finished = false;
		Scanner scanner = new Scanner(System.in);
		String inputtedText;
		while(!finished)	
		{
			System.out.println("Enter a comma separated list of words (or an empty list to quit):");
			inputtedText = scanner.nextLine();
			if(inputtedText.isEmpty())
			{
				System.out.println("Goodbye.");
				finished = true;
			}
			else if(isWordChain(readWordList(inputtedText)))
			{
				System.out.println("Valid chain of words from Lewis Carroll's word-links game.");
			}
			else
			{
				System.out.println("Not a valid chain of words from Lewis Carroll's word-links game.");
				
			}
		}
	}
	public static String[] readDictionary()
	{
		In dictionaryInput = new In("words.txt");
	    String dictionary = dictionaryInput.readAll();
	    String[] wordDictionary = dictionary.split("\n");
		return wordDictionary;
	}
	public static String[] readWordList(String inputtedList)
	{
	    String fullInputtedList = inputtedList;
		String[] wordInput = fullInputtedList.split(",\\ |\\,| ");
		return wordInput;		
	}
	public static boolean isUniqueList(String[] inputtedList)
	{
		for(int index = 0;index<inputtedList.length;index++)
		{
			for(int comparisionIndex = 1;comparisionIndex<inputtedList.length;comparisionIndex++)
			{
				if(index!=comparisionIndex)
				{
					if(inputtedList[index].equals(inputtedList[comparisionIndex]))
					{
						return false;
					}
				}
			}
		}
		return true;
	}
	public static boolean isEnglishWord(String testWord)
	{
		if(Arrays.binarySearch(readDictionary(),testWord)>0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public static boolean isDifferentByOne(String comparisionWord,String testWord)
	{
		String comparisionWordLowerCase = comparisionWord.toLowerCase();
		char[] comparisionCharWord = comparisionWordLowerCase.toCharArray();
		
		String testWordLowerCase = testWord.toLowerCase();
		char[] testCharWord = testWordLowerCase.toCharArray();
		
		if(comparisionCharWord.length!=testCharWord.length)
		{
			return false;
		}
		int letterDifferences = 0;
		for(int index = 0; index<comparisionCharWord.length; index++)
		{
			if(comparisionCharWord[index]==testCharWord[index])
			{	
			}
			else
			{
				letterDifferences++;
			}
			if(letterDifferences>1)
			{
				return false;
			}
		}
		return true;		
	}
	public static boolean isWordChain(String[] listOfWords)
	{
		String[] dictionary = readDictionary();
		if(isUniqueList(listOfWords))
		{
			boolean validWord;
			boolean validWordLink;
			int prevIndex = 0;
			for(int index=0; index<listOfWords.length;index++)
			{
				validWord = isEnglishWord(listOfWords[index]);
				if(index!=0)
				{
					validWordLink = isDifferentByOne(listOfWords[prevIndex],listOfWords[index]);
					if(validWordLink==false)
					{
						return false;
					}
				}
				if(validWord==false)
				{
					return false;
				}
				prevIndex = index;
			}
		}
		else
		{
			return false;
		}
		return true;
	}
}

