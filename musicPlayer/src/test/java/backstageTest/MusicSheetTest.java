package backstageTest;

import java.util.List;
import java.util.Map;

import team.javaMusicPlayer.model.Music;
import team.javaMusicPlayer.model.MusicSheet;
import team.javaMusicPlayer.service.MusicService;
import team.javaMusicPlayer.service.MusicSheetService;
import team.javaMusicPlayer.service.SokectService;

public class MusicSheetTest {

	public static void main(String[] args) {
		MusicSheetService musicSheetService=new MusicSheetService();
		//创建歌单 DONE
		//musicSheetService.createMusicSheet("歌单测试1", logBuild.toString(), "Su Tianyu", "");
//		musicSheetService.createMusicSheet("歌单测试1", logBuild.toString(), "宿天宇", "");
//		musicSheetService.createMusicSheet("歌单测试1", logBuild.toString(), "宿天宇", "");
		//删除歌单 DONE
		//musicSheetService.deleteMusicSheet(1);
		
		
		//测试添加歌曲
		//MusicService musicService=new MusicService();
	//System.out.println(musicService.insert("1-3", "/home/sky/音乐/CloudMusic/郭旭 - 不找了.mp3", "郭旭"));
//		System.out.println(musicService.insert("在他乡", "/home/sky/netEasyMusic/水木年华 - 在他乡.mp3", "sky"));
		
		//删除歌曲
		//System.out.println(musicService.delete(5));
		
		//查询歌曲BY ID/md5
		//System.out.println(musicService.getById(4).getName());
		//System.out.println(musicService.getByMd5Value("fb64324662e1cf1b6f4715a6b5b1aac3").getName());
		
		// 添加歌曲到歌单，msid=9；
		
		//musicSheetService.addMusicsToMusicSheet(9, musicService.getById(40));
		//musicSheetService.addMusicsToMusicSheet(9, musicService.getById(musicService.insert("远走高飞", "/home/sky/音乐/CloudMusic/金志文 - 远走高飞.mp3", "金志文")));
		// 从歌单移除歌曲
		//musicSheetService.removeMusicFromMusicSheet(cid);
		
		//获取歌单
//		MusicSheet musicSheet=musicSheetService.getMusicSheet(2);
//		System.out.println(musicSheet.getName());
//		List<MusicSheet> msls=musicSheetService.getMusicSheetsByCreatorId(logBuild.toString());
//		for (MusicSheet musicSheet2 : msls) {
//			System.out.println(musicSheet2.getId());
//		}
		//获取歌单歌曲
//		List<Music> tmpm=musicSheetService.getMusicByMusicSheetId(2);
//		System.out.println(tmpm.size());
//		for (Music m : tmpm) {
//			System.out.println(m.getName());
//		}
		
		//删除歌单
//		System.out.println(musicSheetService.deleteMusicSheet(3));
		
		//播放歌单全部本地歌曲
		musicSheetService.playAllSongs(22);
		
		//上传歌单
//		MusicSheet my= musicSheetService.getMusicSheet(2);
//		SokectService.uploadMusicSheet(my);
		
	}

}
