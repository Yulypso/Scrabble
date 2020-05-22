import java.util.*;
import java.util.List;
import java.text.*;
import java.io.FileNotFoundException;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ScrabbleGUI implements ActionListener {

	private JButton searchButton = new JButton("Search");
	private JTextField letterTextField = new JTextField(20); 
	private Dictionary dico;
	private JTextArea wordListTextArea = new JTextArea(20,2);


	public ScrabbleGUI() throws FileNotFoundException {

		try {
			dico = new Dictionary("fr_FR_utf8.dico");
		} catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
		}

		searchButton.setActionCommand("key1");
  		searchButton.addActionListener(this);

		JFrame frame = new JFrame("Scrabble GUI");

		frame.setMinimumSize(new Dimension(680, 480));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		frame.setLayout(new BorderLayout());

		FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT); 


		JPanel topPanel = new JPanel(flowLayout); 
		//topPanel.setBorder(BorderFactory.createTitledBorder(""));
		topPanel.add(letterTextField); 
		topPanel.add(searchButton); 


		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1, 1));
		bottomPanel.setBorder(BorderFactory.createTitledBorder(""));
		bottomPanel.add(wordListTextArea);
		JScrollPane scroll = new JScrollPane(wordListTextArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		bottomPanel.add(scroll);

		frame.getContentPane().add(topPanel, BorderLayout.NORTH); 
		frame.getContentPane().add(bottomPanel, BorderLayout.CENTER); 






		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

		switch(e.getActionCommand()) {
			case "key1" : 
			wordListTextArea.setText("");

				String text = letterTextField.getText();
				String outputletters1 = dico.replaceFrenchCharacter(text); //convertir les caracteres accentués
				String outputletters = outputletters1.toLowerCase(); //convertir en caractere minuscules 

				char[] letters = outputletters.toCharArray(); //convertir String
				List<String> mylist = dico.getWordsThatCanBeComposed(letters); 




////////////////// comparateur

				String[] wordList = new String[mylist.size()];
				mylist.toArray(wordList);

				ScrabbleComparator sc = new ScrabbleComparator(letters);
				Arrays.sort(wordList, sc);

//////////////// composition

				wordListTextArea.append(mylist.size() + " matching word(s) found : \n" );

				for(String s : wordList) {
					String p = dico.replaceFrenchCharacter(s);
			
					String q = p.toLowerCase();

					char[] a = dico.getComposition(q, letters); 
					String aTemp = String.valueOf(a);

			//System.out.println(aTemp);

					int value = sc.wordValue(aTemp);
			//System.out.println(value);

					wordListTextArea.append(" - " + s +" ("+ Arrays.toString(a)+ " " + value +") \n");
	}







/*
				wordListTextArea.append(wordList.size() + " word(s) found : " + "\n");


				for(String a : wordList){
   					wordListTextArea.append(" - " + a + "\n");
				}

*/


				/*char[] myletters;

				for(String b : wordList) {
					myletters = dico.getComposition(b, letters);
				}

				ScrabbleComparator sc = new ScrabbleComparator(myletters);
				Arrays.sort(wordList, sc);*/

				wordListTextArea.updateUI();


				break;

		}

	}

	public static void main(String[] args) throws FileNotFoundException {

		new ScrabbleGUI();
	}
}


