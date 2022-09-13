package bigBoy.app;

import bigBoyUtils.DBUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.RestClient;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: hupuTest
 * @description: 举报管理
 * @author: zhuli
 * @create: 2020-11-09 15:16
 **/
public class infromTest {
    private final String apiDomain = "http://bigboy-sit.hupu.com";

    private String project = "1";//Android--iOS

    private String version = "1";//版本号

    private final String addInformChannelServive = "/appapi/"+project+"/"+version+"/inform/addInformChannel";

    private final String addInformReplyService = "/appapi/"+project+"/"+version+"/inform/addInformReply";

    private BigInteger goodsId = DBUtil.getForValue("select id from goods_pro order by ct asc limit 1;","bigBoyData.properties");

    private String goodsName = DBUtil.getForValue("select name from goods_pro order by ct asc limit 1;","bigBoyData.properties");

    private Long shopId = DBUtil.getForValue("select id from shops order by ct asc limit 1;","bigBoyData.properties");

    private String shopName = DBUtil.getForValue("select name from shops order by ct asc limit 1;","bigBoyData.properties");


    /**
     * 举报渠道
     * @throws Exception
     */
//    @Test(priority = 1)
    public void addInformChannelTest() throws Exception{
        RestClient rc = new RestClient(apiDomain,addInformChannelServive);
        loginTest login = new loginTest();
        String cookie = login.checkSecurityCodeSTest();
        rc.setHeader("Cookie",cookie);
//        rc.setHeader("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MDQ5MDg5NjEsInVzZXJJZCI6Mn0.6ZyYx09LVx54nfdwg8sB9Aaa-IGyrSCdjN1zVGaRYpc");
        Map<String,Object> map = new HashMap<>();
        map.put("reason","这个渠道信息不准！！！");
        map.put("channel","渠道");
        map.put("goodsCategory","高达");
        map.put("goodsId",goodsId);
        map.put("goodsName",goodsName);
        map.put("shopId",shopId);
        map.put("shopName",shopName);
        rc.body(JSONArray.toJSON(map));
        JSONObject jsonObj = rc.post();
        System.out.println("调用接口，接口返回信息：" + jsonObj);
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");
    }
}
