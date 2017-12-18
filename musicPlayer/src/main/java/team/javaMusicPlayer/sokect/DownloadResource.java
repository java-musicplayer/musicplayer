package team.javaMusicPlayer.sokect;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import team.javaMusicPlayer.model.MusicSheet;

public class DownloadResource {
	private static ResourceBundle rb;
	static {
		rb=ResourceBundle.getBundle("team.javaMusicPlayer.sokect.server");
	}

	/*
	 * 功能:下载歌曲
	 */
	public static String downloadMusic(String md5Value) {
		return sendGet(rb.getString("server.downloadMusic"), "md5="+md5Value);
	}

	/*
	 * 功能:下载图片
	 */
	public static String downloadPicture(String uuid) {
		return sendGet(rb.getString("server.downloadPicture"), "uuid="+uuid);
	}

	/**
	 * 功能:获取所有歌单
	 * 
	 */
	public static List<MusicSheet> queryMusic() {
		String JOSN=sendGet(rb.getString("server.queryMusicSheets"), "type=all");
		ArrayList<MusicSheet> musicSheets=new ArrayList<MusicSheet>();
		try {
			musicSheets=JSON.parseObject(JOSN,new TypeReference<ArrayList<MusicSheet>>() {});
		} catch (com.alibaba.fastjson.JSONException e) {
			e.printStackTrace();
		}
		return musicSheets;
	}

	/**
	 * 发送GET请求，并且保存图片歌曲资源
	 * @return 请求 JSON,返回JSON
	 * @return 请求下载歌曲或者图片， 返回保存的绝对路径
	 * @return 下载发生异常返回null
	 */

	private static String sendGet(String url, String param) {
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			String fileName = "";
			for (String key : map.keySet()) {
				if("Content-Disposition".equals(key)) {
					for (String name : map.get(key)) {
						if(name.contains("filename")) {
							fileName = name.split("=")[1];
							break;
						}
					}
				}
			}

			// 中文解码
			fileName = URLDecoder.decode(fileName, "utf-8");
			System.out.println("请求地址:"+connection.getURL()+"\t"+"当前下载:"+fileName);
			InputStream inputStream = connection.getInputStream();
			byte[] bytes = readInputStream(inputStream);

			if ("type=all".equals(param)) {
				String jsonOrign=new String(bytes);
				int head=jsonOrign.indexOf("musicSheetList")+"musicSheetList".length()+2;
				int tail=jsonOrign.indexOf("message")-2;
				return jsonOrign.substring(head, tail);
			}
			else {
				File file = null;
				FileOutputStream outFile = null;
				try {
					// 写出文件，MP3
					if (fileName.contains("mp3")) {
						file = new File("data/musics/" + fileName);
					} else {
						// 写出图片
//						file = new File("data/pictures/" + param.split("=")[1]);
						file = new File("data/pictures/" + fileName);
//						DataInputStream dataInputStream= new DataInputStream(realUrl.openStream());
//						bytes=readInputStream(dataInputStream);
					}
					outFile=new FileOutputStream(file);
					outFile.write(bytes);

				} catch (Exception e) {
					//e.printStackTrace();
					return null;
				} finally {
					if (outFile != null) {
						outFile.close();
					}
				}
				return file.getAbsolutePath();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		// 创建一个Buffer字符串
		byte[] buffer = new byte[1024];
		// 每次读取的字符串长度，如果为-1，代表全部读取完毕
		int len = 0;
		// 使用一个输入流从buffer里把数据读取出来
		while ((len = inStream.read(buffer)) != -1) {
			// 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
			outStream.write(buffer, 0, len);
		}
		// 关闭输入流
		inStream.close();
		// 把outStream里的数据写入内存
		return outStream.toByteArray();
	}

}
