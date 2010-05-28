package cn.edu.xmu.software.ijoke.entity;

/**
 * Cartoon entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Cartoon implements java.io.Serializable {

	// Fields

	private Integer id;
	private String cartoonId;
	private String fileId;
	private String cartoonTitle;
	private String uploadTime;
	private String authorName;
	private String uploaderId;
    private Integer status;
	// Constructors

	/** default constructor */
	public Cartoon() {
	}

	/** minimal constructor */
	public Cartoon(String cartoonId) {
		this.cartoonId = cartoonId;
	}

	/** full constructor */
	public Cartoon(String cartoonId, String fileId, String cartoonTitle,
			String uploadTime, String authorName, String uploaderId) {
		this.cartoonId = cartoonId;
		this.fileId = fileId;
		this.cartoonTitle = cartoonTitle;
		this.uploadTime = uploadTime;
		this.authorName = authorName;
		this.uploaderId = uploaderId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCartoonId() {
		return this.cartoonId;
	}

	public void setCartoonId(String cartoonId) {
		this.cartoonId = cartoonId;
	}

	public String getFileId() {
		return this.fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getCartoonTitle() {
		return this.cartoonTitle;
	}

	public void setCartoonTitle(String cartoonTitle) {
		this.cartoonTitle = cartoonTitle;
	}

	public String getUploadTime() {
		return this.uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getAuthorName() {
		return this.authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getUploaderId() {
		return this.uploaderId;
	}

	public void setUploaderId(String uploaderId) {
		this.uploaderId = uploaderId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}