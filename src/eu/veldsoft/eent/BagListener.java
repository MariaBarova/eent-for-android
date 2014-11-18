/*
 * BagListener.java
 *
 * Created on March 17, 2003, 1:26 PM
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

public class BagListener implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		Bag b = (Bag) e.getSource();
		
		if (Tigris2.jCheckBox1.isSelected()) {
			Board.currentPlayer.removeCivTile(0);
			Tigris2.uic[0].removePlayerTile();
			b.setImage(Board.currentPlayer);
		}

		if (Tigris2.jCheckBox2.isSelected()) {
			Board.currentPlayer.removeCivTile(1);
			Tigris2.uic[1].removePlayerTile();
			b.setImage(Board.currentPlayer);
		}
		if (Tigris2.jCheckBox3.isSelected()) {
			Board.currentPlayer.removeCivTile(2);
			Tigris2.uic[2].removePlayerTile();
			b.setImage(Board.currentPlayer);
		}

		if (Tigris2.jCheckBox4.isSelected()) {
			Board.currentPlayer.removeCivTile(3);
			Tigris2.uic[3].removePlayerTile();
			b.setImage(Board.currentPlayer);
		}

		if (Tigris2.jCheckBox5.isSelected()) {
			Board.currentPlayer.removeCivTile(4);
			Tigris2.uic[4].removePlayerTile();
			b.setImage(Board.currentPlayer);
		}
		if (Tigris2.jCheckBox6.isSelected()) {
			Board.currentPlayer.removeCivTile(5);
			Tigris2.uic[5].removePlayerTile();
			b.setImage(Board.currentPlayer);
		}
		/*
		 * Change State of the Checkboxes to false
		 */
		Tigris2.jCheckBox1.setSelected(false);
		Tigris2.jCheckBox2.setSelected(false);
		Tigris2.jCheckBox3.setSelected(false);
		Tigris2.jCheckBox4.setSelected(false);
		Tigris2.jCheckBox5.setSelected(false);
		Tigris2.jCheckBox6.setSelected(false);

		Tigris2.checkPlayerTurn();
	}
}
