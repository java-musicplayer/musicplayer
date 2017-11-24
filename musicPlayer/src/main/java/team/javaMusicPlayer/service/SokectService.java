package team.javaMusicPlayer.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


import team.javaMusicPlayer.dao.impl.MusicDaoImpl;
import team.javaMusicPlayer.model.Music;
import team.javaMusicPlayer.model.MusicSheet;
import team.javaMusicPlayer.sokect.DownloadResource;

public class SokectService {
	
	/**
	 * 功能: 下载歌曲,并且存到数据库,判断本地是否有MD5一样的歌曲
	 * @param <md5,name>
	 * @return 返回完整下载后歌曲信息
	 */
	public static List<Music> downloadMusic(Map<String, String> musicItems) {
		
		MusicDaoImpl musicDaoImpl=new MusicDaoImpl();
		List<Music> ans=new ArrayList<Music>();
		for (String ss : musicItems.keySet()) {
			Music m=musicDaoImpl.getByMd5Value(ss);
			//判断本地是否有本首歌曲，没有进行下载
			if(m!=null)
				ans.add(m);
			else {
				m=new Music();
				String musicUrl=DownloadResource.downloadMusic(ss);
				m.setMd5value(ss);
				m.setName(musicItems.get(ss));
				m.setMusicUrl(musicUrl);
				m.setSinger("");
				if(musicDaoImpl.insert(m))
					ans.add(m);
			}
		}
		return ans;
	}
	
	
	/*
	 * 功能: 下载图片
	 * 
	 */
	public static String downloadPicture(String uuid) {
		//检测本地有没有该UUID的图片
		File[] files=new File("data/pictures/").listFiles();
		for (File file : files) {
			if(file.getName().contains(uuid))
				return file.getAbsolutePath();
		}
		return	DownloadResource.downloadPicture(uuid);
	}
	
	
	/**
	 * 功能: 查询所有在线用户歌单
	 * @return
	 */
	public static List<MusicSheet> queryMusicSheets() {
		//根据学号去重复，实现别人都在听那里不重复显示同一个学号的歌单
		Set<MusicSheet> singleMusicSheet=new HashSet<MusicSheet>();
		singleMusicSheet.addAll(DownloadResource.queryMusic());
		List<MusicSheet> anSheets=new ArrayList<MusicSheet>();
		for (MusicSheet ms : singleMusicSheet) {
			anSheets.add(ms);
		}
		//
		return anSheets;
	}

}
