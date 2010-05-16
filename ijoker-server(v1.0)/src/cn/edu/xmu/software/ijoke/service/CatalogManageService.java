package cn.edu.xmu.software.ijoke.service;

public interface CatalogManageService {

	public boolean addCatalog(String classId,String className);
	public boolean deleteCatalog(String classId);
	public boolean updateCatalog(String classId,String className);
}
