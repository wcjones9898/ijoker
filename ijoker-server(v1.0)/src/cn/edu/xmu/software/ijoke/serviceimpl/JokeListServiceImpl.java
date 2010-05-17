package cn.edu.xmu.software.ijoke.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.edu.xmu.software.ijoke.dao.ClassAndJokeFileDAO;
import cn.edu.xmu.software.ijoke.dao.JokeDAO;
import cn.edu.xmu.software.ijoke.dao.JokeFileDAO;
import cn.edu.xmu.software.ijoke.entity.ClassAndJokeFile;
import cn.edu.xmu.software.ijoke.entity.JokeFile;
import cn.edu.xmu.software.ijoke.service.JokeListService;
import cn.edu.xmu.software.ijoke.view.Joke;
public class JokeListServiceImpl  implements JokeListService{


	//private JokeFileDAO jokeFileDAO = new JokeFileDAO();
	private ClassAndJokeFileDAO classAndFileDAO = new ClassAndJokeFileDAO();
    private JokeFileDAO jokeFileDAO = new JokeFileDAO();
    private JokeDAO jokeDAO = new JokeDAO();
    
    // WebService方法 用于向客户端传递列表 参数为主题的Id，开始列表的页数及页的长度
	public List jokeListService(String catalogId,int begin, int limit)
	{
	
		 //List<ClassAndJokeFile> clAndFlList = (List<ClassAndJokeFile>) classAndFileDAO.findClassItemDAOByClassId(classId);
		System.out.println("WebService方法jokeListService用于向客户端传递列表 参数为主题的Id，开始列表的页数及页的长度");
		System.out.println("catalogId = "+catalogId);
		List<ClassAndJokeFile> clAndFlList = (List<ClassAndJokeFile>) classAndFileDAO.
		findClassAndJokeByLimit(catalogId, (begin-1)*limit, limit);
		ArrayList<cn.edu.xmu.software.ijoke.entity.Joke> jokeList = new ArrayList();
		 for(int i=0; i<clAndFlList.size(); i++)
		 {
			 ClassAndJokeFile classAndJokeFileTemp = clAndFlList.get(i);
			 cn.edu.xmu.software.ijoke.entity.Joke  serverJoke = jokeDAO.findByJokeId(classAndJokeFileTemp.getJokeId());
             if(serverJoke!=null)
			 jokeList.add(serverJoke);
             
		 }
		 return entityToView(jokeList);
	}
	public List<Joke> entityToView(
			List<cn.edu.xmu.software.ijoke.entity.Joke> jokesEntity) {

		List<Joke> jokesView = new ArrayList<Joke>();
		for (cn.edu.xmu.software.ijoke.entity.Joke j : jokesEntity) {
			Joke jokeView = new Joke();

			jokeView.setId(""+j.getJokeId());
			jokeView.setTitle(""+j.getTitle());
			jokeView.setAuthor(""+j.getAuthorName());
			jokeView.setUploadTime(""+j.getUploadTime());
			jokeView.setLike(j.getLikeNum());

		    jokeView.setKeyWord(""+j.getDescription());
			JokeFile jokeFile = jokeFileDAO.findJokeFileByFileId(j.getFileId());
			if(jokeFile!=null)
			{
			String location = jokeFile.getFilePath()+jokeFile.getFileName()+jokeFile.getFileExtension();
            jokeView.setFileLength(jokeFile.getFileLength());
            jokeView.setFileTime(jokeFile.getFileTime());
			jokeView.setLocation(location);
			jokeView.setKeyWord(j.getDescription());
			jokesView.add(jokeView);
			}

		}
		return jokesView;

	}
	@Test
	public void testJokeListService()
	{
		List<Joke> jokeList = jokeListService("1",1,5);
		for(int i=0; i<jokeList.size(); i++)
			
			System.out.println(jokeList.get(i).getAuthor()+jokeList.get(i).getTitle());
	}

}
