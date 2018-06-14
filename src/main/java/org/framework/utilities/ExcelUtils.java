package org.framework.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *	This class handles all Excel related operations
 */

public class ExcelUtils {

	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	public static XSSFRow Row;

	// This method is to set the File path and to open the Excel file, Pass
	// Excel Path and Sheetname as Arguments to this method

	public static void setExcelFile(String Path, String SheetName) throws Exception {
		try {
			// Open the Excel file
			FileInputStream ExcelFile = new FileInputStream(Path);

			// Access the required test data sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);

		} catch (Exception e) {
			throw (e);
		}
	}

	// This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num

	public static String getCellData(int RowNum, int ColNum) throws Exception {
		try {
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

			String CellData = Cell.getStringCellValue();

			return CellData;

		} catch (Exception e) {
			return "";
		}
	}

	// This method is to write in the Excel cell, Row num and Col num are the parameters

	@SuppressWarnings({ "deprecation", "static-access" })
	public static void setCellData(String path, String Result, int RowNum, int ColNum, String sheetName) throws Exception {
		FileInputStream excelFile = new FileInputStream(path);

		// Access the required test data sheet
		ExcelWBook = new XSSFWorkbook(excelFile);
		ExcelWSheet = ExcelWBook.getSheet(sheetName);
		CellStyle style = ExcelWBook.createCellStyle();

		try {
			Row = ExcelWSheet.getRow(RowNum);
			if (null == Row) {
				Row = ExcelWSheet.createRow(RowNum);
			}

			Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);

			if (Cell == null) {
				Cell = Row.createCell(ColNum);

				// Set borders to the cell
				style.setBorderBottom(CellStyle.BORDER_THICK);
				style.setBorderTop(CellStyle.BORDER_THICK);
				style.setBorderRight(CellStyle.BORDER_THICK);
				style.setBorderLeft(CellStyle.BORDER_THICK);

				style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
				style.setTopBorderColor(IndexedColors.BLACK.getIndex());
				style.setRightBorderColor(IndexedColors.BLACK.getIndex());
				style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
				
				style.setAlignment(CellStyle.ALIGN_CENTER);

				Cell.setCellValue(Result);

			} else {
				Cell.setCellValue(Result);
			}

			// Constant variables Test Data path and Test Data file name
			FileOutputStream fileOut = new FileOutputStream(path);
			ExcelWBook.write(fileOut);

			fileOut.flush();
			fileOut.close();

		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 * @author Chandu 
	 * Get the effective value of a cell, formatted according to the formatting of the cell. If the cell contains a formula, it is evaluated first, then the result is formatted.
	 * 
	 * @param row - the row
	 * @param columnIndex - the cell's column index
	 * @return the cell's value
	 */

	public static String getCellValue(Row row, int columnIndex) {
		try {
			String cellValue = null;
			Cell cell = row.getCell(columnIndex);
			if (cell == null) {
				// no data in this cell
				cellValue = null;
			}
			cellValue = cell.toString();
			return cellValue;
		} catch (Exception e) {
			throw (e);
		}
	}
	
	/**
	 * This method returns one row of data with all column values for specific rowIndex 
	 * @param sheetName
	 * @param filePath
	 * @param rowIndex
	 * @param colIndex
	 * @return
	 * @throws Throwable
	 */
	public static Row getSpecificRowData(String sheetName, String filePath, int rowIndex, int colIndex) throws Throwable {
		FileInputStream excelFile = new FileInputStream(filePath);

		// Access the required test data sheet
		ExcelWBook = new XSSFWorkbook(excelFile);
		ExcelWSheet = ExcelWBook.getSheet(sheetName);
		
		return CellUtil.getRow(rowIndex, ExcelWSheet);
	}
	
	public static String getColumnValue(String key, int columnIndex, String filePath, String sheetName) throws Throwable{
		String methodNameCellValue = null, testFolderCellValue = null;
		try {
			int totalRowCount = getTotalRowCount(sheetName, filePath);
			Row row;
			
			for (int rowIndex = 1; rowIndex <= totalRowCount; rowIndex++ ) {
				row = getSpecificRowData(sheetName, filePath, rowIndex, columnIndex);
				methodNameCellValue = getCellValue(row, columnIndex);
				
				if (methodNameCellValue.equalsIgnoreCase(key)) {
					testFolderCellValue = getCellValue(row, columnIndex-1);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return testFolderCellValue;
	}
	
	public static int getTotalRowCount(String sheetName, String filePath) throws IOException {
		FileInputStream excelFile = new FileInputStream(filePath);
		ExcelWBook = new XSSFWorkbook(excelFile);
		ExcelWSheet = ExcelWBook.getSheet(sheetName);

		return ExcelWSheet.getPhysicalNumberOfRows();
	}
	
	public static String getSpecificCellValueFromColumn(String cellValue, int columnIndexToSearch, int columnIndexToGet, String filePath, String sheetName) throws Throwable{
		String ExcelCellValue = null;
		try {
			int totalRowCount = getTotalRowCount(sheetName, filePath);
			Row row;
			
			for (int rowIndex = 1; rowIndex <= totalRowCount; rowIndex++ ) {
				row = getSpecificRowData(sheetName, filePath, rowIndex, columnIndexToSearch);
				ExcelCellValue = getCellValue(row, columnIndexToSearch);
				
				if (ExcelCellValue.equalsIgnoreCase(cellValue)) {
					return getCellValue(row, columnIndexToGet);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ExcelCellValue;
	}
}
