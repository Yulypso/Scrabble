import java.util.*;
import java.text.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Dictionary {

	private String[] wordList = new String[354491];

	public Dictionary(String filename) throws FileNotFoundException {

		File file = new File("./"+filename);
		int cpt = 0;

		if(!file.exists()) {

  			throw new FileNotFoundException("Can't find "+file.getPath());

  		}else {

			Scanner scan = new Scanner(file);

			try {
				while(scan.hasNext()) {
					//System.out.println(scan.next());
					wordList[cpt] = scan.next();
					cpt++;
				}

				/*for(String s : wordList) {
					System.out.println(s);
				}*/

			} catch(RuntimeException e) {

				System.err.println(e.getMessage());
				System.exit(-1);
			}
  		}
		

	}

	public boolean isValidWord(String word) {
		boolean a = false;

		int position = Arrays.binarySearch(wordList, word);
		
		if(position >= 0) {
			a = true;
		} else {
			a = false;
		}
		return a;
	}
		
	public static boolean mayBeComposed(String word, char[] letters) {
		boolean[] isUsed = new boolean[letters.length]; //same size as letters
		Arrays.fill(isUsed, false); //set all elements to false
		
		int cpt = 0;
		int nbjoker = 0;

		for(int p = 0; p<letters.length; p++) {
			if(letters[p] == '*') {
				nbjoker++;
			}
		}
		
		for(int i=0; i<word.length(); i++) {
			for(int j=0; j<letters.length; j++) {

				if(word.charAt(i) == letters[j] && isUsed[j] == false) {
					isUsed[j] = true;
					cpt++;
					break;
				}
			}
		}

		if(word.length()-nbjoker <= cpt) {
			return true;
		} else {
			return false;
		}
	}

	public static String replaceFrenchCharacter(String s) {
		char c;

		String a = s.replaceAll("à", "a")
					.replaceAll("â", "a")
					.replaceAll("ä", "a")
					.replaceAll("ç", "c")
					.replaceAll("é", "e")
					.replaceAll("è", "e")
					.replaceAll("ê", "e")
					.replaceAll("ë", "e")
					.replaceAll("î", "i")
					.replaceAll("ï", "i")
					.replaceAll("ô", "o")
					.replaceAll("ö", "o")
					.replaceAll("œ", "oe")
					.replaceAll("æ", "ae");
		return a;
	}

	public List<String> getWordsThatCanBeComposed(char[] letters) {

		LinkedList<String> list = new LinkedList<String>(); 


		for(String s : wordList) {
			String a = replaceFrenchCharacter(s);

			if(mayBeComposed(a.toLowerCase(), letters) == true) {
				list.add(s);
			}
		}

		return list;
	}

	/*public static char[] getComposition(String word, char[] letters) {

		char[] myArray = new char[letters.length];
		int cpt = 0;
		int j=0;

		for(char c : letters) {
			for(int i=0; i<word.length(); i++) {

				if(c == word.charAt(i)) {
					myArray[j] = c;
					j++;
					cpt++;
				}
			}
		}

		int nbJokerARajouter = letters.length - cpt;

		for(j = j; j<nbJokerARajouter; j++) {
			myArray[j] = '*';
		}

		return myArray;
	}*/

	public static char[] getComposition(String word, char[] letters) {

		boolean[] isUsed = new boolean[letters.length]; //same size as letters
		Arrays.fill(isUsed, false); //set all elements to false
		
		int cpt = 0;
		int nbjoker = 0;

		for(int p = 0; p<letters.length; p++) {
			if(letters[p] == '*') {
				nbjoker++;
			}
		}
		
		for(int i=0; i<word.length(); i++) {
			for(int j=0; j<letters.length; j++) {

				if(word.charAt(i) == letters[j] && isUsed[j] == false) {
					isUsed[j] = true;
					cpt++;
					break;
				}
			}
		}

		char[] myArray = new char[word.length()];
		int nbLetters = 0;
		int p =0;


		if(word.length()-nbjoker <= cpt) {
			for(int k=0; k<isUsed.length; k++) {
				if(isUsed[k] == true) {
					nbLetters++;
					myArray[p]=letters[k];
					p++;
				}
			}

			int nbJokerARajouter = word.length() - nbLetters;

			for(int o = 0; o<nbJokerARajouter; o++) {
				myArray[p] = '*';
				p++;
			}

			return myArray;
		} else {
			return null;
		}
	}
}