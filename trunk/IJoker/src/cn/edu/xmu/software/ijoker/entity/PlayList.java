package cn.edu.xmu.software.ijoker.entity;

import java.util.ArrayList;
/**
 * @author hipisy
 *
 */
public class PlayList {

	private String title;
	private ArrayList<Joke> jokeList = null;

	public void addJoke(Joke joke) {
		if (jokeList == null)
			jokeList = new ArrayList<Joke>();

		jokeList.add(joke);

		System.out.println("Joke " + jokeList.size() + ": "
				+ joke.getLocation());
	}

	public ArrayList<Joke> getJokeList() {
		return jokeList;
	}

	public void setJokeList(ArrayList<Joke> jokeList) {
		this.jokeList = jokeList;
	}

	/**
	 * @return Returns the title.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

}
