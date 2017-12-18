package team.javaMusicPlayer.WestGUI;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import team.javaMusicPlayer.CenterGUI.MusicList;
import team.javaMusicPlayer.CenterGUI.MusicListInformation;
import team.javaMusicPlayer.model.MusicSheet;

public class OtherMusicLists extends JPanel {

	/**
	 * 
	 */
	private Object[][] shareMusicLists = {{"",""}};
	private Object[][] listContents = {{"","",""}};
	private String[] shareMusicColumnNames = { "分享者", "歌 单" };
	private String[] musicColumnNames = { "曲名", "播放", "下载" };
	private List<MusicSheet> shareMusicSheet;
	public void setShareMusicLists(Object[][] shareMusicLists) {
		this.shareMusicLists = shareMusicLists;
	}

	private static final long serialVersionUID = 1L;
	//合并数组
	private Object[][] getList(Object[][] a, Object[][] b){
		int count = 0;
		Object[][] c = new Object[a.length + b.length][2];
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
		Object[][] c = new Object[a.length + b.length][3];
		for(int i = 0; i < a.length; i++) {
			c[count][0] = a[i][0];
			c[count][1] = "播放";
			c[count][2] = "下载";
			count ++;
		}
		for(int i = 0; i < b.length; i++) {
			c[count][0] = b[i][0];
			c[count][1] = "播放";
			c[count][2] = "下载";
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
			Object[][] a1 = {{listOfOtherMusic.get(0).getCreator(), listOfOtherMusic.get(0).getName()}};
			Object[][] a2 = {{listOfOtherMusic.get(1).getCreator(), listOfOtherMusic.get(1).getName()}};
			shareMusicLists = getList(a1, a2);
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
		            String listName = shareMusicSheet.get(row).getName();
		            String listCreatorName = shareMusicSheet.get(row).getCreator();
		            String listCreateTime = shareMusicSheet.get(row).getDateCreated();
		            
		            String rount=shareMusicSheet.get(row).getPicture();
		            System.out.println(rount);
		            //jpn部分，即歌单信息部分
		            //获取绘制图片
		    		BufferedImage bfImages = null;
					try {
						bfImages = ImageIO.read(new File(rount));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		    		String imageFormat =rount.split("\\.")[rount.split("\\.").length-1];
		    		System.out.println(imageFormat);
		    		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    		try {
						ImageIO.write(bfImages, imageFormat , baos);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		    		ImageIcon images = new ImageIcon(baos.toByteArray());
		    		int height = 0, width = 200;
		    		height = width * images.getIconHeight()/ images.getIconWidth();
		    		images.setImage(images.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		 
		            jpn.getLblistName().setText(listName);
		            jpn.getLbCreatorName().setText(listCreatorName);
		            jpn.getLbCreateTime().setText(listCreateTime);
		            
		 
		            jpn.getLbMusicShow().setIcon(images);
		      
		            jpn.setTypeOfMusic(0);
		            jpn.getLbMusicShow().repaint();
		            jpn.getLbMusicShow().updateUI();
		         	       
		            
		            jpn.updateUI();
		            
		            //歌单列表部分
		            
		          //合并歌单内容
		            //获取歌单内容信息
		            Map<String, String> shareMusicListContent =  shareMusicSheet.get(row).getMusicItems();
		            ml.setShareMusicListContent(shareMusicListContent);
		            jpn.setShareMusicListContent(shareMusicListContent);
		    		int size = shareMusicListContent.size();
		    		ArrayList<String> keys = new ArrayList<String>(); 
		    		ArrayList<String> values = new ArrayList<String>(); 
		    		for (String key : shareMusicListContent.keySet()) {  
		    		  
		    		    keys.add(key); 
		    		  
		    		}  
		    		for (String value : shareMusicListContent.values()) {  
		    			  
		    			values.add(value); 
		    		  
		    		}
		    		if(size == 1) {

		    			Object[][] a1 = {{shareMusicListContent.get(keys.get(0)), "播放", "下载"}};
		    			listContents = a1;
		    			System.out.println(listContents[0][0].toString());
		    			
		    		}
		    		else if(size == 2) {

	    				Object[][] a2 ={{shareMusicListContent.get(keys.get(0)), "播放", "下载"}};
	    				Object[][] a1 = {{shareMusicListContent.get(keys.get(1)), "播放", "下载"}};
	    				System.out.println(a2[0][1].toString());
	    				System.out.println(a2[0][2].toString());
	    				listContents = getListContent(a1, a2);
		    		}
		    		else {

	    				Object[][] a2 ={{shareMusicListContent.get(keys.get(0)), "播放", "下载"}};
	    				Object[][] a1 = {{shareMusicListContent.get(keys.get(1)), "播放", "下载"}};
	    				listContents = getListContent(a1, a2);
	    				for(int i = 2; i < size; i++) {
	    					Object [][] a={{shareMusicListContent.get(keys.get(i)), "播放", "下载"}};
	    					listContents = getListContent(listContents, a);
	    				}
		    		}
		    		ml.setTypeOfMusic(0);
		    		    		
		    		ml.getDtMusicLists().setDataVector(listContents, musicColumnNames);
		    		ml.getMusicList().revalidate();
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
	}

}
