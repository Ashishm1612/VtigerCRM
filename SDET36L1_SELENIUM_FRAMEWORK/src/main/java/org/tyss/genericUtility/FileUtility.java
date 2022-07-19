package org.tyss.genericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * This class contains reusable methods for csv file or propertyfile
 * @author Ashish
 *
 */
public class FileUtility {
	Properties property;
	
	public void initializePropertyFile(String filePath) {
		//to get the common data from property file
				FileInputStream fis;
				try {
					fis = new FileInputStream(filePath);
					 property=new Properties();
					property.load(fis);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				 catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
	}
	public String getDataFromProperty(String key) {
		return property.getProperty(key);
	}

	

}
