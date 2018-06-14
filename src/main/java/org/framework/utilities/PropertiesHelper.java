package org.framework.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import org.framework.Base;

/**
 * This class handles all Properties file related operations
 */

public class PropertiesHelper extends Base {
	
	public static String readProperties(String key) {
		Properties prop = new Properties();
		
		try {
			File file = new File(configPropertiesPath);
			FileInputStream fis = new FileInputStream(file);

			prop.load(fis);

		} catch (Exception e) {
			e.printStackTrace();
			Log4j.info(e.getMessage());
		}
		return prop.getProperty(key);
	}

	public static void writeProperties(String key, String value) throws Throwable {
		try {
			FileInputStream in = new FileInputStream(configPropertiesPath);
			Properties prop = new Properties();
			prop.load(in);
			in.close();

			FileOutputStream output = null;
			output = new FileOutputStream(configPropertiesPath);

			// set the properties value
			prop.setProperty(key, value);

			// save properties to project root folder
			prop.store(output, null);
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
			Log4j.info(e.getMessage());
		}
	}
	
	public static String instantiateProperties(String pageClassName, String key) {
        Properties prop = new Properties();
        try {
        	File file = new File(FileUtilityHelper.getClassLocatorPath(pageClassName));
            FileInputStream fis = new FileInputStream(file);
            prop.load(fis);
        } catch (Exception e) {
            Log4j.info(e.getMessage());
            e.printStackTrace();
        }
        return prop.getProperty(key);
    }
}
