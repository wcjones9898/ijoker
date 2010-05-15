package cn.edu.xmu.software.ijoke.entity;

/**
 * ClassAndJokeFile entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ClassAndJokeFile implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String classId;

    private String jokeId;
	// Constructors

	/** default constructor */

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}



	public String getJokeId() {
		return jokeId;
	}

	public void setJokeId(String jokeId) {
		this.jokeId = jokeId;
	}

	/** full constructor */


}