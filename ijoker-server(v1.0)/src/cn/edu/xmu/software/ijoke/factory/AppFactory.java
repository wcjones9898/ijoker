package cn.edu.xmu.software.ijoke.factory;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.edu.xmu.software.ijoke.service.AdminLoginService;
import cn.edu.xmu.software.ijoke.service.CatalogService;
import cn.edu.xmu.software.ijoke.service.ClassManageService;
import cn.edu.xmu.software.ijoke.service.JokeInfoService;
import cn.edu.xmu.software.ijoke.service.SearchByJokeIdService;
import cn.edu.xmu.software.ijoke.service.SearchService;
import cn.edu.xmu.software.ijoke.service.UploadJokeFileService;
import cn.edu.xmu.software.ijoke.service.UserService;

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
	public static SearchByJokeIdService getSearchByJokeIdService()
	{
		return (SearchByJokeIdService) app.getBean("SearchByJokeIdService");
	}
	public static AdminLoginService  getAdminLoginService()
	{
		return (AdminLoginService) app.getBean("AdminLoginService");
	}
	public static ClassManageService  getClassManageService()
	{
		return (ClassManageService) app.getBean("ClassManageService");
	}
	public static UserService getUserService()
	{
		return (UserService) app.getBean("UserService");
	}

	@Test
	public void testUploadJokeFileService()
	{
		getUploadJokeFileService().insertJokeFile(".mp3","/guodegang/","123aredsfa");;
	}
	
}