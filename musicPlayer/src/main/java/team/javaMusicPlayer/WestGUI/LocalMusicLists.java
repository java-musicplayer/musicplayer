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
import java.util.List;
import javax.imageio.ImageIO;
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
import team.javaMusicPlayer.model.Music;
import team.javaMusicPlayer.model.MusicSheet;
import team.javaMusicPlayer.service.MusicSheetService;

public class LocalMusicLists extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Object[][] localMusicLists = { { "" }};
	private String[] localMusicColumnNames = { "歌 单" };
	private Object[][] listContents = {{"","",""}};
	private String[] musicColumnNames = { "曲名", "播放", "下载" };
	List<MusicSheet> myList;
	DefaultTableModel dtLocalMusicLists;
	JTable localMusicListsTable;
	
	public Object[][] getListContents() {
		return listContents;
	}
	public void setListContents(Object[][] listContents) {
		this.listContents = listContents;
	}
	public String[] getMusicColumnNames() {
		return musicColumnNames;
	}
	public void setMusicColumnNames(String[] musicColumnNames) {
		this.musicColumnNames = musicColumnNames;
	}
	public void setMyList(List<MusicSheet> myList) {
		this.myList = myList;
	}
	public List<MusicSheet> getMyList() {
		return myList;
	}
	public JTable getLocalMusicListsTable() {
		return localMusicListsTable;
	}
	public void setLocalMusicListsTable(JTable localMusicListsTable) {
		this.localMusicListsTable = localMusicListsTable;
	}
	public DefaultTableModel getDtLocalMusicLists() {
		return dtLocalMusicLists;
	}
	public void setDtLocalMusicLists(DefaultTableModel dtLocalMusicLists) {
		this.dtLocalMusicLists = dtLocalMusicLists;
	}
	//合并数组
	private Object[][] getList(Object[][] a, Object[][] b){
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
	public void setLocalMusicLists(Object[][] localMusicLists) {
		this.localMusicLists = localMusicLists;
	}

	public LocalMusicLists(MusicListInformation  jpn, List<MusicSheet> listOfOtherMusic, MusicList ml, StringBuilder logBuild) {
		// TODO Auto-generated constructor stub
		myList = listOfOtherMusic;
		int size = listOfOtherMusic.size();
		if(size == 1) {
			Object[][] a1 = {{listOfOtherMusic.get(0).getName()}};
			localMusicLists = a1;
		}
		else if(size == 2) {
			Object[][] a2 = {{listOfOtherMusic.get(1).getName()}};
			Object[][] a1 = {{listOfOtherMusic.get(0).getName()}};
			localMusicLists = getList(a1, a2);
		}
		else {
			if(!listOfOtherMusic.isEmpty()) {
				Object[][] a2 = {{listOfOtherMusic.get(1).getName()}};
				Object[][] a1 = {{listOfOtherMusic.get(0).getName()}};
				localMusicLists = getList(a1, a2);
				for(int i = 2; i < size; i++) {
					Object [][] a= {{listOfOtherMusic.get(i).getName()}};
					localMusicLists = getList(localMusicLists, a);
				}
			}
			
		}
		
		this.setPreferredSize(new Dimension(250, 290));
		BoxLayout localMusicListsLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(localMusicListsLayout);
		
		JLabel title = new JLabel("我的歌单");
		dtLocalMusicLists = new DefaultTableModel(localMusicLists, localMusicColumnNames) {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		localMusicListsTable = new JTable(dtLocalMusicLists);
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
		            String listName = myList.get(row).getName();
		            String listCreatorName = myList.get(row).getCreator();
		            String listCreateTime = myList.get(row).getDateCreated();
		            System.out.println(listName);
		            String rount=myList.get(row).getPicture();
		            System.out.println(rount);
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
		      
		            MusicSheetService musicSheetService=new MusicSheetService();
		        
		            jpn.setTypeOfMusic(1);
		            jpn.setTmpm(musicSheetService.getMusicByMusicSheetId(myList.get(row).getId()));
		            jpn.setIdOfList(myList.get(row).getId());
		            jpn.getLbMusicShow().repaint();
		            jpn.getLbMusicShow().updateUI();
		            
		            jpn.updateUI();
		            

		            
		            
		          //合并歌单内容
		            //获取歌单内容信息
		            MusicSheetService mss = new MusicSheetService();
		            int index = mss.getMusicSheetsByCreatorId(logBuild.toString()).get(row).getId();
		            List<Music> tmpm=mss.getMusicByMusicSheetId(index);
		            ml.setTmpm(tmpm);
		    		int size = tmpm.size();
		    		if(size == 1) {
		    			Object[][] a1 = {{tmpm.get(0).getName(), "播放", "下载"}};
		    			listContents = a1;
		    		}
		    		else if(size == 2) {
		    			Object[][] a1 = {{tmpm.get(0).getName(), "播放", "下载"}};
		    			Object[][] a2 = {{tmpm.get(1).getName(), "播放", "下载"}};
		    			listContents = getList(a1, a2);
		    		}
		    		else {
		    			if(!tmpm.isEmpty()) {
		    				Object[][] a1 = {{tmpm.get(0).getName(), "播放", "下载"}};
		    				Object[][] a2 = {{tmpm.get(1).getName(), "播放", "下载"}};
		    				listContents = getList(a1, a2);
		    				for(int i = 2; i < size; i++) {
		    					Object [][] a= {{tmpm.get(i).getName(), "播放", "下载"}};
		    					listContents = getList(listContents, a);
		    				}
		    			}
		    	
		    		}
		    		ml.setTypeOfMusic(1);
		    		    		
		    		ml.getDtMusicLists().setDataVector(listContents, musicColumnNames);
		    		ml.getMusicList().revalidate();
		    		ml.updateUI();
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
