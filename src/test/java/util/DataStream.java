package util;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class DataStream {
	public DataStream() {
	}

	public static String[][] getDataByTableName(String excelFilePath, String sheetName, String tableName, int executeRow) {
		String[][] datatTable = getDataByTableName(excelFilePath, sheetName, tableName);
		if (executeRow < 0) {
			datatTable = getExecutionTable(datatTable);
		}

		if (executeRow > 0) {
			datatTable = (String[][])Arrays.copyOfRange(datatTable, executeRow - 1, executeRow);
		}

		return datatTable;
	}

	public static String[][] getExecutionTable(String[][] rawTable) {
		int executionNumber = 0;
		String[][] outputFinalTable = rawTable;
		int j = rawTable.length;

		for(int var5 = 0; var5 < j; ++var5) {
			String[] o = outputFinalTable[var5];
			if (!o[0].trim().equals("")) {
				++executionNumber;
			}
		}

		outputFinalTable = new String[executionNumber][rawTable[0].length];
		j = 0;
		String[][] var9 = rawTable;
		int var10 = rawTable.length;

		for(int var7 = 0; var7 < var10; ++var7) {
			String[] o = var9[var7];
			if (!o[0].trim().equals("")) {
				outputFinalTable[j] = o;
				++j;
			}
		}

		return outputFinalTable;
	}

	public static String[][] getDataByTableName(String excelFilePath, String sheetName, String tableName) {
		String[][] tabArray = (String[][])null;

		try {
			InputStream stream = DataStream.class.getClassLoader().getResourceAsStream(excelFilePath);
			Workbook workbook = Workbook.getWorkbook(stream);
			Sheet sheet = workbook.getSheet(sheetName);
			Cell tableStart = sheet.findCell(tableName);
			int startRow = tableStart.getRow();
			int startCol = tableStart.getColumn();
			Cell endDashCell = sheet.findCell(tableName, startCol + 1, startRow + 1, 100, 64000, false);
			int endDashRow = endDashCell.getRow();
			int endCol = endDashCell.getColumn();
			tabArray = new String[endDashRow - startRow - 1][endCol - startCol - 1];
			int ci = 0;

			for(int i = startRow + 1; i < endDashRow; ++ci) {
				int cj = 0;

				for(int j = startCol + 1; j < endCol; ++cj) {
					tabArray[ci][cj] = sheet.getCell(j, i).getContents();
					++j;
				}

				++i;
			}
		} catch (Exception var17) {
			var17.printStackTrace();
		}

		return tabArray;
	}

	public static String[][] getTableArray(String excelFilePath, String sheetName) {
		String tableName = "---";
		String[][] tabArray = (String[][])null;

		try {
			Workbook workbook = Workbook.getWorkbook(new File(excelFilePath));
			Sheet sheet = workbook.getSheet(sheetName);
			Cell firstTthreeDashCell = sheet.findCell(tableName);
			int lableRow = firstTthreeDashCell.getRow() + 1;
			Cell secondDashCell = sheet.findCell(tableName, 0, lableRow, 0, 64000, false);
			int firstDataRow = secondDashCell.getRow() + 1;
			Cell endDashCell = sheet.findCell(tableName, 0, firstDataRow, 0, 64000, false);
			int endDashRow = endDashCell.getRow();
			List<Integer> labelList = new ArrayList();
			int totalColumns = sheet.getColumns();

			for(int c = 0; c < totalColumns; ++c) {
				String columnName = sheet.getCell(c, lableRow).getContents();
				if (!columnName.trim().isEmpty()) {
					labelList.add(c);
				}
			}

			List<String> rowList = new ArrayList();
			List<List<String>> tabList = new ArrayList();

			int rowNumber;
			int i;
			for(rowNumber = firstDataRow; rowNumber < endDashRow; ++rowNumber) {
				Iterator var17 = labelList.iterator();

				while(var17.hasNext()) {
					i = (Integer)var17.next();
					rowList.add(sheet.getCell(i, rowNumber).getContents());
				}

				tabList.add(new ArrayList(rowList));
				rowList.clear();
			}

			rowNumber = tabList.size();
			int colNumber = labelList.size();
			tabArray = new String[rowNumber][colNumber];

			for(i = 0; i < rowNumber; ++i) {
				for(int j = 0; j < colNumber; ++j) {
					tabArray[i][j] = (String)((List)tabList.get(i)).get(j);
				}
			}
		} catch (Exception var20) {
			var20.printStackTrace();
		}

		return tabArray;
	}
}
