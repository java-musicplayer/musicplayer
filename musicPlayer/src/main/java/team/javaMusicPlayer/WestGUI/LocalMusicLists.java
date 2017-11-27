package team.javaMusicPlayer.WestGUI;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class LocalMusicLists extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object[][] localMusicLists = { { "music sheet 04" }, { "music sheet 05" }, { "music sheet 06" },
			{ "music sheet 04" }, { "music sheet 04" }, { "music sheet 04" }, { "music sheet 04" },
			{ "music sheet 04" }, { "music sheet 04" }, { "music sheet 04" } };
	private String[] localMusicColumnNames = { "歌 单" };
	public LocalMusicLists() {
		// TODO Auto-generated constructor stub
		this.setPreferredSize(new Dimension(250, 290));
		BoxLayout localMusicListsLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(localMusicListsLayout);
		
		JLabel title = new JLabel("我的歌单");
		JTable localMusicListsTable = new JTable(localMusicLists, localMusicColumnNames);
		localMusicListsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane localMusicScrollPane = new JScrollPane(localMusicListsTable);
		this.add(Box.createVerticalStrut(5));
		this.add(title);
		this.add(Box.createVerticalStrut(5));
		this.add(localMusicScrollPane);
	}

}
