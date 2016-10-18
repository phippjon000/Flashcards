// A class that manages a list of cards initialized
// from a file. The file is layed out like:
// "question","answer"
import java.util.*;
import java.io.*;

public class CardManager {
   private Queue<Card> cards;
   private int cardsSize;
   private int cardsLeft;
   private boolean valid;
   
   public CardManager(File cardValues) {
      cards = new LinkedList<Card>();
      cardsSize = 0;
	  cardsLeft = 0;
	  valid = true;
	  
      try (BufferedReader br = new BufferedReader(new FileReader(cardValues))) {
		  String cardString;
	     while ((cardString = br.readLine()) != null) {
		    String[] cardParts = cardString.split(",");
			Card card = new Card(cardParts[0], cardParts[1]);
			cards.add(card);
			cardsSize++;
		 }
		 
		 cardsLeft = cardsSize;
		 shuffleCards();
	  } catch (Exception e) {
	     System.err.println("An error occured when parsing file " + cardValues.getName() + ", " + e.getMessage());
		 valid = false;
	  }
   }
   
   public Card getNextCard() {
      Card nextCard = cards.remove();
	  cards.add(nextCard);
	  cardsLeft--;
	  if (cardsLeft <= 0) {
	     shuffleCards();
		 cardsLeft = cardsSize;
	  }
	  
	  return nextCard;
   }
   
   private void shuffleCards() {
      List<Card> oldCards =  new ArrayList<Card>();
	  for (int i = 0; i < cardsSize; i++) {
	     oldCards.add(cards.remove());
	  }
	  Collections.shuffle(oldCards);
	  for (int i = 0; i < oldCards.size(); i++) {
		 cards.add(oldCards.get(i));
	  }
   }
   
   public boolean isValid() {
	  return valid;
   }
}