package com.qa.opencart.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.qa.opencart.constants.AppConstant;

public class ExcelUtil {

	static String path = "./src/test/resource/TestData/TestData.xls";

	public static Object[][] readData(String sheetName) {
		System.out.println(sheetName);
		Object[][] obj = null;

		try {
			FileInputStream fis = new FileInputStream(path);
			try {
				Workbook wbk = WorkbookFactory.create(fis);
				Sheet sheet = wbk.getSheet(sheetName);
				int row =sheet.getLastRowNum();
				int column = sheet.getRow(0).getLastCellNum() ;
				obj = new Object[row][column];
				
				
				for(int i=0; i<row; i++) {
					for(int j=0; j<column; j++) {
						obj[i][j] = sheet.getRow(row).getCell(j).toString();
					}
				}
				
			} catch (InvalidFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
}
