package backstageTest;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import team.javaMusicPlayer.model.MusicSheet;
import team.javaMusicPlayer.service.MusicSheetService;
import team.javaMusicPlayer.service.SokectService;
import team.javaMusicPlayer.sokect.DownloadResource;

public class SokectTest {
	public static void main(String[] args) {
		SokectService sokectService = new SokectService();

		// 获取歌单
		List<MusicSheet> online = SokectService.queryMusicSheets();
		 for (MusicSheet ms : online) {
		 System.out.println(ms.getPicture());
		 }

		// 下载歌曲
		// SokectService.downloadMusic(online.get(0).getMusicItems());

		// 下载图片
		 //SokectService.downloadPicture(online.get(3).getPicture());
//		DownloadResource.downloadPicture("2a31e7b1bc9b4b4d8f920c08540c4a00");
		
		
		// 播放网络歌单
//		MusicSheetService musicSheetService = new MusicSheetService();
//		musicSheetService.playAllSongs(ondline.get(0).getMusicItems());
		
		
		
	}

}
