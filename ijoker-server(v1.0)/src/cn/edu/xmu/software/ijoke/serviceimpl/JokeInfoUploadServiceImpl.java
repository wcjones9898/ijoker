package cn.edu.xmu.software.ijoke.serviceimpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
		joke.setUploadTime((new Date()).toString());
		joke.setStatus("0");
		jokeDAO.insertJoke(joke);
		ClassAndJokeFile classAndJokeFile = new ClassAndJokeFile();
		classAndJokeFile.setJokeId(joke.getJokeId());
		classAndJokeFileDAO.insertClassAndJokeFile(classAndJokeFile);
		
	}
	public String jokeInfoUploadServiceByServer (String title, String keyWord, 
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
		joke.setUploadTime((new Date()).toString());
		joke.setStatus("0");
		jokeDAO.insertJoke(joke);
		ClassAndJokeFile classAndJokeFile = new ClassAndJokeFile();
		classAndJokeFile.setJokeId(joke.getJokeId());
		classAndJokeFileDAO.insertClassAndJokeFile(classAndJokeFile);
		return fileId;
		
	}
	public String copyTo(String oldFilePath,String fileName)
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
				return Consts.COPY_FAIL;
			}
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return Consts.COPY_FAIL;
			}
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return Consts.COPY_FAIL;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			return Consts.COPY_FAIL;
		}
		return Consts.COPY_SUCCESS;
	}
	@Test
	public void testJokeInfoUploadService()
	{
		
		copyTo("D:/1.jpg",jokeInfoUploadServiceByServer("lsjlkj", "adsfadf", 
				"1","ladklfjasd","adfasdkjhf"));
	}
	
}
