package cn.edu.xmu.software.ijoke.action;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;

import cn.edu.xmu.software.ijoke.entity.JokeFile;
import cn.edu.xmu.software.ijoke.factory.ConfigFactory;
import cn.edu.xmu.software.ijoke.service.UploadJokeFileService;
import cn.edu.xmu.software.ijoke.view.Joke;
public class UploadJokeFileAction extends BaseAction {

	private File jokeFile = null;
	private String title = null;
	private String keyWord = null;
	private String userId = null;
	UploadJokeFileService uploadJokeFileService;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String execute()
	{
		System.out.println("文件上传成功....");
//	    JokeFile jokeFile = new JokeFile();
//	    jokeFile.setFileExtension(this.jokeFile.getName().substring(this.jokeFile.getName().indexOf(".")));
//	    String fileId = createFileId();
//	    jokeFile.setFileName(fileId);
//	    jokeFile.setFileId(fileId);
//	    Joke joke = new Joke();
//	   // joke.setAuthor(author)
//	    
//	    return  uploadJokeFileService.insertJokeFile(
//	    		jokeFile.getFileExtension(), ConfigFactory.getJokePath(), fileId);
		return null;
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
}
