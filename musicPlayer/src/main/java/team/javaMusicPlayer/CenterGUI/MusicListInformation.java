package team.javaMusicPlayer.CenterGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MusicListInformation extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String musicListName = "这是歌单";
	private String createTime = "2016-11-11";
	private String creator = "创建者";
	private String imageRounte = "/home/sky/java-course/java-projects/exercises/musicplayer/musicPlayer/resources/images/defaultFaceImg.jpeg";
	
	JLabel lblistName;
	JLabel lbCreatorName;
	JLabel lbCreateTime;
	JLabel lbMusicShow;
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
	public MusicListInformation() {
		// TODO Auto-generated constructor stub
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setPreferredSize(new Dimension(735, 300));

		ImageIcon images = new ImageIcon(imageRounte);
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
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		JButton btnDownLoad = new JButton("下载全部");
		btnDownLoad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
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
