package cn.edu.xmu.software.ijoke.action;

import java.util.List;

import cn.edu.xmu.software.ijoke.entity.User;
import cn.edu.xmu.software.ijoke.service.JokeInfoService;
import cn.edu.xmu.software.ijoke.service.UserService;
import cn.edu.xmu.software.ijoke.utils.Consts;
import cn.edu.xmu.software.ijoke.utils.Messages;
import cn.edu.xmu.software.ijoke.view.Joke;

@SuppressWarnings("serial")
public class UserListAction extends BaseAction {
	private UserService userService;
	private List<User> userList;
	private String selectedUserId;
	private int selectedUserIndex;
	private User selectedUser;
	private int index_start,index_end;
	
	public UserListAction(){
		index_start=0;
		index_end=Consts.ONEPAGE_ITEM_NUM;
	}

	@Override
	public String execute() throws Exception {
		
		userList=userService.getUserWithOutVerify(index_start, index_end);
		System.out.println("getUserVerified:"+userList.size());
		return SUCCESS;
	}	
	
	public String loadLockedUser(){
		userList=userService.getUserVerified(index_start, index_end);	
		return SUCCESS;
	}
	
	public String loadUserForDetail(){
		selectedUser=userList.get(selectedUserIndex);
		return SUCCESS;
	}
	
	public String unlockUser(){	
		
		System.out.println("unlocking user:"+selectedUser.getUserName());
		
		return SUCCESS;
	}
	
	public String lockUser(){
		System.out.println("locking user:"+selectedUser.getUserName());		
		
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
		if (userList.size()== Consts.ONEPAGE_ITEM_NUM){
			index_start+=Consts.ONEPAGE_ITEM_NUM;
			index_start++;
			index_end+=Consts.ONEPAGE_ITEM_NUM;
			index_end++;		
		} else {
			this.addActionMessage(Messages.LAST_PAGE);	
		}
		return SUCCESS;
	}

	
	
	
	
	
	
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public String getSelectedUserId() {
		return selectedUserId;
	}

	public void setSelectedUserId(String selectedUserId) {
		this.selectedUserId = selectedUserId;
	}

	public int getSelectedUserIndex() {
		return selectedUserIndex;
	}

	public void setSelectedUserIndex(int selectedUserIndex) {
		this.selectedUserIndex = selectedUserIndex;
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}
}
