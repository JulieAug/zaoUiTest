package bigBoy.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import util.AppiumDriverUtil;

/**
 * @program: zaoUiTest
 * @description: 商品列表页
 * @author: zhuli
 * @create: 2020-11-17 16:26
 **/
public class goodsTest {
    public static AppiumDriver<WebElement> driver;

    @BeforeTest
    public void setPreCondition() throws Exception {
        driver = AppiumDriverUtil.createDriver();
    }

    /**
     * 商品列表页
     * @throws InterruptedException
     */
    @Test(priority = 1)
    public void buyTest() throws InterruptedException{
        //点击商品tab
        driver.findElement(By.xpath("//*[@text='商品']")).click();
        //点击商品页面搜索框
        driver.findElement(By.xpath("//*[@resource-id='com.bigboy.zao:id/searchClickLayout']")).click();
        //点击资源位——商城首页第一个资源位
        driver.findElement(By.xpath("//*[@class='androidx.recyclerview.widget.RecyclerView']/android.widget.LinearLayout[1]")).click();

        //点击资源位——商城首页第一个资源位（利用text定位，text文案会变化）
//        driver.findElement(By.xpath("//*[@text='尖货秒杀']")).click();

    }
}
