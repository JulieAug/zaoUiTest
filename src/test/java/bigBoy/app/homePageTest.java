package bigBoy.app;

import com.alibaba.fastjson.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.RestClient;


/**
 * @program: hupuTest
 * @description: app首页
 * @author: zhuli
 * @create: 2020-10-26 10:49
 **/
public class homePageTest {

    private final String  apiDomain = "http://bigboy-sit.hupu.com";

    private String project = "1";//Android--iOS

    private String version = "1";//版本号

    /**
     * 首页轮播图&热门商品查询
     * @throws Exception
     */
    @Test(priority = 1)
    public void queryHomePageTopTest() throws Exception{
        RestClient rc = new RestClient(apiDomain,"/appapi/"+project+"/"+version+"/homePage/queryTopInfo");
        JSONObject jsonObj = rc.get();
        System.out.println("调用查看资源位&商品接口，接口返回信息：" + jsonObj);
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");
    }

    /**
     * 首页帖子列表
     * @throws Exception
     */
    @Test(priority = 2)
    public void queryHomePageThreadListTest() throws Exception{
        RestClient rc = new RestClient(apiDomain,"/appapi/"+project+"/"+version+"/homePage/queryThreadList");
        rc.setHeader("cid","123456");
        JSONObject jsonObj = rc.get();
        System.out.println("调用首页推荐列表接口，接口返回信息：" + jsonObj);
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");
    }
}
