package org.tyss.genericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This class contains methods to read and fetch data from excel file
 * @author Ashish
 *
 */
public class ExcelUtility {
	private Workbook workbook;
	/**
	 * This method is used to initialize the excel file
	 * @param excelPath
	 */
	public void initializeExcelFile(String excelPath) {
		//to get the test data from exel file
				FileInputStream fisexcel;
				try {
					fisexcel = new FileInputStream(excelPath);
					 workbook= WorkbookFactory.create(fisexcel);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
					
				 catch (EncryptedDocumentException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
	}
	public String getDataFromExcel(int rowNumber,int cellNUmber,String sheetName ) {
		
		return new DataFormatter().formatCellValue( workbook.getSheet(sheetName).getRow(rowNumber).getCell(cellNUmber));
	}

	
public void workbookClose() {
	try {
		workbook.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
	

}
