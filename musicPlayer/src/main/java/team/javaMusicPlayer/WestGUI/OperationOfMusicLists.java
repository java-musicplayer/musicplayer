package team.javaMusicPlayer.WestGUI;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import team.javaMusicPlayer.model.MusicSheet;
import team.javaMusicPlayer.service.MusicService;
import team.javaMusicPlayer.service.MusicSheetService;

public class OperationOfMusicLists extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OperationOfMusicLists(LocalMusicLists lists, StringBuilder logBuild) {
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
				fm.setSize(700, 500);
				fm.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				
				Container thisContainer = fm.getContentPane();
				thisContainer.setLayout(new BoxLayout(thisContainer, BoxLayout.Y_AXIS));
				
				JPanel pnListName = new JPanel();
				pnListName.setPreferredSize(new Dimension(500, 30));
				pnListName.setLayout(new FlowLayout(FlowLayout.CENTER));
				JLabel lbListName = new JLabel("歌单名称");
				JTextField txtListName = new JTextField(15);
				pnListName.add(lbListName);
				pnListName.add(txtListName);
				
				JPanel pnUpName = new JPanel();
				pnUpName.setPreferredSize(new Dimension(500, 30));
				pnUpName.setLayout(new FlowLayout(FlowLayout.CENTER));
				JLabel lbName = new JLabel("名称");
				JTextField txtName = new JTextField(15);
				pnUpName.add(lbName);
				pnUpName.add(txtName);
				
				JPanel pnUpPhoto = new JPanel();
				pnUpPhoto.setPreferredSize(new Dimension(500, 400));
				pnUpPhoto.setLayout(new FlowLayout(FlowLayout.CENTER));
				JLabel lbPhoto = new JLabel("图片路径");
				JFileChooser txtPhoto = new JFileChooser();

				pnUpPhoto.add(lbPhoto);
				pnUpPhoto.add(txtPhoto);
				
				JPanel pnOperation = new JPanel();
				pnOperation.setPreferredSize(new Dimension(500, 30));
				pnOperation.setLayout(new FlowLayout(FlowLayout.CENTER));
				JButton btnSure = new JButton("确定");
				btnSure.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						MusicSheetService musicSheetService=new MusicSheetService();
						//创建歌单
						String route = txtPhoto.getSelectedFile().getAbsolutePath();
						musicSheetService.createMusicSheet(txtListName.getText(), logBuild.toString(), txtName.getText(), route);
						if(lists.getDtLocalMusicLists().getValueAt(0, 0).equals("")) {
							lists.getDtLocalMusicLists().removeRow(0);
						}
						lists.getDtLocalMusicLists().addRow(new Object[] {txtListName.getText(), logBuild.toString(), txtName.getText(), route});
						lists.setMyList(musicSheetService.getMusicSheetsByCreatorId(logBuild.toString()));
						lists.getLocalMusicListsTable().revalidate();
						lists.updateUI();
						fm.setVisible(false);
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
				
				thisContainer.add(pnListName);

				thisContainer.add(pnUpName);
				thisContainer.add(pnUpPhoto);
				thisContainer.add(pnOperation);
				
				fm.setVisible(true);

				
			}
		});
		JButton btnDeleteList = new JButton("删除歌单");
		btnDeleteList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("删除歌单")) {
					int rowCount = lists.getDtLocalMusicLists().getRowCount()-1;
					int nowRow =  lists.getLocalMusicListsTable().getSelectedRow();
					if(rowCount >= 0) {
						lists.getDtLocalMusicLists().removeRow(nowRow);
						lists.getDtLocalMusicLists().setRowCount(rowCount);
						
						MusicSheetService ms = new MusicSheetService();
						List<MusicSheet> ls = ms.getMusicSheetsByCreatorId(logBuild.toString());
						ms.deleteMusicSheet(ls.get(nowRow).getId());
					}
					lists.getLocalMusicListsTable().revalidate();
					
				}
				// TODO Auto-generated method stub

			}
		});
		
		
		JButton btnList = new JButton("添加新歌");
		btnList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				JFrame fm = new JFrame();
				fm.setTitle("添加新歌");
				fm.setSize(700, 500);
				fm.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				fm.setVisible(true);
				
				Container thisContainer = fm.getContentPane();
				thisContainer.setLayout(new BoxLayout(thisContainer, BoxLayout.Y_AXIS));
				
				JPanel pnMusicName = new JPanel();
				pnMusicName.setPreferredSize(new Dimension(500, 30));
				pnMusicName.setLayout(new FlowLayout(FlowLayout.CENTER));
				JLabel lbMusicName = new JLabel("歌名");
				JTextField txtMusicName = new JTextField(15);
				pnMusicName.add(lbMusicName);
				pnMusicName.add(txtMusicName);
				
				JPanel pnMusicRout = new JPanel();
				pnMusicRout.setPreferredSize(new Dimension(500, 400));
				pnMusicRout.setLayout(new FlowLayout(FlowLayout.CENTER));
				JLabel lbMusicRout = new JLabel("歌曲路径");
				JFileChooser txtMusicRout = new JFileChooser();

				pnMusicRout.add(lbMusicRout);
				pnMusicRout.add(txtMusicRout);
				
				JPanel pnMusicerName = new JPanel();
				pnMusicerName.setPreferredSize(new Dimension(500, 30));
				pnMusicerName.setLayout(new FlowLayout(FlowLayout.CENTER));
				JLabel lbMusicerName = new JLabel("歌手名称");
				JTextField txtMusicerName = new JTextField(15);
				pnMusicerName.add(lbMusicerName);
				pnMusicerName.add(txtMusicerName);
				
				JPanel pnOperation = new JPanel();
				pnOperation.setPreferredSize(new Dimension(500, 30));
				pnOperation.setLayout(new FlowLayout(FlowLayout.CENTER));
				JButton btnSure = new JButton("确定");
				btnSure.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						MusicSheetService musicSheetService=new MusicSheetService();
						//创建歌单
						MusicService musicService=new MusicService();

						int nowRow = lists.getLocalMusicListsTable().getSelectedRow();
						System.out.println(nowRow);
						MusicSheet ms = lists.getMyList().get(nowRow);
						musicSheetService.addMusicsToMusicSheet(ms.getId(), musicService.getById(musicService.insert(txtMusicName.getText(), txtMusicRout.getSelectedFile().getAbsolutePath(), txtMusicerName.getText())));
						
						lists.getLocalMusicListsTable().revalidate();
						lists.updateUI();
						
						fm.setVisible(false);
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
				
				
				thisContainer.add(pnMusicName);
				thisContainer.add(pnMusicRout);
				thisContainer.add(pnMusicerName);
				thisContainer.add(pnOperation);
				

				
			}
		});
		
		this.add(btnNewList);
		this.add(btnDeleteList);
		this.add(btnList);
	}

}
