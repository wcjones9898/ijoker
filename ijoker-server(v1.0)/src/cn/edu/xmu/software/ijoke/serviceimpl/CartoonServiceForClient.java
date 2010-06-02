package cn.edu.xmu.software.ijoke.serviceimpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import cn.edu.xmu.software.ijoke.service.CartoonInfoService;
import cn.edu.xmu.software.ijoke.entity.Cartoon;
import cn.edu.xmu.software.ijoke.entity.CartoonFile;
import cn.edu.xmu.software.ijoke.factory.AppFactory;
public class CartoonServiceForClient {

	private CartoonInfoService cartooInfoService = AppFactory.getCartoonInfoService();
	public List<String> cartoonService(int cartoonId)
	{
		List<Cartoon> cartoonList = cartooInfoService.getCartoonList(0, 5);
		Cartoon cartoon = cartoonList.get((int)(Math.random()*cartoonList.size()+1)-1);
		//Cartoon cartoon = cartooInfoService.findCartoon(cartoonId);
		List filesName = new ArrayList<String>();
		Iterator it = cartoon.getCartoonFiles().iterator();
		while(it.hasNext())
		{
			CartoonFile cartoonFile = (CartoonFile)it.next();
			filesName.add(cartoonFile.getFilePath()+cartoonFile.getFileName()+cartoonFile.getFileExtension());
		}

		return filesName;
	}
	@Test
	public void testCartoonService()
	{
		List cartoonList = cartoonService(2);
		for(int i=0; i<cartoonList.size(); i++)
			System.out.println(cartoonList.get(i));
	}
}
