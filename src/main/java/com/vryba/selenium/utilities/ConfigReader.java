package com.vryba.selenium.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	static Properties prop;
	public static void main(String[] args) throws IOException {
		//Create Object of Properties Class
		prop = new Properties();
		//Use System.getProperty to get the relative path of file in Workspace.
		// Now file path is machine independent.
		String path = System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties";
		System.out.println("Actual Location of File -> " + path);
		//Create FileInputStream object of Config/data file
		FileInputStream fis= new FileInputStream(path);
		// Pass fs object to load method of Properties object
		prop.load(fis);
		// Use getProperty method of Properties object to get the values.
		System.out.println(prop.getProperty("FFDriverPath"));
		System.out.println(prop.getProperty("ieDriverPath"));
	}
	public String getChromePath() {
		String path = prop.getProperty("chromeDriverPath");
		return path;
	}
	public String getAppUrl() {
		return prop.getProperty("URL");
	}
}

