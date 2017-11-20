package example;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Header extends JFrame {
	private JFrame frame;// 窗体
	private JMenuBar mBar;// 菜单
	private JPanel panel;// 面板
	private JLabel label;// 标签

	// 构造函数
	public Header() {
		frame = new JFrame();
		panel = new JPanel();
		mBar = new JMenuBar();
		// 设置菜单栏
		this.setTitle("正大学院导游系统");
		this.setJMenuBar(mBar);
		this.setResizable(false);
		// 添加菜单栏
		JMenu sMenu, jMenu, gMenu, cMenu, bMenu;
		sMenu = new JMenu("系统");
		jMenu = new JMenu("简介");
		gMenu = new JMenu("工具");
		cMenu = new JMenu("查询");
		bMenu = new JMenu("帮助");
		mBar.add(sMenu);
		mBar.add(jMenu);
		mBar.add(gMenu);
		mBar.add(cMenu);
		mBar.add(bMenu);
		// 系统添加选项菜单
		JMenuItem sItem, cItem, tItem;
		sItem = new JMenuItem("开始导航");
		cItem = new JMenuItem("刷新系统");
		tItem = new JMenuItem("退出系统");
		sMenu.add(sItem);
		sMenu.addSeparator();
		sMenu.add(cItem);
		sMenu.addSeparator();
		sMenu.add(tItem);
		// 简介添加选项菜单
		JMenuItem xItem;
		xItem = new JMenuItem("学院简介");
		jMenu.add(xItem);
		// 工具添加选项菜单
		JMenuItem xyItem, ycItem;
		xyItem = new JMenuItem("显示菜单");
		ycItem = new JMenuItem("隐藏菜单");
		gMenu.add(xyItem);
		gMenu.addSeparator();
		gMenu.add(ycItem);
		// 查询添加选项菜单
		JMenuItem lItem;
		lItem = new JMenuItem("路线查询");
		cMenu.add(lItem);
		// 帮助添加选项菜单
		JMenuItem smItem, gyItem;
		smItem = new JMenuItem("说明");
		gyItem = new JMenuItem("关于");
		bMenu.add(smItem);
		bMenu.addSeparator();
		bMenu.add(gyItem);
		// 窗体设置
		this.add(panel);
		panel.setBackground(Color.green);
		this.setBounds(180, 10, 1024, 680);
		this.setVisible(true);
		mBar.add(label);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new Header();
	}
}