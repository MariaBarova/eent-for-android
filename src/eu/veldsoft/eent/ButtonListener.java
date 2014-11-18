/*
 * ButtonListener.java
 *
 * Created on March 9, 2003, 2:37 PM
 */
/*
 It is distributed under the GNU Public Licence (GPL) version 2.  See
 http://www.gnu.org/ for further details of the GPL.
 */
package eu.veldsoft.eent;

/**
 *
 * @author  ali
 */
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class ButtonListener implements ActionListener {
	JButton sel;
	Grid selected;

	/** remember the previous selected button */
	public static Grid lastSelected;

	String gridText;

	public void actionPerformed(ActionEvent e) {
		if (Board.currentPlayer != null) {
			if (Board.currentPlayer.isCPU()) {
				// do nothing
			} else {
				sel = (JButton) e.getSource();
				gridText = sel.getActionCommand();
				Board.pressed = gridText;
				// Tigris2.infolabel.setText(gridText);
				if ((gridText == Tigris2.OK)) {
					// Board.getSelected();
				}

				else if (gridText == Tigris2.CANCEL) {

					// do nothing

				} else {
					selected = (Grid) sel;
					if (lastSelected != null) {
						lastSelected.setTile(selected);

						if ((lastSelected.getActionCommand()).endsWith("B")) {
							lastSelected.setBorder(null);
							// Board.lastSelected=null;
							// selected=null;
						} else {
							lastSelected.setBorder(BorderFactory
									.createEtchedBorder());
						}
					}
				}
				selected.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,
						Color.green));
				lastSelected = selected;
			}
		}
	}
}
