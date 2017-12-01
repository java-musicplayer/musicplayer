package team.javaMusicPlayer.WestGUI;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import team.javaMusicPlayer.CenterGUI.MusicList;
import team.javaMusicPlayer.CenterGUI.MusicListInformation;
import team.javaMusicPlayer.model.MusicSheet;

public class LocalMusicLists extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Object[][] localMusicLists = { { "" }};
	private String[] localMusicColumnNames = { "歌 单" };
	List<MusicSheet> myList;
	//合并数组
	private Object[][] getList(Object[][] a, Object[][] b){
		int count = 0;
		Object[][] c = new Object[a.length + b.length][a.length + b.length];
		for(int i = 0; i < a.length; i++) {
			c[count][0] = a[i][0];
			count ++;
		}
		for(int i = 0; i < b.length; i++) {
			c[count][0] = b[i][0];
			count ++;
		}
			
		return c;
		
	}
	public void setLocalMusicLists(Object[][] localMusicLists) {
		this.localMusicLists = localMusicLists;
	}

	public LocalMusicLists(MusicListInformation  jpn, List<MusicSheet> listOfOtherMusic, MusicList ml) {
		// TODO Auto-generated constructor stub
		myList = listOfOtherMusic;
		int size = listOfOtherMusic.size();
		if(size == 1) {
			Object[][] a1 = {{listOfOtherMusic.get(1).getCreator(), listOfOtherMusic.get(1).getName()}};
			localMusicLists = a1;
		}
		if(size == 2) {
			Object[][] a1 = {{listOfOtherMusic.get(2).getCreator(), listOfOtherMusic.get(2).getName()}};
			localMusicLists = a1;
		}
		else {
			if(!listOfOtherMusic.isEmpty()) {
				Object[][] a2 = {{listOfOtherMusic.get(2).getCreator(), listOfOtherMusic.get(2).getName()}};
				Object[][] a1 = {{listOfOtherMusic.get(1).getCreator(), listOfOtherMusic.get(1).getName()}};
				localMusicLists = getList(a1, a2);
				for(int i = 2; i < size; i++) {
					Object [][] a= {{listOfOtherMusic.get(i).getCreator(), listOfOtherMusic.get(i).getName()}};
					localMusicLists = getList(localMusicLists, a);
				}
			}
			
		}
		
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
		        	//jpn.invalidate();  
		        	
		            Point p = e.getPoint();
		            int row = localMusicListsTable.rowAtPoint(p);
		            int column = localMusicListsTable.columnAtPoint(p);
		            System.out.println(row);
		            System.out.println(column);
		            String listName = (String) localMusicLists[row][column];
		            System.out.println(listName);
		            
		            jpn.getLblistName().setText(listName);
		            
		            jpn.updateUI();
		            
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
