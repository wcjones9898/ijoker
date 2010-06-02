package cn.edu.xmu.software.ijoke.service;

import java.util.ArrayList;
import java.util.List;

import cn.edu.xmu.software.ijoke.view.Joke;
import cn.edu.xmu.software.ijoke.entity.ClassAndJokeFile;
public interface JokeInfoService {

	public ArrayList<cn.edu.xmu.software.ijoke.entity.Joke> findAllByLimit10(int begin);
    public boolean updateJoke(String jokeId,String title, String keyWord);
    public boolean updateJoke(Joke joke);
    public boolean addJokeToClass(String jokeId,String classId);
    public boolean deleteJokeToClass(String jokeId,String classId);
    public List<Joke> getWithoutVerifyJokes(int begin,int pageSize);
    public List<Joke> getVerifiedJokes(int begin,int pageSize);
	public boolean deleteJoke(String jokeId);
	public List<ClassAndJokeFile> getCatalogAndJokeList(String jokeId,int begin,int pageSize);
}
