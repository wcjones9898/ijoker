package cn.edu.xmu.software.ijoke.serviceimpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.edu.xmu.software.ijoke.service.CartoonInfoService;
import cn.edu.xmu.software.ijoke.entity.Cartoon;
import cn.edu.xmu.software.ijoke.entity.CartoonFile;
import cn.edu.xmu.software.ijoke.factory.AppFactory;
public class CartoonServiceForClient {

	private CartoonInfoService cartooInfoService = AppFactory.getCartoonInfoService();
	public List<String> cartoonService(int cartoonId)
	{
		System.out.println("this is in cartoon service");
		Cartoon cartoon = cartooInfoService.findCartoon(cartoonId);
		List filesName = new ArrayList<String>();
		Iterator it = cartoon.getCartoonFiles().iterator();
		while(it.hasNext())
		{
			filesName.add(((CartoonFile)it.next()).getFileName());
		}
		return filesName;
	}
}
