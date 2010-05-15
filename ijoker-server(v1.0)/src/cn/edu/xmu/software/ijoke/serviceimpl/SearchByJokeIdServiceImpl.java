package cn.edu.xmu.software.ijoke.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.edu.xmu.software.ijoke.dao.JokeDAO;
import cn.edu.xmu.software.ijoke.dao.JokeFileDAO;
import cn.edu.xmu.software.ijoke.factory.AppFactory;
import cn.edu.xmu.software.ijoke.service.SearchByJokeIdService;
import cn.edu.xmu.software.ijoke.view.Joke;

public class SearchByJokeIdServiceImpl implements SearchByJokeIdService{

	JokeDAO jokeDAO ;
	private JokeFileDAO jokeFileDAO;
	public List<Joke> entityToView(
			List<cn.edu.xmu.software.ijoke.entity.Joke> jokesEntity) {

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
			jokesView.add(jokeView);

		}
		return jokesView;

	}
	public List<Joke> searchByJokeIdService(List<String> jokeIdList) {
		List  jokes = new ArrayList<cn.edu.xmu.software.ijoke.entity.Joke>();
		for(int i = 0; i<jokeIdList.size(); i++)
		{
 		   jokes.add(jokeDAO.findByJokeId(jokeIdList.get(i)));
		}
		// TODO Auto-generated method stub
		return this.entityToView(jokes);
	}
	public JokeDAO getJokeDAO() {
		return jokeDAO;
	}
	public void setJokeDAO(JokeDAO jokeDAO) {
		this.jokeDAO = jokeDAO;
	}
	public JokeFileDAO getJokeFileDAO() {
		return jokeFileDAO;
	}
	public void setJokeFileDAO(JokeFileDAO jokeFileDAO) {
		this.jokeFileDAO = jokeFileDAO;
	}

	@Test
	public void testSearchByJokeIdService()
	{
		List i = new ArrayList<String>();
		i.add("2010051221120731");
		List<Joke> jokeList = AppFactory.getSearchByJokeIdService().searchByJokeIdService(i);
		for(int j=0;j<jokeList.size();j++)
		{
			System.out.println(jokeList.get(j).getAuthor());
		}
	}

}
