import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.InputStream;

class WordLoader {
	
	public List<Word> loadCSV(String csvFilePath) {

		try { // if a file named csvFilePath is in the current folder (usually the case in
				// this project)
			InputStream myObj = DictionaryApp.class.getResourceAsStream(csvFilePath);
			Scanner lineReader = new Scanner(myObj, "UTF-8");
			lineReader.nextLine(); // the first row is just "Word,Definition,Part of Speech"
			List<Word> listOfWords = new ArrayList<>();

			while (lineReader.hasNextLine()) {
				String dataOfRow = lineReader.nextLine();
				List<String> data = Arrays.asList(dataOfRow.split(","));
				String word = data.get(0).toLowerCase().trim();
				String definition = data.get(1).trim();
				Word.speech partOfSpeech = Word.speech.valueOf(data.get(2));
				listOfWords.add(new Word(word, definition, partOfSpeech));
			}
			lineReader.close();
			return listOfWords;

		} catch (Exception e1) { // if file named csvFilePath is in the project folder
			try {
				File myObj = new File(csvFilePath);
				Scanner lineReader = new Scanner(myObj, "UTF-8");
				lineReader.nextLine(); // the first row is just "Word,Definition,Part of Speech"
				List<Word> listOfWords = new ArrayList<>();

				while (lineReader.hasNextLine()) {
					String dataOfRow = lineReader.nextLine();
					List<String> data = Arrays.asList(dataOfRow.split(","));
					String word = data.get(0).toLowerCase().trim();
					String definition = data.get(1).trim();
					Word.speech partOfSpeech = Word.speech.valueOf(data.get(2));
					listOfWords.add(new Word(word, definition, partOfSpeech));
				}
				lineReader.close();
				return listOfWords;

			} catch (Exception e2) { // if file is not in current folder or project folder, then return null
				return null;
			}
		}
	}
}

public class DictionaryApp {

	public static void main(String[] args) throws Exception {
		System.out.println("Welcome to DictionaryApp");
		WordLoader loader = new WordLoader();
		List<Word> words = loader.loadCSV("DictionaryDataSet.csv");
		DictionaryBackEndInterface engine = new DictionaryBackEnd();
		if (words != null) {
			for (Word word : words)
				engine.insert(word);
		}
		DictionaryFrontEndInterface ui = new DictionaryFrontEnd();
		ui.run(engine);
	}

}
