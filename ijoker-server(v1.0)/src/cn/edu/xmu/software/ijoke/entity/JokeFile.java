package cn.edu.xmu.software.ijoke.entity;

import java.io.Serializable;

public class JokeFile implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String filePath;
    private String fileName;
    private String fileExtension;
    private String fileId;
    private Integer Id;
    private Integer fileLength;
    private Integer fileTime;
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getJokeFile() {
		return filePath;
	}

	public void setJokeFile(String filePath) {
		filePath = filePath;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public Integer getFileLength() {
		return fileLength;
	}

	public void setFileLength(Integer fileLength) {
		this.fileLength = fileLength;
	}

	public Integer getFileTime() {
		return fileTime;
	}

	public void setFileTime(Integer fileTime) {
		this.fileTime = fileTime;
	}
	

}
