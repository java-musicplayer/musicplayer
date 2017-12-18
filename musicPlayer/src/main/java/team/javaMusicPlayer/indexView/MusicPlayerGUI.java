package team.javaMusicPlayer.indexView;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
		//登录
		String log = new String("1");
		StringBuilder logBuild = new StringBuilder(log);
		JFrame fm = new JFrame();
		fm.setTitle("登录");
		fm.setSize(400,200);
		fm.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		fm.setVisible(true);
		
		Container fmContainer = fm.getContentPane();
		fmContainer.setLayout(new BoxLayout(fmContainer, BoxLayout.Y_AXIS));
		
		JPanel pnListName = new JPanel();
		pnListName.setPreferredSize(new Dimension(500, 30));
		pnListName.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lbListName = new JLabel("学号");
		JTextField txtListName = new JTextField(15);
		pnListName.add(lbListName);
		pnListName.add(txtListName);

		JPanel pnOperation = new JPanel();
		pnOperation.setPreferredSize(new Dimension(500, 30));
		pnOperation.setLayout(new FlowLayout(FlowLayout.CENTER));
		JButton btnSure = new JButton("确定");
		class ActionLog implements ActionListener{
			private StringBuilder log;
			public ActionLog(StringBuilder log1) {
				log = log1;
			}
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				log.append(txtListName.getText().substring(1));
				
				System.out.println(log);
				//创建歌单
				fm.setVisible(false);
			}
		}
		btnSure.addActionListener(new ActionLog(logBuild) );
		System.out.println(logBuild.toString());
		
		pnOperation.add(btnSure);
		
		fmContainer.add(pnListName);
		fmContainer.add(pnOperation);
		
		fm.setVisible(true);

		while(logBuild.toString().equals("1")) {
			;
		}
		System.out.println(logBuild.toString());

		//播放线程
		Map<Integer, Thread> lt = new HashMap<Integer, Thread>();
		
		//Center
		JPanel centerJPanel = new JPanel();
		centerJPanel.setPreferredSize(new Dimension(735, 625));
		BoxLayout centerPanelLayout = new BoxLayout(centerJPanel, BoxLayout.Y_AXIS);
		centerJPanel.setLayout(centerPanelLayout);
		
		MusicListInformation mi = new MusicListInformation(lt);
		MusicList list = new MusicList(lt);
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
		List<MusicSheet> myMusicLists = musicSheetService.getMusicSheetsByCreatorId(logBuild.toString());
		
		
		for (MusicSheet musicSheet2 : myMusicLists) {
			System.out.println(musicSheet2);
		}
		LocalMusicLists myLists = new LocalMusicLists(mi, myMusicLists, list, logBuild);
		westJPanel.add(new OtherMusicLists(mi, shareMusicLists, list));
		westJPanel.add(myLists);
		westJPanel.add(new OperationOfMusicLists(myLists, logBuild));

		
		
		//Buttom
		JPanel buttomJPanel = new JPanel();
		buttomJPanel.setPreferredSize(new Dimension(1035, 60));
		buttomJPanel.setLayout(new FlowLayout(5));
		buttomJPanel.add(new OperationOfMusic(list, lt));
	
		thisContainer.add("West", westJPanel);
		thisContainer.add("Center", centerJPanel);
		thisContainer.add("South", buttomJPanel);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MusicPlayerGUI().setVisible(true);
	}

}
