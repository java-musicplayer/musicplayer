package team.javaMusicPlayer.indexView;

import javax.swing.*;
import java.awt.*;
public class IndexFrame {

	JFrame indexFrame;
	JPanel mainPanel;
	JMenuBar topMenubar;
	public IndexFrame() {
		// TODO Auto-generated constructor stub
		//顶层容器参数
		indexFrame = new JFrame("冈昜雲音乐");
		indexFrame.setSize(1035, 685);
		indexFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//内容面板
		mainPanel = new JPanel();
		indexFrame.setContentPane(mainPanel);
		//菜单栏
		topMenubar = new JMenuBar();
		indexFrame.setJMenuBar(topMenubar);
		JMenu menu1 = new JMenu("歌单管理");
		JMenu menu2 = new JMenu("别人的歌单");
		JMenu menu3 = new JMenu("我的歌单");
		topMenubar.add(menu1);
		topMenubar.add(menu2);
		topMenubar.add(menu3);
		

		//左侧面板容器
		JPanel leftPanel = new JPanel();
		JLabel lab1 = new JLabel("别人的音乐");
		JLabel lab2 = new JLabel("我的音乐");
		JPanel leftHeaderPanel = new JPanel();
		JPanel leftFooterPanel = new JPanel();
		JButton btnTest = new JButton("测试");
		JButton btnEditMusic = new JButton("歌单管理");
		leftPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		leftHeaderPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		leftFooterPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		//中心面板
		JPanel centerPanel = new JPanel();
		JLabel lab3 = new JLabel("播放界面");
		JPanel centerFooterPanel = new JPanel();
		JButton btnUpMusic = new JButton("上一首");
		JButton btnDownMusic = new JButton("下一首");
		JButton btnPlayMusic = new JButton("播放");
		centerPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		//布局方式
		mainPanel.setLayout( new BorderLayout());
		leftPanel.setLayout(new BorderLayout());
		centerPanel.setLayout(new BorderLayout());
		leftHeaderPanel.setLayout(new BorderLayout());
		leftFooterPanel.setLayout(new BorderLayout());
		
		//左侧
		leftPanel.add(leftHeaderPanel, "North");
		leftPanel.add(leftFooterPanel, "Center");
		
		leftHeaderPanel.add(lab1, "North");
		leftHeaderPanel.add(btnTest, "South");
		leftFooterPanel.add(lab2, "North");
		leftFooterPanel.add(btnEditMusic, "South");
		
		//中心
		centerPanel.add(lab3, "North");
		centerPanel.add(centerFooterPanel, "South");
		centerFooterPanel.add(btnUpMusic);
		centerFooterPanel.add(btnDownMusic);
		centerFooterPanel.add(btnPlayMusic);
		mainPanel.add(centerPanel, "Center");
		mainPanel.add(leftPanel, "West");
		
		indexFrame.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IndexFrame index = new IndexFrame();
	}

}
