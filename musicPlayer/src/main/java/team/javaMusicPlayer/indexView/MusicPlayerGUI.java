package team.javaMusicPlayer.indexView;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;

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

public class MusicPlayerGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MusicPlayerGUI() {
		// TODO Auto-generated constructor stub
		this.setTitle("冈昜雲音乐");
		this.setSize(1035, 685);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container thisContainer = this.getContentPane();
		thisContainer.setLayout(new BorderLayout(4, 4));
		//背景图
//		JPanel thisContainer=new JPanel() {  
//  
//            /**
//			 * 
//			 */
//			private static final long serialVersionUID = 1L;
//
//			protected void paintComponent(Graphics g) {  
//                ImageIcon icon = new ImageIcon("./../image/背景.jpg");  
//                Image img = icon.getImage();  
//                g.drawImage(img, 0, 0, 1035, 685, icon.getImageObserver());  
//            }  
//  
//        };  
//		this.setContentPane(thisContainer);
//		thisContainer.setLayout(new BorderLayout(4, 4));
		
		//West
		JPanel westJPanel = new JPanel();
		westJPanel.setPreferredSize(new Dimension(300, 625));
		BoxLayout westPanelLayout = new BoxLayout(westJPanel, BoxLayout.Y_AXIS);
		westJPanel.setLayout(westPanelLayout);
		westJPanel.setBorder(BorderFactory.createEtchedBorder());
		
		westJPanel.add(new OtherMusicLists());
		westJPanel.add(new LocalMusicLists());
		westJPanel.add(new OperationOfMusicLists());
		//Center
		JPanel centerJPanel = new JPanel();
		centerJPanel.setPreferredSize(new Dimension(735, 625));
		BoxLayout centerPanelLayout = new BoxLayout(centerJPanel, BoxLayout.Y_AXIS);
		centerJPanel.setLayout(centerPanelLayout);
		
		centerJPanel.add(new MusicListInformation());
		centerJPanel.add(new MusicList());
		
		
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
