package OpaoPham;

import javafx.scene.control.Button;

public class Card extends Button {
	// TODO Auto-generated constructor stub
	private int row;
	private int column;
	private String cardLocName ;

	public Card(String cln) {
		super();
		cardLocName = cln;
	}
		
	public Card(int r, int c, String cln) {
		super();
		row = r;
		column = c;
		cardLocName = cln;
	}

	public void setRowColumn(int r, int c) {
		row = r;
		column = c;		
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public String getCardLocName() {
		return cardLocName;
	}

	// to-do toString?
}
