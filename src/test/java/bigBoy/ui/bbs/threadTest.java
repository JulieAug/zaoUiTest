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
import util.RandomUtil;
import util.SwipeUtil;

import java.io.IOException;
import java.util.List;

/**
 * @program: zaoUiTest
 * @description: 帖子模块
 * @author: zhuli
 * @create: 2021-11-29 15:09
 **/
public class threadTest {
//    public static AppiumDriver<WebElement> driver = runTest.driver;
    public static AppiumDriver<WebElement> driver;

    @BeforeTest
    public void setPreCondition(){
        driver = AppiumDriverUtil.createDriver();
    }

//    private String title;

    /**
     * 发布日志
     */
    @Test(priority = 1)
    public void addThreadTest(){
//        title = DateUtil.getDate()+"Android自动化回归日志"+ RandomUtil.getDigits(3);
        //点击发布帖子按钮
        driver.findElement(By.id("com.bigboy.zao:id/publishImg")).click();
        //点击发布日志
        driver.findElement(By.xpath("//*[@text='发日志']")).click();
        //点击添加图片
        driver.findElement(By.id("com.bigboy.zao:id/choose_pic_iv")).click();

//        driver.findElement(By.xpath("//*[@text='添加图片']")).click();
        // 获取所有图片
        List<WebElement> images = driver.findElements(By.id("com.bigboy.zao:id/check_view"));
        images.get(0).click();
        images.get(1).click();
        images.get(2).click();
        driver.findElement(By.id("com.bigboy.zao:id/button_apply")).click();//选择图片完成
        driver.findElement(By.id("com.bigboy.zao:id/bbsTitleEt")).sendKeys(DateUtil.getDate()+"——Android自动化回归日志——"+ RandomUtil.getDigits(3));
        driver.findElement(By.id("com.bigboy.zao:id/bbsContentEt")).sendKeys("回归帖子正文内容");
        //选择话题列表中的第2个话题
        List<WebElement> topic = driver.findElements(By.id("com.bigboy.zao:id/tagNameTv"));
        topic.get(1).click();

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

        //存草稿
        driver.findElement(By.xpath("//*[@text='存草稿']")).click();
        //点击取消按钮，返回至首页
        driver.findElement(By.id("com.bigboy.zao:id/cancelIv")).click();
        //再次点击发帖按钮，编辑帖子进行发布
        driver.findElement(By.id("com.bigboy.zao:id/publishImg")).click();
        driver.findElement(By.xpath("//*[@text='发日志']")).click();

        driver.findElement(By.id("com.bigboy.zao:id/bbsContentEt")).sendKeys("------回归帖子正文内容编辑后------");
        driver.findElement(By.id("com.bigboy.zao:id/publishBtn")).click();//点击发贴按钮
    }

    /**
     * 发布评论
     */
    @Test(priority = 2)
    public void addReplyTest(){
        //点击"我的"tab
        driver.findElement(By.xpath("//*[@resource-id='com.bigboy.zao:id/tabLayout']/android.view.ViewGroup[4]")).click();
        //点击用户昵称，跳转到个人详情页
        driver.findElement(By.id("com.bigboy.zao:id/userNameIv")).click();
        //点击进入日志详情页
        driver.findElement(By.id("com.bigboy.zao:id/topic_title")).click();
        //向上滑动页面
        SwipeUtil.swipeUp(driver);
        //点击评论框
        driver.findElement(By.id("com.bigboy.zao:id/inputTv")).click();
        //输入评论内容
        driver.findElement(By.id("com.bigboy.zao:id/inputEt")).sendKeys("评论一下日志"+RandomUtil.getDigits(2));
        //点击发送评论
        driver.findElement(By.id("com.bigboy.zao:id/commentPublishTv")).click();

        //点击一级评论
        driver.findElement(By.id("com.bigboy.zao:id/userContentTv")).click();
        //输入评论内容
        driver.findElement(By.id("com.bigboy.zao:id/inputEt")).sendKeys("回复评论"+RandomUtil.getDigits(2));
        //点击发送评论
        driver.findElement(By.id("com.bigboy.zao:id/commentPublishTv")).click();

    }
    }
