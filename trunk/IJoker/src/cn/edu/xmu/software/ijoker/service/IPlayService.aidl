package cn.edu.xmu.software.ijoker.service;
import cn.edu.xmu.software.ijoker.entity.Joke;
import cn.edu.xmu.software.ijoker.entity.ClassItem;
interface IPlayService {
    void chose(int position);
    void like();
    void play();
    void pause();
    void stop();
	List<Joke> getJokeList();
	List<ClassItem> getDivisionList();
	void updateJokeList(int page);
	void updateDivisionList();
	 boolean isPlaying();
	 Joke getJokePlaying();
}