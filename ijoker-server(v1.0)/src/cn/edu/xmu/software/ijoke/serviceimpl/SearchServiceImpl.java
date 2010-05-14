package cn.edu.xmu.software.ijoke.serviceimpl;

import java.util.List;

import org.junit.Test;

import cn.edu.xmu.software.ijoke.dao.JokeDAO;
import cn.edu.xmu.software.ijoke.entity.Joke;
import cn.edu.xmu.software.ijoke.factory.AppFactory;
import cn.edu.xmu.software.ijoke.service.SearchService;

public class SearchServiceImpl implements SearchService {
	private JokeDAO jokeDAO;
	

	public List<Joke> searchByTitle(String title) {
	      
		return jokeDAO.findByTitle(title);
	}

	public List<Joke> searchByAuthor(String author) {
		
		return jokeDAO.findByAuthor(author);
	}

	public List<Joke> searchByAllInfo(String info) {
		return jokeDAO.findByAllInfo(info);
	}
	
	
	public JokeDAO getJokeDAO() {
		return jokeDAO;
	}

	public void setJokeDAO(JokeDAO jokeDAO) {
		this.jokeDAO = jokeDAO;
	}

	@Test
	public void testSearchByTitle(){
    	SearchService searchService = AppFactory.getSearchService();
    	List<Joke> jokes = searchService.searchByAllInfo("a");
		/*JokeDAO jokeDAO1 = (JokeDAO) AppFactory.app.getBean("JokeDAO");
		List<Joke> jokes = jokeDAO1.findByTitle("a");*/
		for(Joke j : jokes)
		{
			System.out.println(j.getTitle() + "  " +j.getDescription());
			
		}
		
	}
}
