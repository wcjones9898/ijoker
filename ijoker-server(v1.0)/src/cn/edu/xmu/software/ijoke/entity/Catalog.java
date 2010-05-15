package cn.edu.xmu.software.ijoke.entity;

import java.io.File;
import java.util.ArrayList;

/**
 * Catalog entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Catalog implements java.io.Serializable {

	// Fields

	private Integer id;
	private String catalogName;
	private String parentId;
	private String catalogId;
	private int catalogLevel;
    private String catalogPath;
    private ArrayList<Catalog> childCatalogs = null;
	// Constructors

	/** default constructor */
	public Catalog() {
	
	}

	/** full constructor */
	public Catalog(String catalogName, String parentId, String catalogId,
			int catalogLevel) {
		this.catalogName = catalogName;
		this.parentId = parentId;
		this.catalogId = catalogId;
		this.catalogLevel = catalogLevel;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCatalogName() {
		return this.catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getCatalogId() {
		return this.catalogId;
	}

	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}

	public int getCatalogLevel() {
		return this.catalogLevel;
	}

	public void setCatalogLevel(int catalogLevel) {
		this.catalogLevel = catalogLevel;
	}

	public String getCatalogPath() {
		return catalogPath;
	}

	public void setCatalogPath(String catalogPath) {
		this.catalogPath = catalogPath;
	}
	
	public ArrayList<Catalog> getChildCatalogs() {
		return childCatalogs;
	}

	public void setChildCatalogs(ArrayList<Catalog> childCatalogs) {
		this.childCatalogs = childCatalogs;
	}

	public String printTree()
	{
		
		String result = "";
		result+="<tree text="+"'"+this.catalogName+"'>"+"\n";
		result+=showFilesInDir();
		if(this.childCatalogs!=null)
		{
		for(int i=0;i<this.getChildCatalogs().size();i++)
		{
			result+=this.getChildCatalogs().get(i).printTree();
		}
		}
		result+="</tree>"+"\n";
		return result;
	}
	public String showFilesInDir()
	{
		
		String result = "";
		File dir = new File(this.catalogPath);
		File filelist[]=dir.listFiles();  
	    int listlen=filelist.length;  
	    for(int   i=0;i<listlen;i++)   {
	      
	      if(!filelist[i].isDirectory()){  
	    	     result+="<tree text="+"'"+ filelist[i].getName()+"'>"+"\n";
	             result+="</tree>"+"\n";
	          }  
	      }
		return result;  
	}

}