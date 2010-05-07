package cn.edu.xmu.software.ijoker.service;
import cn.edu.xmu.software.ijoker.entity.Joke;
interface IPlayService {
    void chose(int position);
    void like(boolean isLike);
    void play();
    void pause();
    void stop();
    void addJokeList(String location);
	void clearJokeList();
	List<Joke> getJokeList();
}