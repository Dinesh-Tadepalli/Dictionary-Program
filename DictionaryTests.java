/*
 * Note From Janice Ahn: We have to use JUnit test so I changed the format as followed. Use the
 * format the I commented in the class.
 */
// These are needed to use JUnit test
import org.junit.Test;

import java.io.File;
import java.lang.invoke.MethodHandles;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.platform.console.ConsoleLauncher;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
// This set the test as "Fail" and print the message
// Should be removed after implementing
import static org.junit.Assert.fail;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DictionaryTests {

	// Data Wrangler Code Tests: Annie Browning
	@Test
	public void DataWranglerTest1() {
		//tests that at least one word object was loaded correctly
		//when the app is run (initial tree contains at least one word
		//object)
		WordLoader c = new WordLoader();
		List<Word> list = c.loadCSV("DictionaryDataSet.csv");
		Word expectedFirst = new Word("Amaze", "to cause awe or inspiration", speech.VERB);
		Word first = list.get(0);
		assertEquals(first, expectedFirst);
	}
	@Test
	public void DataWranglerTest2() {
		//tests that the csv file we are reading from has the proper object type
		//specified for the part of speech (enum)
		WordLoader d = new WordLoader();
		List<Word> list = d.loadCSV("DictionaryDataSet.csv");
		
			assertFalse(list.size().equals(1));
		
	}
	@Test
	public void DataWranglerTest3() {
		// tests that the entire csv file was loaded into the driver class
		WordLoader e = new WordLoader();
		List<Word> list = e.loadCSV("DictionaryDataSet.csv");
		File csv = new File(DictionaryDataSet.csv);
		Scanner scanner = new Scanner(csv);
		int numWordsInFile = 0;
		while(scanner.hasNext()){
			numWordsInFile++;
		}
		numWordsInFile --; //because first line in file is not a word object
		assertEquals(numWordsInFile, list.size());
	}


	/* Back End Developer Tests - wrote by Jihyun Ahn */
	@Test
	public void BackendDeveloper_TestInsertion() {
		// This test is to check if the insert, contains, and print methods from BackEnd
		// file works properly
		// Results should be the same as intended if the insertion function works well
		// call the BackEnd class
		DictionaryBackEnd test = new DictionaryBackEnd();

		// Set the word objects for the test
		Word word1 = new Word();
		word1.wordString = "Love";
		word1.definition = "feeling when you want to be with others and happy when your togethor";
		word1.partOfSpeech = Word.speech.NOUN;

		Word word2 = new Word();
		word2.wordString = "Banana";
		word2.definition = "frute which is yellow and long and sweet";
		word2.partOfSpeech = Word.speech.NOUN;

		Word word3 = new Word();
		word3.wordString = "Apple";
		word3.definition = "A for apple lol";
		word3.partOfSpeech = Word.speech.NOUN;

		Word word4 = new Word();
		word4.wordString = "Banana";
		word4.definition = "Long banana";
		word4.partOfSpeech = Word.speech.NOUN;

		Word word5 = new Word();
		word5.wordString = "B";
		word5.definition = "Long";
		word5.partOfSpeech = Word.speech.NOUN;

		// check if the insert works well
		test.insert(word1);
		test.insert(word2);
		test.insert(word3);
		// To check inserting the same wordString
		test.insert(word4);

		// set the wantedList string for comparing the actual result for the JUnit test
		String wantedList = "[ Apple: (NOUN) A for apple lol, Banana: (NOUN) frute which is yellow and long and sweet, Love: (NOUN) feeling when you want to be with others and happy when your togethor ]";

		// JUnit tests
		assertEquals(wantedList, test.print()); // To check if the insertion and print method works well
		assertTrue(test.contains(word4)); // To check if the contains method works as intended
		assertFalse(test.contains(word5)); // (same as above)
	}

	@Test
	public void BackendDeveloper_TestSearching() {
		// This test is to check if the insert, searchByWord, searchByDefinition, and
		// print methods from BackEnd file works properly
		// call the BackEnd class
		DictionaryBackEnd test2 = new DictionaryBackEnd();

		// Set the word objects for the test
		Word word1 = new Word();
		word1.wordString = "Love";
		word1.definition = "feeling when you want to be with others and happy when your togethor";
		word1.partOfSpeech = Word.speech.NOUN;

		Word word2 = new Word();
		word2.wordString = "Banana";
		word2.definition = "frute which is yellow and long and sweet";
		word2.partOfSpeech = Word.speech.NOUN;

		Word word3 = new Word();
		word3.wordString = "Cat";
		word3.definition = "one of the mammals that cries MEOW. They are loved as pets by many people.";
		word3.partOfSpeech = Word.speech.NOUN;

		Word word4 = new Word();
		word4.wordString = "Banana";
		word4.definition = "Long banana";
		word4.partOfSpeech = Word.speech.NOUN;

		Word word5 = new Word();
		word5.wordString = "Dog";
		word5.definition = "one of the mammals that are loved as pets by many people.";
		word5.partOfSpeech = Word.speech.NOUN;

		Word word6 = new Word();
		word6.wordString = "Love";
		word6.definition = "I love you";
		word6.partOfSpeech = Word.speech.NOUN;

		// check if the insert works well
		test2.insert(word1);
		test2.insert(word2);
		test2.insert(word3);
		test2.insert(word5);
		// To check inserting the same wordString
		test2.insert(word4);

		// set the wantedList string for comparing the actual result for the JUnit test
		String wantedList = "[ Banana: (NOUN) frute which is yellow and long and sweet, Cat: (NOUN) one of the mammals that cries MEOW. They are loved as pets by many people., Dog: (NOUN) one of the mammals that are loved as pets by many people., Love: (NOUN) feeling when you want to be with others and happy when your togethor ]";
		String wantedDefinition1 = "feeling when you want to be with others and happy when your togethor";
		String wantedDefinition2 = "one of the mammals that cries MEOW. They are loved as pets by many people.";
		String wantedWord = "Cat Dog ";

		// JUnit tests
		assertEquals(wantedList, test2.print()); // To check if the printing method in alphabetic order works
		assertTrue(test2.contains(word6)); // To check if the contains method work well
		assertEquals(wantedDefinition1, test2.searchByWord(word6)); // To check if the searchByWord method works as
		// intended
		assertEquals(wantedDefinition2, test2.searchByWord(word3)); // (same as above)
		assertEquals(wantedWord, test2.searchByDefinition("loved as pets by many people.")); // To check if the
		// SeachByDefinition
		// method works as
		// intended
		assertEquals("Love ", test2.searchByDefinition("feeling")); // (same as above)
	}

	@Test
	public void BackendDeveloper_TestMoreInsertion() {
		// This test is to check if the insert, contains, and searchByWord method works
		// well.
		// This test can be updated if more functions added
		// call the BackEnd class
		DictionaryBackEnd test3 = new DictionaryBackEnd();

		// Set the word objects for the test
		Word word1 = new Word();
		word1.wordString = "Globalization";
		word1.definition = "the process by which businesses or other organizations develop international influence or start operating on an international scale.";
		word1.partOfSpeech = Word.speech.NOUN;

		Word word2 = new Word();
		word2.wordString = "Pretty";
		word2.definition = "attractive in a delicate way without being truly beautiful or handsome.";
		word2.partOfSpeech = Word.speech.ADJECTIVE;

		Word word3 = new Word();
		word3.wordString = "Beautiful";
		word3.definition = "pleasing the senses or mind aesthetically.";
		word3.partOfSpeech = Word.speech.ADJECTIVE;

		Word word4 = new Word();
		word4.wordString = "Sun";
		word4.definition = "the star around which the earth orbits.";
		word4.partOfSpeech = Word.speech.NOUN;

		Word word5 = new Word();
		word5.wordString = "Moon";
		word5.definition = "the natural satellite of the earth, visible (chiefly at night) by reflected light from the sun.";
		word5.partOfSpeech = Word.speech.NOUN;

		Word word6 = new Word();
		word6.wordString = "Star";
		word6.definition = "a fixed luminous point in the night sky which is a large, remote incandescent body like the sun.";
		word6.partOfSpeech = Word.speech.NOUN;

		// check if the insert works well
		test3.insert(word1);
		test3.insert(word2);
		test3.insert(word3);
		test3.insert(word4);
		test3.insert(word5);

		// set the wantedList string for comparing the actual result for the JUnit test
		String wantedList1 = "[ Beautiful: (ADJECTIVE) pleasing the senses or mind aesthetically., Globalization: (NOUN) the process by which businesses or other organizations develop international influence or start operating on an international scale., Moon: (NOUN) the natural satellite of the earth, visible (chiefly at night) by reflected light from the sun., Pretty: (ADJECTIVE) attractive in a delicate way without being truly beautiful or handsome., Sun: (NOUN) the star around which the earth orbits. ]";
		String wantedDefinition = "the natural satellite of the earth, visible (chiefly at night) by reflected light from the sun.";
		// JUnit tests
		assertEquals(wantedList1, test3.print()); // To check if the insertion and print method works well
		assertTrue(test3.contains(word4)); // To check if the contains method works as intended
		assertTrue(test3.contains(word5)); // (same as above)
		assertEquals(wantedDefinition, test3.searchByWord(word5)); // To check if the searchByWord method works well
	}
	/* End of the test for Back End code */


	// Front End Developer Tests
	/* Front End Developer Tests - wrote by Jeff Kim */
	@Test
	public void FrontEndTest1() {
		// check whether the word is "successfully" added to the database or not

		// insert word: "cat" and definition: "cute animal" part of Speech: "Noun" and Quit
		TextUITester tester = new TextUITester("1\ncat\ncute animal\nn\nn\n5\ny\n");
		DictionaryBackEndInterface helpEngine = new DictionaryBackEnd();
		DictionaryFrontEndInterface helpUI = new DictionaryFrontEnd();

		helpUI.run(helpEngine);

		String output = tester.checkOutput(); //
		String expectedPhrase = "The Word has been successfully added";
		assertTrue(output.contains(expectedPhrase));


	}

	@Test
	public void FrontEndTest2() {
		// Word (ëŒ€ë¬¸ìž�) ë¥¼ ì³¤ì�„ë•Œ, definitionì�´ ì•Œë§žê²Œ ë‚˜ì˜¤ëŠ”ì§€
		TextUITester tester = new TextUITester("1\ncat\ncute animal\nn\nn\n2\nCat\n5\ny\n");
		DictionaryBackEndInterface helpEngine = new DictionaryBackEnd();
		DictionaryFrontEndInterface helpUI = new DictionaryFrontEnd();

		helpUI.run(helpEngine);

		String output = tester.checkOutput(); //
		String expectedPhrase = "The Word has been successfully added";
		assertTrue(output.contains(expectedPhrase));

		String expectedDefinition = "cute animal";
		assertTrue(output.contains(expectedDefinition));


	}

	@Test
	public void FrontEndTest3() {
		// definitionì�„ ì°¾ì•˜ì�„ë•Œ, wordê°€ ì œëŒ€ë¡œ ë‚˜ì˜¤ëŠ”
		TextUITester tester = new TextUITester("1\ncat\ncute animal\nn\nn\n3\ncute animal\n5\ny\n");
		DictionaryBackEndInterface helpEngine = new DictionaryBackEnd();
		DictionaryFrontEndInterface helpUI = new DictionaryFrontEnd();

		helpUI.run(helpEngine);

		String output = tester.checkOutput(); //
		String expectedPhrase = "The Word has been successfully added";
		assertTrue(output.contains(expectedPhrase));

		String expectedWord = "cat";
		assertTrue(output.contains(expectedWord));


	}

	@Test
	public void IntegrationManager_TestCSV() {
		/*
		 * Integration Manager test for Data Wrangler - written by Dinesh Tadepalli
		 *
		 * This method tests that DictionaryDataSet.csv is imported correctly.
		 */

		WordLoader loader = new WordLoader();
		List<Word> list = loader.loadCSV("DictionaryDataSet.csv"); // load words from .csv file
		List<String> words = new ArrayList() {
		};

		// put the actual words of the each Word object into another list
		for (int i = 0; i < list.size(); i++) {
			words.add(list.get(i).wordString);
		}

		// test that the list of words contains the words from the csv file
		assertTrue(words.contains("amaze"));
		assertTrue(words.contains("fancy"));
		assertTrue(words.contains("quack"));
		assertTrue(words.contains("underdog"));
		assertTrue(words.contains("zoo"));

		// test that the words' definitions match their expected definitions
		// there are also spaces after the definitions in the csv file. These
		// definitions are trimmed, as tested below
		assertEquals(list.get(words.indexOf("lucky")).definition, "The presence of good fortune");
		assertEquals(list.get(words.indexOf("quack")).definition, "The noise made by a duck");
		assertEquals(list.get(words.indexOf("vim")).definition, "A unix text editor");
	}

	@Test
	public void IntegrationManager_TestInsertion() {
		/*
		 * Integration Manager test for Back End Developer - written by Dinesh Tadepalli
		 *
		 * This method tests that double insertions do not work and that adding spaces
		 * and/or using CAPS has no effect.
		 */

		DictionaryBackEndInterface engine = new DictionaryBackEnd();

		engine.insert(new Word("cat", "cute animal", Word.speech.NOUN));
		engine.insert(new Word("bottle", "container from which to drink water", Word.speech.NOUN));
		engine.insert(new Word("kill", "remove a life", Word.speech.VERB));

		// testing that the correct string of the word is returned when
		// the definition of the word is searched
		assertEquals(engine.searchByDefinition("cute animal"), "cat ");
		assertEquals(engine.searchByDefinition("container from which to drink water"), "bottle ");
		assertEquals(engine.searchByDefinition("remove a life"), "kill ");
		assertEquals(engine.searchByDefinition("blah blah"), ""); // search should fail

		assertEquals(engine.searchByWord(new Word("cat", null, null)), "cute animal");
		assertEquals(engine.searchByWord(new Word("bottle", null, null)), "container from which to drink water");
		assertEquals(engine.searchByWord(new Word("kill", null, null)), "remove a life");
		assertEquals(engine.searchByDefinition("more bleh bleh"), ""); // search should fail
	}

	@Test
	public void IntegrationManager_TestExitOutput() {
		/*
		 * Integration Manager test for Front End Developer - written by Dinesh
		 * Tadepalli
		 *
		 * This method checks that if you try to exit, the correct prompt is shown
		 */

		TextUITester tester;
		DictionaryBackEndInterface helpEngine = new DictionaryBackEnd();
		DictionaryFrontEndInterface helpUI = new DictionaryFrontEnd();

		tester = new TextUITester("5\nN\n5\nY");
		helpUI.run(helpEngine);
		String output = tester.checkOutput();

		assertTrue(output.contains("End of the Application"));
	}

	public static void main(String[] args) {
		String className = MethodHandles.lookup().lookupClass().getName();
		String classPath = System.getProperty("java.class.path").replace(" ", "\\ ");
		String[] arguments = new String[]{"-cp", classPath, "--include-classname=.*", "--select-class=" + className};
		ConsoleLauncher.main(arguments);
	}

}
