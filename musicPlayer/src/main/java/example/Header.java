package example;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Header extends JFrame {
	private JFrame frame;// ����
	private JMenuBar mBar;// �˵�
	private JPanel panel;// ���
	private JLabel label;// ��ǩ

	// ���캯��
	public Header() {
		frame = new JFrame();
		panel = new JPanel();
		mBar = new JMenuBar();
		// ���ò˵���
		this.setTitle("����ѧԺ����ϵͳ");
		this.setJMenuBar(mBar);
		this.setResizable(false);
		// ��Ӳ˵���
		JMenu sMenu, jMenu, gMenu, cMenu, bMenu;
		sMenu = new JMenu("ϵͳ");
		jMenu = new JMenu("���");
		gMenu = new JMenu("����");
		cMenu = new JMenu("��ѯ");
		bMenu = new JMenu("����");
		mBar.add(sMenu);
		mBar.add(jMenu);
		mBar.add(gMenu);
		mBar.add(cMenu);
		mBar.add(bMenu);
		// ϵͳ���ѡ��˵�
		JMenuItem sItem, cItem, tItem;
		sItem = new JMenuItem("��ʼ����");
		cItem = new JMenuItem("ˢ��ϵͳ");
		tItem = new JMenuItem("�˳�ϵͳ");
		sMenu.add(sItem);
		sMenu.addSeparator();
		sMenu.add(cItem);
		sMenu.addSeparator();
		sMenu.add(tItem);
		// ������ѡ��˵�
		JMenuItem xItem;
		xItem = new JMenuItem("ѧԺ���");
		jMenu.add(xItem);
		// �������ѡ��˵�
		JMenuItem xyItem, ycItem;
		xyItem = new JMenuItem("��ʾ�˵�");
		ycItem = new JMenuItem("���ز˵�");
		gMenu.add(xyItem);
		gMenu.addSeparator();
		gMenu.add(ycItem);
		// ��ѯ���ѡ��˵�
		JMenuItem lItem;
		lItem = new JMenuItem("·�߲�ѯ");
		cMenu.add(lItem);
		// �������ѡ��˵�
		JMenuItem smItem, gyItem;
		smItem = new JMenuItem("˵��");
		gyItem = new JMenuItem("����");
		bMenu.add(smItem);
		bMenu.addSeparator();
		bMenu.add(gyItem);
		// ��������
		this.add(panel);
		panel.setBackground(Color.green);
		this.setBounds(180, 10, 1024, 680);
		this.setVisible(true);
		mBar.add(label);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new Header();
		//jfklsdlfjksldj就分开了圣诞节快乐飞机上的法律上的家附近的酸辣粉
	}
}