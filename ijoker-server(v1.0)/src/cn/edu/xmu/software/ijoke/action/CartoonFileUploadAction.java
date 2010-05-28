package cn.edu.xmu.software.ijoke.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.xmu.software.ijoke.service.CartoonInfoService;
import cn.edu.xmu.software.ijoke.utils.Consts;
import cn.edu.xmu.software.ijoke.utils.FileUtil;

@SuppressWarnings("serial")
public class CartoonFileUploadAction extends BaseAction {
	
	private List<File> cartoon;	
	private List<String> cartoonContentType;//获取上传文件的类型，注意上传类型变量命名方式
	private List<String> cartoonFileName;//获取上传文件的名称
	private String fileSavePath;
	//设置上传文件的保存路径，利用struts2框架的设置注入。在struts.xml文件配置<param name = "fileSavePath"/>关键字
	private String title,username;
	private CartoonInfoService cartoonInfoService ;
		
	public CartoonFileUploadAction(){
		fileSavePath= Consts.jokeUploadTempPath;	
	}
	public String execute() throws IOException
	{
		System.out.println(cartoon.size());
		username=(String)this.getSession().get("username");	
		
		for (int i=0;i<=cartoon.size()-1;i++){
			File tempFile= new File(fileSavePath + cartoonFileName.get(i));
	        FileUtil.copy(cartoon.get(i), tempFile);
			System.out.println(tempFile.getAbsolutePath());
			cartoon.set(i, tempFile);
		}
		cartoonInfoService.uploadCartoonFiles(cartoon, username, title);					
		return SUCCESS;
	}
	
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}		

	public List<File> getCartoon() {
		return cartoon;
	}

	public void setCartoon(List<File> cartoon) {
		this.cartoon = cartoon;
	}

	public List<String> getCartoonContentType() {
		return cartoonContentType;
	}

	public void setCartoonContentType(List<String> cartoonContentType) {
		this.cartoonContentType = cartoonContentType;
	}

	public List<String> getCartoonFileName() {
		return cartoonFileName;
	}

	public void setCartoonFileName(List<String> cartoonFileName) {
		this.cartoonFileName = cartoonFileName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFileSavePath() {
		return fileSavePath;
	}
	public void setFileSavePath(String fileSavePath) {
		this.fileSavePath = fileSavePath;
	}

	public CartoonInfoService getCartoonInfoService() {
		return cartoonInfoService;
	}
	
	public void setCartoonInfoService(CartoonInfoService cartoonInfoService) {
		this.cartoonInfoService = cartoonInfoService;
	}


	
}
