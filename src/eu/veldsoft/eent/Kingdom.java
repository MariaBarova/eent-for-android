/*
 * Kingdom.java
 *
 * Created on 24 maart 2003, 21:14
 */

/*
 It is distributed under the GNU Public Licence (GPL) version 2.  See
 http://www.gnu.org/ for further details of the GPL.
 */
package eu.veldsoft.eent;

/**
 *
 * @author  me
 *
 */
import java.util.Vector;

class Kingdom {
	private Vector<Grid> settlements;
	private Vector<Grid> farmers;
	private Vector<Grid> temples;
	private Vector<Grid> markets;
	public static int times = 0;

	public static final int NO_LEADER = 1001;
	public static final int HAS_LEADER = 2002;
	public static final int OTHER_KINGDOM = 3003;

	private int kingdomNum;

	private Vector<Kingdom> joinedWith;

	private Grid[] leaders;

	/** Creates a new instance of Kingdom */
	public Kingdom(int knum) {
		settlements = new Vector<Grid>();
		farmers = new Vector<Grid>();
		temples = new Vector<Grid>();
		markets = new Vector<Grid>();
		joinedWith = new Vector<Kingdom>();
		leaders = new Grid[4];
		kingdomNum = knum;
	}

	public void addCivTile(Grid grid) {
		CivTile civ = (CivTile) grid.getTile();
		switch (civ.getCivType()) {
		case CivTile.TEMPLE:
			temples.add(grid);
			grid.setKingdom(this);
			break;
		case CivTile.MARKET:
			markets.add(grid);
			grid.setKingdom(this);
			break;
		case CivTile.SETTLEMENT:
			settlements.add(grid);
			grid.setKingdom(this);
			break;
		case CivTile.FARM:
			farmers.add(grid);
			grid.setKingdom(this);
			break;
		}
	}

	public void removeGridFromKingdom(Grid grid) {
		Tile tile = grid.getTile();
		Vector<Kingdom> temp_vector = wanderKingdoms(null);
		switch (tile.getTileType()) {
		case Tile.LEAD_TILE:
			switch (((LeadTile) tile).getLeaderType()) {
			case LeadTile.LEADER_KING:
				int king = hasKing();
				if (king == HAS_LEADER) {
					leaders[LeadTile.LEADER_KING - LeadTile.LEAD]
							.removeBoardTile();
					leaders[LeadTile.LEADER_KING - LeadTile.LEAD] = null;
				} else if (king == OTHER_KINGDOM) {
					for (int i = 0; i < temp_vector.size(); i++) {
						if (((Kingdom) temp_vector.elementAt(i)).hasKing() == HAS_LEADER) {
							((Kingdom) temp_vector.elementAt(i))
									.removeGridFromKingdom(grid);
						}
					}
				}
				break;
			case LeadTile.LEADER_PRIEST:
				int priest = hasPriest();
				if (priest == HAS_LEADER) {
					leaders[LeadTile.LEADER_PRIEST - LeadTile.LEAD]
							.removeBoardTile();
					leaders[LeadTile.LEADER_PRIEST - LeadTile.LEAD] = null;
				} else if (priest == OTHER_KINGDOM) {
					for (int i = 0; i < temp_vector.size(); i++) {
						if (((Kingdom) temp_vector.elementAt(i)).hasPriest() == HAS_LEADER) {
							((Kingdom) joinedWith.elementAt(i))
									.removeGridFromKingdom(grid);
						}
					}
				}
				break;
			case LeadTile.LEADER_TRADER:
				int trader = hasTrader();
				if (trader == HAS_LEADER) {
					leaders[LeadTile.LEADER_TRADER - LeadTile.LEAD]
							.removeBoardTile();
					leaders[LeadTile.LEADER_TRADER - LeadTile.LEAD] = null;
				} else if (trader == OTHER_KINGDOM) {
					for (int i = 0; i < temp_vector.size(); i++) {
						if (((Kingdom) temp_vector.elementAt(i)).hasTrader() == HAS_LEADER) {
							((Kingdom) joinedWith.elementAt(i))
									.removeGridFromKingdom(grid);
						}
					}
				}
				break;
			case LeadTile.LEADER_FARMER:
				int farmer = hasFarmer();
				if (farmer == HAS_LEADER) {
					leaders[LeadTile.LEADER_FARMER - LeadTile.LEAD]
							.removeBoardTile();
					leaders[LeadTile.LEADER_FARMER - LeadTile.LEAD] = null;
				} else if (farmer == OTHER_KINGDOM) {
					for (int i = 0; i < temp_vector.size(); i++) {
						if (((Kingdom) temp_vector.elementAt(i)).hasFarmer() == HAS_LEADER) {
							((Kingdom) joinedWith.elementAt(i))
									.removeGridFromKingdom(grid);
						}
					}
				}
				break;
			}
			break;
		case Tile.CIV_TILE:
			switch (((CivTile) tile).getCivType()) {
			case CivTile.SETTLEMENT:
				for (int i = 0; i < settlements.size(); i++) {
					if (((Grid) settlements.elementAt(i)).equals(grid)) {
						Grid g = (Grid) settlements.remove(i);
						g.removeBoardTile();
					}
				}
				break;
			case CivTile.TEMPLE:
				for (int i = 0; i < temples.size(); i++) {
					if (((Grid) temples.elementAt(i)).equals(grid)) {
						Grid g = (Grid) temples.remove(i);
						g.removeBoardTile();
					}
				}
				break;
			case CivTile.MARKET:
				for (int i = 0; i < markets.size(); i++) {
					if (((Grid) markets.elementAt(i)).equals(grid)) {
						Grid g = (Grid) markets.remove(i);
						g.removeBoardTile();
					}
				}
				break;
			case CivTile.FARM:
				for (int i = 0; i < farmers.size(); i++) {
					if (((Grid) farmers.elementAt(i)).equals(grid)) {
						Grid g = (Grid) farmers.remove(i);
						g.removeBoardTile();
					}
				}
				break;
			}
			break;
		}
	}

	public void removeAllSettlements() {
		for (int i = 0; i < settlements.size(); i++) {
			Grid g = (Grid) settlements.remove(i);
			g.removeBoardTile();
		}
	}

	public void removeAllMarkets() {
		for (int i = 0; i < markets.size(); i++) {
			Grid g = (Grid) markets.remove(i);
			g.removeBoardTile();
		}
	}

	public void removeAllTemples() {
		for (int i = 0; i < temples.size(); i++) {
			Grid g = (Grid) temples.remove(i);
			g.removeBoardTile();
		}
	}

	public void removeAllFarmers() {
		for (int i = 0; i < farmers.size(); i++) {
			Grid g = (Grid) farmers.remove(i);
			g.removeBoardTile();
		}
	}

	public void addLeader(Grid grid) {
		LeadTile lead = (LeadTile) grid.getTile();

		switch (lead.getLeaderType()) {
		case LeadTile.LEADER_KING:
			if (hasKing() == NO_LEADER) {
				grid.setKingdom(this);
				leaders[LeadTile.LEADER_KING - LeadTile.LEAD] = grid;
			}
			break;
		case LeadTile.LEADER_PRIEST:
			if (hasPriest() == NO_LEADER) {
				grid.setKingdom(this);
				leaders[LeadTile.LEADER_PRIEST - LeadTile.LEAD] = grid;
			}
			break;
		case LeadTile.LEADER_TRADER:
			if (hasTrader() == NO_LEADER) {
				grid.setKingdom(this);
				leaders[LeadTile.LEADER_TRADER - LeadTile.LEAD] = grid;
			}
			break;
		case LeadTile.LEADER_FARMER:
			if (hasFarmer() == NO_LEADER) {
				grid.setKingdom(this);
				leaders[LeadTile.LEADER_FARMER - LeadTile.LEAD] = grid;
			}
			break;
		}

	}

	public boolean hasLeader() {
		if (hasKing() >= HAS_LEADER || hasPriest() >= HAS_LEADER
				|| hasFarmer() >= HAS_LEADER || hasTrader() >= HAS_LEADER) {
			return true;
		} else {
			return false;
		}
	}

	public boolean hasKingInKingdom() {
		if (leaders[LeadTile.LEADER_KING - LeadTile.LEAD] == null) {
			return false;
		} else {
			return true;
		}
	}

	public int hasKing() {
		if (hasKingInKingdom()) {
			return HAS_LEADER;
		} else {
			Vector<Kingdom> temp_vector = wanderKingdoms(null);
			for (int i = 0; i < temp_vector.size(); i++) {
				if (((Kingdom) temp_vector.elementAt(i)).hasKingInKingdom()) {
					return OTHER_KINGDOM;
				}
			}
			return NO_LEADER;
		}
	}

	public boolean hasPriestInKingdom() {
		if (leaders[LeadTile.LEADER_PRIEST - LeadTile.LEAD] == null) {
			return false;
		} else {
			return true;
		}
	}

	public int hasPriest() {
		if (hasPriestInKingdom()) {
			return HAS_LEADER;
		} else {
			Vector<Kingdom> temp_vector = wanderKingdoms(null);
			for (int i = 0; i < temp_vector.size(); i++) {
				if (((Kingdom) temp_vector.elementAt(i)).hasPriestInKingdom()) {
					return OTHER_KINGDOM;
				}
			}
			return NO_LEADER;
		}
	}

	public boolean hasFarmerInKingdom() {
		if (leaders[LeadTile.LEADER_FARMER - LeadTile.LEAD] == null) {
			return false;
		} else {
			return true;
		}
	}

	public int hasFarmer() {
		if (hasFarmerInKingdom()) {
			return HAS_LEADER;
		} else {
			Vector<Kingdom> temp_vector = wanderKingdoms(null);
			for (int i = 0; i < temp_vector.size(); i++) {
				if (((Kingdom) temp_vector.elementAt(i)).hasFarmerInKingdom()) {
					return OTHER_KINGDOM;
				}
			}
			return NO_LEADER;
		}
	}

	public boolean hasTraderInKingdom() {
		if (leaders[LeadTile.LEADER_TRADER - LeadTile.LEAD] == null) {
			return false;
		} else {
			return true;
		}
	}

	public int hasTrader() {
		if (hasTraderInKingdom()) {
			return HAS_LEADER;
		} else {
			Vector<Kingdom> temp_vector = wanderKingdoms(null);
			for (int i = 0; i < temp_vector.size(); i++) {
				if (((Kingdom) temp_vector.elementAt(i)).hasTraderInKingdom()) {
					return OTHER_KINGDOM;
				}
			}
			return NO_LEADER;
		}
	}

	/**
	 * return the Leader_king in the kingdom if it is not found in the current
	 * kingdom all joined kingdoms are searched for the Leader_king
	 */
	public Grid getKing() {
		int king = hasKing();
		if (king == HAS_LEADER) {
			return leaders[LeadTile.LEADER_KING - LeadTile.LEAD];
		} else if (king == OTHER_KINGDOM) {
			Vector<Kingdom> temp_vector = wanderKingdoms(null);
			for (int i = 0; i < temp_vector.size(); i++) {
				if (((Kingdom) temp_vector.elementAt(i)).hasKingInKingdom()) {
					return ((Kingdom) temp_vector.elementAt(i)).getKing();
				}
			}
		}
		return null;
	}

	/**
	 * return the Leader_priest in the kingdom if it is not found in the current
	 * kingdom all joined kingdoms are searched for the Leader_priest
	 */
	public Grid getPriest() {
		int priest = hasPriest();
		if (priest == HAS_LEADER) {
			return leaders[LeadTile.LEADER_PRIEST - LeadTile.LEAD];
		} else if (priest == OTHER_KINGDOM) {
			Vector<Kingdom> temp_vector = wanderKingdoms(null);
			for (int i = 0; i < temp_vector.size(); i++) {
				if (((Kingdom) temp_vector.elementAt(i)).hasPriestInKingdom()) {
					return ((Kingdom) temp_vector.elementAt(i)).getPriest();
				}
			}
		}
		return null;
	}

	/**
	 * return the Leader_trader in the kingdom if it is not found in the current
	 * kingdom all joined kingdoms are searched for the Leader_trader
	 */
	public Grid getTrader() {
		int trader = hasTrader();
		if (trader == HAS_LEADER) {
			return leaders[LeadTile.LEADER_TRADER - LeadTile.LEAD];
		} else if (trader == OTHER_KINGDOM) {
			Vector<Kingdom> temp_vector = wanderKingdoms(null);
			for (int i = 0; i < temp_vector.size(); i++) {
				if (((Kingdom) temp_vector.elementAt(i)).hasTraderInKingdom()) {
					return ((Kingdom) temp_vector.elementAt(i)).getTrader();
				}
			}
		}
		return null;
	}

	/**
	 * return the Leader_farmer in the kingdom if it is not found in the current
	 * kingdom all joined kingdoms are searched for the Leader_farmer
	 */
	public Grid getFarmer() {
		int farmer = hasFarmer();
		if (farmer == HAS_LEADER) {
			return leaders[LeadTile.LEADER_FARMER - LeadTile.LEAD];
		} else if (farmer == OTHER_KINGDOM) {
			Vector<Kingdom> temp_vector = wanderKingdoms(null);
			for (int i = 0; i < temp_vector.size(); i++) {
				if (((Kingdom) temp_vector.elementAt(i)).hasFarmerInKingdom()) {
					return ((Kingdom) temp_vector.elementAt(i)).getFarmer();
				}
			}
		}
		return null;
	}

	/** return the number of settlements in this kingdom */
	public int getNumberOfSettlements() {
		return settlements.size();
	}

	/**
	 * return the total number of settlements in all the joined kingdoms
	 */
	public int getTotalNumberOfSettlements() {
		int temp = 0;
		Vector<Kingdom> temp_vector = wanderKingdoms(null);
		for (int i = 0; i < temp_vector.size(); i++) {
			temp = temp
					+ ((Kingdom) temp_vector.elementAt(i))
							.getNumberOfSettlements();
		}
		return temp;
	}

	/** return the number of farms in this kingdom */
	public int getNumberOfFarmers() {
		return farmers.size();
	}

	/**
	 * return the total number of farms in all the joined kingdoms
	 */
	public int getTotalNumberOfFarmers() {
		int temp = 0;
		Vector<Kingdom> temp_vector = wanderKingdoms(null);
		for (int i = 0; i < temp_vector.size(); i++) {
			temp = temp
					+ ((Kingdom) temp_vector.elementAt(i)).getNumberOfFarmers();
		}
		return temp;
	}

	/** return the number of temples in this kingdom */
	public int getNumberOfTemples() {
		return temples.size();
	}

	/**
	 * return the total number of temples in all the joined kingdoms
	 */
	public int getTotalNumberOfTemples() {
		int temp = 0;
		Vector<Kingdom> temp_vector = wanderKingdoms(null);
		for (int i = 0; i < temp_vector.size(); i++) {
			temp = temp
					+ ((Kingdom) temp_vector.elementAt(i)).getNumberOfTemples();
		}
		return temp;
	}

	/** return the number of markets in this kingdom */
	public int getNumberOfMarkets() {
		return markets.size();
	}

	/**
	 * return the total number of markets in all the joined kingdoms
	 */
	public int getTotalNumberOfMarkets() {
		int temp = 0;
		Vector<Kingdom> temp_vector = wanderKingdoms(null);
		for (int i = 0; i < temp_vector.size(); i++) {
			temp = temp
					+ ((Kingdom) temp_vector.elementAt(i)).getNumberOfMarkets();
		}
		return temp;
	}

	/**
	 * join two vector with the result of a new vector that has only the
	 * elements of each that are different
	 */
	private Vector<Kingdom> unifyVectors(Vector<Kingdom> vector_a,
			Vector<Kingdom> vector_b) {
		for (int i = 0; i < vector_b.size(); i++) {
			if (vector_a.contains(vector_b.elementAt(i)) == false) {
				vector_a.add(vector_b.elementAt(i));
			}
		}
		return vector_a;
	}

	/**
	 * a vector with every kingdom this kingdom is joined with that is directly
	 * and indirectly
	 */
	public Vector<Kingdom> wanderKingdoms(Kingdom exclude_me) {
		Vector<Kingdom> joinedKingdoms = new Vector<Kingdom>();

		joinedKingdoms.add(this);
		for (int i = 0; i < joinedWith.size(); i++) {
			if (exclude_me != null
					&& joinedWith.elementAt(i).equals(exclude_me)) {
				// TODO Remove empty if body.
			} else {
				joinedKingdoms = unifyVectors(joinedKingdoms,
						((Kingdom) joinedWith.elementAt(i))
								.wanderKingdoms(this));
			}
		}
		return joinedKingdoms;
	}

	/** return the joined Kingdoms */
	public Vector<Kingdom> getJoinedKingdoms() {
		return joinedWith;
	}

	/**
	 * check to see if a Kingdom is already joined with this kingdom
	 */
	public boolean isJoinedWith(Kingdom k_dom) {
		Vector<Kingdom> temp_vector = wanderKingdoms(null);
		if (temp_vector.contains(k_dom)) {
			return true;
		}
		return false;
	}

	/**
	 * check to see if this kingdom is joined with a kingdom
	 */
	public boolean isJoined() {
		return (!joinedWith.isEmpty());
	}

	/**
	 * join two kingdoms together
	 */
	public boolean joinKingdoms(Kingdom k_dom) {
		Vector<Kingdom> temp_vector = wanderKingdoms(null);
		if (temp_vector.contains(k_dom)) {
			return false;
		} else {
			joinedWith.add(k_dom);
			k_dom.joinKingdoms(this);
			return true;
		}
	}

	/** returns the number associated with the kingdom */
	public int getKingdomNum() {
		return this.kingdomNum;
	}
}
