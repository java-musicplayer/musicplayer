package team.javaMusicPlayer.WestGUI;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class OtherMusicLists extends JPanel {

	/**
	 * 
	 */
	private Object[][] shareMusicLists = { { "101", "music sheet 01" }, { "102", "music sheet 02" },
			{ "103", "music sheet 03" } };
	private String[] shareMusicColumnNames = { "分享者", "歌 单" };
	
	private static final long serialVersionUID = 1L;

	public OtherMusicLists() {
		// TODO Auto-generated constructor stub
		this.setPreferredSize(new Dimension(250, 290));
		BoxLayout otherMusicListsLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(otherMusicListsLayout);
		
		JLabel title = new JLabel("共享音乐");
		JTable otherMusicLists = new JTable(shareMusicLists, shareMusicColumnNames);
		otherMusicLists.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane otherMusicScrollPane = new JScrollPane(otherMusicLists);
		this.add(Box.createVerticalStrut(5));
		this.add(title);
		this.add(Box.createVerticalStrut(5));
		this.add(otherMusicScrollPane);
	}

}
