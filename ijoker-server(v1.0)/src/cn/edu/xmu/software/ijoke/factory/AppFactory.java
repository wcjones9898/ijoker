package cn.edu.xmu.software.ijoke.factory;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.edu.xmu.software.ijoke.service.CatalogService;
import cn.edu.xmu.software.ijoke.service.JokeInfoService;
import cn.edu.xmu.software.ijoke.service.SearchService;
import cn.edu.xmu.software.ijoke.service.UploadJokeFileService;

public class AppFactory {
	public static ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
	public static UploadJokeFileService getUploadJokeFileService()
	{
		return (UploadJokeFileService)app.getBean("UploadJokeFileService");
	}
	public static CatalogService getCatalogService()
	{
		return (CatalogService)app.getBean("CatalogService");
	}
	public static SearchService getSearchService() {

		return (SearchService) app.getBean("SearchService");
	}
	public static JokeInfoService getJokeInfoService() {

		return (JokeInfoService) app.getBean("JokeInfoService");
	}
	@Test
	public void testUploadJokeFileService()
	{
		getUploadJokeFileService().insertJokeFile(".mp3","/guodegang/","123aredsfa");;
	}
	
}
