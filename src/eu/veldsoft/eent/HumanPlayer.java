/*
 * HumanPlayer.java
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
 * @author  me
 */
public class HumanPlayer extends Player {
    
    /** Creates a new instance of HumanPlayer */
    public HumanPlayer() {
	    super(Player.HUMAN);
	    LeadTile[] templead = new LeadTile[4];
	    templead[0] = new LeadTile(LeadTile.LEADER_KING  , (Player)this);
	    templead[1] = new LeadTile(LeadTile.LEADER_PRIEST, (Player)this);
	    templead[2] = new LeadTile(LeadTile.LEADER_FARMER, (Player)this);
	    templead[3] = new LeadTile(LeadTile.LEADER_TRADER, (Player)this);
	    setLeaders(templead);
    }
}
