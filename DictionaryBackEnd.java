import java.util.LinkedList;
// --== CS400 File Header Information ==--
// Name: 	 Jihyun Ahn (Janice Ahn)
// Email: 	 ahn58@wisc.edu
// Team: 	 BM
// TA:  	 Surabhi Gupta
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>


//Interface that doesn't have to be changed
interface DictionaryBackEndInterface {
	public void insert(Word word);

	public boolean contains(Word word);

	public String searchByWord(Word word);

	public String searchByDefinition(String definition);

	public String print();
}


// Main code
public class DictionaryBackEnd implements DictionaryBackEndInterface {
	// call the RedBlackTree class to make a new RBT
	public RedBlackTree<Word> newRBT = new RedBlackTree<Word>();

	/* This method is to insert the given node from the user to RBT */
	@Override
	public void insert(Word word) {
		// method to insert the given word in the tree
		if (word == null || word.getWordString() == null) {
			System.out.println("Failed: please type the word that you want to add(the given word is null).");
		} else if (newRBT.contains(word)) {
			System.out.println("Failed: The given word is already in the Tree.");
		} else{newRBT.insert(word);} 
	
	}// End of the insert method

	/* contains method is to check if the given wordString is in the tree return
	 * true if the word exist || return false it's not*/
	@Override
	public boolean contains(Word word) {
		// if the input is not given properly then return null with the failed comment
		if (word == null || word.getWordString() == null) {
			return false;
		}
		// else, then compare the given word with the node that is one in the RBT
		RedBlackTree.Node<Word> tempNode = newRBT.root;
		while (tempNode != null) {
			Word tempWord = tempNode.data; // get the comparable word object from the tree
			if (word.compareTo(tempWord) == 0) {
				// If the given word is equal to the tempWord then return true
				return true;
			} else if (word.compareTo(tempWord) > 0) {
				// when the given word is bigger then the tempWord,
				// then move to the rightChild of the tempWord to compare
				tempNode = tempNode.rightChild;
			} else {
				tempNode = tempNode.leftChild;
			}
		} // End of the while loop
		// if the given node is not in the RBT, then return false with the failed comment
		return false;
	}// End of the contains method

	/* method to search the word and return the definition of the word */
	@Override
	public String searchByWord(Word word) {
		// if the RedBlackTree does not contain the given word, then it return null
		if (word == null || word.getWordString() == null) {
			return null;
		} else if (contains(word)) {
			RedBlackTree.Node<Word> tempNode = newRBT.root;
			while (tempNode != null) {
				Word tempWord = tempNode.data; // get the comparable word object from the tree
				if (word.compareTo(tempWord) == 0) {
					// If the given word is equal to the tempWord then return the definition
					word = tempWord;
					return word.getDefinition();

				} else if (word.compareTo(tempWord) > 0) {
					// when the given word is bigger then the tempWord,
					// then move to the rightChild of the tempWord to compare
					tempNode = tempNode.rightChild;
				} else {
					tempNode = tempNode.leftChild;
				}
			} // End of the while loop
		}
		return null;
	}// End of the searchByWord method

	/* method to search the word by given definition and return the wordString */
	@Override
	public String searchByDefinition(String definition) {
		String wordContains = "";									// return value
		if (definition == null) {
			return null;
		}
		LinkedList<RedBlackTree.Node<Word>> q = new LinkedList<>();	// make a new Linked list to store nodes from the RedBlackTree
		q.add(newRBT.root);											// add the root node in the linked list
		while (!q.isEmpty()) {										
			RedBlackTree.Node<Word> next = q.removeFirst();			// make a new Node file 
			if (next.leftChild != null) {
				q.add(next.leftChild);
			}
			if (next.rightChild != null) {
				q.add(next.rightChild);
			}
			if (next.data.definition.contains(definition)) {
				wordContains += next.data.wordString + " ";
			}	
		}
		return wordContains;
		
	}// End of the searchByDefinition method

	@Override
	public String print() {
		// This method is for printing the word in dictionary in alphabet order
		
		return newRBT.toString();
	}
	
}// End of the class


//Placeholder which can be used by FrontEnd developers
class DictionaryBackEndPlaceholder implements DictionaryBackEndInterface {
	private Word onlyWord;

	public void insert(Word word) {
		this.onlyWord = word;
	}
	public boolean contains(Word word) {
		return onlyWord.equals(word);
	}
	public String searchByWord(Word word) {
		return "Definition of word";
	}
	public String searchByDefinition(String definition) {
		return "word";
	}
	public String print() {
		return "[ word1, word2 ]";
	}
}
