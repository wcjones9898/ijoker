package cn.edu.xmu.software.ijoke.entity;

public class CartoonFile {

	private Integer Id;
	private String fileId;
	private Integer fileLength;
	private String fileExtension;
	private String fileName;
	private String filePath;
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
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
	public String getFileExtension() {
		return fileExtension;
	}
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
	
}
