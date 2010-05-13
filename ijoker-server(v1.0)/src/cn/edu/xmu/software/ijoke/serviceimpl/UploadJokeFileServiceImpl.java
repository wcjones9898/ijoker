package cn.edu.xmu.software.ijoke.serviceimpl;

import org.junit.Test;

import cn.edu.xmu.software.ijoke.dao.JokeFileDAO;
import cn.edu.xmu.software.ijoke.entity.JokeFile;
import cn.edu.xmu.software.ijoke.factory.ConfigFactory;
import cn.edu.xmu.software.ijoke.service.UploadJokeFileService;

public class UploadJokeFileServiceImpl implements UploadJokeFileService{

	private JokeFileDAO jokeFileDAO;
	
	public JokeFileDAO getJokeFileDAO() {
		return jokeFileDAO;
	}

	public void setJokeFileDAO(JokeFileDAO jokeFileDAO) {
		this.jokeFileDAO = jokeFileDAO;
	}

	public void UploadJokeFileServiceImpl()
	{
		System.out.println("上传文件服务......");
	}

	public String insertJokeFile(String fileExtension, String filePath,
			String fileId) {
		// TODO Auto-generated method stub
		try{
		JokeFile jokeFile = new JokeFile();
		jokeFile.setFileExtension(fileExtension);
		jokeFile.setFilePath(ConfigFactory.getJokePath());
		jokeFile.setFileName(fileId);
		jokeFile.setFileId(fileId);
        jokeFileDAO.insertJokeFile(jokeFile);
        return "ok";
		}
		catch(Exception e)
		{
			System.out.println("插入上传文件信息出错");
			return "error";
		}
	}

}
