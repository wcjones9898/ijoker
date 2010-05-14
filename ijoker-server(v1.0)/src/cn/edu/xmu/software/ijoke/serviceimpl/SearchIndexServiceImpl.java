package cn.edu.xmu.software.ijoke.serviceimpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import cn.edu.xmu.software.ijoke.service.SearchIndexService;
import cn.edu.xmu.software.ijoke.entity.Joke;
import cn.edu.xmu.software.ijoke.factory.ConfigFactory;
import cn.edu.xmu.software.ijoke.factory.AppFactory;
public class SearchIndexServiceImpl implements SearchIndexService{

	private String createIndexName()
	{
		String indexName = null;
		Timestamp date = new Timestamp((new Date().getTime()));
		indexName = date.toString().replace(" ","");
		indexName = indexName.replace("-","");
		indexName = indexName.replace(":","");
		indexName = indexName.replace(".","");
	    return indexName;
	}
	public File createSearchIndex() {
		// TODO Auto-generated method stub
		
		File file = new File(ConfigFactory.getSearchIndexPath()+
				createIndexName() + ".txt");
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int j=0; j<AppFactory.getJokeInfoService().getJokeNum()/10 ; j++)
		{
	    ArrayList<Joke> jokeList = AppFactory.getJokeInfoService().findAllByLimit10(0);
		for(int i=0; i<jokeList.size(); i++)
		{
			jokeList.get(i).setTitle(jokeList.get(i).getTitle().replace("&", ""));
			try {
				fos.write((jokeList.get(i).getJokeId()
						+"&&&&"+jokeList.get(i).getTitle()+"&&&&"
						+jokeList.get(i).getAuthorName()+"&&&&"
						+jokeList.get(i).getDescription()).getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
		try {
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file;
	}

	@Test
	public void testCreateSearchIndex()
	{
		createSearchIndex();
	}
}
