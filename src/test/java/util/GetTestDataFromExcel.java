package util;

import org.testng.annotations.DataProvider;

/**
 * 
 * @author anthony
 * @date Dec 4, 2017
 * @updateTime 2:54:23 PM
 */

public class GetTestDataFromExcel {

	/*
	 * Read test data from test data file
	 */
	public static Object[][] getDataTable(String xlFilePath, String sheetName, String tableName) {

		String[][] dtatTable = (String[][]) DataStream.getDataByTableName(xlFilePath, sheetName, tableName, 0);
		return dtatTable;
	}

	@DataProvider(name = "bindCardUserInfo")
	public static Object[][] bindCardUserData() throws Exception {
		Object[][] accountInfo = getDataTable("user.xls", "userInfo", "userInfo");
		return accountInfo;
	}
}
