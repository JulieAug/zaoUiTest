package bigBoy.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import util.AppiumDriverUtil;

/**
 * @program: zaoUiTest
 * @description: 个人页面
 * @author: zhuli
 * @create: 2020-11-17 16:27
 **/
public class myTest {
    public static AppiumDriver<WebElement> driver;

    @BeforeTest
    public void setPreCondition() throws Exception {
        driver = AppiumDriverUtil.createDriver();
    }


    @Test(priority = 1)
    public void myTest() throws InterruptedException{
        driver.findElement(By.xpath("//*[@resource-id='com.bigboy.zao:id/tabLayout']/android.view.ViewGroup[4]")).click();
        Thread.sleep(2000);

        //点击首页tab
        driver.findElement(By.xpath("//*[@resource-id='com.bigboy.zao:id/tabLayout']/android.view.ViewGroup[1]")).click();
        Thread.sleep(2000);
    }
}
