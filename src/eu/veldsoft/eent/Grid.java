/*
* Grid.java
*
* Created on March 11, 2003, 10:43 AM
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

import java.lang.*;
import javax.swing.*;
//import javax.swing.event.*;
//import javax.swing.border.*;
//import java.awt.event.*;

public class Grid extends JButton implements Runnable {
	public static final int SET = 0;
	public static final int TEM = 1;
	public static final int FARM = 2;
	public static final int MARK = 3;
	public static final int STOP = 4;
	public static final int WATER = 5;
	public static final int GROUND = 6;
	public static final int TERS = 7;
	public static final int CAT = 8;
	
	public static final int P_KING = 9;
	public static final int P_PRIEST = 10;
	public static final int P_FARMER = 11;
	public static final int P_TRADER =12;
	
	public static final int C_KING = 13;
	public static final int C_PRIEST = 14;
	public static final int C_FARMER = 15;
	public static final int C_TRADER = 16;
	
	public static final int MON1 = 17;
	public static final int MON2 = 18;
	public static final int MON3 = 19;
	public static final int MON4 = 20;
	
	public static final int unif = 21;
	
	private int type;
	private Tile placedTile;
	private Kingdom kingdom;
        private Grid cause_conflict;
        private Player conflict_player;
	
	/** create a empty grid with a placedTile for use
	*   with setTile(Grid selected) in ComputerPlayer
	*/
	public Grid(Tile civ, String cmd)
	{
		this.placedTile = (Tile)civ;
		this.setActionCommand(cmd);
	}
	
	/** Create a new instance of a Grid.
	* Give an optional type
	* Next give postition and size
	* and last a actionCommand
	*/
	public Grid(int newtype,int x,int y,int width,int height,String cmd, Kingdom k_dom)
	{
		this.type=newtype;
		this.setBounds(x, y, width, height);
		this.setActionCommand(cmd);
		this.setType(type);
		this.kingdom=k_dom;
	}
	
	/** Change the displayed ImageIcon on a grid */
	public void setType(int t)
	{
		this.type = t;
		if (this.type == WATER)
		{
			this.setIcon(Board.images[WATER]);
		}
		else if (this.type == GROUND)
		{
			this.setIcon(Board.images[GROUND]);
		}
		else if (this.type == TERS) 
		{
			this.setIcon(Board.images[TERS]);
		}
		else if (this.type == SET) 
		{
			this.setIcon(Board.images[SET]);
		}
		else if (this.type == TEM) 
		{
			this.setIcon(Board.images[TEM]);
		}
		else if (this.type == FARM) 
		{
			this.setIcon(Board.images[FARM]);
		}
		else if (this.type == MARK) 
		{
			this.setIcon(Board.images[MARK]);
		}
		else if (this.type == STOP) 
		{
			this.setIcon(Board.images[STOP]);
		}
		else if (this.type == CAT) 
		{
			this.setIcon(Board.images[CAT]);
		}
		else if (this.type == P_KING) 
		{
			this.setIcon(Board.images[P_KING]);
		}
		else if (this.type == P_PRIEST) 
		{
			this.setIcon(Board.images[P_PRIEST]);
		}
		else if (this.type == P_FARMER) 
		{
			this.setIcon(Board.images[P_FARMER]);
		}
		else if (this.type == P_TRADER) 
		{
			this.setIcon(Board.images[P_TRADER]);
		}
		else if (this.type == MON1) 
		{
			this.setIcon(Board.images[MON1]);
		}
		else if (this.type == MON2) 
		{
			this.setIcon(Board.images[MON2]);
		}
		else if (this.type == MON3) 
		{
			this.setIcon(Board.images[MON3]);
		}
		else if (this.type == MON4) 
		{
			this.setIcon(Board.images[MON4]);
		}
		else if (this.type == unif) 
		{
			this.setIcon(Board.images[unif]);
		}
		else if (this.type == C_KING) 
		{
			this.setIcon(Board.images[C_KING]);
		}
		else if (this.type == C_PRIEST) 
		{
			this.setIcon(Board.images[C_PRIEST]);
		}
		else if (this.type == C_FARMER) 
		{
			this.setIcon(Board.images[C_FARMER]);
		}
		else if (this.type == C_TRADER) 
		{
			this.setIcon(Board.images[C_TRADER]);
		}
	}
	
	/** overloading setTile to work with Grids */
	public boolean setTile(Grid selected)
	{
		String gridText = selected.getActionCommand();
		int gridNumber = Integer.parseInt((this.getActionCommand()).substring(0,1));
		//System.out.println(gridNumber);
		if (gridText.endsWith("B"))
		{
			//Tigris2.infolabel.setText(selected.getGridType()+"");
			if (this.hasTile())
			{
				if ((this.getTile()).getTileType() == Tile.CIV_TILE &&
				(this.getActionCommand()).endsWith("P"))
				{
					if ((((CivTile)this.getTile()).getCivType() != CivTile.FARM &&
					selected.getGridType() == Grid.WATER) ||
					(((CivTile)this.getTile()).getCivType() == CivTile.FARM &&
					selected.getGridType() != Grid.WATER))
					{
						Tigris2.infolabel.setText("Cannot be placed here");
						return false;
					}
					else if (selected.getGridType() == Grid.WATER ||
					selected.getGridType() == Grid.GROUND)
					{
						/* debug info
						System.out.println("-----------------------------------------");
						*/
						/* call checkKingdoms to choose which action
						* should be done
						*/
						int temp = Board.checkKingdom(this, selected);
						if (temp >= 0)
						{
							selected.setTile(getTile());
							Board.getKingdom(temp).addCivTile(selected);
							Board.Monument(selected);
							this.removePlayerTile();
							Board.currentPlayer.removeCivTile(gridNumber);
							Tigris2.checkPlayerTurn();
							Tigris2.infolabel.setText("Playing");
							Board.checkPoints(selected);
							return true;
						}
						else if (temp == Board.UNIFICATION)
						{
							System.out.println("unification");
                                                        System.out.println(Board.currentPlayer);
							Board.unification(this, selected);
							return true;
                                                        //selected.setType(Grid.unif);
							//unification code here
						}
						else if (temp == Board.EXTERNAL_CONFLICT)
						{
                					//Board.conflict=true;
                                                        cause_conflict=selected;
                                                        cause_conflict.setTile(getTile());
                                                        conflict_player=Board.currentPlayer;							Board.conflict=true;
                                                        //selected.setTile(getTile());
                                                        System.out.println("external conflict");
                                                        
                                                        
                                                        if (this.getActionCommand().endsWith("B"))
                                                        this.removeBoardTile();
                                                        else {
                                                        this.removePlayerTile();
                                                        }
                                                        Thread th1 = new Thread(this,"ext_conflict");
                                                        th1.start();
                                                       // if (Board.externalConflict(Board.currentPlayer,selected))
                                                         //  System.out.println("conflict is solved");
                                                        
							//external conflict code here
						}
						
						//System.out.println(gridNumber);
						//Board.creatKingdoms(selected);
					}
					else
					{
						Tigris2.infolabel.setText("Already used. Please select another location.");
						return false;
					}
				}
				
				if ((this.getTile()).getTileType() == Tile.CAT_TILE &&
				(this.getActionCommand()).endsWith("C"))
				{
					if (((this.getTile()).getTileType() == CatTile.CAT_TILE &&
					selected.getGridType() == Grid.WATER) || ((this.getTile()).getTileType() == CatTile.CAT_TILE &&
					selected.getGridType() == Grid.GROUND) || ((this.getTile()).getTileType() == CatTile.CAT_TILE &&
					selected.getTile().getTileType() == Tile.CIV_TILE))
					{
						/* debug info
						System.out.println("-----------------------------------------");
						*/
						selected.setTile(this.getTile());
						selected.setKingdom(null);
						
						this.removePlayerTile();
						if (Board.currentPlayer.isCPU())
						{
							Board.currentPlayer.removeCatTile(gridNumber);
						}
						else
						{
							Board.currentPlayer.removeCatTile(gridNumber -10);
						}
						Tigris2.infolabel.setText("Playing");
						Tigris2.checkPlayerTurn();
						return true;
					}
					else
					{
						Tigris2.infolabel.setText("Already used. Please select another location.");
						return false;
					}
				}
				
				if ((this.getTile()).getTileType() == Tile.LEAD_TILE)
				{
					if (selected.getGridType() == Grid.GROUND &&
					Board.isNearATemple(selected))
					{
						/* call checkKingdoms to choose which action
						* should be done
						*/
						int temp = Board.checkKingdom(this, selected);
						if (temp >= 0)
						{
							/* debug info
							System.out.println("-----------------------------------------");
							System.out.println(this.getActionCommand() +
							" in " + this.getKingdom() + " is " + this.inKingdom());
							System.out.println("kingdom number " + temp);
							System.out.println("place " + selected.getActionCommand() +
							" in " + Board.getKingdom(temp));
							*/
							if ((this.getActionCommand()).endsWith("L"))
							{
								selected.setTile(this.getTile());
								Board.getKingdom(temp).addLeader(selected);
								this.removePlayerTile();
							}
							else
							{
								if (Board.getKingdom(temp) == this.getKingdom())
								{
									selected.setTile(this.getTile());
									/* debug info
									System.out.println("trying to remove " +
									this.getActionCommand() +  " from " +
									this.kingdom);
									*/
									(this.kingdom).removeGridFromKingdom(this);
									Board.getKingdom(temp).addLeader(selected);
								}
								else
								{
									selected.setTile(this.getTile());
									Board.getKingdom(temp).addLeader(selected);
									/* debug info
									System.out.println("trying to remove " +
									this.getActionCommand() +  " from " +
									this.kingdom);
									*/
									(this.kingdom).removeGridFromKingdom(this);
								}
							}
						}
						else if (temp == Board.INTERNAL_CONFLICT)
						{
                					//Board.conflict=true;
                                                        cause_conflict=selected;
                                                        cause_conflict.setTile(getTile());
                                                        conflict_player=Board.currentPlayer;
                                                        if (this.getActionCommand().endsWith("B")){
                                                        this.removeBoardTile();
                                                        }
                                                        else {
                                                        this.removePlayerTile();
                                                        }
                                                        //System.out.println("internal conflict");
                                                        //Tigris2.infolabel.setText("select support temples an pressd the OK button");
                                                        Thread th = new Thread(this,"int_conflict");
                                                        th.start();
							
							//internal conflict code here
						}
						
						if (Board.currentPlayer.isCPU())
						{
							Board.currentPlayer.removeLeadTile(gridNumber);
						}
						else
						{
							Board.currentPlayer.removeLeadTile(gridNumber - 6);
						}
						
						//Board.creatKingdoms(selected); 
						Tigris2.infolabel.setText("Playing");
						Tigris2.checkPlayerTurn();
						return true;
					}
					else
					{
						Tigris2.infolabel.setText("A Leader cant be placed here");
						return false;
					}
				}
			}
		}
		return false;
	}
	
	/** Selected the right setType command based on
	* the placedTile on the grid */
	public void placeTile(Tile tile)
	{
		placedTile = tile;
		if (placedTile != null)
		{
			switch(placedTile.getTileType())
			{
				case Tile.CAT_TILE:
				setType(CAT);
				break;
				case Tile.CIV_TILE:
				switch (((CivTile)placedTile).getCivType())
				{
					case CivTile.TEMPLE:
					setType(TEM);
					break;
					case CivTile.MARKET:
					setType(MARK);
					break;
					case CivTile.SETTLEMENT:
					setType(SET);
					break;
					case CivTile.FARM:
					setType(FARM);
					break;
				}
				break;
				case Tile.TEM_TILE:
				setType(TERS);
				break;
				case Tile.LEAD_TILE:
				if ((((LeadTile)placedTile).getPlayer()).isCPU())
				{
					switch(((LeadTile)placedTile).getLeaderType())
					{
						case LeadTile.LEADER_KING:
						setType(C_KING);
						break;
						case LeadTile.LEADER_PRIEST:
						setType(C_PRIEST);
						break;
						case LeadTile.LEADER_FARMER:
						setType(C_FARMER);
						break;
						case LeadTile.LEADER_TRADER:
						setType(C_TRADER);
						break;
					}
				}
				else
				{
					switch(((LeadTile)placedTile).getLeaderType())
					{
						case LeadTile.LEADER_KING:
						setType(P_KING);
						break;
						case LeadTile.LEADER_PRIEST:
						setType(P_PRIEST);
						break;
						case LeadTile.LEADER_FARMER:
						setType(P_FARMER);
						break;
						case LeadTile.LEADER_TRADER:
						setType(P_TRADER);
						break;
					}
				}
				break;
			}
		}
		else
		{
			if (Board.currentPlayer.isCPU())
			{
				//do nothing
			}
			else
			{
				setType(STOP);
			}
		}
	}
	
	/** Using placedTile to only allow placing of tiles
	* on a free grid, placing of Catastrophe Tiles, or
	* on the 6 grids of the player. Also changing
	* the placed tile on the grid as a result */
	public void setTile(Tile tile)
	{
		if (this.hasTile())
		{
			if ((this.getActionCommand()).endsWith("P")) 
			{
				placeTile(tile);
			}
			
			else if ((placedTile.getTileType() != Tile.CAT_TILE) &&
			(tile.getTileType() == Tile.CAT_TILE))
			{
				placeTile(tile);
			}
		}
		else
		{
			placeTile(tile);
		}
	}
	
	/** Remove the selected Tile of a humanplayer
	* from the interface
	*/
	public void removePlayerTile()
	{
		placedTile = null;
		if (Board.currentPlayer.isCPU())
		{
			//do nothing
		}
		else
		{
			setType(STOP);
		}
	}
	
	/** Remove the a Tile from a Grid on the Gameboard
	* this method should not be used directly. Use
	* Kingdom.removeGridFromKingdom instead
	*/
	public void removeBoardTile() 
	{
		switch (placedTile.getTileType()) 
		{
			case Tile.CAT_TILE:
			break;
			case Tile.LEAD_TILE:
			placedTile = null;
			setType(GROUND);
			break;
			case Tile.CIV_TILE:
			switch(((CivTile)placedTile).getCivType()) 
			{
				case CivTile.FARM:
				placedTile = null;
				setType(WATER);
				break;
				default:
				placedTile = null;
				setType(GROUND);
				break;
			}
			case Tile.TEM_TILE:
			break;
		}
	}
	
	/** Returns the placedTile that is available */
	public Tile getTile() 
	{
		return placedTile;
	}
	
	/** checks if a Tile has been placed */
	public boolean hasTile() 
	{
		if (placedTile == null) 
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	/** Returns the type of Grid there a three
	* types, Water, Ground and Maintemples */
	public int getGridType() 
	{
		return this.type;
	}
	
	/** check to see if a grid is associated
	* with a Kingdom
	*/
	public boolean inKingdom()
	{
		if (kingdom == null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	/** returns the kingdom, this is null when
	* no kingdom is available
	*/
	public Kingdom getKingdom()
	{
		return kingdom;
	}
	
	/** set the kingdom for a grid */
	public void setKingdom(Kingdom king)
	{
		kingdom = king;
	}
        
        /** When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see     java.lang.Thread#run()
         *
         */
        public void run()
	{
            System.out.println(Thread.currentThread().getName());
            if (Thread.currentThread().getName().equals("int_conflict"))
	    {
		    //Thread myThread = Thread.currentThread();
		    
		    try {
			    while(Board.pressed != Tigris2.OK)
			    {
				    Tigris2.infolabel.setText("select support temples and pressd the OK button");
				    Thread.sleep(2000);
				    //System.out.println("went to sleep");
			    }
		    }
		    catch (InterruptedException e)
		    {
			    // the VM doesn't want us to sleep anymore,
			    // so get back to work
		    }
		    
		    
		    //System.out.println("conflict"+cause_conflict);
		    //System.out.println(conflict_player);
		    Board.conflict(conflict_player,cause_conflict);
		    Tigris2.infolabel.setText("conflict done");
		    //System.out.println("conflict done");
		    
	    }
            else if (Thread.currentThread().getName().equals("ext_conflict"))
	    {
		    try
		    {
			    while(Board.pressed != Tigris2.OK)
			    {
				    Tigris2.infolabel.setText("select support tiles and pressd the OK button");
				    Thread.sleep(2000);
				    //System.out.println("went to sleep");
			    }
		    }
		    catch (InterruptedException e)
		    {
			    // the VM doesn't want us to sleep anymore,
			    // so get back to work
		    }
		    //System.out.println("conflict"+cause_conflict);
		    //System.out.println(conflict_player);
		    Board.externalConflict(conflict_player,cause_conflict);
		    //Tigris2.infolabel.setText("conflict done");
		    //System.out.println("conflict done");
	    }
	}
}
