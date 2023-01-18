package OpaoPham;

import javafx.scene.control.Button;

public class NewButton extends Button {
		// TODO Auto-generated constructor stub
		private int row;
		private int column;

	public NewButton(int r, int c) {
		super();
		row = r;
		column = c;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	// to-do toString?
}
