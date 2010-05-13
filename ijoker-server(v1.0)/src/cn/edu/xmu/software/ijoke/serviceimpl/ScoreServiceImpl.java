package cn.edu.xmu.software.ijoke.serviceimpl;

import org.junit.Test;

import cn.edu.xmu.software.ijoke.dao.JokeDAO;
import cn.edu.xmu.software.ijoke.service.ScoreService;

public class ScoreServiceImpl implements ScoreService{

	private JokeDAO jokeDAO = new JokeDAO();
	public Integer scoreService(String jokeId) {
		// TODO Auto-generated method stub
		jokeDAO.updateJokeByJokeId(jokeId);
		return 1;
	}

	@Test
	public void testScoreService()
	{
		scoreService("4");
	}
}
