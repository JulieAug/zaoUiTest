package bigBoy.ui;

import bigBoy.ui.bbs.articleTest;
import bigBoy.ui.bbs.threadTest;
import bigBoy.ui.login.loginTest;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import util.AppiumDriverUtil;

/**
 * @program: zaoUiTest
 * @description: 测试运行类
 * @author: zhuli
 * @create: 2021-12-13 18:26
 **/
public class runTest {
    public static AppiumDriver<WebElement> driver;

    @BeforeTest
    public void setPreCondition(){
        driver = AppiumDriverUtil.createDriver();
    }

    @Test
    public void runTest() throws Exception {
        loginTest.loginTest();
//        threadTest.addThreadTest();
        articleTest.addArticleTest();
    }
}
