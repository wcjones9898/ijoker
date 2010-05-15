package cn.edu.xmu.software.ijoke.service;

import java.io.IOException;
import java.util.ArrayList;

import cn.edu.xmu.software.ijoke.entity.Catalog;

public interface CatalogService {

	public String createCatalog(String parentId, String catalogName);
	public String updateCatalog(Catalog catalog);
	public Catalog findCatalogById(String catalogId);
	public ArrayList<Catalog> findChildCatalog(String catalogId);
	public String deleteCatalog(String catalogId);
	public String createTree(Catalog catalog);
	public void makeXMLFile() throws IOException;
}
