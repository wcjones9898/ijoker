package cn.edu.xmu.software.ijoker.service;
import cn.edu.xmu.software.ijoker.entity.Joke;
import cn.edu.xmu.software.ijoker.entity.ClassItem;
interface IPlayService {
    void chose(int position);
    void like();
    void play();
    void pause();
    void stop();
    void addJokeList(String location);
	void clearJokeList();
	List<Joke> getJokeList();
	List<ClassItem> getDivisionList();
	void updateJokeList();
	void updateDivisionList();
	 boolean isPlaying();
	 Joke getJokePlaying();
}