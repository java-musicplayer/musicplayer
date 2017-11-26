package team.javaMusicPlayer.indexView;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
		this.setVisible(true);
		Container thisContainer = this.getContentPane();
		thisContainer.setLayout(new BorderLayout(4, 4));
		//West
		JPanel westJPanel = new JPanel();
		westJPanel.setPreferredSize(new Dimension(300, 625));
		BoxLayout westPanelLayout = new BoxLayout(westJPanel, BoxLayout.Y_AXIS);
		westJPanel.setLayout(westPanelLayout);
		thisContainer.add("West", westJPanel);
		
		
		//Center
		JPanel centerJPanel = new JPanel();
		centerJPanel.setPreferredSize(new Dimension(735, 625));
		BoxLayout centerPanelLayout = new BoxLayout(centerJPanel, BoxLayout.Y_AXIS);
		centerJPanel.setLayout(centerPanelLayout);
		thisContainer.add("Center", centerJPanel);
		
		
		//Buttom
		JPanel buttomJPanel = new JPanel();
		buttomJPanel.setPreferredSize(new Dimension(1035, 60));
		buttomJPanel.setLayout(new FlowLayout(5));
		thisContainer.add("West", buttomJPanel);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MusicPlayerGUI();
	}

}
