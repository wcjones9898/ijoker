package cn.edu.xmu.software.ijoke.serviceimpl;

import java.util.ArrayList;

import org.jgroups.util.List;
import org.junit.Test;

import cn.edu.xmu.software.ijoke.dao.CatalogDAO;
import cn.edu.xmu.software.ijoke.dao.ClassItemDAO;
import cn.edu.xmu.software.ijoke.service.TopicListService;
import cn.edu.xmu.software.ijoke.entity.Catalog;
public class TopicListServiceImpl implements TopicListService{

	private CatalogDAO catalogDAO = new CatalogDAO();
	public ArrayList topicListService() {
		// TODO Auto-generated method stub
		
		return catalogDAO.findAll();
	}
	@Test
	public void testTopicListService()
	{
		ArrayList<Catalog> topicList = topicListService();
		for(int i=0 ; i<topicList.size(); i++)
			System.out.println(topicList.get(i).getCatalogId());
	}

}
