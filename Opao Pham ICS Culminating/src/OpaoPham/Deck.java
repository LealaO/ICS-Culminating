package OpaoPham;

import java.util.ArrayList;

/**
 * Class for creating a card deck (ArrayList) of total cards and face-up cards.
 * @author Opao, Leala & Pham, Julie
 *
 */
public class Deck {
	private ArrayList<Card> cards;
	private ArrayList<Card> faceUpcards = new ArrayList<Card>();
	
	/**
	 * Creates a deck of cards.
	 * @param pcards
	 */
	public Deck(ArrayList<Card> pcards) {
		cards = pcards;
	}
	
	/**
	 * Assigns an ArrayList of cards to the deck of cards.
	 * @param pcards
	 */
	public void setDeck(ArrayList<Card> pcards) {
		cards = pcards;
	}
	
	/**
	 * Returns the deck.
	 * @return cards
	 */
	public ArrayList<Card> getDeck() {
		return cards;
	}

	/**
	 * Returns if the face up cards
	 * @return boolean
	 */
	public boolean getFaceUpCardsStatus() {
		int unmatchedCount = getUnmatchedCount();

		if (unmatchedCount < 2) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Gets the amount of unmatched cards
	 * @return unmatchedCount
	 */
	public int getUnmatchedCount() {
		int unmatchedCount = 0;
		int numOfFaceUpCards = faceUpcards.size();
		if (numOfFaceUpCards >= 2) {
			//gets the last two cards and see if one or more of them are unmatched
			for (int c = numOfFaceUpCards - 2; c < numOfFaceUpCards; c++) {
				if (!faceUpcards.get(c).getIsCardMatched()) {
					unmatchedCount++;
				}
			}
		}
		return unmatchedCount;
	}
	
	/**
	 * Adds card to the face-up cards deck.
	 * @param card - Card that is revealed to the player
	 */
	public void addFacedUpCard(Card card) {
		faceUpcards.add(card);
	}
		
	/**
	 * Returns ArrayList of face-up cards.
	 * @return faceUpcards - deck of face-up cards.
	 */
	public ArrayList<Card> getFacedUpCards() {
		return faceUpcards;
	}
	
	/**
	 * Track the number of correct pairs
	 * @return true
	 */
	public boolean trackCorrectFaceUpCards() {
		return true;
	}
	
	/**
	 * Check if the selected cards are a match or not
	 * @return boolean
	 */
	public boolean isCardMatch() {
		if (faceUpcards.size() >= 2) {
			int i = faceUpcards.size() - 2;
			if (faceUpcards.get(i).getCardLocName().equals(faceUpcards.get(i + 1).getCardLocName())) {
				return true;
			}
		}
		return false;
		
	}

}