import java.util.*;
import java.text.*;
import java.io.FileNotFoundException;

public class ScrabbleConsole {

	public ScrabbleConsole() throws FileNotFoundException {
		System.out.println("Welcome to the Scrabble assistant");

		Dictionary d = new Dictionary("fr_FR_utf8.dico");
		
		Scanner scan = new Scanner(System.in);
	//System.out.println("Please enter a word:");
	//String word = scan.next();

		System.out.println("Please enter a letter list:");
		String letterList = scan.next();

		/* permet de bien convertir les caracteres speciaux */ 
		//String outputword1 = d.replaceFrenchCharacter(word); //word modifié
	String outputletters1 = d.replaceFrenchCharacter(letterList); //letterList moifiée
		
		/* permet de bien reduire en minuscule les String */ 
		//String outputword = outputword1.toLowerCase();
	String outputletters = outputletters1.toLowerCase();

	char[] letters = outputletters.toCharArray();

		/* --------------------------------------------------*/

		/*if(d.mayBeComposed(outputword, letters) == true) {
			System.out.println(word+" may be composed with letters : "+ Arrays.toString(letters));
		}*/

////////////////

	List<String> mylist = d.getWordsThatCanBeComposed(letters);
	//System.out.println(mylist.size() + " : " + mylist);
	
////////////////// comparateur

	String[] wordList = new String[mylist.size()];
	mylist.toArray(wordList);

	ScrabbleComparator sc = new ScrabbleComparator(letters);
	Arrays.sort(wordList, sc);

//////////////// composition

	System.out.println(wordList.length + " matching word(s) found : ");

	for(String s : wordList) {
			String p = d.replaceFrenchCharacter(s);
			
			String q = p.toLowerCase();

			char[] a = d.getComposition(q, letters); 
			String aTemp = String.valueOf(a);

			//System.out.println(aTemp);

			int value = sc.wordValue(aTemp);
			//System.out.println(value);

			System.out.println(s +" ("+ Arrays.toString(a)+ " " + value +") ");
	}


// permet de trier selon les points, fonctionne parfaitemant sauf pour les cas avec joker 
// on a le tri, mais pas les mots correctement affiché ! en effet le tri s'effectue grace à la 
// considération des jokers. 
	
	
	/*List<String> mylist = d.getWordsThatCanBeComposed(letters);
	//System.out.println(mylist.size() + " : " + mylist);
	
////////////////// comparateur

	String[] wordList = new String[mylist.size()];
	mylist.toArray(wordList);

	ScrabbleComparator sc = new ScrabbleComparator(letters);
	//Arrays.sort(wordList, sc);

//////////////// composition
	String[] wordListJoker = new String[mylist.size()];
	int l = 0;


	for(String s : wordList) { 
			String p = d.replaceFrenchCharacter(s);
			String q = p.toLowerCase();

			char[] a = d.getComposition(q, letters); 
			String aTemp = String.valueOf(a);
			wordListJoker[l] = aTemp; //on remplit les listes de mots en tenant compte des jokers
			l++;
	}

	System.out.print(wordList.length + " matching word(s) found : ");
	Arrays.sort(wordListJoker, sc); //on compare avec les jokers 

	for(String e : wordListJoker) {
			//String p = d.replaceFrenchCharacter(e);
			
			//String q = p.toLowerCase();

			char[] a = d.getComposition(e, letters); 
			String aTemp = String.valueOf(a);

			//System.out.println(aTemp);

			int value = sc.wordValue(aTemp);
			//System.out.println(value);

			System.out.print(e +" ("+ Arrays.toString(a)+ " " + value +") ");
	}*/

}


	public static void main(String[] args) throws FileNotFoundException {
		new ScrabbleConsole();			
	}
}
