package team.javaMusicPlayer.model;

import java.util.List;

public class MusicPlayer {
	private int totalSongs;
	private List<Music> playList;
	
	/*
	 * 功能:添加待播放音乐到播放器
	 *@param: 歌曲
	 *@return: 当前歌曲数量
	 */
	public int addMusic(Music music) {
		
		return getTotalSongs();
	}

	public List<Music> getPlayList() {
		return playList;
	}

	public int getTotalSongs() {
		return totalSongs;
	}
	

}
