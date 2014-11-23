/*
 * TemTile.java
 *
 * Created on 24 maart 2003, 10:33
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
class TemTile extends Tile {
	private boolean treasure;

	/** Creates a new instance of TemTile */
	public TemTile() {
		super(Tile.TEM_TILE);
		treasure = true;
	}

}
