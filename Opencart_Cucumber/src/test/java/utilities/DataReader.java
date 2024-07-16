package utilities;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataReader {
	
	public static List<HashMap<String,String>> getExcelTestData(String filepath, String sheetname){
		
		List<HashMap<String,String>> testData = new ArrayList<>();
		
		try {
			FileInputStream fs = new FileInputStream(filepath);
			XSSFWorkbook workbook = new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheet(sheetname);
			Row headerRow = sheet.getRow(0);
			
			for(int i=1;i<sheet.getPhysicalNumberOfRows();i++) {
				Row currentRow = sheet.getRow(i);
				HashMap<String,String> excelTestData = new HashMap<>();
				for(int j=0;j<currentRow.getPhysicalNumberOfCells();j++) {
					Cell currentCellData = currentRow.getCell(j);
					excelTestData.put(headerRow.getCell(j).getStringCellValue(), currentCellData.getStringCellValue());	
				}
				testData.add(excelTestData);	
			}
			workbook.close();
			fs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return testData;
		
	}
 
}
