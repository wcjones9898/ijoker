package cn.edu.xmu.software.ijoke.service;

import java.util.List;

import cn.edu.xmu.software.ijoke.entity.User;

public interface UserService {

	public List<User> getUserWithOutVerify(int begin, int pageSize);
	public List<User> getUserVerified(int begin, int pageSize);
	public boolean verify(String userId, int status);
}
