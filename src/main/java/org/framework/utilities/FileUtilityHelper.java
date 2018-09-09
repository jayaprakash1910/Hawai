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
	
}
