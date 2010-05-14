package cn.edu.xmu.software.ijoke.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;

public class ConfigFactory {

	public static Properties getConfigProperties() throws IOException {
		Properties properties = new Properties();
		InputStream iFile = null;
		try {
			// iFile = new
			// FileInputStream(ConfigFactory.class.getResource("").toString()+"config.ini");
			iFile = new FileInputStream(ConfigFactory.class.getResource("")
					.toString()
					+ "/config.properties");
			properties.load(iFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return properties;

	}

	public static String getJokePath() {
		String filePath = null;
		try {
			filePath = getConfigProperties().getProperty("jokeRootPath");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filePath;
	}

	public static String getJokeTempPath()
	{
		String filePath = null;
		try {
			filePath = getConfigProperties().getProperty("jokeTempPath");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filePath;
	}
	
	public static String getSearchIndexPath()
	{
		String filePath = null;
		try {
			filePath = getConfigProperties().getProperty("searchIndexPath");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filePath;
	}
	@Test
	public void testGetJokePath() {
		System.out.print(getJokePath());
	}

}
