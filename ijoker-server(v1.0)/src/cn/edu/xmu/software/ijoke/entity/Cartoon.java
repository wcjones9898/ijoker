package cn.edu.xmu.software.ijoke.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Cartoon entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Cartoon implements java.io.Serializable {

	// Fields

	private Integer id;
	private String cartoonTitle;
	private String uploadTime;
	private String authorName;
	private String uploaderId;
	private Integer status;
	private Set cartoonFiles = new HashSet(0);

	// Constructors

	/** default constructor */
	public Cartoon() {
	}

	/** minimal constructor */
	public Cartoon(Integer status) {
		this.status = status;
	}

	/** full constructor */
	public Cartoon(String cartoonTitle, String uploadTime, String authorName,
			String uploaderId, Integer status, Set cartoonFiles) {
		this.cartoonTitle = cartoonTitle;
		this.uploadTime = uploadTime;
		this.authorName = authorName;
		this.uploaderId = uploaderId;
		this.status = status;
		this.cartoonFiles = cartoonFiles;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Set getCartoonFiles() {
		return this.cartoonFiles;
	}

	public void setCartoonFiles(Set cartoonFiles) {
		this.cartoonFiles = cartoonFiles;
	}

}