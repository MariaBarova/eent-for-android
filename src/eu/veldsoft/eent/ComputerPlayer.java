/*
 * ComputerPlayer.java
 *
 * Created on 24 maart 2003, 21:12
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
class ComputerPlayer extends Player {

	private int civ, chooser, lead, cat, row, col;

	/** Creates a new instance of ComputerPlayer */
	public ComputerPlayer() {
		super(Player.CPU);
		LeadTile[] templead = new LeadTile[4];
		templead[0] = new LeadTile(LeadTile.LEADER_KING, (Player) this);
		templead[1] = new LeadTile(LeadTile.LEADER_PRIEST, (Player) this);
		templead[2] = new LeadTile(LeadTile.LEADER_FARMER, (Player) this);
		templead[3] = new LeadTile(LeadTile.LEADER_TRADER, (Player) this);
		setLeaders(templead);
	}

	/**
	 * computerplayer makes to moves. This is done totally at random and should
	 * be changed to be more compettative
	 */
	public void ComputerPlay() {
		CivTile[] tempciv = getPlayerTiles();
		LeadTile[] templead = getLeaders();
		CatTile[] tempcat = getCatTiles();

		while (hasTurn()) {
			chooser = Util.PRNG.nextInt(3);
			civ = Util.PRNG.nextInt(6);
			lead = Util.PRNG.nextInt(4);
			cat = Util.PRNG.nextInt(2);

			switch (chooser) {
			case 1:
				if (tempciv[civ] != null) {
					Grid g = new Grid(tempciv[civ], (civ + "P"));
					row = Util.PRNG.nextInt(11);
					col = Util.PRNG.nextInt(16);
					while (!(g.setTile(Board.grids[row][col]))) {
						row = Util.PRNG.nextInt(11);
						col = Util.PRNG.nextInt(16);
					}
					this.incr_turn();
					System.out.println(hasTurn());
					System.out.println(civ + ", " + row + "-" + col);
				}
				break;
			case 2:
				if (templead[lead] != null) {
					Grid m = new Grid(templead[lead], (lead + "L"));
					while (!(m.setTile(Board.grids[row][col]))) {
						row = Util.PRNG.nextInt(11);
						col = Util.PRNG.nextInt(16);
					}
					this.incr_turn();
					System.out.println(hasTurn());
					System.out.println(lead + ", " + row + "-" + col);

				}
				break;
			case 3:
				if (tempcat[cat] != null) {

					Grid m = new Grid(tempcat[cat], (cat + "C"));
					while (!(m.setTile(Board.grids[row][col]))) {
						row = Util.PRNG.nextInt(11);
						col = Util.PRNG.nextInt(16);
					}
					this.incr_turn();
					System.out.println(hasTurn());
					System.out.println(cat + ", " + row + "-" + col);

				}
				break;
			}
		}
		Tigris2.checkPlayerTurn();
	}
}
