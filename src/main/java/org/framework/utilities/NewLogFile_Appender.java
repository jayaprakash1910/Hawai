package org.framework.utilities;

import org.apache.log4j.FileAppender;

/**
 * @author Chandu
 * This class returns the time stamp which is used to append to the Log file for each execution
 */
public class NewLogFile_Appender  extends FileAppender {

	public NewLogFile_Appender() {
    }
    
	@Override
    public void setFile(String file) {
        super.setFile(prependDate(file));
    }

    private static String prependDate(String filename) {
        return System.currentTimeMillis() + "_" + filename;
    }
}