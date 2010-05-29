package cn.edu.xmu.software.ijoke.serviceimpl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Transaction;
import org.junit.Test;

import cn.edu.xmu.software.ijoke.dao.CartoonDAO;
import cn.edu.xmu.software.ijoke.dao.CartoonFileDAO;
import cn.edu.xmu.software.ijoke.dao.IjokerAdminDAO;
import cn.edu.xmu.software.ijoke.dao.UserDAO;
import cn.edu.xmu.software.ijoke.entity.Cartoon;
import cn.edu.xmu.software.ijoke.entity.CartoonFile;
import cn.edu.xmu.software.ijoke.entity.IjokerAdmin;
import cn.edu.xmu.software.ijoke.factory.IdFactroy;
import cn.edu.xmu.software.ijoke.factory.AppFactory;
import cn.edu.xmu.software.ijoke.service.CartoonInfoService;

public class CartoonInfoServiceImpl implements CartoonInfoService{

	private CartoonDAO cartoonDAO;
	private CartoonFileDAO cartoonFileDAO;
	private IjokerAdminDAO userDAO;
	public CartoonDAO getCartoonDAO() {
		return cartoonDAO;
	}

	public void setCartoonDAO(CartoonDAO cartoonDAO) {
		this.cartoonDAO = cartoonDAO;
	}

	public CartoonFileDAO getCartoonFileDAO() {
		return cartoonFileDAO;
	}

	public void setCartoonFileDAO(CartoonFileDAO cartoonFileDAO) {
		this.cartoonFileDAO = cartoonFileDAO;
	}

	public boolean uploadCartoonFiles(List<File> fileList, String userName,String cartoonTitle) {
		// TODO Auto-generated method stub
		File tempFile = null;
		try{
		for(int i=0;i<fileList.size();i++)
		{
			String fileId = IdFactroy.createId();
			tempFile = fileList.get(i);
			CartoonFile cartoonFile = new CartoonFile();
			cartoonFile.setFileExtension(tempFile.getName().substring(tempFile.getName().indexOf(".")));
		    cartoonFile.setFileName(fileId);
		    cartoonFile.setFileId(fileId);
		    
		    Cartoon cartoon = new Cartoon();
		    cartoon.setAuthorName("");
		    cartoon.setCartoonId(fileId);
		    cartoon.setFileId(fileId);
		    cartoon.setCartoonTitle(cartoonTitle);
		    IjokerAdmin user = (IjokerAdmin) userDAO.findByAdminName(userName).get(0);
		    if(user != null)
		    {
		    cartoon.setUploaderId(user.getAdminId());
		    cartoon.setAuthorName(userName);
		    }
		    SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm");
		    cartoon.setUploadTime(formatter.format(new Date()));
		    cartoon.setStatus(0);
		    cartoonFileDAO.save(cartoonFile);
		    cartoonDAO.save(cartoon);
		
		}
		
		return true;
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public List<Cartoon> getVerifiedCartoonList(int begin, int pageSize) {
		// TODO Auto-generated method stub
		return cartoonDAO.findByStatus(1, begin, pageSize);
	}

	public List<Cartoon> getWithoutVerifiedCartoonList(int begin, int pageSize) {
		// TODO Auto-generated method stub
		return cartoonDAO.findByStatus(0, begin, pageSize);
	}
	public List<Cartoon> getCartoonList(int begin, int pageSize)
	{
		return cartoonDAO.findAll(begin, pageSize);
	}
	public boolean updateCartoon(Cartoon cartoon) {
		// TODO Auto-generated method stub
		try{
		cartoonDAO.save(cartoon);
		return true;
		}catch(Exception e)
		{
			return false;
		}
	}

	public boolean verify(String cartoonId) {
		// TODO Auto-generated method stub\
		try{
		Cartoon cartoon = (Cartoon) cartoonDAO.findByCartoonId(cartoonId).get(0);
		cartoon.setStatus(1);
		cartoonDAO.update(cartoon);
		return true;
		}catch(Exception e) 
		{
			return false;
		}
	}
	public boolean delete(String cartoonId) {
		// TODO Auto-generated method stub
		try{
			Cartoon cartoon = (Cartoon) cartoonDAO.findByCartoonId(cartoonId).get(0);

			cartoonDAO.delete(cartoon);
			return true;
			}catch(Exception e) 
			{
				return false;
			}
	}
//	@Test
//	public void testVerify()
//	{
//		System.out.println(AppFactory.getCartoonInfoService().verify("20100528132405631"));
//	}
	@Test
	public void testDelete()
	{
		System.out.println(AppFactory.getCartoonInfoService().delete("20100528132405631"));
	}
//	@Test 
//	public void testUploadCartoonFiles()
//	{
//		ArrayList<File> fileList = new ArrayList();
//		File file1 = new File("D:/1.jpg");
//		File file2 = new File("D:/2.jpg");
//		fileList.add(file1);
//		fileList.add(file2);
//		
//		System.out.println(AppFactory.getCartoonInfoService().uploadCartoonFiles(fileList,"1","风景"));
//		
//	}
//	@Test
//	public void testGetCartoonList()
//	{
//		ArrayList<Cartoon> fileList = (ArrayList<Cartoon>) AppFactory.getCartoonInfoService().getVerifiedCartoonList(0, 5);
//		for(int i=0; i<fileList.size(); i++)
//		System.out.println(fileList.get(i).getAuthorName());
//	}
//
//	@Test
//	public void testGetWithoutVerifiedCartoonList()
//	{
//		ArrayList<Cartoon> fileList = (ArrayList<Cartoon>) AppFactory.getCartoonInfoService().getWithoutVerifiedCartoonList(0, 5);
//		for(int i=0; i<fileList.size(); i++)
//		System.out.println(fileList.get(i).getCartoonId());
//	}





}
