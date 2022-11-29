package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelOperations {

	public static Object[][] readExcelData(String inputFile, String sheetName) throws IOException {
		File file = new File(inputFile);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook wb = new XSSFWorkbook(inputStream);
		Sheet sheet = wb.getSheet(sheetName);
		int totalRows = sheet.getLastRowNum();
		int totalCols = sheet.getRow(0).getLastCellNum();

		System.out.println("Total row count is" + totalRows);
		System.out.println("Total columns is" + totalCols);

		Object[][] data = new Object[totalRows][totalCols];

		for (int rowIndex = 0; rowIndex < totalRows; rowIndex++) {

			for (int colIndex = 0; colIndex < totalCols; colIndex++) {

				Cell cell = sheet.getRow(rowIndex + 1).getCell(colIndex);
				if (cell.getCellType() == CellType.STRING)
					data[rowIndex][colIndex] = cell.getStringCellValue();
				else if (cell.getCellType() == CellType.BOOLEAN)
					data[rowIndex][colIndex] = cell.getBooleanCellValue();
				else if (cell.getCellType() == CellType.NUMERIC)
					if (DateUtil.isCellDateFormatted(cell)) {
						SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
						formatter.format(cell.getDateCellValue());
						data[rowIndex][colIndex] = cell.getDateCellValue();
					} else
						data[rowIndex][colIndex] = cell.getNumericCellValue();
			}

		}

		return data;
	}
}
