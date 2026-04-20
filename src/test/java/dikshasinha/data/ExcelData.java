package dikshasinha.data;

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

public class ExcelData {

	public static List<String> getData(String testCaseName) throws IOException {
		FileInputStream excelFile = new FileInputStream("/Users/dikshasinha/Downloads/Product_Test_Data.xlsx");
		XSSFWorkbook workBook = new XSSFWorkbook(excelFile);
		XSSFSheet sheet;
		List<String> list = new ArrayList<>();
		int count = workBook.getNumberOfSheets();
		for (int i = 0; i < count; i++) {
			String sheetName = workBook.getSheetName(i);
			System.out.print(sheetName);
			if (sheetName.equals("TestData")) {
				sheet = workBook.getSheetAt(i);
				Iterator<Row> rows = sheet.rowIterator();
				Row row1 = rows.next();
				Iterator<Cell> cells = row1.iterator();
				int k = 0;
				int column = 0;
				while (cells.hasNext()) {
					Cell cell = cells.next();
					String cellValue = cell.getStringCellValue();
					if (cellValue.equals("testcases")) {
						column = k;
						break;
					}
					k++;
				}
				int rowNum = 0;
				while (rows.hasNext()) {
					Row row = rows.next();
					String cellValue = row.getCell(column).getStringCellValue();
					if (cellValue.equals(testCaseName)) {
						Iterator<Cell> cell = row.cellIterator();
						while (cell.hasNext()) {
							Cell curr = cell.next();
							if (curr.getCellType() == CellType.NUMERIC) {
								list.add(String.valueOf(curr.getNumericCellValue()));
							} else if (curr.getCellType() == CellType.STRING) {
								list.add(curr.getStringCellValue());
							}
						}
					}
					rowNum++;
				}

			}
		}
		return list;
	}

}
