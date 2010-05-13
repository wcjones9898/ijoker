package cn.edu.xmu.software.ijoke.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * ClassItem entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ClassItem implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String className;
	private Integer classLevel;
	private String classId;
    private int jokeNum;

	// Constructors

	/** default constructor */
	public ClassItem() {
	}

	/** minimal constructor */
	public ClassItem(String className, Integer classLevel, String classId) {
		this.className = className;
		this.classLevel = classLevel;
		this.classId = classId;
	}

	/** full constructor */
	public ClassItem(String className, Integer classLevel, String classId,
			Set classAndJokeFiles) {
		this.className = className;
		this.classLevel = classLevel;
		this.classId = classId;

	}

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