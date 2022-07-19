package org.vtiger.practise;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FetchTheDataFromTheExcel1 {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		DataFormatter dataFormat=new DataFormatter();
		FileInputStream fisExcel=new FileInputStream("./src/test/resources/testData.xlsx");
Workbook workbook=WorkbookFactory.create(fisExcel);
Sheet sheet = workbook.getSheet("Sheet1");
Row row = sheet.getRow(1);
 int rowNum = sheet.getLastRowNum();
 int cellNum = row.getLastCellNum();
 String[][] str= new String[rowNum][cellNum];
 for(int i=1;i<=rowNum;i++)
 {
	 for(int j=0;j<cellNum;j++)
	 {
		 str[i-1][j]=dataFormat.formatCellValue(sheet.getRow(i).getCell(j));
	 }
 }
/* for(int i=1;i<str.length;i++) {
	 for(int j=0;j<str.length;j++)
	 {
		 
	 }
 }*/
 System.out.println(str[1][2]);
 
 workbook.close();
	}

}
