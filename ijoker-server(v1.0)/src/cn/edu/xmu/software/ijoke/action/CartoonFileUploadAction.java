package cn.edu.xmu.software.ijoke.action;

import java.io.File;
import java.io.IOException;

import org.apache.struts2.ServletActionContext;

import cn.edu.xmu.software.ijoke.service.JokeInfoUploadService;
import cn.edu.xmu.software.ijoke.utils.Consts;
import cn.edu.xmu.software.ijoke.utils.FileUtil;
public class CartoonFileUploadAction extends BaseAction {
	
	private File cartoonFile;	
	private String cartoonFileContentType;//获取上传文件的类型，注意上传类型变量命名方式
	private String cartoonFileFileName;//获取上传文件的名称
	private String fileSavePath;
	//设置上传文件的保存路径，利用struts2框架的设置注入。在struts.xml文件配置<param name = "fileSavePath"/>关键字
	private String title,keyword,username;
	private JokeInfoUploadService jokeInfoUploadService;
	
	public CartoonFileUploadAction(){
		fileSavePath= Consts.jokeUploadTempPath;	
	}
	
	public String execute() throws IOException
	{
		/*username=(String)this.getSession().get("username");		
        File tempFile = new File(fileSavePath + cartoonFileFileName);
        FileUtil.copy(cartoonFile, tempFile);
        
		jokeInfoUploadService.jokeInfoUploadServiceByServer(title, keyword, username,tempFile);
		System.out.println(username+" "+tempFile.getAbsolutePath());*/
		return SUCCESS;
	}

	
	
	

	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}		

	public File getCartoonFile() {
		return cartoonFile;
	}

	public void setCartoonFile(File cartoonFile) {
		this.cartoonFile = cartoonFile;
	}

	public String getCartoonFileContentType() {
		return cartoonFileContentType;
	}

	public void setCartoonFileContentType(String cartoonFileContentType) {
		this.cartoonFileContentType = cartoonFileContentType;
	}

	public String getCartoonFileFileName() {
		return cartoonFileFileName;
	}

	public void setCartoonFileFileName(String cartoonFileFileName) {
		this.cartoonFileFileName = cartoonFileFileName;
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

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public JokeInfoUploadService getJokeInfoUploadService() {
		return jokeInfoUploadService;
	}

	public void setJokeInfoUploadService(JokeInfoUploadService jokeInfoUploadService) {
		this.jokeInfoUploadService = jokeInfoUploadService;
	}



	
}
