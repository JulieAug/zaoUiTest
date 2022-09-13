package bigBoy.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import util.AppiumDriverUtil;
import util.SwipeUtil;

import java.util.concurrent.TimeUnit;

/**
 * @program: zaoUiTest
 * @description: 展览tab页
 * @author: zhuli
 * @create: 2020-11-17 16:23
 **/
public class showTest {
    public static AppiumDriver<WebElement> driver;

    @BeforeTest
    public void setPreCondition() throws Exception {
        driver = AppiumDriverUtil.createDriver();
    }

    /**
     * 展览页
     * @throws InterruptedException
     */
    @Test(priority = 1)
    public void showTest() throws InterruptedException{
        //点击看展tab
        driver.findElement(By.xpath("//*[@resource-id='com.bigboy.zao:id/tabLayout']/android.view.ViewGroup[2]")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);//隐式等待 10秒
        //点击橱窗下的作品
        driver.findElement(By.xpath("//*[@resource-id='com.bigboy.zao:id/standLayout']/android.view.ViewGroup[1]")).click();
        driver.navigate().back();// 后退

//        SwipeUtil.swipeUpandDown(driver);
//        SwipeUtil.swipeLeftandRight(driver);
//        SwipeUtil.swipeUpandDown(driver);

    }
}
