package dikshasinha.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dikshasinha.data.ExcelData;

public class TestSample {

	@Test
	public void testSample() throws IOException {
		ExcelData data = new ExcelData();
		List<String> list = data.getData("Smoke");
		System.out.print(list);
	}
	
	@DataProvider
	public Object[][] excelData() throws IOException {
		FileInputStream excelFile = new FileInputStream("/Users/dikshasinha/Downloads/Excel_Data.xlsx");
		XSSFWorkbook workBook = new XSSFWorkbook(excelFile);
		XSSFSheet sheet = workBook.getSheet("Sheet1");
		Iterator<Row> rows = sheet.rowIterator();
		List<List<String>> list = new ArrayList<>();
		rows.next();
		while(rows.hasNext()) {
			Iterator<Cell> cells = rows.next().cellIterator();
			List<String> sublist = new ArrayList<>();
			while(cells.hasNext()) {
				Cell cell = cells.next();
				if(cell.getCellType()== CellType.NUMERIC) {
					String val = String.valueOf(cell.getNumericCellValue());
					sublist.add(val);
				}else if(cell.getCellType()== CellType.STRING) {
					sublist.add(cell.getStringCellValue());

				}
			}
			list.add(sublist);
		}
		workBook.close();
		Object[][] data = new Object[list.size()][];
	    for (int i = 0; i < list.size(); i++) {
	        data[i] = list.get(i).toArray();
	    }
	    return data;
	}
	
	@Test(dataProvider = "excelData")
	public void myTest(String col1, String col2, String col3) {
	    System.out.println("Data: " + col1 + " " + col2+ " " +col3);
	}

}
