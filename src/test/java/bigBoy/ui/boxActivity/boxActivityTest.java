package bigBoy.ui.boxActivity;

import bigBoy.ui.bbs.articleTest;
import bigBoy.ui.login.loginTest;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import util.AppiumDriverUtil;

import java.util.concurrent.TimeUnit;

/**
 * @program: zaoUiTest
 * @description: 抽赏
 * @author: zhuli
 * @create: 2022-07-06 15:29
 **/
public class boxActivityTest {
    public static AppiumDriver<WebElement> driver;

    @BeforeTest
    public void setPreCondition(){
        driver = AppiumDriverUtil.createDriver();
    }

    @Test
    public void boxActivityTest() throws Exception {
        driver.findElement(By.xpath("//*[@text='造物赏']")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@text='弹幕test']")).click();
//        driver.findElement(By.xpath("//*[@text='19.9盲盒潮玩箱']")).click();

        //勾选阅读同意按钮
//        driver.findElement(By.id("com.bigboy.zao:id/confirmImg")).click();
//        Thread.sleep(2000);
//        driver.findElement(By.id("com.bigboy.zao:id/loginBtn")).click();
    }
}
