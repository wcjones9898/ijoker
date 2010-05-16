package cn.edu.xmu.software.ijoke.service;

public interface JokeInfoUploadService {
	
	public void jokeInfoUploadService (String title, String keyWord, 
			String userId,String fileExtension,String fileName);
	public String jokeInfoUploadServiceByServer (String title, String keyWord, 
			String userId,String fileExtension,String fileName);
	public boolean copyTo(String oldFilePath,String fileName);
}
