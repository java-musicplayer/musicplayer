package team.javaMusicPlayer.WestGUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class OtherMusicLists extends JPanel {

	/**
	 * 
	 */
	private Object[][] shareMusicLists = { { "101", "music sheet 01" }, { "102", "music sheet 02" },
			{ "103", "music sheet 03" } };
	private String[] shareMusicColumnNames = { "分享者", "歌 单" };
	
	public void setShareMusicLists(Object[][] shareMusicLists) {
		this.shareMusicLists = shareMusicLists;
	}

	private static final long serialVersionUID = 1L;

	public OtherMusicLists() {
		// TODO Auto-generated constructor stub
		this.setPreferredSize(new Dimension(250, 290));
		BoxLayout otherMusicListsLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(otherMusicListsLayout);
		this.setBorder(BorderFactory.createEtchedBorder());
		
		JLabel title = new JLabel("共享音乐");
		DefaultTableModel dtOtherMusicLists = new DefaultTableModel(shareMusicLists, shareMusicColumnNames) {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JTable otherMusicLists = new JTable(dtOtherMusicLists);
		otherMusicLists.addMouseListener(new MouseListener() {
			
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
		            int row = otherMusicLists.rowAtPoint(p);
		            int column = otherMusicLists.columnAtPoint(p);
		            //从而获得双击时位于的单元格
		            //dtOtherMusicLists.addRow(new Vector());
		        }
			}
		});
		otherMusicLists.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane otherMusicScrollPane = new JScrollPane(otherMusicLists);
		
//		JPanel pnButton = new JPanel();
//		pnButton.setLayout(new FlowLayout(FlowLayout.LEFT));
//		JButton btnListMusics = new JButton("查看歌单");
//		btnListMusics.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				if(e.getActionCommand().equals("查看歌单")) {
//					dtOtherMusicLists.addRow(new Vector());
//				}
//			}
//		});
//		pnButton.add(btnListMusics);
//		
		this.add(Box.createVerticalStrut(5));
		this.add(title);
		this.add(Box.createVerticalStrut(5));
		this.add(otherMusicScrollPane);
		this.add(Box.createVerticalStrut(5));
		//this.add(pnButton);
	}

}
