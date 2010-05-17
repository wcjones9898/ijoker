package cn.edu.xmu.software.ijoke.service;

import java.util.List;

import cn.edu.xmu.software.ijoke.entity.Catalog;

public interface CatalogManageService {

	public boolean addCatalog(String classId,String className);
	public boolean deleteCatalog(String classId);
	public boolean updateCatalog(String classId,String className);
	public List<Catalog> getCatalogList(int begin, int pageSize);
}
