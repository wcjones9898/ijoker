package cn.edu.xmu.software.ijoke.service;

public interface UploadJokeFileService {

	public boolean insertJokeFile(String fileExtension, String filePath, String fileId);
}
