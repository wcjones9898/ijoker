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
	public boolean insertJokeFile(String fileExtension, String filePath, String fileId)
	{
		JokeFile jokeFile = new JokeFile();
		jokeFile.setFileExtension(fileExtension);
		String filePathTemp = null;
		try{
			filePathTemp=ConfigFactory.getJokePath()+filePath;
		}catch(Exception e)
		{
			System.out.println(" 无法获得系统路径");
		}
		jokeFile.setFilePath(filePath);
		jokeFile.setFileName(fileId);
		jokeFile.setFileId(fileId);
        jokeFileDAO.insertJokeFile(jokeFile);
        return true;
        
	}
	
	@Test
	public void testInsertJokeFile()
	{
		insertJokeFile(".mp3","/guodegang/","123aredsfa");
	}

	public boolean insertJokeFile(JokeFile jokeFile) {
		// TODO Auto-generated method stub
		if(jokeFile == null)
			return false;
		jokeFileDAO.insertJokeFile(jokeFile);
		return true;
	}

}
