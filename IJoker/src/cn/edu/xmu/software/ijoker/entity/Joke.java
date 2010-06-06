package cn.edu.xmu.software.ijoker.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author hipisy
 * 
 */

public class Joke implements Parcelable {

	/**
	 * 
	 */

	private String Id;
	private String title;
	private String author;
	private String uploadTime;
	private String location;
	private String keyword;
	private int like;
	private long fileLength;
	private long fileTime;

	/**
	 * * @return
	 */

	public String getTitle() {
		return title;
	}

	public long getFileLength() {
		return fileLength;
	}

	public void setFileLength(long fileLength) {
		this.fileLength = fileLength;
	}

	public long getFileTime() {
		return fileTime;
	}

	public void setFileTime(long fileTime) {
		this.fileTime = fileTime;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	/**
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getUploadTime() {
		return uploadTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	public static final Parcelable.Creator<Joke> CREATOR = new Parcelable.Creator<Joke>() {
		public Joke createFromParcel(Parcel in) {
			return new Joke(in);
		}

		public Joke[] newArray(int size) {
			return new Joke[size];
		}
	};

	public Joke() {
	}

	private Joke(Parcel in) {
		readFromParcel(in);
	}

	public void readFromParcel(Parcel in) {
		this.Id = in.readString();
		this.title = in.readString();
		this.author = in.readString();
		this.uploadTime = in.readString();
		this.location = in.readString();
		this.like = in.readInt();
		this.keyword = in.readString();
		this.fileLength = in.readLong();
		this.fileTime = in.readLong();
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(Id);
		dest.writeString(title);
		dest.writeString(author);
		dest.writeString(uploadTime);
		dest.writeString(location);
		dest.writeInt(like);
		dest.writeString(keyword);
		dest.writeLong(fileLength);
		dest.writeLong(fileTime);
	}

	public void like() {
		like++;
	}
}
