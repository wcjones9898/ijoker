package cn.edu.xmu.software.ijoke.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;

public class ConfigFactory {

	  public static String getJokePath()
	  {
		 
		    
	        Properties   properties   =   new   Properties();  
	        InputStream iFile = null;
			try {
				//iFile = new   FileInputStream(ConfigFactory.class.getResource("").toString()+"config.ini");
				iFile = new   FileInputStream("D:/config.ini");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	        try {
				properties.load(iFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	        String   filePath   =   properties.getProperty("jokepath")   ;   
	        return filePath;
	  }
	 @Test
	 public void testGetJokePath()
	 {
		 getJokePath();
	 }
	  
}
