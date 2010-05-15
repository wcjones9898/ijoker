package cn.edu.xmu.software.ijoke.serviceimpl;

import org.junit.Test;

import cn.edu.xmu.software.ijoke.dao.JokeDAO;
import cn.edu.xmu.software.ijoke.entity.Joke;
import cn.edu.xmu.software.ijoke.service.VerifyService;
import cn.edu.xmu.software.ijoke.utils.Consts;
public class VerifyServiceImpl implements VerifyService{

	private JokeDAO jokeDAO = new JokeDAO();
	public String verify(String jokeId) {
		// TODO Auto-generated method stub
		try{
		Joke joke = jokeDAO.findByJokeId(jokeId);
		joke.setStatus("1");
		jokeDAO.updateJoke(joke);
		return Consts.VERIFY_SUCCESS;
		}catch(Exception e)
		{
			e.printStackTrace();
			return Consts.VERIFY_FAIL;
		}
	}

	@Test
	public void testVerify()
	{
		verify("1");
	}
}
