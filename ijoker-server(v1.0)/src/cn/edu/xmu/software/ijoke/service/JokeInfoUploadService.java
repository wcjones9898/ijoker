package cn.edu.xmu.software.ijoke.service;

import java.io.File;

public interface JokeInfoUploadService {
	
	public void jokeInfoUploadService (String title, String keyWord, 
			String userId,String fileExtension,String fileName);
	
	public String jokeInfoUploadServiceByServer (
			String title, String keyWord, 
			String userId,String fileExtension,String fileId);
	
	public boolean jokeInfoUploadServiceByServer(String title,String key,String userId,File file);
}
