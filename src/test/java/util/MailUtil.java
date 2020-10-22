package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Anthony on 2017/06/08.
 */
public class MailUtil {
	private String rootPath = System.getProperty("user.dir");

	public String readFile(String filePath)

	{
		String str = "";
		String reportFile = rootPath + filePath;
		File file = new File(reportFile);
		try {
			FileInputStream in = new FileInputStream(file);
			int size = in.available();
			byte[] buffer = new byte[size];
			in.read(buffer);
			in.close();
			//str = new String(buffer, "GB2312");
			str = new String(buffer, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;

	}

}
