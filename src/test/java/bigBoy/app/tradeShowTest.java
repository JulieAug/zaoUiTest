package bigBoy.app;

import com.alibaba.fastjson.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.RestClient;

import java.util.List;

/**
 * @program: hupuTest
 * @description: 展览页面
 * @author: zhuli
 * @create: 2020-11-09 14:40
 **/
public class tradeShowTest {
    private final String apiDomain = "http://bigboy-sit.hupu.com";

    private String project = "1";//Android--iOS

    private String version = "1";//版本号

    private final String queryAppTradeShowListServive = "/appapi/"+project+"/"+version+"/trade/show/queryAppTradeShowList";

    private final String queryDisplayWindowDetailService = "/appapi/"+project+"/"+version+"/display/show/queryDisplayInfo";

    private String displayWindowId;

    /**
     * 展览页面接口
     * @throws Exception
     */
    @Test(priority = 1)
    public void queryAppTradeShowListTest() throws Exception{
        RestClient rc = new RestClient(apiDomain,queryAppTradeShowListServive);
        JSONObject jsonObj = rc.get();
        System.out.println("调用查看展览页面接口，接口返回信息：" + jsonObj);
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");
        displayWindowId = jsonObj.getJSONArray("data").getJSONObject(0).getString("id");
    }

    /**
     * 查看展览下某一个橱窗的信息
     * @throws Exception
     */
    @Test(priority = 2)
    public void queryDisplayWindowDetailTest() throws Exception{
        RestClient rc = new RestClient(apiDomain,queryDisplayWindowDetailService);
        loginTest login = new loginTest();
        String token = login.checkSecurityCodeSTest();
        rc.params("token",token);
        rc.params("id",displayWindowId);
        JSONObject jsonObj = rc.get();
        System.out.println("调用查看展览页面接口，接口返回信息：" + jsonObj);
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");
    }
}
