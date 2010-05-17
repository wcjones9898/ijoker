package cn.edu.xmu.software.ijoke.action;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;

import cn.edu.xmu.software.ijoke.entity.JokeFile;
import cn.edu.xmu.software.ijoke.factory.ConfigFactory;
import cn.edu.xmu.software.ijoke.service.JokeInfoUploadService;
import cn.edu.xmu.software.ijoke.service.UploadJokeFileService;
import cn.edu.xmu.software.ijoke.view.Joke;
public class UploadJokeFileAction extends BaseAction {

	private File jokeFile;
	private String title,keyWord,username,fileExtension,fileName;
	private JokeInfoUploadService jokeInfoUploadService;
	
	
	
	public String execute()
	{
		username=(String)this.getSession().get("username");
		fileExtension=jokeFile.getName().substring(this.jokeFile.getName().indexOf("."));
		
		jokeInfoUploadService.jokeInfoUploadServiceByServer(title, keyWord, username, fileExtension, fileName);
		System.out.println("文件上传成功....");
		return SUCCESS;
	}

	private String createSynchronizationTicket() {
		String synchronizationTicket = null;
		Timestamp date = new Timestamp((new Date().getTime()));
		synchronizationTicket = date.toString().replace(" ", "");
		synchronizationTicket = synchronizationTicket.replace("-", "");
		synchronizationTicket = synchronizationTicket.replace(":", "");
		synchronizationTicket = synchronizationTicket.replace(".", "");
		return synchronizationTicket;
	}

	private String createFileId() {
		String fileName = null;
		Timestamp date = new Timestamp((new Date().getTime()));
		fileName = date.toString().replace(" ", "");
		fileName = fileName.replace("-", "");
		fileName = fileName.replace(":", "");
		fileName = fileName.replace(".", "");
		return fileName;
	}
	
	
	
	
	
	
	public File getJokeFile() {
		return jokeFile;
	}

	public void setJokeFile(File jokeFile) {
		this.jokeFile = jokeFile;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public JokeInfoUploadService getJokeInfoUploadService() {
		return jokeInfoUploadService;
	}

	public void setJokeInfoUploadService(JokeInfoUploadService jokeInfoUploadService) {
		this.jokeInfoUploadService = jokeInfoUploadService;
	}



	
}
