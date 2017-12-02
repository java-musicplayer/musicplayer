package backstageTest;


import java.util.List;
import java.util.Scanner;

import team.javaMusicPlayer.model.Music;
import team.javaMusicPlayer.model.MusicPlayer;
import team.javaMusicPlayer.model.MusicSheet;
import team.javaMusicPlayer.service.MusicSheetService;

public class MusicPlayerTest {
	public static void main(String[] args) {
		MusicPlayer musicPlayer=MusicPlayer.getInstance();
		
		MusicSheetService musicSheetService=new MusicSheetService();
		//获取歌单中音乐
		List<Music> tmpm=musicSheetService.getMusicByMusicSheetId(2);
		//添加单曲
//		musicPlayer.addMusic((tmpm.get(0)));
		
		//添加歌单所有歌曲
		musicPlayer.addMusicToPlayer(tmpm);
		
	
			System.out.println("bofang");
		
			//起一个线程
			Thread thread=new Thread() {
				public void run() {
					try {
						do {
							musicPlayer.autoNext();
						} while (musicPlayer.getNowMusic()!=null);
						
							
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			thread.start();
			System.out.println("线程开启成功！");
			Scanner scanner=new Scanner(System.in);
			
			//改变自动播放模式
//			scanner.nextLine();
//			musicPlayer.setNowMode(MusicPlayer.PLAYMODE.SINGLE);
//			System.out.println("改变播放模式!");
//			
			//在自动播放中切换上一首、下一首开个线程。执行完自己退出到自动播放的线程里面。
//			try {
//				//下一首
//				scanner.nextLine();
//				musicPlayer.playNext();
//				System.out.println("播放下一首!");
//				//上一首
//				scanner.nextLine();
//				musicPlayer.playLast();
//				System.out.println("播放上一首!");
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			//移除所有歌曲
			scanner.nextLine();
			musicPlayer.close();
//			musicPlayer.removeAllMusics();
			System.out.println("移除所有歌曲，播放完当前曲目停止播放！");
		
	}

}
