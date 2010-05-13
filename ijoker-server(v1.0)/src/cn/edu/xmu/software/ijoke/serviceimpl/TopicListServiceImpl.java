package cn.edu.xmu.software.ijoke.serviceimpl;

import java.util.ArrayList;

import cn.edu.xmu.software.ijoke.dao.ClassItemDAO;
import cn.edu.xmu.software.ijoke.service.TopicListService;

public class TopicListServiceImpl implements TopicListService{

	private ClassItemDAO classItemDAO = new ClassItemDAO();
	public ArrayList topicListService() {
		// TODO Auto-generated method stub
		
		return classItemDAO.findAll();
	}
	

}
