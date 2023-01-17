package OpaoPham;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	private ArrayList<Card> cards;
	private ArrayList<Card> faceUpcards = new ArrayList<Card>();
	
	public Deck(ArrayList<Card> pcards) {
		cards = pcards;
	}
	
	public void setDeck(ArrayList<Card> pcards) {
		cards = pcards;
	}
	
	public ArrayList<Card> getDeck() {
		return cards;
	}
	
	//Work on
	public boolean getFaceUpCardsStatus() {
		int numOfFaceUpCards = faceUpcards.size();
		
		//If 2> return false
		if (numOfFaceUpCards <= 2) {
			return false;
		}
		else {
			return true;
		}
		
	}
	
	public void addFacedUpCard(Card card) {
		faceUpcards.add(card);
	}
	
	public void removeFaceUpCards() {
		faceUpcards.clear();
	}
	
	
	public ArrayList<Card> getFacedUpCards() {
		return faceUpcards;
	}

	public boolean trackFaceUpCards() {
		
		return true;
	}
	
	public boolean isCardMatch() {
		return true;
	}


}
