package team.javaMusicPlayer.indexView;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import team.javaMusicPlayer.ButtomGUI.OperationOfMusic;
import team.javaMusicPlayer.CenterGUI.MusicList;
import team.javaMusicPlayer.CenterGUI.MusicListInformation;
import team.javaMusicPlayer.WestGUI.LocalMusicLists;
import team.javaMusicPlayer.WestGUI.OperationOfMusicLists;
import team.javaMusicPlayer.WestGUI.OtherMusicLists;
import team.javaMusicPlayer.model.MusicSheet;
import team.javaMusicPlayer.service.MusicSheetService;
import team.javaMusicPlayer.service.SokectService;

public class MusicPlayerGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MusicPlayerGUI() {
		// TODO Auto-generated constructor stub
		MusicSheetService musicSheetService = new MusicSheetService();
		
		this.setTitle("冈昜雲音乐");
		this.setSize(1035, 685);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container thisContainer = this.getContentPane();
		thisContainer.setLayout(new BorderLayout(4, 4));
		
		//Center
		JPanel centerJPanel = new JPanel();
		centerJPanel.setPreferredSize(new Dimension(735, 625));
		BoxLayout centerPanelLayout = new BoxLayout(centerJPanel, BoxLayout.Y_AXIS);
		centerJPanel.setLayout(centerPanelLayout);
		
		MusicListInformation mi = new MusicListInformation();
		MusicList list = new MusicList();
		centerJPanel.add(mi);
		centerJPanel.add(list);
		
		//West
		JPanel westJPanel = new JPanel();
		westJPanel.setPreferredSize(new Dimension(300, 625));
		BoxLayout westPanelLayout = new BoxLayout(westJPanel, BoxLayout.Y_AXIS);
		westJPanel.setLayout(westPanelLayout);
		westJPanel.setBorder(BorderFactory.createEtchedBorder());
		//获取分享歌单
		List<MusicSheet> shareMusicLists = SokectService.queryMusicSheets();
		//获取个人歌单
		List<MusicSheet> myMusicLists = musicSheetService.getMusicSheetsByCreatorId("16020031066");
		LocalMusicLists myLists = new LocalMusicLists(mi, myMusicLists, list);
		westJPanel.add(new OtherMusicLists(mi, shareMusicLists, list));
		westJPanel.add(myLists);
		westJPanel.add(new OperationOfMusicLists());

		
		
		//Buttom
		JPanel buttomJPanel = new JPanel();
		buttomJPanel.setPreferredSize(new Dimension(1035, 60));
		buttomJPanel.setLayout(new FlowLayout(5));
		buttomJPanel.add(new OperationOfMusic());
	
		thisContainer.add("West", westJPanel);
		thisContainer.add("Center", centerJPanel);
		thisContainer.add("South", buttomJPanel);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MusicPlayerGUI().setVisible(true);
	}

}
