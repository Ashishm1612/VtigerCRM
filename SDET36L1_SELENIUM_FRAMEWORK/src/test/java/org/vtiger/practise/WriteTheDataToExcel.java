package org.vtiger.practise;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteTheDataToExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
	FileInputStream fisExcel= new FileInputStream("./src/test/resources/testData.xlsx");
	Workbook workbook= WorkbookFactory.create(fisExcel);
	Sheet sheet = workbook.getSheet("Contacts");
	Row row = sheet.getRow(12);
	Cell cell = row.createCell(2);
	cell.setCellValue("fail");
	FileOutputStream fosExcel= new FileOutputStream("./src/test/resources/testData.xlsx");
	workbook.write(fosExcel);
	System.out.println("Data Entered");
	workbook.close();
	}

}
