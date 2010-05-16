package cn.edu.xmu.software.ijoke.serviceimpl;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.edu.xmu.software.ijoke.container.UserRequest;
import cn.edu.xmu.software.ijoke.container.UserRequestContainer;
import cn.edu.xmu.software.ijoke.factory.ConfigFactory;
import cn.edu.xmu.software.ijoke.service.JokeInfoUploadService;
import cn.edu.xmu.software.ijoke.utils.Consts;
public class UploadFileServiceImpl extends HttpServlet {

	private String uploadPath = ConfigFactory.getJokePath(); // 上传文件的目录
	private String tempPath = ConfigFactory.getJokeTempPath(); // 临时文件目录
	File tempPathFile;
	int i = 1;
	String title = null;
	String userId = null;
	String keyWord = null;
	String titleGBK = null;
	String userIdGBK = null;
	String keyWordGBK = null;
	String fileExtension = null;
    String fileId = null;
    String filePath = null;
    int fileLength = 0 ;
    int fileTime = 0;
    String synchronizationTicket = null;
	public void init() throws ServletException {
		System.out.println("UploadFileService Servlet");
		File uploadFile = new File(uploadPath);
		if (!uploadFile.exists()) {
			uploadFile.mkdirs();
		}
		File tempPathFile = new File(tempPath);
		if (!tempPathFile.exists()) {
			tempPathFile.mkdirs();
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter pw;
		try {
			pw = response.getWriter();
			pw.write(createSynchronizationTicket());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void uploadFileInfo(HttpServletRequest request) {
		{
			try {
				if ((title = request.getParameter("title")) != null) {
					titleGBK = new String(title.getBytes("ISO-8859-1"), "gbk");
					System.out.println("titleGBK = " + titleGBK);
				}
				if ((userId = request.getParameter("userId")) != null) {
					userIdGBK = new String(userId.getBytes("ISO-8859-1"), "gbk");
					System.out.println("userIdGBK = " + userIdGBK);
				}
				if ((keyWord = request.getParameter("keyWord")) != null) {
					keyWordGBK = new String(keyWord.getBytes("ISO-8859-1"),
							"gbk");
					System.out.println("userIdGBK = " + userIdGBK);
				}
				synchronizationTicket = request.getParameter("synchronizationTicket");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public void uploadFile(HttpServletRequest request) {
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

	private String createSynchronizationTicket ()
	{
		String synchronizationTicket = null;
		Timestamp date = new Timestamp((new Date().getTime()));
		synchronizationTicket = date.toString().replace(" ","");
		synchronizationTicket = synchronizationTicket.replace("-","");
		synchronizationTicket = synchronizationTicket.replace(":","");
		synchronizationTicket = synchronizationTicket.replace(".","");
	    return synchronizationTicket;
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
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("系统路径为"+ConfigFactory.getJokePath());
		System.out.println("上传服务启动.....");
		System.out.println(request.getHeader("Content-Type"));

		if (request.getHeader("Content-Type") != null
				&& request.getHeader("Content-Type").indexOf(
						"multipart/form-data") >= 0) {
			uploadFile(request);
			PrintWriter pw;
			try {
				pw = response.getWriter();
				this.synchronizationTicket = createSynchronizationTicket();
				pw.write(synchronizationTicket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			UserRequest userRequest = new UserRequest();
			userRequest.setFileName(fileId);
			userRequest.setFileExtensition(fileExtension);
			userRequest.setSynchronizationTicket(synchronizationTicket);
			UserRequestContainer.addToList(userRequest);

		} 
		else {
			UserRequest userRequest = UserRequestContainer.check(synchronizationTicket);
			if( userRequest!=null)
			{
			    System.out.println("上传成功，开始录入笑话信息");
			    uploadFileInfo(request);
			    JokeInfoUploadService jokeInfoUploadService = new JokeInfoUploadServiceImpl();
			    jokeInfoUploadService.jokeInfoUploadService(titleGBK, keyWordGBK,
					userIdGBK, userRequest.getFileExtensition(),userRequest.getFileName());
			}
		}

	}

}
