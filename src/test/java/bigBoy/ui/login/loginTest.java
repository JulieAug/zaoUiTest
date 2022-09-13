package bigBoy.ui.login;

import bigBoy.ui.runTest;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import util.AppiumDriverUtil;

/**
 * @program: zaoUiTest
 * @description: 登录造物App
 * @author: zhuli
 * @create: 2021-12-13 15:58
 **/
public class loginTest {
    public static AppiumDriver<WebElement> driver = runTest.driver;
    private static String phone = "11117888454";
    private static String password = "hupu123456";


    /**
     * 首页唤起登录
     */
    @Test(priority = 1)
    public static void loginTest(){
        driver.findElement(By.xpath("//*[@text='首页']")).click();
        //点击首页下方登录按钮
        driver.findElement(By.xpath("//*[@text='登录']")).click();
        //勾选阅读同意按钮
        driver.findElement(By.id("com.bigboy.zao:id/confirmImg")).click();
        //点击虎扑登录，输入账号密码
        driver.findElement(By.id("com.bigboy.zao:id/loginHpIv")).click();
        driver.findElement(By.id("com.bigboy.zao:id/phonetEt")).sendKeys(phone);
        driver.findElement(By.id("com.bigboy.zao:id/passwordEt")).sendKeys(password);
        //点击登录确定按钮
        driver.findElement(By.id("com.bigboy.zao:id/loginBtn")).click();
    }
}
