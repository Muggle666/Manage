package com.hao.onlineExam.excel;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.NotOLE2FileException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hao.onlineExam.model.vo.ExamUserVO;
import com.hao.onlineExam.util.MD5Utils;

public class ExcelUtils {
	public static final String OFFICE_EXCEL_2003_POSTFIX = ".xls";
	public static final String OFFICE_EXCEL_2007_POSTFIX = ".xlsx";

	public static List<ExamUserVO> getStudentListByExcel(Workbook wk) throws NoSuchAlgorithmException{
		
		final String GENDER_MALE_STRING = "男",GENDER_FEMALE_STRING = "女";
		final Integer GENDER_MALE_INTEGER = 0 , GENDER_FEMALE_INTEGER = 1;
		List<ExamUserVO> resultList = new ArrayList<ExamUserVO>();
		Sheet sheet = wk.getSheetAt(0);
		int maxCellNumber = 0;
		int maxRowNumber = sheet.getLastRowNum();
		ExamUserVO vo = null;
		for(int currentRowNumber = 1;currentRowNumber <= maxRowNumber;currentRowNumber++){
			Row row = sheet.getRow(currentRowNumber);
			maxCellNumber = row.getLastCellNum();
			vo = new ExamUserVO();
			for(int currentCellNumber = 0;currentCellNumber <= maxCellNumber;currentCellNumber++){
				Cell cell = row.getCell(currentCellNumber);
				switch(currentCellNumber){
				case 0 :
					vo.setUserId((String) getCellVaule(cell));
					break;
				case 1 :
					vo.setUserName((String) getCellVaule(cell));
					break;
				case 2 :
					/*String gender =(String) getCellVaule(cell);
					if(GENDER_MALE_INTEGER.equals(gender)){
						vo.setGender(GENDER_MALE_STRING);
					}else if(GENDER_FEMALE_INTEGER.equals(gender)){
						vo.setGender(GENDER_FEMALE_STRING);
					}*/
					vo.setGender(getCellVaule(cell));
					break;
				case 3:
					vo.setTel((String) getCellVaule(cell));
					break;
				case 4:
					vo.setEmail((String) getCellVaule(cell));
					break;
				case 5:
					vo.setAddress((String) getCellVaule(cell));
					break;
				case 6:
					vo.setBirthday((String) getCellVaule(cell));
					break;
				case 7:
					String decodingPassword = (String) getCellVaule(cell);
					vo.setPassword(MD5Utils.getMD5Code(decodingPassword));
					break;
				}
			}
			resultList.add(vo);
		}
		return resultList;
	}
	
	
	
	private static String getCellVaule(Cell cell) {
		if(cell == null){
			return "";
		}
		if(cell.getCellType() == cell.CELL_TYPE_BOOLEAN){
			return String.valueOf(cell.getBooleanCellValue());
		}else if(cell.getCellType() == cell.CELL_TYPE_NUMERIC){
			DecimalFormat df = new DecimalFormat("0");
			return df.format(cell.getNumericCellValue());
		}else{
			return String.valueOf(cell.getStringCellValue());
		}
	}
	



	public static Workbook openWorkbook(InputStream is, String postfix) {
		Workbook hssfWorkbook = null;
		try {
			if (OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
				hssfWorkbook = new HSSFWorkbook(is);
			} else if (OFFICE_EXCEL_2007_POSTFIX.equals(postfix)) {
				hssfWorkbook = new XSSFWorkbook(is);
			}
		} catch (IOException e) {
			if (e instanceof NotOLE2FileException) {
				return null;
			}
			e.printStackTrace();
		}
		return hssfWorkbook;
	}
}
