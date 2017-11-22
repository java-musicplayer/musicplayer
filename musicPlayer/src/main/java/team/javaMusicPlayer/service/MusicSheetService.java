/**
 * 
 */
package team.javaMusicPlayer.service;

import java.util.List;

import team.javaMusicPlayer.model.Music;
import team.javaMusicPlayer.model.MusicSheet;

/**
 * @author sky
 *
 */
public class MusicSheetService {
	/*
	 * 功能:获取歌单信息
	 * @param: 歌单的ID
	 * 
	 */
	public MusicSheet getMusicSheet(int msid) {
		return null;
	}
	
	
	/*
	 * 功能: 获取某人的所有歌单
	 * @return：返回所有歌单
	 */
	public List<MusicSheet> getMusicSheetsByCreatorId(String creatorId) {
		return null;
	}
	
	
	/*
	 * 功能:获取歌单的歌曲列表
	 * 说明: 这个列表里面的Music ID属性：歌曲在本歌单中的ID，此ID可用作移除本歌曲从本歌单中
	 */
	public List<Music> getMusicByMusicSheetId(int msid) {
		return null;
	}
	/*
	 * 功能:删除歌单
	 * @param: 歌单的ID 
	 */
	public boolean deleteMusicSheet(int msid) {
		return false;
	}
	
	/*
	 * 功能: 移除歌单中歌曲
	 * @param: 歌单歌曲中的关联ID 
	 */
	public boolean removeMusicFromMusicSheet(int cid) {
		return false;
	}
	
	/*
	 * 功能: 创建歌单
	 * @param: name:歌单名字 	creator:歌单创建者ID	creatorName：歌单创建者名字		pictureUrl: 歌单封面ID
	 * @return: 创建成功返回歌单的ID，否则返回 -1
	 */
	public int createMusicSheet(String name, String creatorId,String creatorName, String pictureUrl)
	{
		return 0;
	}
	
	/*
	 * 功能：添加歌曲到歌单
	 * @return: 是否成功
	 */
	public boolean addMusicsToMusicSheet(int msid,List<Music> musicLis) {
		return false;
	}

}
