import java.util.*; 
import java.text.*;
import java.lang.*; 
import java.io.*; 

public class ScrabbleComparator implements Comparator<String> {

	private char[] letters;

	public ScrabbleComparator(char[] letters) {

		this.letters = letters;
	}

	public static int letterValue(char letter) {

		int value = 0;

		if(letter == '*'){
			value = 0;
		}
		else if(letter == 'e' || letter == 'a' || 
			    letter == 'i' || letter == 'n' || 
			    letter == 'o' || letter == 'r' || 
			    letter == 's' || letter == 't' || 
			    letter == 'u' || letter == 'l'){
			value = 1;
		}
		else if(letter == 'd' || letter == 'm' || 
			    letter == 'g') {
			value = 2;
		}
		else if(letter == 'b' || letter == 'c' || 
			    letter == 'p') {
			value = 3;
		}	
		else if(letter == 'f' || letter == 'h' || 
			    letter == 'v') {
			value = 4;
		}
		else if(letter == 'j' || letter == 'q') {
			value = 8; 
		}
		else if(letter == 'k' || letter == 'w' || 
			    letter == 'x' || letter == 'y' || 
			    letter == 'z') {
			value = 10;
		}

		//System.out.println(value);
		return value;
	}

	public static int lettersValue(char[] letters) {

		int myLettersValues = 0;

		for(char c : letters) {
			myLettersValues = myLettersValues + letterValue(c);
		}

		return myLettersValues;
	}


	public int wordValue(String word) {
		char[] letters = word.toCharArray();
		int value = 0;

		value = lettersValue(letters);

		return value;
	}

	public int compare(String s1, String s2) {

		String sComp1 = s1.toLowerCase();
		String sComp2 = s2.toLowerCase();

		String sComp3 = Dictionary.replaceFrenchCharacter(sComp1);
		String sComp4 = Dictionary.replaceFrenchCharacter(sComp2);

		char[] sComp5 = Dictionary.getComposition(sComp3, letters);
		char[] sComp6 = Dictionary.getComposition(sComp4, letters);

		String sComp7 = String.valueOf(sComp5);
		String sComp8 = String.valueOf(sComp6);

		int valueS1 = wordValue(sComp7);
		int valueS2 = wordValue(sComp8);

		if(valueS1 > valueS2)
			return -1;
		else if(valueS1 == valueS2)
			return 0;
		else 
			return 1;
	}
}