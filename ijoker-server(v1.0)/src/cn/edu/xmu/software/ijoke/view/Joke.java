package cn.edu.xmu.software.ijoke.view;

import java.io.Serializable;



/**
 * @author hipisy
 * 
 */

public class Joke implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	private String Id;
	private String title;
	private String author;
	private String uploadTime;
	private String location;
	private int like;
	//private int dislike;

	/**
	 * * @return
	 */


	public String getTitle() {
		return title;
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



	
	public Joke() {
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}






}
