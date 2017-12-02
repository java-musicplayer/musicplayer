package team.javaMusicPlayer.ButtomGUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class OperationOfMusic extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OperationOfMusic() {
		// TODO Auto-generated constructor stub
		this.setLayout(new FlowLayout(5));
		JButton playNow = new JButton("播放");
		playNow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		JButton playPreview = new JButton("上一首");
		playPreview.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		JButton playDown = new JButton("下一首");
		playDown.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		JSlider slider = new JSlider(0, 100, 0);
		slider.setPreferredSize(new Dimension(700, 60));
		this.add(playPreview);
		this.add(playNow);
		this.add(playDown);
		this.add(slider);
	}

}
