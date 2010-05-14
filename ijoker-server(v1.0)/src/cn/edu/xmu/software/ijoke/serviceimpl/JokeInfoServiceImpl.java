package cn.edu.xmu.software.ijoke.serviceimpl;

import java.util.ArrayList;

import cn.edu.xmu.software.ijoke.dao.JokeDAO;
import cn.edu.xmu.software.ijoke.entity.Joke;
import cn.edu.xmu.software.ijoke.service.JokeInfoService;

public class JokeInfoServiceImpl implements JokeInfoService{

	private JokeDAO jokeDAO ;
	public ArrayList<Joke> findAllByLimit10(int begin) {
		// TODO Auto-generated method stub
		return jokeDAO.findAllByLimit10(begin);
	}

	public Joke findByJokeId(String jokeId) {
		// TODO Auto-generated method stub
		return null;
	}

	public JokeDAO getJokeDAO() {
		return jokeDAO;
	}

	public void setJokeDAO(JokeDAO jokeDAO) {
		this.jokeDAO = jokeDAO;
	}

	public int getJokeNum() {
		// TODO Auto-generated method stub
		return jokeDAO.getJokesNum();
	}

}
