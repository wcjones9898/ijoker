package cn.edu.xmu.software.ijoke.service;

import java.util.ArrayList;
import java.util.List;

import cn.edu.xmu.software.ijoke.view.Joke;

public interface JokeInfoService {

	public ArrayList<cn.edu.xmu.software.ijoke.entity.Joke> findAllByLimit10(int begin);
    public String updateJoke(String jokeId,String title, String keyWord);
    public String addJokeToClass(String jokeId,String classId);
    public String deleteJokeToClass(String jokeId,String classId);
    public List<Joke> getWithoutVerifyJokes(int begin,int pageSize);
    public List<Joke> getVerifiedJokes(int begin,int pageSize);
	public String deleteJoke(String jokeId);
}
