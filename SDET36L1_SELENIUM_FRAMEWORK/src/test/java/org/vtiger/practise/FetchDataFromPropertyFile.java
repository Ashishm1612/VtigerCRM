package org.vtiger.practise;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class FetchDataFromPropertyFile {

	public static void main(String[] args) throws IOException {
		FileInputStream fis= new FileInputStream("./src/test/resources/commonData.properties");
		Properties property=new Properties();
		property.load(fis);
		String data = property.getProperty("url");
		System.out.println(data);
		

	}

}
