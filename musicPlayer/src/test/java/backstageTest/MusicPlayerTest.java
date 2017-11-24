package backstageTest;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javazoom.jl.player.Player;
import team.javaMusicPlayer.model.Music;

public class MusicPlayerTest {

	public static void main(String[] args) {
//		Player sysPlayer = null;		//调用系统播放器，只能播放MP3
//		String url="/home/sky/netEasyMusic/李健-贝加尔湖畔 (Live).mp3";
//		try {
//			BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(url));
//			sysPlayer = new Player(buffer);
//			System.out.println(sysPlayer.isComplete());
//			sysPlayer.play();
//			sysPlayer.close();
//			System.out.println(sysPlayer.getPosition());
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//		
//		
////		try {
////			Thread.sleep(5000);
////		} catch (InterruptedException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		
//		try {
//		
//			Thread.sleep(2500);
//			System.out.println(sysPlayer.getPosition());
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		
		//Content-Type 		Content-Type--->[application/json;charset=utf-8]
		//Content-Length 9939
		
//		byte[] bytes=sendGet("http://service.uspacex.com/music.server/queryMusicSheets", 
//				"type=all");
//		System.out.println(new String(bytes));
//		
		//music
//		byte[] bytes=sendGet("http://service.uspacex.com/music.server/downloadMusic", 
//				"md5=df142e440c2162c5d5c51077e387dc80");
//		File pic=new File("data/picture/11.mp3");
//		try {
//			FileOutputStream fileOutputStream=new FileOutputStream(pic);
//			fileOutputStream.write(bytes);
//			fileOutputStream.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		
		
		//pic
//		byte[] bytes=sendGet("http://service.uspacex.com/music.server/downloadPicture", 
//				"uuid=235edc3a68144beb8e8980e59941c470");
//		File pic=new File("data/picture/kk.jpg");
//		try {
//			FileOutputStream fileOutputStream=new FileOutputStream(pic);
//			fileOutputStream.write(bytes);
//			fileOutputStream.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
	

		
		
		//
//		try {
//			System.out.println("fileName:"+URLDecoder.decode("%E8%B0%81%E4%BC%B4%E6%88%91%E9%97%AF%E8%8D%A1.mp3"
//					,"utf-8"));
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		

};
		
public static void testMulti(int...param) {
	for (int i : param) {
		System.out.println(i);
	}
}

	 public static byte[] sendGet(String url, String param) {
	        try {
	            String urlNameString = url + "?" + param;
	            URL realUrl = new URL(urlNameString);
	            // 打开和URL之间的连接
	            URLConnection connection = realUrl.openConnection();
	            // 设置通用的请求属性
	            connection.setRequestProperty("accept", "*/*");
	            connection.setRequestProperty("connection", "Keep-Alive");
	            connection.setRequestProperty("user-agent",
	                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	            // 建立实际的连接
	            connection.connect();
	            // 获取所有响应头字段
	            Map<String, List<String>> map = connection.getHeaderFields();
	            // 遍历所有的响应头字段
	            
	            //Content-Disposition--->[attachment; filename=%E8%B0%81%E4%BC%B4%E6%88%91%E9%97%AF%E8%8D%A1.mp3]
	            for (String key : map.keySet()) {
	                System.out.println(key + "--->" + map.get(key));
	            }
	              InputStream inputStream= connection.getInputStream();
	              return readInputStream(inputStream);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }

	 public static byte[] readInputStream(InputStream inStream) throws Exception{  
	        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
	        //创建一个Buffer字符串  
	        byte[] buffer = new byte[1024];  
	        //每次读取的字符串长度，如果为-1，代表全部读取完毕  
	        int len = 0;  
	        //使用一个输入流从buffer里把数据读取出来  
	        while( (len=inStream.read(buffer)) != -1 ){  
	            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度  
	            outStream.write(buffer, 0, len);  
	        }  
	        //关闭输入流  
	        inStream.close();  
	        //把outStream里的数据写入内存  
	        return outStream.toByteArray();  
	    }  
	 
	 public static void testYy(String aa) {
		 aa="666";
		
	}
}
