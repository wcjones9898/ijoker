package cn.edu.xmu.software.ijoke.action;

import java.util.List;
import java.util.Set;

import cn.edu.xmu.software.ijoke.entity.Cartoon;
import cn.edu.xmu.software.ijoke.entity.CartoonFile;
import cn.edu.xmu.software.ijoke.service.CartoonInfoService;
import cn.edu.xmu.software.ijoke.utils.Consts;
import cn.edu.xmu.software.ijoke.utils.Messages;

@SuppressWarnings("serial")
public class CartoonListAction extends BaseAction {
	private CartoonInfoService cartoonInfoService;
	private List<Cartoon> cartoonList;
	private int selectedCartoonId;
	private int selectedCartoonIndex;
	private Cartoon selectedCartoon;
	private Set<CartoonFile> selectedCartoonFiles;
	private int index_start,index_end;
	
	public CartoonListAction(){
		index_start=0;
		index_end=Consts.ONEPAGE_ITEM_NUM;
	}

	@Override
	public String execute(){
		cartoonList=cartoonInfoService.getWithoutVerifiedCartoonList(index_start,index_end);			
		return SUCCESS;
	}	
	
	@SuppressWarnings("unchecked")
	public String loadCartoonForModify(){
		selectedCartoon=cartoonList.get(selectedCartoonIndex);
		selectedCartoonFiles=selectedCartoon.getCartoonFiles();	
		
		System.out.println(selectedCartoonFiles.size());
		
		return SUCCESS;
	}
	
	public String modifyCartoon(){	
		System.out.println(selectedCartoon.getCartoonTitle()+" "+selectedCartoon.getAuthorName());
		cartoonInfoService.updateCartoon(selectedCartoon);
		return SUCCESS;
	}
	
	public String deleteCartoon(){
		System.out.println("deleteCartoon:"+selectedCartoonId);		
		cartoonInfoService.delete(selectedCartoonId);
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
		if (cartoonList.size()== Consts.ONEPAGE_ITEM_NUM){
			index_start+=Consts.ONEPAGE_ITEM_NUM;
			index_start++;
			index_end+=Consts.ONEPAGE_ITEM_NUM;
			index_end++;		
		} else {
			this.addActionMessage(Messages.LAST_PAGE);	
		}
		return SUCCESS;
	}

	
	
	
	
	public CartoonInfoService getCartoonInfoService() {
		return cartoonInfoService;
	}

	public void setCartoonInfoService(CartoonInfoService cartoonInfoService) {
		this.cartoonInfoService = cartoonInfoService;
	}

	public List<Cartoon> getCartoonList() {
		return cartoonList;
	}

	public void setCartoonList(List<Cartoon> cartoonList) {
		this.cartoonList = cartoonList;
	}

	public int getSelectedCartoonId() {
		return selectedCartoonId;
	}

	public void setSelectedCartoonId(int selectedCartoonId) {
		this.selectedCartoonId = selectedCartoonId;
	}

	public int getSelectedCartoonIndex() {
		return selectedCartoonIndex;
	}

	public void setSelectedCartoonIndex(int selectedCartoonIndex) {
		this.selectedCartoonIndex = selectedCartoonIndex;
	}

	public Cartoon getSelectedCartoon() {
		return selectedCartoon;
	}

	public void setSelectedCartoon(Cartoon selectedCartoon) {
		this.selectedCartoon = selectedCartoon;
	}

	public int getIndex_start() {
		return index_start;
	}

	public void setIndex_start(int index_start) {
		this.index_start = index_start;
	}

	public int getIndex_end() {
		return index_end;
	}

	public void setIndex_end(int index_end) {
		this.index_end = index_end;
	}

	public Set<CartoonFile> getSelectedCartoonFiles() {
		return selectedCartoonFiles;
	}

	public void setSelectedCartoonFiles(Set<CartoonFile> selectedCartoonFiles) {
		this.selectedCartoonFiles = selectedCartoonFiles;
	}
	
}
