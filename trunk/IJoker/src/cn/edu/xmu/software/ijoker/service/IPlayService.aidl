package cn.edu.xmu.software.ijoker.service;
import cn.edu.xmu.software.ijoker.entity.Joke;
import cn.edu.xmu.software.ijoker.entity.ClassItem;
interface IPlayService {
    void play(in Joke joke);
    void pause();
    void stop();
	 boolean isPlaying();
	 Joke getJokePlaying();
}