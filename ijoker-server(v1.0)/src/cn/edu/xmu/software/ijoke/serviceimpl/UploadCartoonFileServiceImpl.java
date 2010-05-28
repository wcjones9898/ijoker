package cn.edu.xmu.software.ijoke.serviceimpl;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.edu.xmu.software.ijoke.factory.ConfigFactory;

public class UploadCartoonFileServiceImpl extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String uploadPath = ConfigFactory.getJokeUploadPath(); // 上传文件的目录
	private String tempPath = ConfigFactory.getJokeUploadTempPath(); // 临时文件目录
	File tempPathFile;
	String fileExtension = null;
    String fileId = null;
    String filePath = null;
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	public void init(ServletConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	public void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	private String createFileName()
	{
		String fileName = null;
		Timestamp date = new Timestamp((new Date().getTime()));
	    fileName = date.toString().replace(" ","");
	    fileName = fileName.replace("-","");
	    fileName = fileName.replace(":","");
	    fileName = fileName.replace(".","");
	    return fileName;
	}
	private void uploadFile(HttpServletRequest request) {
		try {
			// Create a factory for disk-based file items
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// Set factory constraints
			factory.setSizeThreshold(4096); // 设置缓冲区大小，这里是4kb
			factory.setRepository(tempPathFile);// 设置缓冲区目录
			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			// Set overall request size constraint
			upload.setSizeMax(10000000); // 设置最大文件尺寸，这里是4MB

			List<FileItem> items = upload.parseRequest(request);// 得到所有的文件
			Iterator<FileItem> i = items.iterator();
			while (i.hasNext()) {
				FileItem fi = (FileItem) i.next();
				String fileName = fi.getName();

				if (fileName != null) {
					fileExtension = fi.getName().substring(
							fi.getName().indexOf("."));
					fileId = createFileName();
					File fullFile = new File(ConfigFactory.getJokePath()+fileId+fileExtension);
					
					File savedFile = new File(uploadPath, fullFile.getName());
					fi.write(savedFile);
				}
			}

			System.out.print("upload succeed");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// 可以跳转出错页面
			e.printStackTrace();
		}
	}


}
