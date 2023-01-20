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
		int unmatchedCount = getUnmatchedCount();

		if (unmatchedCount < 2) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * @return
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
	 * 
	 * @param card
	 */
	public void addFacedUpCard(Card card) {
		faceUpcards.add(card);
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
		if (faceUpcards.size() >= 2) {
			int i = faceUpcards.size() - 2;
			if (faceUpcards.get(i).getCardLocName().equals(faceUpcards.get(i + 1).getCardLocName())) {
				return true;
			}
		}
		return false;
		
		
	}


}
