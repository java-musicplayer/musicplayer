package team.javaMusicPlayer.WestGUI;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import team.javaMusicPlayer.CenterGUI.MusicList;
import team.javaMusicPlayer.CenterGUI.MusicListInformation;
import team.javaMusicPlayer.model.Music;
import team.javaMusicPlayer.model.MusicSheet;
import team.javaMusicPlayer.service.MusicSheetService;
import team.javaMusicPlayer.service.SokectService;

public class OtherMusicLists extends JPanel {

	/**
	 * 
	 */
	private Object[][] shareMusicLists = {{"",""}};
	private Object[][] listContents = {{"","","","","",""}};
	private String[] shareMusicColumnNames = { "分享者", "歌 单" };
	private String[] musicColumnNames = { "曲名", "歌手", "时长", "播放", "下载" };
	private List<MusicSheet> shareMusicSheet;
	public void setShareMusicLists(Object[][] shareMusicLists) {
		this.shareMusicLists = shareMusicLists;
	}

	private static final long serialVersionUID = 1L;
	//合并数组
	private Object[][] getList(Object[][] a, Object[][] b){
		int count = 0;
		Object[][] c = new Object[a.length + b.length][a.length + b.length];
		for(int i = 0; i < a.length; i++) {
			c[count][0] = a[i][0];
			c[count][1] = a[i][1];
			count ++;
		}
		for(int i = 0; i < b.length; i++) {
			c[count][0] = b[i][0];
			c[count][1] = b[i][1];
			count ++;
		}
			
		return c;
		
	}
	//合并歌单内容
	private Object[][] getListContent(Object[][] a, Object[][] b){
		int count = 0;
		Object[][] c = new Object[a.length + b.length][a.length + b.length];
		for(int i = 0; i < a.length; i++) {
			c[count][0] = a[i][0];
			c[count][1] = a[i][1];
			c[count][2] = a[i][2];
			c[count][3] = "播放";
			c[count][4] = "下载";
			count ++;
		}
		for(int i = 0; i < b.length; i++) {
			c[count][0] = b[i][0];
			c[count][1] = b[i][1];
			c[count][2] = b[i][2];
			c[count][3] = "播放";
			c[count][4] = "下载";
			count ++;
		}
			
		return c;
		
	}
	
	public OtherMusicLists(MusicListInformation jpn, List<MusicSheet> listOfOtherMusic, MusicList ml) {
		// TODO Auto-generated constructor stub
		//获取他人歌单
		shareMusicSheet = listOfOtherMusic;
		int size = listOfOtherMusic.size();
		if(size == 1) {
			Object[][] a1 = {{listOfOtherMusic.get(0).getCreator(), listOfOtherMusic.get(0).getName()}};
			shareMusicLists = a1;
		}
		else if(size == 2) {
			Object[][] a1 = {{listOfOtherMusic.get(1).getCreator(), listOfOtherMusic.get(1).getName()}};
			shareMusicLists = a1;
		}
		else {
			if(!shareMusicSheet.isEmpty()) {
				Object[][] a2 = {{listOfOtherMusic.get(1).getCreator(), listOfOtherMusic.get(1).getName()}};
				Object[][] a1 = {{listOfOtherMusic.get(0).getCreator(), listOfOtherMusic.get(0).getName()}};
				shareMusicLists = getList(a1, a2);
				for(int i = 2; i < size; i++) {
					Object [][] a= {{listOfOtherMusic.get(i).getCreator(), listOfOtherMusic.get(i).getName()}};
					shareMusicLists = getList(shareMusicLists, a);
				}
			}
	
		}

		//页面部分
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
		            String listName = shareMusicSheet.get(row).getName();
		            String listCreatorName = shareMusicSheet.get(row).getCreator();
		            String listCreateTime = shareMusicSheet.get(row).getDateCreated();
		            String uuid = shareMusicSheet.get(row).getUuid();
//		            String rount = SokectService.downloadPicture(uuid);
		            
		            String rount=shareMusicSheet.get(row).getPicture();
		            System.out.println(shareMusicSheet.get(row).getPicture());
		            //jpn部分，即歌单信息部分
		            //获取绘制图片
		    		ImageIcon images = new ImageIcon(rount);
		    		
		    		int height = 0, width = 200;
		    		height = width * images.getIconHeight()/ images.getIconWidth();
		    		images.setImage(images.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		            JLabel lbList = new JLabel(images);
		            jpn.getLblistName().setText(listName);
		            jpn.getLbCreatorName().setText(listCreatorName);
		            jpn.getLbCreateTime().setText(listCreateTime);
		            jpn.setLbMusicShow(lbList);
		            jpn.updateUI();
		            
		            //歌单列表部分
		            
		          //合并歌单内容
		            //获取歌单内容信息
		            int musicListId = shareMusicSheet.get(row).getId();
		            List<Music> listContent = new MusicSheetService().getMusicByMusicSheetId(musicListId);
		            
		    		int size = listContent.size();
		    		if(size == 1) {
		    			Object[][] a1 = {{listContent.get(1).getName(), listContent.get(1).getSinger(),
		    				"10min", "播放", "下载"}};
		    			listContents = a1;
		    		}
		    		if(size == 2) {
		    			Object[][] a1 = {{listContent.get(2).getName(), listContent.get(2).getSinger(),
		    				"10min", "播放", "下载"}};
		    			listContents = a1;
		    		}
		    		else {
		    			if(!listContent.isEmpty()) {
		    				Object[][] a2 = {{listContent.get(2).getName(), listContent.get(2).getSinger(),
			    				"10min", "播放", "下载"}};
			    			Object[][] a1 = {{listContent.get(1).getName(), listContent.get(1).getSinger(),
			    				"10min", "播放", "下载"}};
			    			listContents = getList(a1, a2);
			    			for(int i = 2; i < size; i++) {
			    				Object [][] a= {{listContent.get(i).getName(), listContent.get(i).getSinger(),
				    				"10min", "播放", "下载"}};
			    				listContents = getList(listContents, a);
			    			}
		    			}
		    			
		    		}
		    		DefaultTableModel dtMusicLists = new DefaultTableModel(listContents, musicColumnNames) {
		    			
		    			/**
		    			 * 
		    			 */
		    			private static final long serialVersionUID = 1L;

		    			@Override
		    			public boolean isCellEditable(int row, int column) {
		    				return false;
		    			}
		    		};
		    		ml.setMusicList(new JTable(dtMusicLists));
		    		ml.updateUI();
		            
		        }
			}
		});
		otherMusicLists.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane otherMusicScrollPane = new JScrollPane(otherMusicLists);
		
		this.add(Box.createVerticalStrut(5));
		this.add(title);
		this.add(Box.createVerticalStrut(5));
		this.add(otherMusicScrollPane);
		this.add(Box.createVerticalStrut(5));
		//this.add(pnButton);
	}

}
