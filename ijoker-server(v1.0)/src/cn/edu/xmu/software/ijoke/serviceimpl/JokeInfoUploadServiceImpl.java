package cn.edu.xmu.software.ijoke.serviceimpl;

import java.util.Date;

import org.junit.Test;

import cn.edu.xmu.software.ijoke.dao.ClassAndJokeFileDAO;
import cn.edu.xmu.software.ijoke.dao.JokeDAO;
import cn.edu.xmu.software.ijoke.dao.JokeFileDAO;
import cn.edu.xmu.software.ijoke.dao.UserDAO;
import cn.edu.xmu.software.ijoke.entity.ClassAndJokeFile;
import cn.edu.xmu.software.ijoke.entity.Joke;
import cn.edu.xmu.software.ijoke.entity.User;
import cn.edu.xmu.software.ijoke.service.UploadJokeFileService;
import cn.edu.xmu.software.ijoke.service.JokeInfoUploadService;

public class JokeInfoUploadServiceImpl implements JokeInfoUploadService{

	private JokeDAO jokeDAO = new JokeDAO();
	private UserDAO userDAO = new UserDAO();

	private ClassAndJokeFileDAO classAndJokeFileDAO = new ClassAndJokeFileDAO();
	private UploadJokeFileService jokeFileService = new JokeFileServiceImpl();
	public void jokeInfoUploadService (String title, String keyWord, 
			String userId,String fileExtension,String fileId)
	{
		String filePath = "/guodegang/";
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
		classAndJokeFile.setFileId(fileId);
		classAndJokeFile.setJokeId(joke.getJokeId());
		classAndJokeFileDAO.insertClassAndJokeFile(classAndJokeFile);
		
	}
	
	@Test
	public void testJokeInfoUploadService()
	{
		jokeInfoUploadService("lsjlkj", "adsfadf", 
				"1","ladklfjasd","adfasdkjhf");
	}
}
