/*
 * CivTile.java
 *
 * Created on 24 maart 2003, 10:32
 */
/*
 It is distributed under the GNU Public Licence (GPL) version 2.  See
 http://www.gnu.org/ for further details of the GPL.
 */
package eu.veldsoft.eent;

/**
 * 
 * @author Administrator
 */
class CivTile extends Tile {

	public static final int MARKET = 1111;

	public static final int FARM = 2222;

	public static final int TEMPLE = 3333;

	public static final int SETTLEMENT = 4444;

	private int civTileType;

	/** Creates a new instance of CivTile */
	public CivTile(int civType) {
		super(Tile.CIV_TILE);
		civTileType = civType;
	}

	/** return the civilisation type */
	public int getCivType() {
		return civTileType;
	}

}
