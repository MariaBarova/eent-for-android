/*
 * ButtonListener2.java
 *
 * Created on April 29, 2003, 6:05 PM
 */
/*
 It is distributed under the GNU Public Licence (GPL) version 2.  See
 http://www.gnu.org/ for further details of the GPL.
 */
package eu.veldsoft.eent;

/**
 *
 * @author  MK
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener2 implements ActionListener {

	Grid Checkbox[] = new Grid[6];

	public void actionPerformed(ActionEvent e) {
		if (Tigris2.jCheckBox1.isSelected()) {
			Checkbox[0] = (Tigris2.uic[0]);
			System.out.println(Checkbox[0]);
			Board.currentPlayer.removeCivTile(0);
			Tigris2.uic[0].removePlayerTile();
		}
		if (Tigris2.jCheckBox2.isSelected()) {
			Checkbox[1] = (Tigris2.uic[1]);
			System.out.println(Checkbox[1]);
			Board.currentPlayer.removeCivTile(1);
			Tigris2.uic[1].removePlayerTile();
		}
		if (Tigris2.jCheckBox3.isSelected()) {
			// Checkbox[2].setTile(Tigris2.uic[2]);
			Checkbox[2] = (Tigris2.uic[2]);
			System.out.println(Checkbox[2]);
			Board.currentPlayer.removeCivTile(2);
			Tigris2.uic[2].removePlayerTile();
		}
		if (Tigris2.jCheckBox4.isSelected()) {
			Checkbox[3] = (Tigris2.uic[3]);
			System.out.println(Checkbox[3]);
			Board.currentPlayer.removeCivTile(3);
			Tigris2.uic[3].removePlayerTile();
		}
		if (Tigris2.jCheckBox5.isSelected()) {
			Checkbox[4] = (Tigris2.uic[4]);
			System.out.println(Checkbox[4]);
			Board.currentPlayer.removeCivTile(4);
			Tigris2.uic[4].removePlayerTile();
		}
		if (Tigris2.jCheckBox6.isSelected()) {
			Checkbox[5] = (Tigris2.uic[5]);
			System.out.println(Checkbox[5]);
			Board.currentPlayer.removeCivTile(5);
			Tigris2.uic[5].removePlayerTile();
		} else {
			// do nothing
		}

	}

	/** Return the selected Tiles used in add support */
	public Grid[] Getselected(ActionEvent e) {
		return Checkbox;
	}

	/** Creates a new instance of ButtonListener2 */
	public ButtonListener2() {
	}

}
