run: compile
	java DictionaryApp

test: compile
	java -cp .:junit5.jar DictionaryTests

compile: 
	javac Word.java
	javac TextUITester.java
	javac RedBlackTree.java
	javac DictionaryBackEnd.java
	javac DictionaryFrontEnd.java
	javac DictionaryApp.java
	javac -cp .:junit5.jar DictionaryTests.java	

clean:
	rm *.class
	rm *~

