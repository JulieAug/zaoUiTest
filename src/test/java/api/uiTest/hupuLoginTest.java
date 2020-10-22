//package api.uiTest;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.Cookie;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.Test;
//
///**
// * @author:zhuli
// * @description www.hupu.com登录
// * @date 2019/6/27
// */
//public class hupuLoginTest {
//
//    @Test
//    public void hupuLoginTest() throws Exception {
//
//        // 设置系统参数，指定chromedriver的路径。
//        // 启动webdriver，作为我们进行操作的浏览器。
//        System.setProperty("webdriver.chrome.driver", "D:\\webDriver\\chromedriver.exe");
//
//        WebDriver driver = new ChromeDriver();
//        // 访问虎扑登录页面
//        driver.get("https://www.hupu.com/");
//        Cookie hupuSS = new Cookie("cookie","_dacevid3=27850921.3c76.3a30.495c.b50723482f89; __gads=ID=1c253fdf5be4b860:T=1562640718:S=ALNI_Mbks1Vfz0blO7790nsmkyM7IjjJNw; _HUPUSSOID=b0fa5bf5-54a3-4a3a-b8be-7f2fb6f9c060; _CLT=918ebe7bb324d8673460f7af1d701a5c; sajssdk_2015_cross_new_user=1; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2216bd4d6f77056-0cf848b5ee3476-e343166-1049088-16bd4d6f7718a2%22%2C%22%24device_id%22%3A%2216bd4d6f77056-0cf848b5ee3476-e343166-1049088-16bd4d6f7718a2%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_referrer%22%3A%22%22%2C%22%24latest_referrer_host%22%3A%22%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%7D%7D; cid=70068715; g=48776659%7C55Oc57G955Oc57G9; u=48776659|55Oc57G955Oc57G9|ade4|6b401fb552b6b600f796bc4de5cba1b6|52b6b600f796bc4d|aHVwdV8zMjJlOWZlOTFiMmU5YzQ2; us=abfc3693d524f46740aa578fa1a8634c8c387b34d3f25061e47ee2f2f56be7064bc95fd94619387715d1a89347a7d7f8bd53b55945019b2cb1f80800baf19546; ua=26044540; __dacevst=a4ec7e68.e4f595e6|1562674289575");
//        driver.manage().addCookie(hupuSS);
//        driver.get("https://www.hupu.com/");
//        // 查找虎扑登录页面账号，密码的输入框
////        WebElement username  = driver.findElement(By.name("username"));
////        WebElement password  = driver.findElement(By.name("password"));
////        WebElement captcha = driver.findElement(By.id("float-captcha"));
////        WebElement login = driver.findElement(By.id("J_submit"));
////
////        // 在输入框元素中输入账号，密码
////        username .sendKeys("18260356798");
////        password.sendKeys("19960806Zl");
////        login.submit();
//    }
//
//
//
//
//    @Test
//    public void chuanshuoLoginTest() throws Exception {
//
//        // 设置系统参数，指定chromedriver的路径。
//        // 启动webdriver，作为我们进行操作的浏览器。
//        System.setProperty("webdriver.chrome.driver", "D:\\webDriver\\chromedriver.exe");
//        WebDriver driver = new ChromeDriver();
//        driver.get("http://test.mobileapi.hupu.com/chuanshuo/index");
//        Cookie hupuSS = new Cookie("cookie","_dacevid3=27850921.3c76.3a30.495c.b50723482f89; __gads=ID=1c253fdf5be4b860:T=1562640718:S=ALNI_Mbks1Vfz0blO7790nsmkyM7IjjJNw; _HUPUSSOID=b0fa5bf5-54a3-4a3a-b8be-7f2fb6f9c060; _CLT=918ebe7bb324d8673460f7af1d701a5c; sajssdk_2015_cross_new_user=1; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2216bd4d6f77056-0cf848b5ee3476-e343166-1049088-16bd4d6f7718a2%22%2C%22%24device_id%22%3A%2216bd4d6f77056-0cf848b5ee3476-e343166-1049088-16bd4d6f7718a2%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_referrer%22%3A%22%22%2C%22%24latest_referrer_host%22%3A%22%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%7D%7D; cid=70068715; g=48776659%7C55Oc57G955Oc57G9; u=48776659|55Oc57G955Oc57G9|ade4|6b401fb552b6b600f796bc4de5cba1b6|52b6b600f796bc4d|aHVwdV8zMjJlOWZlOTFiMmU5YzQ2; us=abfc3693d524f46740aa578fa1a8634c8c387b34d3f25061e47ee2f2f56be7064bc95fd94619387715d1a89347a7d7f8bd53b55945019b2cb1f80800baf19546; ua=26044540; __dacevst=a4ec7e68.e4f595e6|1562674289575");
//        driver.manage().addCookie(hupuSS);
//        // 访问传说后台登录页面
//        driver.get("http://test.mobileapi.hupu.com/chuanshuo/index");
//        // 查找虎扑登录页面账号，密码的输入框
//        WebElement password  = driver.findElement(By.name("token"));
//        WebElement login = driver.findElement(By.className("text-input"));
//
//        // 在输入框元素中输入密码
//        password.sendKeys("chuanshuo");
//        login.submit();
//    }
//}
