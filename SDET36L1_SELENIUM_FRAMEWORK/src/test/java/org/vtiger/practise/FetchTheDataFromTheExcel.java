package org.vtiger.practise;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FetchTheDataFromTheExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		DataFormatter dataFormat=new DataFormatter();
		FileInputStream fisExcel=new FileInputStream("./src/test/resources/testData.xlsx");
		Workbook workbook=WorkbookFactory.create(fisExcel);
		Sheet sheet = workbook.getSheet("Contacts");
		for(int i=0;i<=sheet.getLastRowNum();i++)
		{
			String data = dataFormat.formatCellValue(sheet.getRow(i).getCell(1));
			if(data.equalsIgnoreCase("ORGANIZATION NAME "))
			{
				System.out.println(sheet.getRow(i+1).getCell(1));
				break;
			}
		}
		workbook.close();

	}

}
