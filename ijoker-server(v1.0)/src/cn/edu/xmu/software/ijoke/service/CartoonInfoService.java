package cn.edu.xmu.software.ijoke.service;

import java.io.File;
import java.util.List;

import cn.edu.xmu.software.ijoke.entity.Cartoon;

public interface CartoonInfoService {
	public boolean uploadCartoonFiles(List<File> fileList, String userId,
			String cartoonTitle);
	public List<Cartoon> getVerifiedCartoonList( int begin, int pageSize);
	public List<Cartoon> getWithoutVerifiedCartoonList(int begin, int pageSize);
}
