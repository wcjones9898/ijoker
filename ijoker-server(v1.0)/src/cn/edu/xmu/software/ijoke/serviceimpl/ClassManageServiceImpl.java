package cn.edu.xmu.software.ijoke.serviceimpl;

import org.junit.Test;

import cn.edu.xmu.software.ijoke.dao.CatalogDAO;
import cn.edu.xmu.software.ijoke.dao.ClassItemDAO;
import cn.edu.xmu.software.ijoke.entity.Catalog;
import cn.edu.xmu.software.ijoke.service.ClassManageService;
import cn.edu.xmu.software.ijoke.factory.AppFactory;
import cn.edu.xmu.software.ijoke.utils.Consts;
public class ClassManageServiceImpl implements ClassManageService{

	private CatalogDAO catalogDAO;
	
	

	public boolean addCatalog(String classId, String className) {
		// TODO Auto-generated method stub
		try{
			if(catalogDAO.findClassItemByClassId(classId)!=null)
				return  false;
		Catalog catalog = new Catalog();
		catalog.setClassId(classId);
		catalog.setClassName(className);
		catalog.setJokeNum(0);
		catalog.setClassLevel(1);
		catalogDAO.save(catalog);
		return true;
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteCatalog(String classId) {
		// TODO Auto-generated method stub
		try{
			Catalog catalog = catalogDAO.findClassItemByClassId(classId);
			if(catalog == null)
				return false;
		catalogDAO.delete(catalog);
		return true;
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateCatalog(String classId, String className) {
		// TODO Auto-generated method stub
		try{
			Catalog catalog = catalogDAO.findClassItemByClassId(classId);

		if(catalog!=null)
		{
			catalog.setClassName(className);
			catalogDAO.update(catalog);
			return true;
		}
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@Test
	public void testAddClassItem()
   {
		System.out.println(AppFactory.getClassManageService().addCatalog("6", "拉拉"));
	}
	@Test
	public void testDeleteClassItem()
	   {
		System.out.println(AppFactory.getClassManageService().deleteCatalog("7"));
		}
	@Test
	public void testUpdateClassItem()
	   {
		System.out.println(AppFactory.getClassManageService().updateCatalog("6","sadf "));
		}

	public CatalogDAO getCatalogDAO() {
		return catalogDAO;
	}

	public void setCatalogDAO(CatalogDAO catalogDAO) {
		this.catalogDAO = catalogDAO;
	}
}