package cn.edu.xmu.software.ijoke.action;


import java.util.List;

import cn.edu.xmu.software.ijoke.entity.Catalog;
import cn.edu.xmu.software.ijoke.service.CatalogManageService;
import cn.edu.xmu.software.ijoke.utils.Consts;
import cn.edu.xmu.software.ijoke.utils.Messages;

public class CatalogManageAction extends BaseAction {
	private CatalogManageService catalogManageService;
	private List<Catalog> catalogList;
	private int index_start,index_end;
	private int selectedIndex;
	private Catalog selectedCatalog;
	private String selectedCatalogId,selectedCatalogName,newCatalogName;
	
	public CatalogManageAction(){
		index_start=0;
		index_end=Consts.ONEPAGE_ITEM_NUM;
	}
	
	@Override
	public String execute() throws Exception {
		catalogList=catalogManageService.getCatalogList(index_start, index_end);
		return SUCCESS;
	}
	
	public String loadCatalogForModify(){
		selectedCatalog=catalogList.get(selectedIndex);
		return SUCCESS;	
	}
			
	public String addCatalog(){
		System.out.println("add:newCatalogName -"+newCatalogName);
		catalogManageService.addCatalog(newCatalogName);
		return SUCCESS;
	}
	
	public String deleteCatalog(){
		System.out.println("delete:CatalogId -"+selectedCatalogId);
		catalogManageService.deleteCatalog(selectedCatalogId);
		return SUCCESS;
	}
	
	public String modifyCatalog(){
		System.out.println("modify:new name -"+selectedCatalogName);
		catalogManageService.updateCatalog(selectedCatalog.getCatalogId(), selectedCatalog.getCatalogName());
		return SUCCESS;
	}
	
	public String prePage(){	
		this.clearErrorsAndMessages();
		if (index_start>Consts.ONEPAGE_ITEM_NUM){
			index_start-=Consts.ONEPAGE_ITEM_NUM;
			index_start--;
			index_end-=Consts.ONEPAGE_ITEM_NUM;
			index_end--;
		} else {			
			this.addActionMessage(Messages.FIRST_PAGE);			
		}
		return SUCCESS;		
	}
	
	public String nextPage(){	
		this.clearErrorsAndMessages();
		if (catalogList.size()== Consts.ONEPAGE_ITEM_NUM){
			index_start+=Consts.ONEPAGE_ITEM_NUM;
			index_start++;
			index_end+=Consts.ONEPAGE_ITEM_NUM;
			index_end++;		
		} else {
			this.addActionMessage(Messages.LAST_PAGE);	
		}
		return SUCCESS;
	}
	
	
	
	
	
	

	public CatalogManageService getCatalogManageService() {
		return catalogManageService;
	}

	public void setCatalogManageService(CatalogManageService catalogManageService) {
		this.catalogManageService = catalogManageService;
	}

	public List<Catalog> getCatalogList() {
		return catalogList;
	}

	public void setCatalogList(List<Catalog> catalogList) {
		this.catalogList = catalogList;
	}

	public String getSelectedCatalogId() {
		return selectedCatalogId;
	}

	public void setSelectedCatalogId(String selectedCatalogId) {
		this.selectedCatalogId = selectedCatalogId;
	}

	public String getSelectedCatalogName() {
		return selectedCatalogName;
	}

	public void setSelectedCatalogName(String selectedCatalogName) {
		this.selectedCatalogName = selectedCatalogName;
	}
	
	public int getSelectedIndex() {
		return selectedIndex;
	}

	public void setSelectedIndex(int selectedIndex) {
		this.selectedIndex = selectedIndex;
	}

	public Catalog getSelectedCatalog() {
		return selectedCatalog;
	}

	public void setSelectedCatalog(Catalog selectedCatalog) {
		this.selectedCatalog = selectedCatalog;
	}

	public String getNewCatalogName() {
		return newCatalogName;
	}

	public void setNewCatalogName(String newCatalogName) {
		this.newCatalogName = newCatalogName;
	}
	
	
	
}
