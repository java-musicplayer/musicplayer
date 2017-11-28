package team.javaMusicPlayer.WestGUI;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import team.javaMusicPlayer.CenterGUI.MusicListInformation;

public class LocalMusicLists extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object[][] localMusicLists = { { "music sheet 04" }, { "music sheet 05" }, { "music sheet 06" },
			{ "music sheet 04" }, { "music sheet 04" }, { "music sheet 04" }, { "music sheet 04" },
			{ "music sheet 04" }, { "music sheet 04" }, { "music sheet 04" } };
	private String[] localMusicColumnNames = { "歌 单" };
	
	public void setLocalMusicLists(Object[][] localMusicLists) {
		this.localMusicLists = localMusicLists;
	}

	public LocalMusicLists() {
		// TODO Auto-generated constructor stub
		this.setPreferredSize(new Dimension(250, 290));
		BoxLayout localMusicListsLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(localMusicListsLayout);
		
		JLabel title = new JLabel("我的歌单");
		DefaultTableModel dtLocalMusicLists = new DefaultTableModel(localMusicLists, localMusicColumnNames) {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JTable localMusicListsTable = new JTable(dtLocalMusicLists);
		localMusicListsTable.addMouseListener(new MouseListener() {
			
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
		            int row = localMusicListsTable.rowAtPoint(p);
		            int column = localMusicListsTable.columnAtPoint(p);
		            System.out.println(row);
		            System.out.println(column);
		            String listName = (String) localMusicLists[column][row];
		            System.out.println(listName);
		            MusicListInformation.setMusicListName(listName);
		            //从而获得双击时位于的单元格
		            //dtOtherMusicLists.addRow(new Vector());
		        }
			}
		});
		localMusicListsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane localMusicScrollPane = new JScrollPane(localMusicListsTable);
		this.add(Box.createVerticalStrut(5));
		this.add(title);
		this.add(Box.createVerticalStrut(5));
		this.add(localMusicScrollPane);
	}

}
