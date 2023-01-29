// --== CS400 File Header Information ==--
// Name: Dong Hoi (Jeff) Kim
// Email: dkim599@wisc.edu
// Team: BM
// TA: Surabhi Gupta
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.util.InputMismatchException;
import java.util.Scanner;

// interface (implemented with proposal)

interface DictionaryFrontEndInterface {
  public void run(DictionaryBackEndInterface searchEngine);

  // DictionarySearch Command Overview:
  // 1. Insert New word into Database
  // 2. search word meaning by word
  // 3. definition description
  // 4. Dictionary word tree print with alphabetical order
  // 5. Quit
}


/**
 * @author Jeff Kim
 *
 */
public class DictionaryFrontEnd implements DictionaryFrontEndInterface {

  @Override
  public void run(DictionaryBackEndInterface searchEngine) {
    int optionNum = 0;
    Scanner scan = new Scanner(System.in);
    
    while (optionNum != 5) {
      // DictionarySearch Command Options
      System.out.println("\nDictionarySearch Command Options");
      System.out.println("1. Insert New Word into Database");
      System.out.println("2. Search Word Definition by Word");
      System.out.println("3. Search Word by Definition");
      System.out.println("4. Generate Word Tree with Alphabetical Order");
      System.out.println("5. Quit");

      // Show the user what to do
      int commandNumKey = 0;
      int userCommandNum = 0;
      while (commandNumKey == 0) { 
        System.out.println("Please Select Command Option by Typing Number (ex.1, 2, 3, 4, or 5): ");
        try {
          userCommandNum = scan.nextInt();
          commandNumKey = 1;
        } catch (InputMismatchException e) {
          System.out.println("Error: invalid input.");
          System.out.println("Please type natural number between 1 to 5.");
          scan.nextLine();
          commandNumKey = 0;
        }
      }
      
      // Check what the user want to do
      switch (userCommandNum) {
        
        case 1:
          // 1. Insert New Word into Database
          optionNum = 1;
          int key = 0; // key for looping case 1
          
          // loop for ask user to keep add word(s) or not
          while (key == 0) {
            Word newWord = new Word();
            // get the word and definition
            // and put it into the list based on the BackEnd's code
            System.out.println(" < Adding a New Word > ");
            System.out.println("Please give us information about the word");
            
            /* start of getting word spelling */
            System.out.println("Please enter the spelling: ");
            String getWord = scan.next(); // Dictionary - insert (Word spelling)
            // check if the input is null
            if (getWord == null) {
              System.out.println("Error: Word Spelling is not provided.");
              break;
            } // have to change it if I use while loop for this case
            newWord.wordString = getWord.toLowerCase().trim();
            /* end of getting word spelling */
            
            /* start of getting word definition */
            System.out.println("Please enter the definition: ");
            scan.nextLine();
            String getDef = scan.nextLine(); // Dictionary - insert (Word Definition)
            // check if the inputs is null
            if (getDef == null) {
              System.out.println("Error: Word Definition is not provided.");
              break;
            } // have to change it if I use while loop for this case
            newWord.definition = getDef.trim();
            /* end of getting word definition */
            
            /* start of getting word's part of speech */ 
            int partOfSpeechKey = 0;
            while (partOfSpeechKey == 0) {
              System.out.println("Please verify Part Of Speech choosing by initial (ex. N, V, or A)");
              System.out.println("Noun");
              System.out.println("Verb");
              System.out.println("Adjective");
              String getPartOfSpeech = scan.next();
              if (getPartOfSpeech == null) {
                System.out.println("Error: Part Of Speech is not provided.");
                partOfSpeechKey = 0;
              } // have to change it if I use while loop for this case
              if (getPartOfSpeech.toUpperCase().equals("N")) {
                partOfSpeechKey = 1;
                newWord.partOfSpeech = Word.speech.NOUN;
              } else if (getPartOfSpeech.toUpperCase().equals("V")) {
                partOfSpeechKey = 1;
                newWord.partOfSpeech = Word.speech.VERB;
              } else if (getPartOfSpeech.toUpperCase().equals("A")) {
                partOfSpeechKey = 1;
                newWord.partOfSpeech = Word.speech.ADJECTIVE;
              } else {
                System.out.println("Error: Input of Part Of Speech is invalid");
                partOfSpeechKey = 0;
              }
            }
            /* end of getting word's part of speech */
            
            // check if the word already exists
            // if it does, then print the error message
            // if not, then add the song to the Database and print successful message
            if (searchEngine.contains(newWord)) {
              System.out
                  .println("The Word is already in the Database. Please choose different Word.");
            } else {
              searchEngine.insert(newWord);
              System.out.println("The Word has been successfully added to the Database!");
            }
            // check whether the user wants to keep adding the word or not
            int insertEndOrNotKey = 0;
            while (insertEndOrNotKey == 0) {
              System.out.println("\nWould you like to add more Words? (Y/N): ");
              String addOrNot = scan.next();

              if (addOrNot.toUpperCase().equals("Y")) {
                // the user would like to add more word
                System.out.println("Restart to add more words\n");
                insertEndOrNotKey = 1;
                key = 0;
              } else if (addOrNot.toUpperCase().equals("N")) {
                // the user does not want to add more word
                System.out.println("End of adding Word(s).");
                insertEndOrNotKey = 1;
                key = 1;
              } else {
                System.out.println("Could not read Y/N.");
                System.out.println("Please type either Y or N\n");
                insertEndOrNotKey = 0;
                key = 0;
              }
            }
          } // end of while loop for looping case 1
          break;

        case 2:
          // 2. Search Word Definition by Word
          // When the user types the word spelling, then app will show its definition
          optionNum = 2;
          System.out.println(" < Searching the Word Definition > ");
          System.out.println("Please enter Word Spelling: ");
          String getWord = scan.next();
          getWord = getWord.toLowerCase().trim();
          Word newWord = new Word(getWord, "", null);
          // check if the input is null
          if (getWord == null) {
            System.out.println("Error: information is not provided.");
            break;
          }
          System.out.println("The Word: " + getWord);
          System.out.println("Its Definition: " + searchEngine.searchByWord(newWord) + "\n");
          break;
          
        case 3:
          // 3. Search Word by Definition
          // When the user types a part of the Definition, app will show the following word
          optionNum = 3;
          System.out.println(" < Searching Word by Definition > ");
          System.out.println("Please enter Definition of the word: ");
          scan.nextLine();
          String getDef = scan.nextLine();
          getDef = getDef.trim();
          // check if the input is null
          if (getDef == null) {
            System.out.println("Error: information is not provided.");
            break;
          }
          System.out.println("The Word which has " + getDef + " as word definition is as follow: ");
          System.out.println(searchEngine.searchByDefinition(getDef) + "\n\n");
          break;

        case 4:
          // 4. Generate Word Tree with Alphabetical Order
          // call tree from BackEnd
          System.out.println(searchEngine.print());
          break;

        case 5:
          // 5. Quit
          optionNum = 5;
          int endOrNotKey = 0;

          while (endOrNotKey == 0) {
            System.out.println("Are you sure to quit this Application? (Y/N): ");
            String endOrNot = "";
            endOrNot = scan.next();
            // Make sure the user wants to exit or not
            if (endOrNot.toUpperCase().equals("Y")) {
              System.out.print("\nEnd of the Application.");
              System.out.print("\nThank you for using it!");
              endOrNotKey = 1;
            } else if (endOrNot.toUpperCase().equals("N")) {
              // if "N" or "n", go back to app
              System.out.print("\nGo Back to the App!");
              endOrNotKey = 1;
              optionNum = 0;
            } else {
              System.out.print("\nCould not read Y/N.");
              System.out.print("\nPlease type either Y or N\n");
              endOrNotKey = 0;
            }
          }
          break;
          
        default:
          System.out.println("Error: the input value should be between 1 and 5 natural number.");
          System.out.println("Please Check the Command Options and Choose One of them.");
          break;
          
      }
    } // End of the while loop
    scan.close(); // close the scanner
  }
}


// placeholder(s) (implemented with proposal, and possibly added to later)
class DictionaryFrontEndPlaceholder implements DictionaryFrontEndInterface {
  public void run(DictionaryBackEndInterface searchEngine) {
    System.out.println("This front end has not been implemented yet.");
  }
}
