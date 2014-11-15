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
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.event.*;
public class BagListener implements ActionListener {
    
    public void actionPerformed(ActionEvent e) {
        Bag b = (Bag)e.getSource();
        //        b.setImage(Board.currentPlayer);
        
//        Tigris2.jCheckBox1.setEnabled(true);
//        Tigris2.jCheckBox2.setEnabled(true);
//        Tigris2.jCheckBox3.setEnabled(true);
//        Tigris2.jCheckBox4.setEnabled(true);
//        Tigris2.jCheckBox5.setEnabled(true);
//        Tigris2.jCheckBox6.setEnabled(true);
        
        if (Tigris2.jCheckBox1.isSelected() ) {
            Board.currentPlayer.removeCivTile(0);
            Tigris2.uic[0].removePlayerTile();
            b.setImage(Board.currentPlayer);
 //            Tigris2.jCheckBox1.setEnabled(false);
        }
        
        if (Tigris2.jCheckBox2.isSelected() ) {
            Board.currentPlayer.removeCivTile(1);
            Tigris2.uic[1].removePlayerTile();
            b.setImage(Board.currentPlayer);
        }
        if (Tigris2.jCheckBox3.isSelected() ) {
            Board.currentPlayer.removeCivTile(2);
            Tigris2.uic[2].removePlayerTile();
            b.setImage(Board.currentPlayer);
        }
        
        if (Tigris2.jCheckBox4.isSelected() ) {
            Board.currentPlayer.removeCivTile(3);
            Tigris2.uic[3].removePlayerTile();
            b.setImage(Board.currentPlayer);
        }
        
        if (Tigris2.jCheckBox5.isSelected() ) {
            Board.currentPlayer.removeCivTile(4);
            Tigris2.uic[4].removePlayerTile();
            b.setImage(Board.currentPlayer);
        }
        if (Tigris2.jCheckBox6.isSelected() ) {
            Board.currentPlayer.removeCivTile(5);
            Tigris2.uic[5].removePlayerTile();
            b.setImage(Board.currentPlayer);
        }
       
//        else {
//        Tigris2.jCheckBox1.setEnabled(false);
//        Tigris2.jCheckBox2.setEnabled(false);
//        Tigris2.jCheckBox3.setEnabled(false);
//        Tigris2.jCheckBox4.setEnabled(false);
//        Tigris2.jCheckBox5.setEnabled(false);
//        Tigris2.jCheckBox6.setEnabled(false);
//        }
        
        /* Change State of the Checkboxes to false
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

