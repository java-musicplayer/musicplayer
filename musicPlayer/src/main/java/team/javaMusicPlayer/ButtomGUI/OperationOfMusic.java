package team.javaMusicPlayer.ButtomGUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;

import team.javaMusicPlayer.CenterGUI.MusicList;
import team.javaMusicPlayer.model.Music;
import team.javaMusicPlayer.model.MusicPlayer;
import team.javaMusicPlayer.service.SokectService;

public class OperationOfMusic extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public OperationOfMusic(MusicList ml, Map<Integer, Thread> lt) {
		// TODO Auto-generated constructor stub
		this.setLayout(new FlowLayout(5));
		JButton playNow = new JButton("播放");
		playNow.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int type = ml.getTypeOfMusic();
				//网络
				int row = ml.getMusicList().getSelectedRow();
				if(type == 0) {
					  
					Map<String, String> shareMusicListContent = ml.getShareMusicListContent();
					ArrayList<String> keys = new ArrayList<String>(); 
		    		ArrayList<String> values = new ArrayList<String>(); 
		    		for (String key : shareMusicListContent.keySet()) {  
		    		  
		    		    keys.add(key); 
		    		  
		    		}  
		    		for (String value : shareMusicListContent.values()) {  
		    			  
		    			values.add(value); 
		    		  
		    		}
		            	MusicPlayer musicPlayer=MusicPlayer.getInstance();
		            	musicPlayer.removeAllMusics();
		            	musicPlayer.addMusic(SokectService.downloadMusic(keys.get(row), values.get(row)));
		    			//起一个线程
		    			Thread thread=new Thread() {
		    				public void run() {
		    					try {
									musicPlayer.play();
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
		    				}
		    			};
		    			if(lt.isEmpty()) {
		    				lt.put(row, thread);
		    				thread.start();
		    			}
		    				
		    			else {
		    				for(Thread tr : lt.values()) {
		    					tr.stop();
		    				}
		    				lt.clear();

		    			}
		    			
		    			System.out.println("线程开启成功！");
		        }
				
				
				//本地
				else {
					List<Music> lm = ml.getTmpm();
					MusicPlayer musicPlayer=MusicPlayer.getInstance();
					musicPlayer.removeAllMusics();
	            	musicPlayer.addMusic(lm.get(row));
	    			//起一个线程
	    			Thread thread=new Thread() {
	    				public void run() {
	    					try {
								musicPlayer.play();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	    				}
	    			};
	    			if(lt.isEmpty()) {
	    				lt.put(row, thread);
	    				thread.start();
	    			}		
	    			else {
	    				for(Thread tr : lt.values()) {
	    					if(tr.isAlive())
	    						tr.stop();
	    				}
	    				lt.clear();

	    			}
	    		
	    			System.out.println("线程开启成功！");

				}
				
			}
		});
		JButton playPreview = new JButton("上一首");
		playPreview.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int type = ml.getTypeOfMusic();
				int row = 0;
				//网络
				if(type == 0) {
					
					Map<String, String> shareMusicListContent = ml.getShareMusicListContent();
					int amount = shareMusicListContent.size();
					if(lt.isEmpty()) {
						JOptionPane.showMessageDialog(null, "你没有选择音乐");  
					}
	    			else {
	    				for(Thread tr : lt.values()) {
	    					if(tr.isAlive())
	    						tr.stop();
	    				}
	    				Integer index = null;
	    				for(Integer it : lt.keySet()) {
	    					index = it;
	    				}
						if(index.intValue() - 1 < 0) {
							row = amount;
						}
						else {
							row = index.intValue() - 1;
						}
	    			}

					ArrayList<String> keys = new ArrayList<String>(); 
		    		ArrayList<String> values = new ArrayList<String>(); 
		    		for (String key : shareMusicListContent.keySet()) {  
		    		  
		    		    keys.add(key); 
		    		  
		    		}  
		    		for (String value : shareMusicListContent.values()) {  
		    			  
		    			values.add(value); 
		    		  
		    		}
		    		MusicPlayer musicPlayer=MusicPlayer.getInstance();
		    		musicPlayer.removeAllMusics();
	            	musicPlayer.addMusic(SokectService.downloadMusic(keys.get(row), values.get(row)));
	    			//起一个线程
	    			Thread thread=new Thread() {
	    				public void run() {
	    					try {
								musicPlayer.play();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	    				}
	    			};
	    			lt.clear();
	    			lt.put(row, thread);
	    			thread.start();
	    			System.out.println("线程开启成功！");
				}
				//本地
				else {
					List<Music> lm = ml.getTmpm();
					int amount = 0;
					amount = lm.size();
					if(lt.isEmpty()) {
						JOptionPane.showMessageDialog(null, "你没有选择音乐");  
					}
	    			else {
	    				for(Thread tr : lt.values()) {
	    					if(tr.isAlive())
	    						tr.stop();
	    				}
	    				Integer index = null;
	    				for(Integer it : lt.keySet()) {
	    					index = it;
	    				}
	    				//超出范围,最后一首
	    				if(index.intValue() - 1 < 0) {

	    					MusicPlayer musicPlayer=MusicPlayer.getInstance();
	    					musicPlayer.removeAllMusics();
	    	            	musicPlayer.addMusic(lm.get(amount - 1));
	    	    			//起一个线程
	    	    			Thread thread=new Thread() {
	    	    				public void run() {
	    	    					try {
	    								musicPlayer.play();
	    							} catch (Exception e) {
	    								// TODO Auto-generated catch block
	    								e.printStackTrace();
	    							}
	    	    				}
	    	    			};
	    	    			lt.clear();
	    	    			lt.put(amount - 1, thread);
			    			thread.start();
			    			System.out.println("线程开启成功！");
	    				}
	    				else {
	    					MusicPlayer musicPlayer=MusicPlayer.getInstance();
	    					musicPlayer.removeAllMusics();
	    	            	musicPlayer.addMusic(lm.get(index.intValue() - 1));
	    	    			//起一个线程
	    	    			Thread thread=new Thread() {
	    	    				public void run() {
	    	    					try {
	    								musicPlayer.play();
	    							} catch (Exception e) {
	    								// TODO Auto-generated catch block
	    								e.printStackTrace();
	    							}
	    	    				}
	    	    			};
	    	    			lt.clear();
	    	    			lt.put(index.intValue() - 1, thread);
			    			thread.start();
			    			System.out.println("线程开启成功！");
	    				}
	    			}
				}
				

			}
		});
		JButton playDown = new JButton("下一首");
		playDown.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int type = ml.getTypeOfMusic();
				int row = 0;
				//网络
				if(type == 0) {
					
					Map<String, String> shareMusicListContent = ml.getShareMusicListContent();
					int amount = shareMusicListContent.size();
					if(lt.isEmpty()) {
						JOptionPane.showMessageDialog(null, "你没有选择音乐");  
					}
	    			else {
	    				for(Thread tr : lt.values()) {
	    					if(tr.isAlive())
	    						tr.stop();
	    				}
	    				Integer index = null;
	    				for(Integer it : lt.keySet()) {
	    					index = it;
	    				}
						if(index.intValue() + 1 >= amount) {
							row = 0;
						}
						else {
							row = index.intValue() + 1;
						}
	    			}

					ArrayList<String> keys = new ArrayList<String>(); 
		    		ArrayList<String> values = new ArrayList<String>(); 
		    		for (String key : shareMusicListContent.keySet()) {  
		    		  
		    		    keys.add(key); 
		    		  
		    		}  
		    		for (String value : shareMusicListContent.values()) {  
		    			  
		    			values.add(value); 
		    		  
		    		}
		    		MusicPlayer musicPlayer=MusicPlayer.getInstance();
		    		musicPlayer.removeAllMusics();
	            	musicPlayer.addMusic(SokectService.downloadMusic(keys.get(row), values.get(row)));
	    			//起一个线程
	    			Thread thread=new Thread() {
	    				public void run() {
	    					try {
								musicPlayer.play();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	    				}
	    			};
	    			lt.clear();
	    			lt.put(row, thread);
	    			thread.start();
	    			System.out.println("线程开启成功！");
				}
				//本地
				else {
					List<Music> lm = ml.getTmpm();
					int amount = lm.size();
					if(lt.isEmpty()) {
						JOptionPane.showMessageDialog(null, "你没有选择音乐");  
					}
	    			else {
	    				for(Thread tr : lt.values()) {
	    					if(tr.isAlive())
	    						tr.stop();
	    				}
	    				Integer index = null;
	    				for(Integer it : lt.keySet()) {
	    					index = it;
	    				}
	    				//超出范围,第一首
	    				if(index.intValue() + 1 >= amount) {

	    					MusicPlayer musicPlayer=MusicPlayer.getInstance();
	    					musicPlayer.removeAllMusics();
	    	            	musicPlayer.addMusic(lm.get(0));
	    	    			//起一个线程
	    	    			Thread thread=new Thread() {
	    	    				public void run() {
	    	    					try {
	    								musicPlayer.play();
	    							} catch (Exception e) {
	    								// TODO Auto-generated catch block
	    								e.printStackTrace();
	    							}
	    	    				}
	    	    			};
	    	    			lt.clear();
	    	    			lt.put(0, thread);
			    			thread.start();
			    			System.out.println("线程开启成功！");
	    				}
	    				else {
	    					MusicPlayer musicPlayer=MusicPlayer.getInstance();
	    					musicPlayer.removeAllMusics();
	    	            	musicPlayer.addMusic(lm.get(index.intValue() + 1));
	    	    			//起一个线程
	    	    			Thread thread=new Thread() {
	    	    				public void run() {
	    	    					try {
	    								musicPlayer.play();
	    							} catch (Exception e) {
	    								// TODO Auto-generated catch block
	    								e.printStackTrace();
	    							}
	    	    				}
	    	    			};
	    	    			lt.clear();
	    	    			lt.put(index.intValue() + 1, thread);
			    			thread.start();
			    			System.out.println("线程开启成功！");
	    				}
	    			}
				}
				
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
