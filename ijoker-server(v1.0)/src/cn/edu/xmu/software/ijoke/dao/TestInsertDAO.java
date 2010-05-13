package cn.edu.xmu.software.ijoke.dao;

import java.util.List;

import cn.edu.xmu.software.ijoke.entity.ClassAndJokeFile;
import cn.edu.xmu.software.ijoke.entity.ClassItem;

public class TestInsertDAO {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	//	JokeDAO jokeDAO = new JokeDAO();
	//	System.out.print(jokeDAO.findByJokeId("1").getAuthorName());
	//	jokeDAO.updateJokeByJokeId("1");
//		ClassAndJokeFileDAO classAndFileDAO = new ClassAndJokeFileDAO();
//		List<ClassAndJokeFile> clAndFlList = (List<ClassAndJokeFile>) classAndFileDAO.
//		findClassAndJokeByLimit("1", 1, 5);
		ClassItemDAO classItemDAO =new ClassItemDAO();
		List<ClassItem> classItemList = classItemDAO.findAll();
//		for(int i=0; i<clAndFlList.size(); i++)
//			System.out.println(clAndFlList.get(i).getFileId().toString());
		for(int i=0; i<classItemList.size(); i++)
			System.out.println(classItemList.get(i).getJokeNum());
	}

}
