package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationRead {
	
	
	public String readProperties(String key) throws IOException
	{
	String value= null;
	String projectPath=	System.getProperty("user.dir");
	File textFile= new File(projectPath+"\\src\\test\\resources\\config.properties");
	FileInputStream fis;
	
		fis = new FileInputStream(textFile);
		Properties prop = new Properties();
		prop.load(fis);
		value=prop.getProperty(key);
		return value;

	}

}
