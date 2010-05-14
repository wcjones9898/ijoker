package cn.edu.xmu.software.ijoke.entity;

/**
 * Catalog entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Catalog implements java.io.Serializable {

	// Fields

	private Integer id;
	private String catalogName;
	private String parentId;
	private String catalogId;
	private int catalogLevel;
    private String catalogPath;
	// Constructors

	/** default constructor */
	public Catalog() {
	}

	/** full constructor */
	public Catalog(String catalogName, String parentId, String catalogId,
			int catalogLevel) {
		this.catalogName = catalogName;
		this.parentId = parentId;
		this.catalogId = catalogId;
		this.catalogLevel = catalogLevel;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCatalogName() {
		return this.catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getCatalogId() {
		return this.catalogId;
	}

	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}

	public int getCatalogLevel() {
		return this.catalogLevel;
	}

	public void setCatalogLevel(int catalogLevel) {
		this.catalogLevel = catalogLevel;
	}

	public String getCatalogPath() {
		return catalogPath;
	}

	public void setCatalogPath(String catalogPath) {
		this.catalogPath = catalogPath;
	}

	
}