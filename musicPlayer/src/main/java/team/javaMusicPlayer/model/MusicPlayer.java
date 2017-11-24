package team.javaMusicPlayer.model;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javazoom.jl.player.Player;

public class MusicPlayer {
	public static enum PLAYMODE {
		RANDOM, ORDER, SINGLE
	}; // 播放模式{随机，顺序，单曲}

	private PLAYMODE nowMode;
	private static MusicPlayer musicPlayer;
	private Music nowMusic;
	private Player sysPlayer; // 调用系统播放器，只能播放MP3
	private List<Music> musicsList;

	static {
		musicPlayer = null;
	}

	/*
	 * 功能: 单例模式获取播放器实例
	 */
	public static MusicPlayer getInstance() {
		if (musicPlayer == null)
			musicPlayer = new MusicPlayer();
		return musicPlayer;
	}

	private MusicPlayer() {
		this.nowMode = PLAYMODE.ORDER;
		this.nowMusic = null;
		this.sysPlayer = null;
		this.musicsList = new ArrayList<Music>();
	}

	/*
	 * 功能: 播放上一首歌曲
	 */
	public void playLast() throws Exception {
		int nowIndex = getNowMusicIndex();
		if (nowIndex > 0)
			nowMusic = musicsList.get(--nowIndex < 0 ? musicsList.size() + nowIndex : nowIndex);
		play();
	}

	/*
	 * 功能: 播放下一首歌曲
	 */
	public void playNext() throws Exception {
		int nowIndex = getNowMusicIndex();
		int musicListSize = musicsList.size();
		if (nowIndex > 0)
			nowMusic = musicsList.get(++nowIndex > (musicListSize - 1) ? nowIndex - musicListSize : nowIndex);
		play();
	}

	/*
	 * 功能: 根据当前模式播放下一首歌曲
	 */
	public void autoNext() throws Exception {
		int nextIndex=0;
		switch (nowMode) {
		case RANDOM:
			Random random=new Random();
			nextIndex=random.nextInt(musicsList.size());
			break;
		case ORDER:
			playNext();
			return;
		case SINGLE:
			nextIndex=getNowMusicIndex();
			break;
		default:
			break;
		}
		nowMusic=musicsList.get(nextIndex);
		play();
	}

	/*
	 * 功能;添加一首音乐到播放器
	 */
	public void addMusic(Music nMusic) {
		musicsList.add(nMusic);
	}

	/*
	 * 功能:移除所有歌曲，切换新歌单时候调用
	 */
	public void removeAllMusics() {
		musicsList.clear();
	}

	/*
	 * 播放当前歌曲
	 */
	public boolean play() throws Exception {
		try {
			if (musicsList.size() != 0 && nowMusic == null) {
				nowMusic = musicsList.get(0);
			}
			BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(nowMusic.getMusicUrl()));
			sysPlayer = new Player(buffer);
			sysPlayer.play();
			return true;
		} catch (Exception e) {
			throw new Exception("播放器当前没有歌曲!");
		}
	}

	/*
	 * 功能： 歌曲是否播放完
	 */
	public boolean isComplete() {
		return sysPlayer.isComplete();
	}

	/*
	 * 功能: 关闭播放器
	 */
	public void close() {
		sysPlayer.close();
	}

	/*
	 * 功能: 获取当前歌曲进度
	 */
	public int where() {
		return sysPlayer.getPosition();
	}

	/*
	 * 功能: 获取当前播放歌曲的ID
	 */
	private int getNowMusicIndex() {
		int nowIndex = -1;
		for (Music music : musicsList) {
			nowIndex++;
			if (music.equals(nowMusic))
				break;
		}
		return nowIndex;
	}

	/**
	 * 功能:添加歌单歌曲到播放器
	 * 本操作会清空当前播放器的歌曲，重新添加进去新的歌曲列表
	 */
	public void addMusicToPlayer(List<Music> nMusics) {
		musicsList.clear();
		for (Music music : nMusics) {
			musicsList.add(music);
		}
	}
	
	
	
	public PLAYMODE getNowMode() {
		return nowMode;
	}

	public Music getNowMusic() {
		return nowMusic;
	}

	public void setNowMusic(Music nowMusic) {
		this.nowMusic = nowMusic;
	}

	public void setNowMode(PLAYMODE nowMode) {
		this.nowMode = nowMode;
	}

}
