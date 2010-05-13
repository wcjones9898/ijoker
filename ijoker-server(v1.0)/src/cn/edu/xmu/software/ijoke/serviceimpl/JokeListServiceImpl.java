package cn.edu.xmu.software.ijoke.serviceimpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.xmu.software.ijoke.dao.ClassAndJokeFileDAO;
import cn.edu.xmu.software.ijoke.dao.ClassItemDAO;
import cn.edu.xmu.software.ijoke.dao.JokeDAO;
import cn.edu.xmu.software.ijoke.dao.JokeFileDAO;
import cn.edu.xmu.software.ijoke.entity.ClassAndJokeFile;
import cn.edu.xmu.software.ijoke.entity.ClassItem;
import cn.edu.xmu.software.ijoke.entity.JokeFile;
import cn.edu.xmu.software.ijoke.service.JokeListService;
import cn.edu.xmu.software.ijoke.view.Joke;
public class JokeListServiceImpl  implements JokeListService{


	//private JokeFileDAO jokeFileDAO = new JokeFileDAO();
	private ClassAndJokeFileDAO classAndFileDAO = new ClassAndJokeFileDAO();
    private JokeFileDAO jokeFileDAO = new JokeFileDAO();
    private JokeDAO jokeDAO = new JokeDAO();
    
    // WebService方法 用于向客户端传递列表 参数为主题的Id，开始列表的页数及页的长度
	public ArrayList jokeListService(String classId,int begin, int limit)
	{
	
		 //List<ClassAndJokeFile> clAndFlList = (List<ClassAndJokeFile>) classAndFileDAO.findClassItemDAOByClassId(classId);
		System.out.println("WebService方法jokeListService用于向客户端传递列表 参数为主题的Id，开始列表的页数及页的长度");
		List<ClassAndJokeFile> clAndFlList = (List<ClassAndJokeFile>) classAndFileDAO.
		findClassAndJokeByLimit(classId, (begin-1)*limit, limit);
		ArrayList<Joke> jokeList = new ArrayList();
		 for(int i=0; i<clAndFlList.size(); i++)
		 {
			 ClassAndJokeFile classAndJokeFileTemp = clAndFlList.get(i);
			 JokeFile jokeFile = jokeFileDAO.findJokeFileByJokeId(classAndJokeFileTemp.getFileId());
			 cn.edu.xmu.software.ijoke.entity.Joke  serverJoke = jokeDAO.findByJokeId(classAndJokeFileTemp.getJokeId());
			 Joke joke = new Joke();
			 joke.setLocation(jokeFile.getFilePath()+jokeFile.getFileName()+jokeFile.getFileExtension());
			 joke.setId(Integer.valueOf(serverJoke.getJokeId()));
			 joke.setTitle(serverJoke.getTitle());
             joke.setAuthor(serverJoke.getAuthorName());
			 joke.setUploadTime(serverJoke.getUploadTime());
			 joke.setLike(serverJoke.getLikeNum());
			// joke.setDislike(serverJoke.getDislikeNum());
			 jokeList.add(joke);		 
		 }
		 return jokeList;
	}


}
