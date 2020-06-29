package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelXUtil {

	FileInputStream fis = null;
	FileOutputStream fos = null;
	XSSFWorkbook workbook = null;
	XSSFSheet sheet = null;
	String excelpath = null;

	public ExcelXUtil(String path) {
		try {
			fis = new FileInputStream(new File(path));
			workbook = new XSSFWorkbook(fis);
			fis.close();

			excelpath = path;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Method to return row count in xlsx workbook
	 * 
	 * @param sheetName
	 * @return
	 */
	public int getRowCount(String sheetName) {
		XSSFSheet sheet = workbook.getSheet(sheetName);
		if (sheet == null) {
			return 0;
		}
		return sheet.getLastRowNum() + 1;
	}

	/**
	 * Method to return column count in xlsx workbook
	 * 
	 * @param sheetName
	 * @param rowIndex
	 * @return
	 */
	public int getColumnCount(String sheetName, int rowIndex) {
		XSSFSheet sheet = workbook.getSheet(sheetName);
		if (sheet == null) {
			return 0;
		}
		XSSFRow row = sheet.getRow(rowIndex);
		return row.getLastCellNum();
	}

	/**
	 * This method returns cell value from xlsx workbook for sheetname, rowindex,
	 * colindex passed
	 * 
	 * @param sheetName
	 * @param rowIndex
	 * @param cellIndex
	 * @return
	 */
	public String getCellValue(String sheetName, int rowIndex, int cellIndex) {
		XSSFSheet sheet = workbook.getSheet(sheetName);

		if (sheet == null) {
			return "sheet does not exist! " + sheetName;
		}
		XSSFRow row = sheet.getRow(rowIndex);
		if (row == null) {
			return "row does not exist! " + rowIndex;
		}
		XSSFCell cell = row.getCell(cellIndex);
		if (cell == null) {
			return "cell does not exist! " + cellIndex;
		}

		return cell.toString();
	}
	
	/**This method write cellvalue for xlsx for sheetname, rowindex,colindex and value  passed
	 * 
	 * @param sheetName
	 * @param rowIndex
	 * @param cellIndex
	 * @param value
	 * @throws IOException
	 */

	public void writeCellValue(String sheetName, int rowIndex, int cellIndex, String value) throws IOException {

		XSSFSheet sheet = workbook.getSheet(sheetName);

		XSSFRow row = sheet.getRow(rowIndex);
		if (row == null) {
			row = sheet.createRow(rowIndex);
		}

		XSSFCell cell = row.getCell(cellIndex);
		if (cell == null) {
			cell = row.createCell(cellIndex);
		}

		cell.setCellValue(value);

		fos = new FileOutputStream(new File(excelpath));
		workbook.write(fos);
		fos.close();
	}

	
	
	public static void main(String[] args) throws IOException {
		// String projectPath= System.getProperty("user.dir");
		File excelFile = new File("C:\\Users\\JAYDEBJANA\\Desktop\\Products_Data.xlsx");
		ExcelXUtil excel = new ExcelXUtil("C:\\Users\\JAYDEBJANA\\Desktop\\Products_Data.xlsx");
		System.out.println(excel.getRowCount("Sheet1"));
		System.out.println(excel.getColumnCount("Sheet1", 1));
		System.out.println(excel.getCellValue("Sheet1", 1, 1));
		excel.writeCellValue("Sheet1", 1, 4, "Test Output");

	}

}
