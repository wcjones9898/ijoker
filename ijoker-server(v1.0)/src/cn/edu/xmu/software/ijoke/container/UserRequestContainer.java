package cn.edu.xmu.software.ijoke.container;

import java.util.ArrayList;

import org.junit.Test;

public class UserRequestContainer {

	public static ArrayList<UserRequest> userRequestList = new ArrayList<UserRequest>();
	public static final int LIST_BUFFER_SIZE = 20;
	public static Integer requestCount = 0;

	public static UserRequest check(String synchronizationTicket) {
		UserRequest userRequest = null;
		for (int i = 0; i < userRequestList.size(); i++) {
			if (userRequestList.get(i)!=null&&userRequestList.get(i).getSynchronizationTicket().equals(
					synchronizationTicket)) {
				userRequest = userRequestList.get(i);
				userRequestList.remove(i);
				requestCount--;
				return userRequest;
			}
		}
		return null;
	}

	public static String addToList(UserRequest userRequest) {
		if (requestCount <= LIST_BUFFER_SIZE) {
			userRequestList.add(userRequest);
			synchronized(requestCount)
			{
			   requestCount++;
			}
			return "OK";
		}
		return "Full";

	}
	
	@Test
	public void testCheck()
	{
		testAddToList();
		UserRequest userRequest =check("11111");
		System.out.println("asdf"+userRequest.getFileExtensition());
	}
	@Test 
	public void testAddToList()
	{
		UserRequest userRequest = new UserRequest();
		userRequest.setFileExtensition(".mp3");
		userRequest.setSynchronizationTicket("11111");
		userRequest.setFileName("dafdsaf");
		System.out.println(requestCount);
		System.out.println(addToList(userRequest));
	}
}
