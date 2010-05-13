package cn.edu.xmu.software.ijoke.serviceimpl;

import java.util.Date;
import java.sql.Timestamp;

import org.junit.Test;

import cn.edu.xmu.software.ijoke.dao.JokeFileDAO;
import cn.edu.xmu.software.ijoke.entity.JokeFile;
import cn.edu.xmu.software.ijoke.service.UploadJokeFileService;
import cn.edu.xmu.software.ijoke.factory.ConfigFactory;
public class JokeFileServiceImpl implements UploadJokeFileService{

	private  JokeFileDAO jokeFileDAO = new JokeFileDAO();
	public String insertJokeFile(String fileExtension, String filePath, String fileId)
	{
		JokeFile jokeFile = new JokeFile();
		jokeFile.setFileExtension(fileExtension);
		jokeFile.setFilePath(ConfigFactory.getJokePath());
		jokeFile.setFileName(fileId);
		jokeFile.setFileId(fileId);
        jokeFileDAO.insertJokeFile(jokeFile);
        return "ok";
        
	}
	
	@Test
	public void testInsertJokeFile()
	{
		insertJokeFile(".mp3","/guodegang/","123aredsfa");
	}

}
