package cn.edu.xmu.software.ijoke.service;

import java.util.List;

import cn.edu.xmu.software.ijoke.entity.Joke;


public interface SearchService {
	public List<Joke> searchByTitle(String title);
	public List<Joke> searchByAuthor(String author);
	public List<Joke> searchByAllInfo(String info);
}
