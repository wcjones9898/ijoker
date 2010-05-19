package cn.edu.xmu.software.ijoke.action;

import java.util.List;
import java.util.Map;

import cn.edu.xmu.software.ijoke.entity.Catalog;
import cn.edu.xmu.software.ijoke.service.CatalogManageService;
import cn.edu.xmu.software.ijoke.service.JokeInfoService;
import cn.edu.xmu.software.ijoke.utils.Consts;
import cn.edu.xmu.software.ijoke.utils.Messages;
import cn.edu.xmu.software.ijoke.view.Joke;

@SuppressWarnings("serial")
public class UnverifyListAction extends BaseAction {
	private JokeInfoService jokeInfoService;
	private CatalogManageService catalogManageService;
	private List<Joke> unverifyList;
	private String selectedJokeId;
	private int selectedJokeIndex;
	private Joke selectedJoke;
	private int index_start,index_end;
	private List<Catalog> catalogList;
	private String selectedCatalogId;
	
	public UnverifyListAction(){
		index_start=0;
		index_end=Consts.ONEPAGE_ITEM_NUM;
	}

	@Override
	public String execute() throws Exception {
		unverifyList=jokeInfoService.getWithoutVerifyJokes(index_start,index_end);		
		return SUCCESS;
	}
		
	public String loadJokeForVerify(){
		selectedJoke=unverifyList.get(selectedJokeIndex);
		catalogList=catalogManageService.getCatalogList(0, Consts.ONEPAGE_ITEM_NUM);
		System.out.println("Catalog num:"+catalogList.size());
//		for (Catalog catalog:catalogs){
//			catalogList.put(catalog.getCatalogId(),catalog.getCatalogName());
//		}	
		return SUCCESS;
	}		
	
	public String deleteJoke(){
		System.out.println("deleteJoke:"+selectedJokeId);		
		jokeInfoService.deleteJoke(selectedJokeId);
		return SUCCESS;
	}
	
	public String verifyJoke(){	
		System.out.println(selectedJoke.getTitle()+" "+selectedJoke.getAuthor()+" "+selectedJoke.getKeyWord());
		jokeInfoService.updateJoke(selectedJoke);
		System.out.println("addJokeToCatalog:"+selectedJoke.getId()+"->"+selectedCatalogId);
		jokeInfoService.addJokeToClass(selectedJoke.getId(), selectedCatalogId);
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
		if (unverifyList.size()== Consts.ONEPAGE_ITEM_NUM){
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

	public JokeInfoService getJokeInfoService() {
		return jokeInfoService;
	}

	public void setJokeInfoService(JokeInfoService jokeInfoService) {
		this.jokeInfoService = jokeInfoService;
	}

	public List<Joke> getUnverifyList() {
		return unverifyList;
	}

	public void setUnverifyList(List<Joke> unverifyList) {
		this.unverifyList = unverifyList;
	}

	public String getSelectedJokeId() {
		return selectedJokeId;
	}

	public void setSelectedJokeId(String selectedJokeId) {
		this.selectedJokeId = selectedJokeId;
	}

	public Joke getSelectedJoke() {
		return selectedJoke;
	}

	public void setSelectedJoke(Joke selectedJoke) {
		this.selectedJoke = selectedJoke;
	}

	public int getSelectedJokeIndex() {
		return selectedJokeIndex;
	}

	public void setSelectedJokeIndex(int selectedJokeIndex) {
		this.selectedJokeIndex = selectedJokeIndex;
	}
	
	
	

}
