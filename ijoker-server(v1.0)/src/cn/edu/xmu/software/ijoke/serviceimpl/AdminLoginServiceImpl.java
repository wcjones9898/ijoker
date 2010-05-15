package cn.edu.xmu.software.ijoke.serviceimpl;

import java.util.List;

import org.junit.Test;

import cn.edu.xmu.software.ijoke.dao.IjokerAdminDAO;
import cn.edu.xmu.software.ijoke.entity.IjokerAdmin;
import cn.edu.xmu.software.ijoke.service.AdminLoginService;
import cn.edu.xmu.software.ijoke.utils.Consts;
import cn.edu.xmu.software.ijoke.factory.AppFactory;
public class AdminLoginServiceImpl implements AdminLoginService{

	private IjokerAdminDAO ijokerAdminDAO;
	
	public IjokerAdminDAO getIjokerAdminDAO() {
		return ijokerAdminDAO;
	}

	public void setIjokerAdminDAO(IjokerAdminDAO ijokerAdminDAO) {
		this.ijokerAdminDAO = ijokerAdminDAO;
	}

	public IjokerAdmin adminLogin(String userName, String passWord) {
		// TODO Auto-generated method stub
		return null;
	}

	public String login(String userName ,String passWord)
	{
		List<IjokerAdmin> adminList = ijokerAdminDAO.findByAdminName(userName);
	
		if(adminList.size()==0||adminList.get(0) == null ||
				!adminList.get(0).getPassWord().equals(passWord))
			return Consts.LOGIN_FAIL;
		else
			return Consts.LOGIN_SUCCESS;
	}
	@Test
	public void testAdminLoginService()
	{
		
		System.out.println(AppFactory.getAdminLoginService().login("admin","123"));
		System.out.println(AppFactory.getAdminLoginService().login("admin0","123"));
		System.out.println(AppFactory.getAdminLoginService().login("admin",""));
		
	}
}
