package org.vtiger.practise;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FetchTheDataFromExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		DataFormatter dataFormat= new DataFormatter();
		FileInputStream fisExcel= new FileInputStream("./src/test/resources/testData.xlsx");
		Workbook workbook= WorkbookFactory.create(fisExcel); //HSSF ->.xls,//XSSF ->xlsx
		String data = dataFormat.formatCellValue(workbook.getSheet("Contacts").getRow(5).getCell(1));
		System.out.println(data);
		workbook.close();

	}

}
