package cn.edu.xmu.software.ijoke.service;

import java.util.ArrayList;

import cn.edu.xmu.software.ijoke.entity.Joke;

public interface JokeInfoService {

	public ArrayList<Joke> findAllByLimit10(int begin);
	public Joke findByJokeId(String jokeId);
	public int getJokeNum();
}
