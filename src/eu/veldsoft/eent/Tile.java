/*
 * Tile.java
 *
 * Created on 24 maart 2003, 8:26
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
public class Tile {

	public static final int CAT_TILE = 11111;

	public static final int CIV_TILE = 22222;

	public static final int TEM_TILE = 33333;

	public static final int LEAD_TILE = 44444;

	private int tileType;

	/** Creates a new instance of Tile */
	public Tile(int newType) {
		tileType = newType;
	}

	/** Return the type of Civilisation Tile */
	public int getTileType() {
		return tileType;
	}
}
