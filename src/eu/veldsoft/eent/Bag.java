/*
  It is distributed under the GNU Public Licence (GPL) version 2.  See
  http://www.gnu.org/ for further details of the GPL.
*/
package eu.veldsoft.eent;

/**
 * Write a description of class Bag here.
 *
 * @author Mohannad
 * @version v1.3
 */
import javax.swing.*;
import java.util.Vector;
import java.util.*;

import java.awt.*;

import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.event.*;

public class Bag extends JButton{
    
    private static final int SETTLEMENTS = 0;
    private static final int TEMPLES = 1;
    private static final int FARMS = 2;
    private static final int MARKETS = 3;
    
    private static final int TOTAL_SET = 30;
    private static final int TOTAL_TEM = 57;
    private static final int TOTAL_FAR = 36;
    private static final int TOTAL_MAR = 30;
    
    private ArrayList[] bagTiles;
    
    private int totalTiles,
    newTiles;
    
    private CivTile[] newTileArray;
    
    /** create a new instance of bag */
    public Bag() {
        bagTiles = new ArrayList[4];
        bagTiles[SETTLEMENTS] = new ArrayList(TOTAL_SET);
        for (int i = 0; i < TOTAL_SET; i++) {
            CivTile civ = new CivTile(CivTile.SETTLEMENT);
            bagTiles[SETTLEMENTS].add(civ);
        }
        bagTiles[TEMPLES] = new ArrayList(TOTAL_TEM);
        for (int i = 0; i < TOTAL_TEM; i++) {
            CivTile civ = new CivTile(CivTile.TEMPLE);
            bagTiles[TEMPLES].add(civ);
        }
        bagTiles[FARMS] = new ArrayList(TOTAL_FAR);
        for (int i = 0; i < TOTAL_FAR; i++) {
            CivTile civ = new CivTile(CivTile.FARM);
            bagTiles[FARMS].add(civ);
        }
        bagTiles[MARKETS] = new ArrayList(TOTAL_MAR);
        for (int i = 0; i < TOTAL_MAR; i++) {
            CivTile civ = new CivTile(CivTile.MARKET);
            bagTiles[MARKETS].add(civ);
        }
        /*
        System.out.println(bagTiles.length);
        System.out.println(bagTiles[MARKETS].size());
         */
        newTiles = 6;
        
        totalTiles = getTotalTiles();
    }
    
    /** return the total of tiles still in the bag */
    public int getTotalTiles() {
        int temp = 0;
        for (int i = 0; i < bagTiles.length; i++) {
            temp = temp + bagTiles[i].size();
        }
        return temp;
    }
    
    /** return several random civtiles from the bag
    * the total number of return tiles is variable
    */
    public CivTile[] getNewTiles(int number) {
        if (number >= totalTiles) {
            newTiles = totalTiles;
        }
        else {
            newTiles = number;
        }
        CivTile[] numberTiles = new CivTile[newTiles];
        int RandomArray[] = new int[newTiles];
        int i = 0;
        if (totalTiles != 0) {
            while ((i < newTiles) && (totalTiles > 0)) {
                RandomArray[i] = 1 + (int)(Math.random() * 4);
		/* debug info
                System.out.println("random"+i+ "=" + RandomArray[i]);
                */
		
                switch (RandomArray[i]) {
                    case 1:
                        if (bagTiles[SETTLEMENTS].size() > 0) {
                            numberTiles[i] = (CivTile)bagTiles[SETTLEMENTS].remove(0);
                            bagTiles[SETTLEMENTS].trimToSize();
                            i++;
                        }
                        break;
                    case 2:
                        if (bagTiles[TEMPLES].size() > 0) {
                            numberTiles[i] = (CivTile)bagTiles[TEMPLES].remove(0);
                            bagTiles[TEMPLES].trimToSize();
                            i++;
                        }
                        break;
                    case 3:
                        if (bagTiles[FARMS].size() > 0) {
                            numberTiles[i] = (CivTile)bagTiles[FARMS].remove(0);
                            bagTiles[FARMS].trimToSize();
                            i++;
                        }
                        break;
                    case 4:
                        if (bagTiles[MARKETS].size() > 0) {
                            numberTiles[i] = (CivTile)bagTiles[MARKETS].remove(0);
                            bagTiles[MARKETS].trimToSize();
                            i++;
                        }
                        break;
                }
                
                totalTiles = getTotalTiles();
		/* debug info
                System.out.println("settlementTile="+ bagTiles[SETTLEMENTS].size());
                System.out.println("templeTile="+ bagTiles[TEMPLES].size());
                System.out.println("farmTile="+ bagTiles[FARMS].size() );
                System.out.println("marketTile=" + bagTiles[MARKETS].size());
                System.out.println("totalTile="+ totalTiles);
                */
		
                /** Change the bag icon when durning the game */
                
                if ( totalTiles == 105 )  {
                    Tigris2.tileBag.setIcon(new ImageIcon(getClass().getResource("/images/bag-half.gif")));
                }
                if ( totalTiles == 80 )  {
                    Tigris2.tileBag.setIcon(new ImageIcon(getClass().getResource("/images/bag-low.gif")));
                }
                if ( totalTiles == 20 )  {
                    Tigris2.tileBag.setIcon(new ImageIcon(getClass().getResource("/images/bag-empty.gif")));
                }
            }
        }
        return numberTiles;
    }
    
    /** add Civtiles to a players hand for each
    * used/empty/swapped tile
    */
    public CivTile[] setPlayerTiles(Player p) {
        int temp = 0;
        CivTile[] tempciv = p.getPlayerTiles();
        for (int i = 0; i < tempciv.length;i++) {
            if (tempciv[i] == null) {
                temp++;
            }
        }
	/* debug info
        System.out.println(temp + " new Tiles");
	*/
        newTileArray = getNewTiles(temp);
        for(int i = 0; i < tempciv.length;i++) {
            if (tempciv[i] == null) {
                tempciv[i] = newTileArray[--temp];
            }
        }
        p.setCivTiles(tempciv);
        return tempciv;
        
    }
    
    /** change the icon on the gameboard for the
    * humanplayer
    */
    public void setImage(Player p) {
        newTileArray = setPlayerTiles(p);
        for (int x = 0; x < newTileArray.length; x++) {
            Tigris2.uic[x].setTile(((Tile)newTileArray[x]));
        }
    }
}
