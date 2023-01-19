package OpaoPham;

import java.util.ArrayList;

/**
 * 
 * @author Opao, Leala & Pham, Julie
 *
 */
public class Deck {
	private ArrayList<Card> cards;
	private ArrayList<Card> faceUpcards = new ArrayList<Card>();
	
	/**
	 * 
	 * @param pcards
	 */
	public Deck(ArrayList<Card> pcards) {
		cards = pcards;
	}
	
	/**
	 * 
	 * @param pcards
	 */
	public void setDeck(ArrayList<Card> pcards) {
		cards = pcards;
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Card> getDeck() {
		return cards;
	}
	
	//Work on
	/**
	 * 
	 * @return
	 */
	public boolean getFaceUpCardsStatus() {
		int numOfFaceUpCards = faceUpcards.size();

		if (numOfFaceUpCards < 2) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * 
	 * @param card
	 */
	public void addFacedUpCard(Card card) {
		faceUpcards.add(card);
	}
	
	/**
	 * 
	 */
	public void removeFaceUpCards() {
		faceUpcards.clear();
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Card> getFacedUpCards() {
		return faceUpcards;
	}
	
	
	/**
	 * 
	 * @return
	 */
	//track the number of correct pairs
	public boolean trackCorrectFaceUpCards() {
		
		return true;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isCardMatch() {
		for(int i = 0; i < faceUpcards.size();) {
			if(faceUpcards.get(i).getCardLocName().equals(faceUpcards.get(i+1).getCardLocName())) {
				return true;
			}
		}
		return false;
		
		
	}


}
