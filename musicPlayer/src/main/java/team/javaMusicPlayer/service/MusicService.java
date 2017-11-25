package team.javaMusicPlayer.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

import team.javaMusicPlayer.dao.impl.MusicDaoImpl;
import team.javaMusicPlayer.model.Music;


public class MusicService {
	private MusicDaoImpl musicDaoImpl;
	
	public MusicService() {
		musicDaoImpl=new MusicDaoImpl();
	}
	
	/**
	 * 向数据库添加歌曲
	 * @param name
	 * @param singer
	 * @param url
	 * @return 本歌曲的ID
	 */
	public int insert(String name, String url,String singer) {
		//计算MD5值
		String md5=calcFileMd5(url);
		//先查看数据库里面有无本首歌曲，因为MD5也是一个主键，不可以重复。
		if(musicDaoImpl.getByMd5Value(md5)==null) {
			Music nMusic=new Music();
			nMusic.setMd5value(md5);
			nMusic.setMusicUrl(url);
			nMusic.setName(name);
			nMusic.setSinger(singer);
			musicDaoImpl.insert(nMusic);
		}
		return musicDaoImpl.getByMd5Value(md5).getId();
		
	}
	
	
	/**
	 * 删除歌曲
	 * @param mid
	 */
	public boolean delete(int mid) {
		return musicDaoImpl.delete(mid);
	}
	
	/**
	 * 通过歌曲ID获取歌曲
	 * @param id
	 * @return
	 */
	public Music getById(int id) {
		return musicDaoImpl.getById(id);
	}

	/**
	 * 通过歌曲MD5获取歌曲
	 * @param md5Value
	 * @return
	 */
	public Music getByMd5Value(String md5Value) {
		return musicDaoImpl.getByMd5Value(md5Value);
	}
	
	/**
	 * 计算文件的MD5
	 * @param url  绝对路径
	 * @return
	 */
	private String calcFileMd5(String url) {
		FileInputStream fis=null;
		try {
			fis = new FileInputStream(url);
			return DigestUtils.md5Hex(IOUtils.toByteArray(fis));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(fis!=null)
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return "";
	}

}
