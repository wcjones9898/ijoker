package cn.edu.xmu.software.ijoke.service;

import java.util.List;

import cn.edu.xmu.software.ijoke.view.Joke;




public interface SearchService {
	public List<Joke> searchByTitle(String title);
	public List<Joke> searchByAuthor(String author);
	public List<Joke> searchByAllInfo(String info);
	public List<Joke> searchService(int searchType, String info);
    public List<Joke> searchFromDataBase(int searchType, String info);
}
