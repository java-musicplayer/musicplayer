package team.javaMusicPlayer.CenterGUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import team.javaMusicPlayer.model.Music;
import team.javaMusicPlayer.model.MusicPlayer;
import team.javaMusicPlayer.service.MusicSheetService;
import team.javaMusicPlayer.service.SokectService;

public class MusicListInformation extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String musicListName = "这是歌单";
	private String createTime = "2016-11-11";
	private String creator = "创建者";
	private String imageRounte = "C:\\Users\\77438\\Desktop\\musicPlayer\\resources\\images\\defaultFaceImg.jpeg";
	
	private JLabel lblistName;
	private JLabel lbCreatorName;
	private JLabel lbCreateTime;
	private JLabel lbMusicShow;
	private ImageIcon images;
	private Map<String, String> shareMusicListContent;
	private List<Music> tmpm;
	private int idOfList;
	private int typeOfMusic = 0;
	
	
	public int getIdOfList() {
		return idOfList;
	}

	public void setIdOfList(int idOfList) {
		this.idOfList = idOfList;
	}

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
	public ImageIcon getImages() {
		return images;
	}
	public void setImages(ImageIcon images) {
		this.images = images;
	}
	public void setLbMusicShow(JLabel lbMusicShow) {
		this.lbMusicShow = lbMusicShow;
	}
	public JLabel getLbCreatorName() {
		return lbCreatorName;
	}
	public JLabel getLbCreateTime() {
		return lbCreateTime;
	}
	public JLabel getLbMusicShow() {
		return lbMusicShow;
	}
	public JLabel getLblistName() {
		return lblistName;
	}
	public void setMusicListName(String musicListName) {
		this.musicListName = musicListName;
	}
	public String getMusicListName() {
		return this.musicListName;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public void setImageRounte(String imageRounte) {
		this.imageRounte = imageRounte;
	}
	public MusicListInformation(Map<Integer, Thread> lt) {
		// TODO Auto-generated constructor stub
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setPreferredSize(new Dimension(735, 300));

		images = new ImageIcon(imageRounte);
		int height = 0, width = 200;
		height = width * images.getIconHeight()/ images.getIconWidth();
		images.setImage(images.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		lbMusicShow = new JLabel(images);
		lbMusicShow.setPreferredSize(new Dimension(width, height));
		
		JPanel informationAndOperation = new JPanel();
		informationAndOperation.setLayout(new BoxLayout(informationAndOperation, BoxLayout.Y_AXIS));
		
		JPanel listName = new JPanel();
		listName.setLayout(new FlowLayout(FlowLayout.LEFT));
		lblistName = new JLabel(musicListName);
		listName.add(lblistName);
		
		JPanel creatorAndTime = new JPanel();
		creatorAndTime.setLayout(new FlowLayout(5));
		lbCreatorName = new JLabel(creator);
		lbCreateTime = new JLabel(createTime);
		creatorAndTime.add(lbCreatorName);
		creatorAndTime.add(lbCreateTime);
		
		JPanel playAndDownLoad = new JPanel();
		playAndDownLoad.setLayout(new FlowLayout(5));
		JButton btnPlay = new JButton("播放全部");
		btnPlay.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MusicSheetService musicSheetService = new MusicSheetService();
				if(typeOfMusic == 0) {
					musicSheetService.playAllSongs(shareMusicListContent);
				}
				else {
					MusicPlayer musicPlayer=MusicPlayer.getInstance();
					musicPlayer.removeAllMusics();
					Thread thread=new Thread() {
	    				public void run() {
	    					try {
	    						musicSheetService.playAllSongs(idOfList);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	    				}
	    			};
	    			if(lt.isEmpty())
	    				lt.put(-1, thread);
	    			else {
	    				for(Thread tr : lt.values()) {
	    					if(tr.isAlive())
	    						tr.stop();
	    				}
	    				lt.clear();
	    				lt.put(-1, thread);
	    			}
	    			thread.start();
	    			System.out.println("线程开启成功！");
				}
			}
		});
		JButton btnDownLoad = new JButton("下载全部");
		btnDownLoad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(typeOfMusic == 0) {
					Thread thread=new Thread() {
	    				public void run() {
	    					try {
	    						SokectService.downloadMusic(shareMusicListContent);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	    				}
	    			};
	    			thread.start();
	    			System.out.println("线程开启成功！");
				}
					
			}
		});
		playAndDownLoad.add(btnPlay);
		playAndDownLoad.add(btnDownLoad);
		
		informationAndOperation.add(listName);
		informationAndOperation.add(creatorAndTime);
		informationAndOperation.add(playAndDownLoad);
		
		this.add(lbMusicShow);
		this.add(informationAndOperation);
	}


}
