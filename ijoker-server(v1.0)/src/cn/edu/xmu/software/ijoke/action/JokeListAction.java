package cn.edu.xmu.software.ijoke.action;

import java.util.List;

import cn.edu.xmu.software.ijoke.entity.Catalog;
import cn.edu.xmu.software.ijoke.service.CatalogManageService;
import cn.edu.xmu.software.ijoke.service.JokeInfoService;
import cn.edu.xmu.software.ijoke.utils.Consts;
import cn.edu.xmu.software.ijoke.utils.Messages;
import cn.edu.xmu.software.ijoke.view.Joke;

@SuppressWarnings("serial")
public class JokeListAction extends BaseAction {
	private JokeInfoService jokeInfoService;
	private CatalogManageService catalogManageService;
	private List<Joke> jokeList;
	private String selectedJokeId;
	private int selectedJokeIndex;
	private Joke selectedJoke;
	private int index_start,index_end;
	private List<Catalog> catalogList;
	private String selectedCatalogId;
	
	public JokeListAction(){
		index_start=0;
		index_end=Consts.ONEPAGE_ITEM_NUM;
	}

	@Override
	public String execute(){
		jokeList=jokeInfoService.getVerifiedJokes(index_start,index_end);			
		return SUCCESS;
	}	
	
	public String loadJokeForModify(){
		selectedJoke=jokeList.get(selectedJokeIndex);
		catalogList=catalogManageService.getCatalogList(0, Consts.ONEPAGE_ITEM_NUM);
		return SUCCESS;
	}
	
	public String modifyJoke(){	
		System.out.println(selectedJoke.getTitle()+" "+selectedJoke.getAuthor()+" "+selectedJoke.getKeyWord());
		jokeInfoService.updateJoke(selectedJoke);
		jokeInfoService.deleteJokeToClass(selectedJoke.getId(), classId);
		jokeInfoService.addJokeToClass(selectedJoke.getId(), selectedCatalogId);
		return SUCCESS;
	}
	
	public String deleteJoke(){
		System.out.println("deleteJoke:"+selectedJokeId);		
		jokeInfoService.deleteJoke(selectedJokeId);
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
		if (jokeList.size()== Consts.ONEPAGE_ITEM_NUM){
			index_start+=Consts.ONEPAGE_ITEM_NUM;
			index_start++;
			index_end+=Consts.ONEPAGE_ITEM_NUM;
			index_end++;		
		} else {
			this.addActionMessage(Messages.LAST_PAGE);	
		}
		return SUCCESS;
	}
	
	
	
	
	
	
	
	public JokeInfoService getJokeInfoService() {
		return jokeInfoService;
	}

	public void setJokeInfoService(JokeInfoService jokeInfoService) {
		this.jokeInfoService = jokeInfoService;
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

	public List<Joke> getJokeList() {
		return jokeList;
	}

	public void setJokeList(List<Joke> jokeList) {
		this.jokeList = jokeList;
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
	
	
	
	
	
	

}
