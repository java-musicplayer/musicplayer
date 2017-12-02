package team.javaMusicPlayer.CenterGUI;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MusicList extends JPanel {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	public void setMusicData(Object[][] musicData) {
		this.musicData = musicData;
	}
	private Object[][] musicData = { { "Yesterday.mp3", "Guns and Roses", "10 min", "播放", "下载" },
			{ "Night train.mp3", "Guns and Roses", "10 min", "播放", "下载" },
			{ "November rain.mp3", "Guns and Roses", "10 min", "播放", "下载" } };
	private String[] musicColumnNames = { "曲名", "歌手", "时长", "播放", "下载" };
	private JTable musicList; 
	
	public void setMusicList(JTable musicList) {
		this.musicList = musicList;
	}

	public MusicList() {
		// TODO Auto-generated constructor stub
		this.setPreferredSize(new Dimension(735, 325));
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layout);

		DefaultTableModel dtMusicLists = new DefaultTableModel(musicData, musicColumnNames) {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		musicList = new JTable(dtMusicLists);
		musicList.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				  if (e.getClickCount() == 2)
			        {
			            Point p = e.getPoint();
			            int row = musicList.rowAtPoint(p);
			            int column = musicList.columnAtPoint(p);
			            //从而获得双击时位于的单元格
			            //dtOtherMusicLists.addRow(new Vector());
			        }
			}
		});
		musicList.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane musicListScrollPane = new JScrollPane(musicList);

		this.add(musicListScrollPane);
		
	}

}
