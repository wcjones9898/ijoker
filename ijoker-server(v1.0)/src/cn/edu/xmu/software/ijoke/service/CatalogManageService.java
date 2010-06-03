package cn.edu.xmu.software.ijoke.service;

import java.util.List;

import cn.edu.xmu.software.ijoke.entity.Catalog;

public interface CatalogManageService {


	public boolean deleteCatalog(String classId);
	public boolean updateCatalog(String classId,String className);
	public List<Catalog> getCatalogList(int begin, int pageSize);
	public boolean addCatalog(String catalogName);
	public Catalog findCatalogById(String catalogId);
}
