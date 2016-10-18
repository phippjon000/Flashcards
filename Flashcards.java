// A class that runs the main Flashcards loop
import java.util.*;
import java.io.*;

public class Flashcards {
   public static void main(String[] args) {
      String filePath = args[0];
	  CardManager cm = new CardManager(new File(filePath));
	  Scanner sc = new Scanner(System.in);
	  
	  if (!cm.isValid()) {
		 System.err.println("An error occured while initializing Flashcards");
		 System.exit(1);
	  }
	  
	  System.out.println("Welcome to Flashcards!");
	  while(true) {
	     Card card = cm.getNextCard();
		 System.out.println("Question: " + card.getQuestion());
		 String userAnswer = sc.nextLine();
		 if (card.getAnswer().toLowerCase().contains(userAnswer)) {
		    System.out.println("Correct! Answer is: " + card.getAnswer());
		 } else {
		    System.out.println("Incorrect. Answer is: " + card.getAnswer());
		 }
	  }
   }
}