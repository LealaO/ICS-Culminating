package OpaoPham;

import javafx.scene.control.Button;

/**
 * 
 * @author Opao, Leala & Pham, Julie
 *
 */
public class Card extends Button {
	// TODO Auto-generated constructor stub
	private int row;
	private int column;
	private String cardLocName ;
	
	/**
	 * 
	 * @param cln
	 */
	public Card(String cln) {
		super();
		cardLocName = cln;
	}
	
	/**
	 * 
	 * @param r
	 * @param c
	 * @param cln
	 */
	public Card(int r, int c, String cln) {
		super();
		row = r;
		column = c;
		cardLocName = cln;
	}
	
	/**
	 * 
	 * @param r
	 * @param c
	 */
	public void setRowColumn(int r, int c) {
		row = r;
		column = c;		
	}
	
	/**
	 * 
	 * @return
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getColumn() {
		return column;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCardLocName() {
		return cardLocName;
	}

	// to-do toString?
}
