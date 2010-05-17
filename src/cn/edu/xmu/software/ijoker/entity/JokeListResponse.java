package cn.edu.xmu.software.ijoker.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class JokeListResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArrayList<Joke> getJokeList() {
		System.out.print("get Joke List");
		return new ArrayList<Joke>();
	}

}
