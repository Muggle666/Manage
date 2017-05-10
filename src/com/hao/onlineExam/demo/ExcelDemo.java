package com.hao.onlineExam.demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDemo {
	public static void main(String[] args) {
		String path = "D://book1.xlsx";
		try {
			InputStream is = new FileInputStream(path);
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
			for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
				XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
				if (xssfSheet == null) {
					continue;
				}
				for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
					XSSFRow xssfRow = xssfSheet.getRow(rowNum);
					if (xssfRow != null) {
						XSSFCell subject = xssfRow.getCell(0);
						XSSFCell book = xssfRow.getCell(1);
						XSSFCell totalScore = xssfRow.getCell(2);
						System.out.println(getValue(subject));
						System.out.println(getValue(book));
						System.out.println(getValue(totalScore));
					}
				}
			}
			xssfWorkbook.close();
			is.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("static-access")
	private static String getValue(XSSFCell xssfRow){
		if(xssfRow == null){
			return "";
		}
		if(xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN){
			return String.valueOf(xssfRow.getBooleanCellValue());
		}else if(xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC){
			DecimalFormat df = new DecimalFormat("0");
			return df.format(xssfRow.getNumericCellValue());
		}else{
			return String.valueOf(xssfRow.getStringCellValue());
		}
	}
}
