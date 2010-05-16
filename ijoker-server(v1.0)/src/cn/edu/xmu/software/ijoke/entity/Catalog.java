package cn.edu.xmu.software.ijoke.entity;

import java.util.Set;

public class Catalog {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String catalogName;
	private Integer catalogLevel;
	private String catalogId;
    private int jokeNum;

	// Constructors


	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public Integer getCatalogLevel() {
		return catalogLevel;
	}

	public void setCatalogLevel(Integer catalogLevel) {
		this.catalogLevel = catalogLevel;
	}

	public String getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}

	public int getJokeNum() {
		return jokeNum;
	}

	public void setJokeNum(int jokeNum) {
		this.jokeNum = jokeNum;
	}

}
