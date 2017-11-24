package team.javaMusicPlayer.model;

public class Music {

	private int id;
	private String name;
	private String singer;
	private String md5value;
	private String musicUrl;

	public Music() {
	}

	public Music(String md5value, String name,String musicUrl) {
		this.md5value = md5value;
		this.name = name;
		this.musicUrl=musicUrl;

	}

	public Music(String md5value, String name, String musicUrl,String singer) {
		this.md5value = md5value;
		this.name = name;
		this.singer = singer;
		this.musicUrl=musicUrl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public String getMd5value() {
		return md5value;
	}

	public void setMd5value(String md5value) {
		this.md5value = md5value;
	}

	public String getMusicUrl() {
		return musicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}
	
	
	
	@Override
	public boolean equals(Object anotherobj) {
		if(anotherobj==this)
			return true;
		if(anotherobj instanceof Music) {
			Music anotherMusic=(Music)anotherobj;
			if(anotherMusic.getId()== this.getId() 
					&& anotherMusic.getName().equals(this.getName())
					&& anotherMusic.getSinger().equals(this.getSinger())
					&& anotherMusic.getMd5value().equals(this.getMd5value())
					&& anotherMusic.getMusicUrl().equals(this.getMusicUrl()))
				return true;
			
		}
		return false;
	}

}
