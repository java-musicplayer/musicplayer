package team.javaMusicPlayer.model;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class MusicSheet {

	private int id;
	private String uuid;
	private String name;
	private String creatorId;
	private String creator;
	private String dateCreated;
	private String picture;
	// <MD5, Music file name>
	private Map<String, String> musicItems;



	public MusicSheet() {
		this.uuid=UUID.randomUUID().toString().replaceAll("-", "");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.dateCreated = formatter.format(new Date());
		//设置默认图片
		File pic=new File("resources/images/defaultFaceImg.jpeg");
		this.picture=pic.getAbsolutePath();
	}

	public MusicSheet(String name) {
		this();
		this.name = name;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUuid() {
		return uuid;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "MusicSheet [id=" + id + ", uuid=" + uuid + ", name=" + name + ", creatorId=" + creatorId + ", creator="
				+ creator + ", dateCreated=" + dateCreated + ", picture=" + picture + ", musicItems=" + musicItems
				+ "]";
	}

	public Map<String, String> getMusicItems() {
		return musicItems;
	}

	public void setMusicItems(Map<String, String> musicItems) {
		this.musicItems = musicItems;
	}
	/**
	 * 通过重写 equals 和 hashCode ，使用 hashSet 实现去重复
	 */
	//实现去重复，通过比较 creatorId
	@Override
	public boolean equals(Object obj) {
		MusicSheet anotherMusicSheet=(MusicSheet)obj;
		return this.creatorId.equals(anotherMusicSheet.creatorId);
	}
	
	@Override
	public int hashCode() {
	String in=creatorId;
		return in.hashCode();
	}

}