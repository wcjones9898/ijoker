package cn.edu.xmu.software.ijoke.serviceimpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import cn.edu.xmu.software.ijoke.dao.ClassAndJokeFileDAO;
import cn.edu.xmu.software.ijoke.dao.JokeDAO;
import cn.edu.xmu.software.ijoke.dao.UserDAO;
import cn.edu.xmu.software.ijoke.entity.ClassAndJokeFile;
import cn.edu.xmu.software.ijoke.entity.Joke;
import cn.edu.xmu.software.ijoke.entity.User;
import cn.edu.xmu.software.ijoke.factory.ConfigFactory;
import cn.edu.xmu.software.ijoke.service.JokeInfoUploadService;
import cn.edu.xmu.software.ijoke.service.UploadJokeFileService;
import cn.edu.xmu.software.ijoke.utils.Consts;

public class JokeInfoUploadServiceImpl implements JokeInfoUploadService{

	private JokeDAO jokeDAO = new JokeDAO();
	private UserDAO userDAO = new UserDAO();
   
	private ClassAndJokeFileDAO classAndJokeFileDAO = new ClassAndJokeFileDAO();
	private UploadJokeFileService jokeFileService = new JokeFileServiceImpl();
	
	public void jokeInfoUploadService (String title, String keyWord, 
			String userId,String fileExtension,String fileId)
	{
		String filePath = ConfigFactory.getJokePath();
		jokeFileService.insertJokeFile(fileExtension, filePath,fileId);
		User user = userDAO.findByUserId(userId);
		Joke joke = new Joke();
		joke.setAuthorName(user.getNickName());
		joke.setJokeId(fileId);
		joke.setFileId(fileId);
		joke.setTitle(title);
		joke.setUploaderId(userId);
		joke.setDescription(keyWord);
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm");
		joke.setUploadTime(formatter.format(new Date()));
		joke.setStatus("0");
		jokeDAO.insertJoke(joke);
		ClassAndJokeFile classAndJokeFile = new ClassAndJokeFile();
		classAndJokeFile.setClassId("0");
		classAndJokeFile.setJokeId(joke.getJokeId());
		classAndJokeFileDAO.insertClassAndJokeFile(classAndJokeFile);
		
	}
	public String jokeInfoUploadServiceByServer (
			String title, String keyWord, 
			String userId,String fileExtension,String fileId)
	{
		String filePath = ConfigFactory.getJokePath();
		jokeFileService.insertJokeFile(fileExtension, filePath,fileId);
		User user = userDAO.findByUserId(userId);
		
		Joke joke = new Joke();
		joke.setAuthorName(user.getNickName());
		joke.setJokeId(fileId);
		joke.setFileId(fileId);
		joke.setTitle(title);
		joke.setUploaderId(userId);
		joke.setDescription(keyWord);
	    
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm");
		joke.setUploadTime(formatter.format(new Date()));
		joke.setStatus("0");
		jokeDAO.insertJoke(joke);
		ClassAndJokeFile classAndJokeFile = new ClassAndJokeFile();
		classAndJokeFile.setJokeId(joke.getJokeId());
		classAndJokeFile.setClassId("0");
		classAndJokeFileDAO.insertClassAndJokeFile(classAndJokeFile);
		return fileId;
		
	}
	public boolean copyTo(String oldFilePath,String fileName)
	{
		File oldFile = new File(oldFilePath);
		
		File newFile = new File(ConfigFactory.getJokePath()+
				fileName+
				oldFile.getName().substring(oldFile.getName().indexOf(".")));
		System.out.println(ConfigFactory.getJokePath()+
				fileName+
				oldFile.getName().substring(oldFile.getName().indexOf(".")));
		
		try {
			FileInputStream fis = new FileInputStream(oldFile);
			FileOutputStream fos = new FileOutputStream(newFile);
			try {
				byte[] buffer = new byte[Consts.BUFFER_SIZE];
				while(fis.read(buffer)!=-1)
				fos.write(buffer);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@Test
	public void testJokeInfoUploadService()
	{
		
		jokeInfoUploadService("test", ""+1, 
				"1",".mp3",""+1);
	}
	public boolean jokeInfoUploadServiceByServer(String title, String key,
			String userId, File file) {
		// TODO Auto-generated method stub
		return true;
	}
	
}
