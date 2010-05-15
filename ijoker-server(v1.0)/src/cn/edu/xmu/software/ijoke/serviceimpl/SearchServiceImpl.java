package cn.edu.xmu.software.ijoke.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.edu.xmu.software.ijoke.dao.JokeDAO;
import cn.edu.xmu.software.ijoke.dao.JokeFileDAO;

import cn.edu.xmu.software.ijoke.factory.AppFactory;
import cn.edu.xmu.software.ijoke.service.SearchService;
import cn.edu.xmu.software.ijoke.view.Joke;

public class SearchServiceImpl implements SearchService {
	private JokeDAO jokeDAO;
	private JokeFileDAO jokeFileDAO;

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
			jokeView.setKeyWord(j.getDescription());
			jokesView.add(jokeView);

		}
		return jokesView;

	}

	public List<Joke> searchByTitle(String title) {
		List<Joke> jokesView =jokeDAO.findByTitle(title);
		return jokesView;
	}

	public List<Joke> searchByAuthor(String author) {
		List<Joke> jokesView = entityToView(jokeDAO.findByAuthor(author));
		return jokesView;
	}

	public List<Joke> searchByAllInfo(String info) {
		List<Joke> jokesView = entityToView(jokeDAO.findByAllInfo(info));
		return jokesView;
	}

	public List<String> searchByAllInfoToId(String info)
	{
		List<String> jokeIdList = jokeDAO.findByAllInfoToJokeId(info);
		return jokeIdList;
	}
	public List<String> searchByAuthorToId(String author) {
		List<String> jokeIdList = jokeDAO.findByAuthorToJokeId(author);
		return jokeIdList;
	}
	public List<String> searchServiceToId(int searchType, String info)
	{
		List<String> jokeList = null;
		switch(searchType)
		{
		case 1:
			jokeList = this.searchByAllInfoToId(info);
			break;
		case 2:
			jokeList = this.searchByAuthorToId(info);
			break;
		}
		return jokeList;
	}
	public List<Joke> searchFromDataBase(int searchType, String info)
	{
		System.out.println("///////搜索服务////////");
		System.out.println("searchType:"+searchType);
		System.out.println("info:"+info);
		List<Joke> jokeList = null;
		switch(searchType)
		{
		case 1:
			jokeList = this.searchByAllInfo(info);
			break;
		case 2:
			jokeList = this.searchByAuthor(info);
			break;
		}
		if(jokeList == null)
			jokeList = new ArrayList<Joke>();
		return jokeList;
	}
	public List<Joke> searchService(int searchType, String info)
	{
		return  AppFactory.getSearchService().searchFromDataBase(searchType, info);
	}
//	@Test
//	public void testSearchByTitle() {
//		SearchService searchService = AppFactory.getSearchService();
//		List<Joke> jokes = searchService.searchByAllInfo("a");
//		/*
//		 * JokeDAO jokeDAO1 = (JokeDAO) AppFactory.app.getBean("JokeDAO");
//		 * List<Joke> jokes = jokeDAO1.findByTitle("a");
//		 */
//		for (Joke j : jokes) {
//			System.out.println(j.getTitle() + "  " + j.getLocation());
//
//		}
//
//	}
	@Test
	public void testSearchService()
	{
		List<Joke> jokes;
		jokes = AppFactory.getSearchService().searchService(1, "l");
		for (Joke j : jokes) {
			System.out.println(j.getTitle() + "  " + j.getLocation());

		}
	}
}
