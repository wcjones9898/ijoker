package cn.edu.xmu.software.ijoke.serviceimpl;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import cn.edu.xmu.software.ijoke.dao.CatalogDAO;
import cn.edu.xmu.software.ijoke.entity.Catalog;
import cn.edu.xmu.software.ijoke.entity.JokeFile;
import cn.edu.xmu.software.ijoke.factory.AppFactory;
import cn.edu.xmu.software.ijoke.service.CatalogService;

public class CatalogServiceImpl implements CatalogService{

	private CatalogDAO catalogDAO;
	
	public String makeDir(String path)
	{
		File file = new File(path);
		if(!file.exists())
		{
			file.mkdirs();
			return "1";
		}
		else 
			return "0";
	}
	public void deleteDir(String path)
	{
		File dir = new File(path);
		File filelist[]=dir.listFiles();  
	    int listlen=filelist.length;  
	    for(int   i=0;i<listlen;i++)   {  
	      if(filelist[i].isDirectory()){  
	           deleteDir(filelist[i].getAbsolutePath());  
	          }  
	          else{  
	            filelist[i].delete();  
	          }  
	      }  
	      dir.delete();//删除当前目录  
	}
	public ArrayList showFilesInDir(String path)
	{
		ArrayList<JokeFile> jokeFileList = new ArrayList<JokeFile>();
		File dir = new File(path);
		File filelist[]=dir.listFiles();  
	    int listlen=filelist.length;  
	    for(int   i=0;i<listlen;i++)   {
	      
	      if(filelist[i].isDirectory()){  
	    	  ArrayList<JokeFile> childFileList = showFilesInDir(filelist[i].getAbsolutePath()); 
	    	  for(int j=0;j<childFileList.size();j++)
	    		    jokeFileList.add(childFileList.get(j));
	          }  
	          else{  
	              JokeFile jokeFile = new JokeFile();
	              String fileName = filelist[i].getName();
	              jokeFile.setFileExtension(
	            		  fileName.substring(fileName.indexOf('.')));
	              jokeFile.setFileName(fileName);
	              jokeFile.setFilePath(filelist[i].getAbsolutePath());
	              jokeFileList.add(jokeFile);
	          }  
	      }
		return jokeFileList;  
	}
	public String createCatalogId()
	{
		String catalogId = null;
		Timestamp date = new Timestamp((new Date().getTime()));
		catalogId = date.toString().replace(" ","");
		catalogId = catalogId.replace("-","");
		catalogId = catalogId.replace(":","");
		catalogId = catalogId.replace(".","");
	    return catalogId;
	}
	public String createCatalog(String parentId, String catalogName) {
		// TODO Auto-generated method stub
		Catalog catalog = new Catalog();
		
		Catalog parent = (Catalog) catalogDAO.findByCatalogId(parentId).get(0);
		
		catalog.setParentId(parentId);
		catalog.setCatalogId(createCatalogId());
		catalog.setCatalogLevel(parent.getCatalogLevel()+1);
		catalog.setCatalogPath(parent.getCatalogPath()+catalogName);
	    catalog.setCatalogName(catalogName);
	    
	    
	    try{
	    	if(this.makeDir(catalog.getCatalogPath()).equals("1"))
	    	{
		        catalogDAO.save(catalog);
		        return "已加入新目录";
	    	}
	    	else
	    		return "目录已存在";
	    }catch(Exception e)
	    {
	        return "插入错误";
	    }
		
	}

	public String deleteCatalog(String catalogId) {
		// TODO Auto-generated method stub
		try{
			Catalog catalog = (Catalog) catalogDAO.findByCatalogId(catalogId).get(0);
			this.deleteDir(catalog.getCatalogPath());
		    catalogDAO.delete(catalog);
		    return "已删除目录";
		}catch(Exception e)
		{
			return "删除错误";
		}
	}

	public String updateCatalog(Catalog catalog) {
		// TODO Auto-generated method stub
		try{
		catalogDAO.save(catalog);
		return "已更新目录";
		}catch(Exception e)
		{
			return "更新错误";
		}
	}
	
	public Catalog findCatalogById(String catalogId)
	{
		return (Catalog) catalogDAO.findByCatalogId(catalogId).get(0);
	}
	
	public ArrayList<Catalog> findChildCatalog(String catalogId)
	{
		return (ArrayList<Catalog>) catalogDAO.findByParentId(catalogId);
	}

	public CatalogDAO getCatalogDAO() {
		return catalogDAO;
	}
	public void setCatalogDAO(CatalogDAO catalogDAO) {
		this.catalogDAO = catalogDAO;
	}
	
	@Test
	public void testCreateCatalog()
	{
		System.out.println(AppFactory.getCatalogService().createCatalog("1", "Bill"));
	}
	@Test
	public void testDeleteCatalog()
	{
		AppFactory.getCatalogService().deleteCatalog("20100514002659141");
	}
	@Test
	public void testShowFilesInDir()
	{
		Catalog catalog = AppFactory.getCatalogService().findCatalogById("1");
		ArrayList<JokeFile> jokeFileList = this.showFilesInDir(catalog.getCatalogPath());
		for(int i=0; i<jokeFileList.size(); i++)
		{
			System.out.println(jokeFileList.get(i).getFilePath());
		}
	}

}
