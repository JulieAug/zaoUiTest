package bigBoy.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import redis.clients.jedis.Jedis;
import util.AppiumDriverUtil;
import util.SwipeUtil;

import java.util.concurrent.TimeUnit;

import static bigBoy.app.RedisTest.connect;

/**
 * @program: hupuTest
 * @description: 安卓登录
 * @author: zhuli
 * @create: 2020-10-30 15:02
 **/
public class homePgaeTest {
    public static AppiumDriver<WebElement> driver;

    private String phone = "18260356798";

    @BeforeTest
    public void setPreCondition() throws Exception {
        driver = AppiumDriverUtil.createDriver();
    }

    /**
     * 首页点赞唤起登录
     */
    @Test(priority = 1)
    public void loginTest(){
        driver.findElement(By.xpath("//*[@text='登录']")).click();//点击首页下方登录按钮
        driver.findElement(By.id("com.bigboy.zao:id/confirmImg")).click();//勾选阅读同意按钮
        driver.findElement(By.id("com.bigboy.zao:id/loginHpIv")).click();//点击虎扑登录
        driver.findElement(By.id("com.bigboy.zao:id/phonetEt")).sendKeys("18260356798");//输入虎扑账号
        driver.findElement(By.id("com.bigboy.zao:id/passwordEt")).sendKeys("hupu123456");//输入虎扑账号密码
        driver.findElement(By.id("com.bigboy.zao:id/loginBtn")).click();//点击登录确定按钮
    }

    /**
     * 首页
     * @throws InterruptedException
     */
    @Test(priority = 2)
    public void homePageTest() throws InterruptedException {
        //点击商品资源位后回退到首页
//        driver.findElement(By.id("com.bigboy.zao:id/topic_iv")).click();
//        Thread.sleep(3000);
//        driver.navigate().back();// 后退

        //点击热门商品后回退到首页
//        driver.findElement(By.xpath("//*[@resource-id='com.bigboy.zao:id/standLayout']/android.view.ViewGroup[1]")).click();
//        Thread.sleep(3000);
//        driver.navigate().back();

        //点击点赞唤起登录
//        driver.findElement(By.id("com.bigboy.zao:id/homeLikeIv")).click();

        driver.findElement(By.id("com.bigboy.zao:id/homeLikeIv")).click();
        //跳转到登录页面
        driver.findElement(By.id("com.bigboy.zao:id/phonetEt")).sendKeys(phone);
        driver.findElement(By.id("com.bigboy.zao:id/verfiyTv")).click();
        Jedis jedis = connect();
        String code=jedis.get("security_code_key_"+phone);
        driver.findElement(By.id("com.bigboy.zao:id/verfyCodeEt")).sendKeys(code);
        driver.findElement(By.id("com.bigboy.zao:id/loginBtn")).click();
        //进行点赞操作
        driver.findElement(By.id("com.bigboy.zao:id/homeLikeIv")).click();
        //取消点赞
        driver.findElement(By.id("com.bigboy.zao:id/homeLikeIv")).click();

        SwipeUtil.swipeUpandDown(driver);//上下滑动推荐页面
    }


//    @AfterClass
//    public void tearDown() throws Exception
//    {
//        driver.quit();
//    }


}
