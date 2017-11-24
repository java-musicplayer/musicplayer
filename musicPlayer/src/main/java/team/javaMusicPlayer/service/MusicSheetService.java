/**
 * 
 */
package team.javaMusicPlayer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import team.javaMusicPlayer.dao.impl.MusicDaoImpl;
import team.javaMusicPlayer.dao.impl.MusicSteetToMusicDaoImpl;
import team.javaMusicPlayer.dao.impl.MusicsSheetDaoImpl;
import team.javaMusicPlayer.model.Music;
import team.javaMusicPlayer.model.MusicPlayer;
import team.javaMusicPlayer.model.MusicSheet;
import team.javaMusicPlayer.model.MusicSheetToMusic;

/**
 * @author sky
 *
 */
public class MusicSheetService {
	private MusicsSheetDaoImpl musicsSheetDaoImpl;
	private MusicSteetToMusicDaoImpl musicSteetToMusicDaoImpl;
	private MusicDaoImpl musicDaoImpl;

	public MusicSheetService() {
		musicsSheetDaoImpl = new MusicsSheetDaoImpl();
		musicSteetToMusicDaoImpl = new MusicSteetToMusicDaoImpl();
		musicDaoImpl = new MusicDaoImpl();
	}

	/**
	 * 功能:获取歌单信息
	 * 
	 * @param 歌单的ID
	 */
	public MusicSheet getMusicSheet(int msid) {
		return musicsSheetDaoImpl.getById(msid);
	}

	/**
	 * 功能: 获取某人的所有歌单
	 * 
	 * @return：返回所有歌单
	 */
	public List<MusicSheet> getMusicSheetsByCreatorId(String creatorId) {
		return musicsSheetDaoImpl.getByCreatorId(creatorId);
	}

	/**
	 * 功能:获取歌单的歌曲列表
	 * 
	 */
	public List<Music> getMusicByMusicSheetId(int msid) {
		List<Music> musics = new ArrayList<Music>();

		List<MusicSheetToMusic> musicSheetToMusics = musicSteetToMusicDaoImpl.getByMusicSheetId(msid);
		for (MusicSheetToMusic musicSheetToMusic : musicSheetToMusics) {
			musics.add(musicDaoImpl.getById(musicSheetToMusic.getMusicId()));
		}
		return musics;
	}

	/**
	 * 功能:删除歌单
	 * 
	 * @param: 歌单的ID
	 */
	public boolean deleteMusicSheet(int msid) {
		return musicsSheetDaoImpl.delete(msid);
	}

	/**
	 * 功能: 移除歌单中歌曲
	 * @since 目前沒有此功能 
	 * @param: 歌单歌曲中的关联ID
	 */
	public boolean removeMusicFromMusicSheet(int cid) {
		return false;
	}

	/**
	 * 功能: 创建歌单
	 * 
	 * @param: name:歌单名字
	 *             creator:歌单创建者ID creatorName：歌单创建者名字 pictureUrl: 歌单封面ID
	 * @return: 创建成功返回歌单的ID，否则返回 -1
	 */
	public int createMusicSheet(String name, String creatorId, String creatorName, String pictureUrl) {
		MusicSheet musicSheet=new MusicSheet();
		musicSheet.setName(name);
		musicSheet.setCreator(creatorName);
		musicSheet.setCreatorId(creatorId);
		musicSheet.setPicture(pictureUrl);
		//插入歌单
		musicsSheetDaoImpl.insert(musicSheet);
		//获取插入歌单的ID
		return musicsSheetDaoImpl.getByUuid(musicSheet.getUuid()).getId();
	}

	/**
	 * 功能：添加歌曲到歌单
	 * @param msid		歌单ID
	 * @param musicLis  歌曲列表,可变参数个数，自动识别为数组
	 * @return
	 *
	 * 以下调用方法皆可以
	 * addMusicsToMusicSheet(msid,music);
	 * addMusicsToMusicSheet(msid,music1,music2,music3);
	 * addMusicsToMusicSheet(msid,music[]);
	 * 
	 */
	public void addMusicsToMusicSheet(int msid, Music... musicLis) {
		for (Music music : musicLis) {
			MusicSheetToMusic musicSheetToMusic=new MusicSheetToMusic();
			musicSheetToMusic.setMusicId(music.getId());
			musicSheetToMusic.setMusicId(msid);
		}
	}

	/**
	 * 功能: 播放歌单全部歌曲
	 * 
	 * @param: 歌单ID
	 * @throws Exception 播放器歌曲出错信息
	 */
	public void playAllSongs(int msid) throws Exception  {
		List<Music> musicsList = getMusicByMusicSheetId(msid);
		MusicPlayerService.addMusicToPlayer(musicsList);
		MusicPlayer.getInstance().play();
	}

	/**
	 * 功能：用于全部播放别人的歌单
	 *  @param musicItems 
	 *  @throws Exception 
	 */
	public void playAllSongs(Map<String, String> musicItems) throws Exception {
		// 这里应该要开线程
		List<Music> musicsList = downloadAllMusic(musicItems);
		MusicPlayerService.addMusicToPlayer(musicsList);
		MusicPlayer.getInstance().play();
	}

	/**
	 * 播放选中歌曲，如果本地下载好了，直接返回歌曲MUSIC，调用方调用播放器的setNowMusic,play即可，
	 * 否则返回null，提示先下载
	 * 
	 * @param md5Value
	 */
	public Music playThisSong(String md5Value) {
		// 查看数据库有无本首歌曲
		return musicDaoImpl.getByMd5Value(md5Value);
	}

	/**
	 * 功能: 下载歌单全部歌曲
	 * 新开后台线程调用
	 * @param <md5,name>
	 * @return 返回下载的歌曲信息
	 */
	public List<Music> downloadAllMusic(Map<String, String> musicItems) {
		return SokectService.downloadMusic(musicItems);
	}

}
