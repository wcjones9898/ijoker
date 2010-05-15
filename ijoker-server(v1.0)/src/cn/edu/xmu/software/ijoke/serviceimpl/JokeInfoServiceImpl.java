package cn.edu.xmu.software.ijoke.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.edu.xmu.software.ijoke.dao.ClassAndJokeFileDAO;
import cn.edu.xmu.software.ijoke.dao.JokeDAO;
import cn.edu.xmu.software.ijoke.dao.JokeFileDAO;
import cn.edu.xmu.software.ijoke.entity.ClassAndJokeFile;
import cn.edu.xmu.software.ijoke.view.Joke;
import cn.edu.xmu.software.ijoke.factory.AppFactory;
import cn.edu.xmu.software.ijoke.service.JokeInfoService;

public class JokeInfoServiceImpl implements JokeInfoService{

	private JokeDAO jokeDAO ;
    private ClassAndJokeFileDAO classAndJokeFileDAO;
    private JokeFileDAO jokeFileDAO;
	public JokeDAO getJokeDAO() {
		return jokeDAO;
	}

	public void setJokeDAO(JokeDAO jokeDAO) {
		this.jokeDAO = jokeDAO;
	}

	public ClassAndJokeFileDAO getClassAndJokeFileDAO() {
		return classAndJokeFileDAO;
	}

	public void setClassAndJokeFileDAO(ClassAndJokeFileDAO classAndJokeFileDAO) {
		this.classAndJokeFileDAO = classAndJokeFileDAO;
	}

	public JokeFileDAO getJokeFileDAO() {
		return jokeFileDAO;
	}

	public void setJokeFileDAO(JokeFileDAO jokeFileDAO) {
		this.jokeFileDAO = jokeFileDAO;
	}

	public ArrayList<cn.edu.xmu.software.ijoke.entity.Joke> findAllByLimit10(int begin) {
		return jokeDAO.findAllByLimit10(begin);
	}
	public List<Joke> entityToView(List<cn.edu.xmu.software.ijoke.entity.Joke> jokesEntity)
	{
		List<Joke> jokesView = new ArrayList<Joke>();
		for (cn.edu.xmu.software.ijoke.entity.Joke j : jokesEntity) {
			Joke jokeView = new Joke();

			jokeView.setId(j.getJokeId());
			jokeView.setTitle(j.getTitle());
			jokeView.setAuthor(j.getAuthorName());
			jokeView.setUploadTime(j.getUploadTime());
			jokeView.setLike(j.getLikeNum());
			String location = jokeFileDAO.findJokeFileByFileId(j.getFileId())
					.getFilePath();
			jokeView.setLocation(location);
			jokeView.setKeyWord(j.getDescription());
			jokesView.add(jokeView);
		}

		return jokesView;
		}
	public String updateJoke(String jokeId, String title, String keyWord) {
		cn.edu.xmu.software.ijoke.entity.Joke joke = jokeDAO.findByJokeId(jokeId);
		joke.setTitle(title);
		joke.setDescription(keyWord);
		try{
		   jokeDAO.updateJoke(joke);
		   return "Update Success";
		}catch(Exception e)
		{
		   return "Update fail";
		}
	}
	public String deleteJokeToClass(String jokeId, String classId) {
		// TODO Auto-generated method stub
		try{
		classAndJokeFileDAO.deleteClassAndJoke(classId, jokeId);
		
		return "delete success";
		}catch(Exception e)
		{
			return "delete fail";
		}

	}

	public String addJokeToClass(String jokeId, String classId) {
		// TODO Auto-generated method stub
		try{
		
		
		classAndJokeFileDAO.addClassAndJoke(classId, jokeId);
		
		return "add success";	
		}catch(Exception e)
		{
			return "add fail";
		}

	}
	public List<Joke> getWithoutVerifyJokes( int begin,int pageSize)
	{
		return this.entityToView(jokeDAO.findByStatus("0",begin, pageSize));
	}
	public List<Joke> getVerifiedJokes( int begin,int pageSize)
	{
		return this.entityToView(jokeDAO.findByStatus("1",begin, pageSize));
	}
   @Test
   public void testUpdateJokeClass()
   {
	   System.out.println(AppFactory.getJokeInfoService().addJokeToClass("2010051221120731", "4"));
   }
   @Test
   public void testUpdateJoke()
   {
	   
	   AppFactory.getJokeInfoService().updateJoke("20100513143038809", "拉拉", "啦啦啦");
   }
   @Test
   public void testGetWithoutVerifyJokes()
   {
	   List<Joke> jokesView = AppFactory.getJokeInfoService().getWithoutVerifyJokes(1,5);
	   for(int i=0; i<jokesView.size(); i++)
	   {
		   System.out.println(jokesView.get(i).getAuthor());
	   }
   }
   @Test
   public void testGetVerifiedJokes()
   {
	   List<Joke> jokesView = AppFactory.getJokeInfoService().getVerifiedJokes(1,5);
	   for(int i=0; i<jokesView.size(); i++)
	   {
		   System.out.println(jokesView.get(i).getAuthor());
	   }
   }

}
