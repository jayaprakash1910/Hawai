package org.framework.utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.log4j.Logger;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.framework.Base;
import org.testng.annotations.Test;
import org.testng.internal.ClassHelper;
import org.testng.internal.PackageUtils;

public class FileUtilityHelper extends Base {
	private static Logger Log = Logger.getLogger(FileUtilityHelper.class.getName());
	public static boolean isFinished = false;

	public static void replaceContent() {
		File file = FileUtilityHelper.getLatestFile(System.getProperty("user.dir") + File.separator + "Results" + File.separator);
		String filePath = file.toString();
		File file2 = FileUtilityHelper.getHTMLFile(filePath);
		String oldFileName = file2.toString();
		String tmpFileName = "tmp_try.html";

		BufferedReader br = null;
		BufferedWriter bw = null;

		try {
			br = new BufferedReader(new FileReader(oldFileName));
			bw = new BufferedWriter(new FileWriter(tmpFileName));

			String line;
			while ((line = br.readLine()) != null) {
				if (line.contains("<span class='panel-name'>Pass Percentage</span>")) {
					line = line.replace("<span class='panel-name'>Pass Percentage</span>",
							"<span class='panel-name'>Tests Pass Percentage</span>");
					bw.write(line);
				}
			}
		} catch (Exception e) {
			return;
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (bw != null) {
					bw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		File oldFile = new File(oldFileName);
		oldFile.delete();

		File newFile = new File(tmpFileName);
		newFile.renameTo(oldFile);
	}
	
	public static void replacePassPercentagePosition() {
		File file = FileUtilityHelper.getLatestFile(System.getProperty("user.dir") + File.separator + "Results" + File.separator);
		String filePath = file.toString();
		File file2 = FileUtilityHelper.getHTMLFile(filePath);
		String oldFileName = file2.toString();
		String tmpFileName = "tmp_try.html";
		
		String actualText = "<div class='col s12 m6 l4 fh'> 						<div class='card-panel'> 							<div>								<span class='panel-name'>Steps View</span>							</div> 							<div class='panel-setting modal-trigger step-status-filter right'>								<a href='#step-status-filter'><i class='mdi-navigation-more-vert text-md'></i></a>							</div> 							<div class='chart-box'>								<canvas class='text-centered' id='step-analysis'></canvas>							</div> 							<div>								<span class='weight-light'><span class='s-pass-count weight-normal'></span> step(s) passed </span>							</div> 							<div>								<span class='weight-light'><span class='s-fail-count weight-normal'></span> step(s) failed, <span class='s-others-count weight-normal'></span> others</span>							</div> 						</div> 					</div>					<div class='col s12 m12 l4 fh'> 						<div class='card-panel'> 							<span class='panel-name'>Tests Pass Percentage</span> 							<span class='pass-percentage panel-lead'></span> 							<div class='progress light-blue lighten-3'> 								<div class='determinate light-blue'></div> 							</div> 						</div> 					</div>";
		
		String expText = "<div class='col s12 m12 l4 fh'> 						<div class='card-panel'> 							<span class='panel-name'>Tests Pass Percentage</span> 							<span class='pass-percentage panel-lead'></span> 							<div class='progress light-blue lighten-3'> 								<div class='determinate light-blue'></div> 							</div> 						</div> 					</div><div class='col s12 m6 l4 fh'> 						<div class='card-panel'> 							<div>								<span class='panel-name'>Steps View</span>							</div> 							<div class='panel-setting modal-trigger step-status-filter right'>								<a href='#step-status-filter'><i class='mdi-navigation-more-vert text-md'></i></a>							</div> 							<div class='chart-box'>								<canvas class='text-centered' id='step-analysis'></canvas>							</div> 							<div>								<span class='weight-light'><span class='s-pass-count weight-normal'></span> step(s) passed </span>							</div> 							<div>								<span class='weight-light'><span class='s-fail-count weight-normal'></span> step(s) failed, <span class='s-others-count weight-normal'></span> others</span>							</div> 						</div> 					</div>";

		BufferedReader br = null;
		BufferedWriter bw = null;

		try {
			br = new BufferedReader(new FileReader(oldFileName));
			bw = new BufferedWriter(new FileWriter(tmpFileName));

			String line;
			while ((line = br.readLine()) != null) {
				if (line.contains(actualText)) {
					line = line.replace(actualText, expText);
					bw.write(line);
				}
			}
		} catch (Exception e) {
			return;
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (bw != null) {
					bw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		File oldFile = new File(oldFileName);
		oldFile.delete();

		File newFile = new File(tmpFileName);
		newFile.renameTo(oldFile);
	}
	
	public static void replacePassFailPercent() {
		File file = FileUtilityHelper.getLatestFile(System.getProperty("user.dir") + File.separator + "Results" + File.separator);
		String filePath = file.toString();
		File file2 = FileUtilityHelper.getHTMLFile(filePath);
		String oldFileName = file2.toString();
		String tmpFileName = "tmp_try.html";

		BufferedReader br = null;
		BufferedWriter bw = null;

		try {
			br = new BufferedReader(new FileReader(oldFileName));
			bw = new BufferedWriter(new FileWriter(tmpFileName));

			String line;
			String actualText = "<div class='col s12 m12 l4 fh'> 						<div class='card-panel'> 							<span class='panel-name'>Tests Pass Percentage</span> 							<span class='pass-percentage panel-lead'></span> 							<div class='progress light-blue lighten-3'> 								<div class='determinate light-blue'></div> 							</div> 						</div> 					</div>";
			String expText = "<div class='col s12 m12 l4 fh'> <div class='card-panel'> <table style='width:80%;' border='3'> <tr> <th style='background-color: #eeeeee;'>Test Case Status</th> <th style='background-color: #eeeeee;'>Percentage</th> </tr> <tr> <td  align = 'center'>Tests Passed</td> <td  align = 'center'><font color='green'><b>" + getPassPercent() + "%</b></font></td> </tr> <tr> <td  align = 'center'>Tests Failed</td> <td  align = 'center'><font color='red'><b>" + getFailPercent() + "%</b></font></td> </tr> </table> </div> </div>";
			while ((line = br.readLine()) != null) {
				if (line.contains(actualText)) {
					line = line.replace(actualText, expText);
					bw.write(line);
				}
			}
		} catch (Exception e) {
			return;
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (bw != null) {
					bw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		File oldFile = new File(oldFileName);
		oldFile.delete();

		File newFile = new File(tmpFileName);
		newFile.renameTo(oldFile);
	}
	

	public static File getLatestFile(String path) {
		File lastModifiedFile = null;
		try {
			File dir = new File(path);
			File[] files = dir.listFiles();

			if (files != null && files.length > 0) {
				lastModifiedFile = files[0];

				for (int i = 1; i < files.length; i++) {
					if (lastModifiedFile.lastModified() < files[i].lastModified()) {
						lastModifiedFile = files[i];
					}
				}
				return lastModifiedFile;
			}

		} catch (Exception e) {
			e.printStackTrace();
			Log4j.info(e.getMessage());
		}
		return lastModifiedFile;
	}
	
	public static File getLatestDirectory(String path) {
		File dir = new File(path);
		File[] dirList = dir.listFiles();

		if (dirList == null || dirList.length == 0) {
			return null;
		}
		
		File lastModifiedFile;
		
		if(!(dirList[0].toString().contains(".DS_Store"))) {
			lastModifiedFile = dirList[0];
		} else {
			lastModifiedFile = dirList[1];
		}
		
		for (int i = 1; i < dirList.length; i++) {
			if (lastModifiedFile.lastModified() < dirList[i].lastModified()) {
				if(!(dirList[i].toString().contains(".DS_Store"))) {
					lastModifiedFile = dirList[i];
				}
				//System.out.println(lastModifiedFile);
			}
		}
		return lastModifiedFile;
	}

	public static File getHTMLFile(String path) {
		File dir = new File(path);
		File[] files = dir.listFiles();

		if (files == null || files.length == 0) {
			return null;
		}

		String htmlFile = null;
		File resultFile = null;

		for (int i = 0; i < files.length; i++) {
			htmlFile = files[i].getName();
			if (htmlFile.contains(".html")) {
				resultFile = files[i];
			}
		}
		return resultFile;
	}
	
	public static void deleteFilesOlderThan30Days() {
		String logFilePath = System.getProperty("user.dir") + File.separator + "logs" + File.separator;
		String resultFilePath = System.getProperty("user.dir") + File.separator + "Results" + File.separator;
		
		FileUtilityHelper.deleteLogsOlderThan30days(logFilePath);
		FileUtilityHelper.deleteResultFilesOlderThan30days(resultFilePath);
	}
	
	public static void deleteLogsOlderThan30days(String logFilePath) {
		File directory = new File(logFilePath);
		Calendar cal1 = new GregorianCalendar();
		Calendar cal2 = new GregorianCalendar();
		
		try {
			if (directory.exists()) {
				File[] listFiles = directory.listFiles();
				for (File listFile : listFiles) {
					Date date1 = new Date();
					cal1.setTime(date1);
					
					long purgeTime = listFile.lastModified();
					Date date2 = new Date(purgeTime);
					cal2.setTime(date2);
					
					int days = daysBetween(date1, date2);
					
					if (days < -30) {
						listFile.delete();
					}
				}
			} else
				Log.info("Files were not deleted, directory " + logFilePath + " does'nt exist!");
		} catch (Exception e) {
			e.printStackTrace();
			Log.info(e.getMessage());
		}
	}
	
	public static void deleteResultFilesOlderThan30days(String resultFilePath) {
		File directory = new File(resultFilePath);
		Calendar cal1 = new GregorianCalendar();
		Calendar cal2 = new GregorianCalendar();
		
		try {
			if (directory.exists()) {
				Date date1 = new Date();
				cal1.setTime(date1);
				
				long purgeTime = directory.lastModified();
				Date date2 = new Date(purgeTime);
				cal2.setTime(date2);
				
				int days = daysBetween(date1, date2);
				
				if (days < -30) {
					Log.info("Directory to be deleted: " + directory.getName());
					directory.delete();
				}
			} else
				Log.info("Files were not deleted, directory " + resultFilePath + " does'nt exist!");
		} catch (Exception e) {
			e.printStackTrace();
			Log.info(e.getMessage());
		}
	}
	
	public static int daysBetween(Date day1, Date day2) {
		return (int)( (day2.getTime() - day1.getTime()) / (1000 * 60 * 60 * 24));
	}
	
	public static String ConvertMilliSecondsToFormattedDate(String milliSeconds){
		String dateFormat = "MM-DD-yyyy hh:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
		
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(milliSeconds));
        return simpleDateFormat.format(calendar.getTime());
    }
	
	@SuppressWarnings("resource")
    public static void transferFileData(File sourceFile, File destFile) throws IOException {
          FileChannel src = new FileInputStream(sourceFile).getChannel();
          FileChannel dest = new FileOutputStream(destFile).getChannel();
          dest.transferFrom(src, 0, src.size());
    }

    public static void compareFiles(String sourceFilePath, String destFilePath) throws IOException {
          BufferedReader reader1 = new BufferedReader(new FileReader(sourceFilePath));
          BufferedReader reader2 = new BufferedReader(new FileReader(destFilePath));
          String line1 = reader1.readLine();
          String line2 = reader2.readLine();
          boolean areEqual = true;
          int lineNum = 1;

          while (line1 != null || line2 != null) {
                if (line1 == null || line2 == null) {
                      areEqual = false;
                      break;
                } else if (!line1.equalsIgnoreCase(line2)) {
                      areEqual = false;
                      break;
                }
                line1 = reader1.readLine();
                line2 = reader2.readLine();
                lineNum++;
          }
          if (areEqual) {
                System.out.println("Two files have same content.");
          } else {
                System.out.println("Two files have different content. They differ at line " + lineNum);
                System.out.println("File1 has " + line1 + " and File2 has " + line2 + " at line " + lineNum);
          }

          reader1.close();
          reader2.close();
    }
    
    public static void printFileContent(String filePath) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(filePath));
        String line = in.readLine();
        while(line != null)
        {
          System.out.println(line);
          line = in.readLine();
        }
        in.close();
    }
    
    public static File finder(String dirName, String fileExtn) {
		File listFile = new File(dirName);
		File dir = new File(dirName);
		
		for (File file : dir.listFiles()) {
			if (file.getName().endsWith((fileExtn))) {
				listFile = file;
			}
		}
		return listFile;
	}
    
	@SuppressWarnings("rawtypes")
	
	public File[] fileFinder(String dirName, String fileExtn) {
		File dir = new File(dirName);
		return dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String filename) {
				return filename.endsWith(fileExtn);
			}
		}); 
	}
	
	public static void renameHTMLFile() throws IOException {
		File latestResultsFolder = FileUtilityHelper.getLatestDirectory(System.getProperty("user.dir") + File.separator + "Results");
		File latestDetailedReport = FileUtilityHelper.getHTMLFile(latestResultsFolder.toString());
		Paths.get(latestDetailedReport.toString());
	}
	
	public static void renameResultsFolderName() throws Exception {
		File dir  = FileUtilityHelper.getLatestDirectory(System.getProperty("user.dir") + File.separator + "Results");
		
		if(environmentName.equalsIgnoreCase("QA")) {
			File newDir = new File(dir.getParent() + File.separator + "Regression_ResultsFile_" + Base.timeStampBeforeSuite);
			dir .renameTo(newDir);
		} else if (environmentName.equalsIgnoreCase("STAGE") || environmentName.equalsIgnoreCase("PROD")) {
			File newDir = new File(dir.getParent() + File.separator + "Smoke_ResultsFile_" + Base.timeStampBeforeSuite);
			dir .renameTo(newDir);
		}
	}
	
	public static void replaceHTMLContent(String deviceMACAddress, String osVersion) {
		File file = FileUtilityHelper.getLatestFile(System.getProperty("user.dir") + File.separator + "Results" + File.separator);
		String filePath = file.toString();
		File file2 = FileUtilityHelper.getHTMLFile(filePath);
		String oldFileName = file2.toString();
		String tmpFileName = "tmp_try.html";

		BufferedReader br = null;
		BufferedWriter bw = null;
		StringBuilder contentBuilder = new StringBuilder();

		try {
			br = new BufferedReader(new FileReader(oldFileName));
			bw = new BufferedWriter(new FileWriter(tmpFileName));

			String line;
			
			while ((line = br.readLine()) != null) {
				contentBuilder.append(line);
			}
			String content = contentBuilder.toString();
			
			if (content.contains("<td>tempOSVersion</td>")) {
				content = content.replace("<td>tempOSVersion</td>", "<td>" + osVersion + "</td>");
			}
			
			bw.write(content);
		} catch (Exception e) {
			// Log.info(e.getMessage());
			return;
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				// Log.info(e.getMessage());
			}
			try {
				if (bw != null) {
					bw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				// Log.info(e.getMessage());
			}
		}
		
		File oldFile = new File(oldFileName);
		oldFile.delete();

		File newFile = new File(tmpFileName);
		newFile.renameTo(oldFile);
	}
	
	public static String getTestcaseImagesPath() throws Exception {
		String directory = getTestcaseImageFilesPath();
		String tempFolderName = directory + "Temp_" + getImageTimeStamp() + File.separator;
		File file = new File (tempFolderName);
		if(!file.exists()){
			new File(tempFolderName).mkdirs();
		}
		return tempFolderName;
	}
	
	public static String getTestcaseImageFilesPath() throws Exception {
		String directory = "ImageProcessing";
		File file = new File (tempTestcaseImagesPath + directory + File.separator);
		
		if(!file.exists()){
			new File(tempTestcaseImagesPath + directory).mkdirs();
		}
		return tempTestcaseImagesPath + directory + File.separator;
	}
	
	public static String getTestcaseScreenshotsPath() {
		String directory = "TestcaseScreenshots";
		String testResultsScreenshotPath = null; 
		String testCaseScreenshotPath = FileUtilityHelper.getLatestDirectory(screenshotPath).toString() + File.separator + directory + File.separator;
		
		File file = new File (testCaseScreenshotPath);
		
		if (!file.exists()) {
			new File(testCaseScreenshotPath).mkdirs();
		}
		
		if (FileUtilityHelper.getLatestDirectory(screenshotPath).toString().contains(".DS_Store")) {
			testResultsScreenshotPath = FileUtilityHelper.getLatestDirectory(screenshotPath).toString().replace(File.separator + ".DS_Store", "");
			testResultsScreenshotPath = testResultsScreenshotPath + File.separator;
		} else {
			testResultsScreenshotPath = testCaseScreenshotPath;
		}
		return testResultsScreenshotPath;
	}
	
	public static String getImageTimeStamp() {
		return CalendarUtils.getCurrentTime().replace(":", "_").replace(".", "_"); 
	}
	
	public static File[] sortFilesInDescendingOrder(String path) throws Exception {
		File dir = new File(path);
		File[] files = dir.listFiles();
		Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
		
		return files;
	}
	
	public static File[] sortFilesInAscendingOrder(String path) throws Exception {
		File dir = new File(path);
		File[] files = dir.listFiles();
		Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_COMPARATOR);
		
		return files;
	}
	
	public static void copyImagesToWordDoc(String imageFilePath, String outputDocumentPath) throws Exception {
        CustomXWPFDocument document = new CustomXWPFDocument();
        File[] files = FileUtilityHelper.sortFilesInAscendingOrder(imageFilePath);
        
        try {
        	XWPFParagraph para = document.createParagraph();
        	para.setAlignment(ParagraphAlignment.CENTER);
        	
        	if (files.length > 0) {
        		for (int i = 0; i < files.length; i++) {
        			File file = files[i];
        			
        			if(!(files[i].toString().contains(File.separator + ".DS_Store"))) {
        				String blipId = para.getDocument().addPictureData(new FileInputStream(new File(file.getAbsolutePath())), Document.PICTURE_TYPE_PNG);
                        document.createPicture(blipId, document.getNextPicNameNumber(Document.PICTURE_TYPE_PNG), 350, 450);
        			}
            	}
            	String lastModifiedImage = FileUtilityHelper.getLatestFile(imageFilePath).toString();
            	String blipId = para.getDocument().addPictureData(new FileInputStream(new File(lastModifiedImage)), Document.PICTURE_TYPE_PNG);
                document.createPicture(blipId, document.getNextPicNameNumber(Document.PICTURE_TYPE_PNG), 350, 450);
        	}
        } catch (Exception e) {
        	e.printStackTrace();
        	Log.info(e.getMessage());
        }
        
        FileOutputStream outStream = null;
        
        try {
        	outStream = new FileOutputStream(outputDocumentPath);
        	document.write(outStream);
        	outStream.close();
        	document.close();
        //	Log.info("Images are copied into the output word document successfully!!");
        	
        } catch (Exception e) {
        	e.printStackTrace();
        	Log.info(e.getMessage());
        }
	}
	
	public static boolean deleteDirectory(String destinationFolderPath) {
		boolean result = false;
		File directory = new File(destinationFolderPath);
		
		File[] contents = directory.listFiles();
		try {
			if (contents != null) {
		        for(int i = 0 ; i < contents.length; i++) {
		        	deleteDirectory(contents[i].toString());
		        }
		        if (directory.delete()) {
		        	result = true;
		        }
		    }
		} catch (Exception e) {
        	e.printStackTrace();
        	Log.info(e.getMessage());
        }
		
		 return result;
	}
	
	public static boolean deleteDir(String directoryFilePath) throws IOException {
		File file = new File(directoryFilePath);
		File[] flist = null;

	    if (file.isFile()) {
	        return file.delete();
	    }

	    if (file.isDirectory()) {
	    	return file.delete();
	    }

	    flist = file.listFiles();
	    if (flist != null && flist.length > 0) {
	        for (File f : flist) {
	            if (!deleteDir(f.toString())) {
	                return false;
	            }
	        }
	    }

	    return file.delete();
	}
	
	public static boolean deleteDirFromCMDPrompt(String directoryPath) throws Exception {
		boolean result = false;
		String osName = System.getProperty("os.name");
		Log4j.info("directory path is " + directoryPath);
		File file =  new File(directoryPath);
		
		
		try {
			if (osName.contains("Windows")) {
				if (file.exists()) {
					Process p = Runtime.getRuntime().exec(new String[]{deleteFolderFilePath , directoryPath});
					/*ProcessBuilder builder = new ProcessBuilder(deleteFolderFilePath , directoryPath);
			        Process p = builder.start(); 
					p.waitFor();*/
					Thread.sleep(1000);
			        StringBuilder sb = new StringBuilder();
			        BufferedReader reader=new BufferedReader(new InputStreamReader(p.getInputStream())); 
			        String line; 
			        while((line = reader.readLine()) != null) { 
			        	sb = sb.append(line).append("\n");
			        } 
			        if (!file.exists()){
			        	result = true;
			        }
				} else if (!file.exists()){
		        	result = true;
		        }
			} else if (osName.contains("Mac")) {
				if (file.exists()) {
					ProcessBuilder builder = new ProcessBuilder("rm -rf " + directoryPath);
			        Process p = builder.start(); 
			        StringBuilder sb = new StringBuilder();
			        
			        p.waitFor(); 
			        BufferedReader reader=new BufferedReader(new InputStreamReader(p.getInputStream())); 
			        String line; 
			        while((line = reader.readLine()) != null) { 
			        	sb = sb.append(line).append("\n");
			        } 
			        result = true;
				} else if (!file.exists()){
		        	result = true;
		        }
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.info(e.getMessage());
		}
		
		return result;
	}
	
	public static void renameFile(String oldFilePath, String newFilePath) throws Exception {
		File oldFile = new File(oldFilePath);
		File newFile = new File(newFilePath);
		
		try {
			if(oldFile.exists()){
				oldFile.renameTo(newFile);
				oldFile.delete();
			}
		} catch(Exception e) {
			e.printStackTrace();
			Log.info(e.getMessage());
		}
	}
	
	public static boolean moveFilesWithDirectory(String destinationFolderPath) throws Exception {
		boolean result = false;
		File destinationFolder = new File(destinationFolderPath);
		File[] listOfDir = destinationFolder.listFiles();
		 
		 try {
			 if (!destinationFolder.exists()) {
			        destinationFolder.mkdirs();
			 }
			 
			 for (int i = 0; i < listOfDir.length; i++) {
				 File sourceFolder = listOfDir[i];
				 
					// Check weather source exists and it is folder.
				    if (sourceFolder.exists() && sourceFolder.isDirectory()) {
				        // Get list of the files and iterate over them
				        File[] listOfFiles = sourceFolder.listFiles();

				        /*if (listOfFiles != null) {
				            for (File child : listOfFiles ) {
				                // Move files to destination folder
				                child.renameTo(new File(destinationFolder + File.separator + child.getName()));
				            }

				            // Add if you want to delete the source folder 
				            sourceFolder.delete();
				        }*/
				        for (File child : listOfFiles) {
				        	Path temp = Files.move(Paths.get(child.getAbsolutePath()), Paths.get(destinationFolderPath + File.separator + child.getName()) );
				        	sourceFolder.delete();
				        	if (temp != null) {
					        	result = true;
					        }
				        }
				    }
				    else {
				        System.out.println(sourceFolder + "  Folder does not exists");
				    }
			 }
		 } catch (Exception e) {
			 e.printStackTrace();
			 Log.info(e.getMessage());
		 }
		return result;
	}
	
	public static boolean moveFilesWithoutDirectory(String sourceFolderPath, String destinationFolderPath) throws Exception {
		boolean result = false;
		File destinationFolder = new File(destinationFolderPath);
	    File sourceFolder = new File(sourceFolderPath);
	    InputStream inStream = null;
		OutputStream outStream = null;
		 
		 try {
			 if (!destinationFolder.exists()) {
			        destinationFolder.mkdirs();
			    }

			    // Check weather source exists and it is folder.
			    if (sourceFolder.exists() && sourceFolder.isDirectory()) {
			        // Get list of the files and iterate over them
			        File[] listOfFiles = sourceFolder.listFiles();

			        if (listOfFiles != null) {
			           /* for (File child : listOfFiles ) {
			                // Move files to destination folder
			                child.renameTo(new File(destinationFolder + "\\" + child.getName()));
			            }*/
			            String fileName;
			            for(int i = 0; i < listOfFiles.length; i++) {
			            	fileName = listOfFiles[i].getName();
			            	File afile = new File(sourceFolderPath + fileName);
			            	File bfile = new File(destinationFolderPath + fileName);
			            	
			            	inStream = new FileInputStream(afile);
			        	    outStream = new FileOutputStream(bfile);

			        	    byte[] buffer = new byte[1024];

			        	    int length;
			        	    //copy the file content in bytes
			        	    while ((length = inStream.read(buffer)) > 0){
			        	    	outStream.write(buffer, 0, length);
			        	    }

			        	    inStream.close();
			        	    outStream.close();

			        	    //delete the original file
			        	    afile.delete();
			            }
			            // Add if you want to delete the source folder 
			            sourceFolder.delete();
			        }
			        result = true;
			    }
			    else {
			       Log.info(sourceFolder + "  Folder does not exists");
			    }
		 } catch (Exception e) {
			 e.printStackTrace();
			 Log.info(e.getMessage());
		 }
		return result;
	}

	public static void deleteEmptyDir() {
		final String FOLDER_LOCATION = System.getProperty("user.dir") + File.separator + "Results" + File.separator;
		boolean isFinished = false;
	    do {
	        isFinished = true;
	        replaceText(FOLDER_LOCATION);
	    } while (!isFinished);
	}

	private static void replaceText(String fileLocation) {
	    File folder = new File(fileLocation);
	    File[] listofFiles = folder.listFiles();
	    if (listofFiles.length == 0) {
	        System.out.println("Folder Name :: " + folder.getAbsolutePath() + " is deleted.");
	        folder.delete();
	        isFinished = false;
	    } else {
	        for (int j = 0; j < listofFiles.length; j++) {
	            File file = listofFiles[j];
	            if (file.isDirectory()) {
	                replaceText(file.getAbsolutePath());
	            }
	        }
	    }
	}

	//////
	public static boolean deleteFileFromFolder(String filePath) throws Exception {
		 File file = new File(filePath);
		 
		 if(file.exists()) {
			 if(file.delete()) {
				 Log.info("File '" + file.toString() + "' is deleted successfully!!");
			 } else {
				 Log.info("Failed to delete the file '" + file.toString() + "' is deleted successfully!!");
			 }
		 }
		 
		 return file.exists();
	 }
	
	public static void moveFilesfromOneFolderToAnotherFolderInCMD(String oldFilePath, String destFolderPath) throws Exception {
		 String osName = System.getProperty("os.name");
		 
		 try {
			 if (osName.contains("Windows")) {
				/*ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "move " + oldFilePath + " " + destFolderPath);
		        Process p = builder.start(); 
		        p.waitFor();*/
				String[] command = {"cmd.exe", "/c", "move " + oldFilePath + " " + destFolderPath};
			 	Process p = Runtime.getRuntime().exec(command);
		        StringBuilder sb = new StringBuilder();
		        
		        p.waitFor(); 
		        BufferedReader reader=new BufferedReader(new InputStreamReader(p.getInputStream())); 
		        String line, st = null; 
		        while((line = reader.readLine()) != null) { 
		        	sb = sb.append(line).append("\n");
		        	st = sb.toString();
		        } 
		        
		        if (st.contains("file(s) moved.")) {
		        	Log.info("Files moved successfully!!");
		        } else {
		        	Log.info("Failed to move files");
		        }
			 }
		 } catch (Exception e) {
			 e.printStackTrace();
			 Log.info(e.getMessage());
		 }
	 }
	
	 public static void copyFileContentsToNewFile(String oldFilePath, String newFilePath) throws Exception {
		 FileReader fr = null;
		 FileWriter fw = null;
		 
		 try {
	            fr = new FileReader(oldFilePath);
	            fw = new FileWriter(newFilePath);
	            int c = fr.read();
	            while(c != -1) {
	                fw.write(c);
	                c = fr.read();
	            }
	        } catch(IOException e) {
	            e.printStackTrace();
	        } finally {
	            close(fr);
	            close(fw);
	        }
	 }
	 
	 public static void close(Closeable stream) {
        try {
            if (stream != null) {
                stream.close();
            }
        } catch(IOException e) {
            e.printStackTrace();
            Log.info(e.getMessage());
        }
    }
	 
	public static void copyFolder(File src, File dest) throws IOException {
    	if(src.isDirectory()){
    		//if directory not exists, create it
    		if(!dest.exists()) {
    			dest.mkdir();
     		  // System.out.println("Directory copied from " + src + "  to " + dest);
    		}

    		//list all the directory contents
    		String files[] = src.list();

    		for (String file : files) {
    		   //construct the src and dest file structure
    		   File srcFile = new File(src, file);
    		   File destFile = new File(dest, file);
    		   //recursive copy
    		   copyFolder(srcFile,destFile);
    		}
    	} else {
    		//if file, then copy it
    		//Use bytes stream to support all file types
    		InputStream in = new FileInputStream(src);
	        OutputStream out = new FileOutputStream(dest);

	        byte[] buffer = new byte[1024];

	        int length;
	        //copy the file content in bytes
	        while ((length = in.read(buffer)) > 0){
	    	   out.write(buffer, 0, length);
	        }

	        in.close();
	        out.close();
	        //System.out.println("File copied from " + src + " to " + dest);
    	}
	}
	
	public static void copyFolderContents(String srcFolderPath, String destFolderPath) throws Exception {
		File srcFolder = new File(srcFolderPath);
		File destFolder = new File(destFolderPath);
		
		try {
			//make sure source exists
	    	if(!srcFolder.exists()){
	           System.out.println("Directory does not exist.");
	           //just exit
	           System.exit(0);
	        } else {
	        	copyFolder(srcFolder,destFolder);
	        //	Log.info("Folder and its contents are successfully copied!!");
	        }
		} catch (Exception e) {
			e.printStackTrace();
			Log.info(e.getMessage());
		}
	}
	
	public static void removeLineFromFile(String file, String lineToRemove) {
	    try {

	      File inFile = new File(file);

	      if (!inFile.isFile()) {
	        System.out.println("Parameter is not an existing file");
	        return;
	      }

	      //Construct the new file that will later be renamed to the original filename.
	      File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

	      BufferedReader br = new BufferedReader(new FileReader(file));
	      PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

	      String line = null;

	      //Read from the original file and write to the new
	      //unless content matches data to be removed.
	      while ((line = br.readLine()) != null) {

	        if (!line.trim().equals(lineToRemove)) {

	          pw.println(line);
	          pw.flush();
	        }
	      }
	      pw.close();
	      br.close();

	      //Delete the original file
	      if (!inFile.delete()) {
	        System.out.println("Could not delete file");
	        return;
	      }

	      //Rename the new file to the filename the original file had.
	      if (!tempFile.renameTo(inFile))
	        System.out.println("Could not rename file");

	    }
	    catch (FileNotFoundException ex) {
	      ex.printStackTrace();
	    }
	    catch (IOException ex) {
	      ex.printStackTrace();
	    }
	  }
	 
	 public static void replaceStringInFile(String file, String search, String replacement) throws FileNotFoundException {
		 File inFile = new File(file);
		 
		 //file reading
		 File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
		 try {
			 
		      BufferedReader br = new BufferedReader(new FileReader(file));
		      PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
			 String line;
		 
		     while ((line = br.readLine()) != null) {
		    	 if (!line.trim().contains(search)) {
		    		 line.replaceAll(search, replacement);
			          pw.println(line);
			          pw.flush();
			        }
			      }
			      pw.close();
			      br.close();
		        
			      if (!inFile.delete()) {
				        System.out.println("Could not delete file");
				        return;
				      }

				      //Rename the new file to the filename the original file had.
				      if (!tempFile.renameTo(inFile))
				        System.out.println("Could not rename file");
		 } catch (Exception e) {
			 e.printStackTrace();
			 Log.info(e.getMessage());
		 }
	 }
	 
	 public static String readFile(String pathname) throws IOException {
	    File file = new File(pathname);
	    StringBuilder fileContents = new StringBuilder((int)file.length());
	    Scanner scanner = new Scanner(file);
	    String lineSeparator = System.getProperty("line.separator");
	
	    try {
	        while(scanner.hasNextLine()) {
	            fileContents.append(scanner.nextLine() + lineSeparator);
	        }
	        return fileContents.toString();
	    } finally {
	        scanner.close();
	    }
	}
	 
	 @SuppressWarnings("rawtypes")
	public static String getClassLocatorPath(String pageClassName) {
		
		 File root = null;
		 root = new File(pageLocatorsPath);
	    try {
	        boolean recursive = true;
	
	        Collection files = FileUtils.listFiles(root, null, recursive);
	
	        for (Iterator iterator = files.iterator(); iterator.hasNext();) {
	            File file = (File) iterator.next();
	            if (file.getName().toLowerCase().contains(pageClassName.toLowerCase())) {
	            	return file.getAbsolutePath();
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
		return null;
	} 
	
	 public static void generateJenkinsReport() throws Exception {
        File file = FileUtilityHelper.getLatestFile(System.getProperty("user.dir") + File.separator + "Results" + File.separator);
        String filePath = file.toString();
        File file2 = FileUtilityHelper.getHTMLFile(filePath);
        String oldFileName = file2.toString();
        String jenkinsReportPath = System.getProperty("user.dir") + File.separator + "JenkinsReport" + File.separator ;
        File jenkinsReportFolder = new File(jenkinsReportPath);
        
        if (!jenkinsReportFolder.exists()) {
        	new File(jenkinsReportPath).mkdirs();
        	//Log4j.info("JenkinsReport folder created successfully!!");
        	//Log4j.info("Path to JenkinsReport folder: " + jenkinsReportPath);
        }
        String newFilePath = System.getProperty("user.dir") + File.separator + "JenkinsReport" + File.separator + "AutomationReport.html";
        FileUtilityHelper.copyFileContentsToNewFile(oldFileName, newFilePath);
        copyFolderContents(file.toString(), System.getProperty("user.dir") + File.separator + "JenkinsReport" + File.separator);
    } 
	 
	public static void copyJenkinsReportToDesktopResultOnlyJobLocation() throws Exception {
		
		if (deviceType.equalsIgnoreCase("Desktop")) {
			File oldJobJenkinsReportFolder = new File(System.getProperty("user.dir") + File.separator + "JenkinsReport" + File.separator);
			//Log4j.info("old file  path: " + oldJobJenkinsReportFolder.toString());
			//deleting the Jenkins report folder from Desktop results only job folder location
			String newJobJenkinsReportFolder = replaceJenkinsDesktopResultsFolderName(System.getProperty("user.dir") + File.separator + "FinalJenkinsReport" + File.separator);
			deleteDirFromCMDPrompt(newJobJenkinsReportFolder);
			//String destResultsFolderPath = replaceJenkinsDesktopResultsFolderName(System.getProperty("user.dir") + File.separator + "JenkinsReport" + File.separator);
			File destResultsFolder = new File(newJobJenkinsReportFolder);
			Log4j.info("Is Destination folder still exist - " + destResultsFolder.exists());
			if (!destResultsFolder.exists()) {
				new File(newJobJenkinsReportFolder).mkdirs();
				Log4j.info("Checking Destination folder After its created, does this exist now ? - " + destResultsFolder.exists());
			}
			Log4j.info("Old reports path: " + oldJobJenkinsReportFolder.toString());
			Log4j.info("New reports path: " + destResultsFolder.toString());
			//copyFolderContents(desktopNewJobFile.toString(), replaceJenkinsDesktopResultsFolderName(System.getProperty("user.dir") + File.separator + "JenkinsReport" + File.separator));
			copyFolderContents(oldJobJenkinsReportFolder.toString(), newJobJenkinsReportFolder);
		}
		Log4j.info("Jenkins report copy completed");
	}
	 
	public static void copyFailedTestsScreenshotsToJenkinsReport() throws Exception {
		File file = FileUtilityHelper.getLatestFile(System.getProperty("user.dir") + File.separator + "Results" + File.separator);
		String filePath = file.toString();
		File file2 = FileUtilityHelper.getHTMLFile(filePath);
		String oldFileName = file2.toString();
		String newFilePath = System.getProperty("user.dir") + File.separator + "JenkinsReport" + File.separator + "AutomationReport.html";
		FileUtilityHelper.copyFileContentsToNewFile(oldFileName, newFilePath);
	}
	
	public static String replaceJenkinsDesktopResultsFolderName(String sourceName) throws Exception {
		String destPath = null;
		if (sourceName.toLowerCase().contains("cp_desktop_wod_daily")) {
			destPath = sourceName.replace("CP_Desktop_WOD_Daily", "Desktop_WOD_Results");
		} else if (sourceName.toLowerCase().contains("cp_desktop_ca_daily")) {
			destPath = sourceName.replace("CP_Desktop_CA_Daily", "Desktop_CA_Results");
		} else if (sourceName.toLowerCase().contains("cp_desktop_aa_daily")) {
			destPath = sourceName.replace("CP_Desktop_AA_Daily", "Desktop_AA_Results");
		}
		
		return destPath;
	}
	
	public static boolean isProcessRunning(String processName) throws IOException, InterruptedException {
		try {
			StringBuilder sb = new StringBuilder();
	        Process p = Runtime.getRuntime().exec(new String[]{listProcessBatFilePath});
	        BufferedReader reader=new BufferedReader(new InputStreamReader(p.getInputStream())); 
	        String line, tasksList = null; 
	        while((line = reader.readLine()) != null) { 
	        	sb = sb.append(line).append("\n");
	        	tasksList = sb.toString();
	        } 
	        Log4j.info("Task list contains java ? - " + tasksList.contains(processName));
	        return tasksList.contains(processName);
		} catch (Exception e) {
			e.printStackTrace();
			Log4j.info(e.getMessage());
		}
		return false;
    }
	
	@SuppressWarnings("resource")
	public static String toString(InputStream inputStream) {
		try {
			Scanner scanner = new Scanner(inputStream, "UTF-8").useDelimiter("\\A");
	        String string = scanner.hasNext() ? scanner.next() : "";
	        scanner.close();

	        return string;
		} catch (Exception e) {
			e.printStackTrace();
			Log4j.info(e.getMessage());
		}
        return "";
    }
	
	public static boolean killTaskProcess(String processName) throws Exception {
		String line, taskKill = "";
		boolean result = false;
		StringBuilder sb = new StringBuilder();
		try {
			if (isProcessRunning(processName)) {
				Log4j.info("java.exe process is running");
				Process p = Runtime.getRuntime().exec(new String[]{killProcessBatFilePath, processName});
		        BufferedReader reader=new BufferedReader(new InputStreamReader(p.getInputStream())); 
		        while((line = reader.readLine()) != null) { 
		        	Log4j.info("before task kill process ");
		        	sb = sb.append(line).append("\n");
		        	Log4j.info("after task kill process ");
		        	taskKill = sb.toString();
		        	Log4j.info("EOW - task kill process end result - " + taskKill);
		        } 
		        Log4j.info("Waiting for 1 sec ");
		        Thread.sleep(1000);
			}
			Log4j.info("task kill process end result - " + taskKill + "-");
			 result = taskKill.toUpperCase().contains("SUCCESS");
			if (result) {
				Log4j.info("Java Kill process successful");
				System.out.println("Java Kill process successful");
			} else {
				Log4j.info("Java Kill process unsuccessful");
				System.out.println("Java Kill process unsuccessful");
			}
	        return result;
		} catch (Exception e) {
			e.printStackTrace();
			Log4j.info(e.getMessage());
		}
		return result;
	}
	
	public static String filePath() {
		String strDirectoy = "ResultFile";
		String screenshotPath = System.getProperty("user.dir") + File.separator + "Results" + File.separator;
		new File(screenshotPath + strDirectoy + "_" + timeStamp).mkdirs();
		return screenshotPath + strDirectoy + "_" + timeStamp + File.separator;
	}
	
	public static void deleteImageProcessingFolder() throws Throwable {
		String directory = "ImageProcessing";
		File file = new File (tempTestcaseImagesPath + directory + File.separator);
		
		if (EnvironmentUtils.isWindows()) {
			if(file.exists()){
				FileUtilityHelper.deleteDirFromCMDPrompt(tempTestcaseImagesPath + directory + File.separator);
			}
		} else if (EnvironmentUtils.isMAC()) {
			if (file.exists()) {
				Runtime.getRuntime().exec("rm -R " + file.getAbsolutePath());
			}
			
		} else if (file.exists()) {
			FileUtilityHelper.deleteDir(tempTestcaseImagesPath + directory + File.separator);
		}
	}
	
	public static void deleteJenkinsReportFolder() throws Throwable {
		String jenkinsReportPath = System.getProperty("user.dir") + File.separator + "JenkinsReport" + File.separator;
		File file = new File (jenkinsReportPath);
		
		if (EnvironmentUtils.isWindows()) {
			if(file.exists()){
				FileUtilityHelper.deleteDirFromCMDPrompt(jenkinsReportPath);
			}
		} else if (EnvironmentUtils.isMAC()) {
			if (file.exists()) {
				Runtime.getRuntime().exec("rm -R " + file.getAbsolutePath());
			}
			
		} else if (file.exists()) {
			FileUtilityHelper.deleteDir(jenkinsReportPath);
		}
	}
	
	public static void deleteFailedTestsScreenshotsFolder() throws Throwable {
		String directory = "FailedTestsScreenshots";
		String failedTestCaseScreenshotPath = System.getProperty("user.dir") + File.separator + "FailedTestsScreenshots" + File.separator;
		File file = new File(failedTestCaseScreenshotPath);
		
		if (EnvironmentUtils.isWindows()) {
			if(file.exists()){
				FileUtilityHelper.deleteDirFromCMDPrompt(failedTestCaseScreenshotPath);
			}
		} else if (EnvironmentUtils.isMAC()) {
			if (file.exists()) {
				Runtime.getRuntime().exec("rm -R " + file.getAbsolutePath());
			}
			
		} else if (file.exists()) {
			FileUtilityHelper.deleteDir(failedTestCaseScreenshotPath + directory + File.separator);
		}
	}
	
}
