/*
 * Tigris2.java
 *
 * Created on March 9, 2003, 12:18 PM
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
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class Tigris2 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JLabel infolabel, swap, ScoreBoard1, ScoreBoard2,
			ScoreBoard3, ScoreBoard4;
	public static final String CANCEL = "999";
	public static final String OK = "666";
	private JDesktopPane jDesktopPane1;
	private Board board = new Board();
	private ButtonListener a1 = new ButtonListener();
	private BagListener a2 = new BagListener();
	private CheckBoxListener a3 = new CheckBoxListener();
	public static Grid[] uic = new Grid[13];
	public JButton Cancel, Ok;
	public static JCheckBox jCheckBox1, jCheckBox2, jCheckBox3, jCheckBox4,
			jCheckBox5, jCheckBox6;

	public static Player[] players;
	private static int playercount;
	private static Bag currentBag;

	/**
	 * public HumanPlayer hump; public ComputerPlayer cpu;
	 */
	public static Bag tileBag;

	/**
	 * 
	 */
	public Tigris2() {
		initComponents();
		this.setBounds(0, 0, 800, 600);
		show();

	}

	/**
	 * 
	 */
	private void initComponents() {
		setTitle("Euphrates & Tigris");
		setFocusableWindowState(false);
		setFont(new Font("Arial", 0, 12));
		setLocationRelativeTo(this);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exitForm(e);
			}
		});
		jDesktopPane1 = new JDesktopPane();
		jDesktopPane1.add(board, JLayeredPane.DEFAULT_LAYER);

		tileBag = new Bag();
		currentBag = tileBag;
		players = new Player[2];
		HumanPlayer hump = new HumanPlayer();
		ComputerPlayer cpu = new ComputerPlayer();
		players[0] = (Player) hump;
		players[1] = (Player) cpu;

		playercount = 0;
		Board.currentPlayer = players[playercount];

		tileBag.setIcon(new ImageIcon(getClass().getResource(
				"/images/bag-full.gif")));
		tileBag.setBorder(new BevelBorder(BevelBorder.RAISED));
		tileBag.setActionCommand("14");
		tileBag.setBounds(650, 280, 80, 70);
		tileBag.addActionListener(a2);
		jDesktopPane1.add(tileBag, JLayeredPane.DEFAULT_LAYER);

		int x = 50;
		int i = 0;
		for (; i < 6; i++) {
			uic[i] = new Grid(-1, x, 450, 35, 35, (String.valueOf(i) + "P"),
					null);
			uic[i].addActionListener(a1);
			x = x + 40;
			jDesktopPane1.add(uic[i], JLayeredPane.DEFAULT_LAYER);

		}

		/*
		 * give the players their civTiles
		 */
		for (int p = 0; p < players.length; p++) {
			if (players[p].isCPU()) {
				currentBag.setPlayerTiles(players[p]);
			} else {
				tileBag.setImage(players[p]);
			}
		}

		x = 320;

		/*
		 * Placing the leader tiles
		 */
		LeadTile[] templead = players[0].getLeaders();
		for (; i < 10; i++) {
			uic[i] = new Grid(-1, x, 460, 35, 35, (i + "L"), null);
			uic[i].setTile(templead[i - 6]);
			uic[i].addActionListener(a1);
			jDesktopPane1.add(uic[i], JLayeredPane.DEFAULT_LAYER);
			x = x + 40;
		}

		uic[10] = new Grid(-1, 530, 450, 35, 35, "10C", null);
		uic[10].setTile(new Tile(CatTile.CAT_TILE));
		uic[10].addActionListener(a1);
		jDesktopPane1.add(uic[10], JLayeredPane.DEFAULT_LAYER);

		uic[11] = new Grid(-1, 575, 450, 35, 35, "11C", null);
		uic[11].setTile(new Tile(CatTile.CAT_TILE));
		uic[11].addActionListener(a1);
		jDesktopPane1.add(uic[11], JLayeredPane.DEFAULT_LAYER);

		Cancel = new JButton();
		Cancel.setText("Cancel");
		Cancel.setBorder(new BevelBorder(BevelBorder.RAISED));
		Cancel.setActionCommand(CANCEL);
		Cancel.setBounds(650, 405, 80, 30);
		Cancel.addActionListener(a1);
		jDesktopPane1.add(Cancel, JLayeredPane.DEFAULT_LAYER);

		Ok = new JButton();
		Ok.setText("OK");
		Ok.setBorder(new BevelBorder(BevelBorder.RAISED));
		Ok.setActionCommand(OK);
		Ok.setBounds(650, 370, 80, 30);
		Ok.addActionListener(a1);
		jDesktopPane1.add(Ok, JLayeredPane.DEFAULT_LAYER);

		infolabel = new JLabel();
		infolabel.setText("Tigris & Euphrates");
		infolabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		infolabel.setBounds(50, 510, 455, 30);
		infolabel.setBackground(Color.white);
		infolabel.setOpaque(true);
		jDesktopPane1.add(infolabel, JLayeredPane.DEFAULT_LAYER);

		ScoreBoard1 = new JLabel();
		ScoreBoard1.setHorizontalAlignment(SwingConstants.CENTER);
		ScoreBoard1.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
		ScoreBoard1.setBounds(550, 510, 30, 30);
		jDesktopPane1.add(ScoreBoard1, JLayeredPane.DEFAULT_LAYER);

		ScoreBoard2 = new JLabel();
		ScoreBoard2.setHorizontalAlignment(SwingConstants.CENTER);
		ScoreBoard2.setBorder(new LineBorder(new Color(255, 0, 0), 4, true));
		ScoreBoard2.setBounds(590, 510, 30, 30);
		jDesktopPane1.add(ScoreBoard2, JLayeredPane.DEFAULT_LAYER);

		ScoreBoard3 = new JLabel();
		ScoreBoard3.setHorizontalAlignment(SwingConstants.CENTER);
		ScoreBoard3.setBorder(new LineBorder(new Color(0, 51, 255), 4, true));
		ScoreBoard3.setBounds(630, 510, 30, 30);
		jDesktopPane1.add(ScoreBoard3, JLayeredPane.DEFAULT_LAYER);

		ScoreBoard4 = new JLabel();
		ScoreBoard4.setHorizontalAlignment(SwingConstants.CENTER);
		ScoreBoard4.setBorder(new LineBorder(new Color(0, 204, 0), 4, true));
		ScoreBoard4.setBounds(670, 510, 30, 30);
		jDesktopPane1.add(ScoreBoard4, JLayeredPane.DEFAULT_LAYER);

		/*
		 * Checkboxes te swap player tiles
		 */
		jCheckBox1 = new JCheckBox();
		jCheckBox1.setBackground((Color) UIManager.getDefaults().get(
				"Button.focus"));
		jCheckBox1.setMargin(new Insets(0, 0, 0, 0));
		jCheckBox1.setBounds(60, 490, 15, 15);
		jCheckBox1.addItemListener(a3);
		jDesktopPane1.add(jCheckBox1, JLayeredPane.DEFAULT_LAYER);

		jCheckBox2 = new JCheckBox();
		jCheckBox2.setBackground((Color) UIManager.getDefaults().get(
				"Button.focus"));
		jCheckBox2.setMargin(new Insets(0, 0, 0, 0));
		jCheckBox2.setBounds(100, 490, 15, 15);
		jCheckBox2.addItemListener(a3);
		jDesktopPane1.add(jCheckBox2, JLayeredPane.DEFAULT_LAYER);

		jCheckBox3 = new JCheckBox();
		jCheckBox3.setBackground((Color) UIManager.getDefaults().get(
				"Button.focus"));
		jCheckBox3.setMargin(new Insets(0, 0, 0, 0));
		jCheckBox3.setBounds(140, 490, 15, 15);
		jCheckBox3.addItemListener(a3);
		jDesktopPane1.add(jCheckBox3, JLayeredPane.DEFAULT_LAYER);

		jCheckBox4 = new JCheckBox();
		jCheckBox4.setBackground((Color) UIManager.getDefaults().get(
				"Button.focus"));
		jCheckBox4.setMargin(new Insets(0, 0, 0, 0));
		jCheckBox4.setBounds(180, 490, 15, 15);
		jCheckBox4.addItemListener(a3);
		jDesktopPane1.add(jCheckBox4, JLayeredPane.DEFAULT_LAYER);

		jCheckBox5 = new JCheckBox();
		jCheckBox5.setBackground((Color) UIManager.getDefaults().get(
				"Button.focus"));
		jCheckBox5.setMargin(new Insets(0, 0, 0, 0));
		jCheckBox5.setBounds(220, 490, 15, 15);
		jCheckBox5.addItemListener(a3);
		jDesktopPane1.add(jCheckBox5, JLayeredPane.DEFAULT_LAYER);

		jCheckBox6 = new JCheckBox();
		jCheckBox6.setBackground((Color) UIManager.getDefaults().get(
				"Button.focus"));
		jCheckBox6.setMargin(new Insets(0, 0, 0, 0));
		jCheckBox6.setBounds(260, 490, 15, 15);
		jCheckBox6.addItemListener(a3);
		jDesktopPane1.add(jCheckBox6, JLayeredPane.DEFAULT_LAYER);

		swap = new JLabel();
		swap.setText("Swap");
		swap.setBounds(10, 490, 41, 16);
		jDesktopPane1.add(swap, JLayeredPane.DEFAULT_LAYER);

		getContentPane().add(jDesktopPane1, BorderLayout.CENTER);
	}

	/**
	 * checks if player still has a turn and if not changes to the next player
	 * and resets the old players turn
	 */
	public static void checkPlayerTurn() {
		if (Board.currentPlayer == null) {
			return;
		}

		if (Board.currentPlayer.hasTurn()) {
			if (Board.currentPlayer.isCPU() == false) {
				Board.currentPlayer.incr_turn();
			}
		} else {
			Board.currentPlayer.reset_turn();
			if (Board.currentPlayer.isCPU()) {
				currentBag.setPlayerTiles(Board.currentPlayer);
			} else {
				currentBag.setImage(Board.currentPlayer);
			}

			if (playercount == 0) {
				Board.currentPlayer = players[++playercount];
				if (Board.currentPlayer.isCPU()) {
					((ComputerPlayer) Board.currentPlayer).ComputerPlay();
				}
			} else {
				playercount = 0;
				Board.currentPlayer = players[playercount];
			}
		}
	}

	private void exitForm(WindowEvent evt) {
		System.exit(0);
	}

	public static void main(String args[]) {
		Tigris2 tigris = new Tigris2();

	}

}
