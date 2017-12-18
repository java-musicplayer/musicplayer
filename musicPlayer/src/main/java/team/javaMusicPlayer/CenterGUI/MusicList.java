package team.javaMusicPlayer.CenterGUI;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import team.javaMusicPlayer.model.Music;
import team.javaMusicPlayer.model.MusicPlayer;
import team.javaMusicPlayer.service.SokectService;

public class MusicList extends JPanel {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	public void setMusicData(Object[][] musicData) {
		this.musicData = musicData;
	}
	private Object[][] musicData = { { "Yesterday.mp3", "播放", "下载" } };
	private String[] musicColumnNames = { "曲名", "播放", "下载" };
	private JTable musicList; 
	private DefaultTableModel dtMusicLists;
	private JScrollPane musicListScrollPane;
	private Map<String, String> shareMusicListContent;
	
	private List<Music> tmpm;
	private int typeOfMusic = 0;
	
	public List<Music> getTmpm() {
		return tmpm;
	}

	public void setTmpm(List<Music> tmpm) {
		this.tmpm = tmpm;
	}

	public int getTypeOfMusic() {
		return typeOfMusic;
	}

	public void setTypeOfMusic(int typeOfMusic) {
		this.typeOfMusic = typeOfMusic;
	}

	public Map<String, String> getShareMusicListContent() {
		return shareMusicListContent;
	}

	public void setShareMusicListContent(Map<String, String> shareMusicListContent) {
		this.shareMusicListContent = shareMusicListContent;
	}

	public JScrollPane getMusicListScrollPane() {
		return musicListScrollPane;
	}

	public void setMusicListScrollPane(JScrollPane musicListScrollPane) {
		this.musicListScrollPane = musicListScrollPane;
	}

	public DefaultTableModel getDtMusicLists() {
		return dtMusicLists;
	}

	public void setDtMusicLists(DefaultTableModel dtMusicLists) {
		this.dtMusicLists = dtMusicLists;
	}

	public void setMusicList(JTable musicList) {
		this.musicList = musicList;
	}

	public JTable getMusicList() {
		return musicList;
	}

	public MusicList(Map<Integer, Thread> lt) {
		// TODO Auto-generated constructor stub
		this.setPreferredSize(new Dimension(735, 325));
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layout);

		dtMusicLists = new DefaultTableModel(musicData, musicColumnNames) {
			
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
			
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				  if (e.getClickCount() == 2)
			        {
					  if(typeOfMusic==0) {
						  ArrayList<String> keys = new ArrayList<String>(); 
				    		ArrayList<String> values = new ArrayList<String>(); 
				    		for (String key : shareMusicListContent.keySet()) {  
				    		  
				    		    keys.add(key); 
				    		  
				    		}  
				    		for (String value : shareMusicListContent.values()) {  
				    			  
				    			values.add(value); 
				    		  
				    		}
						  
				            Point p = e.getPoint();
				            int row = musicList.rowAtPoint(p);
				            int column = musicList.columnAtPoint(p);
				            System.out.println(row);
				            System.out.println(column);
				            //播放
				            if(column == 1 || column == 0) {
				            	MusicPlayer musicPlayer=MusicPlayer.getInstance();
				            	musicPlayer.removeAllMusics();
				            	musicPlayer.addMusic(SokectService.downloadMusic(keys.get(row), values.get(row)));
				    			//起一个线程
				    			Thread thread=new Thread() {
				    				public void run() {
				    					try {
											musicPlayer.play();
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
				    				}
				    			};
				    			if(lt.isEmpty())
				    				lt.put(row, thread);
				    			else {
				    				for(Thread tr : lt.values()) {
				    					if(tr.isAlive())
				    						tr.stop();
				    				}
				    				lt.clear();
				    				lt.put(row, thread);
				    			}
				    			thread.start();
				    			System.out.println("线程开启成功！");
				            	
				            }
				            //下载
				            if(column == 2) {
				            	Thread thread=new Thread() {
				    				public void run() {
				    					try {
				    						SokectService.downloadMusic(keys.get(row), values.get(row));
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
				    				}
				    			};
				    			thread.start();
				    			System.out.println("线程开启成功！");
				            	
				            }
				            //从而获得双击时位于的单元格
					  }
					  else {
				            Point p = e.getPoint();
				            int row = musicList.rowAtPoint(p);
				            int column = musicList.columnAtPoint(p);
				            System.out.println(row);
				            System.out.println(column);
				            //播放
				            if(column == 1 || column == 0) {
				            	MusicPlayer musicPlayer=MusicPlayer.getInstance();
				            	musicPlayer.removeAllMusics();
				            	musicPlayer.addMusic(tmpm.get(row));
				    			//起一个线程
				    			Thread thread=new Thread() {
				    				public void run() {
				    					try {
											musicPlayer.play();
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
				    				}
				    			};
				    			if(lt.isEmpty())
				    				lt.put(row, thread);
				    			else {
				    				for(Thread tr : lt.values()) {
				    					if(tr.isAlive())
				    						tr.stop();
				    				}
				    				lt.clear();
				    				lt.put(row, thread);
				    			}
				    			thread.start();
				    			System.out.println("线程开启成功！");
				            	
				            }
				            //下载
				            if(column == 2) {
				            	;
				            }
					  }
			    		
			        }
			}
		});
		musicList.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		musicListScrollPane = new JScrollPane(musicList);

		this.add(musicListScrollPane);
		
	}

}
