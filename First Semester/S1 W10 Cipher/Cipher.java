import java.util.Random;
import java.util.Scanner;

public class Cipher {
	public static final char[] ALPHABET  = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 
										'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',' '};
	public static void main(String[] args) 
	{
		boolean finished = false;
		char[] cipher = createCipher();
		System.out.print("This program encrypts and decrypts lowercase text.\n");
		Scanner scanner = new Scanner(System.in);
		while(!finished)	
		{
			System.out.println("Please enter whether you would like to 'encrypt','decrypt' or 'quit':");
			if((scanner.hasNext("encrypt"))||(scanner.hasNext("Encrypt")))
			{
				startEncryption(scanner,cipher);
			}
			else if((scanner.hasNext("decrypt"))||(scanner.hasNext("Decrypt")))
			{
				startDecryption(scanner,cipher);
			}
			else if((scanner.hasNext("quit"))||(scanner.hasNext("Quit")))
			{
				System.out.println("Goodbye.");
				finished = true;
			}
			else
			{
				System.out.print("Invalid input, please retry entering the correct instruction.\n");
				scanner.nextLine();
			}
		}
		scanner.close();
	}
	
	public static char[] createCipher()
	{
		char[] randomisedAlphabet = new char[ALPHABET.length];
		System.arraycopy( ALPHABET, 0, randomisedAlphabet, 0, ALPHABET.length);
	    Random generator = new Random();
	    for (int index=0; index<randomisedAlphabet.length; index++)
	    {
	    	int secondIndex = generator.nextInt(randomisedAlphabet.length);
	    	char temp = randomisedAlphabet[index];
	    	randomisedAlphabet[index] = randomisedAlphabet[secondIndex];
	    	randomisedAlphabet[secondIndex] = temp;
	    }
		return randomisedAlphabet;
	}
	
	
	public static String encrypt(char[] plainTextPhrase, char[] cipher)
	{
		char encryptedArray[] = new char[(plainTextPhrase.length)];
		for(int indexEncrypt = 0; plainTextPhrase.length>indexEncrypt; indexEncrypt++)
		{
			for (int indexAlphabet = 0; ALPHABET.length>indexAlphabet; indexAlphabet++)
			{
				if(plainTextPhrase[indexEncrypt]==ALPHABET[indexAlphabet])
				{
					encryptedArray[indexEncrypt]=cipher[indexAlphabet];
					indexAlphabet = ALPHABET.length; //To prevent unnecessary looping
				}
			}
		}
		String encryptedPhrase = new String( encryptedArray );
		return encryptedPhrase;
	}
	
	
	public static String crypt(char[] encryptedPhrase,char[] cipher)
	{
		char decryptedArray[] = new char[(encryptedPhrase.length)];
		for(int indexDecrypt = 0; encryptedPhrase.length>indexDecrypt; indexDecrypt++)
		{
			for (int indexCipher = 0; cipher.length>indexCipher; indexCipher++)
			{
				if(encryptedPhrase[indexDecrypt]==cipher[indexCipher])
				{
					decryptedArray[indexDecrypt]=ALPHABET[indexCipher];
					indexCipher = ALPHABET.length; //To prevent unnecessary looping
				}
			}
		}
		String decryptedPhrase = new String( decryptedArray );
		return decryptedPhrase;
	}
	
	
	
	public static void startEncryption(Scanner scanner,char[] cipher)
	{
		boolean finishedEncryption = false;
		scanner.nextLine();
		while(!finishedEncryption)
		{
			System.out.println("This program will now encrypt any lowercase plain text phrase (containing no numbers or special characters)."
					+ "\nPlease enter your plain text lowercase phrase you would like to encrypt:");
			String inputtedPlainText = scanner.nextLine().toLowerCase();
			char[] plainTextPhrase = inputtedPlainText.toCharArray();
			boolean encryptable = isValidInput(plainTextPhrase);
			if (encryptable)
			{
				System.out.println("Your text encrypted is:"+encrypt(plainTextPhrase, cipher));
				finishedEncryption = true;
			}
			else if (!encryptable)
			{
				System.out.println("Invalid input.");
			}
		}
	}
	
	public static void startDecryption(Scanner scanner,char[] cipher)
	{
		scanner.nextLine();
		boolean finishedDecryption = false;
		while(!finishedDecryption)
		{
			System.out.println("This program will now decrypt any lowercase plain text phrase (containing no numbers or special characters)."
					+"\nPlease enter your plain text lowercase phrase you would like to decrypt:");
			String inputtedEncryptedText = scanner.nextLine().toLowerCase();
			char[] encryptedPhrase = inputtedEncryptedText.toCharArray();
			boolean decryptable = isValidInput(encryptedPhrase);
			if (decryptable)
			{
				System.out.println("Your text decrypted is:"+crypt(encryptedPhrase, cipher));
				finishedDecryption = true;
			}
			else
			{
				System.out.println("Invalid Input.");
			}
		}
	}
	
	public static boolean isValidInput(char[] plainTextPhrase)
	{
		
		for(int indexPlainText= 0; plainTextPhrase.length>indexPlainText ; indexPlainText++)
		{
			for(int indexAlphabet = 0; ALPHABET.length>indexAlphabet; indexAlphabet++)
			{
				if((plainTextPhrase[indexPlainText])==(ALPHABET[indexAlphabet]))
				{
					indexAlphabet = ALPHABET.length; //To prevent unnecessary looping
				}
				else
				{
					if(indexAlphabet+1==(ALPHABET.length))
					{
						return false;
					}
				}	
			}
		}
		return true;
	}
}
