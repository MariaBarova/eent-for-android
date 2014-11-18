/*
 * Board.java
 *
 * Created on March 11, 2003, 2:39 PM
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

import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

public class Board extends JDesktopPane {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Remember current player
	 */
	public static Player currentPlayer;
	public static Kingdom[] kingdoms;

	private int kingdomcount = 10;

	private static Player owner;

	public static Grid around[];
	public static final int above = 0;
	public static final int under = 1;
	public static final int left = 2;
	public static final int right = 3;
	public static boolean conflict = false;
	public static String pressed = null;
	public static boolean unification = false;

	private static int monument = 4;

	public static Grid[][] grids = new Grid[11][16];
	ButtonListener al = new ButtonListener();

	public static ImageIcon[] images;

	public static final int NO_KINGDOMS = -0001;
	public static final int UNIFICATION = -1112;
	public static final int INTERNAL_CONFLICT = -2223;
	public static final int EXTERNAL_CONFLICT = -3334;
	public static final int TOO_MANY_KINGDOMS = -4445;

	public Board() {
		this.setBounds(0, 0, 650, 450);
		kingdoms = new Kingdom[kingdomcount];
		for (int i = 0; i < kingdomcount; i++) {
			kingdoms[i] = new Kingdom(i);
		}

		/* Loading the images for grid */
		images = new ImageIcon[22];
		images[0] = new ImageIcon(getClass().getResource(
				"images/Settlement.gif"));
		images[1] = new ImageIcon(getClass().getResource("images/temple.gif"));
		images[2] = new ImageIcon(getClass().getResource("images/farmer.gif"));
		images[3] = new ImageIcon(getClass().getResource("images/market.gif"));
		images[4] = new ImageIcon(getClass().getResource("images/stop.gif"));
		images[5] = new ImageIcon(getClass().getResource("images/water.gif"));
		images[6] = new ImageIcon(getClass().getResource("images/ground.gif"));
		images[7] = new ImageIcon(getClass().getResource("images/ters.gif"));
		images[8] = new ImageIcon(getClass().getResource("images/cat.gif"));
		images[9] = new ImageIcon(getClass().getResource(
				"images/playerking.gif"));
		images[10] = new ImageIcon(getClass().getResource(
				"images/playerpriest.gif"));
		images[11] = new ImageIcon(getClass().getResource(
				"images/playerfarmer.gif"));
		images[12] = new ImageIcon(getClass().getResource(
				"images/playertrader.gif"));
		images[13] = new ImageIcon(getClass().getResource(
				"images/computerking.gif"));
		images[14] = new ImageIcon(getClass().getResource(
				"images/computerpriest.gif"));
		images[15] = new ImageIcon(getClass().getResource(
				"images/computerfarmer.gif"));
		images[16] = new ImageIcon(getClass().getResource(
				"images/computertrader.gif"));
		images[17] = new ImageIcon(getClass().getResource(
				"images/monument1.gif"));
		images[18] = new ImageIcon(getClass().getResource(
				"images/monument2.gif"));
		images[19] = new ImageIcon(getClass().getResource(
				"images/monument3.gif"));
		images[20] = new ImageIcon(getClass().getResource(
				"images/monument4.gif"));
		images[21] = new ImageIcon(getClass().getResource("images/unif.gif"));

		paintGrids();
	}

	/**
	 * Create the board made out of grids
	 */
	public void paintGrids() {
		int x = 50;
		int y = 50;
		int ycount = 0;
		int i = 0;

		while (i < 11) {
			int j = 0;
			while (j < 16) {
				String s = (String.valueOf(i) + "-" + String.valueOf(j) + "B");
				grids[i][j] = new Grid(Grid.GROUND, x, y, 35, 35, s, null);
				grids[i][j].setBorder(null);
				grids[i][j].addActionListener(al);
				x = x + 35;
				ycount++;
				if (ycount == 16) {
					y = y + 35;
					x = 50;
					ycount = 0;
				}
				add(grids[i][j]);
				j++;
			}
			i++;
		}

		grids[0][4].setType(Grid.WATER);
		grids[0][5].setType(Grid.WATER);
		grids[0][6].setType(Grid.WATER);
		grids[0][7].setType(Grid.WATER);
		grids[0][8].setType(Grid.WATER);

		/* main temple */
		grids[0][10].setType(Grid.TERS);
		grids[0][10].setKingdom(kingdoms[0]);

		grids[0][12].setType(Grid.WATER);

		/* main temple */
		grids[1][1].setType(Grid.TERS);
		grids[1][1].setKingdom(kingdoms[1]);

		grids[1][4].setType(Grid.WATER);
		grids[1][12].setType(Grid.WATER);

		/* main temple */
		grids[1][15].setType(Grid.TERS);
		grids[1][15].setKingdom(kingdoms[2]);

		grids[2][3].setType(Grid.WATER);
		grids[2][4].setType(Grid.WATER);

		/* main temple */
		grids[2][5].setType(Grid.TERS);
		grids[2][5].setKingdom(kingdoms[3]);

		grids[2][12].setType(Grid.WATER);
		grids[2][13].setType(Grid.WATER);
		grids[3][0].setType(Grid.WATER);
		grids[3][1].setType(Grid.WATER);
		grids[3][2].setType(Grid.WATER);
		grids[3][3].setType(Grid.WATER);
		grids[3][13].setType(Grid.WATER);
		grids[3][14].setType(Grid.WATER);
		grids[3][15].setType(Grid.WATER);

		/* main temple */
		grids[4][13].setType(Grid.TERS);
		grids[4][13].setKingdom(kingdoms[4]);

		grids[4][14].setType(Grid.WATER);
		grids[4][15].setType(Grid.WATER);
		grids[5][14].setType(Grid.WATER);
		grids[6][0].setType(Grid.WATER);
		grids[6][1].setType(Grid.WATER);
		grids[6][2].setType(Grid.WATER);
		grids[6][3].setType(Grid.WATER);

		/* main temple */
		grids[6][8].setType(Grid.TERS);
		grids[6][8].setKingdom(kingdoms[5]);

		grids[6][12].setType(Grid.WATER);
		grids[6][13].setType(Grid.WATER);
		grids[6][14].setType(Grid.WATER);

		/* main temple */
		grids[7][1].setType(Grid.TERS);
		grids[7][1].setKingdom(kingdoms[6]);

		grids[7][3].setType(Grid.WATER);
		grids[7][4].setType(Grid.WATER);
		grids[7][5].setType(Grid.WATER);
		grids[7][6].setType(Grid.WATER);
		grids[7][12].setType(Grid.WATER);
		grids[8][6].setType(Grid.WATER);
		grids[8][7].setType(Grid.WATER);
		grids[8][8].setType(Grid.WATER);
		grids[8][9].setType(Grid.WATER);
		grids[8][10].setType(Grid.WATER);
		grids[8][11].setType(Grid.WATER);
		grids[8][12].setType(Grid.WATER);

		/* main temple */
		grids[8][14].setType(Grid.TERS);
		grids[8][14].setKingdom(kingdoms[7]);

		/* main temple */
		grids[9][5].setType(Grid.TERS);
		grids[9][5].setKingdom(kingdoms[8]);

		/* main temple */
		grids[10][10].setType(Grid.TERS);
		grids[10][10].setKingdom(kingdoms[9]);
	}

	public static boolean isNearATemple(Grid sel) {
		String cmd = sel.getActionCommand();
		int row = Integer.parseInt(cmd.substring(0, cmd.indexOf('-')));
		int col = Integer.parseInt(cmd.substring(cmd.indexOf('-') + 1,
				cmd.length() - 1));
		around = new Grid[8];
		for (int i = 0; i < 8; i++) {
			around[i] = null;
		}

		if (row - 1 >= 0) {
			around[above] = grids[row - 1][col];
		}
		if (row + 1 < 11) {
			around[under] = grids[row + 1][col];
		}
		if (col - 1 >= 0) {
			around[left] = grids[row][col - 1];
		}
		if (col + 1 < 16) {
			around[right] = grids[row][col + 1];
		}

		boolean t = false, test1 = false, test2 = false, test3 = false, test4 = false;

		if (around[above] != null) {
			if (around[above].getGridType() == Grid.TEM
					|| around[above].getGridType() == Grid.TERS) {
				test1 = true;
			}
		}
		if (around[under] != null) {
			if (around[under].getGridType() == Grid.TEM
					|| around[under].getGridType() == Grid.TERS) {
				test2 = true;
			}
		}
		if (around[left] != null) {
			if (around[left].getGridType() == Grid.TEM
					|| around[left].getGridType() == Grid.TERS) {
				test3 = true;
			}
		}
		if (around[right] != null) {
			if (around[right].getGridType() == Grid.TEM
					|| around[right].getGridType() == Grid.TERS) {
				test4 = true;
			}
		}

		if (test1 || test2 || test3 || test4) {
			t = true;
		}
		return t;
	}

	public static void Monument(Grid selc) {
		if (monument > 0) {
			String cmd = selc.getActionCommand();
			int row = Integer.parseInt(cmd.substring(0, cmd.indexOf('-')));
			int col = Integer.parseInt(cmd.substring(cmd.indexOf('-') + 1,
					cmd.length() - 1));
			boolean check1 = false, check2 = false, check3 = false, check4 = false;

			if (col + 1 < 16) {
				check1 = true;
			}
			if (col - 1 >= 0) {
				check2 = true;
			}
			if (row + 1 < 11) {
				check3 = true;
			}
			if (row - 1 >= 0) {
				check4 = true;
			}

			if (check1 && check3) {
				if (selc.getGridType() == (Board.grids[row][col + 1])
						.getGridType()
						&& selc.getGridType() == (Board.grids[row + 1][col])
								.getGridType()
						&& selc.getGridType() == (Board.grids[row + 1][col + 1])
								.getGridType()) {
					monument--;
					System.out.println("is een mon1" + " M " + monument);
					selc.setType(Grid.MON1);
					Board.grids[row][col + 1].setType(Grid.MON2);
					Board.grids[row + 1][col].setType(Grid.MON3);
					Board.grids[row + 1][col + 1].setType(Grid.MON4);
					check1 = false;
					check2 = false;
					check3 = false;
					check4 = false;
				}
			}
			if (check1 && check4) {
				if (selc.getGridType() == (Board.grids[row - 1][col])
						.getGridType()
						&& selc.getGridType() == (Board.grids[row][col + 1])
								.getGridType()
						&& selc.getGridType() == (Board.grids[row - 1][col + 1])
								.getGridType()) {
					monument--;
					System.out.println("is een mon2" + " M" + monument);
					Board.grids[row - 1][col].setType(Grid.MON1);
					Board.grids[row - 1][col + 1].setType(Grid.MON2);
					selc.setType(Grid.MON3);
					Board.grids[row][col + 1].setType(Grid.MON4);
					check1 = false;
					check2 = false;
					check3 = false;
					check4 = false;
				}
			}
			if (check2 && check3) {
				if (selc.getGridType() == (Board.grids[row][col - 1])
						.getGridType()
						&& selc.getGridType() == (Board.grids[row + 1][col - 1])
								.getGridType()
						&& selc.getGridType() == (Board.grids[row + 1][col])
								.getGridType()) {
					monument--;
					System.out.println("is een mon3" + " M" + monument);
					Board.grids[row][col - 1].setType(Grid.MON1);
					selc.setType(Grid.MON2);
					Board.grids[row + 1][col - 1].setType(Grid.MON3);
					Board.grids[row + 1][col].setType(Grid.MON4);
					check1 = false;
					check2 = false;
					check3 = false;
					check4 = false;
				}
			}

			if (check2 && check4) {
				if (selc.getGridType() == (Board.grids[row - 1][col])
						.getGridType()
						&& selc.getGridType() == (Board.grids[row - 1][col - 1])
								.getGridType()
						&& selc.getGridType() == (Board.grids[row][col - 1])
								.getGridType()) {
					monument--;
					System.out.println("is een mon4" + " M" + monument);
					Board.grids[row - 1][col - 1].setType(Grid.MON1);
					Board.grids[row - 1][col].setType(Grid.MON2);
					Board.grids[row][col - 1].setType(Grid.MON3);
					selc.setType(Grid.MON4);
					check1 = false;
					check2 = false;
					check3 = false;
					check4 = false;
				}
			}
		}
	}

	public static Grid[] checkAround(Grid g) {
		String cmd = g.getActionCommand();
		int row = Integer.parseInt(cmd.substring(0, cmd.indexOf('-')));
		int col = Integer.parseInt(cmd.substring(cmd.indexOf('-') + 1,
				cmd.length() - 1));

		around = new Grid[4];
		for (int i = 0; i < 4; i++) {
			around[i] = null;
		}

		if (row - 1 >= 0) {
			around[above] = grids[row - 1][col];
		}
		if (row + 1 < 11) {
			around[under] = grids[row + 1][col];
		}
		if (col - 1 >= 0) {
			around[left] = grids[row][col - 1];
		}
		if (col + 1 < 16) {
			around[right] = grids[row][col + 1];
		}
		return around;
	}

	public static Vector<Kingdom> checkForKingdoms(Grid grid) {
		Grid[] temp_array = checkAround(grid);
		Vector<Kingdom> temp_vector = new Vector<Kingdom>();
		for (int i = 0; i < temp_array.length; i++) {
			if (temp_array[i] == null) {
				// do nothing
			} else if (temp_array[i].inKingdom()) {
				if (temp_vector.contains(temp_array[i].getKingdom())) {
					// do nothing
				} else {
					temp_vector.add(temp_array[i].getKingdom());
				}
			}
		}
		return temp_vector;
	}

	/**
	 * get de status of a grid to see if a problem will arise meaning a conflict
	 * between player or a unification a two main temples
	 */
	public static int checkKingdom(Grid from_grid, Grid to_grid) {
		Tile from_tile = from_grid.getTile();
		Vector<Kingdom> temp_vector = checkForKingdoms(to_grid);
		switch (from_tile.getTileType()) {
		case Tile.CIV_TILE:
			if (temp_vector.size() > 2) {
				// Improve me ;-)
				return TOO_MANY_KINGDOMS;
			} else if (temp_vector.size() == 2) {
				Kingdom first_kingdom = (Kingdom) temp_vector.elementAt(0);
				Kingdom second_kingdom = (Kingdom) temp_vector.elementAt(1);
				if (first_kingdom.isJoinedWith(second_kingdom)) {
					return first_kingdom.getKingdomNum();
				} else if (first_kingdom.hasLeader()
						&& second_kingdom.hasLeader()) {
					if (first_kingdom.hasKing() >= Kingdom.HAS_LEADER
							&& second_kingdom.hasKing() >= Kingdom.HAS_LEADER
							&& first_kingdom.getKing() != second_kingdom
									.getKing()) {
						return EXTERNAL_CONFLICT;
					} else if (first_kingdom.hasPriest() >= Kingdom.HAS_LEADER
							&& second_kingdom.hasPriest() >= Kingdom.HAS_LEADER
							&& first_kingdom.getPriest() != second_kingdom
									.getPriest()) {
						return EXTERNAL_CONFLICT;
					} else if (first_kingdom.hasTrader() >= Kingdom.HAS_LEADER
							&& second_kingdom.hasTrader() >= Kingdom.HAS_LEADER
							&& first_kingdom.getTrader() != second_kingdom
									.getTrader()) {
						return EXTERNAL_CONFLICT;
					} else if (first_kingdom.hasFarmer() >= Kingdom.HAS_LEADER
							&& second_kingdom.hasFarmer() >= Kingdom.HAS_LEADER
							&& first_kingdom.getFarmer() != second_kingdom
									.getFarmer()) {
						return EXTERNAL_CONFLICT;
					} else {
						return UNIFICATION;
					}
				} else {
					return UNIFICATION;
				}
			} else if (temp_vector.size() == 1) {
				Kingdom first_kingdom = (Kingdom) temp_vector.elementAt(0);
				return first_kingdom.getKingdomNum();
			}
			return NO_KINGDOMS;
		case Tile.LEAD_TILE:
			if (temp_vector.size() > 1) {
				// Improve me ;-)
				return TOO_MANY_KINGDOMS;
			} else if (temp_vector.size() == 1) {
				Kingdom first_kingdom = (Kingdom) temp_vector.elementAt(0);
				if (first_kingdom.hasLeader()) {
					switch (((LeadTile) from_tile).getLeaderType()) {
					case LeadTile.LEADER_KING:
						if (first_kingdom.hasKing() >= Kingdom.HAS_LEADER
								&& !(((first_kingdom.getKing()).getTile())
										.equals(from_tile))) {
							return INTERNAL_CONFLICT;
						} else {
							return first_kingdom.getKingdomNum();
						}
					case LeadTile.LEADER_PRIEST:
						if (first_kingdom.hasPriest() >= Kingdom.HAS_LEADER
								&& !(((first_kingdom.getPriest()).getTile())
										.equals(from_tile))) {
							return INTERNAL_CONFLICT;
						} else {
							return first_kingdom.getKingdomNum();
						}
					case LeadTile.LEADER_TRADER:
						if (first_kingdom.hasTrader() >= Kingdom.HAS_LEADER
								&& !(((first_kingdom.getTrader()).getTile())
										.equals(from_tile))) {
							return INTERNAL_CONFLICT;
						} else {
							return first_kingdom.getKingdomNum();
						}
					case LeadTile.LEADER_FARMER:
						if (first_kingdom.hasFarmer() >= Kingdom.HAS_LEADER
								&& !(((first_kingdom.getFarmer()).getTile())
										.equals(from_tile))) {
							return INTERNAL_CONFLICT;
						} else {
							return first_kingdom.getKingdomNum();
						}
					}
				} else {
					return first_kingdom.getKingdomNum();
				}
			}
			return NO_KINGDOMS;
		case Tile.CAT_TILE:
			// fix me ;-)
			break;
		}
		// if this number is returned something is really wrong :-)
		return -999999;
	}

	/**
	 * returns the kingdom associated with the number
	 */
	public static Kingdom getKingdom(int kingdomnumber) {
		if (kingdomnumber >= 0 && kingdomnumber < kingdoms.length) {
			return kingdoms[kingdomnumber];
		} else {
			return null;
		}
	}

	/**
	 * gives points to a player based on the leader in the kingdom
	 */
	public static void checkPoints(Grid g) {
		if (g.inKingdom() == false) {
			return;
		}

		Kingdom kingdom = g.getKingdom();
		if (kingdom.hasLeader() == false) {
			return;
		}

		switch (((CivTile) g.getTile()).getCivType()) {
		case CivTile.TEMPLE:
			if (kingdom.hasPriest() >= Kingdom.HAS_LEADER) {
				owner = ((LeadTile) ((kingdom.getPriest()).getTile()))
						.getPlayer();
				owner.incr_score(Player.TEMPLE_POINT);
				if (owner.isCPU()) {
					System.out.println(owner.getTemplePoints()
							+ " computer player red score");
				} else {
					Tigris2.ScoreBoard2.setText("" + owner.getTemplePoints());
				}
			} else if (kingdom.hasKing() >= Kingdom.HAS_LEADER) {
				owner = ((LeadTile) ((kingdom.getKing()).getTile()))
						.getPlayer();
				owner.incr_score(Player.TEMPLE_POINT);
				if (owner.isCPU()) {
					System.out.println(owner.getTemplePoints()
							+ " computer player black score");
				} else {
					Tigris2.ScoreBoard2.setText("" + owner.getTemplePoints());
				}
			}
			break;
		case CivTile.FARM:
			if (kingdom.hasFarmer() >= Kingdom.HAS_LEADER) {
				owner = ((LeadTile) ((kingdom.getFarmer()).getTile()))
						.getPlayer();
				owner.incr_score(Player.FARM_POINT);
				if (owner.isCPU()) {
					System.out.println(owner.getFarmPoints()
							+ " computer player blue score");
				} else {
					Tigris2.ScoreBoard3.setText("" + owner.getFarmPoints());
				}
			} else if (kingdom.hasKing() >= Kingdom.HAS_LEADER) {
				owner = ((LeadTile) ((kingdom.getKing()).getTile()))
						.getPlayer();
				owner.incr_score(Player.FARM_POINT);
				if (owner.isCPU()) {
					System.out.println(owner.getFarmPoints()
							+ " computer player green score");
				} else {
					Tigris2.ScoreBoard3.setText("" + owner.getFarmPoints());
				}
			}
			break;
		case CivTile.SETTLEMENT:
			if (kingdom.hasKing() >= Kingdom.HAS_LEADER) {
				owner = ((LeadTile) ((kingdom.getKing()).getTile()))
						.getPlayer();
				owner.incr_score(Player.PEOPLE_POINT);
				if (owner.isCPU()) {
					System.out.println(owner.getPeoplePoints()
							+ " computer player black score");
				} else {
					Tigris2.ScoreBoard1.setText("" + owner.getPeoplePoints());
				}
			}
			break;
		case CivTile.MARKET:
			if (kingdom.hasTrader() >= Kingdom.HAS_LEADER) {
				owner = ((LeadTile) ((kingdom.getTrader()).getTile()))
						.getPlayer();
				owner.incr_score(Player.MARKET_POINT);
				if (owner.isCPU()) {
					System.out.println(owner.getMarketPoints()
							+ " computer player blue score");
				} else {
					Tigris2.ScoreBoard4.setText("" + owner.getMarketPoints());
				}
			} else if (kingdom.hasKing() >= Kingdom.HAS_LEADER) {
				owner = ((LeadTile) ((kingdom.getKing()).getTile()))
						.getPlayer();
				owner.incr_score(Player.MARKET_POINT);
				if (owner.isCPU()) {
					System.out.println(owner.getMarketPoints()
							+ " computer player blue score");
				} else {
					Tigris2.ScoreBoard4.setText("" + owner.getMarketPoints());
				}
			}
			break;
		}

	}

	public static void unification(Grid from_grid, Grid to_grid) {
		Vector<Kingdom> temp_vector = checkForKingdoms(to_grid);
		int gridNumber = Integer.parseInt((from_grid.getActionCommand())
				.substring(0, 1));
		Kingdom first_kingdom = (Kingdom) temp_vector.elementAt(0);
		Kingdom second_kingdom = (Kingdom) temp_vector.elementAt(1);
		to_grid.setTile(from_grid.getTile());
		first_kingdom.addCivTile(to_grid);
		from_grid.removePlayerTile();
		Board.currentPlayer.removeCivTile(gridNumber);
		first_kingdom.joinKingdoms(second_kingdom);
		Tigris2.checkPlayerTurn();
		Tigris2.infolabel.setText("Playing");
		Board.checkPoints(to_grid);
	}

	/* the methode conflict handels the internal conflict */

	public static void conflict(Player attack, Grid selec) {
		/*
		 * attack is cuurent playerselec is the selected grid that started the
		 * conflict
		 */
		Player attacker = null;
		Player defender = null;
		Grid defendleader = null;
		Kingdom temp_king = null;
		int att_supp = 0, def_supp = 0, attackCount = 0, defendCount = 0;

		// determine the attacker and the defender
		attacker = attack;
		if (attack.isCPU()) {
			defender = Tigris2.players[0];
		} else {
			defender = Tigris2.players[1];
		}
		att_supp = getSupport(Grid.TEM, attacker);
		def_supp = getSupport(Grid.TEM, defender);

		// find where the opponent is
		switch (selec.getGridType()) {
		case Grid.P_FARMER:
			defendleader = scan(Grid.C_FARMER);
			break;
		case Grid.P_KING:
			defendleader = scan(Grid.C_KING);
			break;
		case Grid.P_PRIEST:
			defendleader = scan(Grid.C_PRIEST);
			break;
		case Grid.P_TRADER:
			defendleader = scan(Grid.C_TRADER);
			break;
		case Grid.C_FARMER:
			defendleader = scan(Grid.P_FARMER);
			break;
		case Grid.C_KING:
			defendleader = scan(Grid.P_KING);
			break;
		case Grid.C_PRIEST:
			defendleader = scan(Grid.C_PRIEST);
			break;
		case Grid.C_TRADER:
			defendleader = scan(Grid.C_TRADER);
			break;
		}
		// determine the other kingdom
		temp_king = defendleader.getKingdom();

		// get the temples around each leader
		Grid[] temp_attack = checkAround(selec);
		for (int i = 0; i < temp_attack.length; i++) {
			if (temp_attack[i] == null) {
				continue;
			}

			if (temp_attack[i].getGridType() == Grid.TEM
					|| temp_attack[i].getGridType() == Grid.TERS) {
				attackCount++;
			}

		}
		Grid[] temp_defen = checkAround(defendleader);
		for (int i = 0; i < temp_defen.length; i++) {
			if (temp_defen[i] == null) {
				continue;
			}

			if (temp_defen[i].getGridType() == Grid.TEM
					|| temp_defen[i].getGridType() == Grid.TERS) {
				defendCount++;
			}

		}

		// add the support tile to each player
		attackCount = attackCount + att_supp;
		defendCount = defendCount + def_supp;

		// find out who the winner is

		if (attackCount > defendCount) {
			// place the leader that lost the conflict back
			defender.setLeader((LeadTile) defendleader.getTile());
			temp_king.removeGridFromKingdom(defendleader);

			// redraw the human player`s leaders
			if (!defender.isCPU()) {
				LeadTile[] templead = defender.getLeaders();
				for (int i = 0; i < 4; i++) {
					Tigris2.uic[i + 6].setTile(templead[i]);
				}
			}

			temp_king.addLeader(selec);

			// the winner gets one winning red point
			attacker.incr_score(Player.TEMPLE_POINT);

			if (attacker.isCPU()) {
				System.out.println(attacker.getTemplePoints()
						+ " computer player red score");
				JOptionPane.showMessageDialog(null,
						"Computer player wins the conflict ");
			} else {
				Tigris2.ScoreBoard2.setText("" + attacker.getTemplePoints());
				JOptionPane.showMessageDialog(null,
						"Human player wins the conflict");
			}
		}
		// do the same if the other leader wins
		else if (defendCount >= attackCount) {
			attacker.setLeader((LeadTile) selec.getTile());
			temp_king.removeGridFromKingdom(selec);
			if (!attacker.isCPU()) {
				LeadTile[] templead = attacker.getLeaders();
				for (int i = 0; i < 4; i++) {
					Tigris2.uic[i + 6].setTile(templead[i]);
				}
			}
			defender.incr_score(Player.TEMPLE_POINT);
			if (defender.isCPU()) {
				System.out.println(defender.getTemplePoints()
						+ " computer player red score");
				JOptionPane.showMessageDialog(null,
						"Computer player wins the conflict ");
			} else {
				Tigris2.ScoreBoard2.setText("" + defender.getTemplePoints());
				JOptionPane.showMessageDialog(null,
						"Human player wins the conflict ");
			}
		}
	}

	/**
	 * search voor the grid with the gridtype by the findtile int returns the
	 * grid if found else null is returned
	 */
	public static Grid scan(int findtile) {
		int i = 0;
		while (i < 11) {
			int j = 0;
			while (j < 16) {
				if (grids[i][j].hasTile()) {
					// find where the given tile type is on the board
					if (grids[i][j].getGridType() == findtile) {
						return (grids[i][j]);
					}
				}
				j++;
			}
			i++;
		}
		return null;
	}

	public static boolean externalConflict(Player attack, Grid selec) {
		// attack is the current player
		// selec is the grid that caused the conflict
		Player attacker = null;
		Player defender = null;
		Grid king1 = null, priest1 = null, farmer1 = null, trader1 = null, king2 = null, priest2 = null, farmer2 = null, trader2 = null;
		Kingdom first_kingdom = null, second_kingdom = null;

		// determine the attacker and the defender
		attacker = attack;
		if (attack.isCPU()) {
			defender = Tigris2.players[0];
		} else {
			defender = Tigris2.players[1];
		}

		// distingushe the kingdoms around the grid
		Grid[] temp = checkAround(selec);
		Vector<Kingdom> temp_vector = new Vector<Kingdom>();
		for (int i = 0; i < temp.length; i++) {
			if (temp[i] == null) {
				continue;
			}
			if (temp[i].inKingdom() == false) {
				continue;
			}
			if (temp_vector.contains(temp[i].getKingdom()) == false) {
				temp_vector.add(temp[i].getKingdom());
			}
		}
		// determine the first and the second kingdom
		first_kingdom = (Kingdom) temp_vector.elementAt(0);
		second_kingdom = (Kingdom) temp_vector.elementAt(1);
		// find out which leaders each kingdom has
		if (first_kingdom.hasKing() == Kingdom.HAS_LEADER) {
			king1 = first_kingdom.getKing();
		}
		if (first_kingdom.hasPriest() == Kingdom.HAS_LEADER) {
			priest1 = first_kingdom.getPriest();
		}
		if (first_kingdom.hasFarmer() == Kingdom.HAS_LEADER) {
			farmer1 = first_kingdom.getFarmer();
		}
		if (first_kingdom.hasTrader() == Kingdom.HAS_LEADER) {
			trader1 = first_kingdom.getTrader();
		}
		if (second_kingdom.hasKing() == Kingdom.HAS_LEADER) {
			king2 = second_kingdom.getKing();
		}
		if (second_kingdom.hasFarmer() == Kingdom.HAS_LEADER) {
			farmer2 = second_kingdom.getFarmer();
		}
		if (second_kingdom.hasTrader() == Kingdom.HAS_LEADER) {
			trader2 = second_kingdom.getTrader();
		}
		if (second_kingdom.hasPriest() == Kingdom.HAS_LEADER) {
			priest2 = second_kingdom.getPriest();
		}
		// slove the first conflict
		// -----------------------------------------------------------------------
		// checks if the kingdoms are not empty
		if (!(first_kingdom == null || second_kingdom == null)) {
			return true;
		}

		// solve the conflict if both kingdoms have kings
		if (king1 != null && king2 != null) {
			// get the settelemts that each kingdom has and check if the
			// first kingdom hase more settelemnt tiles
			if (first_kingdom.getNumberOfSettlements() > second_kingdom
					.getNumberOfSettlements()) {
				// checks if the attacker the winner is
				if (((LeadTile) king1.getTile()).getPlayer() == attacker) {
					// if the winner is the attackers he gets the points
					for (int i = 0; i <= second_kingdom
							.getNumberOfSettlements(); i++) {
						attacker.incr_score(Player.PEOPLE_POINT);
						// System.out.println("attacker wins");
					}

					// remove and return the leader that lost the conflict
					LeadTile[] temp_lead_tile = defender.getLeaders();
					for (int i = 0; i < temp_lead_tile.length; i++) {
						if (temp_lead_tile[i] == null) {
							temp_lead_tile[i] = new LeadTile(
									((LeadTile) king2.getTile())
											.getLeaderType(),
									defender);
							second_kingdom.removeGridFromKingdom(king2);
							// update the loser`s lead tiles
							defender.setLeaders(temp_lead_tile);

							// if the losser is human player repaint the
							// leaders
							if (!defender.isCPU()) {
								LeadTile[] templead = defender.getLeaders();
								for (int ii = 0; ii < 4; i++) {
									Tigris2.uic[ii + 6].setTile(templead[ii]);
								}
							}
							break;
						}
					}
				}
				// if the winner is the defender he gets the points
				else if (((LeadTile) king1.getTile()).getPlayer() == defender) {
					for (int i = 0; i <= second_kingdom
							.getNumberOfSettlements(); i++) {
						defender.incr_score(Player.PEOPLE_POINT);
						// System.out.println("defender wins");
					}

					LeadTile[] temp_lead_tile = attacker.getLeaders();
					for (int i = 0; i < temp_lead_tile.length; i++) {
						if (temp_lead_tile[i] == null) {
							temp_lead_tile[i] = new LeadTile(
									((LeadTile) king2.getTile())
											.getLeaderType(),
									attacker);
							second_kingdom.removeGridFromKingdom(king2);
							attacker.setLeaders(temp_lead_tile);

							// if the losser is human player repaint the
							// leaders
							if (!attacker.isCPU()) {
								LeadTile[] templead = attacker.getLeaders();
								for (int ii = 0; ii < 4; i++) {
									Tigris2.uic[ii + 6].setTile(templead[ii]);
								}
							}
							break;
						}
					}
				}
				second_kingdom.removeAllSettlements();
				// start again to check weather other conflicts exists
				externalConflict(attack, selec);
			}
			// get the settelemts that each kingdom has and check if the
			// second kingdom hase more settelemnt tiles
			else if (first_kingdom.getNumberOfSettlements() < second_kingdom
					.getNumberOfSettlements()) {
				// checks if the attacker is the winner
				if (((LeadTile) king2.getTile()).getPlayer() == attacker) {
					// if the winner is the attackers he gets the points
					for (int i = 0; i <= first_kingdom.getNumberOfSettlements(); i++) {
						attacker.incr_score(Player.PEOPLE_POINT);
						// System.out.println("attacker wins");
					}

					// remove and return the leader that lost the conflict
					LeadTile[] temp_lead_tile = defender.getLeaders();
					for (int i = 0; i < temp_lead_tile.length; i++) {
						if (temp_lead_tile[i] == null) {
							temp_lead_tile[i] = new LeadTile(
									((LeadTile) king1.getTile())
											.getLeaderType(),
									defender);
							first_kingdom.removeGridFromKingdom(king1);
							defender.setLeaders(temp_lead_tile);

							if (!defender.isCPU()) {
								LeadTile[] templead = defender.getLeaders();
								for (int ii = 0; ii < 4; i++) {
									Tigris2.uic[ii + 6].setTile(templead[ii]);
								}
							}
							break;
						}
					}
				}
				// if the winner is the defender he gets the points
				else if (((LeadTile) king2.getTile()).getPlayer() == defender) {
					for (int i = 0; i <= first_kingdom.getNumberOfSettlements(); i++) {
						defender.incr_score(Player.PEOPLE_POINT);
						// System.out.println("defender wins");
					}

					LeadTile[] temp_lead_tile = attacker.getLeaders();
					for (int i = 0; i < temp_lead_tile.length; i++) {
						if (temp_lead_tile[i] == null) {
							temp_lead_tile[i] = new LeadTile(
									((LeadTile) king1.getTile())
											.getLeaderType(),
									attacker);
							first_kingdom.removeGridFromKingdom(king1);
							attacker.setLeaders(temp_lead_tile);

							if (!attacker.isCPU()) {
								LeadTile[] templead = attacker.getLeaders();
								for (int ii = 0; ii < 4; i++) {
									Tigris2.uic[ii + 6].setTile(templead[ii]);
								}
							}
							break;
						}
					}
				}
				first_kingdom.removeAllSettlements();
				externalConflict(attack, selec);
			}
			// ______________________________________________________________________________________
			else if (first_kingdom.getNumberOfSettlements() == second_kingdom
					.getNumberOfSettlements()) {
				if (((LeadTile) king1.getTile()).getPlayer() == defender) {
					for (int i = 0; i <= first_kingdom.getNumberOfSettlements(); i++) {
						defender.incr_score(Player.PEOPLE_POINT);
						// System.out.println("attacker wins");
					}
					// attacker=((LeadTile)king2.getTile()).getPlayer();

					LeadTile[] temp_lead_tile = attacker.getLeaders();
					for (int i = 0; i < temp_lead_tile.length; i++) {
						if (temp_lead_tile[i] == null) {
							temp_lead_tile[i] = new LeadTile(
									((LeadTile) king2.getTile())
											.getLeaderType(),
									attacker);
							first_kingdom.removeGridFromKingdom(king2);
							defender.setLeaders(temp_lead_tile);

							if (!defender.isCPU()) {
								LeadTile[] templead = defender.getLeaders();
								for (int ii = 0; ii < 4; i++) {
									Tigris2.uic[ii + 6].setTile(templead[ii]);
								}
							}
							break;
						}
					}
					second_kingdom.removeAllSettlements();
					externalConflict(attack, selec);
				} else {
					for (int i = 0; i <= second_kingdom
							.getNumberOfSettlements(); i++) {
						defender.incr_score(Player.PEOPLE_POINT);
						// System.out.println("defender wins");
					}
					LeadTile[] temp_lead_tile = attacker.getLeaders();
					for (int i = 0; i < temp_lead_tile.length; i++) {
						if (temp_lead_tile[i] == null) {
							temp_lead_tile[i] = new LeadTile(
									((LeadTile) king1.getTile())
											.getLeaderType(),
									attacker);
							first_kingdom.removeGridFromKingdom(king1);
							attacker.setLeaders(temp_lead_tile);

							if (!attacker.isCPU()) {
								LeadTile[] templead = attacker.getLeaders();
								for (int ii = 0; ii < 4; i++) {
									Tigris2.uic[ii + 6].setTile(templead[ii]);
								}
							}
							break;
						}
					}
					first_kingdom.removeAllSettlements();
				}
				// if the winner is the attackers he gets the points
				// remove and return the leader that lost the conflict
				// if the winner is the defender he gets the points
				// ___________________________________________________________________________________
			}

		}
		// end of the first conflict
		// ----------------------------------------------------------------------------
		else if (priest1 != null && priest2 != null) {
			if (first_kingdom.getNumberOfTemples() > second_kingdom
					.getNumberOfTemples()) {
				if (((LeadTile) priest1.getTile()).getPlayer() == attacker) {
					// if the winner is the attackers he gets the points
					for (int i = 0; i <= second_kingdom.getNumberOfTemples(); i++) {
						attacker.incr_score(Player.TEMPLE_POINT);
						// System.out.println("attacker wins");
					}

					// remove and return the leader that lost the conflict
					LeadTile[] temp_lead_tile = defender.getLeaders();
					for (int i = 0; i < temp_lead_tile.length; i++) {
						if (temp_lead_tile[i] == null) {
							temp_lead_tile[i] = new LeadTile(
									((LeadTile) priest2.getTile())
											.getLeaderType(),
									defender);
							second_kingdom.removeGridFromKingdom(priest2);
							defender.setLeaders(temp_lead_tile);

							if (!defender.isCPU()) {
								LeadTile[] templead = defender.getLeaders();
								for (int ii = 0; ii < 4; i++) {
									Tigris2.uic[ii + 6].setTile(templead[ii]);
								}
							}
							break;
						}
					}
				}
				// if the winner is the defender he gets the points
				else if (((LeadTile) priest1.getTile()).getPlayer() == defender) {
					for (int i = 0; i <= second_kingdom.getNumberOfTemples(); i++) {
						defender.incr_score(Player.TEMPLE_POINT);
						// System.out.println("defender wins");
					}

					LeadTile[] temp_lead_tile = attacker.getLeaders();
					for (int i = 0; i < temp_lead_tile.length; i++) {
						if (temp_lead_tile[i] == null) {
							temp_lead_tile[i] = new LeadTile(
									((LeadTile) priest2.getTile())
											.getLeaderType(),
									attacker);
							second_kingdom.removeGridFromKingdom(priest2);
							attacker.setLeaders(temp_lead_tile);

							if (!attacker.isCPU()) {
								LeadTile[] templead = attacker.getLeaders();
								for (int ii = 0; ii < 4; i++) {
									Tigris2.uic[ii + 6].setTile(templead[ii]);
								}
							}
							break;
						}
					}
				}
				second_kingdom.removeAllTemples();
				// call the methode again to resolve the other conflicts
				externalConflict(attack, selec);
			} else if (first_kingdom.getNumberOfTemples() < second_kingdom
					.getNumberOfTemples()) {
				if (((LeadTile) priest2.getTile()).getPlayer() == attacker) {
					// if the winner is the attackers he gets the points
					for (int i = 0; i <= first_kingdom.getNumberOfTemples(); i++) {
						attacker.incr_score(Player.TEMPLE_POINT);
						// System.out.println("attacker wins");
					}

					// remove and return the leader that lost the conflict
					LeadTile[] temp_lead_tile = defender.getLeaders();
					for (int i = 0; i < temp_lead_tile.length; i++) {
						if (temp_lead_tile[i] == null) {
							temp_lead_tile[i] = new LeadTile(
									((LeadTile) priest1.getTile())
											.getLeaderType(),
									defender);
							first_kingdom.removeGridFromKingdom(priest1);
							defender.setLeaders(temp_lead_tile);

							if (!defender.isCPU()) {
								LeadTile[] templead = defender.getLeaders();
								for (int ii = 0; ii < 4; i++) {
									Tigris2.uic[ii + 6].setTile(templead[ii]);
								}
							}
							break;
						}
					}
				}
				// if the winner is the defender he gets the points
				else if (((LeadTile) priest2.getTile()).getPlayer() == defender) {
					for (int i = 0; i <= first_kingdom.getNumberOfTemples(); i++) {
						defender.incr_score(Player.TEMPLE_POINT);
						// System.out.println("defender wins");
					}

					LeadTile[] temp_lead_tile = attacker.getLeaders();
					for (int i = 0; i < temp_lead_tile.length; i++) {
						if (temp_lead_tile[i] == null) {
							temp_lead_tile[i] = new LeadTile(
									((LeadTile) priest1.getTile())
											.getLeaderType(),
									attacker);
							first_kingdom.removeGridFromKingdom(priest1);
							attacker.setLeaders(temp_lead_tile);

							if (!attacker.isCPU()) {
								LeadTile[] templead = attacker.getLeaders();
								for (int ii = 0; ii < 4; i++) {
									Tigris2.uic[ii + 6].setTile(templead[ii]);
								}
							}
							break;
						}
					}
				}
				first_kingdom.removeAllTemples();
				// call the methode again to resolve the other conflicts
				externalConflict(attack, selec);
			}
			// ______________________________________________________________________________________
			else if (first_kingdom.getNumberOfTemples() == second_kingdom
					.getNumberOfTemples()) {
				if (((LeadTile) priest1.getTile()).getPlayer() == defender) {
					for (int i = 0; i <= first_kingdom.getNumberOfTemples(); i++) {
						defender.incr_score(Player.TEMPLE_POINT);
						// System.out.println("attacker wins");
					}
					// attacker=((LeadTile)king2.getTile()).getPlayer();

					LeadTile[] temp_lead_tile = attacker.getLeaders();
					for (int i = 0; i < temp_lead_tile.length; i++) {

						if (temp_lead_tile[i] == null) {
							temp_lead_tile[i] = new LeadTile(
									((LeadTile) priest2.getTile())
											.getLeaderType(),
									attacker);
							first_kingdom.removeGridFromKingdom(priest2);
							defender.setLeaders(temp_lead_tile);

							if (!defender.isCPU()) {
								LeadTile[] templead = defender.getLeaders();
								for (int ii = 0; ii < 4; i++) {
									Tigris2.uic[ii + 6].setTile(templead[ii]);
								}
							}
							break;
						}
					}
					second_kingdom.removeAllTemples();
					// call the methode again to resolve the other conflicts
					externalConflict(attack, selec);
				} else {
					for (int i = 0; i <= second_kingdom.getNumberOfTemples(); i++) {
						defender.incr_score(Player.TEMPLE_POINT);
						// System.out.println("defender wins");
					}
					LeadTile[] temp_lead_tile = attacker.getLeaders();
					for (int i = 0; i < temp_lead_tile.length; i++) {
						if (temp_lead_tile[i] == null) {
							temp_lead_tile[i] = new LeadTile(
									((LeadTile) priest1.getTile())
											.getLeaderType(),
									attacker);
							first_kingdom.removeGridFromKingdom(priest1);
							attacker.setLeaders(temp_lead_tile);

							if (!attacker.isCPU()) {
								LeadTile[] templead = attacker.getLeaders();
								for (int ii = 0; ii < 4; i++) {
									Tigris2.uic[ii + 6].setTile(templead[ii]);
								}
							}
							break;
						}
					}
					first_kingdom.removeAllTemples();
					// call the methode again to resolve the other conflicts
					externalConflict(attack, selec);
				}
				// if the winner is the attackers he gets the points
				// remove and return the leader that lost the conflict
				// if the winner is the defender he gets the points
				// ___________________________________________________________________________________
			}
		}
		// solve second conflict
		else if (farmer1 != null && farmer2 != null) {
			if (first_kingdom.getNumberOfFarmers() > second_kingdom
					.getNumberOfFarmers()) {
				if (((LeadTile) farmer1.getTile()).getPlayer() == attacker) {
					// if the winner is the attackers he gets the points
					for (int i = 0; i <= second_kingdom.getNumberOfFarmers(); i++) {
						attacker.incr_score(Player.FARM_POINT);
						// System.out.println("attacker wins");
					}

					// remove and return the leader that lost the conflict
					LeadTile[] temp_lead_tile = defender.getLeaders();
					for (int i = 0; i < temp_lead_tile.length; i++) {
						if (temp_lead_tile[i] == null) {
							temp_lead_tile[i] = new LeadTile(
									((LeadTile) farmer2.getTile())
											.getLeaderType(),
									defender);
							second_kingdom.removeGridFromKingdom(farmer2);
							defender.setLeaders(temp_lead_tile);

							if (!defender.isCPU()) {
								LeadTile[] templead = defender.getLeaders();
								for (int ii = 0; ii < 4; i++) {
									Tigris2.uic[ii + 6].setTile(templead[ii]);
								}
							}
							break;
						}
					}
				}
				// if the winner is the defender he gets the points
				else if (((LeadTile) farmer1.getTile()).getPlayer() == defender) {
					for (int i = 0; i <= second_kingdom.getNumberOfFarmers(); i++) {
						defender.incr_score(Player.FARM_POINT);
						// System.out.println("defender wins");
					}

					LeadTile[] temp_lead_tile = attacker.getLeaders();
					for (int i = 0; i < temp_lead_tile.length; i++) {
						if (temp_lead_tile[i] == null) {
							temp_lead_tile[i] = new LeadTile(
									((LeadTile) farmer2.getTile())
											.getLeaderType(),
									attacker);
							second_kingdom.removeGridFromKingdom(farmer2);
							attacker.setLeaders(temp_lead_tile);

							if (!attacker.isCPU()) {
								LeadTile[] templead = attacker.getLeaders();
								for (int ii = 0; ii < 4; i++) {
									Tigris2.uic[ii + 6].setTile(templead[ii]);
								}
							}
							break;
						}
					}
				}
				second_kingdom.removeAllFarmers();
				// call the methode again to resolve the other conflicts
				externalConflict(attack, selec);
			} else if (first_kingdom.getNumberOfFarmers() < second_kingdom
					.getNumberOfFarmers()) {
				if (((LeadTile) farmer2.getTile()).getPlayer() == attacker) {
					// if the winner is the attackers he gets the points
					for (int i = 0; i <= first_kingdom.getNumberOfFarmers(); i++) {
						attacker.incr_score(Player.FARM_POINT);
						// System.out.println("attacker wins");
					}

					// remove and return the leader that lost the conflict
					LeadTile[] temp_lead_tile = defender.getLeaders();
					for (int i = 0; i < temp_lead_tile.length; i++) {
						if (temp_lead_tile[i] == null) {
							temp_lead_tile[i] = new LeadTile(
									((LeadTile) farmer1.getTile())
											.getLeaderType(),
									defender);
							first_kingdom.removeGridFromKingdom(farmer1);
							defender.setLeaders(temp_lead_tile);

							if (!defender.isCPU()) {
								LeadTile[] templead = defender.getLeaders();
								for (int ii = 0; ii < 4; i++) {
									Tigris2.uic[ii + 6].setTile(templead[ii]);
								}
							}
							break;
						}
					}
				}
				// if the winner is the defender he gets the points
				else if (((LeadTile) farmer2.getTile()).getPlayer() == defender) {
					for (int i = 0; i <= first_kingdom.getNumberOfFarmers(); i++) {
						defender.incr_score(Player.FARM_POINT);
						// System.out.println("defender wins");
					}

					LeadTile[] temp_lead_tile = attacker.getLeaders();
					for (int i = 0; i < temp_lead_tile.length; i++) {
						if (temp_lead_tile[i] == null) {
							temp_lead_tile[i] = new LeadTile(
									((LeadTile) farmer1.getTile())
											.getLeaderType(),
									attacker);
							first_kingdom.removeGridFromKingdom(farmer1);
							attacker.setLeaders(temp_lead_tile);

							if (!attacker.isCPU()) {
								LeadTile[] templead = attacker.getLeaders();
								for (int ii = 0; ii < 4; i++) {
									Tigris2.uic[ii + 6].setTile(templead[ii]);
								}
							}
							break;
						}
					}
				}
				first_kingdom.removeAllFarmers();
				// call the methode again to resolve the other conflicts
				externalConflict(attack, selec);
			}
			// ______________________________________________________________________________________
			else if (first_kingdom.getNumberOfFarmers() == second_kingdom
					.getNumberOfFarmers()) {
				if (((LeadTile) farmer1.getTile()).getPlayer() == defender) {
					for (int i = 0; i <= first_kingdom.getNumberOfFarmers(); i++) {
						defender.incr_score(Player.FARM_POINT);
						// System.out.println("attacker wins");
					}
					// attacker=((LeadTile)king2.getTile()).getPlayer();

					LeadTile[] temp_lead_tile = attacker.getLeaders();
					for (int i = 0; i < temp_lead_tile.length; i++) {
						if (temp_lead_tile[i] == null) {
							temp_lead_tile[i] = new LeadTile(
									((LeadTile) farmer2.getTile())
											.getLeaderType(),
									attacker);
							first_kingdom.removeGridFromKingdom(farmer2);
							defender.setLeaders(temp_lead_tile);

							if (!defender.isCPU()) {
								LeadTile[] templead = defender.getLeaders();
								for (int ii = 0; ii < 4; i++) {
									Tigris2.uic[ii + 6].setTile(templead[ii]);
								}
							}
							break;
						}
					}
					second_kingdom.removeAllFarmers();
					// call the methode again to resolve the other conflicts
					externalConflict(attack, selec);
				} else {
					for (int i = 0; i <= second_kingdom.getNumberOfFarmers(); i++) {
						defender.incr_score(Player.FARM_POINT);
						// System.out.println("defender wins");
					}
					LeadTile[] temp_lead_tile = attacker.getLeaders();
					for (int i = 0; i < temp_lead_tile.length; i++) {
						if (temp_lead_tile[i] == null) {
							temp_lead_tile[i] = new LeadTile(
									((LeadTile) farmer1.getTile())
											.getLeaderType(),
									attacker);
							first_kingdom.removeGridFromKingdom(farmer1);
							attacker.setLeaders(temp_lead_tile);

							if (!attacker.isCPU()) {
								LeadTile[] templead = attacker.getLeaders();
								for (int ii = 0; ii < 4; i++) {
									Tigris2.uic[ii + 6].setTile(templead[ii]);
								}
							}
							break;
						}
					}
					first_kingdom.removeAllFarmers();
					// call the methode again to resolve the other conflicts
					externalConflict(attack, selec);
				}
				// if the winner is the attackers he gets the points
				// remove and return the leader that lost the conflict
				// if the winner is the defender he gets the points
				// ___________________________________________________________________________________
			}
			// solve third
		} else if (trader1 != null && trader2 != null) {
			if (first_kingdom.getNumberOfMarkets() > second_kingdom
					.getNumberOfMarkets()) {
				if (((LeadTile) trader1.getTile()).getPlayer() == attacker) {
					// if the winner is the attackers he gets the points
					for (int i = 0; i <= second_kingdom.getNumberOfMarkets(); i++) {
						attacker.incr_score(Player.MARKET_POINT);
						// System.out.println("attacker wins");
					}

					// remove and return the leader that lost the conflict
					LeadTile[] temp_lead_tile = defender.getLeaders();
					for (int i = 0; i < temp_lead_tile.length; i++) {
						if (temp_lead_tile[i] == null) {
							temp_lead_tile[i] = new LeadTile(
									((LeadTile) trader2.getTile())
											.getLeaderType(),
									defender);
							second_kingdom.removeGridFromKingdom(trader2);
							defender.setLeaders(temp_lead_tile);
							// repaint the leaders of the human player
							if (!defender.isCPU()) {
								LeadTile[] templead = defender.getLeaders();
								for (int ii = 0; ii < 4; i++) {
									Tigris2.uic[ii + 6].setTile(templead[ii]);
								}
							}
							break;
						}
					}
				}
				// if the winner is the defender he gets the points
				else if (((LeadTile) trader1.getTile()).getPlayer() == defender) {
					for (int i = 0; i <= second_kingdom.getNumberOfMarkets(); i++) {
						defender.incr_score(Player.MARKET_POINT);
						// System.out.println("defender wins");
					}

					LeadTile[] temp_lead_tile = attacker.getLeaders();
					for (int i = 0; i < temp_lead_tile.length; i++) {
						if (temp_lead_tile[i] == null) {
							temp_lead_tile[i] = new LeadTile(
									((LeadTile) trader2.getTile())
											.getLeaderType(),
									attacker);
							second_kingdom.removeGridFromKingdom(trader2);
							attacker.setLeaders(temp_lead_tile);

							if (!attacker.isCPU()) {
								LeadTile[] templead = attacker.getLeaders();
								for (int ii = 0; ii < 4; i++) {
									Tigris2.uic[ii + 6].setTile(templead[ii]);
								}
							}
							break;
						}
					}
				}
				second_kingdom.removeAllMarkets();
				// call the methode again to resolve the other conflicts
				externalConflict(attack, selec);
			} else if (first_kingdom.getNumberOfMarkets() < second_kingdom
					.getNumberOfMarkets()) {
				if (((LeadTile) trader2.getTile()).getPlayer() == attacker) {
					// if the winner is the attackers he gets the points
					for (int i = 0; i <= first_kingdom.getNumberOfMarkets(); i++) {
						attacker.incr_score(Player.MARKET_POINT);
						// System.out.println("attacker wins");
					}

					// remove and return the leader that lost the conflict
					LeadTile[] temp_lead_tile = defender.getLeaders();
					for (int i = 0; i < temp_lead_tile.length; i++) {
						if (temp_lead_tile[i] == null) {
							temp_lead_tile[i] = new LeadTile(
									((LeadTile) trader1.getTile())
											.getLeaderType(),
									defender);
							first_kingdom.removeGridFromKingdom(trader1);
							defender.setLeaders(temp_lead_tile);

							if (!defender.isCPU()) {
								LeadTile[] templead = defender.getLeaders();
								for (int ii = 0; ii < 4; i++) {
									Tigris2.uic[ii + 6].setTile(templead[ii]);
								}
							}
							break;
						}
					}
				}
				// if the winner is the defender he gets the points
				else if (((LeadTile) trader2.getTile()).getPlayer() == defender) {
					for (int i = 0; i <= first_kingdom.getNumberOfMarkets(); i++) {
						defender.incr_score(Player.MARKET_POINT);
						// System.out.println("defender wins");
					}
					LeadTile[] temp_lead_tile = attacker.getLeaders();
					for (int i = 0; i < temp_lead_tile.length; i++) {
						if (temp_lead_tile[i] == null) {
							temp_lead_tile[i] = new LeadTile(
									((LeadTile) trader1.getTile())
											.getLeaderType(),
									attacker);
							first_kingdom.removeGridFromKingdom(trader1);
							attacker.setLeaders(temp_lead_tile);

							if (!attacker.isCPU()) {
								LeadTile[] templead = attacker.getLeaders();
								for (int ii = 0; ii < 4; i++) {
									Tigris2.uic[ii + 6].setTile(templead[ii]);
								}
							}
							break;
						}
					}
				}
				first_kingdom.removeAllMarkets();
				// call the methode again to resolve the other conflicts
				externalConflict(attack, selec);
			}
			// ______________________________________________________________________________________
			else if (first_kingdom.getNumberOfMarkets() == second_kingdom
					.getNumberOfMarkets()) {
				if (((LeadTile) priest1.getTile()).getPlayer() == defender) {
					for (int i = 0; i <= first_kingdom.getNumberOfMarkets(); i++) {
						defender.incr_score(Player.MARKET_POINT);
						// System.out.println("attacker wins");
					}
					// attacker=((LeadTile)king2.getTile()).getPlayer();

					LeadTile[] temp_lead_tile = attacker.getLeaders();
					for (int i = 0; i < temp_lead_tile.length; i++) {
						if (temp_lead_tile[i] == null) {
							temp_lead_tile[i] = new LeadTile(
									((LeadTile) priest2.getTile())
											.getLeaderType(),
									attacker);
							first_kingdom.removeGridFromKingdom(trader2);
							defender.setLeaders(temp_lead_tile);

							if (!defender.isCPU()) {
								LeadTile[] templead = defender.getLeaders();
								for (int ii = 0; ii < 4; i++) {
									Tigris2.uic[ii + 6].setTile(templead[ii]);
								}
							}
							break;
						}
					}
					second_kingdom.removeAllMarkets();
					// call the methode again to resolve the other conflicts
					externalConflict(attack, selec);
				} else {
					for (int i = 0; i <= second_kingdom.getNumberOfMarkets(); i++) {
						defender.incr_score(Player.MARKET_POINT);
						// System.out.println("defender wins");
					}
					LeadTile[] temp_lead_tile = attacker.getLeaders();
					for (int i = 0; i < temp_lead_tile.length; i++) {
						if (temp_lead_tile[i] == null) {
							temp_lead_tile[i] = new LeadTile(
									((LeadTile) trader1.getTile())
											.getLeaderType(),
									attacker);
							first_kingdom.removeGridFromKingdom(trader1);
							attacker.setLeaders(temp_lead_tile);

							if (!attacker.isCPU()) {
								LeadTile[] templead = attacker.getLeaders();
								for (int ii = 0; ii < 4; i++) {
									Tigris2.uic[ii + 6].setTile(templead[ii]);
								}
							}
							break;
						}
					}
					first_kingdom.removeAllMarkets();
					// call the methode again to resolve the other conflicts
					externalConflict(attack, selec);
				}
				// if the winner is the attackers he gets the points
				// remove and return the leader that lost the conflict
				// if the winner is the defender he gets the points
				// ___________________________________________________________________________________
			}
			// solve fourth
		}

		return true;
	}

	/** Return the selected Tiles used in add support */
	public static Grid[] getSelected() {
		Grid Checkbox[] = new Grid[6];

		if (Tigris2.jCheckBox1.isSelected()) {
			Checkbox[0] = (Tigris2.uic[0]);
			Board.currentPlayer.removeCivTile(0);
			Tigris2.uic[0].removePlayerTile();
		}
		if (Tigris2.jCheckBox2.isSelected()) {
			Checkbox[1] = (Tigris2.uic[1]);
			Board.currentPlayer.removeCivTile(1);
			Tigris2.uic[1].removePlayerTile();
		}
		if (Tigris2.jCheckBox3.isSelected()) {
			Checkbox[2] = (Tigris2.uic[2]);
			Board.currentPlayer.removeCivTile(2);
			Tigris2.uic[2].removePlayerTile();
		}
		if (Tigris2.jCheckBox4.isSelected()) {
			Checkbox[3] = (Tigris2.uic[3]);
			Board.currentPlayer.removeCivTile(3);
			Tigris2.uic[3].removePlayerTile();
		}
		if (Tigris2.jCheckBox5.isSelected()) {
			Checkbox[4] = (Tigris2.uic[4]);
			Board.currentPlayer.removeCivTile(4);
			Tigris2.uic[4].removePlayerTile();
		}
		if (Tigris2.jCheckBox6.isSelected()) {
			Checkbox[5] = (Tigris2.uic[5]);
			Board.currentPlayer.removeCivTile(5);
			Tigris2.uic[5].removePlayerTile();
		}
		return Checkbox;
	}

	/**
	 * returns the support tilescount of the given player
	 */
	public static int getSupport(int type, Player player) {
		int support = 0;
		if (player.isCPU()) {
			CivTile[] supp = player.getPlayerTiles();
			switch (type) {
			case Grid.TEM:
				for (int i = 0; i < supp.length; i++) {
					if (supp[i] != null) {
						if (supp[i].getCivType() == CivTile.TEMPLE) {
							support++;
							supp[i] = null;
						}
					}
				}
				break;
			case Grid.FARM:
				for (int i = 0; i < supp.length; i++) {
					if (supp[i] != null) {
						if (supp[i].getCivType() == CivTile.FARM) {
							support++;
							supp[i] = null;
						}
					}
				}
				break;
			case Grid.MARK:
				for (int i = 0; i < supp.length; i++) {

					if (supp[i] != null) {
						if (supp[i].getCivType() == CivTile.MARKET) {
							support++;
							supp[i] = null;
						}
					}
				}
				break;
			case Grid.SET:
				for (int i = 0; i < supp.length; i++) {

					if (supp[i] != null) {
						if (supp[i].getCivType() == CivTile.SETTLEMENT) {
							support++;
							supp[i] = null;
						}
					}
				}
				break;
			}

			// update cpu player tiles
			player.setCivTiles(supp);
		} else {
			Grid[] supp = getSelected();
			switch (type) {
			case Grid.TEM:
				for (int i = 0; i < supp.length; i++) {
					if (supp[i] != null) {
						if (supp[i].getGridType() == Grid.TEM) {
							support++;
						}
					}
				}
				break;
			case Grid.FARM:
				for (int i = 0; i < supp.length; i++) {
					if (supp[i] != null) {
						if (supp[i].getGridType() == Grid.FARM) {
							support++;
						}
					}
				}
				break;
			case Grid.MARK:
				for (int i = 0; i < supp.length; i++) {
					if (supp[i] != null) {
						if (supp[i].getGridType() == Grid.MARK) {
							support++;
						}
					}
				}
				break;
			case Grid.SET:
				for (int i = 0; i < supp.length; i++) {

					if (supp[i] != null) {
						if (supp[i].getGridType() == Grid.SET) {
							support++;
						}
					}
				}
				break;
			}
		}
		return support;
	}
}
