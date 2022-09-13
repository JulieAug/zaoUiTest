package bigBoy.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import redis.clients.jedis.Jedis;
import util.AppiumDriverUtil;
import util.SwipeUtil;


import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static bigBoy.app.RedisTest.connect;

/**
 * @program: hupuTest
 * @description: Ui单个操作测试
 * @author: zhuli
 * @create: 2020-11-03 14:20
 **/
public class uiTest {
    public static AppiumDriver<WebElement> driver;

    @BeforeTest
    public void setPreCondition() throws Exception {
        driver = AppiumDriverUtil.createDriver();
    }

//    @Test
    public void test() throws MalformedURLException, InterruptedException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "d6eae185");
        capabilities.setCapability("platformVersion", "10");
        capabilities.setCapability("appPackage", "com.hupu.bluebook");
        capabilities.setCapability("appActivity", "com.hupu.bluebook.home.HomeActivity");
        capabilities.setCapability("noReset", true);
        capabilities.setCapability("sessionOverride", true);
        driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);//隐式等待 10秒

        //点击看展tab
        driver.findElement(By.xpath("//*[@resource-id='com.hupu.bluebook:id/tabLayout']/android.view.ViewGroup[2]")).click();
        Thread.sleep(1000);
    }

private String phone = "18260356798";

    @Test
    public void addBehaviorTest() throws Exception{
        //点击点赞唤起登录
        driver.findElement(By.id("com.hupu.bluebook:id/homeLikeIv")).click();
        //跳转到登录页面
        driver.findElement(By.id("com.hupu.bluebook:id/phonetEt")).sendKeys(phone);
//        driver.findElement(By.id("com.hupu.bluebook:id/verfiyTv")).click();
        Jedis jedis = connect();
        String code=jedis.get("security_code_key_"+phone);
        driver.findElement(By.id("com.hupu.bluebook:id/verfyCodeEt")).sendKeys(code);
        driver.findElement(By.id("com.hupu.bluebook:id/loginBtn")).click();
        //进行点赞操作
        driver.findElement(By.id("com.hupu.bluebook:id/homeLikeIv")).click();
        //取消点赞
        driver.findElement(By.id("com.hupu.bluebook:id/homeLikeIv")).click();
    }
}
