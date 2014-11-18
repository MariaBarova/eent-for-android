/*
 * Player.java
 *
 * Created on 24 maart 2003, 21:11
 */

/*
 It is distributed under the GNU Public Licence (GPL) version 2.  See
 http://www.gnu.org/ for further details of the GPL.
 */
package eu.veldsoft.eent;

/**
 * 
 * @author me
 */
public class Player {
	public static final int TRES_POINT = 100;
	public static final int PEOPLE_POINT = 101;
	public static final int TEMPLE_POINT = 102;
	public static final int FARM_POINT = 103;
	public static final int MARKET_POINT = 104;

	private final int POINT = 100;

	public static final int CPU = 1122;
	public static final int HUMAN = 2211;

	private LeadTile[] leaders;

	private CivTile[] civTiles;

	private CatTile[] catTiles;

	private int turn;

	private int[] score;

	private int playerType;

	/** Creates a new instance of Player */
	public Player(int p_type) {
		playerType = p_type;
		leaders = new LeadTile[4];
		civTiles = new CivTile[6];
		catTiles = new CatTile[2];
		catTiles[0] = new CatTile();
		catTiles[1] = new CatTile();
		turn = 0;
		score = new int[5];
	}

	/**
	 * check if current player is a computer controlled player
	 */
	public boolean isCPU() {
		if (playerType == CPU)
			return true;
		else
			return false;
	}

	/**
	 * returns the type of player. Computer or a human player
	 */
	public int getPlayerType() {
		return playerType;
	}

	/**
	 * return the leaders that not have been placed on the board
	 */
	public LeadTile[] getLeaders() {
		return leaders;
	}

	/**
	 * return the entire civtile array the player has in hand including the
	 * empty tiles
	 */
	public CivTile[] getPlayerTiles() {
		return civTiles;
	}

	public boolean setLeader(LeadTile lead) {
		switch (lead.getLeaderType()) {
		case LeadTile.LEADER_KING:
			if (leaders[LeadTile.LEADER_KING - LeadTile.LEAD] == null) {
				leaders[LeadTile.LEADER_KING - LeadTile.LEAD] = lead;
				return true;
			} else {
				return false;
			}
		case LeadTile.LEADER_PRIEST:
			if (leaders[LeadTile.LEADER_PRIEST - LeadTile.LEAD] == null) {
				leaders[LeadTile.LEADER_PRIEST - LeadTile.LEAD] = lead;
				return true;
			} else {
				return false;
			}
		case LeadTile.LEADER_TRADER:
			if (leaders[LeadTile.LEADER_TRADER - LeadTile.LEAD] == null) {
				leaders[LeadTile.LEADER_TRADER - LeadTile.LEAD] = lead;
				return true;
			} else {
				return false;
			}
		case LeadTile.LEADER_FARMER:
			if (leaders[LeadTile.LEADER_FARMER - LeadTile.LEAD] == null) {
				leaders[LeadTile.LEADER_FARMER - LeadTile.LEAD] = lead;
				return true;
			} else {
				return false;
			}
		default:
			return false;
		}
	}

	/**
	 * replace the leaders the player has in hand
	 */
	public boolean setLeaders(LeadTile[] lead) {
		if (lead.length == leaders.length) {
			leaders = lead;
			return true;
		}
		return false;
	}

	/** remove the leader from the players hand */
	public boolean removeLeadTile(int place) {
		if (place >= 0 && place < leaders.length) {
			leaders[place] = null;
			return true;
		}
		return false;
	}

	/**
	 * replace the civtiles the player has in hand
	 */
	public boolean setCivTiles(CivTile[] civ) {
		if (civ.length == civTiles.length) {
			civTiles = civ;
			return true;
		}
		return false;
	}

	/** remove a single CivTile from hand */
	public boolean removeCivTile(int place) {
		if (place >= 0 && place < civTiles.length) {
			civTiles[place] = null;
			return true;
		}
		return false;
	}

	/** get the cattiles in hand */
	public CatTile[] getCatTiles() {
		return catTiles;
	}

	/** remove a single CatTile from hand */
	public boolean removeCatTile(int place) {
		if (place >= 0 && place < catTiles.length) {
			catTiles[place] = null;
			return true;
		}
		return false;
	}

	/** increase the turn of the player */
	public void incr_turn() {
		turn++;
	}

	/** reset the turn of the player */
	public void reset_turn() {
		turn = 0;
	}

	/** checks if player has any turns left */
	public boolean hasTurn() {
		if (playerType == HUMAN) {
			if (turn >= 0 && turn < 1) {
				return true;
			}
			return false;
		} else {
			if (turn >= 0 && turn < 2) {
				return true;
			}
			return false;
		}
	}

	/** add a scored point to the player */
	public void incr_score(int point_type) {
		switch (point_type) {
		case TRES_POINT:
			score[point_type - POINT]++;
			break;
		case FARM_POINT:
			score[point_type - POINT]++;
			break;
		case MARKET_POINT:
			score[point_type - POINT]++;
			break;
		case TEMPLE_POINT:
			score[point_type - POINT]++;
			break;
		case PEOPLE_POINT:
			score[point_type - POINT]++;
			break;
		}
	}

	/** return the number of gained treasures */
	public int getTreasure() {
		return score[TRES_POINT - POINT];
	}

	/** return the number of PEOPLE_POINTS */
	public int getPeoplePoints() {
		return score[PEOPLE_POINT - POINT];
	}

	/** returns the number of temple_points */
	public int getTemplePoints() {
		return score[TEMPLE_POINT - POINT];
	}

	/** returns the number of farm_points */
	public int getFarmPoints() {
		return score[FARM_POINT - POINT];
	}

	/** returns the number of market_points */
	public int getMarketPoints() {
		return score[MARKET_POINT - POINT];
	}
}
