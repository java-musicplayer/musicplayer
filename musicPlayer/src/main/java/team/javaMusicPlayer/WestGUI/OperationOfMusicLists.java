package team.javaMusicPlayer.WestGUI;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import team.javaMusicPlayer.service.MusicSheetService;

public class OperationOfMusicLists extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OperationOfMusicLists() {
		// TODO Auto-generated constructor stub
		this.setPreferredSize(new Dimension(250, 45));
		FlowLayout layout = new FlowLayout(5);
		this.setLayout(layout);
		
		JButton btnNewList = new JButton("新建歌单");
		
		btnNewList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame fm = new JFrame();
				fm.setTitle("创建新歌单");
				fm.setSize(500, 300);
				fm.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				
//				JDialog log = new JDialog(fm,"创建歌单",true);
//				log.setSize(500, 300);
//				log.setVisible(true);
				
				Container thisContainer = fm.getContentPane();
				thisContainer.setLayout(new BoxLayout(thisContainer, BoxLayout.Y_AXIS));
				
				JPanel pnListName = new JPanel();
				pnListName.setPreferredSize(new Dimension(500, 30));
				pnListName.setLayout(new FlowLayout(FlowLayout.CENTER));
				JLabel lbListName = new JLabel("歌单名称");
				JTextField txtListName = new JTextField(15);
				pnListName.add(lbListName);
				pnListName.add(txtListName);
				
				JPanel pnUpId = new JPanel();
				pnListName.setPreferredSize(new Dimension(500, 30));
				pnListName.setLayout(new FlowLayout(FlowLayout.CENTER));
				JLabel lbNumber = new JLabel("学号");
				JTextField txtNumber = new JTextField(15);
				pnUpId.add(lbNumber);
				pnUpId.add(txtNumber);
				
				JPanel pnUpName = new JPanel();
				pnListName.setPreferredSize(new Dimension(500, 30));
				pnListName.setLayout(new FlowLayout(FlowLayout.CENTER));
				JLabel lbName = new JLabel("名称");
				JTextField txtName = new JTextField(15);
				pnUpName.add(lbName);
				pnUpName.add(txtName);
				
				JPanel pnOperation = new JPanel();
				pnOperation.setPreferredSize(new Dimension(500, 30));
				pnOperation.setLayout(new FlowLayout(FlowLayout.CENTER));
				JButton btnSure = new JButton("确定");
				btnSure.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						MusicSheetService musicSheetService=new MusicSheetService();
						
						musicSheetService.createMusicSheet(txtListName.getText(), txtNumber.getText(), txtName.getText(), "");
					}
				});
				
				JButton btnCancel = new JButton("取消");
				btnCancel.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						fm.setVisible(false);
					}
				});
				pnOperation.add(btnSure);
				pnOperation.add(btnCancel);
				
				thisContainer.add(Box.createVerticalStrut(50));
				thisContainer.add(pnListName);
				thisContainer.add(pnUpId);
				thisContainer.add(pnUpName);
				thisContainer.add(pnOperation);
				
				fm.setVisible(true);

				
			}
		});
		JButton btnDeleteList = new JButton("删除歌单");
		btnDeleteList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame fm = new JFrame();
				fm.setTitle("删除歌单");
				fm.setSize(500, 300);
				fm.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				
//				JDialog log = new JDialog(fm,"创建歌单",true);
//				log.setSize(500, 300);
//				log.setVisible(true);
				
				Container thisContainer = fm.getContentPane();
				thisContainer.setLayout(new BoxLayout(thisContainer, BoxLayout.Y_AXIS));
				
				JPanel pnListName = new JPanel();
				pnListName.setPreferredSize(new Dimension(500, 30));
				pnListName.setLayout(new FlowLayout(FlowLayout.CENTER));
				JLabel lbListName = new JLabel("歌单名称");
				JTextField txtListName = new JTextField(15);
				pnListName.add(lbListName);
				pnListName.add(txtListName);
				
				JPanel pnOperation = new JPanel();
				pnOperation.setPreferredSize(new Dimension(500, 30));
				pnOperation.setLayout(new FlowLayout(FlowLayout.CENTER));
				JButton btnSure = new JButton("确定");
				btnSure.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						MusicSheetService musicSheetService=new MusicSheetService();
						
					}
				});
				
				JButton btnCancel = new JButton("取消");
				btnCancel.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						fm.setVisible(false);
					}
				});

				pnOperation.add(btnSure);
				pnOperation.add(btnCancel);
				
				thisContainer.add(Box.createVerticalStrut(50));
				thisContainer.add(pnListName);
				thisContainer.add(pnOperation);
				
				fm.setVisible(true);
			}
		});
		
		
//		JButton btnList = new JButton("查看歌单");
//		btnList.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
		
		this.add(btnNewList);
		this.add(btnDeleteList);
		//this.add(btnList);
	}

}
