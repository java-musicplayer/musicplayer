package team.javaMusicPlayer.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


import team.javaMusicPlayer.dao.impl.MusicDaoImpl;
import team.javaMusicPlayer.model.Music;
import team.javaMusicPlayer.model.MusicSheet;
import team.javaMusicPlayer.sokect.DownloadResource;
import team.javaMusicPlayer.sokect.MusicSheetAndFilesUploader;

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
				if(musicUrl==null) {
					//下载失败，略过这条记录
					System.out.println(ss+"下载失败!");
					continue;
				}
				System.out.println(musicItems.get(ss)+"  OK!"+"\t"+"URL:"+musicUrl);
				m.setMd5value(ss);
				m.setName(musicItems.get(ss).split("\\.")[0]);
				m.setMusicUrl(musicUrl);
				m.setSinger("");
				if(musicDaoImpl.insert(m))
					ans.add(m);
			}
		}
		return ans;
	}
	
	
	/**
	 * 下载一首歌曲,存库，下载失败返回NULL
	 * @param downMusic
	 * @return
	 */
	public static Music downloadMusic(String md5Value ,String name) {
		MusicDaoImpl musicDaoImpl=new MusicDaoImpl();
		Music m=musicDaoImpl.getByMd5Value(md5Value);
		//判断本地是否有本首歌曲，没有进行下载
		if(m==null) {
			m=new Music();
			String musicUrl=DownloadResource.downloadMusic(md5Value);
			if(musicUrl==null) {
				//下载失败，略过这条记录
				System.out.println(name+"下载失败!");
				return null;
			}
			System.out.println(name+"  OK!"+"\t"+"URL:"+musicUrl);
			m.setMd5value(md5Value);
			m.setName(name.split("\\.")[0]);
			m.setMusicUrl(musicUrl);
			m.setSinger("");
			if(musicDaoImpl.insert(m))
				return m;
			else
				return null;
		}
		else
			return m;
	}
	
	
	/**
	 * 功能: 下载图片
	 * @return 失败返回null，正确返回绝对路径
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
	 * 歌单封面默认代表此歌单封面不存在
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
		//填充图片属性
		//下载各个歌单图片
		for (MusicSheet musicSheet : anSheets) {
			String picUrl=downloadPicture(musicSheet.getUuid());
			if(picUrl!=null) {
				musicSheet.setPicture(picUrl);
			}else {
				//设置默认图片
				musicSheet.setPicture(new File("resources/images/defaultFaceImg.jpeg").getAbsolutePath());
				System.out.println(musicSheet.getName()+"没有封面!"+musicSheet.getUuid()+"使用默认图片");
			}
		}
		return anSheets;
	}

	
	/**
	 * 调用方开一个线程，写好回调函数，提示上传完成
	 * 歌单上传，上传歌单到服务器（不需要包括musicItems），但是ID，很重要
	 * @param ms
	 * @return
	 */
	public static boolean uploadMusicSheet(MusicSheet ms) {
		//给歌单添加 musicitems
		MusicSheetService musicSheetService=new MusicSheetService();
		Map<String, String> musicItems=new HashMap<String,String>();
		List<Music> ls=musicSheetService.getMusicByMusicSheetId(ms.getId());
		for (Music music : ls) {
			musicItems.put(music.getMd5value(), music.getName());
		}
		ms.setMusicItems(musicItems);
		return MusicSheetAndFilesUploader.upload(ms);
	}
}
