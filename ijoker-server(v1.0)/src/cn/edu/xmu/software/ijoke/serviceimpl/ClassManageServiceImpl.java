package cn.edu.xmu.software.ijoke.serviceimpl;

import org.junit.Test;

import cn.edu.xmu.software.ijoke.dao.ClassItemDAO;
import cn.edu.xmu.software.ijoke.entity.ClassItem;
import cn.edu.xmu.software.ijoke.service.ClassManageService;
import cn.edu.xmu.software.ijoke.factory.AppFactory;
import cn.edu.xmu.software.ijoke.utils.Consts;
public class ClassManageServiceImpl implements ClassManageService{

	private ClassItemDAO classItemDAO;
	
	public ClassItemDAO getClassItemDAO() {
		return classItemDAO;
	}

	public void setClassItemDAO(ClassItemDAO classItemDAO) {
		this.classItemDAO = classItemDAO;
	}

	public String addClassItem(String classId, String className) {
		// TODO Auto-generated method stub
		try{
		ClassItem classItem = new ClassItem();
		classItem.setClassId(classId);
		classItem.setClassName(className);
		classItem.setJokeNum(0);
		classItem.setClassLevel(1);
		classItemDAO.save(classItem);
		return Consts.CLASS_ADD_SUCCESS;
		}catch(Exception e)
		{
			e.printStackTrace();
			return Consts.CLASS_ADD_FAIL;
		}
	}

	public String deleteClassItem(String classId) {
		// TODO Auto-generated method stub
		try{
		classItemDAO.delete(classItemDAO.findClassItemByClassId(classId));
		return Consts.CLASS_DELETE_SUCCESS;
		}catch(Exception e)
		{
			e.printStackTrace();
			return Consts.CLASS_DELETE_FAIL;
		}
	}

	public String updateClassItem(String classId, String className) {
		// TODO Auto-generated method stub
		try{
		ClassItem classItem = classItemDAO.findClassItemByClassId(classId);
		if(classItem!=null)
		{
			classItem.setClassName(className);
			classItemDAO.update(classItem);
			return Consts.CLASS_UPDATE_SUCCESS;
		}
		}catch(Exception e)
		{
			e.printStackTrace();
			return Consts.CLASS_UPDATE_FAIL;
		}
		return Consts.CLASS_UPDATE_FAIL;
	}

	@Test
	public void testAddClassItem()
   {
		AppFactory.getClassManageService().addClassItem("5", "拉拉");
	}
//	@Test
//	public void testDeleteClassItem()
//	   {
//			AppFactory.getClassManageService().deleteClassItem("5");
//		}
	@Test
	public void testUpdateClassItem()
	   {
			AppFactory.getClassManageService().updateClassItem("5","便便");
		}
}