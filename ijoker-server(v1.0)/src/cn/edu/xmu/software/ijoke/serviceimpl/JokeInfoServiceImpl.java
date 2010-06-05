package cn.edu.xmu.software.ijoke.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.edu.xmu.software.ijoke.dao.ClassAndJokeFileDAO;
import cn.edu.xmu.software.ijoke.dao.JokeDAO;
import cn.edu.xmu.software.ijoke.dao.JokeFileDAO;
import cn.edu.xmu.software.ijoke.entity.Catalog;
import cn.edu.xmu.software.ijoke.entity.ClassAndJokeFile;
import cn.edu.xmu.software.ijoke.entity.JokeFile;
import cn.edu.xmu.software.ijoke.factory.AppFactory;
import cn.edu.xmu.software.ijoke.service.JokeInfoService;
import cn.edu.xmu.software.ijoke.utils.Consts;
import cn.edu.xmu.software.ijoke.view.Joke;
import cn.edu.xmu.software.ijoke.view.CatalogAndJokeView;
public class JokeInfoServiceImpl implements JokeInfoService {

	private JokeDAO jokeDAO;
	private ClassAndJokeFileDAO classAndJokeFileDAO;
	private JokeFileDAO jokeFileDAO;

	public JokeDAO getJokeDAO() {
		return jokeDAO;
	}

	public void setJokeDAO(JokeDAO jokeDAO) {
		this.jokeDAO = jokeDAO;
	}

	public ClassAndJokeFileDAO getClassAndJokeFileDAO() {
		return classAndJokeFileDAO;
	}

	public void setClassAndJokeFileDAO(ClassAndJokeFileDAO classAndJokeFileDAO) {
		this.classAndJokeFileDAO = classAndJokeFileDAO;
	}

	public JokeFileDAO getJokeFileDAO() {
		return jokeFileDAO;
	}

	public void setJokeFileDAO(JokeFileDAO jokeFileDAO) {
		this.jokeFileDAO = jokeFileDAO;
	}

	public ArrayList<cn.edu.xmu.software.ijoke.entity.Joke> findAllByLimit10(
			int begin) {
		return jokeDAO.findAllByLimit10(begin);
	}

	public List<Joke> entityToView(
			List<cn.edu.xmu.software.ijoke.entity.Joke> jokesEntity) {
		List<Joke> jokesView = new ArrayList<Joke>();
		for (cn.edu.xmu.software.ijoke.entity.Joke j : jokesEntity) {
			Joke jokeView = new Joke();

			jokeView.setId(j.getJokeId());
			jokeView.setTitle(j.getTitle());
			jokeView.setAuthor(j.getAuthorName());
			jokeView.setUploadTime(j.getUploadTime());
			jokeView.setLike(j.getLikeNum());
			JokeFile jokeFile = jokeFileDAO.findJokeFileByFileId(j.getFileId());
			if(jokeFile!=null)
			{
			    String location = jokeFile.getFilePath();
			    jokeView.setLocation(Consts.jokeMediaServerPath+location+jokeFile.getFileName()+jokeFile.getFileExtension());
			    jokeView.setKeyWord(j.getDescription());
			    jokesView.add(jokeView);
			    System.out.println(jokeView.getLocation());
			}
		}

		return jokesView;
	}

	public boolean updateJoke(String jokeId, String title, String keyWord) {
		cn.edu.xmu.software.ijoke.entity.Joke joke = jokeDAO
				.findByJokeId(jokeId);
		if(joke==null)
			return false;
		joke.setTitle(title);
		joke.setDescription(keyWord);
		try {
			jokeDAO.updateJoke(joke);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
    public boolean updateJoke(Joke joke)
    {
    	cn.edu.xmu.software.ijoke.entity.Joke jokeTemp = jokeDAO
		.findByJokeId(joke.getId());
      if(jokeTemp==null)
	   return false;
       jokeTemp.setTitle(joke.getTitle());
       jokeTemp.setDescription(joke.getKeyWord());
    	try {
			jokeDAO.updateJoke(jokeTemp);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }
	public boolean deleteJokeToClass(String jokeId, String classId) {
		// TODO Auto-generated method stub
		try {
			
			if(classAndJokeFileDAO.deleteClassAndJoke(classId, jokeId))
			    return false;
			else
				return true;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean addJokeToClass(String jokeId, String classId) {
		// TODO Auto-generated method stub
		try {

			ClassAndJokeFile classAndJokeFile = 
				classAndJokeFileDAO.findClassAndJoke("0", jokeId);
			if(classAndJokeFile!=null)
			{
				classAndJokeFileDAO.deleteClassAndJoke("0", jokeId);
			}
			
			if (classAndJokeFileDAO.findClassAndJoke(classId, jokeId) == null
					&& AppFactory.getCatalogManageService()
					.findCatalogById(classId)!=null 
					&& jokeDAO.findByJokeId(jokeId)!=null) {
				classAndJokeFileDAO.addClassAndJoke(classId, jokeId);
               // verify(jokeId);
				return true;
			}
			else
				return false;
		} catch (Exception e) {
			return false;
		}

	}

	public List<Joke> getWithoutVerifyJokes(int begin, int pageSize) {
		
		return this.entityToView(jokeDAO.findByStatus("0", begin, pageSize));
	}

	public List<Joke> getVerifiedJokes(int begin, int pageSize) {
		System.out.print(begin+"   "+pageSize);
		return this.entityToView(jokeDAO.findByStatus("1", begin, pageSize));
	}
	public boolean verify(String jokeId) {
		// TODO Auto-generated method stub
		try{
		cn.edu.xmu.software.ijoke.entity.Joke joke = jokeDAO.findByJokeId(jokeId);
		joke.setStatus("1");
		
		System.out.println("审核笑话"+joke.getJokeId());
		jokeDAO.updateJoke(joke);
		return true;
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	public boolean deleteJoke(String jokeId)
	{
		try{
		cn.edu.xmu.software.ijoke.entity.Joke joke = jokeDAO.findByJokeId(jokeId);
		if(joke == null)
			return false;
		jokeFileDAO.deleteJokeFile(joke.getFileId());
		jokeDAO.deleteJoke(jokeId);
		return true;
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	public List getCatalogAndJokeList(String jokeId,int begin,int pageSize) {
		// TODO Auto-generated method stub
		List<ClassAndJokeFile> catalogAndJokeList = classAndJokeFileDAO.findCatalogAndJokeByJokeId(jokeId, begin, pageSize);
		List catalogAndJokeViewList = new ArrayList<CatalogAndJokeView>();
		for(int i=0; i<catalogAndJokeList.size();i++)
		{
			Catalog catalog = AppFactory.getCatalogManageService()
			.findCatalogById(catalogAndJokeList.get(i).getClassId());
			
			if(catalog!=null)
			{
			CatalogAndJokeView catalogAndJokeView = new CatalogAndJokeView();
			catalogAndJokeView.setCatalogName(catalog.getCatalogName());
			catalogAndJokeView.setCatalogId(catalog.getCatalogId());
			catalogAndJokeView.setJokeTitle(jokeDAO.findByJokeId(jokeId).getTitle());
			catalogAndJokeView.setJokeId(jokeId);
			catalogAndJokeViewList.add(catalogAndJokeView);
			}
		}
		return catalogAndJokeViewList;
	}
public Joke getJokeByJokeId(String jokeId)
{
	cn.edu.xmu.software.ijoke.entity.Joke joke = jokeDAO.findByJokeId(jokeId);
	JokeFile jokeFile = jokeFileDAO.findJokeFileByFileId(joke.getFileId());
	Joke jokeView = new Joke();
	jokeView.setAuthor(joke.getAuthorName());
	jokeView.setFileLength(jokeFile.getFileLength());
	jokeView.setFileTime(jokeView.getFileTime());
	jokeView.setId(joke.getJokeId());
	jokeView.setTitle(joke.getTitle());
	jokeView.setLike(joke.getLikeNum());
	jokeView.setLocation(jokeFile.getFilePath()+jokeFile.getFileName()+jokeFile.getFileExtension());
	jokeView.setUploadTime(joke.getUploadTime());
	
	return jokeView; 
	}
//@Test
//public void testGetJokeByJokeId()
//{
//        System.out.println(AppFactory.getJokeInfoService().getJokeByJokeId("20100520121536241").getTitle());
//
//	}
//@Test
//	public void testUpdateJokeClass() {
//		System.out.println(AppFactory.getJokeInfoService().addJokeToClass(
//				"2010051221120731", "4"));
//	}
//	@Test
//	public void testDeleteJoke()
//	{
//		 System.out.println(AppFactory.getJokeInfoService().deleteJoke("test"));
//	}
//	 @Test
//	 public void testUpdateJoke()
//	 {
//		   Joke joke = new Joke();
//		   joke.setId("adfasdkjhf");
//		   joke.setKeyWord( "拉拉");
//		   joke.setTitle("啦啦lasdjflkajs");
//	       System.out.println(AppFactory.getJokeInfoService().updateJoke(joke));
//	 }
//	 
//	 @Test
//	 public void testDeleteJokeClass()
//	 {
//			 System.out.println(AppFactory.getJokeInfoService().deleteJokeToClass(
//						"20100522164940615", "4"));		 
//	 }
//	 @Test
//	 public void testGetWithoutVerifyJokes()
//	 {
//		
//	 List<Joke> jokesView =
//	 AppFactory.getJokeInfoService().getWithoutVerifyJokes(1,5);
//	 for(int i=0; i<jokesView.size(); i++)
//	 {
//	 System.out.println(jokesView.get(i).getAuthor());
//	 }
//	 }
//	 @Test
//	 public void testGetVerifiedJokes()
//	 {
//		 
//	 List<Joke> jokesView =
//	 AppFactory.getJokeInfoService().getVerifiedJokes(0,16);
//	 for(int i=0; i<jokesView.size(); i++)
//	 {
//	 System.out.println(jokesView.get(i).getAuthor());
//	 }
//	 }
@Test
public void testAddCatalogAndJoke()
{
	AppFactory.getJokeInfoService().addJokeToClass("20100520122823437","1");
}
	 @Test
	 public void testGetCatalogAndJoke()
	 {
		 
	 List<CatalogAndJokeView> catalogAndJokeList =
	 AppFactory.getJokeInfoService().getCatalogAndJokeList("20100520122823437",0,5);
	 for(int i=0; i<catalogAndJokeList.size(); i++)
	 {
	 System.out.println(catalogAndJokeList.get(i).getCatalogId());
	 }
	 }



}
