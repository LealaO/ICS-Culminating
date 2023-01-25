package OpaoPham;

import javafx.scene.control.Button;

/**
 * Class for converting the cards into click-able buttons.
 * @author Opao, Leala & Pham, Julie
 *
 */
public class Card extends Button {
	private int row;
	private int column;
	private String cardLocName;
	private boolean isMatched = false;
	
	/**
	 * 
	 * @param cln
	 */
	public Card(String cln) {
		super();
		cardLocName = cln;
	}
	
	/**
	 * Creates a card class
	 * @param r - Input for row value
	 * @param c - Input for column value
	 * @param cln - Card name
	 */
	public Card(int r, int c, String cln) {
		super();
		row = r;
		column = c;
		cardLocName = cln;
	}
	
	/**
	 * Sets row and column values.
	 * @param r - Input for row value
	 * @param c - Input for column value
	 */
	public void setRowColumn(int r, int c) {
		row = r;
		column = c;		
	}
	
	/**
	 * Returns row value.
	 * @return row
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * Returns column value.
	 * @return column
	 */
	public int getColumn() {
		return column;
	}
	
	/**
	 * Returns cardLocName.
	 * @return cardLocName - Card value
	 */
	public String getCardLocName() {
		return cardLocName;
	}

	/**
	 * Returns isMatched
	 * @return isMatched - Does the card have a match
	 */
	public boolean getIsCardMatched() {
		return isMatched;
	}

	/**
	 * Sets isMatched to true
	 */
	public void setIsCardMatched() {
		isMatched = true;
	}

}
