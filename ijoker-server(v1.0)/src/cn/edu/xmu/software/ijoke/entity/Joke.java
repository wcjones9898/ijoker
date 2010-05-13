package cn.edu.xmu.software.ijoke.entity;

/**
 * Joke entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Joke implements java.io.Serializable {

	// Fields

	private Integer id;
	private String authorName;
	private String fileId;
	private String title;
	private String jokeId;
	private String uploaderId;
    private String uploadTime;
    private int likeNum;
    private String status;  //用来标注Joke是否经过审核
 
	// Constructors

	/** default constructor */
	public Joke() {
	}

	/** minimal constructor */
	public Joke(String title) {
		this.title = title;
	}



	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getJokeId() {
		return this.jokeId;
	}

	public void setJokeId(String jokeId) {
		this.jokeId = jokeId;
	}

	

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getUploaderId() {
		return uploaderId;
	}

	public void setUploaderId(String uploaderId) {
		this.uploaderId = uploaderId;
	}



	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

	public int getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(int likeNum) {
		this.likeNum = likeNum;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



}