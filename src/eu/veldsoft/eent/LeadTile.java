/*
  It is distributed under the GNU Public Licence (GPL) version 2.  See
  http://www.gnu.org/ for further details of the GPL.
 */
package eu.veldsoft.eent;

/**
 * Write a description of class Leader here.
 * 
 * @author (Mohannad)
 * @version (1.1)
 */
public class LeadTile extends Tile {
	public static final int LEADER_KING = 1110;
	public static final int LEADER_PRIEST = 1111;
	public static final int LEADER_FARMER = 1112;
	public static final int LEADER_TRADER = 1113;

	public static final int LEAD = 1110;

	private int leaderType;
	private Player owner;

	/**
	 * Constructor for objects of class Leader
	 */
	public LeadTile(int newType, Player own) {
		super(Tile.LEAD_TILE);
		this.leaderType = newType;
		this.owner = own;
	}

	/** return the type of leader */
	public int getLeaderType() {
		return this.leaderType;
	}

	/** return the player associated with the leadtile */
	public Player getPlayer() {
		return this.owner;
	}

}
