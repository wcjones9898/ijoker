package cn.edu.xmu.software.ijoke.service;

import cn.edu.xmu.software.ijoke.entity.JokeFile;

public interface UploadJokeFileService {

	public boolean insertJokeFile(String fileExtension, String filePath, String fileId);
	public boolean insertJokeFile(JokeFile jokeFile);
}
