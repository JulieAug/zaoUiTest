//package api.uiTest;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxOptions;
//import org.openqa.selenium.firefox.FirefoxProfile;
//import org.openqa.selenium.remote.DesiredCapabilities;
//
////Firefox浏览器驱动类
//public class FFDriver {
//	//将webdriver对象设置为成员变量。
//	private WebDriver driver = null;
//
//	public FFDriver(String propath, String driverpath) {
//		// 设置 Firefox驱动的路径
//		System.setProperty("webdriver.gecko.driver", driverpath);
//		// 设置Firefox的安装目录，判断输入的propath，firefox启动路径参数是否为空字符串，如果为空，则不需要再设置启动路径。
//		if (propath != null && propath.length() > 0)
//		System.setProperty("webdriver.firefox.bin", propath);
//		//firefox用户文件和配置的设置。
//		FirefoxOptions firefoxOptions = new FirefoxOptions() {
//			FirefoxProfile profile = new FirefoxProfile();
//		};
//
//		// 创建一个 Firefox的浏览器实例
//		try {
//			driver = new FirefoxDriver(firefoxOptions);
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("log--error：创建driver失败！！");
//		}
//
//	}
//
//	public WebDriver getdriver() {
//		return this.driver;
//	}
//}