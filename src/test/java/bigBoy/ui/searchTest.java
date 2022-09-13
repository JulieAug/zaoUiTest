package bigBoy.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import util.AppiumDriverUtil;
import util.SwipeUtil;

/**
 * @program: zaoUiTest
 * @description: 搜索页面测试
 * @author: zhuli
 * @create: 2020-11-17 16:17
 **/
public class searchTest {
    public static AppiumDriver<WebElement> driver;

    @BeforeTest
    public void setPreCondition() throws Exception {
        driver = AppiumDriverUtil.createDriver();
    }

    /**
     * 点击进入搜索页面
     * @throws InterruptedException
     */
    @Test(priority = 1)
    public void searchTest() throws InterruptedException{
        //点击热搜词进行搜索
        driver.findElement(By.xpath("//*[@class='android.widget.LinearLayout']/android.view.ViewGroup[1]")).click();
        driver.findElement(By.id("com.bigboy.zao:id/searchTv")).click();
        SwipeUtil.swipeUpandDown(driver);
        driver.findElement(By.xpath("//*[@class='android.widget.LinearLayout']/android.view.ViewGroup[2]")).click();//点击高达tab
        driver.findElement(By.xpath("//*[@class='android.widget.LinearLayout']/android.view.ViewGroup[3]")).click();
        driver.findElement(By.xpath("//*[@class='android.widget.LinearLayout']/android.view.ViewGroup[4]")).click();
        driver.findElement(By.xpath("//*[@class='android.widget.LinearLayout']/android.view.ViewGroup[5]")).click();
        for(int i=0;i<2;i++){
            driver.findElement(By.xpath("//*[@text='销量']")).click();//点击销量tab
            SwipeUtil.swipeUpandDown(driver);
            driver.findElement(By.xpath("//*[@text='价格']")).click();
            SwipeUtil.swipeUpandDown(driver);
        }
    }

}
