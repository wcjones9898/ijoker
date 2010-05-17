package cn.edu.xmu.software.ijoke.serviceimpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import cn.edu.xmu.software.ijoke.dao.CatalogDAO;
import cn.edu.xmu.software.ijoke.dao.ClassItemDAO;
import cn.edu.xmu.software.ijoke.entity.Catalog;
import cn.edu.xmu.software.ijoke.service.CatalogManageService;
import cn.edu.xmu.software.ijoke.factory.AppFactory;
import cn.edu.xmu.software.ijoke.utils.Consts;
public class CatalogManageServiceImpl implements CatalogManageService{

	private CatalogDAO catalogDAO;
	
	private void getCatalogId()
	{
		
	}


	private String createCatalogId()
	{
		String catalogId = null;
		Timestamp date = new Timestamp((new Date().getTime()));
		catalogId = date.toString().replace(" ","");
		catalogId = catalogId.replace("-","");
		catalogId = catalogId.replace(":","");
		catalogId = catalogId.replace(".","");
	    return catalogId;
	}
	public boolean addCatalog(String catalogName) {
		// TODO Auto-generated method stub

		try{
			if(catalogDAO.findCatalogByCatalogName(catalogName)!=null)
				return  false;
			
			Catalog catalog = new Catalog();
			catalog.setCatalogId(this.createCatalogId());
			catalog.setCatalogName(catalogName);
			catalog.setCatalogLevel(1);
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
			Catalog catalog = catalogDAO.findCatalogByCatalogId(classId);
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
			Catalog catalog = catalogDAO.findCatalogByCatalogId(classId);

		if(catalog!=null)
		{
			catalog.setCatalogName(className);
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




	public CatalogDAO getCatalogDAO() {
		return catalogDAO;
	}

	public void setCatalogDAO(CatalogDAO catalogDAO) {
		this.catalogDAO = catalogDAO;
	}

	public List<Catalog> getCatalogList() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Catalog> getCatalogList(int begin, int pageSize) {
		// TODO Auto-generated method stub
		
		return this.catalogDAO.findByLimit(begin, pageSize);
	}
	@Test
	public void testAddClassItem()
   {
		System.out.println(AppFactory.getCatalogManageService().addCatalog( "拉拉啦啦啦"));
	}
//	@Test
//	public void testUpdateClassItem()
//	   {
//		System.out.println(AppFactory.getCatalogManageService().updateCatalog("6","sadf "));
//		}
//	@Test
//	public void testDeleteClassItem()
//	   {
//		System.out.println(AppFactory.getCatalogManageService().deleteCatalog("7"));
//		}
	@Test
	public void testGetCatalogList()
	{
		List<Catalog> catalogList = AppFactory.getCatalogManageService().getCatalogList(1, 5);
		for(int i=0 ;i <catalogList.size();i++)
			System.out.print(catalogList.get(i).getCatalogName());
	}


}