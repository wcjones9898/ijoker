package cn.edu.xmu.software.ijoke.serviceimpl;

import org.junit.Test;

import cn.edu.xmu.software.ijoke.dao.JokeDAO;
import cn.edu.xmu.software.ijoke.entity.Joke;
import cn.edu.xmu.software.ijoke.factory.AppFactory;
import cn.edu.xmu.software.ijoke.service.VerifyService;
public class VerifyServiceImpl implements VerifyService{
    private JokeDAO jokeDAOServer;
	
	public boolean verify(String jokeId) {
		// TODO Auto-generated method stub
		try{
		Joke joke = jokeDAOServer.findByJokeId(jokeId);
		joke.setStatus("1");
		System.out.println(joke.getJokeId());
		jokeDAOServer.updateJoke(joke);
		return true;
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public JokeDAO getJokeDAOServer() {
		return jokeDAOServer;
	}

	public void setJokeDAOServer(JokeDAO jokeDAOServer) {
		this.jokeDAOServer = jokeDAOServer;
	}

	@Test
	public void testVerify()
	{
		AppFactory.getVerifyService().verify("20100518011631055");
	}
}
