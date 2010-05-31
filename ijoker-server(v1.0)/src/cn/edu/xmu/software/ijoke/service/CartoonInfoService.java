package cn.edu.xmu.software.ijoke.service;

import java.io.File;
import java.util.List;

import cn.edu.xmu.software.ijoke.entity.Cartoon;

public interface CartoonInfoService {
	public boolean uploadCartoonFiles(List<File> fileList, String userName,
			String cartoonTitle);
	public List<Cartoon> getVerifiedCartoonList( int begin, int pageSize);
	public List<Cartoon> getWithoutVerifiedCartoonList(int begin, int pageSize);
	public boolean updateCartoon(Cartoon cartoon);
	public boolean verify(Integer cartoonId);
	public boolean delete(Integer cartoonId);
	public boolean deleteCartoonFile(Integer cartoonFileId);
	public boolean deleteCartoonFile(String cartoonFileId);
	public List<Cartoon> getCartoonList(int begin, int pageSize);
	public Cartoon findCartoon(Integer cartoonId);
}