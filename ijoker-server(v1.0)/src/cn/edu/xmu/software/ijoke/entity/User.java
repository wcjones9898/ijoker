package cn.edu.xmu.software.ijoke.entity;


import java.io.Serializable;
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String userId;
	private String userName;
	private String nickName;
	private String passWord;
	private int status;

	
	//private SqlMapUserDao userDao = new SqlMapUserDao();
	public User(Integer id, String userName, String passWord,
			String userId, String nickName) {
		this.id = id;
		this.userName = userName;
		this.passWord = passWord;
		this.userId = userId;
		this.nickName = nickName;
	}

	public User(){}
	public User(String userName,String passWord)
	{
		this.userName = userName;
		this.passWord = passWord;
		
		
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
