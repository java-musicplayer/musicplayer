package team.javaMusicPlayer.CenterGUI;

import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MusicList extends JPanel {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private Object[][] musicData = { { "Yesterday.mp3", "Guns and Roses", "10 min", "", "" },
			{ "Night train.mp3", "Guns and Roses", "10 min", "", "" },
			{ "November rain.mp3", "Guns and Roses", "10 min", "", "" } };
	private String[] musicColumnNames = { "曲名", "歌手", "时长", "播放", "下载" };
	public MusicList() {
		// TODO Auto-generated constructor stub
		this.setPreferredSize(new Dimension(735, 325));
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layout);

		JTable musicList = new JTable(musicData, musicColumnNames);
		musicList.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane musicListScrollPane = new JScrollPane(musicList);

		this.add(musicListScrollPane);
		
	}

}
