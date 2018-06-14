package org.framework.utilities;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.framework.Base;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ScreenshotUtilsHelper extends Base {
	//protected static AppiumDriver<MobileElement> driver;	
	/****************************************************************************************************************************
	 * Function Name : get_ScreenShot() Description : Capture Screenshot
	 * 
	 * @param fileName: FileName screenshot save in local directory
	 ****************************************************************************************************************************/
	public static String get_ScreenShot(String fileName) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String source = scrFile.getAbsolutePath().substring(scrFile.getAbsolutePath().lastIndexOf(File.separator) + 1).toString();
		String screenshotDestinationPath = null;
		
		try {
				FileUtils.moveToDirectory(scrFile, new File(fileName), false);
				File newFile = new File( fileName + File.separator + source);
				screenshotDestinationPath = fileName + File.separator + Base.currentTestMethodName + "_" + CalendarHelper.getLocalDateTime().replace(" ", "_").replace(":", "_").replace("/", "_") + ".png";
				new File(newFile.toString()).renameTo((new File(screenshotDestinationPath)));
		} catch (IOException e) {
			e.printStackTrace();
			logHelper.info(e.getMessage());
		}
		return screenshotDestinationPath;
	}
	
	public static String getScreenshot() throws Exception {
		String dateName = CalendarHelper.getCurrentTime().replace(" ", "_").replace(":", "_").replace("/", "_");
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		//String failedTestCaseScreenshotPath = FileUtilityHelper.getLatestDirectory(screenshotPath).toString() + File.separator + "FailedTestsScreenshots" + File.separator;
		File latestResultsFolder = FileUtilityHelper.getLatestDirectory(System.getProperty("user.dir") + File.separator + "Results" + File.separator);
		String failedTestCaseScreenshotPath =  latestResultsFolder.toString();
		/*File file = new File(failedTestCaseScreenshotPath);
		
		if (!file.exists()) {
			new File(failedTestCaseScreenshotPath).mkdirs();
		}*/
		
		String destination = failedTestCaseScreenshotPath + File.separator + dateName + ".png";
		File finalDestination = new File(destination);
		
		FileUtils.copyFile(source, finalDestination);
		
		destination = finalDestination.getName();
		return destination;
	}

	public static void captureScreenshot() throws Exception {
		try {
				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				String source = scrFile.toString();
				BufferedImage screenFullImage = createImage(source);
			
			  //create word doc
			    XWPFDocument doc = new XWPFDocument();
			    // create para and run
			    XWPFParagraph para = doc.createParagraph();    
			    XWPFRun run = para.createRun();
			//    String docFilePath = FileUtilityHelper.getTestcaseScreenshotsPath() + "TestResultsWithScreenshots.docx" ;
	
			    para.setAlignment(ParagraphAlignment.CENTER);
	
			    // convert buffered image to Input Stream
			    ByteArrayOutputStream baos = new ByteArrayOutputStream();
			    ImageIO.write(screenFullImage, "png", baos);
			    baos.flush();
			    ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
			    baos.close();
	
			    // add image to word doc
			    run.addBreak();
			    run.addPicture(bis, XWPFDocument.PICTURE_TYPE_PNG, "image file", Units.toEMU(220), Units.toEMU(350)); // 200x200 pixels
			    bis.close();
			    doc.close();
			    // write word doc to file
			//    FileOutputStream fos = new FileOutputStream(docFilePath);
			 //   doc.write(fos);
			 //   fos.close(); 
	            
		} catch (Exception e) {
			e.printStackTrace();
			Log4j.info(e.getMessage());
		}
	}
	
	public static  BufferedImage createImage(String sourceFileName) throws Exception{
	    BufferedImage bufferedImage;
	    try {
	    	String destFileName = FileUtilityHelper.getTestcaseImagesPath() + "pic" + "_" + FileUtilityHelper.getImageTimeStamp() +".png";
	    	FileUtilityHelper.renameFile(sourceFileName, destFileName);
	    	bufferedImage = ImageIO.read(new File(destFileName));
	    	return bufferedImage;
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	public static void copyImagesToWord() throws Throwable {
		String docFilePath = FileUtilityHelper.getTestcaseScreenshotsPath() + "TestResultsWithScreenshots.docx" ;
		FileUtilityHelper.moveFilesWithDirectory(FileUtilityHelper.getTestcaseImageFilesPath());
		FileUtilityHelper.copyImagesToWordDoc(FileUtilityHelper.getTestcaseImageFilesPath(), docFilePath);
		
		deleteImageProcessingFolder();
	}
	
	/**
	 * This method captures Screenshots into a local directory
	 * @param fileName
	 * @throws Exception
	 */
	public static void captureDesktopScreenshot(String fileName) throws Exception {
		try {
				Robot robot = new Robot();
			    Rectangle screenRect  = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			    BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
			    ImageIO.write(screenFullImage , "png", new File(fileName));
			    
			    //create word doc
			    XWPFDocument doc = new XWPFDocument();
			    // create para and run
			    XWPFParagraph para= doc.createParagraph();    
			    XWPFRun run = para.createRun();
			    String docFilePath = FileUtilityHelper.getTestcaseScreenshotsPath() + "TestResultsWithScreenshots.docx" ;

			    para.setAlignment(ParagraphAlignment.CENTER);

			    // convert buffered image to Input Stream
			    ByteArrayOutputStream baos = new ByteArrayOutputStream();
			    ImageIO.write(screenFullImage, "png", baos);
			    baos.flush();
			    ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
			    baos.close();

			    // add image to word doc
			    run.addBreak();
			    run.addPicture(bis, XWPFDocument.PICTURE_TYPE_PNG, "image file", Units.toEMU(650), Units.toEMU(350)); // 200x200 pixels
			    bis.close();
			    // write word doc to file
			    FileOutputStream fos = new FileOutputStream(docFilePath);
			    doc.write(fos);
			    fos.close(); 
			    doc.close();
			    
	            
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void getDesktopScreenshot(String fileName) throws Exception {
		try {
			Robot robot = new Robot();
		    Rectangle screenRect  = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		    BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
		    ImageIO.write(screenFullImage , "png", new File(fileName));
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	public static void captureFirstDesktopScreenshot() throws Exception {
//		 if (deviceType.equalsIgnoreCase("Desktop")) {
//	        	File latestResultsFolder = FileUtilityHelper.getLatestDirectory(System.getProperty("user.dir") + File.separator + "Results" + File.separator);
//	            String latestResultsFolderPath = latestResultsFolder.toString();
//	            
//	            ScreenshotUtilsHelper.getDesktopScreenshot(latestResultsFolderPath + File.separator + "DesktopScreenshot.png");
//	     }
//	}
}
