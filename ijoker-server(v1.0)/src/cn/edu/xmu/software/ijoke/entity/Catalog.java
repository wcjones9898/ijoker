package cn.edu.xmu.software.ijoke.entity;

import java.util.Set;

public class Catalog {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String className;
	private Integer classLevel;
	private String classId;
    private int jokeNum;

	// Constructors


	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Integer getClassLevel() {
		return this.classLevel;
	}

	public void setClassLevel(Integer classLevel) {
		this.classLevel = classLevel;
	}

	public String getClassId() {
		return this.classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public int getJokeNum() {
		return jokeNum;
	}

	public void setJokeNum(int jokeNum) {
		this.jokeNum = jokeNum;
	}

}
