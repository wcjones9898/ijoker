package cn.edu.xmu.software.ijoke.serviceimpl;

import org.junit.Test;

import cn.edu.xmu.software.ijoke.dao.JokeDAO;
import cn.edu.xmu.software.ijoke.entity.Joke;
import cn.edu.xmu.software.ijoke.service.VerifyService;

public class VerifyServiceImpl implements VerifyService{

	private JokeDAO jokeDAO = new JokeDAO();
	public void verify(String jokeId) {
		// TODO Auto-generated method stub
		Joke joke = jokeDAO.findByJokeId(jokeId);
		joke.setStatus("1");
		jokeDAO.updateJoke(joke);
	}

	@Test
	public void testVerify()
	{
		verify("1");
	}
}
