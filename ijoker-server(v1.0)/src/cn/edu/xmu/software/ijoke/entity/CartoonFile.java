package cn.edu.xmu.software.ijoke.entity;

/**
 * CartoonFile entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class CartoonFile implements java.io.Serializable {

	// Fields

	private Integer id;
	private Cartoon cartoon;
	private String fileId;
	private String fileName;
	private String fileExtension;
	private String filePath;
	private Integer fileLength;

	// Constructors

	/** default constructor */
	public CartoonFile() {
	}

	/** minimal constructor */
	public CartoonFile(String fileId) {
		this.fileId = fileId;
	}

	/** full constructor */
	public CartoonFile(Cartoon cartoon, String fileId, String fileName,
			String fileExtension, String filePath, Integer fileLength) {
		this.cartoon = cartoon;
		this.fileId = fileId;
		this.fileName = fileName;
		this.fileExtension = fileExtension;
		this.filePath = filePath;
		this.fileLength = fileLength;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cartoon getCartoon() {
		return this.cartoon;
	}

	public void setCartoon(Cartoon cartoon) {
		this.cartoon = cartoon;
	}

	public String getFileId() {
		return this.fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileExtension() {
		return this.fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Integer getFileLength() {
		return this.fileLength;
	}

	public void setFileLength(Integer fileLength) {
		this.fileLength = fileLength;
	}

}