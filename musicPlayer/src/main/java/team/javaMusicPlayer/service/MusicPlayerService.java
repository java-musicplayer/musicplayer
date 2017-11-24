package team.javaMusicPlayer.service;

import java.util.List;

import team.javaMusicPlayer.model.Music;
import team.javaMusicPlayer.model.MusicPlayer;

public class MusicPlayerService {
	
	/*
	 * 功能:添加歌曲到播放器
	 */
	public static void addMusicToPlayer(List<Music> nMusics) {
		MusicPlayer musicPlayer=MusicPlayer.getInstance();
		musicPlayer.removeAllMusics();
		for (Music music : nMusics) {
			musicPlayer.addMusic(music);
		}
	}
	
	

}
