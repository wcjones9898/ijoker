package cn.edu.xmu.software.ijoke.service;

import java.util.List;

import cn.edu.xmu.software.ijoke.view.Joke;
public interface SearchByJokeIdService {

	public List<Joke> searchByJokeIdService(List<String> jokeIdList);
}
