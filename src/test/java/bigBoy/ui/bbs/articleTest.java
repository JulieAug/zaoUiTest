package bigBoy.ui.bbs;

import bigBoy.ui.runTest;
import cn.hutool.core.util.RuntimeUtil;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import util.AppiumDriverUtil;
import util.DateUtil;
import util.SwipeUtil;

import java.io.IOException;
import java.util.List;

/**
 * @program: zaoUiTest
 * @description: 文章测试
 * @author: zhuli
 * @create: 2021-12-13 16:03
 **/
public class articleTest {
    public static AppiumDriver<WebElement> driver = runTest.driver;

//    @BeforeTest
    public void setPreCondition(){
        driver = AppiumDriverUtil.createDriver();
    }

    /**
     * 发布文章
     */
    @Test(priority = 1)
    public static void addArticleTest() throws InterruptedException {
        //点击发布文章按钮
        driver.findElement(By.id("com.bigboy.zao:id/publishImg")).click();
        //点击发布文章
        driver.findElement(By.xpath("//*[@text='写文章']")).click();
        //点击添加话题
        List<WebElement> topic = driver.findElements(By.id("com.bigboy.zao:id/tagNameTv"));
        topic.get(0).click();

        driver.findElement(By.id("com.bigboy.zao:id/bbsTitleEt")).sendKeys("Android自动化回归文章"+ DateUtil.getCurrentDate());
        driver.findElement(By.xpath("//*[@text='编辑正文（最少20字+1张图片或视频）']")).sendKeys("输入文章内容测试" +
                "输入文章内容测试输入文章内容测试输入文章内容测试输入文章内容测试输入文章内容测试");

        //点击添加图片
        driver.findElement(By.id("com.bigboy.zao:id/choose_pic_iv")).click();
        // 获取所有图片
        List<WebElement> images = driver.findElements(By.id("com.bigboy.zao:id/check_view"));
        images.get(0).click();
        images.get(1).click();
        images.get(2).click();
        images.get(3).click();
        driver.findElement(By.id("com.bigboy.zao:id/button_apply")).click();//选择图片完成
        //输入文章描述
        driver.findElement(By.id("com.bigboy.zao:id/pic_descript")).sendKeys("文章中图片描述限制50个字哈 testing");

        //点击添加视频
        driver.findElement(By.id("com.bigboy.zao:id/choose_video_iv")).click();
        // 获取所有视频
        List<WebElement> videos = driver.findElements(By.id("com.bigboy.zao:id/check_view"));
        videos.get(0).click();
        videos.get(1).click();
        driver.findElement(By.id("com.bigboy.zao:id/button_apply")).click();//选择视频完成

        //添加商品——先输入关键词搜索商品，再选择商品
        driver.findElement(By.id("com.bigboy.zao:id/addGoodLayout")).click();
        //使用adb命令调起本地的输入法
        RuntimeUtil.execForStr("adb shell ime set com.sohu.inputmethod.sogou/.SogouIME");
        String cmdstr="adb shell input keyevent 66";
        try {
            driver.findElement(By.id("com.bigboy.zao:id/headerTitleTv")).sendKeys("高达5号机");
            Runtime.getRuntime().exec(cmdstr).waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<WebElement> goods = driver.findElements(By.id("com.bigboy.zao:id/goodsNameTv"));
        goods.get(0).click();//点击搜索结果页的 第一个商品
        SwipeUtil.swipeDown(driver);

        //存草稿
        driver.findElement(By.xpath("//*[@text='存草稿']")).click();
        //点击取消--成功回退到首页
        driver.findElement(By.xpath("//*[@text='取消']")).click();
        //再次点击发帖按钮，编辑帖子进行发布
        driver.findElement(By.id("com.bigboy.zao:id/publishImg")).click();
        driver.findElement(By.xpath("//*[@text='写文章']")).click();
//        SwipeUtil.swipeUp(driver);
//        driver.findElement(By.id("com.bigboy.zao:id/bbsTitleEt")).sendKeys("Android自动化回归文章(编辑后…)"+ DateUtil.getCurrentDate());

        //点击预览按钮
        driver.findElement(By.id("com.bigboy.zao:id/preViewBtn")).click();
        SwipeUtil.swipeUpandDown(driver);

        //点击发贴按钮
        driver.findElement(By.id("com.bigboy.zao:id/publishBtn")).click();

        Thread.sleep(2000);
    }
}
