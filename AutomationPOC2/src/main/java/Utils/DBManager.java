package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DBManager {
	public Map<String, String> testData;

	public Map<String, String> getExcelData(String excelPath, String sheetName, String scenarioName) throws Exception {
		testData = new LinkedHashMap<>();
		testData.clear();
		FileInputStream file = new FileInputStream(new File(excelPath));
		Workbook workbook = new XSSFWorkbook(file);
		Sheet sheet = workbook.getSheet(sheetName);

		// Check if the sheet exists
		if (sheet == null) {
			System.out.println("Sheet not found: " + sheetName);
			return null;
		} else {
			System.out.println("Sheet loaded successfully!");
		}

		for (Row row : sheet) {
			Row headerRow = sheet.getRow(0);

			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row dataRow = sheet.getRow(i);
				Cell scenario = dataRow.getCell(0);

				if (scenario != null && scenario.getStringCellValue().equalsIgnoreCase(scenarioName)) {

					for (int j = 0; j < headerRow.getLastCellNum(); j++) {
						Cell headerCell = headerRow.getCell(j);
						Cell dataCell = dataRow.getCell(j);

						String key = headerCell != null ? headerCell.toString() : "";
						String value = dataCell != null ? dataCell.toString() : "";
						testData.put(key, value);

					}break;

				}
			}
		}
		return testData;

	}
}
