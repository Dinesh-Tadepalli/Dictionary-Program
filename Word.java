/**
 * word object class
 * author: Annie
 */
public class Word implements Comparable<Word> {
    
    //enum for types of speech
    enum speech{
        NOUN,
        VERB,
        ADJECTIVE
    }

    //class vars
    public String wordString;
    public Word parent;
    public Word leftChild;
    public Word rightChild;
    public int wordLength;
    public speech partOfSpeech;
    public String definition;
    public boolean isBlack;

    //contructors
    public Word(){
        wordString = "";
        parent = null;
        leftChild = null;
        rightChild = null;
        wordLength = 0;
        partOfSpeech = null;
        definition = "";
    }

    // remember to add part of speech in all caps ex: speech.NOUN
    public Word(String wordString, String definition, speech partOfSpeech){
        this.wordString = wordString;
        this.definition = definition;
        this.partOfSpeech = partOfSpeech;
        wordLength = wordString.length();
        leftChild = null;
        rightChild = null;
        parent = null;
    }

    //setters
    public void setParent(Word parent){
        this.parent = parent;
    }
    public void setLeftChild(Word leftChild){
        this.leftChild = leftChild;
    }
    public void setRightChild(Word rightChild){
        this.rightChild = rightChild;
    }
    public void setColor(boolean isBlack){
        this.isBlack = isBlack;
    }

    //getters
    public Word getParent(){
        return parent;
    }
    public Word getLeftChild() {
        return leftChild;
    }
    public Word getRightChild() {
        return rightChild;
    }
    public String getWordString() {
        return wordString;
    }
    public speech getPartOfSpeech(){
        return partOfSpeech;
    }
    public int getWordLength(){
        return wordLength;
    }
    public String getDefinition(){
        return definition;
    }

    //compares the current word object to another word
    @Override
    public int compareTo(Word word) {
        return this.getWordString().compareTo(word.getWordString());
    }

    public String toString(){
        return wordString+": ("+partOfSpeech.toString()+") "+definition;
    }

    //different than compareTo() method, mostly for use on the front end
    public boolean equals(Word other){
        boolean result = false;
        if (this.getWordString().equals(other.getWordString()) &&
            this.getDefinition().equals(other.getDefinition()) &&
            this.getPartOfSpeech().equals(other.getPartOfSpeech())){
            result = true;
        }
        return result;
    }

}
