import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SpellCheck {
	
	/*
	 * This method opens a dictionary file specified by the user's command line argument.
	 * It then loads each word form the dictionary into a BST. It will then take user input
	 * and spell-check it against the words in the BST, notifying the user of typos and
	 * offering corrections.
	 */
	public static void main(String[] args) {
		
		if (args.length != 1) { // no file specified
			System.out.println("No file specified.");
			return;
		}
		
		String fileName = args[0];
		
		BinarySearchTree<String> bst = new BinarySearchTree<String>();
		
		try { // open file
			
			FileInputStream file = new FileInputStream(fileName); // open file
			Scanner s = new Scanner(file); // open scanner on file
			
			// collect lines from file
			while (s.hasNextLine()) { // for all lines in file
				bst.insert(s.nextLine()); // store line in lines
			}
			
			s.close(); // close scanner
			 
		} catch (FileNotFoundException e) { // file not found
			System.out.println("File not found.");
			return;
		}
		
		Scanner s = new Scanner(System.in);
		System.out.println("Loaded the words into a tree with height = " + bst.height());
		String input = ""; // user input
		String[] words; // split input into words
		String match = ""; // check for prefix match
		while (!input.equals("END")) {
			
			input = s.nextLine();
			if (input.equals("")) { continue; } // skip empty input
			if (input.equals("END")) { continue; } // skip END processing
			
			words = input.split(" "); // split input into individual words
			for (int i = 0; i < words.length; i++) { // for every word
				
				input = words[i];
				if (bst.search(input) == null) { // if word is not in tree
					match = bst.prefix(input); // check for prefix match
					if (match != null) { // if match was found
						System.out.println("Did you mean \"" + match + "\"?");
						continue;
					}
					
					// else
					System.out.println(input + " is spelled wrong!");
				}
			}
		}
		s.close(); // close scanner
		
	}
}